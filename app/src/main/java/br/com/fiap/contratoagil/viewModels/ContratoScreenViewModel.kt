package br.com.fiap.contratoagil.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.contratoagil.database.repository.ContratoRepository
import br.com.fiap.contratoagil.database.repository.UsuarioRepository
import br.com.fiap.contratoagil.model.ContratoModel
import br.com.fiap.contratoagil.model.ImovelModel
import br.com.fiap.contratoagil.model.InquilinoModel
import br.com.fiap.contratoagil.model.Usuario
import java.text.SimpleDateFormat

class ContratoScreenViewModel: ViewModel() {

    val prazoReajusteList = listOf<String>("Mensal", "Semestral", "Anual")
    val indiceReajusteList = listOf<String>("IGP-M", "INPC", "IPC", "IPCA")

    private val _nomeLocatario = MutableLiveData<String>()
    val nomeLocatario: LiveData<String> = _nomeLocatario

    private val _locatario = MutableLiveData<InquilinoModel>()
    val locatario: LiveData<InquilinoModel> = _locatario

    private val _imovel = MutableLiveData<ImovelModel>()
    val imovel: LiveData<ImovelModel> = _imovel

    private val _meioPagamento = MutableLiveData<String>()
    val meioPagamento: LiveData<String> = _meioPagamento

    private val _diaPagamento = MutableLiveData<String>()
    val diaPagamento: LiveData<String> = _diaPagamento

    private val _nomeImovel = MutableLiveData<String>()
    val nomeImovel: LiveData<String> = _nomeImovel

    private val _prazoReajuste = MutableLiveData<String>()
    val prazoReajuste: LiveData<String> = _prazoReajuste

    private val _indiceReajuste = MutableLiveData<String>()
    val indiceReajuste: LiveData<String> = _indiceReajuste

    private val _dtVigencia = MutableLiveData<String>()
    val dtVigencia: LiveData<String> = _dtVigencia

    private val _dtTermino = MutableLiveData<String>()
    val dtTermino: LiveData<String> = _dtTermino

    private val _valorAluguel = MutableLiveData<String>()
    val valorAluguel: LiveData<String> = _valorAluguel

    fun onNomeLocatarioChanged(nomeLocatario: String) {
        _nomeLocatario.value = nomeLocatario
    }

    fun onLocatarioChanged(locatario: InquilinoModel) {
        _locatario.value = locatario
    }

    fun onImovelChanged(imovel: ImovelModel) {
        _imovel.value = imovel
    }

    fun onMeioPagamentoChanged(meioPagamento: String) {
        _meioPagamento.value = meioPagamento
    }

    fun onDiaPagamentoChanged(diaPagamento: String) {
        _diaPagamento.value = diaPagamento
    }

    fun onNomeImovelChanged(nomeImovel: String) {
        _nomeImovel.value = nomeImovel
    }

    fun onPrazoReajusteChanged(prazoReajuste: String) {
        _prazoReajuste.value = prazoReajuste
    }

    fun onIndiceReajusteChanged(indiceReajuste: String) {
        _indiceReajuste.value = indiceReajuste
    }

    fun onDtVigenciaChanged(dtVigencia: String) {
        _dtVigencia.value = dtVigencia
    }

    fun onDtTerminoChanged(dtTermino: String) {
        _dtTermino.value = dtTermino
    }

    fun onValorAluguelChanged(valorAluguel: String) {
        _valorAluguel.value = valorAluguel
    }


    fun btnCadastrar(context: Context) {
        val contratoRepository = ContratoRepository(context)
        contratoRepository.salvar(ContratoModel(id = 0,
            nomeLocatario = locatario.value!!.nomeLocatario,
            meioPagamento = meioPagamento.value.toString(),
            diaPagamento = diaPagamento.value.toString(),
            nomeImovel = imovel.value!!.nomeImovel,
            prazoReajuste = prazoReajuste.value.toString(),
            indiceReajuste = indiceReajuste.value.toString(),
            dtVigencia = dtVigencia.value.toString(),
            dtTermino = dtTermino.value.toString(),
            valorAluguel = valorAluguel.value.toString()
        ))
    }

    fun listarUsuario(context: Context): Usuario {
        val usuarioRepository = UsuarioRepository(context)
        return usuarioRepository.listarUsuario()
    }

}