package leetcode.hard.selfCrossing;

public class Solution
{
    private static boolean fallsInBetween(int number, int startRange, int endRange)
    {
        if (number == startRange || number == endRange)
        {
            return true;
        }
        if (startRange > endRange)
        {
            if (number > startRange)
            {
                return false;
            }
            return number >= endRange;
        }
        if (number < startRange)
        {
            return false;
        }
        return number <= endRange;
    }

    boolean isSelfCrossing(int[] x)
    {
        if (x == null)
        {
            return false;
        }
        if (x.length < 4)
        {
            return false;
        }
        MovementTracker movementTracker = new MovementTracker();
        /*for (int i : x)
        {
            if (movementTracker.checkIfCrossAndUpdate(i))
            {
                return true;
            }
        }*/
        return movementTracker.check(x);
    }

    private static class MovementTracker
    {
        int currentX = 0;
        int currentY = 0;
        int movementCounter = 0;
        String[] directions = new String[]{"North", "West", "South", "East"};
        boolean outbound = true;
        PointDefinition[] corners = new PointDefinition[]{new PointDefinition(0, 0), null, null, null};
        BoundaryDefinition[] boundaryDefinitions = new BoundaryDefinition[]{new BoundaryDefinition(), new BoundaryDefinition(), new BoundaryDefinition(), new BoundaryDefinition()};

        boolean check(int[] steps)
        {
            corners[1] = new PointDefinition(0, steps[0]);
            corners[2] = new PointDefinition(0 - steps[1], steps[0]);
            corners[3] = new PointDefinition(0 - steps[1], steps[0] - steps[2]);

            boundaryDefinitions[0].update(corners[0].x, corners[0].y, corners[1].x, corners[1].y);
            boundaryDefinitions[1].update(corners[1].x, corners[1].y, corners[2].x, corners[2].y);
            boundaryDefinitions[2].update(corners[2].x, corners[2].y, corners[3].x, corners[3].y);

            if (corners[3].y >= 0)
            {
                outbound = false;
                if (boundaryDefinitions[0].doesLineCross(corners[3].x, corners[3].y, corners[3].x + steps[3], corners[3].y))
                {
                    return true;
                }
            }

            if (steps.length == 4)
            {
                return false;
            }

            else if (fallsInBetween(corners[3].x + steps[3], boundaryDefinitions[1].startX, boundaryDefinitions[1].endX))
            {
                outbound = false;
            }
            if (corners[3].x + steps[3] == 0)
            {
                boundaryDefinitions[1].update(0, 0, corners[3].x, corners[3].y);
            }

            boundaryDefinitions[3].update(corners[3].x, corners[3].y, corners[3].x + steps[3], corners[3].y);
            currentX = corners[3].x + steps[3];
            currentY = corners[3].y;

            for (int i = 4; i < steps.length; i++)
            {
                if (checkIfCrossAndUpdate(steps[i]))
                {
                    return true;
                }
            }
            return false;
        }

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

            if (outbound)
            {
                if ("East".equals(direction))
                {
                    if (fallsInBetween(toBeEndX, boundaryDefinitions[3].startX, boundaryDefinitions[3].endX))
                    {
                        boundaryDefinitions[1].updateSelfWith(boundaryDefinitions[3]);
                        outbound = false;
                    }
                    else if (fallsInBetween(toBeEndX, boundaryDefinitions[1].startX, boundaryDefinitions[1].endX))
                    {
                        outbound = false;
                    }
                    boundaryDefinitions[3].update(currentX, currentY, toBeEndX, toBeEndY);
                }
                else if ("North".equals(direction))
                {
                    if (fallsInBetween(toBeEndY, boundaryDefinitions[0].startY, boundaryDefinitions[0].endY))
                    {
                        boundaryDefinitions[2].updateSelfWith(boundaryDefinitions[0]);
                        outbound = false;
                    }
                    else if (fallsInBetween(toBeEndY, boundaryDefinitions[2].startY, boundaryDefinitions[2].endY))
                    {
                        outbound = false;
                    }
                    boundaryDefinitions[0].update(currentX, currentY, toBeEndX, toBeEndY);
                }
                else if ("West".equals(direction))
                {
                    if (fallsInBetween(toBeEndX, boundaryDefinitions[1].startX, boundaryDefinitions[1].endX))
                    {
                        boundaryDefinitions[3].updateSelfWith(boundaryDefinitions[1]);
                        outbound = false;
                    }
                    else if (fallsInBetween(toBeEndX, boundaryDefinitions[3].startX, boundaryDefinitions[3].endX))
                    {
                        outbound = false;
                    }
                    boundaryDefinitions[1].update(currentX, currentY, toBeEndX, toBeEndY);
                }
                else if ("South".equals(direction))
                {
                    if (fallsInBetween(toBeEndY, boundaryDefinitions[2].startY, boundaryDefinitions[2].endY))
                    {
                        boundaryDefinitions[0].updateSelfWith(boundaryDefinitions[2]);
                        outbound = false;
                    }
                    else if (fallsInBetween(toBeEndY, boundaryDefinitions[0].startY, boundaryDefinitions[0].endY))
                    {
                        outbound = false;
                    }
                    boundaryDefinitions[2].update(currentX, currentY, toBeEndX, toBeEndY);
                }
            }
            else
            {
                if ("East".equals(direction))
                {
                    if (boundaryDefinitions[0].doesLineCross(currentX, currentY, toBeEndX, toBeEndY))
                    {
                        return true;
                    }
                    boundaryDefinitions[3].update(currentX, currentY, toBeEndX, toBeEndY);
                }
                else if ("North".equals(direction))
                {
                    if (boundaryDefinitions[1].doesLineCross(currentX, currentY, toBeEndX, toBeEndY))
                    {
                        return true;
                    }
                    boundaryDefinitions[0].update(currentX, currentY, toBeEndX, toBeEndY);
                }
                else if ("West".equals(direction))
                {
                    if (boundaryDefinitions[2].doesLineCross(currentX, currentY, toBeEndX, toBeEndY))
                    {
                        return true;
                    }
                    boundaryDefinitions[1].update(currentX, currentY, toBeEndX, toBeEndY);
                }
                else if ("South".equals(direction))
                {
                    if (boundaryDefinitions[3].doesLineCross(currentX, currentY, toBeEndX, toBeEndY))
                    {
                        return true;
                    }
                    boundaryDefinitions[2].update(currentX, currentY, toBeEndX, toBeEndY);
                }
            }

            currentX = toBeEndX;
            currentY = toBeEndY;

            ++movementCounter;

            return false;
        }
    }

    private static class PointDefinition
    {
        int x;
        int y;

        PointDefinition(int x, int y)
        {
            this.x = x;
            this.y = y;
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

        void updateSelfWith(BoundaryDefinition otherBoundaryDefinition)
        {
            this.startX = otherBoundaryDefinition.startX;
            this.startY = otherBoundaryDefinition.startY;
            this.endX = otherBoundaryDefinition.endX;
            this.endY = otherBoundaryDefinition.endY;
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
                    return fallsInBetween(otherLineStartY, startY, endY);
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
                    return fallsInBetween(otherLineStartX, startX, endX);
                }
            }
            return false;
        }
    }
}
