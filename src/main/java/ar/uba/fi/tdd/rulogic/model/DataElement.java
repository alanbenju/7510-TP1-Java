package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;

/**
 * Created by alan on 05/11/17.
 */
public class DataElement {
    private String text;
    private String name;
    private boolean isRule=false;

    public DataElement(String data){
        this.text = String.join("",data.split(" ")).replace(".","");
        this.name = data.split("\\(")[0].replace(" ","");
        this.checkIfRule();
    }

    private void checkIfRule(){
        String[] separated = this.text.split(":-");
        this.isRule = separated.length > 1;
    }

    public boolean isRule() {
        return this.isRule;
    }

    public ArrayList<DataElement> getFacts(DataElement query){
        //ToDo
        System.out.println(query.getText()+" CON "+this.text);


        return new ArrayList();
    }

    public String getName(){
        return this.name;
    }

    public String getText(){
        return this.text;
    }

    public boolean isEqual(DataElement query){
        return query.getText().equals(this.text);
    }

    public boolean incorrect(){
        //ToDo
        return false;
    }

}

