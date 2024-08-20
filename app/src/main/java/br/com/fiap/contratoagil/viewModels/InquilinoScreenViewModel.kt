package br.com.fiap.contratoagil.viewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.contratoagil.database.repository.ImovelRepository
import br.com.fiap.contratoagil.database.repository.InquilinoRepository
import br.com.fiap.contratoagil.model.ImovelModel
import br.com.fiap.contratoagil.model.InquilinoModel

class InquilinoScreenViewModel: ViewModel() {

    val listaGenero = listOf<String>("Masculino", "Feminino")
    val listaEstadoCivil = listOf<String>("Solteiro", "Casado", "Divorciado", "Viuvo", "Separado")

    private val _nomeLocatario = MutableLiveData<String>()
    val nomeLocatario: LiveData<String> = _nomeLocatario

    private val _rg = MutableLiveData<String>()
    val rg: LiveData<String> = _rg

    private val _cpf = MutableLiveData<String>()
    val cpf: LiveData<String> = _cpf

    private val _qualificacao = MutableLiveData<String>()
    val qualificacao: LiveData<String> = _qualificacao

    private val _statusAlert = MutableLiveData<Boolean>()
    val statusAlert: LiveData<Boolean> = _statusAlert

    private val _inquilino = MutableLiveData<InquilinoModel>()
    val inquilino: LiveData<InquilinoModel> = _inquilino

    private val _idInquilino = MutableLiveData<Int>()
    val idInquilino: LiveData<Int> = _idInquilino

    private val _genero = MutableLiveData<String>()
    val genero: LiveData<String> = _genero

    private val _estadoCivil = MutableLiveData<String>()
    val estadoCivil: LiveData<String> = _estadoCivil

    private val _qtdMembros = MutableLiveData<String>()
    val qtdMembros: LiveData<String> = _qtdMembros

    private val _telefone = MutableLiveData<String>()
    val telefone: LiveData<String> = _telefone

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _textBtn = MutableLiveData<String>()
    val textBtn: LiveData<String> = _textBtn

    fun onTextBtnChanged(textBtn: String) {
        _textBtn.value = textBtn
    }
    fun onRgChanged(rg: String) {
        _rg.value = rg
    }
    fun onCpfChanged(cpf: String) {
        _cpf.value = cpf
    }
    fun onQualificacaoChanged(qualificacao: String) {
        _qualificacao.value = qualificacao
    }

    fun onNomeLocatarioChanged(nomeLocatario: String) {
        _nomeLocatario.value = nomeLocatario
    }

    fun onInquilinoChanged(inquilino: InquilinoModel) {
        _inquilino.value = inquilino
    }

    fun onIdInquilinoChanged(idInquilino: Int) {
        _idInquilino.value = idInquilino
    }

    fun onGeneroChanged(genero: String) {
        _genero.value = genero
    }

    fun onEstadoCivilChanged(estadoCivil: String) {
        _estadoCivil.value = estadoCivil
    }

    fun onQtdMembrosChanged(qtdMembros: String) {
        _qtdMembros.value = qtdMembros
    }

    fun onTelefooneChanged(telefone: String) {
        _telefone.value = telefone
    }

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onStatusAlertChanged(statusAlert: Boolean) {
        _statusAlert.value = statusAlert
    }

    fun listarInquilinos(context: Context): List<InquilinoModel> {
        val inquilinoRepository = InquilinoRepository(context)
        return inquilinoRepository.listarInquilinos()
    }

    fun listarObjInquilino(context: Context): List<InquilinoModel>{
        val inquilinoRepository = InquilinoRepository(context)
        return inquilinoRepository.listarObjInquilinos()
    }

    fun btnCadastrar(context: Context) {
        val inquilinoRepository = InquilinoRepository(context)
        inquilinoRepository.salvar(InquilinoModel(id = 0,
            nomeLocatario = nomeLocatario.value.toString(),
            genero = genero.value.toString(),
            estadoCivil = estadoCivil.value.toString(),
            qtdMembros = qtdMembros.value.toString(),
            telefone = telefone.value.toString(),
            email = email.value.toString(),
            rg = rg.value.toString(),
            cpf = cpf.value.toString(),
            qualificacao = qualificacao.value.toString()
        ))
        Toast.makeText(context, "Inquilino Cadastrado com Sucesso", Toast.LENGTH_SHORT).show()
    }

    fun btnAlterar(context: Context) {

        val inquilinoRepository = InquilinoRepository(context)
        inquilinoRepository.atualizar(InquilinoModel(id = idInquilino.value!!,
            nomeLocatario = nomeLocatario.value.toString(),
            genero = genero.value.toString(),
            estadoCivil = estadoCivil.value.toString(),
            qtdMembros = qtdMembros.value.toString(),
            telefone = telefone.value.toString(),
            email = email.value.toString(),
            rg = rg.value.toString(),
            cpf = cpf.value.toString(),
            qualificacao = qualificacao.value.toString()
        ))
        Toast.makeText(context, "Inquilino Alterado com Sucesso", Toast.LENGTH_SHORT).show()

    }

    fun btnExcluir(context: Context) {

        val inquilinoRepository = InquilinoRepository(context)
        inquilinoRepository.excluir(InquilinoModel(id = idInquilino.value!!,
            nomeLocatario = nomeLocatario.value.toString(),
            genero = genero.value.toString(),
            estadoCivil = estadoCivil.value.toString(),
            qtdMembros = qtdMembros.value.toString(),
            telefone = telefone.value.toString(),
            email = email.value.toString(),
            rg = rg.value.toString(),
            cpf = cpf.value.toString(),
            qualificacao = qualificacao.value.toString()
        ))
        Toast.makeText(context, "Inquilino Excluido com Sucesso", Toast.LENGTH_SHORT).show()

    }

}