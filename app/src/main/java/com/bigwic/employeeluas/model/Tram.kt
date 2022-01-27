package com.bigwic.employeeluas.model

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class Tram constructor(
    @Attribute
    var dueMins: String,
    @Attribute
    var destination: String,
)