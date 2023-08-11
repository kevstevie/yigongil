package com.created.team201.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoCreateRequestDto(
    @SerialName("content")
    val content: String,
)
