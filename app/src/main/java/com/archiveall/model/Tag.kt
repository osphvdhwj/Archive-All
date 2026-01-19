package com.archiveall.model

data class Tag(
    val name: String,
    val usageCount: Int = 0,
    val isPrebuilt: Boolean = false
)
