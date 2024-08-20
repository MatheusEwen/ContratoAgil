package br.com.fiap.contratoagil.database.repository

import android.content.Context
import br.com.fiap.contratoagil.database.dao.InstanciaDb
import br.com.fiap.contratoagil.model.ImovelModel
import br.com.fiap.contratoagil.model.InquilinoModel
import br.com.fiap.contratoagil.model.Usuario

class UsuarioRepository(context: Context) {

    private val db = InstanciaDb.getDatabase(context).usuarioDao()

    fun salvar(usuario: Usuario): Long{
        return  db.salvar(usuario)
    }

    fun atualizar(usuario: Usuario): Int{
        return db.atualizar(usuario)
    }

    fun excluir() : Int{
        return db.excluir()
    }

    fun listarUsuario(): Usuario{
        return db.listarUsuario()
    }

    fun listarUsuarios(): List<Usuario>{
        return db.listarUsuarios()
    }

    fun listarUsuarios2(): List<Usuario>{
        return db.listarUsuarios()
    }

    fun buscarContatoPeloId(id: Int): Usuario {
        return db.buscarUsuarioPeloId(id)
    }
}