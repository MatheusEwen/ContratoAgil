package br.com.fiap.contratoagil.helper

import android.util.Base64

class Base64Custom {

    companion object{
        fun codificarBase64(texto: String): String? {
            return Base64.encodeToString(texto.toByteArray(), Base64.DEFAULT)
                .replace("(\\n|\\r)".toRegex(), "")
        }

    }

}