<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:date="http://exslt.org/dates-and-times"
                xmlns:str="http://exslt.org/strings"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:xf="http://www.ecrion.com/xf/1.0" xmlns:slt="http://www.w3.org/1999/XSL/Transform"
                extension-element-prefixes="date str">

  <xsl:template match="/">
    <fo:root xml:lang="nb" xf:vdp-enable="false" language="NO" font-family="Arial">
      <fo:block break-before="page" font-size="1.5em" font-weight="bold" id="EN006">Starting state</fo:block>
      <xsl:for-each select="CustomerPDF">
        <fo:block language="NO">
          Dette er en CustomerPDF
        </fo:block>
        <xsl:apply-templates select="customers" />
      </xsl:for-each>
    </fo:root>
  </xsl:template>

  <xsl:template match="customers">
    <fo:block language="NO">
      Customer list:
    </fo:block>
    <xsl:for-each select="customers">
      <fo:block language="NO">
        A Customer:
      </fo:block>
      <xsl:apply-templates select="customer" />
    </xsl:for-each>
  </xsl:template>

  <xsl:template match="customer">
    <fo:block font-size="8pt" language="NO" margin-bottom="2mm">
      <xsl:value-of select="./customerId/text()" />
    </fo:block>
    <fo:block language="NO" margin-bottom="2mm" role="H2">
      <xsl:value-of select="./name/firstname/text()" />
    </fo:block>
  </xsl:template>

</xsl:stylesheet>