package ar.uba.fi.tdd.rulogic.model;

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
    public void add(String text) {
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
            System.out.println("NO ES VALIDO "+text);
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
            //System.out.printf("rule");
        }
        else{
            this.query = new Fact(query);
            //System.out.printf("fact");
        }
    }

    public boolean isQueryValid() {
        //ToDo
        return this.names.containsKey(query.getName());
    }

    public boolean existFactsFromQuery() {
        ArrayList<Fact> factsToCheck = new ArrayList();
        if (this.query.isRule()){
            DataElement rule = this.rules.get(this.query.getName());
            factsToCheck = rule.getFacts(this.query);
            //System.out.printf("ES UN PIOLA RULE");
        }
        else{
            //System.out.printf("ES UN PUTA FACT");
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
           // System.out.println("element to check with db "+element.getText());
            result = this.checkOneFact(element);
        }

        //System.out.println("FIN "+result);
        return result;
    }


    private boolean checkOneFact(DataElement element){

        /*Set<String> keys = this.facts.keySet();
        Iterator<String> itr = keys.iterator();
        while (itr.hasNext()) {
            // Getting Key
            String str = itr.next();
            System.out.println("Key: "+str);
            Iterator<Fact> elementsIterator = this.facts.get(str).iterator();
            while(elementsIterator.hasNext()){
                DataElement posibleElement = elementsIterator.next();
                System.out.printf("value "+posibleElement.getText());
            }
        }*/
        //System.out.printf("mi elemento "+element.getName()); //ME DEVOLVIO HIJO EL PELOTUDO
        ArrayList<Fact> posibleFacts = this.facts.get(element.getName());
        Iterator<Fact> elementsIterator = posibleFacts.iterator();
        boolean result = false;
        while(elementsIterator.hasNext() && !result){

            DataElement posibleElement = elementsIterator.next();
           // System.out.printf("posible "+posibleElement.getText());
            result = posibleElement.isEqual(element);
        }
        return result;
    }


}

/*


 factsToCheck
* */
