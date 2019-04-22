package chap3;

import dataobjects.ScoreAndGrade;

import java.util.List;
import java.util.function.Consumer;

public class PaperChecker {
    public void checkPapers(List<ScoreAndGrade> scoreAndGrades, Consumer<ScoreAndGrade> consumer) {
        for (ScoreAndGrade scoreAndGrade : scoreAndGrades) {
            consumer.accept(scoreAndGrade);
        }
    }
}
