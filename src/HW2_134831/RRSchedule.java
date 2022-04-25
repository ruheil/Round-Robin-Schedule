package HW2_134831;

import java.util.ArrayList;

public class RRSchedule {
    //initializing the instance variables of the process
    private ArrayList<Process> processes;
    private static ArrayList<Process> listOfProcesses = new ArrayList<>();
    private int counter = 0; //to track the time
    private int quantum;

    //initializing the instance variables of the RRSchedule object
    /**
     * constructs an RRSchedule object
     * @param processes arraylist with process objects
     * @param quantum the quantum length
     */
    public RRSchedule(ArrayList<Process> processes, int quantum) {
        this.processes = processes;
        this.quantum = quantum;
    }

    //creating a method that adds a process to the path list
    /**
     * adds the process to the list of path
     * @param p the process that is wanted to be added
     */
    private void setProcesses(Process p) {
        listOfProcesses.add(p);
    }

    //creating a method that pops a process from the processes
    /**
     * removes the process from the list of processes
     */
    private void Pop() {
        processes.remove(0);
    }

    //creating a method that push a process to the processes list
    /**
     * push the process to the list of processes
     * @param p the process wanted to be pushed
     */
    private void Push(Process p) {
        processes.add(p);
    }

    //creating a method that calculates the turnaround time
    /**
     * calculates the process's turnaround time
     * @param p the process that is wanted to get its turnaround time
     * @return the turnaround time of the process
     */
    private int calcTurnaround(Process p) {
        //completion - arrival = turnaround
        return p.getComplete() - p.getArrival();
    }

    //creating a method that calculates the waiting time
    /**
     * calculates the process's waiting time
     * @param p the process (from the path) that is wanted to get its turnaround time
     * @param p2 the process (from the list of processes) that is wanted to get its turnaround time
     * @return the waiting time of the process
     */
    private int calcWaiting(Process p, Process p2) {
        return calcTurnaround(p) - p2.getOldExe();
    }

    //creating a method that runs the RR Scheduling
    /**
     * executes the class by pushing and popping the processes' list
     */
    public void RunRR() {
        while (processes.size() > 0) {
            Process thatProcess = processes.get(0); //shortcut to call element with 0 index in the process list
            //if the process has a remaining time to be executed
            if (thatProcess.getExeTime() > quantum) {
                thatProcess.setExeTime(thatProcess.getExeTime() - quantum);
                counter += quantum;
                thatProcess.setComplete(counter);
                setProcesses(thatProcess);
                Pop();
                Push(thatProcess);
            //if the process equals the quantum length exactly
            } else if (thatProcess.getExeTime() == quantum) {
                thatProcess.setExeTime(0);
                counter += quantum;
                thatProcess.setComplete(counter);
                setProcesses(thatProcess);
                Pop();
            //other than that
            } else {
                counter += thatProcess.getExeTime();
                thatProcess.setExeTime(0);
                thatProcess.setComplete(counter);
                setProcesses(thatProcess);
                Pop();
            }
        }

    }

    //creating a method that prints a table with all the needed info
    /**
     * prints a table containing the required information
     */
    public void printPerformance() {

        ArrayList<Integer> turnarounds = new ArrayList<>(); //arraylist that stores all the turnaround times of all processes   
        ArrayList<Integer> waitings = new ArrayList<>(); //arraylist that stores waiting times of all processes

        System.out.println("Round-Robin Scheduler");
        System.out.println("*".repeat(25));
        System.out.println("The processes will run in order as following:");
        //for each element in the path list:
        for (Process element : listOfProcesses)
            System.out.print(element.getID() + "        ");
        System.out.println("");
        System.out.println("*".repeat(35));
        System.out.printf("%-7s %-25s %-30s %-25s\n", "PID", "Execution time", "Turnaround Time", "Waiting Time");
        for (int i = 0; i < processes.size(); i++) {
            System.out.printf("%-7s %-25d %-30d %-25d\n", processes.get(i).getID(), processes.get(i).getOldExe(),
                    calcTurnaround(listOfProcesses.get(i)), calcWaiting(listOfProcesses.get(i), processes.get(i)));
            waitings.add(calcWaiting(listOfProcesses.get(i), processes.get(i))); //adds the waiting time of process with index i to the arraylist
            turnarounds.add(calcTurnaround(listOfProcesses.get(i))); //adds the turnaround time of process with index i to the arraylist
        }
        System.out.println("*".repeat(80));
        double sumTurn = 0; //initializing a variable to store the sum of the turnaround times
        double sumWait = 0; //initializing a variable to store the sum of the waiting times
        for (int i = 0; i < processes.size(); i++) {
            sumTurn += turnarounds.get(i);
            sumWait += waitings.get(i);
        }

        double avgTurn = sumTurn / turnarounds.size(); //gets the average of the turnaround times
        double avgWait = sumWait / waitings.size(); //gets the average of the waiting times

        System.out.printf("%s %.2f\n", "Average Waiting time: ", avgWait);
        System.out.printf("%s %.2f\n", "Average Turnaround time: ", avgTurn);

        System.out.println("*".repeat(80));

    }
}
