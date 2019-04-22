package ds;

import java.util.*;

class TechGigNewYarParty {

    private final int[] input;
    private List<IndividualBestCase> individualBestCases = new ArrayList<>();

    public TechGigNewYarParty(int[] input) {
        this.input = input;
    }

    public Result findMaxSum() {
        if (input.length == 1) {
            return new Result(input[0], new ArrayList<Integer>() {
                {
                    add(0);
                }
            });
        }

        if (input.length == 2) {
            if (input[0] >= input[1]) {
                return new Result(input[0], new ArrayList<Integer>() {
                    {
                        add(0);
                    }
                });
            } else {
                return new Result(input[1], new ArrayList<Integer>() {
                    {
                        add(1);
                    }
                });
            }
        }


        for (int i = 0; i < input.length; i++) {
            individualBestCases.add(new IndividualBestCase(input[i], i));
        }


        for (int i = 2; i < input.length; i++) {
            individualBestCases.get(i).updateSelfFromTrainingElement(individualBestCases.get(i - 2), individualBestCases.get(i - 1));
        }

        IndividualBestCase max = individualBestCases
                .stream()
                .max(Comparator.comparingInt(IndividualBestCase::getMaxSum))
                .orElse(null);

        return new Result(max.getMaxSum(), max.getPathToMaxSum());
    }
}

class IndividualBestCase {
    private final int baseValue;
    private final List<Integer> pathToMaxSum;
    private int maxSum;


    IndividualBestCase(int baseValue, int index) {
        this.baseValue = baseValue;
        this.maxSum = baseValue;
        this.pathToMaxSum = new ArrayList<Integer>() {
            {
                add(index);
            }
        };
    }

    public int getBaseValue() {
        return baseValue;
    }

    public int getMaxSum() {
        return maxSum;
    }

    public List<Integer> getPathToMaxSum() {
        return pathToMaxSum;
    }

    public void updateSelfFromTrainingElement(IndividualBestCase oneBeforeAdjacentTrailingElement, IndividualBestCase adjacentTrailingElement) {
        int valueWithOneBeforeAdjacent = oneBeforeAdjacentTrailingElement.getMaxSum()+this.getBaseValue();
        if (adjacentTrailingElement.getMaxSum()>valueWithOneBeforeAdjacent)
        {
            this.maxSum = adjacentTrailingElement.getMaxSum();
            this.pathToMaxSum.remove(0);
            this.pathToMaxSum.addAll(0, adjacentTrailingElement.getPathToMaxSum());
        }else if (valueWithOneBeforeAdjacent > this.getBaseValue())
        {
            this.maxSum = valueWithOneBeforeAdjacent;
            this.pathToMaxSum.addAll(0, oneBeforeAdjacentTrailingElement.getPathToMaxSum());
        }
    }

}


class Result {
    private int max;
    private List<Integer> indices;

    public Result(int max, List<Integer> indices) {
        this.max = max;
        this.indices = indices;
    }

    public String consolidate() {
        return String.format("%d\n%s", max, indices);
    }
}
