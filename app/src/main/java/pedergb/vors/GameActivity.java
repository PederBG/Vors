package pedergb.vors;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    final ArrayList<Integer> colors = new ArrayList<>(Arrays.asList(Color.RED, Color.GREEN,
            Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.LTGRAY));

    View background;
    TextView txtQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        background = findViewById(R.id.gameView);
        txtQuestions = (TextView) findViewById(R.id.txtQuestion);

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nextQuestion = Globals.questions.get(
                        new Random().nextInt(Globals.questions.size()));
                txtQuestions.setText(parseQuestion(nextQuestion));
                background.setBackgroundColor(colors.get(new Random().nextInt(colors.size())));
            }
        });
    }

    // ------------------ Handler for back press button ---------------- \\
    boolean twice;
    @Override
    public void onBackPressed() {
        if (twice){
            Intent mainMenuIntent = new Intent(GameActivity.this, MenuActivity.class);
            GameActivity.this.startActivity(mainMenuIntent);
            finish();
        }
        twice = true;
        showToastMessage("Main menu?", 500);
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

    private String parseQuestion(String quest){
        List<String> localPlayerNames = new ArrayList<>(Globals.playerNames);

        ArrayList<String> questList = new ArrayList<>(Arrays.asList(quest.split(" ")));
        String result = "";
        for (int i=0; i < questList.size(); i++) {
            if (questList.get(i).contains("###")){
                String rdnName = localPlayerNames.get(new Random().nextInt(localPlayerNames.size()));
                localPlayerNames.remove(rdnName);
                questList.set(i, rdnName);
            }
            result += questList.get(i) + " ";
        }


        return result;
    }
}
