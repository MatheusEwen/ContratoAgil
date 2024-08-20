package br.com.fiap.contratoagil.viewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import br.com.fiap.contratoagil.ConfiguracaoFirebase
import br.com.fiap.contratoagil.database.repository.ImovelRepository
import br.com.fiap.contratoagil.helper.Base64Custom
import br.com.fiap.contratoagil.model.ImovelModel
import br.com.fiap.contratoagil.model.Usuario

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.*
import com.google.firebase.database.*
import com.google.firebase.database.ktx.values
import kotlin.math.log

class LoginScreenViewModel : ViewModel() {

    private var autenticacao: FirebaseAuth? = null

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _senha = MutableLiveData<String>()
    val senha: LiveData<String> = _senha

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onSenhaChanged(senha: String) {
        _senha.value = senha
    }

    fun entrarBtn(context: Context, navControler: NavController, configScreenViewModel: ConfigScreenViewModel) {


        if (!email.toString().isEmpty()) {
            if (!senha.toString().isEmpty()) {
                validarLogin(context, navControler, configScreenViewModel)

            } else {
                Toast.makeText(context, "Preencha a senha", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Preencha o Email", Toast.LENGTH_SHORT).show()
        }
    }

    fun validarLogin(context: Context, navControler: NavController, configScreenViewModel: ConfigScreenViewModel) {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao()
        autenticacao?.signInWithEmailAndPassword(email.value.toString(), senha.value.toString())
            ?.addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
                if (task.isSuccessful) {
                    configScreenViewModel.carregaDadosUser(context)
                    navControler.navigate("home")

                } else {
                    var excecao = ""
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthInvalidUserException) {
                        Toast.makeText(context,
                            "Usuario não esta cadastrado",
                            Toast.LENGTH_SHORT).show()
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(context,
                            "Email e senha não correspondem a um usuario cadastrado ",
                            Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        excecao = "Erro: " + e.message.toString()
                        e.printStackTrace()
                    }
                    Toast.makeText(context, excecao, Toast.LENGTH_SHORT).show()
                }
            })
    }
}