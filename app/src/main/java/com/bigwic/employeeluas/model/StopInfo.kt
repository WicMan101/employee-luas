package com.bigwic.employeeluas.model

//@Root(name = "stopInfo")
data class StopInfo constructor(
    var created: String,
    var stop: String,
    var stopAbv: String,
    var message: String,
    var direction: List<Direction>
)
