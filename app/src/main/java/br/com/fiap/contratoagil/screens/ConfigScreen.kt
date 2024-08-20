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
import br.com.fiap.contratoagil.componets.dropDownMenu
import br.com.fiap.contratoagil.database.repository.UsuarioRepository
import br.com.fiap.contratoagil.viewModels.ConfigScreenViewModel


@Composable
fun ConfigScreen(
    configScreenViewModel: ConfigScreenViewModel,
    navController: NavController,
) {

    val context = LocalContext.current
    val dados = configScreenViewModel.listarUsuario(context)

    val id by configScreenViewModel.id.observeAsState(initial = dados.id)

    val nomeUsu by configScreenViewModel.nomeUsu.observeAsState(initial = dados.nomeUsu)

    val genero by configScreenViewModel.genero.observeAsState(initial = dados.genero)

    val rg by configScreenViewModel.rg.observeAsState(initial = dados.rg)

    val cpf by configScreenViewModel.cpf.observeAsState(initial = dados.cpf)

    val qualificacao by configScreenViewModel.qualificacao.observeAsState(initial = dados.qualificacao)

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
                                        configScreenViewModel.onNomeUsuChanged(it)
                                    })
                                Text(text = "Selecione seu Genero")
                                dropDownMenu(value = genero,
                                    label = genero,
                                    listaItems = configScreenViewModel.listaGenero
                                ) {
                                    configScreenViewModel.onGeneroChanged(it)
                                }
                                CaixaDeEntrada(value = rg,
                                    placeholder = "",
                                    label = "Insira seu RG",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Number,
                                    atualizaValor = {
                                        configScreenViewModel.onRgChanged(it)
                                    })
                                CaixaDeEntrada(value = cpf,
                                    placeholder = "",
                                    label = "Insira seu CPF",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Number,
                                    atualizaValor = {
                                        configScreenViewModel.onCpfChanged(it)
                                    })
                                CaixaDeEntrada(value = qualificacao,
                                    placeholder = "",
                                    label = "Qual a sua qualificação",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Text,
                                    atualizaValor = {
                                        configScreenViewModel.onQualificacaoChanged(it)
                                    })
                                Button(
                                    onClick = {
                                        if (qualificacao != "" && nomeUsu != "" && rg != "" && cpf != "" && genero != ""){
                                            configScreenViewModel.btnAlterarDados(context, navController,
                                                id.toString(), nomeUsu, rg, cpf, qualificacao,genero)
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
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Backup dos Dados", fontSize = 24.sp)
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = "O que você quer fazer?")
                                Button(
                                    onClick = { configScreenViewModel.btnBackupNuvem(context)},
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    colors = ButtonDefaults.buttonColors(Color(0xffA39B7E))
                                ) {
                                    Text(text = "Subir na Nuvem", color = Color.White)
                                }
                                Button(
                                    onClick = { configScreenViewModel.btnDownloadNuvem(context)},
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    colors = ButtonDefaults.buttonColors(Color(0xffA39B7E))
                                ) {
                                    Text(text = "Trazer da Nuvem", color = Color.White)
                                }


                            }
                        }

                    }

                }
            }
        }
    }
}