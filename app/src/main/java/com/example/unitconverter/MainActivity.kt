package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.math.exp
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Display()
                }
            }
        }
    }
}

@Composable
fun Display() {

    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Metre") }
    var outputUnit by remember { mutableStateOf("Centimetre") }
    var inExpanded by remember { mutableStateOf(false) }
    var outExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(0.01) }
    val outConversionFactor = remember { mutableStateOf(0.01) }

    val customText = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 30.sp,
        color = Color.Red,
        fontWeight = FontWeight.ExtraBold
    )


    fun Conversion(){
        val inputValueDouble=inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100 / outConversionFactor.value).roundToInt() / 100.0
        outputValue=result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text("Unit Converter", style = customText)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {inputValue=it}, label = {Text("Enter a value")}, modifier = Modifier.padding(30.dp))
        Spacer(modifier = Modifier.height(16.dp))
        val context= LocalContext.current
        Row {
            Box {
                Button(onClick = {inExpanded=true}) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "DropDown Menu")
                }
                DropdownMenu(expanded = inExpanded, onDismissRequest = { inExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text("Metre") },
                        onClick = {
                            inExpanded=false
                            inputUnit="Metre"
                            conversionFactor.value=1.0
                            Conversion()
                        })
                    DropdownMenuItem(
                        text = { Text("Centimetre") },
                        onClick = {
                            inExpanded=false
                            inputUnit="Centimetre"
                            conversionFactor.value=0.01
                            Conversion()
                        })
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            inExpanded=false
                            inputUnit="Feet"
                            conversionFactor.value=0.3048
                            Conversion()
                        })
                    DropdownMenuItem(
                        text = { Text("Inch") },
                        onClick = {
                            inExpanded=false
                            inputUnit="Inch"
                            conversionFactor.value=0.025
                            Conversion()
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = {outExpanded=true}) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "DropDown Menu")
                }
                DropdownMenu(expanded = outExpanded, onDismissRequest = { outExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text("Metre") },
                        onClick = {
                            outExpanded=false
                            outputUnit="Metre"
                            outConversionFactor.value=1.0
                            Conversion()
                        })
                    DropdownMenuItem(
                        text = { Text("Centimetre") },
                        onClick = {
                            outExpanded=false
                            outputUnit="Centimetre"
                            outConversionFactor.value=0.01
                            Conversion()
                        })
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            outExpanded=false
                            outputUnit="Feet"
                            outConversionFactor.value=0.3048
                            Conversion()
                        })
                    DropdownMenuItem(
                        text = { Text("Inch") },
                        onClick = {
                            outExpanded=false
                            outputUnit="Inch"
                            outConversionFactor.value=0.025
                            Conversion()
                        })
                }

            }
        }
            Spacer(modifier = Modifier.height(16.dp))
            Row{
                Text(text= "Entered Value: $inputValue $inputUnit",
                    style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.ExtraBold)

                Spacer(modifier = Modifier.width(16.dp))

                Text(text = "Result: $outputValue $outputUnit",
                    style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.ExtraBold)
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DisplayPreview() {
        Display()
    }
