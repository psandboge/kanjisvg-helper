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
    </xsl:template>

    <xsl:template match="text[not(position()=last())]">
        <xsl:text>{ "transform": "</xsl:text>
        <xsl:value-of select="attribute::transform"/>
        <xsl:text>", "value": "</xsl:text>
        <xsl:apply-templates/>
        <xsl:text>"},&#xa;</xsl:text>
    </xsl:template>

    <xsl:template match="text[position()=last()]">
        <xsl:text>{ "transform": "</xsl:text>
        <xsl:value-of select="attribute::transform"/>
        <xsl:text>", "value": "</xsl:text>
        <xsl:apply-templates/>
        <xsl:text>"}&#xa;</xsl:text>
    </xsl:template>
</xsl:stylesheet>
