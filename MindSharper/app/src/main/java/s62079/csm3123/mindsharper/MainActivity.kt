@file:OptIn(ExperimentalMaterial3Api::class)

package s62079.csm3123.mindsharper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import s62079.csm3123.mindsharper.ui.theme.MindSharperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MindSharperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Home()
                }
            }
        }
    }
}

//@Composable
//fun Home(modifier: Modifier = Modifier) {
//    BoxWithConstraints {
//        val constraints = ConstraintSet {
//            val text1 = createRefFor("text1")
//            val text2 = createRefFor("text2")
//            val text3 = createRefFor("text3")
//            val rad1 = createRefFor("rad1")
//            val rad2 = createRefFor("rad2")
//            val rad3 = createRefFor("rad3")
//
//            constrain(text1) {
//                top.linkTo(parent.top, margin = 64.dp)
//                start.linkTo(parent.start, margin = 16.dp)
//            }
//            constrain(text2) {
//                top.linkTo(text1.bottom, margin = 8.dp)
//                start.linkTo(parent.start, margin = 16.dp)
//            }
//            constrain(text3) {
//                top.linkTo(text2.bottom, margin = 64.dp)
//                start.linkTo(parent.start, margin = 16.dp)
//            }
//            constrain(rad1) {
//                top.linkTo(text3.bottom, margin = 8.dp)
//                start.linkTo(parent.start)
//            }
//            constrain(rad2) {
//                top.linkTo(text3.bottom, margin = 8.dp)
//                start.linkTo(rad1.end, margin = 16.dp)
//            }
//            constrain(rad3) {
//                top.linkTo(text3.bottom, margin = 8.dp)
//                start.linkTo(rad2.end, margin = 16.dp)
//                end.linkTo(parent.end, margin = 16.dp)
//            }
//        }
//
//        ConstraintLayout(constraints) {
//            Text("TextView1", modifier = Modifier.layoutId("text1"))
//            Text("TextView2", modifier = Modifier.layoutId("text2"))
//            Text("TextView3", modifier = Modifier.layoutId("text3"))
//            RadioButton(
//                selected = false,
//                onClick = { /*TODO*/ },
//                modifier = Modifier.layoutId("rad1")
//            )
//            RadioButton(
//                selected = false,
//                onClick = { /*TODO*/ },
//                modifier = Modifier.layoutId("rad2")
//            )
//            RadioButton(
//                selected = false,
//                onClick = { /*TODO*/ },
//                modifier = Modifier.layoutId("rad3")
//            )
//        }
//    }
//}

@Composable
fun Home() {
    val context = LocalContext.current

    val fontSizeSmall = 14.sp
    val fontSizeMedium = 24.sp
    val fontSizeLarge = 34.sp

    val rOptions = listOf("i3", "i5", "i7")
    var rChoice by remember { mutableStateOf(rOptions[0]) }
    var answerValue by remember { mutableStateOf("") }
    var number1 by remember { mutableIntStateOf((0..9).random()) }
    var operator by remember { mutableStateOf(listOf("+", "-", "*", "/").random()) }
    var number2 by remember { mutableIntStateOf((0..9).random()) }
    var point = remember { mutableIntStateOf(0) }

    fun setQuestion() {
        operator = listOf("+", "-", "*", "/").random()
        when (rChoice) {
            "i3" -> {
                number1 = (0..9).random()
                do {
                    number2 = (0..9).random()
                } while (operator == "/" && number2 == 0)
            }

            "i5" -> {
                number1 = (0..99).random()
                do {
                    number2 = (0..99).random()
                } while (operator == "/" && number2 == 0)
            }

            "i7" -> {
                number1 = (0..999).random()
                do {
                    number2 = (0..999).random()
                } while (operator == "/" && number2 == 0)
            }
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White,
            ),
            title = { Text("Mind Sharpener") },
        )
    }, bottomBar = {
        BottomAppBar {
            Row(
                horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "POINT: ", fontSize = fontSizeLarge, color = Color.Blue)
                Text(text = point.value.toString(), fontSize = fontSizeLarge, color = Color.Red)
            }
        }
    }) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text("Instructions: ", fontSize = fontSizeSmall)
            Text(
                "This is a simple mathematics games which is believed can train your brain. Two numbers are given randomly based on your level choice together with the operator_ You just need to calculates the answer, write your answer and press check button. Every correct answer will give you 1 point while wrong answer will deduct 1 point.",
                fontSize = fontSizeSmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                "Choose Level:", fontSize = fontSizeSmall, modifier = Modifier.padding(top = 32.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                rOptions.forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = (option == rChoice),
                            onClick = {
                                rChoice = option
                                setQuestion()
                            },
                        )
                        Text(text = option, fontSize = fontSizeSmall)
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
            ) {
                Text(
                    number1.toString(),
                    fontSize = fontSizeMedium,
                    modifier = Modifier.padding(32.dp)
                )
                Text(operator, fontSize = fontSizeMedium, modifier = Modifier.padding(32.dp))
                Text(
                    number2.toString(),
                    fontSize = fontSizeMedium,
                    modifier = Modifier.padding(32.dp)
                )
            }
            TextField(
                label = { Text("Answer") },
                value = answerValue,
                onValueChange = {
                    if (it.isEmpty() || it.matches(Regex("^-?[0-9]*$"))) {
                        answerValue = it
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
//                    Toast.makeText(context, answerValue, Toast.LENGTH_SHORT).show()
                    val i: Int? = answerValue.toIntOrNull();
                    if (i != null) {
                        var answer: Int = 0
                        when (operator) {
                            "+" -> answer = number1 + number2
                            "-" -> answer = number1 - number2
                            "*" -> answer = number1 * number2
                            "/" -> answer = number1 / number2
                        }
                        point.intValue += if (i == answer) 1 else -1
                        setQuestion()
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text("Check")
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MindSharperTheme {
        Home()
    }
}