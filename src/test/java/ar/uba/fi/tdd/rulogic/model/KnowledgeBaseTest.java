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
    private KnowledgeBase badBase;
	public  KnowledgeBaseTest(){
		try {
			knowledgeBase = new KnowledgeBase("rules.db");
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}
        try {
            knowledgeBase = new KnowledgeBase("bad-rules.db");
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
        boolean result = false;
        try {
            result = this.knowledgeBase.answer("hijo(pepe,juan)");
        } catch (InvalidQueryException e) {
            e.printStackTrace();
        }
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
	public void pepeTrue() throws InvalidQueryException {
        boolean result =this.knowledgeBase.answer("varon(pepe)");
		Assert.assertTrue(result);
	}
	@Test
	public void menFalse() throws InvalidQueryException {
        boolean result =this.knowledgeBase.answer("varon(cacacacaca)");
		Assert.assertFalse(result);
	}
    @Test
    public void tioTrue() throws InvalidQueryException {
        boolean result =this.knowledgeBase.answer("tio(nicolas,alejandro, roberto)");
        Assert.assertTrue(result);
    }
    @Test
    public void badQuery() {
        boolean result = false;
        try {
            result = !this.knowledgeBase.answer("asdsadasdasdasdadas");
        } catch (InvalidQueryException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(!result);
    }



}
