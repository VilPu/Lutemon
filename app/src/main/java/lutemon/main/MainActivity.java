package lutemon.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    public LutemonAdapter lutemonAdapter = new LutemonAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.rvLutemons);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(lutemonAdapter);

    }

    //Refreshes the RecyclerView anytime MainActivity opens
    @Override
    protected void onResume() {
        super.onResume();
        lutemonAdapter.notifyDataSetChanged();
    }


    //Methods for switching activity
    public void switchToBattleFieldActivity(View view) {
        Intent intent = new Intent(this, BattleFieldActivity.class);
        startActivity(intent);
    }

    public void switchToTrainingFieldActivity(View view) {
        Intent intent = new Intent(this, TrainingAreaActivity.class);
        startActivity(intent);
    }

    public void switchToAddLutemonActivity(View view) {
        Intent intent = new Intent(this, AddLutemonActivity.class);
        startActivity(intent);
    }

}