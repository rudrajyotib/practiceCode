package chap3;

import dataobjects.ScoreAndGrade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntBiFunction;

import static java.util.Comparator.comparing;

public class ConsumerImplTest {

    private List<ScoreAndGrade> scoreAndGrades;

    @Before
    public void setup(){
        scoreAndGrades =new ArrayList<ScoreAndGrade>(){
            {
                add(new ScoreAndGrade(39));
                add(new ScoreAndGrade(43));
                add(new ScoreAndGrade(99));
            }
        };
    }


    @Test
    public void shouldGradeAllPapersByConsumer(){
        ConsumerImpl consumer=new ConsumerImpl();
        for (ScoreAndGrade scoreAndGrade : scoreAndGrades) {
            consumer.accept(scoreAndGrade);
        }
        Assert.assertEquals(3, scoreAndGrades.get(0).getGrade());
        ToIntBiFunction<ScoreAndGrade, ScoreAndGrade> e1=(scoreAndGrade, scoreAndGrade2) -> scoreAndGrade.getScore()>= scoreAndGrade2.getScore() ? -1:1;
        int i = e1.applyAsInt(scoreAndGrades.get(0), scoreAndGrades.get(1));
        scoreAndGrades.sort((o1, o2) -> o1.getScore()>=o2.getScore()?1:-1);
        scoreAndGrades.sort(comparing(ScoreAndGrade::getScore));
        Assert.assertEquals(39, scoreAndGrades.get(0).getScore());
    }

    @Test
    public void shouldGradePapersByPaperChecker(){
        PaperChecker paperChecker=new PaperChecker();
        paperChecker.checkPapers(scoreAndGrades, ScoreAndGrade::calculateGrade);
        Assert.assertEquals(3, scoreAndGrades.get(0).getGrade());
    }
}
