package com.bawp.jettip_test

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bawp.bmi_copy.ui.widgets.RoundIconButton
import com.bawp.jettip_test.components.InputField
import com.bawp.jettip_test.ui.theme.JetTipTestTheme
import com.bawp.jettip_test.util.calculateTotalPerPerson
import com.bawp.jettip_test.util.calculateTotalTip
import kotlin.math.roundToInt

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                TipCalculator()
            }
        }
    }
}

@Composable
fun MyApp( content: @Composable () -> Unit){
    /*
      content: @Composable ... it's called a container function
      which makes MyApp more flexible to deal with
     */
    JetTipTestTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}
@ExperimentalComposeUiApi
@Composable
fun TipCalculator() {
    Surface(modifier = Modifier
        .padding(12.dp)) {
        Column() {
            MainContent()
        }
    }
}

@Preview
@Composable
fun TopHeader(totalPerPers: Double = 0.0) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .padding(12.dp)
        .clip(shape = CircleShape.copy(all = CornerSize(12.dp))),
        color = Color(0xFFe9d7f7)
           ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.Center) {
            Text(text = "Total Per Person",
                style = MaterialTheme.typography.subtitle1)
           val total =  "%.2f".format(totalPerPers)
            Text(text = "\$$total",
                style = MaterialTheme.typography.h4)


        }

    }

}

@ExperimentalComposeUiApi
@Preview
@Composable
fun MainContent() {
    BillForm()
}


@ExperimentalComposeUiApi
@Composable
fun BillForm(modifier: Modifier = Modifier,
             range: IntRange = 1..100,
             onValChange: (String) -> Unit = {}
            ) {

    val splitBy = remember {
        mutableStateOf(1)
    }

//    val sliderPosition: MutableState<Float> = remember {
//        mutableStateOf(0f)
//    }
    var sliderPosition by remember {
        mutableStateOf(0f)
    }
    val totalTipAmt = remember {
        mutableStateOf(0.0)
    }
//    val totalTipAmt: MutableState<Double> = remember {
//        mutableStateOf(0.0)
//    }
    val totalPerPerson = remember {
        mutableStateOf(0.0)
    }

    val tipPercentage = (sliderPosition * 100).toInt()
    val totalBill = rememberSaveable{ mutableStateOf("") } //or just remember {}
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(totalBill.value) {
        totalBill.value.trim().isNotEmpty()
    }

    TopHeader(totalPerPers = totalPerPerson.value)

    Surface(modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth(),
        shape = CircleShape.copy(all = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)) {

        Column(modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start) {

            InputField(
                valueState = totalBill, labelId = "Enter Bill",
                enabled = true,
                onAction = KeyboardActions {
                    //The submit button is disabled unless the inputs are valid. wrap this in if statement to accomplish the same.
                    if (!valid) return@KeyboardActions
                    onValChange(totalBill.value.trim())
                    //totalBill.value = ""
                    keyboardController?.hide() //(to use this we need to use @ExperimentalComposeUiApi
                },
                      )

            if (valid) {
                Row(modifier = Modifier.padding(3.dp), horizontalArrangement = Arrangement.Start) {
                    Text(text = "Split",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically))
                    Spacer(modifier = Modifier.width(120.dp))

                    Row(modifier = Modifier.padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End) {

                        RoundIconButton(imageVector = Icons.Default.Remove, onClick = {
                            splitBy.value = if (splitBy.value > 1) splitBy.value - 1 else 1
                            totalPerPerson.value =
                                calculateTotalPerPerson(totalBill = totalBill.value.toDouble(),
                                    splitBy = splitBy.value,
                                    tipPercent = tipPercentage)
                        })

                        Text(text = "${splitBy.value}",
                            Modifier.align(alignment = Alignment.CenterVertically)
                                .padding(start = 9.dp, end = 9.dp))
                        RoundIconButton(imageVector = Icons.Default.Add, onClick = {
                            if (splitBy.value < range.last) {
                                splitBy.value = splitBy.value + 1

                                totalPerPerson.value =
                                    calculateTotalPerPerson(totalBill = totalBill.value.toDouble(),
                                        splitBy = splitBy.value,
                                        tipPercent = tipPercentage)
                            }
                        })

                    }
                }
                //Tip Row
                Row(modifier = Modifier.padding(horizontal = 3.dp).padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.End) {
                    Text(text = "Tip",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically))

                    Spacer(modifier = Modifier.width(200.dp))

                    Text(text = "$${totalTipAmt.value}",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically))

                }
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(text = "$tipPercentage %")
                    Spacer(modifier = Modifier.height(14.dp))
                    Slider(value = sliderPosition,
                        onValueChange = { newVal ->
                            sliderPosition = newVal
                            totalTipAmt.value =
                                calculateTotalTip(totalBill = totalBill.value.toDouble(),
                                    tipPercent = tipPercentage)

                            totalPerPerson.value =
                                calculateTotalPerPerson(totalBill = totalBill.value.toDouble(),
                                    splitBy = splitBy.value,
                                    tipPercent = tipPercentage)
                            Log.d("Slider", "Total Bill-->: ${"%.2f".format(totalTipAmt.value)}")

                        },
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                        steps = 5,
                        onValueChangeFinished = {
                            Log.d("Finished", "BillForm: $tipPercentage")
                            //This is were the calculations should happen!
                        })

                }

            }


        }
    }//end isValid

}

@Composable
private fun TipSlider(
    modifier: Modifier = Modifier,
    sliderState: MutableState<Float>,
    totalTipState: MutableState<Double>,
    totalBillState: MutableState<String>
                     ) {
    val tipPercentage = (sliderState.value.toInt())

    val percentage = buildAnnotatedString {
        withStyle(
            style = SpanStyle(fontSize = 32.sp)
                 ) { append(tipPercentage.toString()) }
        append(" %")
    }
    //Slider
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = percentage.toString())
        Spacer(modifier = Modifier.height(14.dp))
        Slider(value = sliderState.value,
            onValueChange = {
                 sliderState.value = it
                totalTipState.value = calculateTotalTip(
                    totalBill = totalBillState.value.toDouble(),
                    tipPercent = tipPercentage
                                                       )
                Log.d("AMT", "TipSlider: ${tipPercentage}")

//                totalTipState.value = calculateTotalTip(tota
//                    tipPercent = tipPercentage).roundToInt().toString()
            },modifier = Modifier.padding(start = 16.dp, end = 16.dp),
              steps = 5,
            valueRange = (0f..100f))


    }







}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MyApp {
     TipCalculator()
  }
}