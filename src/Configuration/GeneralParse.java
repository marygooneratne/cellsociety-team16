package Configuration;

public class GeneralParse {
    private double GOFPercFireprob;
    private double probFish;
    private double probEmpty;
    private int fishTime;
    private int sharkTime;
    private int starveTime;

    public void startParse(String filename){
        XMLparser myParser = new XMLparser();
        myParser.parse(filename);
        String typeSimulation = myParser.getType();
        if(typeSimulation.equals("GameOfLife")||typeSimulation.equals("Percolation")||typeSimulation.equals("SpreadingFire")){
            ProbabilityParser gofParse = new ProbabilityParser();
            gofParse.parseGame(filename+".xml");
            GOFPercFireprob=gofParse.getProbPop();
        }

        else if(typeSimulation.equals("WatorWorld")){
            WatorWorldParser watorParse = new WatorWorldParser();
            watorParse.parseWator(filename+".xml");
            probFish=watorParse.getProbFish();
            probEmpty=watorParse.getProbEmpty();
            fishTime=watorParse.getFishTime();
            sharkTime=watorParse.getSharkTime();
            starveTime=watorParse.getStarveTime();
        }

        else if(typeSimulation.equals("Segregation")){

        }

    }
}
