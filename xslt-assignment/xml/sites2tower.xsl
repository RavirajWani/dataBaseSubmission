<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
	<html>
	<head>
	</head>
	<body>
		<ol>
		<xsl:for-each select="sites/site/tower/equipment">
			
				
					<li >
						<xsl:value-of select="@name"/>
						<xsl:text>Site/Tower </xsl:text>
						<xsl:value-of select="../../@name"/>
						<xsl:text>/ </xsl:text>
						<xsl:value-of select="../@name"/>
						<xsl:text> </xsl:text>
					</li>
			
		</xsl:for-each>
		</ol>
	</body>
	</html>
	</xsl:template>
</xsl:stylesheet>