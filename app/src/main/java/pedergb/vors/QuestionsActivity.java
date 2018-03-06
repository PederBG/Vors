package pedergb.vors;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.button;

public class QuestionsActivity extends AppCompatActivity {

    Button btAddQuest, btAdd, btBack;
    LinearLayout questLayout;
    RelativeLayout addWindow, fadeOut;
    EditText etQuest;

    ArrayList<Button> questList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        // Create question list
        makeQuestionList();

        //---------------------------------------------------------------
        btAddQuest = (Button) findViewById(R.id.btAddQuest);
        btAdd = (Button) findViewById(R.id.btAdd);
        btBack = (Button) findViewById(R.id.btBack);
        addWindow = (RelativeLayout) findViewById(R.id.addWindow);
        fadeOut = (RelativeLayout) findViewById(R.id.fadeOut);
        etQuest = (EditText) findViewById(R.id.etQuest);


        btAddQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addWindow.setVisibility(View.VISIBLE);
                    fadeOut.setVisibility(View.VISIBLE);
                    btAddQuest.setEnabled(false);
                    for (Button bt : questList) {
                        bt.setEnabled(false);
                    }
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.questions.add(etQuest.getText().toString());
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                etQuest.setText("");
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWindow.setVisibility(View.GONE);
                fadeOut.setVisibility(View.GONE);
                btAddQuest.setEnabled(true);
                questLayout.removeAllViews(); //TODO: ADD SINGLE QUEST INSTEAD OF ADDING ALL AGAIN
                makeQuestionList();
            }
        });
    }
    private void makeQuestionList() {
        questList = new ArrayList<>();
        questLayout = (LinearLayout) findViewById(R.id.questionList);

        for (int i = 0; i < Globals.questions.size(); i++) {
            final int FINAL_NUM = i;
            Button button = new Button(this);
                /*button.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1.0f));*/
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            lp.setMargins(10, 5, 10, 5);
            button.setLayoutParams(lp);
            button.setBackgroundColor(Color.parseColor("#cce6eded"));

            button.setText("Question " + (i + 1) + "\n" + "\n" + Globals.questions.get(i) + "\n");
            button.setPadding(0, 20, 0, 0);

            questLayout.addView(button);
            questList.add(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToastMessage(Globals.questions.get(FINAL_NUM), 500);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Intent menuIntent = new Intent(QuestionsActivity.this, MenuActivity.class);
        finish();
        QuestionsActivity.this.startActivity(menuIntent);
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
