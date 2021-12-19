<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:date="http://exslt.org/dates-and-times"
                xmlns:str="http://exslt.org/strings"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:xf="http://www.ecrion.com/xf/1.0" xmlns:slt="http://www.w3.org/1999/XSL/Transform"
                extension-element-prefixes="date str">
  <xsl:output indent="no" encoding="utf-8" />
  <xsl:param name="XFCrtLocalDate">2021-09-13</xsl:param>
  <xsl:param name="XFCrtLocalTime">16:24:00</xsl:param>
  <xsl:param name="XFCrtLocalDateTime">2021-09-13T16:24:00</xsl:param>
  <xsl:param name="XFCrtUTCDate">2021-09-13</xsl:param>
  <xsl:param name="XFCrtUTCTime">11:29:22</xsl:param>
  <xsl:param name="XFCrtUTCDateTime">2021-09-13T11:29:22</xsl:param>
  <xsl:param name="XFOutputFormat" />
  <xf:defaultTabStop val="25mm" />
  <xsl:variable name="tekster" select="'/PapirSkattemelding/papirskattemeldingTekster'" />


  <!-- ========================= ROOT TEMPLATE =========================== -->
  <xsl:template match="/">
    <fo:root xml:lang="nb" xf:vdp-enable="false" language="NO" font-family="Arial">
      <fo:layout-master-set>
        <!-- Første side definisjon -->
        <fo:simple-page-master master-name="Foerste" page-height="297mm" page-width="210mm"
                               xf:auto-adjust-regions="false"
                               margin-left="10mm" margin-right="27mm" margin-top="13mm" margin-bottom="3mm">
          <fo:region-body margin-top="115mm" margin-bottom="10mm" />
          <fo:region-before extent="0mm" />
          <fo:region-after extent="0mm" />
        </fo:simple-page-master>

        <!-- Påfølgende sider definisjon -->
        <fo:simple-page-master master-name="Kommende" page-height="297mm" page-width="210mm"
                               xf:auto-adjust-regions="false"
                               margin-left="10mm" margin-right="27mm" margin-top="13mm" margin-bottom="3mm">
          <fo:region-body region-name="xsl-region-body" margin-top="34mm" margin-bottom="10mm" />
          <fo:region-before extent="34mm" />
          <fo:region-after extent="13mm" />
        </fo:simple-page-master>

        <!-- Sekvens av sider definisjon -->
        <fo:page-sequence-master master-name="Normalsider">
          <fo:repeatable-page-master-alternatives maximum-repeats="no-limit">
            <fo:conditional-page-master-reference master-reference="Foerste" page-position="first" />
            <fo:conditional-page-master-reference master-reference="Kommende" />
          </fo:repeatable-page-master-alternatives>
        </fo:page-sequence-master>
      </fo:layout-master-set>

      <!-- Innhold på side sekvens med topp og bunntekst -->
      <fo:page-sequence master-reference="Normalsider" language="NO">
        <fo:title>Skattemeldingen for 2021</fo:title>

        <fo:static-content flow-name="xsl-region-before">
          <!-- main header on every page -->
          <xsl:call-template name="logo-skatteetaten" />
          <!-- sub header -->
          <fo:retrieve-marker retrieve-class-name="subHeader" retrieve-position="first-starting-within-page" />
        </fo:static-content>

        <fo:flow flow-name="xsl-region-body" xsl:use-attribute-sets="tekst">
          <fo:block language="NO">
            <!-- sub header for the first page -->
            <fo:marker marker-class-name="subHeader">
              <fo:block-container absolute-position="absolute" left="20mm" top="15.5mm">
                <fo:block font-size="4m" language="NO">
                  Text 1
                  Text 2
                </fo:block>
              </fo:block-container>
              <fo:block-container absolute-position="absolute" left="90mm" top="0mm">
                <fo:block language="NO" font-weight="bolder" font-size="10mm" role="H1">Skattemelding
                </fo:block>
                <fo:block language="NO" font-weight="bold" font-size="5mm" role="H2">for formues- og
                  inntektsskatt
                  2021
                </fo:block>
              </fo:block-container>
              <fo:block-container absolute-position="absolute" left="175mm" top="-14.2mm">
                <fo:block>
                  <fo:external-graphic>
                    <xsl:attribute name="src">
                      Text URL
                    </xsl:attribute>
                  </fo:external-graphic>
                </fo:block>
              </fo:block-container>
            </fo:marker>
          </fo:block>
          <fo:block>
            <!-- sub header for the not-first pages -->
            <fo:marker marker-class-name="subHeader">
              <fo:block-container absolute-position="absolute" left="15mm" top="16mm">
                <fo:block text-align="right" language="NO">
                  <xsl:value-of select="concat(//partInfo/sammenstiltnavn/text(), ' ')" />
                  <xsl:value-of select="concat(//partInfo/gjeldendeTin/text(), '')" />
                </fo:block>
              </fo:block-container>
            </fo:marker>
          </fo:block>
          <xsl:for-each select="CustomerPDF">
            <xsl:apply-templates select="customer" />
          </xsl:for-each>
        </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>


  <xsl:template name="logo-skatteetaten">
    <fo:block language="NO" xmlns:fo="http://www.w3.org/1999/XSL/Format">
      <fo:external-graphic src="url(data:image/png;base64,i iVBORw0KGgoAAAANSUhEUgAAAV4AAACWBAMAAABkyf1EAAAAG1BMVEXMzMyWlpacnJyqqqrFxcWxsbGjo6O3t7e+vr6He3KoAAAACXBIWXMAAA7EAAAOxAGVKw4bAAAEcElEQVR4nO2aTW/bRhCGh18ij1zKknMkbbf2UXITIEeyMhIfRaF1exQLA/JRclslRykO+rs7s7s0VwytNmhJtsA8gHZEcox9PTs7uysQgGEYhmEYhmEYhmEYhmEYhmEYhmEYhmEYhmEYhmEYhmGYr2OWRK/ReIKI8Zt7Hb19wTcQ0uTkGh13bQupcw7gPOvdo12/5CzNtNR7xLUtNtT3CGBQ6g3InjY720pvofUec22LJPr8PhEp2OMPyI40PdwWUdronCu9yQpdPx53bQlfLKnfOVhlnDYRBXve4Ov+IZTeMgdedm0NR+xoXJeQvdJ3CvziykSukwil16W/Oe7aGjIjqc/9ib4jQlJy0uArtN4A0+cvXFvDkmUJ47sJ1Y1ATLDNVXZkNPIepQzxy1ki9fqiwbUj/I+64zxWNzyZnPuhvohJ9K70VvXBixpcu2SAHU+Xd9EKdEJDNpYP3AQr3bQSpPQ6Y6/4dl1z7ZDbArsszjA7L0g7ibB0CDcidUWVoErvIMKZh2Xs0LUzcLW6V5NfiUgNEbaYmAVL6bXl0nJRc+1S72ua/D/cTjGPlQj7eUqd7A096rYlRjdPYlhz7VIvxpVG3cemDKF+WAwLY/6XelOZKTXXzsC4xvDjjtSN6kHLhLke6PrwM8h1raf40qjrGO7H9aTEbduucjS04ZrYU/4iuS5Z2Hdt0rvCLFdmLEXcU30AGddST62o+sLcf5l6k7CP+ru4pLYqX/VFyxbm/utQbx/r22ZEbTb2f5I2kns1Y1OQR8ZyofX+TjJxj1Rz7QQVnf1QzR26Oth0ueJVYcRP6ZUPac/Rx/5M6ixO1dhSrT3Y1DpiYmx3tF4ZUdpz9LD/dSg9PXES0LB71BwcGjKROuV28lnvnv7HHJsezheBGH5+X2CfSfRbMKW+5aGs3JFjMrjGibJc0S7TJzqjHrh2hDybj9XRXNZa89Aro55XBdbW5wti2c/5WJ7jJ1RolVUn/HWpb0I58Tziup6Rx7Dm2hnbRP1GM9PW/NFmQ4PtVRVN63Wvxfmu5sowDMMwDMMwDMMwDMMwDMMwDMMwzL+CpT//F/6beoV8zb2Jmt4Qryx6lTUCsENQ75HOkhXAO3EPVgyQtKtUy3C/e+FJg17Zjnew1Xrdb9InbG4WqfUAftG+WhLwPVyfg536+MU7m4C1CMk4ZznpXZzDYI1PDL2nS1hpvc5cNd7E2sJg05Fe7/7d3Fln8Cvc3bwB616auxsKl4WPghjemHrDqyDWeu1UNW5s2btPnSQ75oOdunEwWazfwgVG0kqluYCM9OIjWOGnfA2b9G4Ha63XKpvQ8perTvTifJNhi6+WMWmi7smEZf6G8MmhlyGq+NqP8GV84TLuJr7UIQVx+bDEoEpRZIz42gs40OuN4Mv8hXzelV7KX1isH+ewTWckikyVv+CfHuqVF7I16gN0VKypX6wPsE+zFPzkinolU9UH8OMGvSpnZqKsv13p/RsMun6X5x/y2LeAr8O66lsBwzBMP/wJfyGq8pgBk6IAAAAASUVORK5CYII=)" content-width="15mm">
        <xsl:attribute name="xf:compat-id">
          id2829672
          <xsl:value-of select="generate-id(.)" />
        </xsl:attribute>
      </fo:external-graphic>
    </fo:block>
  </xsl:template>

  <!-- =========================== Default Templates ================================ -->
  <xsl:template match="customer">
    <fo:block font-size="8pt" language="NO" margin-bottom="2mm">
      <xsl:value-of select="./customerId/text()" />
    </fo:block>
    <fo:block language="NO" margin-bottom="2mm" role="H2">
      <xsl:value-of select="./name/firstname/text()" />
    </fo:block>
  </xsl:template>


</xsl:stylesheet>
