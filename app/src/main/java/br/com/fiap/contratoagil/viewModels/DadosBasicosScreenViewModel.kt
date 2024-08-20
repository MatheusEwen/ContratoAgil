package br.com.fiap.contratoagil.viewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import br.com.fiap.contratoagil.database.repository.InquilinoRepository
import br.com.fiap.contratoagil.database.repository.UsuarioRepository
import br.com.fiap.contratoagil.model.InquilinoModel
import br.com.fiap.contratoagil.model.Usuario

class DadosBasicosScreenViewModel: ViewModel() {

    val listaGenero = listOf<String>("Masculino", "Feminino")

    private val _nomeUsu = MutableLiveData<String>()
    val nomeUsu: LiveData<String> = _nomeUsu

    private val _genero = MutableLiveData<String>()
    val genero: LiveData<String> = _genero

    private val _rg = MutableLiveData<String>()
    val rg: LiveData<String> = _rg

    private val _cpf = MutableLiveData<String>()
    val cpf: LiveData<String> = _cpf

    private val _qualificacao = MutableLiveData<String>()
    val qualificacao: LiveData<String> = _qualificacao

    fun onQualificacaoChanged(qualificacao: String) {
        _qualificacao.value = qualificacao
    }

    fun onCpfChanged(cpf: String) {
        _cpf.value = cpf
    }

    fun onRgChanged(rg: String) {
        _rg.value = rg
    }

    fun onNomeUsuChanged(nomeUsu: String) {
        _nomeUsu.value = nomeUsu
    }

    fun onGeneroChanged(genero: String) {
        _genero.value = genero
    }

    fun btnCadastrar(context: Context, navController: NavController) {
        val dadosBasicosRepository = UsuarioRepository(context)
        dadosBasicosRepository.salvar(Usuario(id = 0,
            nomeUsu = nomeUsu.value.toString(),
            genero = genero.value.toString(),
            rg = rg.value.toString(),
            cpf = cpf.value.toString(),
            qualificacao = qualificacao.value.toString()
        ))
        Toast.makeText(context, "Dados Básicos cadastrados com sucesso", Toast.LENGTH_SHORT).show()
        navController.navigate("home")
    }



}