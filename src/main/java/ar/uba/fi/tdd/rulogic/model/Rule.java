package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;

/**
 * Created by alan on 09/11/17.
 */
public class Rule extends DataElement {
    public Rule(String data) {
        super(data);
    }

    public boolean isRule() {return true;}

    public boolean isValid(){
        return parser.isValidRule(this.text);
    }

    /*
    *   in : hijo(varon,pepe)
    *   out: [varon,pepe]
     */
    public ArrayList<Fact> getFacts(DataElement query){
        ArrayList<Fact> factsToCheck = new ArrayList();
        String[] queryParams = query.getParams(query.getText());
        String[] dataList = this.text.split(":-");
        String[] dataParams = this.getParams(dataList[0]);
        String[] notReadyFacts = dataList[1].replace("),",")-").split("-");
        if(queryParams.length==dataParams.length){
            for (int i=0;i<notReadyFacts.length;i++){
                for (int j=0;j<dataParams.length;j++){
                    notReadyFacts[i]=notReadyFacts[i].replace(dataParams[j],queryParams[j]);
                }
                factsToCheck.add(new Fact(notReadyFacts[i]));
            }
        }
        return factsToCheck;
    }


}
