package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.stream.Collectors;



public class KnowledgeBase {
	private Hashtable<String, ArrayList<DataElement>> facts;
	private Hashtable<String, ArrayList<DataElement>> rules;
    private ArrayList<DataElement> elements;

	public KnowledgeBase(){
		facts = new Hashtable();
		rules = new Hashtable();
		parseDB();
	}

	private void parseDB(){
		String db = getBdFile("rules.db");
        String[] dbList = db.split("\\./n");
		for (int i = 0;i < dbList.length;i++){
            elements.add(new DataElement(dbList[i]));
		}
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
        DataElement[] factsToCheck = data.getFacts();
        /*
        /*recorrer factsToCheck y chequear con la base de datos
         */
        /*Iterator<String> nombreIterator = nombreArrayList.iterator();
        while(nombreIterator.hasNext()){
            String elemento = nombreIterator.next();
            System.out.print(elemento+" / ");
        }*/

	    return true;
	}

}
