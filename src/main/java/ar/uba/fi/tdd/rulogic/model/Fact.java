package ar.uba.fi.tdd.rulogic.model;

/**
 * Created by alan on 09/11/17.
 */
public class Fact extends DataElement {
    public Fact(String data) {
        super(data);
    }
    public boolean isRule() {return false;}
    public boolean isValid(){
        return parser.isValidFact(this.text);
    }

}
