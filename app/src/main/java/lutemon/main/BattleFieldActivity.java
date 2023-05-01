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

public class BattleFieldActivity extends AppCompatActivity {

    private int numberOfCheckedCheckBoxes = 0;
    private final HashMap<Integer, CheckBox> checkBoxes = new HashMap<>();
    private final HashMap<Integer, Lutemon> lutemonHashMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_field);

        createLutemonCheckBoxes();
        moveLutemonsFromArrayListToHashMap();

    }

    //creates a HashMap that is identical to storage.
    public void moveLutemonsFromArrayListToHashMap() {
        for (Lutemon lutemon : BattleField.getInstance().getLutemons()
        ) {
            lutemonHashMap.put(lutemon.getId(), lutemon);
        }
    }

    //Creates checkboxes that correspond to each Lutemon in storage.
    public void createLutemonCheckBoxes() {
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        linearLayout.removeAllViews();

        for (Lutemon lutemon : BattleField.getInstance().getLutemons()
        ) {
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

    //Gives method fightTillLutemonDies participants and calls it creating the fighting logic
    public void fight(View view) {
        Lutemon fightingLutemon1 = null;
        Lutemon fightingLutemon2 = null;

        LinearLayout linearLayout = findViewById(R.id.llBattleLog);
        linearLayout.removeAllViews();

        if (numberOfCheckedCheckBoxes != 2) return;

        for (HashMap.Entry<Integer, CheckBox> set : checkBoxes.entrySet()
        ) {
            if (set.getValue().isChecked()) {
                if (fightingLutemon1 == null) fightingLutemon1 = lutemonHashMap.get(set.getKey());
                else fightingLutemon2 = lutemonHashMap.get(set.getKey());
            }
        }

        while (fightTillLutemonDies(fightingLutemon1, fightingLutemon2));
        addText("Taistelu on päättynyt.");

        checkBoxes.clear();
        createLutemonCheckBoxes();
        numberOfCheckedCheckBoxes = 0;

    }

    //Goes through the whole fighting process including the battlelog, returns false when the other Lutemon dies
    public boolean fightTillLutemonDies(Lutemon fightingLutemon1, Lutemon fightingLutemon2) {

        addLutemonStatsToBattleLog(fightingLutemon1);
        addLutemonStatsToBattleLog(fightingLutemon2);

        addText(fightingLutemon1.getName() + " hyökkää kohti " + fightingLutemon2.getName() + "!");
        fightingLutemon1.attack(fightingLutemon2);

        if (fightingLutemon2.getHealth() <= 0) {
            addText(fightingLutemon2.getName() + " kuoli.");

            BattleField.getInstance().getLutemons().remove(lutemonHashMap.get(fightingLutemon2.getId()));
            lutemonHashMap.remove(fightingLutemon2.getId());
            return false;
        } else addText(fightingLutemon2.getName() + " huijasi kuolemaa!");

        addLutemonStatsToBattleLog(fightingLutemon1);
        addLutemonStatsToBattleLog(fightingLutemon2);

        addText(fightingLutemon2.getName() + " hyökkää kohti " + fightingLutemon1.getName());
        fightingLutemon2.attack(fightingLutemon1);

        if (fightingLutemon1.getHealth() <= 0) {
            addText(fightingLutemon1.getName() + " kuoli.");

            BattleField.getInstance().getLutemons().remove(lutemonHashMap.get(fightingLutemon1.getId()));
            lutemonHashMap.remove(fightingLutemon1.getId());
            return false;
        } else addText(fightingLutemon1.getName() + " huijasi kuolemaa!");
        return true;


    }

    //sets Lutemons stats to the battle log using addText method
    public void addLutemonStatsToBattleLog(Lutemon lutemon) {
        addText("||  " + lutemon.getName() + " (" + lutemon.getColor() + ") " + " h: " + lutemon.getAttack() + " p: " + lutemon.getDefense() + " kok: " + lutemon.getExperience() + " elämä: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth() + "  ||");
    }

    //Checks for selected checkboxes and sends corresponding Lutemons to Home's storage
    public void sendLutemonToHome(View view) {

        for (HashMap.Entry<Integer, CheckBox> set : checkBoxes.entrySet()
        ) {
            if (set.getValue().isChecked()) {

                Home.getInstance().addLutemon(lutemonHashMap.get(set.getKey()));
                BattleField.getInstance().getLutemons().remove(lutemonHashMap.get(set.getKey()));
                lutemonHashMap.remove(set.getKey());

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