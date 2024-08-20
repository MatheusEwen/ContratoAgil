package br.com.fiap.contratoagil.screens

import android.os.Environment
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import br.com.fiap.contratoagil.Gerador
import br.com.fiap.contratoagil.R
import br.com.fiap.contratoagil.componets.CaixaDeEntrada
import br.com.fiap.contratoagil.componets.dropDownMenu
import br.com.fiap.contratoagil.componets.dropDownMenuImovel
import br.com.fiap.contratoagil.componets.dropDownMenuInquilino
import br.com.fiap.contratoagil.model.ContratoModel
import br.com.fiap.contratoagil.viewModels.ContratoScreenViewModel
import br.com.fiap.contratoagil.viewModels.ImovelScreenViewModel
import br.com.fiap.contratoagil.viewModels.InquilinoScreenViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


@Composable
fun ContratoScreen(
    contratoScreenViewModel: ContratoScreenViewModel,
    navController: NavController,
    imovelScreenViewModel: ImovelScreenViewModel,
    inquilinoScreenViewModel: InquilinoScreenViewModel,
) {

    System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl")
    System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl")
    System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl")

    val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

    val locatario by contratoScreenViewModel.locatario.observeAsState(initial = null)

    val imovel by contratoScreenViewModel.imovel.observeAsState(initial = null)

    val meioPagamento by contratoScreenViewModel.meioPagamento.observeAsState(initial = "")

    val diaPagamento by contratoScreenViewModel.diaPagamento.observeAsState(initial = "")

    val nomeImovel by contratoScreenViewModel.nomeImovel.observeAsState(initial = "")

    val prazoReajuste by contratoScreenViewModel.prazoReajuste.observeAsState(initial = "")

    val indiceReajuste by contratoScreenViewModel.indiceReajuste.observeAsState(initial = "")

    val valorAluguel by contratoScreenViewModel.valorAluguel.observeAsState(initial = "")

    var dtInicio by remember {
        mutableStateOf(LocalDate.now())

    }

    val dtInicioFormatada by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("dd MMM yyy")
                .format(dtInicio)
        }
    }

    var dtTermino by remember {
        mutableStateOf(LocalDate.now())
    }

    val dtTerminoFormatada by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("dd MMM yyy")
                .format(dtTermino)
        }
    }

    val context = LocalContext.current

    val dateVigenciaState = rememberMaterialDialogState()
    val dateTerminoState = rememberMaterialDialogState()

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
                        Text(text = "Gerar Contrato", fontSize = 24.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                dropDownMenuInquilino(
                                    label = "Selecione o Locatário",
                                    contratoScreenViewModel = contratoScreenViewModel,
                                    listaItems = inquilinoScreenViewModel.listarObjInquilino(context = context).map {
                                        inquilinoModel ->  inquilinoModel
                                    }
                                ) {
                                    contratoScreenViewModel.onNomeLocatarioChanged(it)
                                }
                                CaixaDeEntrada(value = meioPagamento,
                                    placeholder = "",
                                    label = "Método de pagamento",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Text,
                                    atualizaValor = {
                                        contratoScreenViewModel.onMeioPagamentoChanged(it)
                                    })

                                CaixaDeEntrada(value = diaPagamento,
                                    placeholder = "",
                                    label = "Dia pagamento",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Number,
                                    atualizaValor = {
                                        contratoScreenViewModel.onDiaPagamentoChanged(it)
                                    })

                                dropDownMenuImovel(
                                    label = "Selecione o imovél",
                                    contratoScreenViewModel = contratoScreenViewModel,
                                    listaItems = imovelScreenViewModel.listarImoveis(context = context)
                                        .map { imovelModel ->
                                            imovelModel
                                        }
                                ) {
                                    contratoScreenViewModel.onNomeImovelChanged(it)
                                }
                                dropDownMenu(value = prazoReajuste,
                                    label = "Prazo de Reajuste",
                                    listaItems = contratoScreenViewModel.prazoReajusteList
                                ) {
                                    contratoScreenViewModel.onPrazoReajusteChanged(it)
                                }
                                dropDownMenu(value = indiceReajuste,
                                    label = "Indice de reajuste",
                                    listaItems = contratoScreenViewModel.indiceReajusteList
                                ) {
                                    contratoScreenViewModel.onIndiceReajusteChanged(it)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Button(
                                        onClick = {
                                            dateVigenciaState.show()
                                        },
                                        shape = Shapes().small,
                                        modifier = Modifier
                                            .padding(top = 8.dp),
                                        colors = ButtonDefaults.buttonColors(Color(0xffA39B7E))
                                    ) {
                                        Text(text = "Data Inicio:", color = Color.White)
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))

                                        Text(text = dtInicioFormatada)


                                    MaterialDialog(
                                        dialogState = dateVigenciaState,
                                        buttons = {
                                            positiveButton(text = "Ok") {

                                            }
                                            negativeButton(text = "Cancel")
                                        }
                                    ) {
                                        datepicker(
                                            initialDate = LocalDate.now(),
                                            title = "Escolha uma data"
                                        ) {
                                            dtInicio = it
                                            contratoScreenViewModel.onDtVigenciaChanged(it.toString())
                                        }
                                    }
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Button(
                                        onClick = {
                                            dateTerminoState.show()
                                        },
                                        shape = Shapes().small,
                                        modifier = Modifier
                                            .padding(top = 8.dp),
                                        colors = ButtonDefaults.buttonColors(Color(0xffA39B7E))
                                    ) {
                                        Text(text = "Data Termino:", color = Color.White)
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = dtTerminoFormatada)
                                    MaterialDialog(
                                        dialogState = dateTerminoState,
                                        buttons = {
                                            positiveButton(text = "Ok") {

                                            }
                                            negativeButton(text = "Cancel")
                                        }
                                    ) {
                                        datepicker(
                                            initialDate = LocalDate.now(),
                                            title = "Escolha uma data"
                                        ) {
                                            dtTermino = it
                                            contratoScreenViewModel.onDtTerminoChanged(it.toString())
                                        }
                                    }
                                }
                                CaixaDeEntrada(value = valorAluguel,
                                    placeholder = "",
                                    label = "Valor do aluguel",
                                    modifier = Modifier,
                                    keyboardType = KeyboardType.Decimal,
                                    atualizaValor = {
                                        contratoScreenViewModel.onValorAluguelChanged(it)
                                    })
                                Button(
                                    onClick = {
                                        contratoScreenViewModel.btnCadastrar(context = context)
                                        val contratoModel = ContratoModel(id = 0,
                                            nomeLocatario = locatario!!.nomeLocatario,
                                            meioPagamento = meioPagamento,
                                            diaPagamento = diaPagamento,
                                            nomeImovel = imovel!!.nomeImovel,
                                            prazoReajuste = prazoReajuste,
                                            indiceReajuste = indiceReajuste,
                                            dtVigencia = dtInicioFormatada,
                                            dtTermino = dtTerminoFormatada,
                                            valorAluguel = valorAluguel
                                        )
                                        val gerador = Gerador()
                                        gerador.createDocx(path = path, contratoDados = contratoModel, context = context, locatario = locatario!!, imovel = imovel!!, contratoScreenViewModel.listarUsuario(context))

                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    colors = ButtonDefaults.buttonColors(Color(0xffA39B7E))
                                ) {
                                    Text(text = "GERAR CONTRATO", color = Color.White)
                                }

                            }
                        }
                    }

                }
            }
        }
    }

}
