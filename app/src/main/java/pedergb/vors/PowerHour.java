package pedergb.vors;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PowerHour extends AppCompatActivity {

    TextView txtCountDown;
    View screen;
    MediaPlayer shot;
    CountDownTimer countDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_hour);
        if (Globals.allwaysOnDisplay) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        shot = MediaPlayer.create(PowerHour.this, Globals.shotSound);

        txtCountDown = (TextView) findViewById(R.id.txtCountDown);
        screen = findViewById(R.id.main_screen);


        countDown = new CountDownTimer(60*60*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                long m = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                long s = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished - m*60*1000);
                long ms = millisUntilFinished - m*60*1000 - s*1000;
                txtCountDown.setText(""+String.format("%02d:%02d",m,s));

                    Random rnd = new Random();
                    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    screen.setBackgroundColor(color);

                if (s == 0){
                    shot.start();
                }
            }

            public void onFinish() {
                txtCountDown.setText("Drunk yet?");
            }

        }.start();
    }

    // ------------------ Handler for back press button ---------------- \\
    boolean twice;
    @Override
    public void onBackPressed() {
        if (twice){
            shot.stop();
            shot.release();
            shot = null;
            countDown.cancel();
            countDown = null;
            Intent mainMenuIntent = new Intent(PowerHour.this, MenuActivity.class);
            PowerHour.this.startActivity(mainMenuIntent);
            finish();
        }
        twice = true;
        showToastMessage("Go to main menu?", 500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        }, 1500);
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
