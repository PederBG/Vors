package pedergb.vors;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    Button btStart, btPowerHour, btDrunkest, btQuestions, btExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // ------------------------------ APP SETUP ---------------------------- \\
        if (Globals.getQuestions(this).size() == 0){
            Globals.setQuestions(this, Globals.defaultQuestions);
        }
        Globals.localQuestions = Globals.getQuestions(this);
        // ------------------------------ --------- ---------------------------- \\


        // Button handling ----------------------------
        btStart = (Button) findViewById(R.id.button1);
        btPowerHour = (Button) findViewById(R.id.button2);
        btDrunkest = (Button) findViewById(R.id.button3);
        btQuestions = (Button) findViewById(R.id.button4);
        btExit = (Button) findViewById(R.id.button5);

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MenuActivity.this, BeforeGame.class);
                finish();
                MenuActivity.this.startActivity(startIntent);
            }
        });

        btPowerHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MenuActivity.this, BeforePowerHour.class);
                finish();
                MenuActivity.this.startActivity(startIntent);
            }
        });

        btQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MenuActivity.this, QuestionsActivity.class);
                finish();
                MenuActivity.this.startActivity(startIntent);
            }
        });

        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        // ---------------------------------------------
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
