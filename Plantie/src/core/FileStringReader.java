package core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import utils.Constants;

/**
 * @author mhoeller
 */
public class FileStringReader {
    
    public String getDataString() throws IOException {
        StringBuffer sb = new StringBuffer();
        try (Stream<String> stream = Files.lines(Paths.get(Constants.FILENAME))) {
            Object[] streamArray = stream.toArray();
            for (Object string : streamArray) {
                sb.append(string);
            }
        }
        return sb.toString();
    }
    
    public String getHtmlString(File f) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(f.getPath()));
        return new String(encoded, "UTF-8");
    }
    
}
