package br.com.fiap.contratoagil.database.repository

import android.content.Context
import br.com.fiap.contratoagil.database.dao.InstanciaDb
import br.com.fiap.contratoagil.model.ContratoModel

class ContratoRepository (context: Context) {

    private val db = InstanciaDb.getDatabase(context).contratoDao()

    fun salvar(contrato: ContratoModel): Long{
        return  db.salvar(contrato)
    }

    fun atualizar(contrato: ContratoModel): Int{
        return db.atualizar(contrato)
    }

    fun excluir(contrato: ContratoModel) : Int{
        return db.excluir(contrato)
    }

    fun vencimentosProximos(): List<ContratoModel>{
        return db.buscarContratos()
    }



}