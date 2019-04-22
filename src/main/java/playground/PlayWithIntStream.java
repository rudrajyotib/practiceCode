package playground;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class PlayWithIntStream {
    public static void main(String[] args) {
        IntStream limit = IntStream.iterate(0, i -> i + 1).limit(3);
        limit.forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });
    }
}
