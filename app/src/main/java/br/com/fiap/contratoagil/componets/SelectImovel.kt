package br.com.fiap.contratoagil.componets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.LiveData
import br.com.fiap.contratoagil.model.ImovelModel
import br.com.fiap.contratoagil.model.InquilinoModel
import br.com.fiap.contratoagil.viewModels.ContratoScreenViewModel
import br.com.fiap.contratoagil.viewModels.ImovelScreenViewModel


@Composable
fun dropDownMenuImovel(
    label: String,
    listaItems: List<ImovelModel>,
    contratoScreenViewModel: ContratoScreenViewModel,
    atualizaValor: (String) -> Unit){

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listaItems
    var selectedText by remember { mutableStateOf("") }


    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column() {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {
                atualizaValor(selectedText)
                selectedText = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(label) },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label.nomeImovel
                    contratoScreenViewModel.onImovelChanged(label)
                    atualizaValor(selectedText)
                    expanded = false

                },
                    text = { Text(text = label.nomeImovel) }

                )

            }
        }
    }

}
