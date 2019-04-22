package dataobjects;

public class ScoreAndGrade {

    private int score;
    private int grade;

    public ScoreAndGrade(int score) {
        this.score = score;
    }

    public void calculateGrade(){
        if (score < 40) {
            grade = 3;
            return;
        }
        if (score <80) {
            grade = 2;
            return;
        }
        grade=1;
    }

    public int getScore() {
        return score;
    }

    public int getGrade() {
        return grade;
    }
}
