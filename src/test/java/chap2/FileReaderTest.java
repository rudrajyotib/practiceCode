package chap2;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileReaderTest {

    private FileReader fileReader;
    private String currentPath;

    @Before
    public void setUp() throws IOException {
        fileReader=new FileReader();
        currentPath=new File(".").getCanonicalPath()+"/src\\test\\test-resource/test";
    }


    @Test
    public void shouldReadLine1() throws IOException {
        assertEquals("Line1", fileReader.provideFirstLineOfFile(currentPath));
    }

    @Test
    public void shouldReadWholeContent() throws IOException {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Line1").append("\n").append("Line2").append("\n");
        assertEquals(stringBuilder.toString(), fileReader.provideWholeFileContent(currentPath));

    }

}