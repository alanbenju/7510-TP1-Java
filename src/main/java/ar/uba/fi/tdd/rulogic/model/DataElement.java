package ar.uba.fi.tdd.rulogic.model;

import javax.xml.crypto.Data;
import java.util.ArrayList;

/**
 * Created by alan on 05/11/17.
 */
public class DataElement {
    private String text;
    private String name;
    private boolean isRule=false;

    public DataElement(String data){
        this.text = String.join("",data.split(" "));
        this.name = data.split("(")[0];

        this.checkIfRule();
    }

    private void checkIfRule(){
        String[] separated = this.text.split(":-");
        this.isRule = separated.length > 1;
    }

    public boolean isRule() {
        return this.isRule;
    }

    public ArrayList<DataElement> getFacts(){

        return new ArrayList();
    }

    public String getName(){
        return this.name;
    }

    public boolean isEqual(DataElement query);
        return true;
    }

}

