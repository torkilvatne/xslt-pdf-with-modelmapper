<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:date="http://exslt.org/dates-and-times"
                xmlns:str="http://exslt.org/strings"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                extension-element-prefixes="date str">

  <xsl:template match="/">
    <fo:root xml:lang="nb" language="NO" font-family="Arial">
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
    <fo:block language="NO">
      <fo:table border="1">
        <fo:tr bgcolor="#9acd32">
          <fo:th>ID</fo:th>
          <fo:th>First name</fo:th>
          <fo:th>Last name</fo:th>
        </fo:tr>
        <xsl:for-each select="customer">
          <fo:tr>
            <fo:td>
              <xsl:value-of select="./customerId/text()" />
            </fo:td>
            <fo:td>
              <xsl:value-of select="./name/firstName/text()" />
            </fo:td>
            <fo:td>
              <xsl:value-of select="./name/lastName/text()" />
            </fo:td>
          </fo:tr>
        </xsl:for-each>
      </fo:table>
    </fo:block>
  </xsl:template>

</xsl:stylesheet>