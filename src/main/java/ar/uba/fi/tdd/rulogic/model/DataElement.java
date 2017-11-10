package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;

/**
 * Created by alan on 05/11/17.
 */
public class DataElement {
    private String text;
    private String name;
    private boolean isRule=false;

    private Parser parser;

    public DataElement(String data){
        this.init(data);
    }

    private void init(String data){
        parser = new Parser();
        this.name = parser.parseName(data);
        this.text = parser.parseText(data);
        this.checkIfRule();
    }

    /*
    * Only works for DataBase not for query
    * */
    private void checkIfRule(){
        String[] separated = this.text.split(":-");
        this.isRule = separated.length > 1;
    }

    public boolean isRule(){
        return true;
    }

    public ArrayList<Fact> getFacts(DataElement query){
        ArrayList<Fact> factsToCheck = new ArrayList();
        String[] queryParams = query.getParams(query.getText());
        String[] dataList = this.text.split(":-");
        String[] dataParams = this.getParams(dataList[0]);
        String[] notReadyFacts = dataList[1].replace("),",")-").split("-");

        for (int i=0;i<notReadyFacts.length;i++){
            for (int j=0;j<dataParams.length;j++){
                notReadyFacts[i]=notReadyFacts[i].replace(dataParams[j],queryParams[j]);
            }
            factsToCheck.add(new Fact(notReadyFacts[i]));
        }
        return factsToCheck;
    }

    /*
    *   in : hijo(varon,pepe)
    *   out: [varon,pepe]
     */
    private String[] getParams(String text){
        String params = text.split("\\(")[1].replace(")","");
        return params.split(",");
    }


    public String getName(){
        return this.name;
    }

    public String getText(){
        return this.text;
    }

    public boolean isEqual(DataElement query){
        //System.out.println(query.getText()+" "+this.text+"="+query.getText().equals(this.text));
        return query.getText().equals(this.text);
    }

    public boolean incorrect(){
        //ToDo
        return false;
    }

}

