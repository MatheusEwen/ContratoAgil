package br.com.fiap.contratoagil.screens

import androidx.compose.foundation.BorderStroke
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
import br.com.fiap.contratoagil.viewModels.InquilinoScreenViewModel

@Composable
fun InquilinoScreen(
    inquilinoScreenViewModel: InquilinoScreenViewModel,
    navController: NavController,
) {
    val context = LocalContext.current

    val statusAlert by inquilinoScreenViewModel.statusAlert.observeAsState(initial = false)

    val cpf by inquilinoScreenViewModel.cpf.observeAsState(initial = "")

    val rg by inquilinoScreenViewModel.rg.observeAsState(initial = "")

    val qualificacao by inquilinoScreenViewModel.qualificacao.observeAsState(initial = "")

    val nomeLocatario by inquilinoScreenViewModel.nomeLocatario.observeAsState(initial = "")

    val textBtn by inquilinoScreenViewModel.textBtn.observeAsState(initial = "CADASTRAR")

    val genero by inquilinoScreenViewModel.genero.observeAsState(initial = "")

    val estadoCivil by inquilinoScreenViewModel.estadoCivil.observeAsState(initial = "")

    val qtdMembros by inquilinoScreenViewModel.qtdMembros.observeAsState(initial = 0)

    val telefone by inquilinoScreenViewModel.telefone.observeAsState(initial = "")

    val email by inquilinoScreenViewModel.email.observeAsState(initial = "")

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
                        contentDescription = "Menu de configurações"
                    )

                }

            }
            LazyColumn() {
                item {

                    Spacer(modifier = Modifier.height(8.dp))
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Inquilino", fontSize = 24.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                dropDownMenuInquilinoAlterar(
                                    label = "Selecione um inquilino ou deixe em branco para adicionar",
                                    inquilinoScreenViewModel = inquilinoScreenViewModel,
                                    listaItems = inquilinoScreenViewModel.listarObjInquilino(context = context).map {
                                            inquilinoModel ->  inquilinoModel}
                                ) {
                                    inquilinoScreenViewModel.onTextBtnChanged("ALTERAR")

                                }
                                CaixaDeEntrada(value = nomeLocatario,
                                    placeholder = "",
                                    label = "Insira o nome do Locatário",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Text,
                                    atualizaValor = {
                                        inquilinoScreenViewModel.onNomeLocatarioChanged(it)
                                    })
                                Text(text = "Selecione seu Genero")
                                dropDownMenu(value = genero,
                                    label = genero,
                                    listaItems = inquilinoScreenViewModel.listaGenero
                                ) {
                                    inquilinoScreenViewModel.onGeneroChanged(it)
                                }
                                Text(text = "Selecione seu estado civil")
                                dropDownMenu(value = estadoCivil,
                                    label = estadoCivil,
                                    listaItems = inquilinoScreenViewModel.listaEstadoCivil
                                ) {
                                    inquilinoScreenViewModel.onEstadoCivilChanged(it)
                                }
                                CaixaDeEntrada(value = qtdMembros,
                                    placeholder = "",
                                    label = "Quantidade de membros na familia",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Number,
                                    atualizaValor = {
                                        inquilinoScreenViewModel.onQtdMembrosChanged(it)
                                    })
                                CaixaDeEntrada(value = telefone,
                                    placeholder = "",
                                    label = "Insira um telefone",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Phone,
                                    atualizaValor = {
                                        inquilinoScreenViewModel.onTelefooneChanged(it)
                                    })

                                CaixaDeEntrada(value = cpf,
                                    placeholder = "",
                                    label = "Insira o CPF",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Number,
                                    atualizaValor = {
                                        inquilinoScreenViewModel.onCpfChanged(it)
                                    })

                                CaixaDeEntrada(value = rg,
                                    placeholder = "",
                                    label = "Insira o RG",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Number,
                                    atualizaValor = {
                                        inquilinoScreenViewModel.onRgChanged(it)
                                    })
                                CaixaDeEntrada(value = qualificacao,
                                    placeholder = "",
                                    label = "Insira a qualificação",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Text,
                                    atualizaValor = {
                                        inquilinoScreenViewModel.onQualificacaoChanged(it)
                                    })
                                CaixaDeEntrada(value = email,
                                    placeholder = "",
                                    label = "Informe um email",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Email,
                                    atualizaValor = {
                                        inquilinoScreenViewModel.onEmailChanged(it)
                                    })
                                Button(
                                    onClick = {

                                        if (inquilinoScreenViewModel.inquilino.value == null){
                                            inquilinoScreenViewModel.btnCadastrar(context)
                                        } else {
                                            inquilinoScreenViewModel.btnAlterar(context)

                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    colors = ButtonDefaults.buttonColors(Color(0xffA39B7E))
                                ) {
                                    Text(text = textBtn, color = Color.White)
                                }
                                if (inquilinoScreenViewModel.inquilino.value != null) {
                                    Button(
                                        onClick = { inquilinoScreenViewModel.onStatusAlertChanged(!statusAlert)},
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 8.dp),
                                        colors = ButtonDefaults.buttonColors(Color(0xffA39B7E))
                                    ) {
                                        Text(text = "Excluir", color = Color.White)
                                    }
                                }
                                if (statusAlert) {
                                    MyAlertDialog(
                                        titulo = "Excluir Inquilino?",
                                        text = "Essa ação é irreversível",
                                        context = context,
                                        onRejeito = {inquilinoScreenViewModel.onStatusAlertChanged(false)}
                                    ) {
                                        inquilinoScreenViewModel.btnExcluir(context)
                                        inquilinoScreenViewModel.onStatusAlertChanged(false)
                                        navController.navigate("home")
                                    }
                                }

                            }
                        }
                    }

                }
            }
        }
    }
}