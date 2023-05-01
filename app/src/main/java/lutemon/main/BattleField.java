package lutemon.main;

public class BattleField extends LutemonStorage{
    private static BattleField battleField = null;

    public static LutemonStorage getInstance() {
        if(battleField == null) {
            battleField = new BattleField();
        }
        return battleField;
    }
}
