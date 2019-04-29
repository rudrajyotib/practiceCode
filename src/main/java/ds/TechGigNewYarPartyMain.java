package ds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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


class TrailContainer implements Comparable {
    private int[] trail;
    private int sum = 0;
    private int currentLength = 0;

    public TrailContainer(int maxTrailLength, int element) {
        trail = new int[maxTrailLength];
        trail[0]=element;
        this.sum=element;
    }

    public TrailContainer addToTrail(int element) {
        TrailContainer trailContainer=new TrailContainer(trail.length,0);
        System.arraycopy(this.trail, 0, trailContainer.trail, 0, trail.length);
        trailContainer.sum=this.sum+element;
        trailContainer.currentLength = this.currentLength+1;
        trailContainer.trail[this.currentLength+1]=element;
        return trailContainer;
    }

    public void clearTrail() {
        sum = 0;
        currentLength = 0;
        trail = new int[trail.length];
    }

    public int elementAt(int index) {
        return trail[index];
    }

    public int getSum() {
        return sum;
    }

    public int trailLength() {
        return currentLength;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(trail)
                .forEach(value -> {
                    if (value != 0) {
                        stringBuilder.append(value);
                    }
                });
        return stringBuilder.toString();
    }

    @Override
    public int compareTo(Object o) {
        TrailContainer otherTrailContainer = (TrailContainer) o;

        int trailLength = this.trail.length;
        int otherTrailLength = otherTrailContainer.trailLength();
        int shorterLength = trailLength >= otherTrailLength ? otherTrailLength : trailLength;
        for (int i = shorterLength - 1; i >= 0; i--) {
            if (trail[i] != otherTrailContainer.elementAt(i)) {
                return trail[i] - otherTrailContainer.elementAt(i);
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
class TechGigNewYearParty {

    private final int[] input;

    public TechGigNewYearParty(int[] input) {
        this.input = input;
    }


    public Result findMaxSum() {

        return getResultsWay2();
    }

    private Result getResultsWay2() {
        if (input.length == 1) {
            TrailContainer trailContainer = new TrailContainer(1, input[0]);
            return new Result(trailContainer);
        }
        if (input.length == 2) {
            if (input[0] >= input[1]) {
                TrailContainer trailContainer = new TrailContainer(1, input[0]);
                return new Result(trailContainer);
            } else {
                TrailContainer trailContainer = new TrailContainer(1, input[1]);
                return new Result(trailContainer);
            }
        }

        TrailContainer[] trailContainers = new TrailContainer[input.length];
        TrailContainer rightMostTrailContainer = new TrailContainer(input.length, input[input.length - 1]);
        trailContainers[input.length-1] = rightMostTrailContainer;

        int elementInSecondFromRightTrailContainer=0;
        if (input[input.length - 2] > input[input.length - 1]) {
            elementInSecondFromRightTrailContainer=(input[input.length - 2]);
        } else {
            elementInSecondFromRightTrailContainer=(input[input.length - 1]);
        }
        trailContainers[input.length-2] = new TrailContainer(input.length,elementInSecondFromRightTrailContainer);

        for (int i = input.length - 3; i >= 0; i--) {
            int valueWithOneBeforeAdjacent = input[i] + trailContainers[i+2].getSum();
            Rearrange rearrange;

            if (trailContainers[i + 2].getSum()>trailContainers[i + 1].getSum())
            {
                rearrange=Rearrange.OneBeforeAdjacent;
            }else if(trailContainers[i + 2].getSum()<trailContainers[i + 1].getSum()){
                rearrange=Rearrange.Adjacent;
            }else{
                rearrange = (trailContainers[i + 2].compareTo(trailContainers[i + 1])) < 0 ? Rearrange.Adjacent:Rearrange.OneBeforeAdjacent;
            }
            if (rearrange.getSum(input, i, trailContainers) < valueWithOneBeforeAdjacent)
            {
                rearrange=Rearrange.SelfWithOneBeforeAdjacent;
            }else if (rearrange.getSum(input, i, trailContainers) == valueWithOneBeforeAdjacent)
            {
                if (rearrange.getTrailContainer(input, i, trailContainers).compareTo(Rearrange.SelfWithOneBeforeAdjacent.getTrailContainer(input, i, trailContainers)) <0 )
                {
                    rearrange=Rearrange.SelfWithOneBeforeAdjacent;
                }
            }
            if (rearrange.getSum(input, i, trailContainers) < input[i])
            {
                rearrange=Rearrange.Self;
            }else if (rearrange.getSum(input, i, trailContainers) == input[i])
            {
                if (rearrange.getTrailContainer(input, i, trailContainers).compareTo(Rearrange.Self.getTrailContainer(input, i, trailContainers)) <0 )
                {
                    rearrange=Rearrange.Self;
                }
            }
            rearrange.updateTrailForElement(input, i, trailContainers);
        }
        return new Result(trailContainers[0]);
    }

}

enum Rearrange
{
    Self
            {
                @Override
                public void updateTrailForElement(int[] input, int elementPosition, TrailContainer[] trailContainers) {
                    TrailContainer trailContainer=new TrailContainer(input.length, input[elementPosition]);
                    trailContainers[elementPosition] = trailContainer;
                }

                @Override
                public TrailContainer getTrailContainer(int[] input, int elementPosition, TrailContainer[] trailContainers) {
                    return new TrailContainer(1, input[elementPosition]);
                }

                @Override
                public int getSum(int[] input, int elementPosition, TrailContainer[] trailContainers) {
                    return input[elementPosition];
                }
            },
    SelfWithOneBeforeAdjacent
            {
                @Override
                public void updateTrailForElement(int[] input, int elementPosition, TrailContainer[] trailContainers) {
                    trailContainers[elementPosition]=trailContainers[elementPosition+2].addToTrail(input[elementPosition]);
                }

                @Override
                public TrailContainer getTrailContainer(int[] input, int elementPosition, TrailContainer[] trailContainers) {
                    return trailContainers[elementPosition + 2];
                }

                @Override
                public int getSum(int[] input, int elementPosition, TrailContainer[] trailContainers) {
                    return input[elementPosition]+trailContainers[elementPosition + 2].getSum();
                }
            },
    Adjacent
            {
                @Override
                public void updateTrailForElement(int[] input, int elementPosition, TrailContainer[] trailContainers) {
                    trailContainers[elementPosition]=trailContainers[elementPosition+1];
                }

                @Override
                public TrailContainer getTrailContainer(int[] input, int elementPosition, TrailContainer[] trailContainers) {
                    return trailContainers[elementPosition + 1];
                }

                @Override
                public int getSum(int[] input, int elementPosition, TrailContainer[] trailContainers) {
                    return trailContainers[elementPosition + 1].getSum();
                }
            },
    OneBeforeAdjacent
            {
                @Override
                public void updateTrailForElement(int[] input, int elementPosition, TrailContainer[] trailContainers) {
                    trailContainers[elementPosition]=trailContainers[elementPosition+2];
                }

                @Override
                public TrailContainer getTrailContainer(int[] input, int elementPosition, TrailContainer[] trailContainers) {
                    return trailContainers[elementPosition + 2];
                }

                @Override
                public int getSum(int[] input, int elementPosition, TrailContainer[] trailContainers) {
                    return trailContainers[elementPosition + 2].getSum();
                }
            };

    public abstract void updateTrailForElement(int[] input, int elementPosition, TrailContainer[] trailContainers);
    public abstract TrailContainer getTrailContainer(int[] input, int elementPosition, TrailContainer[] trailContainers);
    public abstract int getSum(int[] input, int elementPosition, TrailContainer[] trailContainers );
}

@SuppressWarnings("WeakerAccess")
class Result {
    private List<Integer> trail;
    private TrailContainer trailContainer;

    public Result(TrailContainer trailContainer) {
        this.trailContainer = trailContainer;
    }

    public Result(List<Integer> trail) {
        this.trail = trail;
    }

    public String consolidateReverseWay() {


        StringBuilder resultBuffer = new StringBuilder();

        if (trailContainer != null) {
            return trailContainer.toString();
        } else {


            Collections.reverse(trail);
            trail.forEach(integer -> {
                if (!integer.equals(0)) {
                    resultBuffer.append(integer);
                }
            });
        }
        return resultBuffer.toString();
    }
}
