package com.example.clasificacionf1.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Carrera(
    @StringRes val nameRes: Int,
    val action :() -> Unit,
)
