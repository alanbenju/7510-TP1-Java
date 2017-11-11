package ar.uba.fi.tdd.rulogic.model;

//import static org.mockito.MockitoAnnotations.initMocks;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.mockito.InjectMocks;

public class RuleTest {

    //@InjectMocks
    public RuleTest(){
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void checkValid() {
        Rule rule = new Rule("tio(X,Y,Z):-hola(X),hola(Y)");
        boolean result = rule.isRule() && rule.isValid();
        Assert.assertTrue(result);
    }
    @Test
    public void checkParams() {
        Rule rule = new Rule("tio(alario,pipa,cabezon");
        String[] shouldBe = {"alario","pipa","cabezon"};
        for (int i=0;i<shouldBe.length;i++){
            Assert.assertTrue(shouldBe[i].equals(rule.getParams("tio(alario,pipa,cabezon")[i]));
        }
    }
}
