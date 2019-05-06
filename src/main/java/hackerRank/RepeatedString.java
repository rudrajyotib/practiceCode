package hackerRank;

@SuppressWarnings("WeakerAccess")
public class RepeatedString {
    private final String initialText;
    private final long fullTextLength;

    public RepeatedString(String initialText, long fullTextLength) {
        this.initialText = initialText;
        this.fullTextLength = fullTextLength;
    }

    public long countAs() {
        long completeRepeat = fullTextLength / initialText.length();
        long partialRepeatCharacter = fullTextLength % initialText.length();

        int countOfAsInInitialText = 0;
        int countOfAsInLeftOverRepeatText = 0;

        for (int i = 0; i < initialText.length(); i++) {
            if (initialText.charAt(i) == 'a') {
                ++countOfAsInInitialText;
                if (i < partialRepeatCharacter) {
                    ++countOfAsInLeftOverRepeatText;
                }
            }
        }
        return countOfAsInInitialText * completeRepeat + countOfAsInLeftOverRepeatText;
    }
}
