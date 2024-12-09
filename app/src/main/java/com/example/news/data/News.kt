package com.example.news.data

import android.media.Image
import androidx.annotation.DrawableRes

data class News(
    val title: String,
    val desc: String,
    @DrawableRes val image: Int
)
