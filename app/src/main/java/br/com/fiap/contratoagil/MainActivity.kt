package br.com.fiap.contratoagil
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.fiap.contratoagil.screens.*
import br.com.fiap.contratoagil.ui.theme.ContratoAgilTheme
import br.com.fiap.contratoagil.viewModels.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var redireciona by remember {
                mutableStateOf("cadastrar")
            }
            var autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao()
            if (autenticacao?.getCurrentUser() != null) {
               redireciona = "home"
            }
            ContratoAgilTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = redireciona,
                        exitTransition = {
                            slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Down,
                                animationSpec = tween(500)
                            ) + fadeOut(animationSpec = tween(500))
                        },
                        enterTransition = {
                            slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Up,
                                animationSpec = tween(500))
                        }
                    ){
                        composable(route = "cadastrar"){ CadastroScreen(CadastrarScreenViewModel(),navController, ConfigScreenViewModel()) }
                        composable(route = "login"){ LoginScreen(LoginScreenViewModel(),navController, ConfigScreenViewModel()) }
                        composable(route = "home"){ MainScreen(MainScreenViewModel(),navController, ConfigScreenViewModel()) }
                        composable(route = "imovel"){ ImovelScreen(ImovelScreenViewModel(),navController) }
                        composable(route = "inquilino"){ InquilinoScreen(InquilinoScreenViewModel(),navController) }
                        composable(route = "dadosBasicos"){ DadosBasicosScreen(DadosBasicosScreenViewModel(),navController) }
                        composable(route = "configuracao"){ ConfigScreen(ConfigScreenViewModel(),navController) }
                        composable(route = "contrato"){ ContratoScreen(ContratoScreenViewModel(),navController,
                            ImovelScreenViewModel(), InquilinoScreenViewModel()) }
                    }
                }
            }
        }
    }
}

