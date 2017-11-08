package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;

/**
 * Created by alan on 05/11/17.
 */
public class DataElement {
    private String text;
    private String name;
    private boolean isRule=false;

    public DataElement(String data){
        this.text = String.join("",data.split(" ")).replace(".","");
        this.name = data.split("\\(")[0].replace(" ","");
        this.checkIfRule();
    }
    /*
    * Only works for DataBase not for query
    * */
    private void checkIfRule(){
        String[] separated = this.text.split(":-");
        this.isRule = separated.length > 1;
    }

    public boolean isRule() {
        return this.isRule;
    }

    public ArrayList<DataElement> getFacts(DataElement query){
        ArrayList<DataElement> factsToCheck = new ArrayList();
        System.out.println(query.getText()+" CON "+this.text);
        String[] queryParams = query.getParams(query.getText());
        String[] dataList = this.text.split(":-");
        String[] dataParams = this.getParams(dataList[0]);
        String[] notReadyFacts = dataList[1].replace("),",")-").split("-");

        for (int i=0;i<notReadyFacts.length;i++){
            for (int j=0;j<dataParams.length;j++){
                notReadyFacts[i]=notReadyFacts[i].replace(dataParams[j],queryParams[j]);
            }
            factsToCheck.add(new DataElement(notReadyFacts[i]));
        }
        return factsToCheck;
    }

    /*
    *   in : hijo(varon,pepe)
    *   out: [varon,pepe]
     */
    private String[] getParams(String text){
        String params = text.split("\\(")[1].replace(")","");
        return params.split(",");
    }

    public void changeToRule(){
        this.isRule=true;
    }

    public String getName(){
        return this.name;
    }

    public String getText(){
        return this.text;
    }

    public boolean isEqual(DataElement query){
        return query.getText().equals(this.text);
    }

    public boolean incorrect(){
        //ToDo
        return false;
    }

}

