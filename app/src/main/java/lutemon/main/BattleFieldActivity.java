package lutemon.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class BattleFieldActivity extends AppCompatActivity {

    private int numberOfCheckedCheckBoxes = 0;
    private final HashMap<Integer, CheckBox> checkBoxes = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_field);

        createLutemonCheckBoxes();

    }

    //Creates checkboxes that correspond to each Lutemon in BattleField storage.
    //Also adds logic that 0...2 cb's in total can only be actived
    public void createLutemonCheckBoxes() {
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        linearLayout.removeAllViews();

        for (Map.Entry<Integer, Lutemon> set : BattleField.getInstance().getLutemons().entrySet()
        ) {
            Lutemon lutemon = set.getValue();

            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(lutemon.getName());
            checkBox.setId(lutemon.getId());
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!checkBox.isChecked() && numberOfCheckedCheckBoxes > 0) {
                        numberOfCheckedCheckBoxes--;
                    } else {
                        numberOfCheckedCheckBoxes++;
                    }
                    if (numberOfCheckedCheckBoxes == 2) {
                        for (HashMap.Entry<Integer, CheckBox> set : checkBoxes.entrySet()
                        ) {
                            if (!set.getValue().isChecked()) {
                                set.getValue().setClickable(false);
                            }
                        }
                    } else {
                        for (HashMap.Entry<Integer, CheckBox> set : checkBoxes.entrySet()
                        ) {
                            if (!set.getValue().isChecked()) {
                                set.getValue().setClickable(true);
                            }
                        }

                    }

                }

            });

            linearLayout.addView(checkBox);
            checkBoxes.put(checkBox.getId(), checkBox);

        }
    }

    //Adds the given string to the battlelog's LinearLayout
    public void addText(String string) {
        LinearLayout linearLayout = findViewById(R.id.llBattleLog);
        TextView textView = new TextView(this);
        textView.setText(string);
        textView.setTextColor(Color.BLACK);
        linearLayout.addView(textView);
    }

    //Finds parameters for method BattleField.fight() and adds battlelog text with addText()
    public void fightTillDeath(View view) {
        LinearLayout llBattleLog = findViewById(R.id.llBattleLog);
        llBattleLog.removeAllViews();

        boolean exit = true;
        Lutemon firstLutemon = null, secondLutemon = null;


        for (Map.Entry<Integer, CheckBox> set : checkBoxes.entrySet()
        ) {
            if (set.getValue().isChecked()) {
                if (firstLutemon == null)
                    firstLutemon = BattleField.getInstance().getLutemons().get(set.getValue().getId());
                else
                    secondLutemon = BattleField.getInstance().getLutemons().get(set.getValue().getId());
            }
        }

        if (firstLutemon == null || secondLutemon == null) return;


        addText(BattleField.fight(firstLutemon, secondLutemon));
        checkBoxes.clear();
        numberOfCheckedCheckBoxes = 0;
        createLutemonCheckBoxes();
    }


    //Checks for selected checkboxes and sends corresponding Lutemons to Home's storage using BattleField.sendHome()
    public void sendLutemonToHome(View view) {

        for (HashMap.Entry<Integer, CheckBox> set : checkBoxes.entrySet()
        ) {
            if (set.getValue().isChecked()) {
                BattleField.sendHome(set.getKey());
            }

        }

        checkBoxes.clear();
        numberOfCheckedCheckBoxes = 0;
        createLutemonCheckBoxes();
    }

    //Switches activity to Main menu
    public void switchToHomeActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}