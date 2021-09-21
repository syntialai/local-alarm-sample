package com.syntia.local_alarm_sample.data.source.response

import com.google.gson.annotations.SerializedName

data class ListItemResponse<T>(

    val results: List<T>,

    @SerializedName("total_results")
    val totalResults: Int)
