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
        /*pepeSonJuanTrue();
		pepeTrue();
		juanTrue();
		pepeTrue();
		menFalse();*/
		Assert.assertFalse(this.knowledgeBase.answer("varon (javier)."));
	}

	@Test
	public void pepeSonJuanTrue() {
	    boolean result = this.knowledgeBase.answer("hijo(pepe,juan)");
        System.out.println(1+" "+result);
		Assert.assertTrue(result);
	}

	@Test
	public void pepaDaughterJuanFalse() {
        boolean result =this.knowledgeBase.answer("hija(pepa,juan)");
        System.out.println(2+" "+result);
		Assert.assertFalse(result);
	}
	@Test
	public void juanTrue() {
        boolean result = this.knowledgeBase.answer("varon(juan)");
        System.out.println(3+" "+result);
		Assert.assertTrue(result);
	}
	@Test
	public void pepeTrue() {
        boolean result =this.knowledgeBase.answer("varon(pepe)");
        System.out.println(4+" "+result);
		Assert.assertTrue(result);
	}
	@Test
	public void menFalse() {
        boolean result =this.knowledgeBase.answer("varon(cacacacaca)");
        System.out.println(5+" "+result);
		Assert.assertFalse(result);
	}

}
