package ar.uba.fi.tdd.rulogic.model;

//import static org.mockito.MockitoAnnotations.initMocks;


import ar.uba.fi.tdd.rulogic.exceptions.InvalidDataException;
import ar.uba.fi.tdd.rulogic.exceptions.InvalidQueryException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.mockito.InjectMocks;

public class KnowledgeBaseTest {

	//@InjectMocks
	private KnowledgeBase knowledgeBase;
	public  KnowledgeBaseTest() throws InvalidDataException {
			knowledgeBase = new KnowledgeBase("rules.db");
    }

	@Before
	public void setUp() throws Exception {
		//initMocks(this);
	}

	@Test
	public void pepeSonJuanTrue() throws InvalidQueryException {
        boolean result = this.knowledgeBase.answer("hijo(pepe,juan)");
        Assert.assertTrue(result);
	}
    @Test
    public void RuleTest1() throws InvalidQueryException {
        boolean result = this.knowledgeBase.answer("hijo(pepe,pepa)");
        Assert.assertTrue(!result);
    }
    @Test
    public void RuleTest2() throws InvalidQueryException {
        boolean result = this.knowledgeBase.answer("hija(maria,hector)");
        Assert.assertTrue(result);
    }
    @Test
    public void RuleTest3() throws InvalidQueryException {
        boolean result = this.knowledgeBase.answer("hijo(pepe,juan)");
        Assert.assertTrue(result);
    }
    @Test
    public void RuleTest4() throws InvalidQueryException {
        boolean result = this.knowledgeBase.answer("hijo(pepe,juan)");
        Assert.assertTrue(result);
    }


	@Test
	public void pepaDaughterJuanFalse() throws InvalidQueryException {
        boolean result =this.knowledgeBase.answer("hija(pepa,juan)");
		Assert.assertFalse(result);
	}
	@Test
	public void juanTrue() throws InvalidQueryException {
        boolean result = this.knowledgeBase.answer("varon(juan)");
		Assert.assertTrue(result);
	}
	@Test
	public void factsTrue1() throws InvalidQueryException {
        boolean result =this.knowledgeBase.answer("varon(pepe)");
		Assert.assertTrue(result);
	}
    @Test
    public void factTrue2() throws InvalidQueryException {
        boolean result =this.knowledgeBase.answer("varon(juan)");
        Assert.assertTrue(result);
    }
    @Test
    public void factTrue3() throws InvalidQueryException {
        boolean result =this.knowledgeBase.answer("varon(hector)");
        Assert.assertTrue(result);
    }
    @Test
    public void factTrue4() throws InvalidQueryException {
        boolean result =this.knowledgeBase.answer("varon(roberto)");
        Assert.assertTrue(result);
    }
    @Test
    public void factTrue5() throws InvalidQueryException {
        boolean result =this.knowledgeBase.answer("varon(alejandro)");
        Assert.assertTrue(result);
    }
    @Test
    public void factTrue6() throws InvalidQueryException {
        boolean result =this.knowledgeBase.answer("mujer(maria)");
        Assert.assertTrue(result);
    }

    @Test
    public void tioTrue() throws InvalidQueryException {
        boolean result =this.knowledgeBase.answer("tio(nicolas,alejandro, roberto)");
        Assert.assertTrue(result);
    }
    @Test
    public void badQuery() {
        boolean result = !this.knowledgeBase.answer("asdsadasdasdasdadas");
        Assert.assertTrue(result);
    }



}
