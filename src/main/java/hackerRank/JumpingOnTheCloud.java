package hackerRank;

@SuppressWarnings("WeakerAccess")
public class JumpingOnTheCloud {
    private final int[] clouds;


    public JumpingOnTheCloud(int[] clouds) {
        this.clouds = clouds;
    }

    public int howManyJumpsNeeded() {
        int jumps = 0;
        int currentPosition = 0;
        while (currentPosition < clouds.length - 1) {
            if (((currentPosition + 2) <= (clouds.length - 1)) && clouds[currentPosition + 2] == 0) {
                ++currentPosition;
            }
            ++currentPosition;
            ++jumps;
        }
        return jumps;
    }
}
