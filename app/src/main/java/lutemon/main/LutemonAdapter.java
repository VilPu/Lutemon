package lutemon.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LutemonAdapter extends RecyclerView.Adapter<LutemonViewHolder> {

    private final Context context;


    public LutemonAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view, parent, false));
    }

    //Sets View's data that is displayed in RecylerView.
    // Gives the buttons functionality to move the corresponding Lutemon of the View to a different storage.
    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        int index = holder.getAdapterPosition();
        Lutemon lutemon = Home.getInstance().getLutemonByIndex(index);

        holder.txtName.setText((lutemon.getName() + " (" + lutemon.getColor() + ")"));
        holder.txtAttack.setText("Hyökkäys: " + lutemon.getAttack());
        holder.txtDefense.setText("Puolustus: " + lutemon.getDefense());
        holder.txtHealth.setText("Elämäpisteet: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth());
        holder.txtExperience.setText("Kokemus: " + lutemon.getExperience());

        if (lutemon.getColor().equals("Vihreä"))
            holder.ivLutemonPic.setImageResource(R.drawable.green);
        if (lutemon.getColor().equals("Musta"))
            holder.ivLutemonPic.setImageResource(R.drawable.black);
        if (lutemon.getColor().equals("Oranssi"))
            holder.ivLutemonPic.setImageResource(R.drawable.orange);
        if (lutemon.getColor().equals("Pinkki"))
            holder.ivLutemonPic.setImageResource(R.drawable.pinky);
        if (lutemon.getColor().equals("Valkoinen"))
            holder.ivLutemonPic.setImageResource(R.drawable.whitey);

        //Removes Lutemon
        holder.btnKillLutemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = holder.getAdapterPosition();
                Home.getInstance().removeLutemonByIndex(index);
                notifyItemRemoved(index);
            }
        });

        //Moves Lutemon to TrainingArea storage.
        holder.btnToTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = holder.getAdapterPosition();
                TrainingArea.getInstance().addLutemon(Home.getInstance().getLutemonByIndex(index));
                Home.getInstance().removeLutemonByIndex(index);
                notifyItemRemoved(index);

            }
        });

        //Moves Lutemon to BattleField storage.
        holder.btnToBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = holder.getAdapterPosition();
                BattleField.getInstance().addLutemon(Home.getInstance().getLutemonByIndex(index));
                Home.getInstance().removeLutemonByIndex(index);
                notifyItemRemoved(index);
            }

        });

    }

    @Override
    public int getItemCount() {
        return Home.getInstance().getLutemons().size();
    }
}
