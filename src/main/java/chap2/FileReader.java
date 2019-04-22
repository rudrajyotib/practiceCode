package chap2;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {

    public String provideFirstLineOfFile(String fileName) throws IOException {

        BufferedReader bufferedReader=new BufferedReader(new java.io.FileReader(fileName));
        String line= readFileContent(bufferedReader1 -> bufferedReader1.readLine(), bufferedReader);
        return line;
    }

    public String provideWholeFileContent(String fileName) throws IOException {

        BufferedReader bufferedReader=new BufferedReader(new java.io.FileReader(fileName));

        return ((BufferedFileProcessor) bufferedReader1 -> {
            String s;
            StringBuffer stringBuffer = new StringBuffer();
            while ((s = bufferedReader1.readLine()) != null) {
                stringBuffer.append(s);
                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }).readFileContent(bufferedReader);
    }


    private String readFileContent(BufferedFileProcessor bufferedFileProcessor, BufferedReader bufferedReader) throws IOException {
        return bufferedFileProcessor.readFileContent(bufferedReader);
    }

}
