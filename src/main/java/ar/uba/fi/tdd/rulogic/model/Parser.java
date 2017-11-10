package ar.uba.fi.tdd.rulogic.model;

/**
 * Created by alan on 09/11/17.
 */
public class Parser {
    public String parseName(String data) {
        return data.split("\\(")[0].replace(" ","");
    }

    public String parseText(String data) {
        return data.replace(".","").replace("\t","").replace(" ", "");
        //return String.join("",data.split(" ")).replace(".","");
    }

    public boolean isRule(String data){
        String[] separated = data.split(":-");
        return separated.length > 1;
    }

    public Boolean isValidFact(String data) {
        String regex = "^[a-zA-Z]*\\([a-zA-Z]+(,[a-zA-Z]*)*\\)";
        return data.matches(regex);
    }

    public Boolean isValidRule(String data) {
        String regex = "^[a-zA-Z]*\\([a-zA-Z]+(,[a-zA-Z]*)*\\):-[a-zA-Z]*\\([a-zA-Z]+(,[a-zA-Z]*)*\\)(,[a-zA-Z]*\\([a-zA-Z]+(,[a-zA-Z]*)*\\))*";
        //System.out.println(data+" - "+data.matches(regex));
        return data.matches(regex);
    }
}
