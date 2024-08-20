package br.com.fiap.contratoagil.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.contratoagil.R
import br.com.fiap.contratoagil.viewModels.ImovelScreenViewModel
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import br.com.fiap.contratoagil.componets.CaixaDeEntrada
import br.com.fiap.contratoagil.componets.MyAlertDialog
import br.com.fiap.contratoagil.componets.dropDownMenu
import br.com.fiap.contratoagil.componets.dropDownMenuImovelAlterar
import br.com.fiap.contratoagil.database.repository.ImovelRepository


@Composable
fun ImovelScreen(imovelScreenViewModel: ImovelScreenViewModel, navController: NavController) {

    val context = LocalContext.current

    val textBtn by imovelScreenViewModel.textBtn.observeAsState(initial = "CADASTRAR")

    val statusAlert by imovelScreenViewModel.statusAlert.observeAsState(initial = false)

    val nomeImovel by imovelScreenViewModel.nomeImovel.observeAsState(initial = "")

    val qtdQuartos by imovelScreenViewModel.qtdQuartos.observeAsState(initial = "")

    val qtdBanheiros by imovelScreenViewModel.qtdBanheiros.observeAsState(initial = "")

    val mtQuadrados by imovelScreenViewModel.mtQuadrados.observeAsState(initial = 0.0)

    val endereco by imovelScreenViewModel.endereco.observeAsState(initial = "")

    val obs by imovelScreenViewModel.obs.observeAsState(initial = "")

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
                        Text(text = "Imovél", fontSize = 24.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                dropDownMenuImovelAlterar(
                                    label = "Selecione o imovel ou deixe em branco para adicionar",
                                    imovelScreenViewModel = imovelScreenViewModel,
                                    listaItems = imovelScreenViewModel.listarImoveis(context = context)
                                        .map { imovelModel ->
                                            imovelModel
                                        }
                                ) {
                                    imovelScreenViewModel.onNomeImovelChanged(it)
                                    imovelScreenViewModel.onTextBtnChanged("ALTERAR")
                                }
                                CaixaDeEntrada(value = nomeImovel,
                                    placeholder = "",
                                    label = "Insira um nome do imovél",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Text,
                                    atualizaValor = {
                                        imovelScreenViewModel.onNomeImovelChanged(it)
                                    })
                                CaixaDeEntrada(value = qtdQuartos,
                                    placeholder = "",
                                    label = "Quantos quartos?",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Number,
                                    atualizaValor = {
                                        imovelScreenViewModel.onQtdQuartosChanged(it)
                                    })
                                CaixaDeEntrada(value = qtdBanheiros,
                                    placeholder = "",
                                    label = "Quantos banheiros?",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Number,
                                    atualizaValor = {
                                        imovelScreenViewModel.onQtdBanheirosChanged(it)
                                    })
                                CaixaDeEntrada(value = mtQuadrados,
                                    placeholder = "",
                                    label = "Quantos metros quadrados?",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Decimal,
                                    atualizaValor = {
                                        imovelScreenViewModel.onMtQuadradosChanged(it)
                                    })
                                CaixaDeEntrada(value = endereco,
                                    placeholder = "",
                                    label = "Insira um endereço completo",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Text,
                                    atualizaValor = {
                                        imovelScreenViewModel.onEnderecoChanged(it)
                                    })
                                CaixaDeEntrada(value = obs,
                                    placeholder = "",
                                    label = "Adicione alguma informação",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Text,
                                    atualizaValor = {
                                        imovelScreenViewModel.onObsChanged(it)
                                    })
                                Button(
                                    onClick = {
                                        if (imovelScreenViewModel.imovel.value == null) {
                                            imovelScreenViewModel.btnCadastrar(context)
                                        } else {
                                            imovelScreenViewModel.btnAlterar(context)

                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    colors = ButtonDefaults.buttonColors(Color(0xffA39B7E))
                                ) {
                                    Text(text = textBtn, color = Color.White)
                                }
                                if (imovelScreenViewModel.imovel.value != null) {
                                    Button(
                                        onClick = { imovelScreenViewModel.onStatusAlertChanged(!statusAlert)},
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
                                        titulo = "Excluir Imovél?",
                                        text = "Essa ação é irreversível",
                                        context = context,
                                        onRejeito = {imovelScreenViewModel.onStatusAlertChanged(false)}
                                    ) {
                                        imovelScreenViewModel.btnExcluir(context)
                                        imovelScreenViewModel.onStatusAlertChanged(false)
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






