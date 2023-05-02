package lutemon.main;

import java.util.Set;

public class TrainingArea extends LutemonStorage {
    private static TrainingArea trainingArea = null;

    public static TrainingArea getInstance() {
        if (trainingArea == null) {
            trainingArea = new TrainingArea();
        }
        return trainingArea;
    }

    //Sends Lutemon with parameter id to Home storage
    public static void sendHome(int id) {
        Home.getInstance().addLutemon(TrainingArea.getInstance().getLutemons().get(id));
        TrainingArea.getInstance().getLutemons().remove(id);
    }

    //Heals Lutemon and increases combat stats via logic
    public static void trainLutemon(Lutemon lutemon) {
        if (lutemon == null) return;

        healLutemon(lutemon);
        giveExperienceToLutemon(lutemon);

        if (lutemon.getExperience() % 5 == 0) {
            increaseLutemonAttack(lutemon);
            increaseLutemonDefense(lutemon);
        }

        if (lutemon.getExperience() % 15 == 0) increaseLutemonMaxHealth(lutemon);
    }

    //Methods to increase Lutemon stats
    private static void healLutemon(Lutemon lutemon) {
        lutemon.setHealth(lutemon.getMaxHealth());
    }

    private static void giveExperienceToLutemon(Lutemon lutemon) {
        lutemon.setExperience(lutemon.getExperience() + 1);
    }

    private static void increaseLutemonAttack(Lutemon lutemon) {
        lutemon.setAttack((int) (lutemon.getAttack() + 2 * Math.random()));
    }

    private static void increaseLutemonDefense(Lutemon lutemon) {
        lutemon.setDefense((int) (lutemon.getDefense() + 2 * Math.random()));
    }

    private static void increaseLutemonMaxHealth(Lutemon lutemon) {
        lutemon.setMaxHealth((int) (lutemon.getMaxHealth() + 3 * Math.random()));
        lutemon.setHealth(lutemon.getMaxHealth());
    }

}

