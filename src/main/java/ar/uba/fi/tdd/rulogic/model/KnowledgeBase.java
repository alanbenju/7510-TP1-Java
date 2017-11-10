package ar.uba.fi.tdd.rulogic.model;

public class KnowledgeBase {

    private FileReader fileReader;

    private DataBase dataBase;

	public KnowledgeBase(){
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
		for (int i = 0;i < dbList.length;i++){
		    dataBase.add(dbList[i]);
		}
	}


	public boolean answer(String query) {
	    this.dataBase.newQuery(query);
        boolean valid = this.dataBase.isQueryValid();
        if (!valid) return false;
        return this.dataBase.existFactsFromQuery();

	}
}
