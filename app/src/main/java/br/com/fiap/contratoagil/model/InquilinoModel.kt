package br.com.fiap.contratoagil.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_Inquilino")
data class InquilinoModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nomeLocatario: String = "",
    val genero: String = "",
    val estadoCivil: String = "",
    val qtdMembros: String = "",
    val telefone: String = "",
    val email: String = "",
    val rg: String = "",
    val cpf: String = "",
    val qualificacao: String = "",

    )
