package lutemon.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    public LutemonAdapter lutemonAdapter = new LutemonAdapter(this, Home.getInstance().getLutemons());

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

    public void testAddLutemons(View view) {
        Home.getInstance().addLutemon(new Lutemon("testi", "vihreä", 1, 2, 10));
        Home.getInstance().addLutemon(new Lutemon("testi2", "vihreä", 1, 2, 10));
        Home.getInstance().addLutemon(new Lutemon("testi3", "vihreä", 1, 2, 10));
        Home.getInstance().addLutemon(new Lutemon("testi4", "vihreä", 1, 2, 10));
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