package lutemon.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Map;

public class TrainingAreaActivity extends AppCompatActivity {
    private int timesTrained = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_field);

        generateTrainingRadioButtons();

    }

    //Resets timesTrained everytime TrainingAreaActivity opens
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

    //Generates RadioButton(s) for each Lutemon in TrainingArea storage
    public void generateTrainingRadioButtons() {
        RadioGroup radioGroup = findViewById(R.id.rgLutemonsInTraining);
        radioGroup.removeAllViews();

        for (Map.Entry<Integer, Lutemon> set : TrainingArea.getInstance().getLutemons().entrySet()
        ) {
            Lutemon lutemon = set.getValue();

            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(lutemon.getName());
            radioButton.setId(lutemon.getId());
            radioButton.setTextColor(Color.BLACK);
            radioGroup.addView(radioButton);

        }

    }

    //Moves Lutemon from TrainingArea storage to Home storage
    public void sendLutemonHome(View view) {
        RadioGroup radioGroup = findViewById(R.id.rgLutemonsInTraining);

        if (radioGroup.getCheckedRadioButtonId() == -1) return;

        TrainingArea.sendHome(radioGroup.getCheckedRadioButtonId());

        generateTrainingRadioButtons();

    }

    //Trains Lutemon via method TrainingArea.trainLutemon()
    public void trainLutemon(View view) {
        TextView tvTimesTrained = findViewById(R.id.tvTimesTrained);
        RadioGroup rgLutemonsInTraining = findViewById(R.id.rgLutemonsInTraining);

        if (rgLutemonsInTraining.getCheckedRadioButtonId() == -1) return;

        Lutemon lutemon = TrainingArea.getInstance().getLutemons().get(rgLutemonsInTraining.getCheckedRadioButtonId());

        TrainingArea.trainLutemon(lutemon);

        timesTrained++;
        tvTimesTrained.setText((timesTrained + " kokemusta saatu"));

    }


}