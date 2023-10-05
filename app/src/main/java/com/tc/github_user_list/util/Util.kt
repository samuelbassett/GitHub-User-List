package com.tc.github_user_list.util

object Util {
    fun abbreviateNumber(value: Int): String {
        return when {
            value >= 1_000_000 -> String.format("%.1fm", value / 1_000_000.0)
            value >= 1_000 -> String.format("%.1fk", value / 1_000.0)
            else -> value.toString()
        }
    }
}