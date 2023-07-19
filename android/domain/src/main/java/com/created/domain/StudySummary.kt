package com.created.domain

data class StudySummary(
    val id: Long,
    val processingStatus: Int,
    val tier: Int,
    val title: String,
    val date: String,
    val period: Period,
    val currentMember: Int,
    val maximumMember: Int
)
