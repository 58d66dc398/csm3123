package com.csm3123.lab8

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csm3123.lab8.ui.theme.Lab8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        setContent {
            Lab8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home(sharedPreferences)
                }
            }
        }
    }
}

fun hasData(sharedPreferences: SharedPreferences): Int {
    return if (sharedPreferences.contains("username")) 1 else 0
}

@Composable
fun Home(sharedPreferences: SharedPreferences, modifier: Modifier = Modifier) {
    var status by remember { mutableIntStateOf(hasData(sharedPreferences)) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (status == 0) {
            Text("Welcome", fontSize = 24.sp)
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") })
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Button(onClick = {
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.putString("password", password)
                editor.apply()
                status++
            }) {
                Text("Login")
            }
        } else {
            Text("Success", fontSize = 24.sp)
            Button(onClick = {
                sharedPreferences.edit().clear().apply()
                status--
            }) {
                Text("Log Out")
            }
            Text("DEBUG")
            Text("Username: ${sharedPreferences.getString("username", "")}")
            Text("Password: ${sharedPreferences.getString("password", "")}")
        }
    }
}