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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.button;

public class QuestionsActivity extends AppCompatActivity {

    Button btAddQuest, btAdd, btSave, btDelete;
    LinearLayout questLayout;
    RelativeLayout addWindow, editWindow, fadeOut, infoWindow;
    EditText etQuest, etEditQuest;
    ImageButton btBack, btClose, btClose2, btInfo, btClose3;

    boolean infoWindowUp;

    ArrayList<Button> questList;

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        // Create question list
        makeQuestionList();

        //---------------------------------------------------------------
        btAddQuest = (Button) findViewById(R.id.btAddQuest);
        btAdd = (Button) findViewById(R.id.btAdd);
        btClose = (ImageButton) findViewById(R.id.btClose);
        btSave = (Button) findViewById(R.id.btSave);
        btDelete = (Button) findViewById(R.id.btDelete);
        btClose2 = (ImageButton) findViewById(R.id.btClose2);
        btBack = (ImageButton) findViewById(R.id.btBack);
        addWindow = (RelativeLayout) findViewById(R.id.addWindow);
        editWindow = (RelativeLayout) findViewById(R.id.editWindow);
        fadeOut = (RelativeLayout) findViewById(R.id.fadeOut);
        etQuest = (EditText) findViewById(R.id.etQuest);
        etEditQuest = (EditText) findViewById(R.id.etEditQuest);
        btInfo = (ImageButton) findViewById(R.id.btInfo);
        infoWindow = (RelativeLayout) findViewById(R.id.infoWindow);
        btClose3 = (ImageButton) findViewById(R.id.btClose3);

        ctx = this;
        infoWindowUp = false;

        // ------------------ ADD WINDOW POPUP LOGIC ------------------
        btAddQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addWindow.setVisibility(View.VISIBLE);
                    fadeOut.setVisibility(View.VISIBLE);
                    btBack.setEnabled(false);
                    btAddQuest.setEnabled(false);
                    btInfo.setEnabled(false);
                    for (Button bt : questList) {
                        bt.setEnabled(false);
                    }
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.LOCAL_TEMPLATES[Globals.activeQuest].add(etQuest.getText().toString());
                Globals.setQuestions(ctx, Globals.LOCAL_TEMPLATES[Globals.activeQuest]);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                etQuest.setText("");
            }
        });
        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWindow.setVisibility(View.GONE);
                fadeOut.setVisibility(View.GONE);
                btBack.setEnabled(true);
                btAddQuest.setEnabled(true);
                btInfo.setEnabled(true);
                questLayout.removeAllViews(); //TODO: ADD SINGLE QUEST INSTEAD OF ADDING ALL AGAIN
                makeQuestionList();
            }
        });
        // ------------------ -------------------- ------------------

        // ------------------ EDIT WINDOW POPUP LOGIC ------------------
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.LOCAL_TEMPLATES[Globals.activeQuest].set(Globals.QUEST_CLICKED, etEditQuest.getText().toString());
                Globals.setQuestions(ctx, Globals.LOCAL_TEMPLATES[Globals.activeQuest]);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                editWindow.setVisibility(View.GONE);
                fadeOut.setVisibility(View.GONE);
                btBack.setEnabled(true);
                btAddQuest.setEnabled(true);
                btInfo.setEnabled(true);
                questLayout.removeAllViews(); //TODO: ADD SINGLE QUEST INSTEAD OF ADDING ALL AGAIN
                makeQuestionList();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.LOCAL_TEMPLATES[Globals.activeQuest].remove(Globals.QUEST_CLICKED);
                Globals.setQuestions(ctx, Globals.LOCAL_TEMPLATES[Globals.activeQuest]);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                editWindow.setVisibility(View.GONE);
                fadeOut.setVisibility(View.GONE);
                btBack.setEnabled(true);
                btAddQuest.setEnabled(true);
                btInfo.setEnabled(true);
                questLayout.removeAllViews(); //TODO: ADD SINGLE QUEST INSTEAD OF ADDING ALL AGAIN
                makeQuestionList();
            }
        });

        btClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editWindow.setVisibility(View.GONE);
                fadeOut.setVisibility(View.GONE);
                btBack.setEnabled(true);
                btAddQuest.setEnabled(true);
                btInfo.setEnabled(true);
                for (Button bt : questList) {
                    bt.setEnabled(true);
                }
            }
        });
        // ------------------ -------------------- ------------------
        btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (infoWindowUp){
                    infoWindow.setVisibility(View.GONE);
                    fadeOut.setVisibility(View.GONE);
                    btBack.setEnabled(true);
                    btAddQuest.setEnabled(true);
                    for (Button bt : questList) {
                        bt.setEnabled(true);
                    }
                    infoWindowUp = false;
                }
                else{
                    infoWindow.setVisibility(View.VISIBLE);
                    fadeOut.setVisibility(View.VISIBLE);
                    btBack.setEnabled(false);
                    btAddQuest.setEnabled(false);
                    for (Button bt : questList) {
                        bt.setEnabled(false);
                    }
                    infoWindowUp = true;
                }

            }
        });
        btClose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoWindow.setVisibility(View.GONE);
                fadeOut.setVisibility(View.GONE);
                btBack.setEnabled(true);
                btAddQuest.setEnabled(true);
                btInfo.setEnabled(true);
                for (Button bt : questList) {
                    bt.setEnabled(true);
                }
                infoWindowUp = false;
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void makeQuestionList() {
        questList = new ArrayList<>();
        questLayout = (LinearLayout) findViewById(R.id.questionList);


        for (int i = 0; i < Globals.LOCAL_TEMPLATES[Globals.activeQuest].size(); i++) {
            final int FINAL_NUM = i;
            Button button = new Button(this);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
            );
            lp.setMargins(10, 5, 10, 5);
            button.setLayoutParams(lp);
            button.setBackgroundColor(Color.parseColor("#cce6eded"));

            button.setText("Question " + (i + 1) + "\n" + "\n" + Globals.LOCAL_TEMPLATES[Globals.activeQuest].get(i) + "\n");
            button.setPadding(0, 20, 0, 0);

            questLayout.addView(button);
            questList.add(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //showToastMessage(Globals.questions.get(FINAL_NUM), 500);
                    Globals.QUEST_CLICKED = FINAL_NUM;
                    editWindow.setVisibility(View.VISIBLE);
                    fadeOut.setVisibility(View.VISIBLE);
                    btBack.setEnabled(false);
                    btAddQuest.setEnabled(false);
                    btInfo.setEnabled(false);
                    for (Button bt : questList) {
                        bt.setEnabled(false);
                    }
                    etEditQuest.setText(((ArrayList<String>) Globals.LOCAL_TEMPLATES[Globals.activeQuest]).get(FINAL_NUM));

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
