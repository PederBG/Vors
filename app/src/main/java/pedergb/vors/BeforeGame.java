package pedergb.vors;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeforeGame extends AppCompatActivity {

    Button btAdd, btStart;
    EditText name;
    ListView listNames;
    ImageButton btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_game);

        btAdd = (Button) findViewById(R.id.btAdd);
        name = (EditText) findViewById(R.id.editText);
        listNames = (ListView) findViewById(R.id.listNames);
        btStart = (Button) findViewById(R.id.btStart);
        btBack = (ImageButton) findViewById(R.id.btBack);

        final List<String> playerNames = new ArrayList<>();


        final ArrayAdapter< String > adapter = new ArrayAdapter <>
                (BeforeGame.this, android.R.layout.simple_list_item_1,
                        playerNames);

        listNames.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerNames.add(name.getText().toString());
                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                name.setText("");
            }
        });

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerNames.size() < 2){
                    showToastMessage("Minimum of 2 players", 1000);
                }
                else {
                    Intent startIntent = new Intent(BeforeGame.this, GameActivity.class);
                    finish();
                    BeforeGame.this.startActivity(startIntent);

                    Globals.playerNames = playerNames;
                }
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
        Intent menuIntent = new Intent(BeforeGame.this, MenuActivity.class);
        finish();
        BeforeGame.this.startActivity(menuIntent);
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
