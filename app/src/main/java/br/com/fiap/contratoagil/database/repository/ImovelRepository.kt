package br.com.fiap.contratoagil.database.repository

import android.content.Context
import br.com.fiap.contratoagil.database.dao.InstanciaDb
import br.com.fiap.contratoagil.model.ImovelModel

class ImovelRepository (context: Context) {

    private val db = InstanciaDb.getDatabase(context).imovelDao()

    fun salvar(contato: ImovelModel): Long{
        return  db.salvar(contato)
    }

    fun atualizar(contato: ImovelModel): Int{
        return db.atualizar(contato)
    }

    fun excluir(contato: ImovelModel) : Int{
        return db.excluir(contato)
    }

    fun listarImoveis(): List<ImovelModel>{
        return db.listarImoveis()
    }

    fun contaImoveis():Int{
        return db.contaImoveis()
    }

}