package ar.uba.fi.tdd.rulogic.model;

import ar.uba.fi.tdd.rulogic.exceptions.InvalidDataException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Created by alan on 09/11/17.
 */
public class DataBase {

    private Hashtable<String, ArrayList<Fact>> facts;
    private Hashtable<String, Rule> rules;
    private Hashtable<String, Integer> names;

    private Parser parser;

    private DataElement query;

    public DataBase(){
        facts = new Hashtable();
        rules = new Hashtable();
        names = new Hashtable();
        parser = new Parser();
    }
    public void add(String text) throws InvalidDataException {
        DataElement data;

        if (parser.isRule(text)) {
            data = new Rule(text);
            //System.out.println("Rule "+text);
            this.rules.put(data.getName(),(Rule)data);
        }
        else{
            //System.out.println("Fact "+text);
            data = new Fact(text);
            this.addFact((Fact)data);
        }
        if (!data.isValid()){
            throw new InvalidDataException("Error reading database: "+text);
        }
        this.names.put(data.getName(),1);
    }

    private void addFact(Fact data){
        if (!this.facts.containsKey(data.getName())) this.facts.put(data.getName(),new ArrayList());
        ArrayList factsArray = this.facts.get(data.getName());
        factsArray.add(data);
        this.facts.put(data.getName(),factsArray);

    }

    public void newQuery(String query) {
        if (this.rules.containsKey(parser.parseName(query))) {
            this.query = new Rule(query);
        }
        else{
            this.query = new Fact(query);
        }
     }

    public boolean isQueryValid() {
        return this.names.containsKey(query.getName());
    }

    public boolean existFactsFromQuery() {
        ArrayList<Fact> factsToCheck = new ArrayList();
        if (this.query.isRule()){
            Rule rule = this.rules.get(this.query.getName());
            factsToCheck = rule.getFacts(this.query);
        }
        else{
            factsToCheck.add((Fact)(this.query));
        }

        return checkFacts(factsToCheck);
    }


    private boolean checkFacts(ArrayList<Fact> factsToCheck){
        if (factsToCheck.size()==0) return false;
        Iterator<Fact> elementsIterator = factsToCheck.iterator();
        boolean result = true;
        while(elementsIterator.hasNext() && result){
            Fact element = elementsIterator.next();
            result = this.checkOneFact(element);
        }

        return result;
    }


    private boolean checkOneFact(DataElement element){
        ArrayList<Fact> posibleFacts = this.facts.get(element.getName());
        Iterator<Fact> elementsIterator = posibleFacts.iterator();
        boolean result = false;
        while(elementsIterator.hasNext() && !result){
            DataElement posibleElement = elementsIterator.next();
            result = posibleElement.isEqual(element);
        }
        return result;
    }

}