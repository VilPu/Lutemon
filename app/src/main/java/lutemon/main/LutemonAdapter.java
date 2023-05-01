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

public class LutemonAdapter extends RecyclerView.Adapter<LutemonViewHolder> {

    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    public LutemonAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
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
        holder.txtName.setText(lutemons.get(index).getName() + " (" + lutemons.get(index).getColor() + ")");
        holder.txtAttack.setText("Hyökkäys: " + lutemons.get(index).getAttack());
        holder.txtDefense.setText("Puolustus: " + lutemons.get(index).getDefense());
        holder.txtHealth.setText("Elämäpisteet: " + lutemons.get(index).getHealth() + "/" + lutemons.get(index).getMaxHealth());
        holder.txtExperience.setText("Kokemus: " + lutemons.get(index).getExperience());

        if (lutemons.get(index).getColor() == "Vihreä") holder.ivLutemonPic.setImageResource(R.drawable.green);
        if (lutemons.get(index).getColor() == "Musta") holder.ivLutemonPic.setImageResource(R.drawable.black);
        if (lutemons.get(index).getColor() == "Oranssi") holder.ivLutemonPic.setImageResource(R.drawable.orange);
        if (lutemons.get(index).getColor() == "Pinkki") holder.ivLutemonPic.setImageResource(R.drawable.pinky);
        if (lutemons.get(index).getColor() == "Valkoinen") holder.ivLutemonPic.setImageResource(R.drawable.whitey);

        //Removes Lutemon
        holder.btnKillLutemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = holder.getAdapterPosition();
                Home.getInstance().getLutemons().remove(index);
                notifyItemRemoved(index);
            }
        });

        //Moves Lutemon to TrainingArea storage.
        holder.btnToTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = holder.getAdapterPosition();
                TrainingArea.getInstance().addLutemon(Home.getInstance().getLutemons().get(index));
                Home.getInstance().getLutemons().remove(index);
                notifyItemRemoved(index);
            }
        });

        //Moves Lutemon to BattleField storage.
        holder.btnToBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = holder.getAdapterPosition();
                BattleField.getInstance().addLutemon(Home.getInstance().getLutemons().get(index));
                Home.getInstance().getLutemons().remove(index);
                notifyItemRemoved(index);
            }

        });

    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
