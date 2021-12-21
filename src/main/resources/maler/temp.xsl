<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
                xmlns:date="http://exslt.org/dates-and-times"
                xmlns:str="http://exslt.org/strings"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                extension-element-prefixes="date str">
  <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <fo:layout-master-set>
      <fo:simple-page-master master-name="my-page">
        <fo:region-body margin="1in" />
      </fo:simple-page-master>
    </fo:layout-master-set>
    <fo:page-sequence master-reference="my-page">
      <fo:flow flow-name="xsl-region-body">
        <fo:block>Hello, shyam!</fo:block>
        <fo:block>
          <fo:table>
            <fo:table-body>
              <fo:table-row>
                <fo:table-cell border="solid 1px black"
                               text-align="center" font-weight="bold">
                  <fo:block>
                    No.
                  </fo:block>
                </fo:table-cell>
                <fo:table-cell border="solid 1px black"
                               text-align="center" font-weight="bold">
                  <fo:block>
                    Name
                  </fo:block>
                </fo:table-cell>
                <fo:table-cell border="solid 1px black"
                               text-align="center" font-weight="bold">
                  <fo:block>
                    Phone Number
                  </fo:block>
                </fo:table-cell>
              </fo:table-row>
              <fo:table-row>
                <fo:table-cell border="solid 1px black" text-align="center">
                  <fo:block>1</fo:block>
                </fo:table-cell>
                <fo:table-cell border="solid 1px black" text-align="center">
                  <fo:block>Abc</fo:block>
                </fo:table-cell>
                <fo:table-cell border="solid 1px black" text-align="center">
                  <fo:block>90909090909</fo:block>
                </fo:table-cell>
              </fo:table-row>
              <fo:table-row>
                <fo:table-cell border="solid 1px black" text-align="center">
                  <fo:block>2</fo:block>
                </fo:table-cell>
                <fo:table-cell border="solid 1px black" text-align="center">
                  <fo:block>Xyz</fo:block>
                </fo:table-cell>
                <fo:table-cell border="solid 1px black" text-align="center">
                  <fo:block>32323232323</fo:block>
                </fo:table-cell>
              </fo:table-row>
            </fo:table-body>
          </fo:table>
        </fo:block>
      </fo:flow>
    </fo:page-sequence>
  </fo:root>
</xsl:stylesheet>