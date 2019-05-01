package ds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TechGigFightWinLose {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTestCases = Integer.parseInt(bufferedReader.readLine());
        StringBuilder finalResultBuilder = new StringBuilder();
        for (int i=0;i<numberOfTestCases;i++)
        {
            int numberOfFights = Integer.parseInt(bufferedReader.readLine());
            String inputOfVillains = bufferedReader.readLine();
            String inputOfHeroes = bufferedReader.readLine();
            FightSimulator fightSimulator=new FightSimulator(numberOfFights, inputOfVillains, inputOfHeroes);
            if (i != 0) {
                finalResultBuilder.append("\n");
            }
            finalResultBuilder.append(fightSimulator.fightResult());
        }
        System.out.println(finalResultBuilder.toString());
    }
}

class FightSimulator
{
    private final int numberOfFights;
    private final String inputOfVillains;
    private final String inputOfHeroes;


    FightSimulator(int numberOfFights, String inputOfVillains, String inputOfHeroes) {
        this.numberOfFights = numberOfFights;
        this.inputOfVillains = inputOfVillains;
        this.inputOfHeroes = inputOfHeroes;
    }

    public String fightResult()
    {
        int villainIndex = 0;
        int heroIndex = 0;
        int[] heroes = new int[numberOfFights];
        int[] villains = new int[numberOfFights];
        for (int i=0;i<numberOfFights;i++)
        {
            CharSequence villainStrength = findStrength(inputOfVillains, villainIndex);
            villainIndex=villainIndex+villainStrength.length()+1;
            CharSequence heroStrength = findStrength(inputOfHeroes, heroIndex);
            heroIndex=heroIndex+heroStrength.length()+1;
            heroes[i]=numerifyStrength(heroStrength);
            villains[i]=numerifyStrength(villainStrength);
        }
        Arrays.sort(heroes);
        Arrays.sort(villains);
        for (int i=numberOfFights-1;i>=0;i--)
        {
            if (heroes[i]<villains[i])
            {
                return "LOSE";
            }
        }
        return "WIN";
    }

    private int numerifyStrength(CharSequence charSequence)
    {
        int length = charSequence.length();
        int strength = 0;
        for (int i=0;i<charSequence.length();i++)
        {
            strength = (int)(Character.getNumericValue(charSequence.charAt(i))*Math.pow(10,length-i-1) )+ strength;
        }
        return strength;
    }



    private CharSequence findStrength(String input, int startIndex)
    {
        int stopAt = startIndex;
        while ( (stopAt<input.length()) && !Character.isWhitespace(input.charAt(stopAt)) )
        {
            ++stopAt;
        }
        return input.subSequence(startIndex, stopAt);
    }
}
