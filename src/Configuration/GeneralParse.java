package Configuration;

public class GeneralParse {
    private double GOFPercFireprob;
    private double probFish;
    private double probEmpty;
    private int fishTime;
    private int sharkTime;
    private int starveTime;
    private double thresh;
    private double probRed;
    private double probEmptSeg;
    private int rows;
    private int columns;

    public void startParse(String filename){
        XMLparser myParser = new XMLparser();
        myParser.parse(filename);
        rows=myParser.getRows();
        columns=myParser.getCols();
        String typeSimulation = myParser.getType();
        if(typeSimulation.equals("GameOfLife")||typeSimulation.equals("Percolation")||typeSimulation.equals("SpreadingFire")){
            ProbabilityParser gofParse = new ProbabilityParser();
            gofParse.parseGame(filename+".xml", myParser.getList());
            //double[] ret = new double[3];
            GOFPercFireprob=gofParse.getProbPop();

        }

        else if(typeSimulation.equals("WatorWorld")){
            WatorWorldParser watorParse = new WatorWorldParser();
            watorParse.parseWator(filename+".xml", myParser.getList());
            probFish=watorParse.getProbFish();
            probEmpty=watorParse.getProbEmpty();
            fishTime=watorParse.getFishTime();
            sharkTime=watorParse.getSharkTime();
            starveTime=watorParse.getStarveTime();

        }

        else if(typeSimulation.equals("Segregation")) {
            SegregationParser segParse = new SegregationParser();
            segParse.segParse(filename + ".xml",myParser.getList());
            thresh = segParse.getThresh();
            probRed = segParse.getProbRed();
            probEmptSeg = segParse.getProbEmpty();

        }
    }
    public int getRows(){
        return rows;
    }
    public int getColumns(){
        return columns;
    }
    public double getGOFPercFireprob(){
        return GOFPercFireprob;
    }




}
