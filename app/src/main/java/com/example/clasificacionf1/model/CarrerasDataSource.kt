package com.example.clasificacionf1.model


import com.example.clasificacionf1.PilotosList
import com.example.clasificacionf1.R
import com.example.clasificacionf1.Ventanas
import com.example.clasificacionf1.ViewModel

object CarrerasRepository {

    val carreraLista = listOf(
        Carrera(
            nameRes = R.string.carrera1,
            action = { viewModel: ViewModel -> viewModel.carrera = 1
                viewModel.titulo = R.string.carrera1}
        ),
        Carrera(
            nameRes = R.string.carrera2,
            action = { viewModel: ViewModel -> viewModel.carrera = 2
                viewModel.titulo = R.string.carrera2}
        ),
        Carrera(
            nameRes = R.string.carrera3,
            action = { viewModel: ViewModel -> viewModel.carrera = 3
                viewModel.titulo = R.string.carrera3}
        ),
        Carrera(
            nameRes = R.string.carrera4,
            action = { viewModel: ViewModel -> viewModel.carrera = 4
                viewModel.titulo = R.string.carrera4}
        ),
        Carrera(
            nameRes = R.string.carrera5,
            action = { viewModel: ViewModel -> viewModel.carrera = 5
                viewModel.titulo = R.string.carrera5}
        ),
        Carrera(
            nameRes = R.string.carrera6,
            action = { viewModel: ViewModel -> viewModel.carrera= 6
                viewModel.titulo = R.string.carrera6}
        ),
        Carrera(
            nameRes = R.string.carrera7,
            action = { viewModel: ViewModel -> viewModel.carrera = 7
                viewModel.titulo = R.string.carrera7}
        ),
        Carrera(
            nameRes = R.string.carrera8,
            action = { viewModel: ViewModel -> viewModel.carrera = 8
                viewModel.titulo = R.string.carrera8}
        ),
        Carrera(
            nameRes = R.string.carrera9,
            action = { viewModel: ViewModel -> viewModel.carrera = 9
                viewModel.titulo = R.string.carrera9}
        ),
        Carrera(
            nameRes = R.string.carrera10,
            action = { viewModel: ViewModel -> viewModel.carrera = 10
                viewModel.titulo = R.string.carrera10}
        )
    )

}
