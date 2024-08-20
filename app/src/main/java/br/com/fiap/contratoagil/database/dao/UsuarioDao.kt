package br.com.fiap.contratoagil.database.dao

import androidx.room.*
import br.com.fiap.contratoagil.model.ImovelModel
import br.com.fiap.contratoagil.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    fun salvar(usuario: Usuario): Long

    @Update
    fun atualizar(usuario: Usuario): Int

    @Query("DELETE FROM tbl_Usuario")
    fun excluir(): Int

    @Query("SELECT * FROM tbl_Usuario WHERE id = :id")
    fun buscarUsuarioPeloId(id: Int): Usuario

    @Query("SELECT * FROM tbl_Usuario limit 1")
    fun listarUsuario(): Usuario

    @Query("SELECT * FROM tbl_Usuario ORDER BY nomeUsu ASC")
    fun listarUsuarios(): List<Usuario>
}