package com.example.appcomponente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcomponente.ui.theme.AppcomponenteTheme
import org.w3c.dom.Text
import androidx.compose.material3.Text as Text1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppcomponenteTheme {
                MySingleScreenPreview()
            }
        }
    }
}

@Preview
@Composable
fun MySingleScreenPreview() {
    MenuTopBar()
    Formulario()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTopBar() {
    TopAppBar(
        title = { Text1("Cadastro") },
        navigationIcon = {
            IconButton(onClick = { /* Handle Menu click */ }) {
                Icon(Icons.Default.Menu, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { /* Handle MoreVert click */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulario() {
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var observacao by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Telefone Fixo") }
    val options = listOf("Telefone Fixo", "Celular", "Whatsapp")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(80.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text1("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Column {
            TextField(
                value = telefone,
                onValueChange = { telefone = it },
                label = { Text1("Telefone") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            var expanded by remember { mutableStateOf(false) }
            CustomDropdown(
                options = options,
                selectedOption = selectedOption,
                onOptionSelected = { selectedOption = it },
                expanded = expanded,
                onExpandedChange = { expanded = it }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        BasicTextField(
            value = observacao,
            onValueChange = { observacao = it },
            singleLine = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {

                }
            ),
            textStyle = TextStyle.Default.copy(
                lineHeight = 20.sp
            ),
            modifier = Modifier.fillMaxWidth().height(120.dp),
            decorationBox = { innerTextField ->

                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            shape = MaterialTheme.shapes.small
                        )
                ) {
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {

                },
                modifier = Modifier.weight(1f),
            ) {
                Text1("Cadastrar", color = Color.White)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {

                },
                modifier = Modifier.weight(1f),
            ) {
                Text1("Cancelar", color = Color.White)
            }
        }
    }
}

@Composable
fun CustomDropdown(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onExpandedChange(!expanded) }
                .background(MaterialTheme.colorScheme.background)
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    shape = MaterialTheme.shapes.small
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text1(
                    text = selectedOption,
                    modifier = Modifier.weight(1f),
                    style = TextStyle(fontSize = 16.sp)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        onExpandedChange(false)
                    }
                ) {

                }
            }
        }
    }
}

fun DropdownMenuItem(onClick: () -> Unit, interactionSource: () -> Unit) {
    TODO("Not yet implemented")
}
