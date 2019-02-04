package Configuration;

public class GeneralParse {
    private double GOFprob;
    public startParse(String filename){
        XMLparser myParser = new XMLparser;
        myParser.parse(filename);
        String typeSimulation = myParser.getType();
        if(typeSimulation.equals("GameOfLife")){
            GameOfLifeParser gofParse = new GameOfLifeParser();
            gofParse.parseGame(filename+".xml");
            GOFprob=gofParse.getProbPop();
        }
        else if(typeSimulation.equals("SpreadingFire")){

        }
        else if(typeSimulation.equals("WaterWorld")){

        }
        else if(typeSimulation.equals("Percolation")){

        }
        else if(typeSimulation.equals("Segregation")){

        }

    }
}
