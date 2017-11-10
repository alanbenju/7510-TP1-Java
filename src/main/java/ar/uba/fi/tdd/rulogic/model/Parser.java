package ar.uba.fi.tdd.rulogic.model;

/**
 * Created by alan on 09/11/17.
 */
public class Parser {
    public String parseName(String data) {
        return data.split("\\(")[0].replace(" ","");
    }

    public String parseText(String data) {
        return String.join("",data.split(" ")).replace(".","");
    }

    public boolean isRule(String data){
        String[] separated = data.split(":-");
        return separated.length > 1;
    }
}
