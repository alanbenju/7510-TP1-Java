package ar.uba.fi.tdd.rulogic.model;

/**
 * Created by alan on 05/11/17.
 */
public class DataElement {
    private String text;
    private String name;

    public DataElement(String data){
        this.text = data;
        this.name = data.split("(")[0];
    }

}

