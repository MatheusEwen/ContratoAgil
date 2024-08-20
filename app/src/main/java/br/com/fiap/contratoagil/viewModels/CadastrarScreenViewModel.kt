package br.com.fiap.contratoagil.viewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import br.com.fiap.contratoagil.ConfiguracaoFirebase
import br.com.fiap.contratoagil.helper.Base64Custom
import br.com.fiap.contratoagil.model.Usuario
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class CadastrarScreenViewModel: ViewModel() {

    private var autenticacao: FirebaseAuth? = null
    private var usuario: Usuario? = null
    private lateinit var navControler: NavController

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _senha = MutableLiveData<String>()
    val senha: LiveData<String> = _senha

    private val _nome = MutableLiveData<String>()
    val nome: LiveData<String> = _nome

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onSenhaChanged(senha: String) {
        _senha.value = senha
    }
    fun onNomeChanged(nome: String) {
        _nome.value = nome
    }

    fun cadastrarbtn(context: Context, navControler: NavController, configScreenViewModel: ConfigScreenViewModel){

        if (!nome.toString().isEmpty()) {
            if (!email.toString().isEmpty()) {
                if (!senha.toString().isEmpty()) {
                    usuario = Usuario()
                    usuario!!.setNome(nome.toString())
                    usuario!!.setEmail(email.toString())
                    usuario!!.setSenha(senha.toString())
                    cadastrarUsuario(context, navControler, configScreenViewModel )
                } else {
                    Toast.makeText(context, "Preencha a senha", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(context, "Preencha o Email", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Preencha o nome", Toast.LENGTH_SHORT).show()
        }
    }

    fun cadastrarUsuario(context: Context, navControler: NavController, configScreenViewModel: ConfigScreenViewModel){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao()
        autenticacao?.createUserWithEmailAndPassword(email.value.toString(), senha.value.toString())
            ?.addOnCompleteListener(OnCompleteListener { task ->
                if (task.isSuccessful) {
                    var idUsuario: String? = Base64Custom.codificarBase64(email.value.toString())
                    var usuario: Usuario = Usuario();
                    usuario.setIdUsuario(idUsuario)
                    usuario.setNome(nome.value.toString())
                    usuario.setEmail(email.value.toString())
                    usuario.salvar()
                    Toast.makeText(context, "CADASTRADO COM SUCESSO!!!", Toast.LENGTH_SHORT).show()
                    configScreenViewModel.excluirDadosBasicos(context)
                    navControler.navigate("dadosBasicos")

                }else{
                    var excecao = ""
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        excecao = "Digite uma senha mais forte"
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        excecao = "Por favor digite um email valido"
                    } catch (e: FirebaseAuthUserCollisionException) {
                        excecao = "Esta conta já foi cadastrada"
                    } catch (e: Exception) {
                        excecao =
                            "Erro:" + e.message
                        e.printStackTrace()
                    }
                    Toast.makeText(context, excecao + email.toString(), Toast.LENGTH_SHORT).show()

                }

            })

    }





}