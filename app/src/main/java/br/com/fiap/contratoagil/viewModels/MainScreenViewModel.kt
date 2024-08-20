package br.com.fiap.contratoagil.viewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import br.com.fiap.contratoagil.ConfiguracaoFirebase
import br.com.fiap.contratoagil.database.repository.ContratoRepository
import br.com.fiap.contratoagil.database.repository.ImovelRepository
import br.com.fiap.contratoagil.model.ContratoModel
import java.text.SimpleDateFormat
import java.util.*

class MainScreenViewModel : ViewModel() {

    var autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao()

    fun singOut() {
        autenticacao!!.signOut()
    }

    fun BuscaQtdImoveis(context: Context): Int {
        val imovelRepository = ImovelRepository(context)
        return imovelRepository.contaImoveis()
    }

    fun dataFormatada(dataString: String, formato: String): Any? {
        try {
            val sdf = SimpleDateFormat(formato)
            return formatarDataParaString(sdf.parse(dataString), "dd MMMM yyyy")
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    fun formatarDataParaString(data: Date, formato: String): String {
        val sdf = SimpleDateFormat(formato)
        return sdf.format(data)
    }

    fun proximosVencimentos(context: Context): List<ContratoModel> {
        val contratoRepository = ContratoRepository(context)
        val list = contratoRepository.vencimentosProximos()
        val formato = SimpleDateFormat("yyyy-MM-dd")
        val dataAtual = Date()
        val calendario = Calendar.getInstance()

        // Defina a data atual no objeto Calendar
        calendario.time = dataAtual

        // Adicione três meses à data atual
        calendario.add(Calendar.MONTH, 3)
        val dataTresMesesFuturo = calendario.time

        // Filtra a lista de objetos
        return list.filter { objeto ->
            val dataObjeto = formato.parse(objeto.dtTermino)
            dataObjeto?.after(dataAtual) == true && dataObjeto.before(dataTresMesesFuturo)

        }
    }
}