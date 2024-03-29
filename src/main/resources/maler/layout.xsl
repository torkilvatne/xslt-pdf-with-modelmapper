<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>

                <!-- Here you define the page layouts with names -->
                <fo:simple-page-master master-name="simpleA4"
                                       page-height="29.7cm"
                                       page-width="21.0cm"
                                       margin="2cm">
                    <fo:region-body/>
                </fo:simple-page-master>

                <!-- Can define multiple and add them as <fo:page-sequence below -->
                <!--<fo:simple-page-master master-name="lastPage" page-height="297mm" page-width="210mm"
                                       margin-left="18mm" margin-right="27mm" margin-top="13mm" margin-bottom="3mm">
                    <fo:region-body region-name="xsl-region-body" margin-top="34mm" margin-bottom="10mm"/>
                    <fo:region-before extent="34mm"/>
                    <fo:region-after extent="13mm"/>
                </fo:simple-page-master>-->


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
        <xsl:call-template name="autogeneratedTable"/>
        <xsl:call-template name="pageBreak"/>
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
            <fo:table-cell padding="5px">
                <fo:block>
                    <fo:inline>
                        <xsl:value-of select="./name/firstName/text()"/>
                    </fo:inline>
                    <fo:inline>
                        <xsl:value-of select="./name/lastName/text()"/>
                    </fo:inline>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell padding="5px">
                <fo:block>
                    <xsl:value-of select="./customerId/text()"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell padding="5px">
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
                Product #<xsl:value-of select="./product/productId/text()"/>:
            </fo:inline>
            <fo:inline>
                <xsl:value-of select="./product/price/text()"/>,-
            </fo:inline>
        </fo:block>
    </xsl:template>

    <xsl:template match="qrCode">
        <fo:block>
            <fo:external-graphic>
                <xsl:attribute name="src">
                    <xsl:value-of select="./url"/>
                </xsl:attribute>
            </fo:external-graphic>
        </fo:block>
    </xsl:template>

    <xsl:template name="checkbox">
        <fo:instream-foreign-object margin-right="5px">
            <svg xmlns="http://www.w3.org/2000/svg">
                <g transform="translate(10, 10)">
                    <g transform="translate(0)">
                        <g class="bar">
                            <rect x="0" y="0" height="10" width="10"
                                  style="fill:white;stroke:black;stroke-width:1;fill-opacity:1;stroke-opacity:1"/>
                        </g>
                    </g>
                </g>
            </svg>
        </fo:instream-foreign-object>
    </xsl:template>

    <xsl:template name="pageBreak">
        <fo:block page-break-before="always">
            This is a page break
        </fo:block>
    </xsl:template>

    <xsl:template name="listWithNumbers">
        <fo:block>
            <fo:list-block>
                <fo:list-item>
                    <fo:list-item-label end-indent="label-end()" background-color="yellow">
                        <fo:block>(<xsl:value-of select="position()"/>)</fo:block>
                    </fo:list-item-label>
                    <fo:list-item-body start-indent="body-start()">
                        <fo:block>ITEM 1</fo:block>
                    </fo:list-item-body>
                </fo:list-item>
                <fo:list-item>
                    <fo:list-item-label end-indent="label-end()">
                        <fo:block>(<xsl:value-of select="position()"/>)</fo:block>
                    </fo:list-item-label>
                    <fo:list-item-body start-indent="body-start()">
                        <fo:block>ITEM 2</fo:block>
                    </fo:list-item-body>
                </fo:list-item>
                <fo:list-item>
                    <fo:list-item-label end-indent="label-end()">
                        <fo:block>(<xsl:value-of select="position()"/>)</fo:block>
                    </fo:list-item-label>
                    <fo:list-item-body start-indent="body-start()">
                        <fo:block>ITEM 2</fo:block>
                    </fo:list-item-body>
                </fo:list-item>
            </fo:list-block>
        </fo:block>
    </xsl:template>

    <xsl:template name="autogeneratedTable">
        <fo:block page-break-before="always">
            <fo:block>
                Did we miss any orders?
            </fo:block>
        </fo:block>
        <fo:table font-size="8pt" width="100%" line-height="20px" border-collapse="collapse" space-after="5pt"
                  xmlns:fox="http://xmlgraphics.apache.org/fop/extensions" fox:header="true"
                  page-break-after="auto">

            <fo:table-column column-number="1" column-width="75%"/>
            <fo:table-column column-number="2" column-width="25%"/>
            <fo:table-header>
                <!-- TODO: Denne her skal ha en border på 1 px solid og ikke noe luft -->
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block border-bottom-width="1.5px" margin-right="20px" border-top="0px" border-left="0px"
                                  border-right="0px" border-style="solid" padding-top="20px">
                            What did you buy?
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom-width="1.5px" margin-right="20px" border-top="0px" border-left="0px"
                                  border-right="0px" border-style="solid" padding-top="20px">
                            How much did it cost?
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-header>
            <xsl:variable name="cells" select="2"/>
            <xsl:variable name="rows" select="10"/>
            <fo:table-body>
                <xsl:for-each select="(//*)[position()&lt;=$rows]">
                    <fo:table-row>
                        <xsl:for-each select="(//*)[position()&lt;=$cells]">
                            <fo:table-cell>
                                <fo:block border-bottom-width="0.5px" margin-right="20px" border-top="0px"
                                          border-left="0px" border-right="0px" border-style="solid"
                                          padding-top="20px">
                                </fo:block>
                            </fo:table-cell>
                            <xsl:value-of select="position()"/>
                        </xsl:for-each>
                    </fo:table-row>
                    <xsl:value-of select="position()"/>
                </xsl:for-each>
            </fo:table-body>
        </fo:table>
    </xsl:template>

</xsl:stylesheet>