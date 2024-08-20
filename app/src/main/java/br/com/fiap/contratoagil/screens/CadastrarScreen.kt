package br.com.fiap.contratoagil.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.contratoagil.R
import br.com.fiap.contratoagil.componets.CaixaDeEntrada
import br.com.fiap.contratoagil.viewModels.CadastrarScreenViewModel
import br.com.fiap.contratoagil.viewModels.ConfigScreenViewModel
import br.com.fiap.contratoagil.viewModels.LoginScreenViewModel



@Composable
fun CadastroScreen(cadastroScreenViewModel: CadastrarScreenViewModel, navControler: NavController, configScreenViewModel: ConfigScreenViewModel) {

    val context = LocalContext.current
    val email by cadastroScreenViewModel.email.observeAsState(initial = "")
    val senha by cadastroScreenViewModel.senha.observeAsState(initial = "")
    val nameUser by cadastroScreenViewModel.nome.observeAsState(initial = "")

    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.30f),
                Alignment.TopEnd,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.compromisso),
                        contentDescription = "Logo App",
                        modifier = Modifier
                            .weight(1f)
                            .size(100.dp)
                    )
                    Text(text = "Welcome", fontSize = 20.sp, color = Color.White)
                }

            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)) {
                Text(text = "Cadastrar")
                Spacer(modifier = Modifier.height(8.dp))
                CaixaDeEntrada(value = nameUser,
                    placeholder = "",
                    label = "Nome do usuario",
                    modifier = Modifier,
                    keyboardType = KeyboardType.Text,
                    atualizaValor = {
                        cadastroScreenViewModel.onNomeChanged(it)
                    })
                Spacer(modifier = Modifier.height(8.dp))
                CaixaDeEntrada(value = email,
                    placeholder = "",
                    label = "Email do Usúario",
                    modifier = Modifier,
                    keyboardType = KeyboardType.Email,
                    atualizaValor = {
                        cadastroScreenViewModel.onEmailChanged(it)
                    })
                Spacer(modifier = Modifier.height(8.dp))
                CaixaDeEntrada(value = senha,
                    placeholder = "",
                    label = "Senha",
                    modifier = Modifier,
                    keyboardType = KeyboardType.Password,
                    atualizaValor = {
                        cadastroScreenViewModel.onSenhaChanged(it)
                    })
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    cadastroScreenViewModel.cadastrarbtn(context, navControler, configScreenViewModel)

                },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "CADASTRAR")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Já tem conta?", fontSize = 14.sp)
                    TextButton(onClick = { navControler.navigate("login") }) {
                        Text(text = "Entrar")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }



    }
}