package br.com.fiap.contratoagil.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.contratoagil.R
import br.com.fiap.contratoagil.componets.OptionMenu
import br.com.fiap.contratoagil.viewModels.ConfigScreenViewModel
import br.com.fiap.contratoagil.viewModels.MainScreenViewModel
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter


@Composable
fun MainScreen(mainScreenViewModel: MainScreenViewModel, navController: NavController, configScreenViewModel: ConfigScreenViewModel) {
    val context = LocalContext.current
    val formato = "yyyy-MM-dd" // Formato da data na string
    Box() {

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {

            Column() {
                OptionMenu(mainScreenViewModel, navController)
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "DashBoard", fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "Total de imovés: ${mainScreenViewModel.BuscaQtdImoveis(context = context)}")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Proximo vencimentos de contrato:")
                            LazyColumn() {
                                items(mainScreenViewModel.proximosVencimentos(context)){
                                    Text(text = "Contrato: ${it.nomeLocatario} - ${ mainScreenViewModel.dataFormatada(it.dtTermino,formato)}")
                                }

                            }
                        }
                    }
                }
            }


            Column(modifier = Modifier.padding(16.dp)) {
                Card(border = BorderStroke(width = 2.dp, color = Color.White)) {
                    Row(modifier = Modifier
                        .fillMaxWidth(), Arrangement.SpaceEvenly) {
                        IconButton(onClick = { navController.navigate("imovel") },
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(64.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.imoveladd),
                                modifier = Modifier.size(50.dp),
                                contentDescription = "ir para imovél"
                            )

                        }
                        IconButton(onClick = { navController.navigate("inquilino") },
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(64.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.persona),
                                modifier = Modifier.size(50.dp),
                                contentDescription = "ir para inquilino"
                            )

                        }
                        IconButton(onClick = { navController.navigate("contrato") },
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(64.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.compromisso),
                                modifier = Modifier.size(50.dp),
                                contentDescription = "ir para contrato"
                            )

                        }

                    }
                }

            }
        }

    }
}





