package com.example.clasificacionf1

import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.Menu
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
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableInferredTarget
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.clasificacionf1.model.Carrera
import com.example.clasificacionf1.model.CarrerasRepository
import com.example.clasificacionf1.model.PilotoRepository
import com.example.clasificacionf1.ui.theme.PilotosTheme


class ViewModel {
    var elegirCarrera by mutableStateOf(false)
    var carrera: MutableState<Int> = mutableStateOf(3)
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PilotosApp() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(viewModel = ViewModel())
            }
        ) {

            val pilotoLista = PilotoRepository.pilotoLista
            PilotosList(pilotos = pilotoLista, contentPadding = it)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBar(viewModel: ViewModel) {
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
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            navigationIcon = {
                IconButton(
                    onClick  = { /* Manejar el evento de clic del icono de navegación */ },
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.TwoTone.ArrowBack,
                        contentDescription = "Menú",
                        modifier = Modifier.size(30.dp)
                    )
                }
            },
            actions = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = { viewModel.elegirCarrera = true },
                        modifier = Modifier.padding(end = 10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Home,
                            contentDescription = "Primer Icono",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        )
        if (expanded) {
            // Renderiza el menú de carreras
            MenuCarreras(
                expanded = expanded,
                setExpanded = { expanded = it },
                carreras = CarrerasRepository.carreraLista
            )
            //PilotosList(pilotos = PilotoRepository.pilotoLista)
        }
    }
    @Composable
    private fun MenuCarreras(
        expanded: Boolean,
        setExpanded: (Boolean) -> Unit,
        carreras : List<Carrera>
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
                            c.action()
                            setExpanded(false)
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
