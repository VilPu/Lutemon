package lutemon.main;

import java.util.Set;

public class Home extends LutemonStorage {

    private static Home home = null;

    public static Home getInstance() {
        if (home == null) {
            home = new Home();
        }
        return home;
    }

    public Lutemon getLutemonByIndex(int index) {
        Set<Integer> set = getInstance().getLutemons().keySet();
        return getInstance().getLutemons().get(set.toArray()[index]);
    }

    public void removeLutemonByIndex(int index) {
        getInstance().getLutemons().remove(getLutemonByIndex(index).getId());
    }

}
