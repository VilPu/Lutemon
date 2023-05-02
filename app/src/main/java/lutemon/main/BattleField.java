package lutemon.main;

public class BattleField extends LutemonStorage {
    private static BattleField battleField = null;

    public static LutemonStorage getInstance() {
        if (battleField == null) {
            battleField = new BattleField();
        }
        return battleField;
    }

    //Makes 2 Lutemons fight untill one of them loses its hp
    public static String fight(Lutemon firstLutemon, Lutemon secondLutemon) {
        StringBuilder battleLog = new StringBuilder();
        boolean exit = false;

        do {
            battleLog.append(getLutemonstats(firstLutemon));
            battleLog.append(getLutemonstats(secondLutemon));
            battleLog.append((firstLutemon.getName() + " hyökkää kohti " + secondLutemon.getName() + "\n"));
            firstLutemon.attack(secondLutemon);

            if (secondLutemon.getHealth() <= 0) {
                battleLog.append((secondLutemon.getName() + " kuoli.\n"));
                BattleField.getInstance().getLutemons().remove(secondLutemon.getId());
                exit = true;
                continue;
            } else battleLog.append((secondLutemon.getName() + " huijasi kuolemaa!\n"));


            battleLog.append(getLutemonstats(firstLutemon));
            battleLog.append(getLutemonstats(secondLutemon));
            battleLog.append((secondLutemon.getName() + " hyökkää kohti " + firstLutemon.getName() + "\n"));

            secondLutemon.attack(firstLutemon);

            if (firstLutemon.getHealth() <= 0) {
                battleLog.append((firstLutemon.getName() + " kuoli.\n"));
                BattleField.getInstance().getLutemons().remove(firstLutemon.getId());
                exit = true;
            } else battleLog.append((firstLutemon.getName() + " huijasi kuolemaa!\n"));


        } while (!exit);

        return battleLog.toString();
    }

    private static String getLutemonstats(Lutemon lutemon) {
        return "||  " + lutemon.getName() + " (" + lutemon.getColor() + ") " + " h: " + lutemon.getAttack() + " p: " + lutemon.getDefense() + " kok: " + lutemon.getExperience() + " elämä: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth() + "  ||\n";
    }

    public static void sendHome(int id) {
        Home.getInstance().addLutemon(BattleField.getInstance().getLutemons().get(id));
        BattleField.getInstance().getLutemons().remove(id);
    }
}
