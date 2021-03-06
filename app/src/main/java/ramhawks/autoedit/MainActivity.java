package ramhawks.autoedit;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton startNearVortex = (ToggleButton) findViewById(R.id.start_near_vortex_tbutton);
        ToggleButton endNearVortex = (ToggleButton) findViewById(R.id.end_near_vortex_tbutton);
        ToggleButton team = (ToggleButton) findViewById(R.id.team_tbutton);
        Button save = (Button) findViewById(R.id.save_button);

        final boolean[] settings = new boolean[3];

        startNearVortex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                settings[0] = isChecked;
            }
        });

        endNearVortex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                settings[1] = isChecked;
            }
        });

        team.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                settings[2] = isChecked;
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Settings: " + settings[0] + settings[1] + settings[2], Toast.LENGTH_LONG).show();
                // Using DIRECTORY_DOWNLOADS because it is compatible with min SDK version
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "auto_init.txt");
                try {
                    FileWriter fw = new FileWriter(file);
                    fw.write((settings[0] ? "t" : "f") + (settings[1] ? "t" : "f") + (settings[2] ? "t" : "f"));
                    fw.close();
                    Toast.makeText(getApplicationContext(), "Wrote to autonomous settings file (" +
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/auto_init.txt)", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "CRITICAL! Could not write to file!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });


    }


}
