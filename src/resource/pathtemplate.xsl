<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="1.0"
>
    <xsl:strip-space elements="*"/>
    <xsl:output method="text"/>

    <xsl:template match="svg">
            <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="g">
            <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="path">
        <xsl:text>"</xsl:text>
        <xsl:value-of select="attribute::d"/>
        <xsl:text>",&#xa;</xsl:text>
    </xsl:template>

    <xsl:template match="text">
    </xsl:template>
</xsl:stylesheet>
