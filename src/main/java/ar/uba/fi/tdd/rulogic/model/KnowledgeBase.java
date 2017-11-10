package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.stream.Collectors;



public class KnowledgeBase {
	private Hashtable<String, ArrayList<DataElement>> facts;
	private Hashtable<String, DataElement> rules;
	private Hashtable<String, Integer> names;

    private FileReader fileReader;

    private DataBase dataBase;

	public KnowledgeBase(){
		facts = new Hashtable();
		rules = new Hashtable();
        names = new Hashtable();
        fileReader = new FileReader();
        dataBase = new DataBase();
        parseDB();
        /*try {
            parseDB();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

	private void parseDB() /*throws Exception*/ {
		String[] dbList = fileReader.getDataFromFile("rules.db");
        DataElement data;
		for (int i = 0;i < dbList.length;i++){
            //data = ;
		    dataBase.add(new DataElement(dbList[i]));
            if (data.incorrect()){
                //throw new Exception("Incorrect database: "+dbList[i]);
            }
            if (data.isRule()) this.rules.put(data.getName(),data);
            else this.addFact(data);
			this.addName(data.getName());
		}
	}


	private void addFact(DataElement data){
	    //CREO QUE ESTOY HACIEDNO MAL STO
	    if (!this.facts.containsKey(data.getName())) this.facts.put(data.getName(),new ArrayList());

        ArrayList factsArray = this.facts.get(data.getName());
        factsArray.add(data);
        this.facts.put(data.getName(),factsArray);
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

	public boolean answer(String query) {
	    DataElement data = new DataElement(query);
        if (!this.names.containsKey(data.getName())) return false;
        if (this.rules.containsKey(data.getName())) data.changeToRule();


        /*Set<String> keys = this.names.keySet();
        Iterator<String> itr = keys.iterator();

        while (itr.hasNext()) {
            // Getting Key
            String str = itr.next();
            System.out.println("Key: "+str+" & Value: "+this.names.get(str));
        }*/




        ArrayList<DataElement> factsToCheck = new ArrayList();
        if (data.isRule()){
            DataElement rule = this.rules.get(data.getName());
            factsToCheck = rule.getFacts(data);
        }
        else{
            factsToCheck.add(data);
        }
        return checkFacts(factsToCheck);
	}

	private boolean checkFacts(ArrayList<DataElement> factsToCheck){

        Iterator<DataElement> elementsIterator = factsToCheck.iterator();
        boolean result = true;
        while(elementsIterator.hasNext() && result){
            DataElement element = elementsIterator.next();
            result = this.checkOneFact(element);
        }

        //System.out.println("FIN "+result);
	    return result;
    }


    private boolean checkOneFact(DataElement element){
        ArrayList<DataElement> posibleFacts = this.facts.get(element.getName());
        Iterator<DataElement> elementsIterator = posibleFacts.iterator();
        boolean result = false;
        while(elementsIterator.hasNext() && !result){
            DataElement posibleElement = elementsIterator.next();
            //System.out.println("CHECK "+posibleElement.getText()+" "+element.getText());
            result = posibleElement.isEqual(element);
            //System.out.println(result);
        }
        //System.out.println("SALGO");
        return result;
    }

}
