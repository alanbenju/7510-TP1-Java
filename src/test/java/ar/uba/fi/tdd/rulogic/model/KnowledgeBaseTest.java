package ar.uba.fi.tdd.rulogic.model;

//import static org.mockito.MockitoAnnotations.initMocks;


import ar.uba.fi.tdd.rulogic.exceptions.InvalidDataException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.mockito.InjectMocks;

public class KnowledgeBaseTest {

	//@InjectMocks
	private KnowledgeBase knowledgeBase;
	public  KnowledgeBaseTest(){
		try {
			knowledgeBase = new KnowledgeBase("rules.db");
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void setUp() throws Exception {
		//initMocks(this);
	}

	@Test
	public void pepeSonJuanTrue() {
	    boolean result = this.knowledgeBase.answer("hijo(pepe,juan)");
		Assert.assertTrue(result);
	}

	@Test
	public void pepaDaughterJuanFalse() {
        boolean result =this.knowledgeBase.answer("hija(pepa,juan)");
		Assert.assertFalse(result);
	}
	@Test
	public void juanTrue() {
        boolean result = this.knowledgeBase.answer("varon(juan)");
		Assert.assertTrue(result);
	}
	@Test
	public void pepeTrue() {
        boolean result =this.knowledgeBase.answer("varon(pepe)");
		Assert.assertTrue(result);
	}
	@Test
	public void menFalse() {
        boolean result =this.knowledgeBase.answer("varon(cacacacaca)");
		Assert.assertFalse(result);
	}
    @Test
    public void tioTrue() {
        boolean result =this.knowledgeBase.answer("tio(nicolas,alejandro, roberto)");
        Assert.assertFalse(result);
    }



}
