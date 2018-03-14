package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import utils.Constant;

/**
 * @author mhoeller
 */
public class FileStringReader {
    
    private String fileString;
    
    public String getFileString() {
        return fileString;
    }
    
    public FileStringReader() throws IOException {
        fileString = getDataString();
    }
    
    private String getDataString() throws IOException {
        StringBuffer sb = new StringBuffer();
        try (Stream<String> stream = Files.lines(Paths.get(Constant.FILENAME))) {
            Object[] streamArray = stream.toArray();
            for (Object string : streamArray) {
                sb.append(string);
            }
        }
        return sb.toString();
    }
    
}
