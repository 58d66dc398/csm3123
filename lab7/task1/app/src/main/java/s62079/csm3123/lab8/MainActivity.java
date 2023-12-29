package s62079.csm3123.lab8;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SensorManager manager;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list_view);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        list.setAdapter(new ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1, sensors));
    }
}
