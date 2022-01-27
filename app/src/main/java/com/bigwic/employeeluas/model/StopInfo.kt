package com.bigwic.employeeluas.model

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class StopInfo constructor(
    @Attribute
    var created: String,
    @Attribute(name = "stop")
    var stop: String,
    @Attribute
    var stopAbv: String,
    @PropertyElement
    var message: String,
    @Element
    var direction: List<Direction>?
)
