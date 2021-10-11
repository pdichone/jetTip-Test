package com.bawp.jettip_test.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
              ) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text =  labelId) },
        leadingIcon = { Icon(imageVector = Icons.Rounded.AttachMoney,
            contentDescription = "Money Icon")
        },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
                     )
}

@Composable
fun OutlinedTextField(modifier: Modifier = Modifier, label: String ="lala", onValChange: (String) -> Unit = {}) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { onValChange(it); text = it},
        label = { Text(label) },
        leadingIcon = { Icon(imageVector = Icons.Default.Money , contentDescription = "lala")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(onDone = {
            this.defaultKeyboardAction(imeAction = ImeAction.Done)}),


        )
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    count: Int = 1,
    signLabel: String = "+",
    onClickButton: (Int) -> Unit = {},
                ) {

    Button(onClick = {
        if (signLabel == "-"){

            onClickButton(count - 1)
        }else {
            onClickButton(count + 1)
        }

        // Log.d("TAG", "CustomButton: ${count}")
    },
        modifier = modifier
            .width(40.dp)
            .height(40.dp),
        colors  = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFe9d7f7))) {
        Text(text = signLabel,
            style = TextStyle(fontWeight = FontWeight.ExtraBold))

    }

}