package br.com.fiap.contratoagil.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.contratoagil.R
import br.com.fiap.contratoagil.componets.CaixaDeEntrada
import br.com.fiap.contratoagil.componets.MyAlertDialog
import br.com.fiap.contratoagil.componets.dropDownMenu
import br.com.fiap.contratoagil.componets.dropDownMenuInquilinoAlterar
import br.com.fiap.contratoagil.viewModels.DadosBasicosScreenViewModel
import br.com.fiap.contratoagil.viewModels.InquilinoScreenViewModel


@Composable
fun DadosBasicosScreen(
    dadosBasicosScreenViewModel: DadosBasicosScreenViewModel,
    navController: NavController,
) {
    val context = LocalContext.current

    val nomeUsu by dadosBasicosScreenViewModel.nomeUsu.observeAsState(initial = "")

    val genero by dadosBasicosScreenViewModel.genero.observeAsState(initial = "")

    val rg by dadosBasicosScreenViewModel.rg.observeAsState(initial = "")

    val cpf by dadosBasicosScreenViewModel.cpf.observeAsState(initial = "")

    val qualificacao by dadosBasicosScreenViewModel.qualificacao.observeAsState(initial = "")

    Box(modifier = Modifier.fillMaxSize()) {
        Column() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xff7A7B7C))

            ) {
                Image(
                    painter = painterResource(R.drawable.casa_logo),
                    contentDescription = "Logo de uma casa",
                    Modifier
                        .padding(16.dp)
                        .size(64.dp)
                )
                Text(text = "AlugaMais", fontSize = 24.sp)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { navController.navigate("home") },
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(60.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.seta_direita),
                        modifier = Modifier.size(60.dp),
                        contentDescription = "voltar"
                    )

                }

            }
            LazyColumn() {
                item {

                    Spacer(modifier = Modifier.height(8.dp))
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Dados do Locador(A)", fontSize = 24.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                CaixaDeEntrada(value = nomeUsu,
                                    placeholder = "",
                                    label = "Insira o nome completo",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Text,
                                    atualizaValor = {
                                        dadosBasicosScreenViewModel.onNomeUsuChanged(it)
                                    })
                                Text(text = "Selecione seu Genero")
                                dropDownMenu(value = genero,
                                    label = genero,
                                    listaItems = dadosBasicosScreenViewModel.listaGenero
                                ) {
                                    dadosBasicosScreenViewModel.onGeneroChanged(it)
                                }
                                CaixaDeEntrada(value = rg,
                                    placeholder = "",
                                    label = "Insira seu RG",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Number,
                                    atualizaValor = {
                                        dadosBasicosScreenViewModel.onRgChanged(it)
                                    })
                                CaixaDeEntrada(value = cpf,
                                    placeholder = "",
                                    label = "Insira seu CPF",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Number,
                                    atualizaValor = {
                                        dadosBasicosScreenViewModel.onCpfChanged(it)
                                    })
                                CaixaDeEntrada(value = qualificacao,
                                    placeholder = "",
                                    label = "Qual a sua qualificação",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Text,
                                    atualizaValor = {
                                        dadosBasicosScreenViewModel.onQualificacaoChanged(it)
                                    })
                                Button(
                                    onClick = {
                                              if (qualificacao != "" && nomeUsu != "" && rg != "" && cpf != "" && genero != ""){
                                                  dadosBasicosScreenViewModel.btnCadastrar(context, navController)
                                              } else {
                                                  Toast.makeText(context, "Preencha todos os dados basicos", Toast.LENGTH_SHORT)
                                                      .show()
                                              }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    colors = ButtonDefaults.buttonColors(Color(0xffA39B7E))
                                ) {
                                    Text(text = "SALVAR", color = Color.White)
                                }

                            }
                        }
                    }

                }
            }
        }
    }
}