package com.bigwic.employeeluas.model

import com.tickaroo.tikxml.annotation.*

@Xml
data class Direction constructor(
    @Attribute
    var name: String,
    @Element
    var tram: List<Tram>?
)