package com.example.clasificacionf1.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.clasificacionf1.ViewModel

data class Carrera(
    @StringRes val nameRes: Int,
    val action: (viewModel: ViewModel) -> Unit
)
