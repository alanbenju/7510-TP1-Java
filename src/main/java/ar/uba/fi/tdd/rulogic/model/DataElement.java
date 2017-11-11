package ar.uba.fi.tdd.rulogic.model;

/**
 * Created by alan on 05/11/17.
 */
public abstract class DataElement {
    protected String text;
    protected String name;
    protected Parser parser;

    public DataElement(String data){
        this.init(data);
    }

    protected void init(String data){
        parser = new Parser();
        this.name = parser.parseName(data);
        this.text = parser.parseText(data);
    }

    public abstract boolean isValid();

    public abstract boolean isRule();

    protected String[] getParams(String text){
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
        return query.getText().equals(this.text);
    }

}

