package leetcode.hard.selfCrossing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Solution
{
    static boolean fallsInBetween(int number, int startRange, int endRange)
    {
        if (startRange > endRange)
        {
            if (number > startRange)
            {
                return false;
            }
            if (number < endRange)
            {
                return false;
            }
        }
        if (number < startRange)
        {
            return false;
        }
        if (number > endRange)
        {
            return false;
        }
        return true;
    }

    public boolean isSelfCrossing(int[] x)
    {
        MovementTracker movementTracker = new MovementTracker();
        for (int i : x)
        {
            if (movementTracker.checkIfCrossAndUpdate(i))
            {
                return true;
            }
        }
        return false;
    }

    private static class MovementTracker
    {
        int currentX = 0;
        int currentY = 0;
        int movementCounter = 0;
        String[] directions = new String[]{"North", "West", "South", "East"};
        Map<String, List<String>> movementToObstractionMap = new HashMap<String, List<String>>()
        {
            {
                put("North", Arrays.asList("West"));
                put("West", Arrays.asList("South"));
                put("South", Arrays.asList("East"));
                put("East", Arrays.asList("North"));
            }
        };
        Map<String, List<String>> movementToOverlapMap = new HashMap<String, List<String>>()
        {
            {
                put("West", Arrays.asList("West", "East"));
                put("North", Arrays.asList("South", "North"));
                put("East", Arrays.asList("West", "East"));
                put("South", Arrays.asList("South", "North"));
            }
        };
        Map<String, BoundaryDefinition> boundaryDefinitionMap = new HashMap<String, BoundaryDefinition>()
        {
            {
                put("North", new BoundaryDefinition());
                put("South", new BoundaryDefinition());
                put("East", new BoundaryDefinition());
                put("West", new BoundaryDefinition());
            }
        };

        boolean checkIfCrossAndUpdate(int steps)
        {
            int toBeEndX;
            int toBeEndY;

            final String direction = directions[movementCounter % 4];
            if ("North".equals(direction))
            {
                toBeEndX = currentX;
                toBeEndY = currentY + steps;
            }
            else if ("West".equals(direction))
            {
                toBeEndX = currentX - steps;
                toBeEndY = currentY;
            }
            else if ("South".equals(direction))
            {
                toBeEndX = currentX;
                toBeEndY = currentY - steps;
            }
            else
            {
                toBeEndX = currentX + steps;
                toBeEndY = currentY;
            }

            boolean overLaps = movementToOverlapMap.get(direction)
                .stream()
                .anyMatch(new Predicate<String>()
                {
                    @Override
                    public boolean test(String s)
                    {
                        boolean overlaps = boundaryDefinitionMap.get(s).doLineOverLap(currentX, currentY, toBeEndX, toBeEndY);
                        //System.out.println("Overlap::"+s+"::On::"+direction+"::"+overlaps);
                        return overlaps;
                    }
                });
            if (overLaps)
            {
                return true;
            }

            boolean crosses = movementToObstractionMap.get(direction)
                .stream()
                .anyMatch(new Predicate<String>()
                {
                    @Override
                    public boolean test(String s)
                    {
                        boolean cross = boundaryDefinitionMap.get(s).doesLineCross(currentX, currentY, toBeEndX, toBeEndY);
                        //System.out.println("Cross::"+s+"::On::"+direction+"::"+cross);
                        return cross;
                    }
                });

            if (crosses)
            {
                return true;
            }

            boundaryDefinitionMap.get(direction).update(currentX, currentY, toBeEndX, toBeEndY);
            currentX = toBeEndX;
            currentY = toBeEndY;

            ++movementCounter;

            return false;
        }
    }

    private static class BoundaryDefinition
    {
        int startX;
        int startY;
        int endX;
        int endY;
        boolean initialised = false;

        void update(int startX, int startY, int endX, int endY)
        {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.initialised = true;
        }

        boolean doLineOverLap(int otherLineStartX, int otherLineStartY, int otherLineEndX, int otherLineEndY)
        {
            if (!initialised)
            {
                return false;
            }
            if (startX == endX)//vertical
            {
                if (otherLineStartX != otherLineEndX)
                {
                    return false;
                }
                if (startX != otherLineStartX)
                {
                    return false;
                }
                if (fallsInBetween(startY, otherLineStartY, otherLineEndY) || fallsInBetween(endY, otherLineStartY, otherLineEndY))
                {
                    return true;
                }
            }
            else //horizontal
            {
                if (otherLineStartY != otherLineEndY)
                {
                    return false;
                }
                if (startY != otherLineStartY)
                {
                    return false;
                }
                if (fallsInBetween(startX, otherLineStartX, otherLineEndX) || fallsInBetween(endX, otherLineStartX, otherLineEndX))
                {
                    return true;
                }
            }
            return false;
        }

        boolean doesLineCross(int otherLineStartX, int otherLineStartY, int otherLineEndX, int otherLineEndY)
        {
            if (!initialised)
            {
                return false;
            }
            if (startX == endX)//vertical
            {
                if (otherLineStartX == otherLineEndX)
                {
                    return false;
                }
                if (fallsInBetween(startX, otherLineStartX, otherLineEndX))
                {
                    if (fallsInBetween(otherLineStartY, startY, endY))
                    {
                        return true;
                    }
                }
            }
            else //horizontal
            {
                if (otherLineStartY == otherLineEndY)
                {
                    return false;
                }
                if (fallsInBetween(startY, otherLineStartY, otherLineEndY))
                {
                    if (fallsInBetween(otherLineStartX, startX, endX))
                    {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
