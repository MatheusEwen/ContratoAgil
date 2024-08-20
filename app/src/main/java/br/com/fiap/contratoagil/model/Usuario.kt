package br.com.fiap.contratoagil.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import br.com.fiap.contratoagil.ConfiguracaoFirebase
import com.google.firebase.database.DatabaseReference

@Entity(tableName = "tbl_Usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nomeUsu: String = "",
    val genero: String = "",
    val rg: String = "",
    val cpf: String = "",
    val qualificacao: String = "",

    ) {

    @Ignore
    private var _nome: String? = null
    @Ignore
    private var _email: String? = null
    @Ignore
    private var _senha: String? = null
    @Ignore
    private var _idUsuario: String? = null

    fun Usuario() {

    }

    @Ignore
    fun salvar() {
        val firebase: DatabaseReference? = ConfiguracaoFirebase.getFirebaseDatabase()
        firebase?.child("usuarios")
            ?.child(this._idUsuario!!)
            ?.child("0")
            ?.setValue(this!!)
    }

    @Ignore
    fun getNome(): String? {
        return _nome
    }

    @Ignore
    fun setNome(nome: String?) {
        _nome = nome
    }

    @Ignore
    fun getEmail(): String? {
        return _email
    }

    @Ignore
    fun setEmail(email: String?) {
        _email = email
    }


    @Ignore
    fun getSenha(): String? {
        return _senha
    }

    @Ignore
    fun setSenha(senha: String?) {
        _senha = senha
    }


    @Ignore
    fun getIdUsuario(): String? {
        return _idUsuario
    }

    @Ignore
    fun setIdUsuario(idUsuario: String?) {
        _idUsuario = idUsuario
    }


}