package sn.sensaloumdev.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sn.sensaloumdev.calculator.ui.theme.CalculatorTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bc),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column {
            Image(
                painter = painterResource(id = R.drawable.uvs),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            CalculatorContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorContent(modifier: Modifier = Modifier) {
    var numA by remember { mutableStateOf("") }
    var numB by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to MicdaCalculator",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = numA,
            onValueChange = { numA = it },
            label = { Text("Number A", color = Color.White) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                cursorColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = numB,
            onValueChange = { numB = it },
            label = { Text("Number B", color = Color.White) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                cursorColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {
                result = performCalculation(numA, numB, Operation.ADD)
            }) {
                Text("+")
            }
            Button(onClick = {
                result = performCalculation(numA, numB, Operation.SUBTRACT)
            }) {
                Text("-")
            }
            Button(onClick = {
                result = performCalculation(numA, numB, Operation.MULTIPLY)
            }) {
                Text("*")
            }
            Button(onClick = {
                result = performCalculation(numA, numB, Operation.DIVIDE)
            }) {
                Text("/")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            numA = ""
            numB = ""
            result = ""
        }) {
            Text("Clear")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Result: $result",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

enum class Operation {
    ADD, SUBTRACT, MULTIPLY, DIVIDE
}

fun performCalculation(numA: String, numB: String, operation: Operation): String {
    val a = numA.toDoubleOrNull()
    val b = numB.toDoubleOrNull()

    return if (a != null && b != null) {
        val result = when (operation) {
            Operation.ADD -> a + b
            Operation.SUBTRACT -> a - b
            Operation.MULTIPLY -> a * b
            Operation.DIVIDE -> a / b
        }
        result.toString()
    } else {
        "Invalid input"
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorAppPreview() {
    CalculatorTheme {
        CalculatorApp()
    }
}