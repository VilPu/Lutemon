package lutemon.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolder extends RecyclerView.ViewHolder {

    TextView txtName, txtAttack, txtDefense, txtHealth, txtExperience;
    ImageView btnKillLutemon, btnToTrain, btnToBattle, ivLutemonPic;

    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.tvName);
        txtAttack = itemView.findViewById(R.id.tvAttack);
        txtDefense = itemView.findViewById(R.id.tvDefense);
        txtHealth = itemView.findViewById(R.id.tvHealth);
        txtExperience = itemView.findViewById(R.id.tvExperience);

        btnKillLutemon = itemView.findViewById(R.id.btnToHome);
        btnToTrain = itemView.findViewById(R.id.btnToTrain);
        btnToBattle = itemView.findViewById(R.id.btnToBattle);

        ivLutemonPic = itemView.findViewById(R.id.ivLutemonPic);


    }
}
