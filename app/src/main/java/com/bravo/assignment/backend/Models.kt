package com.bravo.assignment.backend

import java.util.*

data class SMS (
    val id : Long,
    val address : String,
    val body : String,
    val date : Date
)
