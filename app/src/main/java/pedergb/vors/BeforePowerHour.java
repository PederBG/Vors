package pedergb.vors;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class BeforePowerHour extends AppCompatActivity {

    CheckBox allwaysOnDisplay, cbFogHorn, cbAirHorn, cbDixieHorn, cbCowMoo;
    Button btStart, btFogHorn, btAirHorn, btDixieHorn, btCowMoo;
    List<MediaPlayer> mediaPlayers = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_power_hour);

        // Button handling ----------------------------
        allwaysOnDisplay = (CheckBox) findViewById(R.id.checkBox1);
        btStart = (Button) findViewById(R.id.button1);

        cbFogHorn = (CheckBox) findViewById(R.id.cbFogHorn);
        btFogHorn = (Button) findViewById(R.id.btFogHorn);

        cbAirHorn = (CheckBox) findViewById(R.id.cbAirHorn);
        btAirHorn = (Button) findViewById(R.id.btAirHorn);

        cbDixieHorn = (CheckBox) findViewById(R.id.cbDixieHorn);
        btDixieHorn = (Button) findViewById(R.id.btDixieHorn);

        cbCowMoo = (CheckBox) findViewById(R.id.cbCowMoo);
        btCowMoo = (Button) findViewById(R.id.btCowMoo);

        final MediaPlayer fogSound = MediaPlayer.create(BeforePowerHour.this, R.raw.foghorn);
        final MediaPlayer airSound = MediaPlayer.create(BeforePowerHour.this, R.raw.airhorn);
        final MediaPlayer dixieSound = MediaPlayer.create(BeforePowerHour.this, R.raw.dixiehorn);
        final MediaPlayer cowSound = MediaPlayer.create(BeforePowerHour.this, R.raw.cowmoo);

        mediaPlayers = Arrays.asList(fogSound, airSound, dixieSound, cowSound);


        allwaysOnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.allwaysOnDisplay = allwaysOnDisplay.isChecked();
            }
        });

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (MediaPlayer player : mediaPlayers){
                    player.stop();
                    player.release();
                    player = null;
                }
                Intent startIntent = new Intent(BeforePowerHour.this, PowerHour.class);
                finish();
                BeforePowerHour.this.startActivity(startIntent);
                //showToastMessage(Boolean.toString(Globals.allwaysOnDisplay), 100);
            }
        });


        btFogHorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fogSound.start();
            }
        });
        cbFogHorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFogHorn.isChecked()){
                    cbAirHorn.setChecked(false);
                    cbDixieHorn.setChecked(false);
                    cbCowMoo.setChecked(false);

                    Globals.shotSound = R.raw.foghorn;
                }
            }
        });

        btAirHorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                airSound.start();
            }
        });
        cbAirHorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAirHorn.isChecked()){
                    cbFogHorn.setChecked(false);
                    cbDixieHorn.setChecked(false);
                    cbCowMoo.setChecked(false);

                    Globals.shotSound = R.raw.airhorn;
                }
            }
        });

        btDixieHorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dixieSound.start();
            }
        });
        cbDixieHorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDixieHorn.isChecked()){
                    cbFogHorn.setChecked(false);
                    cbAirHorn.setChecked(false);
                    cbCowMoo.setChecked(false);

                    Globals.shotSound = R.raw.dixiehorn;
                }
            }
        });

        btCowMoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cowSound.start();
            }
        });
        cbCowMoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbCowMoo.isChecked()){
                    cbFogHorn.setChecked(false);
                    cbAirHorn.setChecked(false);
                    cbDixieHorn.setChecked(false);

                    Globals.shotSound = R.raw.cowmoo;
                }
            }
        });

        // ---------------------------------------------


    }

    // ------------------ Handler for back press button ---------------- \\
    @Override
    public void onBackPressed() {
        for (MediaPlayer player : mediaPlayers){
            player.stop();
            player.release();
            player = null;
        }

        Intent menuIntent = new Intent(BeforePowerHour.this, MenuActivity.class);
        finish();
        BeforePowerHour.this.startActivity(menuIntent);
    }
    // ...................................................................... \\

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
