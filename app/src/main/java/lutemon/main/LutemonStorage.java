package lutemon.main;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LutemonStorage {
    private final LinkedHashMap<Integer, Lutemon> lutemons = new LinkedHashMap<>();


    public HashMap<Integer, Lutemon> getLutemons() {
        return lutemons;
    }

    public void addLutemon(Lutemon lutemon) {
        lutemons.put(lutemon.getId(), lutemon);
    }


}
