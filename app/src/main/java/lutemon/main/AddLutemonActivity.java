package lutemon.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.google.android.material.snackbar.Snackbar;

public class AddLutemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lutemon);

    }

    //Adds a new Lutemon to Home's storage.
    //Does not create one if no name is given or no radiobutton is checked.
    public void addNewLutemon(View view) {

        RadioGroup rgColors = findViewById(R.id.rgColors);
        EditText etLutemonName = findViewById(R.id.etLutemonName);
        int IDofSelected = rgColors.getCheckedRadioButtonId();


        if (etLutemonName.getText().toString().isEmpty()) {
            return;
        }

        switch (IDofSelected) {
            case R.id.rbGreen:
                Home.getInstance().addLutemon(new Lutemon(etLutemonName.getText().toString(), "Vihreä", 6, 3, 19));
                break;

            case R.id.rbBlack:
                Home.getInstance().addLutemon(new Lutemon(etLutemonName.getText().toString(), "Musta", 9, 0, 16));
                break;

            case R.id.rbPink:
                Home.getInstance().addLutemon(new Lutemon(etLutemonName.getText().toString(), "Pinkki", 7, 2, 18));
                break;

            case R.id.rbOrange:
                Home.getInstance().addLutemon(new Lutemon(etLutemonName.getText().toString(), "Oranssi", 8, 1, 17));
                break;

            case R.id.rbWhite:
                Home.getInstance().addLutemon(new Lutemon(etLutemonName.getText().toString(), "Valkoinen", 5, 4, 20));
                break;

            default:
                return;
        }

        lutemonCreatedSnackbar("Lutemon luotiin!");

    }


    //Creates a snackbar to top of the screen displaying parameter "message"
    public void lutemonCreatedSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(getWindow().getCurrentFocus(), message, Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        snackbar.show();
    }

    public void switchToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}