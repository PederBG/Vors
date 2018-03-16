package pedergb.vors;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    Button btStart, btPowerHour, btQuestions, btSettings;
    ImageButton btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // ------------------------------ APP SETUP ---------------------------- \\
        if (Globals.getQuestions(this).size() == 1 && Globals.activeQuest < 3){
            // Will always have one question since first will then be "NOT FOUND"
            Globals.setQuestions(this, Globals.DEFAULT_TEMPLATES[Globals.activeQuest]);
        }
        Globals.LOCAL_TEMPLATES[Globals.activeQuest] = Globals.getQuestions(this);

        // ------------------------------ --------- ---------------------------- \\


        // Button handling ----------------------------
        btStart = (Button) findViewById(R.id.button1);
        btQuestions = (Button) findViewById(R.id.button2);
        btPowerHour = (Button) findViewById(R.id.button3);
        btSettings = (Button) findViewById(R.id.button4);
        btBack = (ImageButton) findViewById(R.id.btBack);


        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MenuActivity.this, BeforeGame.class);
                finish();
                MenuActivity.this.startActivity(startIntent);
            }
        });

        btQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent powerhourIntent = new Intent(MenuActivity.this, QuestionsActivity.class);
                finish();
                MenuActivity.this.startActivity(powerhourIntent);
            }
        });

        btPowerHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questIntent = new Intent(MenuActivity.this, BeforePowerHour.class);
                finish();
                MenuActivity.this.startActivity(questIntent);
            }
        });

        btSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(MenuActivity.this, SettingsActivity.class);
                finish();
                MenuActivity.this.startActivity(settingsIntent);
            }
        });

        // ---------------------------------------------
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    // ------------------ Handler for back press button ---------------- \\
    boolean twice;
    @Override
    public void onBackPressed() {
        if (twice){
            finish();
            System.exit(0);
        }
        twice = true;
        showToastMessage("Exit?", 500);
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
