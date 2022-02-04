<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-height="29.7cm" page-width="21.0cm" margin="2cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="simpleA4">
                <fo:flow flow-name="xsl-region-body">
                    <xsl:apply-templates/>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

    <xsl:template match="CustomerPDF">
        <fo:block font-family="Helvetica" font-size="24pt" font-weight="bold">
            Customer PDF
        </fo:block>
        <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="customers">
        <fo:table table-omit-header-at-break="true" margin-top="4px" width="100%" font-size="8pt" line-height="14px"
                  border-collapse="collapse" space-after="5pt" xmlns:fox="http://xmlgraphics.apache.org/fop/extensions"
                  fox:header="true"
                  page-break-after="auto">

            <fo:table-column column-number="1" column-width="50%"/>
            <fo:table-column column-number="2" column-width="32%"/>
            <fo:table-column column-number="3" column-width="18%"/>

            <fo:table-header>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block language="NO" font-style="italic" linefeed-treatment="preserve">
                            Name
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block language="NO" font-style="italic" linefeed-treatment="preserve">
                            Id
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block language="NO" font-style="italic" linefeed-treatment="preserve">
                            Order list
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-header>
            <fo:table-body>
                <xsl:apply-templates/>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template match="customer">
        <fo:table-row border-style="solid" border-color="black">
            <fo:table-cell>
                <fo:block>
                    <fo:inline>
                        <xsl:value-of select="./name/firstName/text()"/>
                    </fo:inline>
                    <fo:inline>
                        <xsl:value-of select="./name/lastName/text()"/>
                    </fo:inline>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell>
                <fo:block>
                    <xsl:value-of select="./customerId/text()"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell>
                <fo:block>
                    <xsl:apply-templates select="orders"/>
                </fo:block>
            </fo:table-cell>
        </fo:table-row>
    </xsl:template>

    <xsl:template match="orders">
        <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="order">
        <fo:block>
            <fo:inline>
                - <xsl:value-of select="./product/productId/text()"/>:
            </fo:inline>
            <fo:inline>
                <xsl:value-of select="./product/price/text()"/>
            </fo:inline>
        </fo:block>
    </xsl:template>

</xsl:stylesheet>