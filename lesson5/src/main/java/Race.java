import java.util.ArrayList;
import java.util.Arrays;

class Race {
    private ArrayList<Stage> stages;

    Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    ArrayList<Stage> getStages() {
        return stages;
    }

    int getStageCount() {
        return stages.size();
    }
}
