package com.example.clasificacionf1.model

import com.example.clasificacionf1.R
import com.example.clasificacionf1.ViewModel

object PilotoRepository {


    private fun getCarrera(viewModel: ViewModel): List<Int> {
        return when (viewModel.carrera) {
            1 -> listOf(6,12,4,18,2,9,15,1,5,8,11,19,7,10,17,20,3,14,13,16)
            2 -> listOf(14,5,7,9,1,12,18,20,3,10,4,16,11,6,19,13,17,8,2,15)
            3 -> listOf(8,2,15,14,1,7,9,5,12,11,6,19,13,17,18,20,3,10,4,16)
            4 -> listOf(19,13,17,5,1,11,6,20,3,10,4,16,18,8,2,15,14,12,7,9)
            5 -> listOf(6,2,13,17,5,1,15,14,12,7,20,3,10,4,19,11,16,18,8,9)
            6 -> listOf(19,13,18,8,2,15,17,5,1,11,6,20,3,10,4,16,14,12,7,9)
            7 -> listOf(19,13,14,12,5,1,11,6,20,3,10,4,7,9,17,16,18,8,2,15)
            8 -> listOf(15,3,10,4,11,6,20,16,18,14,19,13,8,2,12,7,9,17,5,1)
            9 -> listOf(14,12,19,13,4,16,18,8,2,15,7,9,17,5,1,11,6,20,3,10)
            10 -> listOf(16,10,4,15,14,1,11,6,20,3,7,9,12,18,8,2,19,13,17,5)
            else -> listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)
        }
    }

    fun getPilotoLista(viewModel: ViewModel): List<Piloto>{

        val carrera = getCarrera(viewModel)

        return  listOf(
            Piloto(
            nameRes = R.string.piloto1,
            teamRes = R.string.equipo1,
            positionRes  = carrera[0],
            imageRes = R.drawable.piloto1
        ),
        Piloto(
            nameRes = R.string.piloto2,
            teamRes = R.string.equipo1,
            positionRes  = carrera[1],
            imageRes = R.drawable.piloto2
        ),
        Piloto(
            nameRes = R.string.piloto3,
            teamRes = R.string.equipo2,
            positionRes  = carrera[2],
            imageRes = R.drawable.piloto3
        ),
        Piloto(
            nameRes = R.string.piloto4,
            teamRes = R.string.equipo2,
            positionRes  = carrera[3],
            imageRes = R.drawable.piloto4
        ),
        Piloto(
            nameRes = R.string.piloto5,
            teamRes = R.string.equipo3,
            positionRes  = carrera[4],
            imageRes = R.drawable.piloto5
        ),
        Piloto(
            nameRes = R.string.piloto6,
            teamRes = R.string.equipo3,
            positionRes  = carrera[5],
            imageRes = R.drawable.piloto6
        ),
        Piloto(
            nameRes = R.string.piloto7,
            teamRes = R.string.equipo4,
            positionRes  = carrera[6],
            imageRes = R.drawable.piloto7
        ),
        Piloto(
            nameRes = R.string.piloto8,
            teamRes = R.string.equipo4,
            positionRes  = carrera[7],
            imageRes = R.drawable.piloto8
        ),
        Piloto(
            nameRes = R.string.piloto9,
            teamRes = R.string.equipo5,
            positionRes  = carrera[8],
            imageRes = R.drawable.piloto9
        ),
        Piloto(
            nameRes = R.string.piloto10,
            teamRes = R.string.equipo5,
            positionRes  = carrera[9],
            imageRes = R.drawable.piloto10
        ),
        Piloto(
            nameRes = R.string.piloto11,
            teamRes = R.string.equipo6,
            positionRes  = carrera[10],
            imageRes = R.drawable.piloto11
        ),
        Piloto(
            nameRes = R.string.piloto12,
            teamRes = R.string.equipo6,
            positionRes  = carrera[11],
            imageRes = R.drawable.piloto12
        ),
        Piloto(
            nameRes = R.string.piloto13,
            teamRes = R.string.equipo7,
            positionRes  = carrera[12],
            imageRes = R.drawable.piloto13
        ),
        Piloto(
            nameRes = R.string.piloto14,
            teamRes = R.string.equipo7,
            positionRes  = carrera[13],
            imageRes = R.drawable.piloto14
        ),
        Piloto(
            nameRes = R.string.piloto15,
            teamRes = R.string.equipo8,
            positionRes  = carrera[14],
            imageRes = R.drawable.piloto15
        ),
        Piloto(
            nameRes = R.string.piloto16,
            teamRes = R.string.equipo8,
            positionRes  = carrera[15],
            imageRes = R.drawable.piloto16
        ),
        Piloto(
            nameRes = R.string.piloto17,
            teamRes = R.string.equipo9,
            positionRes  = carrera[16],
            imageRes = R.drawable.piloto17
        ),
        Piloto(
            nameRes = R.string.piloto18,
            teamRes = R.string.equipo9,
            positionRes  = carrera[17],
            imageRes = R.drawable.piloto18
        ),
        Piloto(
            nameRes = R.string.piloto19,
            teamRes = R.string.equipo10,
            positionRes  = carrera[18],
            imageRes = R.drawable.piloto19
        ),
        Piloto(
            nameRes = R.string.piloto20,
            teamRes = R.string.equipo10,
            positionRes  = carrera[19],
            imageRes = R.drawable.piloto20
        )
        )


    }
}
