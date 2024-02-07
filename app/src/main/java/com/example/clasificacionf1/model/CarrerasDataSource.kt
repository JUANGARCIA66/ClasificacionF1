package com.example.clasificacionf1.model


import com.example.clasificacionf1.PilotosList
import com.example.clasificacionf1.R
import com.example.clasificacionf1.ViewModel

object CarrerasRepository {
    val viewModel = ViewModel()

    val carreraLista = listOf(
        Carrera(
            nameRes = R.string.carrera1,
            action = { viewModel.carrera.value = 1 }
        ),
        Carrera(
            nameRes = R.string.carrera2,
            action = { viewModel.carrera.value = 2
            }
        ),
        Carrera(
            nameRes = R.string.carrera3,
            action = { viewModel.carrera.value = 3 }
        ),
        Carrera(
            nameRes = R.string.carrera4,
            action = { viewModel.carrera.value = 4 }
        ),
        Carrera(
            nameRes = R.string.carrera5,
            action = { viewModel.carrera.value = 5 }
        ),
        Carrera(
            nameRes = R.string.carrera6,
            action = { viewModel.carrera.value = 6 }
        ),
        Carrera(
            nameRes = R.string.carrera7,
            action = { viewModel.carrera.value = 7 }
        ),
        Carrera(
            nameRes = R.string.carrera8,
            action = { viewModel.carrera.value = 8 }
        ),
        Carrera(
            nameRes = R.string.carrera9,
            action = { viewModel.carrera.value = 9 }
        ),
        Carrera(
            nameRes = R.string.carrera10,
            action = { viewModel.carrera.value = 10 }
        )
    )

}
