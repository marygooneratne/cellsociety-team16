package Configuration;

public class GeneralParse {
    private double GOFPercFireprob;
    public void startParse(String filename){
        XMLparser myParser = new XMLparser;
        myParser.parse(filename);
        String typeSimulation = myParser.getType();
        if(typeSimulation.equals("GameOfLife")||typeSimulation.equals("Percolation")||typeSimulation.equals("SpreadingFire")){
            ProbabilityParser gofParse = new ProbabilityParser();
            gofParse.parseGame(filename+".xml");
            GOFPercFireprob=gofParse.getProbPop();
        }

        else if(typeSimulation.equals("WatorWorld")){

        }

        else if(typeSimulation.equals("Segregation")){

        }

    }
}
