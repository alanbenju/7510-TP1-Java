package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by alan on 09/11/17.
 */
public class DataBase {

    private Hashtable<String, ArrayList<DataElement>> facts;
    private Hashtable<String, DataElement> rules;
    private Hashtable<String, Integer> names;

    public DataBase(){
        facts = new Hashtable();
        rules = new Hashtable();
        names = new Hashtable();
    }
    public void add(Rule rule) {

    }

    public void add(Fact fact) {

    }
}
