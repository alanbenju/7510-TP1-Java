package ar.uba.fi.tdd.rulogic.model;

/**
 * Created by alan on 09/11/17.
 */
public class Creator {
    public void Creator(){}

    public Fact createFact(String text){
        return new Fact(text);
    }

    public Rule createRule(String text){
        return new Rule(text);
    }

}
