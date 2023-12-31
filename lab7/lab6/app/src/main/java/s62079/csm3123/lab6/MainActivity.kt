package s62079.csm3123.lab6

import android.Manifest.permission.CALL_PHONE
import android.Manifest.permission.READ_PHONE_STATE
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import s62079.csm3123.lab6.ui.theme.Lab6Theme

class MainActivity : ComponentActivity() {
    private var context: Context = this
    private var telephony: TelephonyManager? = null

    private fun callNumber(number: String) {
        if (context != null) {
            if (ActivityCompat.checkSelfPermission(
                    this, CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                if (telephony!!.simState == TelephonyManager.SIM_STATE_READY) {
                    val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel: $number"))
                    try {
                        context.startActivity(intent)
                    } catch (s: SecurityException) {
                        Toast.makeText(context, "Error calling", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Sim card not working something something",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            } else {
                Toast.makeText(context, "No call permission bruh", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(this, arrayOf(CALL_PHONE), 1)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab6Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(::callNumber)
                    if (ActivityCompat.checkSelfPermission(
                            this, READ_PHONE_STATE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        telephony = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
                        telephony!!.registerTelephonyCallback(
                            this.mainExecutor, PhoneCallListener(this)
                        )
                    } else {
                        val text = "Bro no read phone permission"
                        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
                        ActivityCompat.requestPermissions(this, arrayOf(READ_PHONE_STATE), 1)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(onCall: (number: String) -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.app_name)) }) },
        modifier = modifier,
    ) { innerPadding ->
        var phone by remember { mutableStateOf("") }
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(text = "+6012 345 6789")
                IconButton(onClick = { -> onCall("0123456789") }) {
                    Icon(Icons.Default.Phone, contentDescription = "Localized description")
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                TextField(value = phone, onValueChange = { value -> phone = value })
                IconButton(onClick = { -> onCall(phone) }) {
                    Icon(Icons.Default.Phone, contentDescription = "Localized description")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab6Theme {
        Greeting({ _: String -> })
    }
}

class PhoneCallListener(context: Context) : TelephonyCallback(),
    TelephonyCallback.CallStateListener {
    private var context: Context

    init {
        this.context = context
    }

    override fun onCallStateChanged(state: Int) {
        var message = "Phone Status: "
        when (state) {
            TelephonyManager.CALL_STATE_RINGING -> {
                message += "Incoming Call"
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

            TelephonyManager.CALL_STATE_OFFHOOK -> {
                message += "OFF HOOK";
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

            TelephonyManager.CALL_STATE_IDLE -> {
                message += "IDLE";
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

}