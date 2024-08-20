package br.com.fiap.contratoagil

import android.content.Context
import android.os.Environment
import android.provider.ContactsContract
import android.widget.Toast
import br.com.fiap.contratoagil.model.ContratoModel
import br.com.fiap.contratoagil.model.ImovelModel
import br.com.fiap.contratoagil.model.InquilinoModel
import br.com.fiap.contratoagil.model.Usuario
import org.apache.poi.xwpf.usermodel.Borders
import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import org.apache.poi.xwpf.usermodel.UnderlinePatterns
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.DataInput
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class Gerador() {


    fun createDocx(
        path: File,
        contratoDados: ContratoModel,
        context: Context,
        locatario: InquilinoModel,
        imovel: ImovelModel,
        usuario: Usuario
    ) {
        try {
            val document = XWPFDocument()

            val root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString()
            val myDir = File("$root/Contratos")
            myDir.mkdirs()
            var file = File(myDir, "Contrato" + "${usuario.nomeUsu}_${locatario.nomeLocatario}.docx")
            var out = FileOutputStream(file)

            val titleParagraph = document.createParagraph()
            titleParagraph.alignment = ParagraphAlignment.CENTER

            val shd = titleParagraph.ctp.addNewPPr().addNewShd()
            shd.fill = "808080" // Cor cinza em hexadecimal

            val titleRun = titleParagraph.createRun()
            titleRun.setText("CONTRATO DE LOCAÇÃO RESIDENCIAL")
            titleRun.isBold = true
            titleRun.fontSize = 16
            titleRun.underline = UnderlinePatterns.SINGLE
            titleParagraph.spacingBefore = 500

            val paragraph1 = document.createParagraph()
            val run1 = paragraph1.createRun()
            run1.setText("Locador(A)")
            run1.addBreak()

            val run2 = paragraph1.createRun()
            run2.setText("Nome: ${usuario.nomeUsu}")
            run2.addBreak()
            run2.setText("RG: ${usuario.rg}    CPF: ${usuario.cpf}")
            run2.addBreak()
            run2.setText("Qualificação: ${usuario.qualificacao}")
            run2.addBreak()
            run2.addBreak()

            val run3 = paragraph1.createRun()
            run3.setText("Locatário(A)")
            run3.addBreak()

            val run4 = paragraph1.createRun()
            run4.setText("Nome: " + locatario.nomeLocatario)
            run4.addBreak()
            run4.setText("CPF: ${locatario.cpf}     RG: ${locatario.rg}")
            run4.addBreak()
            run4.setText("Qualificação: ${locatario.qualificacao}")
            run4.addBreak()
            run4.setText("Telefone: ${locatario.telefone}")
            run4.addBreak()
            run4.addBreak()

            run4.setText("Tipo do imovél: RESIDENCIA")
            run4.addBreak()
            run4.setText("Endereço: ${imovel.endereco}")
            run4.addBreak()

            val run5 = paragraph1.createRun()
            run5.setText("VALOR DO ALUGUEL: R$" + contratoDados.valorAluguel)
            run5.addBreak()
            run5.addBreak()

            val run6 = paragraph1.createRun()
            run6.setText("REAJUSTE")
            run6.addBreak()

            val run7 = paragraph1.createRun()
            run7.setText("Prazo de reajuste: " + contratoDados.prazoReajuste)
            run7.addBreak()

            val run8 = paragraph1.createRun()
            run8.setText("Indice: " + contratoDados.indiceReajuste)
            run8.addBreak()
            run8.setText("Fica convencido que em caso de alteração na legislação vigente, a periodicidade e o índice de reajuste aqui compactuados, terão vigência na forma em que a nova norma estabelecer.")
            run8.addBreak()
            run8.addBreak()


            val run9 = paragraph1.createRun()
            run9.setText("PRAZO DO CONTRATO")
            run9.addBreak()

            val run10 = paragraph1.createRun()
            run10.setText("DATA INICIO: " + contratoDados.dtVigencia + "/ DATA TERMINO: " + contratoDados.dtTermino)
            run10.addBreak()
            run10.setText("Dia e Local de Pagamento: Todo dia ${contratoDados.diaPagamento} de cada mês, por meio de ${contratoDados.meioPagamento}")
            run10.addBreak()
            run10.addBreak()

            val run11 = paragraph1.createRun()
            run11.setText("CLÁUSULAS")
            run11.isBold = true
            run11.fontSize = 16
            run11.addBreak()
            val run12 = paragraph1.createRun()
            run12.setText("PRIMEIRA - O prazo desta locação é o constante no início deste contrato. No término indicado, o(a) locatário(a) se obriga a entregar o imovél livre e desembaraçado de coisas e pessoas, no estado em que o recebeu, conforme o TERMO DE VISTORIA independentemente de Notificação ou Interpelação Judicial, ressalvada a hipótese de prorrogação da locação.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Parágrafo único - caso o locatário não restitua o imóvel no fim do prazo contratual deverá pagar enquanto estiver na Posse do mesmo o aluguel mensal reajustado nos termos da cláusula 14ª até a efetiva desocupação do imóvel objeto deste instrumento.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Segunda - todos os impostos e taxas que atualmente recaem sobre o imóvel locado bem como qualquer aumento dos mesmos ou novos que venham a ser criados pelo poder público são da inteira responsabilidade do locatário (a) que se obriga a pagá-los ao (a) locador (a) para que este os liquide em seus respectivos vencimentos. São ainda de responsabilidade do (a) locatário (a) as contas de luz água e gás que não são inclusos no valor do aluguel assim como outras despesas atinentes.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Parágrafo único – O(a) locatário(a) será responsável pelas despesas e multas decorrentes de eventuais retenções dos avisos de impostos taxas e outros que já incidem ou venham a incidir sobre o imóvel objeto da presente locação bem como os recibos referentes aos impostos e taxas serão entregues juntamente com o do aluguel correspondente ao mês fazendo parte integrante do mesmo.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Terceira - A falta de pagamento e somente deverá ser realizado no local e forma prevista no contrato nas épocas supra determinadas dos aluguéis e encargos por si só constitui o(a) locatário (a) em mora independentemente de qualquer notificação interpelação ou aviso extrajudicial.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Quarta - executadas as obras ou reparações que sejam necessárias a segurança do imóvel obriga-se o (a) locatário(a) pelas demais devendo manter o imóvel locado e seus pertences que ora recebe em perfeito estado de funcionamento conservação e limpeza notadamente as instalações sanitárias e elétricas vidros e pinturas fato que é comprovado pelo a locatário (a) e seus fiadores por vistoria realizada.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Quinta - todas as benfeitorias que forem feitas excluídas naturalmente as instalações de natureza profissional e imóveis ficarão integradas ao imóvel sem que por elas tenham o (a) locatário a direito a qualquer indenização ou pagamento a introdução de tais bem feitorias dependerá de autorização por escrito do locador (a).")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Parágrafo único - Quando do término da locação o (a) locatário (a) deverá restituir o imóvel nas mesmas condições em que se recebe agora ficando desde já convencionado que se não o fizer o (a) locador (a) estará autorizado a mandar executar todos os reparos necessários cobrando do a locatário (a) a importância gasta como encargos de locação mediante tomada de preço de 3 empresas especializadas servindo de título hábil recibo passado pelo executante dos serviços.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Sexta - é expressada  vedado o(a) locatário (a) sublocar o imóvel no todo ou em parte cedê-lo a terceiros seja a título gratuito ou oneroso transferir este contato contrato ou dar destinação diversa do uso ou finalidade previsto no presente instrumento sem prévia anuência por escrito dada pelo locador (a).")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Sétima - no caso de desapropriação do imóvel objeto deste contrato o(a) locador (a) e seus administradores e ou procuradores ficarão exonerados de toda e qualquer responsabilidade decorrente deste instrumento ressalvando-se o(a) locatário (a) faculdade de agir tão somente contra o poder expropriador.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Oitava - fica o(a) locador (a) por si ou por seus prepostos autorizado a vistoriar o imóvel sempre que julgar conveniente")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Nona – O (a) locatário (a) se obriga a satisfazer por sua conta exclusiva a qualquer exigência dos poderes públicos em razão da atividade exercida no imóvel assumindo toda a responsabilidade por quaisquer infrações em que ocorrer a este propósito por inobservância das determinações das autoridades competentes.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Décima – o (a) locatário (a) declara neste ato ter pleno conhecimento de que o resgate de recibos posteriores não significa nem representa quinta são de outras obrigações de pula no presente contrato deixadas de cobrar nas épocas certas principalmente os encargos fixados no presente instrumento.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Décima-Primeiro -  Se o(a) locador(a) admitir em benefício do (a) locatário (a) qualquer atraso no pagamento do aluguel e de mais de despesas que lhe incubam ou no cumprimento de qualquer outra obrigação contratual essa tolerância não pode ser considerada como alteração nas condições deste contrato pois se constitui em ato de mera liberalidade do locador.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Décima-Segunda -Tudo o que for devido em razão deste contrato será cobrado em processo executivo ou em ação apropriada no foro da situação do imóvel com Renúncia de qualquer outro por mais privilegiado que seja correndo por conta da parte vencida além do principal e da multa estipulada na cláusula 13ª da todas as despesas judiciais e extrajudiciais +10% de honorários advocatícios essa porcentagem será devida mesmo que a responsabilidade seja liquidada amigavelmente no departamento jurídico independentemente de qualquer procedimento judicial não podendo o(a) locatário (a) sobre qualquer pretexto se opor a esse pagamento.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Décima-Terceira - Fica estipulada a multa de 3 aluguéis vigente a época da infração na qual incorrer a parte que infringir qualquer uma das cláusulas deste contrato ressalvada a parte inocente o direito de poder considerar simultaneamente rescindida a locação independentemente de qualquer outra formalidade judicial ou extrajudicial.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Décima-Quarta-  Na hipótese de ocorrer a prorrogação desta locação o aluguel mensal será reajustado de acordo com os índices permitidos pela legislação em vigor a época da prorrogação preferencialmente o pactuado no presente instrumento.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Décima-Quinta- Fica acordado que a falta de pagamento do aluguel no vencimento acarretar multa automática de 10% sobre o valor do aluguel além dos honorários advocatícios previstos neste instrumento sendo que o atraso superior a 30 dias incidirá sobre os aluguéis 1% de juros de mora bem como a correção que couber com base em índices usuais permitidos para atualização monetária desta espécie sem prejuízo a competente ação de despejo por falta de pagamento.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Décima-Sexta-  Será por conta do a locatário (a) o seguro complementar ou integral do imóvel relativo a incêndio e outros sinistros no valor de avaliação indicado pela cia seguradora a ser feito pelo locador (a) cia de seguros de sua livre escolha sendo que o valor do prêmio ou a locatário (a) autoriza desde já seja cobrado juntamente com os aluguéis a título de acessórios.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Décima-Sétima-  Em caso de devolução de cheques relativo a pagamento da locação e seus encargos seja a que título for seja remetido o recibo com os encargos pertinentes inclusive CPMF acrescido dos honorários advocatícios se os previstos neste instrumento e ensejo ao imediato pedido de despejo por falta de pagamento.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Décima-Oitava- O (a) locatário (a) fica na obrigação de transferir a ligação de luz para o seu nome dentro do prazo de 30 dias do início deste contrato caso o(a) locatário (a) não faça fica de pleno direito do (a) locador (a) mandar cortar no órgão competente ou fornecimento além da Constituição em falta grave com a aplicação da penalidade prevista na cláusula 13ª do presente instrumento.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Décima-Nona-  A Entrega das chaves do imóvel para vistoria só poderá ser feita ao locador e nunca à outra pessoa após o (a) locatário a haver cumprido todas as cláusulas e condições previstas neste contrato sob pena de não fazê-lo continuar responsável pelos aluguéis e encargos até o acerto final.")
            run12.addBreak()
            run12.addBreak()
            run12.setText("E por estarem as partes justas e contratadas e de pleno acordo com todas as cláusulas e condições do presente contrato de locação as partes por si seus herdeiros e sucessores assinam este instrumento nas suas 3 vias de igual teor para um só efeito na presença das testemunhas abaixo.")
            run12.addBreak()
            run12.addBreak()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val dataAtual = dateFormat.format(Date())
            run12.setText(dataAtual.toString())
            run12.addBreak()
            run12.addBreak()
            run12.addBreak()
            run12.setText("______________________________________")
            run12.addBreak()
            run12.setText("Locador: ${usuario.nomeUsu}")
            run12.addBreak()
            run12.addBreak()
            run12.addBreak()
            run12.setText("______________________________________")
            run12.addBreak()
            run12.setText("Locatária: ${locatario.nomeLocatario}")
            run12.addBreak()
            run12.setText("TESTEMUNHAS:")
            run12.addBreak()
            run12.setText("Testemunha 1: ___________________________________")
            run12.addBreak()
            run12.addBreak()
            run12.setText("Testemunha 2: ___________________________________")
            document.write(out)
            //outputStream.close()
            out.close()
            Toast.makeText(context, "Documento criado com sucesso", Toast.LENGTH_SHORT).show()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }


}