package lutemon.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class TrainingAreaActivity extends AppCompatActivity {

    private HashMap<Integer, RadioButton> radioButtonHashMap = new HashMap<>();
    private HashMap<Integer, Lutemon> lutemonHashMap = new HashMap<>();

    private int timesTrained = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_field);

        generateTrainingRadioButtons();
        addLutemonsFromArrayListToHashMap();

    }

    @Override
    protected void onResume() {
        super.onResume();
        timesTrained = 0;
    }

    //Method for switching to MainActivity
    public void switchToHomeActivityFromTraining(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Generates radiobuttons for each Lutemon in TrainingArea storage
    public void generateTrainingRadioButtons() {
        RadioGroup radioGroup = findViewById(R.id.rgLutemonsInTraining);
        radioGroup.removeAllViews();
        radioButtonHashMap.clear();

        for (Lutemon lutemon : TrainingArea.getInstance().getLutemons()
        ) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(lutemon.getName());
            radioButton.setId(lutemon.getId());
            radioButton.setTextColor(Color.BLACK);
            radioGroup.addView(radioButton);

            radioButtonHashMap.put(radioButton.getId(), radioButton);
        }

    }

    //Moves Lutemon from TrainingArea storage to Home storage
    public void sendLutemonHome(View view) {
        RadioGroup radioGroup = findViewById(R.id.rgLutemonsInTraining);

        Home.getInstance().addLutemon(lutemonHashMap.get(radioGroup.getCheckedRadioButtonId()));
        TrainingArea.getInstance().getLutemons().remove(lutemonHashMap.get(radioGroup.getCheckedRadioButtonId()));
        lutemonHashMap.remove(radioGroup.getCheckedRadioButtonId());

        generateTrainingRadioButtons();

    }

    //Logic for training of Lutemon.
    // Heals Lutemon completely
    // Increases attack and defense every 5 experience.
    // Increases  MaxHealth every 15 experience.
    public void trainLutemon(View view) {
        RadioGroup radioGroup = findViewById(R.id.rgLutemonsInTraining);
        TextView tvTimesTrained = findViewById(R.id.tvTimesTrained);

        Lutemon lutemon = lutemonHashMap.get(radioGroup.getCheckedRadioButtonId());

        if (lutemon == null) return;

        healLutemon(lutemon);
        giveExperienceToLutemon(lutemon);

        if (lutemon.getExperience() % 5 == 0) {
            increaseLutemonAttack(lutemon);
            increaseLutemonDefense(lutemon);
        }

        if (lutemon.getExperience() % 15 == 0) increaseLutemonMaxHealth(lutemon);

        timesTrained++;
        tvTimesTrained.setText(timesTrained + " kokemus saatu");

    }

    public void healLutemon(Lutemon lutemon) {
        lutemon.setHealth(lutemon.getMaxHealth());
    }

    public void giveExperienceToLutemon(Lutemon lutemon) {
        lutemon.setExperience(lutemon.getExperience() + 1);
    }

    public void increaseLutemonAttack(Lutemon lutemon) {
        lutemon.setAttack((int) (lutemon.getAttack() + 2 * Math.random()));
    }

    public void increaseLutemonDefense(Lutemon lutemon) {
        lutemon.setDefense((int) (lutemon.getDefense() + 2 * Math.random()));
    }

    public void increaseLutemonMaxHealth(Lutemon lutemon) {
        lutemon.setMaxHealth((int) (lutemon.getMaxHealth() + 3 * Math.random()));
    }

    //Creates a HashMap which is identical to TrainingArea storage
    public void addLutemonsFromArrayListToHashMap() {
        for (Lutemon lutemon : TrainingArea.getInstance().getLutemons()
        ) {
            lutemonHashMap.put(lutemon.getId(), lutemon);
        }
    }

}