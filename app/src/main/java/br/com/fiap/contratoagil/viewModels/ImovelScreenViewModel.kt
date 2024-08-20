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

class ImovelScreenViewModel : ViewModel() {

    private val _nomeImovel = MutableLiveData<String>()
    val nomeImovel: LiveData<String> = _nomeImovel

    private val _statusAlert = MutableLiveData<Boolean>()
    val statusAlert: LiveData<Boolean> = _statusAlert

    private val _idImovel = MutableLiveData<Int>()
    val idImovel: LiveData<Int> = _idImovel

    private val _qtdQuartos = MutableLiveData<String>()
    val qtdQuartos: LiveData<String> = _qtdQuartos

    private val _qtdBanheiros = MutableLiveData<String>()
    val qtdBanheiros: LiveData<String> = _qtdBanheiros

    private val _mtQuadrados = MutableLiveData<String>()
    val mtQuadrados: LiveData<String> = _mtQuadrados

    private val _endereco = MutableLiveData<String>()
    val endereco: LiveData<String> = _endereco

    private val _obs = MutableLiveData<String>()
    val obs: LiveData<String> = _obs

    private val _imovel = MutableLiveData<ImovelModel>()
    val imovel: LiveData<ImovelModel> = _imovel

    private val _textBtn = MutableLiveData<String>()
    val textBtn: LiveData<String> = _textBtn

    fun onTextBtnChanged(textBtn: String) {
        _textBtn.value = textBtn
    }
    fun onStatusAlertChanged(statusAlert: Boolean) {
        _statusAlert.value = statusAlert
    }

    fun onIdMovelChanged(idImovel: Int) {
        _idImovel.value = idImovel
    }

    fun onImovelChanged(imovel: ImovelModel) {
        _imovel.value = imovel
    }

    fun onNomeImovelChanged(nomeImovel: String) {
        _nomeImovel.value = nomeImovel
    }

    fun onQtdQuartosChanged(qtdQuartos: String) {
        _qtdQuartos.value = qtdQuartos
    }

    fun onQtdBanheirosChanged(qtdBanheiros: String) {
        _qtdBanheiros.value = qtdBanheiros
    }

    fun onMtQuadradosChanged(mtQuadrados: String) {
        _mtQuadrados.value = mtQuadrados
    }

    fun onEnderecoChanged(endereco: String) {
        _endereco.value = endereco
    }

    fun onObsChanged(obs: String) {
        _obs.value = obs
    }

    fun listarImoveis(context: Context): List<ImovelModel> {
        val imovelRepository = ImovelRepository(context)
        return imovelRepository.listarImoveis()
    }


    fun btnCadastrar(context: Context) {
        val imovelRepository = ImovelRepository(context)
        imovelRepository.salvar(ImovelModel(id = 0,
            nomeImovel = nomeImovel.value.toString(),
            endereco = endereco.value.toString(),
            qtdQuartos = qtdQuartos.value.toString(),
            qtdBanheiros = qtdBanheiros.value.toString(),
            mtQuadrados = mtQuadrados.value.toString(),
            obs = obs.value.toString()
        )
        )
        Toast.makeText(context, "Imovel Cadastrado com Sucesso", Toast.LENGTH_SHORT).show()
    }

    fun btnAlterar(context: Context) {
        val imovelRepository = ImovelRepository(context)
        imovelRepository.atualizar(ImovelModel(id = idImovel.value!!,
            nomeImovel = nomeImovel.value.toString(),
            endereco = endereco.value.toString(),
            qtdQuartos = qtdQuartos.value.toString(),
            qtdBanheiros = qtdBanheiros.value.toString(),
            mtQuadrados = mtQuadrados.value.toString(),
            obs = obs.value.toString()))
        Toast.makeText(context, "Imovél Alterado com Sucesso", Toast.LENGTH_SHORT).show()

    }

    fun btnExcluir(context: Context) {
        val imovelRepository = ImovelRepository(context)
        imovelRepository.excluir(ImovelModel(id = idImovel.value!!,
            nomeImovel = nomeImovel.value.toString(),
            endereco = endereco.value.toString(),
            qtdQuartos = qtdQuartos.value.toString(),
            qtdBanheiros = qtdBanheiros.value.toString(),
            mtQuadrados = mtQuadrados.value.toString(),
            obs = obs.value.toString()))
        Toast.makeText(context, "Imovél EXCLUIDO com Sucesso", Toast.LENGTH_SHORT).show()

    }


}