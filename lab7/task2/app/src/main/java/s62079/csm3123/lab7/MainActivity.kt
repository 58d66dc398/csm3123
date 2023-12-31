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
import androidx.compose.foundation.layout.Column
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
    private var proximitySensor: Sensor? = null
    private var humiditySensor: Sensor? = null
    private var accelerometer: Sensor? = null
    private var gyroscope: Sensor? = null
    private var magnetometer: Sensor? = null
    private val lightValue = mutableStateOf("")
    private val proximityValue = mutableStateOf("")
    private val humidityValue = mutableStateOf("")
    private val accelerometerValue = mutableStateOf("")
    private val gyroscopeValue = mutableStateOf("")
    private val magnetometerValue = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        manager = getSystemService(SENSOR_SERVICE) as SensorManager
        lightSensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT)
        proximitySensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        humiditySensor = manager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gyroscope = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        magnetometer = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        manager.registerListener(
            this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL
        )
        manager.registerListener(
            this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL
        )
        manager.registerListener(
            this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL
        )
        manager.registerListener(
            this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL
        )
        manager.registerListener(
            this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL
        )
        manager.registerListener(
            this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL
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
        if (event != null) {
            val type = event.sensor.type
            val value1 = event.values[0].toString()
            val value2 = event.values[1].toString()
            val value3 = event.values[2].toString()
            if (type == Sensor.TYPE_LIGHT) {
                lightValue.value = value1
            } else if (type == Sensor.TYPE_PROXIMITY) {
                proximityValue.value = value1
            } else if (type == Sensor.TYPE_RELATIVE_HUMIDITY) {
                humidityValue.value = value1
            } else if (type == Sensor.TYPE_ACCELEROMETER) {
                accelerometerValue.value = "$value1, $value2, $value3"
            } else if (type == Sensor.TYPE_GYROSCOPE) {
                gyroscopeValue.value = "$value1, $value2, $value3"
            } else if (type == Sensor.TYPE_MAGNETIC_FIELD) {
                magnetometerValue.value = "$value1, $value2, $value3"
            }

            if (!listOf(
                    Sensor.TYPE_ACCELEROMETER,
                    Sensor.TYPE_GYROSCOPE,
                    Sensor.TYPE_MAGNETIC_FIELD
                ).contains(type)
            ) {
                Log.d("TAG", event.sensor.name + " " + value1)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    @Composable
    fun Greeting() {
        Column {
            Text("Light Sensor: ${lightValue.value}")
            Text("Proximity Sensor: ${proximityValue.value}")
            Text("Humidity Sensor: ${humidityValue.value}")
            Text("Accelerometer: ${accelerometerValue.value}")
            Text("Gyroscope: ${gyroscopeValue.value}")
            Text("Magnetometer: ${magnetometerValue.value}")
        }
    }
}
