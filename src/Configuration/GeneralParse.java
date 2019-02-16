package Configuration;

/*
@author - Hyunjae Lee
This is the main parser class that can parse all different types of simulation xml files.
 */

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
    private int numAgents;

/*
@param - filename
This method is the method that parses the given file and assigns the instance values with their value as given by the xml document.
 */
    public void startParse(String filename) throws BadFileInputException{
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
        else if(this.typeSimulation.equals("SugarScape")){
            SugarParser sugarParse = new SugarParser();
            sugarParse.parseSugar(filename);
            numAgents=sugarParse.getNumAgents();
        }
    }
    /*
    @returns- int
    Gets number of rows of simulation
     */
    public int getRows(){
        return rows;
    }
    /*
    @returns- int
    Gets number of columns of simulation
     */
    public int getColumns(){
        return columns;
    }
    //for Percolation
    /*
    Gets number of percolating cells
     */
    public int getNumPerc(){
        return numPerc;
    }
    /*
    Gets probability of percolated cells
     */
    public double getPercProb(){
        return percProb;
    }
    //for SpreadingFire
    /*
    Gets probability of burning cells
     */
    public double getFireProb(){
        return fireProb;
    }
    /*
    Gets number of trees
     */
    public int getNumTree(){
        return numTree;
    }
    /*
    Gets number of burned trees
     */
    public int getNumBurn(){
        return numBurn;
    }
    //for GameOfLife
    /*
    Gets probability of Game Of Life cells being alive
     */
    public double getGOFPercFireprob(){
        return GOFPercFireprob;
    }
    //for WatorWorld
    /*
    Gets probability of cell being fish
     */
    public double getProbFish(){
        return probFish;
    }
    /*
    Gets probability of cell being empty
     */
    public double getProbEmpty(){
        return probEmpty;
    }
    /*
    Gets time of each cycle for fish
     */
    public int getFishTime(){
        return fishTime;
    }
    /*
    Gets time of each cycle for shark
     */
    public int getSharkTime(){
        return sharkTime;
    }
    /*
    Gets time for starvation
     */
    public int getStarveTime(){
        return starveTime;
    }
    //for Segregation
    /*
    Gets probability of cell being empty
     */
    public double getProbEmptSeg(){
        return probEmptSeg;
    }
    /*
    Gets probability for cell to be red
     */
    public double getProbRed(){
        return probRed;
    }
    /*
    Gets threshold value for segregation
     */
    public double getThresh(){
        return thresh;
    }
    //for SugarScape]
    /*
    Gets number of agents
     */
    public int getNumAgents(){
        return numAgents;
    }
    /*
    Gets type of simulation as specified by xml file
     */
    public String getTypeSimulation(){
        return this.typeSimulation;
    }



}
