package s62079.csm3123.lab7

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import s62079.csm3123.lab7.ui.theme.Lab7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab7Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var manager: SensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
                    var sensors: List<Sensor> = manager.getSensorList(Sensor.TYPE_ALL)
                    Greeting(sensors)
                }
            }
        }
    }
}

@Composable
fun Greeting(sensors: List<Sensor>, modifier: Modifier = Modifier) {
    LazyColumn(Modifier.padding()) {
        items(sensors.size) { i ->
            Text(text = sensors[i].toString(), Modifier.padding(16.dp))
        }
    }
}
