package br.com.fiap.contratoagil.viewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import br.com.fiap.contratoagil.ConfiguracaoFirebase
import br.com.fiap.contratoagil.database.repository.ContratoRepository
import br.com.fiap.contratoagil.database.repository.ImovelRepository
import br.com.fiap.contratoagil.database.repository.InquilinoRepository
import br.com.fiap.contratoagil.database.repository.UsuarioRepository
import br.com.fiap.contratoagil.helper.Base64Custom
import br.com.fiap.contratoagil.model.ContratoModel
import br.com.fiap.contratoagil.model.ImovelModel
import br.com.fiap.contratoagil.model.InquilinoModel
import br.com.fiap.contratoagil.model.Usuario
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class ConfigScreenViewModel: ViewModel() {

    val listaGenero = listOf<String>("Masculino", "Feminino")

    private val _nomeUsu = MutableLiveData<String>()
    val nomeUsu: LiveData<String> = _nomeUsu

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    private val _genero = MutableLiveData<String>()
    val genero: LiveData<String> = _genero

    private val _rg = MutableLiveData<String>()
    val rg: LiveData<String> = _rg

    private val _cpf = MutableLiveData<String>()
    val cpf: LiveData<String> = _cpf

    private val _qualificacao = MutableLiveData<String>()
    val qualificacao: LiveData<String> = _qualificacao

    fun onQualificacaoChanged(qualificacao: String) {
        _qualificacao.value = qualificacao
    }

    fun onIdChanged(id: String) {
        _id.value = id
    }

    fun onCpfChanged(cpf: String) {
        _cpf.value = cpf
    }

    fun onRgChanged(rg: String) {
        _rg.value = rg
    }

    fun onNomeUsuChanged(nomeUsu: String) {
        _nomeUsu.value = nomeUsu
    }

    fun onGeneroChanged(genero: String) {
        _genero.value = genero
    }

    fun btnAlterarDados(
        context: Context,
        navController: NavController,
        id: String,
        nomeUsu: String,
        rg: String,
        cpf: String,
        qualificacao: String,
        genero: String,
    ) {
        val dadosBasicosRepository = UsuarioRepository(context)
        val autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao()
        val idUsuario: String? = Base64Custom.codificarBase64(autenticacao?.getCurrentUser()?.getEmail()!!)
        val firebase: DatabaseReference? = ConfiguracaoFirebase.getFirebaseDatabase()
        val usu = Usuario(id = id.toInt(),
            nomeUsu = nomeUsu,
            genero = genero,
            rg = rg,
            cpf = cpf,
            qualificacao = qualificacao
        )
        dadosBasicosRepository.atualizar(usu)
        Toast.makeText(context, "Dados Básicos Alterados com sucesso", Toast.LENGTH_SHORT).show()

        firebase?.child("usuarios")
            ?.child(idUsuario!!)
            ?.child("0")
            ?.setValue(usu)

        navController.navigate("home")
    }

    fun listarUsuario(context: Context): Usuario {
        val usuarioRepository = UsuarioRepository(context)
        return usuarioRepository.listarUsuario()
    }

    fun excluirDadosBasicos(context: Context): Int {
        val usuarioRepository = UsuarioRepository(context)
        return usuarioRepository.excluir()
    }

    fun carregaDadosUser(context: Context) {

        val autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao()
        val idUsuario: String? = Base64Custom.codificarBase64(autenticacao?.getCurrentUser()?.getEmail()!!)
        val firebase: DatabaseReference? = ConfiguracaoFirebase.getFirebaseDatabase()
        val refUser = firebase?.child("usuarios")?.child(idUsuario.toString())
        refUser!!.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Aqui você obtém os dados do Firebase
                val tipo = object : GenericTypeIndicator<List<Usuario>>() {}
                val usuarios: List<Usuario>? = dataSnapshot.getValue(tipo)
                if (usuarios != null) {
                    for (usuario in usuarios) {
                        Toast.makeText(context, "usu"+ usuario.toString(), Toast.LENGTH_SHORT).show()
                        val dadosBasicosRepository = UsuarioRepository(context)
                        dadosBasicosRepository.salvar(usuario)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "erro: " + error.message, Toast.LENGTH_LONG).show()
            }
        })
    }



    fun btnBackupNuvem(context: Context) {
        val imoveis = ImovelRepository(context).listarImoveis()
        val inquilinos = InquilinoRepository(context).listarInquilinos()
        val contratos = ContratoRepository(context).vencimentosProximos()
        val autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao()
        val idUsuario: String? = Base64Custom.codificarBase64(autenticacao?.getCurrentUser()?.getEmail()!!)
        val firebase: DatabaseReference? = ConfiguracaoFirebase.getFirebaseDatabase()

        firebase?.child("imoveis")
            ?.child(idUsuario!!)
            ?.setValue(imoveis)

        firebase?.child("inquilinos")
            ?.child(idUsuario!!)
            ?.setValue(inquilinos)

        firebase?.child("contratos")
            ?.child(idUsuario!!)
            ?.setValue(contratos)
    }

    fun btnDownloadNuvem(context: Context) {
        val autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao()
        val idUsuario: String? = Base64Custom.codificarBase64(autenticacao?.getCurrentUser()?.getEmail()!!)
        val firebase: DatabaseReference? = ConfiguracaoFirebase.getFirebaseDatabase()
        val refImovel = firebase?.child("imoveis")?.child(idUsuario.toString())
        val refInquilino = firebase?.child("inquilinos")?.child(idUsuario.toString())

        refImovel?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Aqui você obtém os dados do Firebase

                val tipo = object : GenericTypeIndicator<List<ImovelModel>>() {}
                val imoveis: List<ImovelModel>? = dataSnapshot.getValue(tipo)

                if (imoveis != null) {
                    val imoveisdb = ImovelRepository(context).listarImoveis()
                    for (imovel in imoveis) {
                        if (imoveisdb.find { it.id == imovel.id } == null){
                            val imovelRepository = ImovelRepository(context)
                            imovelRepository.salvar(ImovelModel(
                                id = imovel.id,
                                nomeImovel = imovel.nomeImovel,
                                qtdBanheiros = imovel.qtdBanheiros,
                                qtdQuartos = imovel.qtdQuartos,
                                obs = imovel.obs,
                                mtQuadrados = imovel.mtQuadrados,
                                endereco = imovel.endereco
                            )
                            )
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Tratamento de erro, se necessário
            }
        })

        refInquilino?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Aqui você obtém os dados do Firebase

                val tipo = object : GenericTypeIndicator<List<InquilinoModel>>() {}
                val inquilinos: List<InquilinoModel>? = dataSnapshot.getValue(tipo)

                if (inquilinos != null) {
                    val inquilinosDb = InquilinoRepository(context).listarInquilinos()
                    for (inquilino in inquilinos) {
                        if (inquilinosDb.find { it.id == inquilino.id } == null){
                            val inquilinoRepository = InquilinoRepository(context)
                            inquilinoRepository.salvar(InquilinoModel(id = 0,
                                nomeLocatario = inquilino.nomeLocatario,
                                genero = inquilino.genero,
                                estadoCivil = inquilino.estadoCivil,
                                qtdMembros = inquilino.qtdMembros,
                                telefone = inquilino.telefone,
                                email = inquilino.email,
                                rg = inquilino.rg,
                                cpf = inquilino.cpf,
                                qualificacao = inquilino.qualificacao
                            ))
                        }
                    }
                }
                Toast.makeText(context, "Download realizado com sucesso", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Tratamento de erro, se necessário
            }
        })

    }

}