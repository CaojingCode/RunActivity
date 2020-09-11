package com.caojing.runactivity.entity

data class SystemDataBean(
    val children: List<SystemDataBean>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int,
    var isSelect: Boolean=false
)