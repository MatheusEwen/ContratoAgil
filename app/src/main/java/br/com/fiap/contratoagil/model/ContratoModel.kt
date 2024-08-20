package br.com.fiap.contratoagil.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_contrato")
data class ContratoModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nomeLocatario: String = "",
    val meioPagamento: String = "",
    val diaPagamento: String = "",
    val nomeImovel: String = "",
    val prazoReajuste: String = "",
    val indiceReajuste: String = "",
    val dtVigencia: String = "",
    val dtTermino: String = "",
    val valorAluguel: String = "",

    )