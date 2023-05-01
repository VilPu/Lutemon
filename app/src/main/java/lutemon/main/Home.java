package lutemon.main;

public class Home extends LutemonStorage {

    private static Home home = null;

    public static Home getInstance() {
        if(home == null) {
            home = new Home();
        }
        return home;
    }

}
