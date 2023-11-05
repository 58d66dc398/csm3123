package s62079.csm3123.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import s62079.csm3123.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage(
                        stringResource(R.string.happy_birthday_sam),
                        stringResource(R.string.from_rem)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 128.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
            Text(
                "Full Name",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                modifier = Modifier.padding(0.dp, 4.dp)
            )
            Text(
                "Title",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
        Column(modifier = Modifier.padding(all = 16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
                Text("+00 (00) 000 000")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
                Text("@socialmediahandle")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
                Text("email@domail.com")
            }
        }
    }
}

@Composable
fun ComposeQuadrant() {
    Column(Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.weight(1f)) {
            Quadrant(
                title = "Text composable",
                description = "Displays text and follows the recommended Material Design guidelines.",
                modifier = Modifier
                    .weight(1f)
                    .background(color = Color(0xFFEADDFF))
            )
            Quadrant(
                title = "Image composable",
                description = "Creates a composable that lays out and draws a given Painter class object.",
                modifier = Modifier
                    .weight(1f)
                    .background(color = Color(0xFFD0BCFF))
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            Quadrant(
                title = "Row composable",
                description = "A layout composable that places its children in a horizontal sequence.",
                modifier = Modifier
                    .weight(1f)
                    .background(color = Color(0xFFB69DF8))
            )
            Quadrant(
                title = "Column composable",
                description = "A layout composable that places its children in a vertical sequence.",
                modifier = Modifier
                    .weight(1f)
                    .background(color = Color(0xFFF6EDFF))
            )
        }
    }
}

@Composable
fun Quadrant(title: String, description: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Text(
            title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
        )
        Text(description, textAlign = TextAlign.Justify)
    }
}

@Composable
fun TaskManger() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_task_completed),
            contentDescription = null
        )
        Text(
            "All tasks completed",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 24.dp, 0.dp, 8.dp)
        )
        Text("Nice work!", fontSize = 16.sp)
    }
}

@Composable
fun ComposeArticle() {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_compose_background),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            "Jetpack Compose tutorial", fontSize = 24.sp,
            modifier = Modifier.padding(all = 16.dp)
        )
        Text(
            "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.",
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(all = 16.dp)
        )
    }
}

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = message,
            fontSize = 96.sp,
            lineHeight = 112.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
//                .align(alignment = Alignment.End)
                .align(alignment = Alignment.CenterHorizontally)
                .background(color = Color.Green)
        )
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.androidparty)
    Box {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        GreetingImage(
            stringResource(R.string.happy_birthday_sam),
            stringResource(R.string.from_rem)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeArticlePreview() {
    HappyBirthdayTheme {
        ComposeArticle()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TaskManagerPreview() {
    HappyBirthdayTheme {
        TaskManger()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComposeQuadrantPreview() {
    HappyBirthdayTheme {
        ComposeQuadrant()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComposeBusinessCard() {
    HappyBirthdayTheme {
        BusinessCard()
    }
}
