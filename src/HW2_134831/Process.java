package HW2_134831;

//creating class process
public class Process {
    // initializing the instance variables of the process
    private static int nextID = 0; // id that is unique for every object
    private int ID = 0; // initial id equals 0
    private int exeTime;
    private int completeTime;
    private int OLD_EXE;
    private int arrival;

    // creating the constructor of the process
    /**
     * constructs a process object
     * 
     * @param exeTime the execution (burst) time of the process
     * @param arrival the arrival time of the process
     */
    public Process(int exeTime, int arrival) {
        ID = nextID;
        nextID++; // increments the id
        this.exeTime = exeTime;
        this.OLD_EXE = exeTime;
        this.arrival = arrival;
    }

    // creating a method that sets the execution time
    /**
     * sets the execution time of a process
     * 
     * @param exeTime the execution time that is wanted to be set to the process
     */
    public void setExeTime(int exeTime) {
        this.exeTime = exeTime;
    }

    // creating a method that gets the arrival time
    /**
     * gets the arrival time of the process
     * 
     * @return the arrival time
     */
    public int getArrival() {
        return arrival;
    }

    // creating a method that gets the old execution time (before change)
    /**
     * gets the first execution time that has been set to a process
     * 
     * @return the old execution time
     */
    public int getOldExe() {
        return OLD_EXE;
    }

    // creating a method that gets the id of the process
    /**
     * gets the ID of the process
     * 
     * @return the ID formatted as "Pn" where n is an integer
     */
    public String getID() {
        return "P" + ID;
    }

    // creating a method that gets the execution time
    /**
     * gets the current execution (burst) of a process
     * @return the current execution time
     */
    public int getExeTime() {
        return exeTime;
    }

    /**
     * sets the complete time of a process
     * @param completeTime the completion time that is wanted to be set to the process
     */
    // creating a method that sets the completion time
    public void setComplete(int completeTime) {
        this.completeTime = completeTime;
    }

    // creating a method that gets the completion time
    /**
     * gets the completion time of the process
     * @return the completion time
     */
    public int getComplete() {
        return completeTime;
    }
}
