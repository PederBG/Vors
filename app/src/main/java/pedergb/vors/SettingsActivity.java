package pedergb.vors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {

    RadioButton btNor, btEng, btNTNU, btCust;
    ImageButton btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btNor = (RadioButton) findViewById(R.id.radioButton);
        btEng = (RadioButton) findViewById(R.id.radioButton2);
        btNTNU = (RadioButton) findViewById(R.id.radioButton3);
        btCust = (RadioButton) findViewById(R.id.radioButton4);

        RadioButton[] buttons = {btNor, btEng, btCust};
        buttons[Globals.activeQuest].setChecked(true);

        btBack = (ImageButton) findViewById(R.id.btBack);

        btNor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btNor.isChecked()) {
                    btEng.setChecked(false);
                    btNTNU.setChecked(false);
                    btCust.setChecked(false);
                }
                Globals.activeQuest = 0;
            }
        });
        btEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btEng.isChecked()) {
                    btNor.setChecked(false);
                    btNTNU.setChecked(false);
                    btCust.setChecked(false);
                }
                Globals.activeQuest = 1;
            }
        });
        btNTNU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btNTNU.isChecked()) {
                    btNor.setChecked(false);
                    btEng.setChecked(false);
                    btCust.setChecked(false);
                }
                Globals.activeQuest = 2;
            }
        });
        btCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btCust.isChecked()) {
                    btNor.setChecked(false);
                    btEng.setChecked(false);
                    btNTNU.setChecked(false);
                }
                Globals.activeQuest = 2;
            }
        });


        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    // ------------------ Handler for back press button ---------------- \\
    @Override
    public void onBackPressed() {
        Intent menuIntent = new Intent(SettingsActivity.this, MenuActivity.class);
        finish();
        SettingsActivity.this.startActivity(menuIntent);
    }
// ...................................................................... \\}
}
