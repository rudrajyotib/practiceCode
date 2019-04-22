package playground;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CreateConstants
{





    public static void main(String[] args) throws URISyntaxException, IOException {
        InputStream in = new CreateConstants().getClass().getClassLoader()
                .getResourceAsStream("allLines");

        URI uri = new CreateConstants().getClass().getClassLoader().getResource("allLines").toURI();

        List<String> strings = Files.readAllLines(Paths.get(uri));

        for (String string : strings) {
            String finalVariableName;
            String finalAttributeName;
            if (string.contains("("))
            {
                finalVariableName=string.replace("(","_").replace(")","");
                finalAttributeName=string.substring(string.indexOf("(")+1, string.lastIndexOf(")"));
            }else{
                finalVariableName=string;
                finalAttributeName=finalVariableName;
            }
            //System.out.println("public static final String Dummy"+finalVariableName.toUpperCase()+"Value="+"\""+"Dummy"+finalVariableName+"\""+";");
            System.out.println("public static final String "+finalVariableName.toUpperCase()+"="+"\""+string+"\""+";");
        }
    }


}
