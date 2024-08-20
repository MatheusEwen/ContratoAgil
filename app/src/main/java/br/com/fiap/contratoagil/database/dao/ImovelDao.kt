package br.com.fiap.contratoagil.database.dao

import androidx.room.*
import br.com.fiap.contratoagil.model.ImovelModel

@Dao
interface ImovelDao {

    @Insert
    fun salvar(imovel: ImovelModel): Long

    @Update
    fun atualizar(imovel: ImovelModel): Int

    @Delete
    fun excluir(imovel: ImovelModel): Int

    @Query("SELECT COUNT() AS QTD FROM tbl_imovel")
    fun contaImoveis(): Int

    @Query("SELECT * FROM tbl_imovel ORDER BY nomeImovel ASC")
    fun listarImoveis(): List<ImovelModel>

}