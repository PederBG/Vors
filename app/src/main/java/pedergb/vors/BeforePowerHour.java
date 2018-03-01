package pedergb.vors;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class BeforePowerHour extends AppCompatActivity {

    CheckBox allwaysOnDisplay;
    Button btStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_power_hour);

        // Button handling ----------------------------
        allwaysOnDisplay = (CheckBox) findViewById(R.id.checkBox1);
        btStart = (Button) findViewById(R.id.button1);

        allwaysOnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.allwaysOnDisplay = allwaysOnDisplay.isChecked();
            }
        });

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(BeforePowerHour.this, PowerHour.class);
                BeforePowerHour.this.startActivity(startIntent);
                //showToastMessage(Boolean.toString(Globals.allwaysOnDisplay), 100);
            }
        });

        // ---------------------------------------------


    }

    // ---------------- Toast message --------------------- \\
    public void showToastMessage(String text, int duration){
        final Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, duration);
    }
}
