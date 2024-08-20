package br.com.fiap.contratoagil.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.contratoagil.model.ContratoModel
import br.com.fiap.contratoagil.model.ImovelModel
import br.com.fiap.contratoagil.model.InquilinoModel
import br.com.fiap.contratoagil.model.Usuario

@Database(entities = [ImovelModel::class, InquilinoModel::class, ContratoModel::class, Usuario::class], version = 10)
abstract class InstanciaDb : RoomDatabase(){

    abstract fun imovelDao(): ImovelDao
    abstract fun inquilinoDao(): InquilinoDao
    abstract fun contratoDao(): ContratoDao
    abstract fun usuarioDao(): UsuarioDao

    companion object{

        private lateinit var instance: InstanciaDb

        fun getDatabase(context: Context): InstanciaDb{
            if (!::instance.isInitialized){
                instance = Room
                    .databaseBuilder(context, InstanciaDb::class.java,"contrato1_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }

    }

}