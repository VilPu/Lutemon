package lutemon.main;

import java.util.ArrayList;

public class LutemonStorage {
    private ArrayList<Lutemon> lutemons = new ArrayList<>();


    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }
    public void addLutemon(Lutemon lutemon) {
        lutemons.add(lutemon);
    }

}
