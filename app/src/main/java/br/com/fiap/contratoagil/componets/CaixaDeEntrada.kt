package br.com.fiap.contratoagil.componets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CaixaDeEntrada(
    value: Any,
    placeholder: String,
    label: String,
    modifier: Modifier,
    keyboardType: KeyboardType,
    atualizaValor: (String) -> Unit
) {
    OutlinedTextField(
        value = value.toString(),
        onValueChange = {
            atualizaValor(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(),
        placeholder = {
            Text(text = placeholder)
        },
        label = {
            Text(text = label)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )

}