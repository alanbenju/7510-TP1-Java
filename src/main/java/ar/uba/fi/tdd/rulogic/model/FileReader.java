package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Created by alan on 09/11/17.
 */
public class FileReader {

    private String fileName;

    public FileReader(String filename){
        this.fileName = filename;
    }

    public String[] getDataFromFile(){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(this.fileName);

        String result = new BufferedReader(new InputStreamReader(is))
                .lines().collect(Collectors.joining("\n"));
        result = result.replace("\n", "").replace("\r", "");
        return result.split("\\.");
    }
}
