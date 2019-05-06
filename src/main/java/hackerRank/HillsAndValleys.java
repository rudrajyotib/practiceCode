package hackerRank;

@SuppressWarnings("WeakerAccess")
public class HillsAndValleys {
    final String pathTraversed;

    public HillsAndValleys(String pathTraversed) {
        this.pathTraversed = pathTraversed;
    }

    public int countValleys() {
        int result = 0;
        int altitude = 0;
        for (int i = 0; i < pathTraversed.length(); i++) {
            char c = pathTraversed.charAt(i);
            if (c == 'U') {
                ++altitude;
                if (altitude == 0) {
                    ++result;
                }
            } else {
                --altitude;
            }
        }
        return result;
    }
}
