package br.com.fiap.contratoagil.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_Imovel")
data class ImovelModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nomeImovel: String = "",
    val qtdQuartos: String = "",
    val qtdBanheiros: String = "",
    val mtQuadrados: String = "",
    val endereco: String = "",
    val obs: String = "",

)