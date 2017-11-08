package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;
	public  KnowledgeBaseTest(){
	    knowledgeBase = new KnowledgeBase();
    }

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void test() {
        Assert.assertTrue(this.knowledgeBase.answer("hijo(juan,pepe)"));
		Assert.assertTrue(this.knowledgeBase.answer("hija(juan,pepa)"));
		Assert.assertTrue(this.knowledgeBase.answer("varon(juan)"));
		Assert.assertTrue(this.knowledgeBase.answer("varon(pepe)"));
        Assert.assertTrue(this.knowledgeBase.answer("varon(cacacacaca)"));

		//Assert.assertTrue(this.knowledgeBase.answer("varon (javier).")); ESTO EN REALIDAD ESTA MAL

	}

}
