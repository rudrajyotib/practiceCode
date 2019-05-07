package hackerRank;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class FrequencyQueries {
    private int[] frequencies = new int[1000001];
    private Map<Integer, FrequencyContainer> allInputs = new HashMap<>();
    private List<Integer> queryResults = new LinkedList<>();

    public void executeCommand(int command, int input) {
        switch (command) {
            case 1:
                if (allInputs.containsKey(input)) {
                    FrequencyContainer frequencyContainer = allInputs.get(input);
                    if (frequencyContainer.getFrequency() > 0) {
                        --frequencies[frequencyContainer.getFrequency()];
                    }
                    frequencyContainer.incrementFrequency();
                    ++frequencies[frequencyContainer.getFrequency()];
                } else {
                    allInputs.put(input, new FrequencyContainer());
                    ++frequencies[1];
                }
                break;

            case 2:
                if (allInputs.containsKey(input)) {
                    FrequencyContainer frequencyContainer = allInputs.get(input);
                    if (frequencyContainer.getFrequency() > 0) {
                        --frequencies[frequencyContainer.getFrequency()];
                        frequencyContainer.decrementFrequency();
                        ++frequencies[frequencyContainer.getFrequency()];
                    }
                }
                break;

            case 3:
                if (input <= 0 || input > frequencies.length) {
                    queryResults.add(0);
                    break;
                }
                queryResults.add(frequencies[input] > 0 ? 1 : 0);
                break;
        }
    }

    public List<Integer> collateResult() {
        return queryResults;
    }
}

@SuppressWarnings("WeakerAccess")
class FrequencyContainer {
    private int frequency = 1;

    public int getFrequency() {
        return frequency;
    }

    public void incrementFrequency() {
        ++frequency;
    }

    public void decrementFrequency() {
        if (frequency > 0) {
            --frequency;
        }
    }
}
