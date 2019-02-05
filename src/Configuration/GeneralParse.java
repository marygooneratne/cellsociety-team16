package Configuration;

public class GeneralParse {
    private double GOFPercFireprob;
    private double fireProb;
    private int numTree;
    private int numBurn;
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
    private int numPerc;
    private double percProb;

    public void startParse(String filename){
        XMLparser myParser = new XMLparser();
        myParser.parse(filename);
        rows=myParser.getRows();
        columns=myParser.getCols();
        this.typeSimulation = myParser.getType();
        if(this.typeSimulation.equals("GameOfLife")){
            ProbabilityParser gofParse = new ProbabilityParser();
            gofParse.parseGame(filename, myParser.getList());
            //double[] ret = new double[3];
            GOFPercFireprob=gofParse.getProbPop();

        }
        else if(this.typeSimulation.equals("SpreadingFire")){
            FireParser myFireParse = new FireParser();
            myFireParse.fireParse(filename);
            fireProb=myFireParse.getProb();
            numTree=myFireParse.getNumTree();
            numBurn=myFireParse.getNumBurn();
            System.out.println(fireProb);
        }
        else if(this.typeSimulation.equals("Percolation")){
            PercolationParser percParse = new PercolationParser();
            percParse.percParse(filename);
            numPerc=percParse.getNumPerc();
            percProb=percParse.getProb();

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
    //for Percolation
    public int getNumPerc(){
        return numPerc;
    }
    public double getPercProb(){
        return percProb;
    }
    //for SpreadingFire
    public double getFireProb(){
        return fireProb;
    }
    public int getNumTree(){
        return numTree;
    }
    public int getNumBurn(){
        return numBurn;
    }
    //for GameOfLife
    public double getGOFPercFireprob(){
        return GOFPercFireprob;
    }
    //for WatorWorld
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
    //for Segregation
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
