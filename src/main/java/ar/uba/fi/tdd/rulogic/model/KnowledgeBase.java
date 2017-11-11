package ar.uba.fi.tdd.rulogic.model;

import ar.uba.fi.tdd.rulogic.exceptions.InvalidDataException;
import ar.uba.fi.tdd.rulogic.exceptions.InvalidQueryException;

public class KnowledgeBase {

    private FileReader fileReader;

    private DataBase dataBase;

	public KnowledgeBase(String filename) throws InvalidDataException {
        fileReader = new FileReader(filename);
        dataBase = new DataBase();
        parseDB();
    }

	private void parseDB() throws InvalidDataException {
		String[] dbList = fileReader.getDataFromFile();
		for (int i = 0;i < dbList.length;i++){
		    dataBase.add(dbList[i]);
		}
	}


	public boolean answer(String query) throws InvalidQueryException {
		this.dataBase.newQuery(query);
		boolean valid = this.dataBase.isQueryValid();
        if (!valid) return false;
        return this.dataBase.existFactsFromQuery();

	}
}
