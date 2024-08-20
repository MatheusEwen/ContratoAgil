package br.com.fiap.contratoagil.database.repository

import android.content.Context
import br.com.fiap.contratoagil.database.dao.InstanciaDb
import br.com.fiap.contratoagil.model.ImovelModel
import br.com.fiap.contratoagil.model.InquilinoModel


class InquilinoRepository (context: Context) {

    private val db = InstanciaDb.getDatabase(context).inquilinoDao()

    fun salvar(inquilino: InquilinoModel): Long{
        return  db.salvar(inquilino)
    }

    fun atualizar(inquilino: InquilinoModel): Int{
        return db.atualizar(inquilino)
    }

    fun excluir(inquilino: InquilinoModel) : Int{
        return db.excluir(inquilino)
    }

    fun listarInquilinos(): List<InquilinoModel>{
        return db.listarInquilinos()
    }

    fun listarObjInquilinos(): List<InquilinoModel>{
        return db.listarInquilinos()
    }

    fun buscarContatoPeloId(id: Int):InquilinoModel{
        return db.buscarInquilinoPeloId(id)
    }

}