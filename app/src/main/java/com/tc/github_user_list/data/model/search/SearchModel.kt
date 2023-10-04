package com.tc.github_user_list.data.model.search


import com.google.gson.annotations.SerializedName

data class SearchModel(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean? = false,
    @SerializedName("items")
    val items: List<SearchItemModel?>? = listOf(),
    @SerializedName("total_count")
    val totalCount: Int? = 0
)