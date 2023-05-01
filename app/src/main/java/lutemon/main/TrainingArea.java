package lutemon.main;

public class TrainingArea extends LutemonStorage{
    private static TrainingArea trainingArea = null;

    public static TrainingArea getInstance() {
        if(trainingArea == null) {
            trainingArea = new TrainingArea();
        }
        return trainingArea;
    }
}
