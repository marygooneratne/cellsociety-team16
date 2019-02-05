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
    private String typeSimulation;

    public void startParse(String filename){
        XMLparser myParser = new XMLparser();
        myParser.parse(filename);
        rows=myParser.getRows();
        columns=myParser.getCols();
        this.typeSimulation = myParser.getType();
        if(this.typeSimulation.equals("GameOfLife")||this.typeSimulation.equals("Percolation")||this.typeSimulation.equals("SpreadingFire")){
            ProbabilityParser gofParse = new ProbabilityParser();
            gofParse.parseGame(filename, myParser.getList());
            //double[] ret = new double[3];
            GOFPercFireprob=gofParse.getProbPop();

        }

        else if(this.typeSimulation.equals("WatorWorld")) {
            WatorWorldParser watorParse = new WatorWorldParser();


            watorParse.parseWator(filename, myParser.getList());
            probFish = watorParse.getProbFish();
            probEmpty = watorParse.getProbEmpty();
            fishTime = watorParse.getFishTime();
            sharkTime = watorParse.getSharkTime();
            starveTime = watorParse.getStarveTime();


//            watorParse.parseWator(filename + ".xml", myParser.getList());
//            probFish = watorParse.getProbFish();
//            probEmpty = watorParse.getProbEmpty();
//            fishTime = watorParse.getFishTime();
//            sharkTime = watorParse.getSharkTime();
//            starveTime = watorParse.getStarveTime();

//        }
        }

        else if(this.typeSimulation.equals("Segregation")) {
            SegregationParser segParse = new SegregationParser();
            segParse.segParse(filename,myParser.getList());
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
    public double getProbFish(){
        return probFish;
    }
    public double getProbEmpty(){
        return probEmpty;
    }
    public int getFishTime(){
        return fishTime;
    }
    public int getSharkTime(){
        return sharkTime;
    }
    public int getStarveTime(){
        return starveTime;
    }
    public double getProbEmptSeg(){
        return probEmptSeg;
    }
    public double getProbRed(){
        return probRed;
    }
    public double getThresh(){
        return thresh;
    }

    public String getTypeSimulation(){
        return this.typeSimulation;
    }



}
