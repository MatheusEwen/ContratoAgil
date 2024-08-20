package br.com.fiap.contratoagil.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import br.com.fiap.contratoagil.viewModels.LoginScreenViewModel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.contratoagil.R
import br.com.fiap.contratoagil.componets.CaixaDeEntrada
import br.com.fiap.contratoagil.viewModels.ConfigScreenViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    loginScreenViewModel: LoginScreenViewModel,
    navControler: NavController,
    configScreenViewModel: ConfigScreenViewModel
) {

    val context = LocalContext.current

    val email by loginScreenViewModel.email.observeAsState(initial = "")

    val senha by loginScreenViewModel.senha.observeAsState(initial = "")

    val (focusUsername, focusPassword) = remember { FocusRequester.createRefs() }
    val keyboardController = LocalSoftwareKeyboardController.current

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
                Text(text = "Entrar")
                CaixaDeEntrada(value = email,
                    placeholder = "",
                    label = "Email do Usúario",
                    modifier = Modifier,
                    keyboardType = KeyboardType.Email,
                    atualizaValor = {
                        loginScreenViewModel.onEmailChanged(it)
                    })
                Spacer(modifier = Modifier.height(8.dp))
                CaixaDeEntrada(value = senha,
                    placeholder = "",
                    label = "Senha",
                    modifier = Modifier,
                    keyboardType = KeyboardType.Password,
                    atualizaValor = {
                        loginScreenViewModel.onSenhaChanged(it)
                    })
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { loginScreenViewModel.entrarBtn(context, navControler, configScreenViewModel) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Entrar")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Não tem uma conta?", fontSize = 14.sp)
                    TextButton(onClick = { navControler.navigate("cadastrar") }) {
                        Text(text = "Registrar")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }


    }
}