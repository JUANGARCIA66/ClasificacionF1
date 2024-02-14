package com.example.clasificacionf1

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.clasificacionf1.model.Piloto
import com.example.clasificacionf1.model.PilotoRepository
import com.example.clasificacionf1.ui.theme.PilotosTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PilotosList(
    pilotos: List<Piloto>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    viewModel: ViewModel,
    navController : NavHostController,
    start : Boolean = false
) {

    if(start){
        viewModel.goBack = false
        viewModel.carrera = 0
        viewModel.mostrarPosicion = false
        viewModel.raceList = true
        viewModel.mostrarTitulo = false
    }else{
        viewModel.raceList = true
        viewModel.goBack = true
        viewModel.mostrarPosicion = true
        viewModel.mostrarTitulo = true
    }
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    // Fade in entry animation for the entire list
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn(contentPadding = contentPadding) {
            itemsIndexed(pilotos.sortedBy { it.positionRes }) { index, p ->
                PilotosListItem(
                    piloto = p,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        // Animate each list item to slide in vertically

                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) } // staggered entrance
                            )
                        ),
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
    }
}
@Composable
fun PilotosListItem(
    piloto: Piloto,
    modifier: Modifier = Modifier,
    viewModel: ViewModel,
    navController : NavHostController
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = modifier.clickable {
            viewModel.piloto = piloto
            navController.navigate(Ventanas.Pilot.name)
                                      },
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(16.dp))

            ) {
                Image(
                    painter = painterResource(piloto.imageRes),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(piloto.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(piloto.teamRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            if(viewModel.mostrarPosicion){


            BoxWithConstraints (modifier = Modifier.padding(horizontal = 10.dp)){
                Box{
                    Text(
                        text = piloto.positionRes.toString() + "º",
                        style = MaterialTheme.typography.displayLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
            }
        }
    }
}

@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PilotoPreview() {
    val piloto = Piloto(
        R.string.piloto1,
        R.string.equipo1,
        1,
        R.drawable.piloto1,
        R.string.stats_piloto1
    )
    PilotosTheme {
        PilotosListItem(piloto = piloto, viewModel = ViewModel(), navController = rememberNavController())
    }
}

//@Preview("Pilotos List")
@Composable
fun PilotosPreview() {
    PilotosTheme(darkTheme = false) {
        Surface (
            color = MaterialTheme.colorScheme.background
        ) {

            PilotosList(
                pilotos = PilotoRepository.getPilotoLista(ViewModel()),
                viewModel = ViewModel(),
                navController = rememberNavController())
        }
    }
}