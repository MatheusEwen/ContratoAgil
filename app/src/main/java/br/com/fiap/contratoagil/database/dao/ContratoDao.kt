package br.com.fiap.contratoagil.database.dao

import androidx.room.*
import br.com.fiap.contratoagil.model.ContratoModel


@Dao
interface ContratoDao {

    @Insert
    fun salvar(contrato: ContratoModel): Long

    @Update
    fun atualizar(contrato: ContratoModel): Int

    @Delete
    fun excluir(contrato: ContratoModel): Int

    @Query("SELECT * FROM tbl_contrato WHERE id = :id")
    fun buscarContratoPeloId(id: Int): ContratoModel

    @Query("SELECT * FROM tbl_contrato")
    fun buscarContratos(): List<ContratoModel>

}