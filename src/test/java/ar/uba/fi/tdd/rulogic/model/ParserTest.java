package ar.uba.fi.tdd.rulogic.model;

//import static org.mockito.MockitoAnnotations.initMocks;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.mockito.InjectMocks;

public class ParserTest {

    //@InjectMocks
    private Parser parser;
    public  ParserTest(){
        /*try {
            knowledgeBase = new KnowledgeBase("rules.db");
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }*/
        parser = new Parser();

    }

    @Before
    public void setUp() throws Exception {
        //initMocks(this);
    }

    @Test
    public void validFact() {
        boolean result = this.parser.isValidFact("varon(juan)");
        Assert.assertTrue(result);
    }
    @Test
    public void notValidFact() {
        boolean result = !this.parser.isValidFact("varon::::(juan)");
        Assert.assertTrue(result);
    }
    @Test
    public void RuleNotValidFact() {
        boolean result = !this.parser.isValidFact("hija(X, Y) :- mujer(X), padre(Y, X).");
        Assert.assertTrue(result);
    }
    @Test
    public void validRule() {
        boolean result = this.parser.isValidRule("hija(X,Y):-mujer(X),padre(Y,X)");
        Assert.assertTrue(result);
    }
    @Test
    public void notValidRule() {
        boolean result = !this.parser.isValidRule("hija(X, Y) = mujer(X), padre(Y, X).");
        Assert.assertTrue(result);
    }

}
