package ds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TechGigNewYarPartyMain
{

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTestCases = Integer.parseInt(bufferedReader.readLine());
        StringBuilder finalResultBuilder = new StringBuilder();
        for (int i=0;i<numberOfTestCases;i++)
        {
            bufferedReader.readLine();
            String tickets = bufferedReader.readLine();
            int[] numericTicketValues = Arrays.stream(tickets.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            TechGigNewYarParty techGigNewYarParty=new TechGigNewYarParty(numericTicketValues);
            if (i!=0)
            {
                finalResultBuilder.append("\n");
            }
            finalResultBuilder.append(techGigNewYarParty.findMaxSum().consolidateReverseWay());
        }
        System.out.println(finalResultBuilder.toString());
    }

}


@SuppressWarnings("WeakerAccess")
class TechGigNewYarParty {

    private final int[] input;
    private List<IndividualBestCase> individualBestCases = new ArrayList<>();

    public TechGigNewYarParty(int[] input) {
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


        for (int i = 2; i < input.length; i++) {
            individualBestCases.get(i).updateSelfFromTrainingElement(individualBestCases.get(i - 2), individualBestCases.get(i - 1));
        }

        IndividualBestCase max = individualBestCases
                .stream()
                .max((o1, o2) -> {
                    if (o1.getMaxSum()!=o2.getMaxSum())
                        return o1.getMaxSum()-o2.getMaxSum();
                    return o1.getTrail().get(o1.getTrail().size()-1) - o2.getTrail().get(o2.getTrail().size()-1);
                })
                .orElse(null);

        assert max != null;
        return new Result(max.getTrail());
    }
}

@SuppressWarnings("WeakerAccess")
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
        int valueWithOneBeforeAdjacent = oneBeforeAdjacentTrailingElement.getMaxSum() + this.getBaseValue();
        if (adjacentTrailingElement.getMaxSum() > valueWithOneBeforeAdjacent) {
            this.maxSum = adjacentTrailingElement.getMaxSum();
            this.trail.remove(0);
            this.trail.addAll(0, adjacentTrailingElement.getTrail());
        } else if (valueWithOneBeforeAdjacent > this.getBaseValue()) {
            this.maxSum = valueWithOneBeforeAdjacent;
            this.trail.addAll(0, oneBeforeAdjacentTrailingElement.getTrail());
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
            if (!integer.equals(0))
            {
                resultBuffer.append(integer);
            }
        });
        return resultBuffer.toString();
    }
}
