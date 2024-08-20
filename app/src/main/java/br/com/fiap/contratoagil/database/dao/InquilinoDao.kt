package br.com.fiap.contratoagil.database.dao

import androidx.room.*

import br.com.fiap.contratoagil.model.InquilinoModel

@Dao
interface InquilinoDao {

    @Insert
    fun salvar(inquilino: InquilinoModel): Long

    @Update
    fun atualizar(inquilino: InquilinoModel): Int

    @Delete
    fun excluir(inquilino: InquilinoModel): Int

    @Query("SELECT * FROM tbl_Inquilino WHERE id = :id")
    fun buscarInquilinoPeloId(id: Int): InquilinoModel

    @Query("SELECT * FROM tbl_Inquilino ORDER BY nomeLocatario ASC")
    fun listarInquilinos(): List<InquilinoModel>

}