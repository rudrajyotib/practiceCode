package leetcode.hard.longestParanthesesSequence;

import java.util.Stack;

class Solution {

    int longestValidParentheses(String s) {

        Evaluator evaluator = new Evaluator();

        for (int i = 0; i < s.length(); i++) {
            if ('(' == (s.charAt(i))) {
                evaluator.openParenthesis();
            } else {
                evaluator.closeParenthesis();
            }
        }

        return evaluator.getMaxCount();
    }

    class Evaluator {
        private final Stack<Integer> weightedOpenParenthesis = new Stack<>();
        private int maxCount;
        private int predecessorCount;

        void reset() {
            predecessorCount = 0;
            weightedOpenParenthesis.removeAllElements();
        }


        void openParenthesis() {
            if (weightedOpenParenthesis.size() == 0) {
                weightedOpenParenthesis.push(predecessorCount);
            } else {
                int currentLastInStack = weightedOpenParenthesis.pop();
                weightedOpenParenthesis.push(currentLastInStack - predecessorCount);
                weightedOpenParenthesis.push(predecessorCount);
            }
            predecessorCount = 0;
        }

        void closeParenthesis() {
            if (weightedOpenParenthesis.size() == 0) {
                reset();
                return;
            }
            int weightOfLastOpenParenthesis = weightedOpenParenthesis.pop();
            weightOfLastOpenParenthesis += 2;
            if (weightOfLastOpenParenthesis > maxCount) {
                maxCount = weightOfLastOpenParenthesis;
            }
            predecessorCount = weightOfLastOpenParenthesis;
            if (weightedOpenParenthesis.size() > 0) {
                int currentLastOpenParenthesis = weightedOpenParenthesis.pop();
                weightedOpenParenthesis.push(currentLastOpenParenthesis + weightOfLastOpenParenthesis);
            }
        }

        int getMaxCount() {
            return maxCount;
        }

    }

}
