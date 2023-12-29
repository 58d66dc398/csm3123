package s62079.csm3123.lab7

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import s62079.csm3123.lab7.ui.theme.Task2Theme

class MainActivity : ComponentActivity(), SensorEventListener {
    private lateinit var manager: SensorManager
    private var lightSensor: Sensor? = null
    private val text = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        manager = getSystemService(SENSOR_SERVICE) as SensorManager
        lightSensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT)
        manager.registerListener(
            this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL
        )
        val message: String = if (lightSensor != null) "found light sensor" else "no light sensor"

        super.onCreate(savedInstanceState)

        setContent {
            Task2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
                    Greeting()
                }
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null && event.sensor.type == Sensor.TYPE_LIGHT) {
            text.value = event.values[0].toString()
            Log.d("TAG", text.value)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    @Composable
    fun Greeting() {

        Text("Sensor value: ${text.value}")
    }
}
