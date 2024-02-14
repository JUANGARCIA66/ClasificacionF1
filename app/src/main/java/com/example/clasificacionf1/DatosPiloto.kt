package com.example.clasificacionf1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clasificacionf1.model.Piloto

@Composable
fun PilotDetails(viewModel: ViewModel, modifier: Modifier = Modifier){

    val piloto = viewModel.piloto

    Card(modifier = Modifier.padding(30.dp)){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen del jugador
        Image(
            painter = painterResource(piloto.imageRes),
            contentDescription = stringResource(id = piloto.nameRes),
            modifier = Modifier.size(200.dp).background(MaterialTheme.colorScheme.background, RoundedCornerShape(15.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre del jugador
        Box(
            modifier = Modifier
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
                .background(MaterialTheme.colorScheme.onSurfaceVariant, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = piloto.nameRes),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant // Color del texto
            )
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
                .sizeIn(minHeight = 40.dp)
                .background(MaterialTheme.colorScheme.outline, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = stringResource(id = piloto.teamRes),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(15.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            )
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
                .sizeIn(minHeight = 40.dp)
                .background(MaterialTheme.colorScheme.outline, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = stringResource(id = piloto.estadisticas),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(15.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            )
        }

    }

    }
}

@Preview
@Composable
fun verCarta(){
    var piloto = Piloto(R.string.piloto1, R.string.equipo1,1, R.drawable.piloto1, R.string.stats_piloto1)

    PilotDetails(viewModel = ViewModel())
}


@Composable
fun HomeScreen(
navController: NavController,
viewModel: ViewModel
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick  = { navController.navigate(Ventanas.Start.name) },
            modifier = Modifier
                .size(200.dp)

        ){
            Box(
                modifier = Modifier
                    .size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.ic_launcher_icon),
                    contentDescription = "Icono F1",
                    modifier = Modifier.size(200.dp)
                )
            }
        }
    }
}


