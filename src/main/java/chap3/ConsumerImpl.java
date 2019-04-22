package chap3;

import dataobjects.ScoreAndGrade;

import java.util.function.Consumer;

public class ConsumerImpl implements Consumer<ScoreAndGrade>{


    @Override
    public void accept(ScoreAndGrade scoreAndGrade) {
        scoreAndGrade.calculateGrade();
    }
}
