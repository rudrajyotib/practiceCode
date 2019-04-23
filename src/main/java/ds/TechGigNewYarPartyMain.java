package ds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TechGigNewYarPartyMain {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTestCases = Integer.parseInt(bufferedReader.readLine());
        StringBuilder finalResultBuilder = new StringBuilder();
        for (int i = 0; i < numberOfTestCases; i++) {
            bufferedReader.readLine();
            String tickets = bufferedReader.readLine();
            int[] numericTicketValues = Arrays.stream(tickets.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(numericTicketValues);
            if (i != 0) {
                finalResultBuilder.append("\n");
            }
            finalResultBuilder.append(techGigNewYearParty.findMaxSum().consolidateReverseWay());
        }
        System.out.println(finalResultBuilder.toString());
    }

}


@SuppressWarnings("WeakerAccess")
class TechGigNewYearParty {

    private final int[] input;
    private List<IndividualBestCase> individualBestCases = new ArrayList<>();

    public TechGigNewYearParty(int[] input) {
        this.input = input;
    }

    public Result findMaxSum() {
        if (input.length == 1) {
            return new Result(new ArrayList<Integer>() {
                {
                    add(input[0]);
                }
            });
        }

        if (input.length == 2) {
            if (input[0] >= input[1]) {
                return new Result(new ArrayList<Integer>() {
                    {
                        add(input[0]);
                    }
                });
            } else {
                return new Result(new ArrayList<Integer>() {
                    {
                        add(input[1]);
                    }
                });
            }
        }


        for (int anInput : input) {
            individualBestCases.add(new IndividualBestCase(anInput));
        }

        individualBestCases.get(1).updateSelfFromTrainingElement(null, individualBestCases.get(0));

        for (int i = 2; i < input.length; i++) {
            individualBestCases.get(i).updateSelfFromTrainingElement(individualBestCases.get(i - 2), individualBestCases.get(i - 1));
        }


        Trail max = individualBestCases
                .get(individualBestCases.size() - 1)
                .getTrails()
                .stream()
                .max(Trail::compareTo)
                .orElse(null);

        assert max != null;
        return new Result(max.getTrailElements());
    }
}

class Trail implements Comparable {
    private final List<Integer> trailElements;

    Trail(int firstElement) {
        this.trailElements = new ArrayList<Integer>() {
            {
                add(firstElement);
            }
        };
    }

    public void deleteAllElements() {
        trailElements.clear();
    }

    public List<Integer> getTrailElements() {
        return trailElements;
    }

    public int getLastElement() {
        return trailElements.get(trailElements.size() - 1);
    }

    public void addToTrail(int element) {
        this.trailElements.add(element);
    }

    public int sumUp() {
        return trailElements.stream().reduce((integer, integer2) -> integer + integer2).orElse(0);
    }

    public Trail copy() {
        Trail copy = new Trail(0);
        copy.deleteAllElements();
        trailElements.forEach(integer -> copy.trailElements.add(integer));
        return copy;
    }

    @Override
    public int compareTo(Object o) {
        Trail otherTrail = (Trail) o;
       // if (this.sumUp() != otherTrail.sumUp())
       //     return this.sumUp() - otherTrail.sumUp();
        int trailLength = this.trailElements.size();
        int otherTrailLength = otherTrail.getTrailElements().size();
        int shorterLength = trailLength >= otherTrailLength ? otherTrailLength : trailLength;
        for (int i = shorterLength - 1; i >= 0; i--) {
            if (trailElements.get(i).intValue() != otherTrail.getTrailElements().get(i).intValue()) {
                return trailElements.get(i).intValue() - otherTrail.getTrailElements().get(i).intValue();
            }
        }
        if (trailLength > otherTrailLength) {
            return -1;
        } else if (otherTrailLength > trailLength) {
            return 1;
        }
        return 0;
    }


}


@SuppressWarnings("WeakerAccess")
class IndividualBestCase {
    private final int baseValue;
    private final List<Trail> trails;
    private int maxSum;


    IndividualBestCase(int baseValue) {
        this.baseValue = baseValue;
        this.maxSum = baseValue;
        this.trails = new ArrayList<Trail>() {
            {
                add(new Trail(baseValue));
            }
        };
    }

    public int getBaseValue() {
        return baseValue;
    }

    public void clearTrails() {
        trails.clear();
    }

    public int getMaxSum() {
        return maxSum;
    }

    public List<Trail> getTrails() {
        return trails;
    }

    public List<Trail> copyTrails() {
        List<Trail> copy = new ArrayList<>();
        trails.forEach(trail -> copy.add(trail.copy()));
        return copy;
    }

    public void updateSelfFromTrainingElement(IndividualBestCase oneBeforeAdjacentTrailingElement, IndividualBestCase adjacentTrailingElement) {
        if (oneBeforeAdjacentTrailingElement == null) {
            if (adjacentTrailingElement.getMaxSum() > this.getBaseValue()) {
                this.maxSum = adjacentTrailingElement.getMaxSum();
                this.clearTrails();
                this.trails.addAll(adjacentTrailingElement.copyTrails());
            }
            return;
        }
        int valueWithOneBeforeAdjacent = oneBeforeAdjacentTrailingElement.getMaxSum() + this.getBaseValue();
        if ((adjacentTrailingElement.getMaxSum() == valueWithOneBeforeAdjacent) && (valueWithOneBeforeAdjacent >= baseValue)) {
            this.clearTrails();
            this.trails.addAll(oneBeforeAdjacentTrailingElement.copyTrails());
            this.trails.forEach(trail -> trail.getTrailElements().add(baseValue));
            this.trails.addAll(adjacentTrailingElement.copyTrails());

            this.maxSum = valueWithOneBeforeAdjacent;
            return;
        }
        if (adjacentTrailingElement.getMaxSum() > valueWithOneBeforeAdjacent) {
            this.maxSum = adjacentTrailingElement.getMaxSum();
            this.clearTrails();
            this.trails.addAll(adjacentTrailingElement.copyTrails());
        } else if (valueWithOneBeforeAdjacent > this.getBaseValue()) {
            this.maxSum = valueWithOneBeforeAdjacent;
            this.clearTrails();
            this.trails.addAll(oneBeforeAdjacentTrailingElement.copyTrails());
            this.trails.forEach(trail -> trail.getTrailElements().add(baseValue));
        }
    }

}


@SuppressWarnings("WeakerAccess")
class Result {
    private List<Integer> trail;

    public Result(List<Integer> trail) {
        this.trail = trail;
    }

    public String consolidateReverseWay() {
        StringBuilder resultBuffer = new StringBuilder();
        Collections.reverse(trail);
        trail.forEach(integer -> {
            if (!integer.equals(0)) {
                resultBuffer.append(integer);
            }
        });
        return resultBuffer.toString();
    }
}
