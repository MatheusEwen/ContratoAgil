package br.com.fiap.contratoagil.componets

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import br.com.fiap.contratoagil.viewModels.ImovelScreenViewModel

@Composable
fun MyAlertDialog(
    titulo: String,
    text: String,
    context: Context,
    onRejeito: () -> Unit,
    onAceito: () -> Unit
) {

    AlertDialog(
        onDismissRequest = { Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show()},
        title = {
            Text(text = titulo)
        },
        text = {
            Text(text = text)
        },
        confirmButton = {
            Button(
                onClick = {
                    onAceito()
                }
            ) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onRejeito()
                    Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = "Cancelar")
            }
        }
    )
}