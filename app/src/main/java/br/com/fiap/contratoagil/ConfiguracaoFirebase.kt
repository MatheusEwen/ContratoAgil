package br.com.fiap.contratoagil

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ConfiguracaoFirebase {



    companion object {
        private var autenticacao: FirebaseAuth? = null
        private var firebase: DatabaseReference? = null
        fun getFirebaseAutenticacao(): FirebaseAuth? {
            if (autenticacao == null) {
                autenticacao = FirebaseAuth.getInstance()
            }
            return autenticacao
        }

        fun getFirebaseDatabase(): DatabaseReference? {
            if (firebase == null) {
                firebase = FirebaseDatabase.getInstance().reference
            }
            return firebase
        }

    }





}