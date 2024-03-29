package com.example.clasificacionf1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.clasificacionf1.model.Carrera
import com.example.clasificacionf1.model.CarrerasRepository
import com.example.clasificacionf1.model.Piloto
import com.example.clasificacionf1.model.PilotoRepository
import com.example.clasificacionf1.ui.theme.PilotosTheme


class ViewModel {
    var elegirCarrera by mutableStateOf(false)
    var carrera by mutableStateOf(0)
    var pilotoLista by mutableStateOf<List<Piloto>>(emptyList())
    var titulo by mutableStateOf(R.string.carrera1)
    var goBack by mutableStateOf(false)
    var raceList by mutableStateOf(true)
    var piloto by mutableStateOf(Piloto(R.string.piloto1, R.string.equipo1,1, R.drawable.piloto1, R.string.stats_piloto1))
    var mostrarPosicion by mutableStateOf(false)
    var mostrarTitulo by mutableStateOf(true)
    fun initPilotoLista() {
        pilotoLista = PilotoRepository.getPilotoLista(this)

    }
}
enum class Ventanas(@StringRes val title: Int){
    Start(title = R.string.start),
    Race(title= R.string.race),
    Pilot(title= R.string.pilot),
    Home(title= R.string.home)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PilotosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PilotosApp()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PilotosApp(
        navController: NavHostController = rememberNavController()

    ) {
        val viewModel = remember { ViewModel() }
        viewModel.initPilotoLista()
        Scaffold(
            modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(viewModel, navController)

            }
        ) {
                NavHost(
                    navController = navController,
                    startDestination = Ventanas.Home.name,
                    modifier = Modifier.padding(top = 88.dp)
                ) {
                    composable(route = Ventanas.Home.name){

                        HomeScreen(navController = navController, viewModel = viewModel)
                    }
                    composable(route = Ventanas.Start.name) {

                            if(viewModel.carrera == 0){
                                PilotosList(
                                pilotos = viewModel.pilotoLista,
                                viewModel = viewModel,
                                navController = navController,
                                start = true

                            )
                            }
                    }
                    composable(route = Ventanas.Race.name) {

                        PilotosList(
                            pilotos = viewModel.pilotoLista,
                            viewModel = viewModel,
                            navController = navController
                        )


                    }
                    composable(route = Ventanas.Pilot.name) {

                        viewModel.mostrarTitulo = false
                        viewModel.raceList = false
                        viewModel.goBack = true
                        PilotDetails(viewModel = viewModel)

                    }

                }


            }
        }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBar(viewModel: ViewModel,
                  navController : NavHostController) {
        var expanded by remember { mutableStateOf(false) }

        LaunchedEffect(viewModel.elegirCarrera) {
            if (viewModel.elegirCarrera) {
                expanded = true
                viewModel.elegirCarrera = false
            }
        }

        CenterAlignedTopAppBar(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .shadow(20.dp),
            title = {
                if(!viewModel.goBack || !viewModel.mostrarTitulo){
                    IconButton(
                        onClick  = { navController.navigate(Ventanas.Home.name) },
                        modifier = Modifier
                            .size(50.dp)

                    ){
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color(0xFF2B2B2B), RoundedCornerShape(25.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.mipmap.ic_launcher_icon),
                                contentDescription = "Icono F1",
                                modifier = Modifier.size(40.dp),
                            )
                        }
                    }
                }else{
                Text(
                    text = stringResource(viewModel.titulo),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            navigationIcon = {
                if(viewModel.goBack)
                {
                IconButton(
                    onClick  = {
                        viewModel.carrera = 0
                            navController.navigate(Ventanas.Start.name)
                    },
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.TwoTone.ArrowBack,
                        contentDescription = "Menú",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
                 },
            actions = {if(viewModel.raceList){


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = { viewModel.elegirCarrera = true },
                        modifier = Modifier.padding(end = 10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Menu,
                            contentDescription = "Icono Menu",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
    }

        )
        if (expanded) {
            // Renderiza el menú de carreras
            MenuCarreras(
                expanded = expanded,
                setExpanded = { expanded = it },
                carreras = CarrerasRepository.carreraLista,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
    @Composable
    private fun MenuCarreras(
        expanded: Boolean,
        setExpanded: (Boolean) -> Unit,
        carreras : List<Carrera>,
        viewModel: ViewModel,
        navController : NavHostController
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.TopEnd)
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { setExpanded(false) }
            ) {
                carreras.forEach { c ->
                    DropdownMenuItem(
                        text = { Text(text = stringResource(c.nameRes)) },
                        onClick = {
                            setExpanded(false)
                            c.action(viewModel)
                            navController.navigate(Ventanas.Race.name)

                        })
                }
            }
        }
    }

    @Preview
    @Composable
    fun PilotosPreview() {
        PilotosTheme {
            PilotosApp()
        }
    }
}
