package br.com.fiap.contratoagil.componets

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.contratoagil.ConfiguracaoFirebase
import br.com.fiap.contratoagil.R
import br.com.fiap.contratoagil.viewModels.ConfigScreenViewModel
import br.com.fiap.contratoagil.viewModels.MainScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionMenu(mainScreenViewModel: MainScreenViewModel, navController: NavController){

    var showMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current

    TopAppBar(
        modifier = Modifier
            .background(Color(0xff7A7B7C)),
        title = {},
        actions = {
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
                Text(text = "ContratoÁgil", fontSize = 24.sp)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { showMenu = !showMenu }, modifier = Modifier.padding(end = 16.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_aberto),
                        modifier = Modifier.size(30.dp),
                        contentDescription = "Menu de configurações"
                    )

                }

                DropdownMenu(
                    expanded = showMenu,
                    offset = DpOffset(-1.dp, -1.dp),
                    onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(onClick = {
                        mainScreenViewModel.singOut()
                        navController.navigate("cadastrar")
                        Toast.makeText(context, "Deslogado com Sucesso!", Toast.LENGTH_SHORT).show()
                    }, text = { Text(text = "LogOut") })
                    DropdownMenuItem(onClick = {
                        navController.navigate("configuracao")
                    }, text = { Text(text = "Configurações do usuário") })
                }

            }

        }
    )


}