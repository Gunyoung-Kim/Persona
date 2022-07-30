package com.gunyoung.persona.common.util

fun String.toLongOrElseNull(): Long? =
    try {
        this.toLong()
    } catch (e: Exception) {
        null
    }
