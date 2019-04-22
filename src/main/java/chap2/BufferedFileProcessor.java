package chap2;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedFileProcessor {

    String readFileContent(BufferedReader bufferedReader) throws IOException;

}
