package ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class TechGigNewYarParty {

    private final int[] input;
    private List<IndividualBestCase> individualBestCases = new ArrayList<>();

    public TechGigNewYarParty(int[] input) {
        this.input = input;
    }

    public Result findMaxSum() {
        if (input.length == 1) {
            return new Result( new ArrayList<Integer>() {
                {
                    add(input[0]);
                }
            });
        }

        if (input.length == 2) {
            if (input[0] >= input[1]) {
                return new Result( new ArrayList<Integer>() {
                    {
                        add(input[0]);
                    }
                });
            } else {
                return new Result( new ArrayList<Integer>() {
                    {
                        add(input[1]);
                    }
                });
            }
        }


        for (int i = 0; i < input.length; i++) {
            individualBestCases.add(new IndividualBestCase(input[i]));
        }


        for (int i = 2; i < input.length; i++) {
            individualBestCases.get(i).updateSelfFromTrainingElement(individualBestCases.get(i - 2), individualBestCases.get(i - 1));
        }

        IndividualBestCase max = individualBestCases
                .stream()
                .max(Comparator.comparingInt(IndividualBestCase::getMaxSum))
                .orElse(null);

        return new Result( max.getTrail());
    }
}

class IndividualBestCase {
    private final int baseValue;
    private final List<Integer> trail;
    private int maxSum;


    IndividualBestCase(int baseValue) {
        this.baseValue = baseValue;
        this.maxSum = baseValue;
        this.trail = new ArrayList<Integer>() {
            {
                add(baseValue);
            }
        };
    }

    public int getBaseValue() {
        return baseValue;
    }

    public int getMaxSum() {
        return maxSum;
    }

    public List<Integer> getTrail() {
        return trail;
    }

    public void updateSelfFromTrainingElement(IndividualBestCase oneBeforeAdjacentTrailingElement, IndividualBestCase adjacentTrailingElement) {
        int valueWithOneBeforeAdjacent = oneBeforeAdjacentTrailingElement.getMaxSum()+this.getBaseValue();
        if (adjacentTrailingElement.getMaxSum()>valueWithOneBeforeAdjacent)
        {
            this.maxSum = adjacentTrailingElement.getMaxSum();
            this.trail.remove(0);
            this.trail.addAll(0, adjacentTrailingElement.getTrail());
        }else if (valueWithOneBeforeAdjacent > this.getBaseValue())
        {
            this.maxSum = valueWithOneBeforeAdjacent;
            this.trail.addAll(0, oneBeforeAdjacentTrailingElement.getTrail());
        }
    }

}


class Result {
    private List<Integer> trail;

    public Result( List<Integer> trail) {
        this.trail = trail;
    }

    public String consolidateReverseWay() {
        StringBuffer resultBuffer = new StringBuffer();
        Collections.reverse(trail);
        trail.forEach(ticketNumber -> resultBuffer.append(ticketNumber));
        return resultBuffer.toString();
    }
}
