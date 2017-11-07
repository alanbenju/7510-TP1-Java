package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.stream.Collectors;



public class KnowledgeBase {
	private Hashtable<String, ArrayList<DataElement>> facts;
	private Hashtable<String, DataElement> rules;
	private Hashtable<String, Integer> names;

	public KnowledgeBase(){
		facts = new Hashtable();
		rules = new Hashtable();
        names = new Hashtable();
        try {
            parseDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private void parseDB() throws Exception {
		String db = getBdFile("rules.db");
        String[] dbList = db.split("\\./n");
        DataElement data;
		for (int i = 0;i < dbList.length;i++){
            data = new DataElement(dbList[i]);
            if (data.incorrect()){
                throw new Exception("Incorrect database: "+dbList[i]);
            }
            if (data.isRule()) this.rules.put(data.getName(),data);
            else this.addFact(data);
			this.addName(data.getName());
		}
	}


	private void addFact(DataElement data){
	    if (!this.rules.containsKey(data.getName())) this.facts.put(data.getName(),new ArrayList<>());
        this.facts.get(data.getName()).add(data);
    }

	private void addName(String name){
		this.names.put(name,1);
	}

	private String getBdFile(String fileName){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);

        String result = new BufferedReader(new InputStreamReader(is))
                .lines().collect(Collectors.joining("\n"));
        return result;
	}

	public boolean answer(String query) throws Exception {
	    DataElement data = new DataElement(query);
		boolean nameExist = this.names.containsKey(data.getName());
        if (!nameExist) return false;

        ArrayList<DataElement> factsToCheck = new ArrayList<>();

        if (data.isRule() && this.rules.containsKey(data.getName())){
            DataElement rule = this.rules.get(data.getName());
            factsToCheck = rule.getFacts(data);
        }
        else{
            factsToCheck.add(data);
        }
        return checkFacts(factsToCheck);

	}

	private boolean checkFacts(ArrayList<DataElement> factsToCheck){

        /*Iterator<DataElement> elementsIterator = this.elements.iterator();
        while(elementsIterator.hasNext()){
            DataElement element = elementsIterator.next();

        }*/
	    return true;
    }

}
