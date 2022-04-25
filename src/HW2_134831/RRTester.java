package HW2_134831;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * pseudo-code:
 * input: a file with the quantum length and each process's execution time
 * (burst time)
 * output: table with the path of the processes, each process's execution time,
 * its turn around time and its waiting time and the average of both.
 * 
 * steps:
 * - creating class process
 * - initializing the instance variables of the process
 * - creating the constructor of the process
 * - creating a method that sets the execution time
 * - creating a method that gets the arrival time
 * - creating a method that gets the old execution time (before change)
 * - creating a method that gets the id of the process
 * - creating a method that gets the execution time
 * - creating a method that sets the completion time
 * - creating a method that gets the completion time
 * - creating RRSchedule class
 * - initializing the instance variables of the RRSchedule object
 * - creating a method that adds a process to the path list
 * - creating a method that pops a process from the processes list
 * - creating a method that push a process to the processes list
 * - creating a method that calculates the turnaround time
 * - creating a method that calculates the waiting time
 * - creating a method that runs the RR Scheduling
 * - creating a method that prints a table with all the needed info
 * - creating a tester class
 * - reading a file that is named "input.txt"
 * - using a scanner to read the file
 * - creating 2 arraylists to store the processes
 * - creating an arraylist to store the arrival times
 * - the first line will always have the quantum length, the rest will always be
 * the processes
 * - splitting each line into an array, first index will always be the arrival
 * and the second will be execution time
 * - creating process object based on the values
 * - creating 2 RRSchedule objects, one to run the RR and the other to print them
 */

// creating a tester class
public class RRTester {
    public static void main(String[] args) throws FileNotFoundException {
        // reading a file that is named "input.txt"
        File file = new File("input.txt");
        // using a scanner to read the file
        Scanner inFile = new Scanner(file);

        // creating 2 arraylists to store the processes
        ArrayList<Process> exeValues = new ArrayList<>();
        ArrayList<Process> exeValues2 = new ArrayList<>();
        // creating an arraylist to store the arrival times
        ArrayList<Integer> arrivalNums = new ArrayList<>();

        int quantum = 0;

        while (inFile.hasNextLine()) {
            int exeTime = 0;
            int arrival = 0;

            // initializing the line
            String line = inFile.nextLine();
            // splitting the line
            String[] splitValues = line.split(" ");
            // if the line splitted has one element
            if (splitValues.length == 1) {
                // the first line will always have the quantum length
                quantum = Integer.parseInt(splitValues[0]);

                // the rest will always be the processes
            } else {
                //converting each value to be Integer
                arrival = Integer.parseInt(splitValues[0]);
                exeTime = Integer.parseInt(splitValues[1]);
                // /creating process object based on the values
                Process p = new Process(exeTime, arrival);
                arrivalNums.add(arrival);
                exeValues.add(p);
                exeValues2.add(p);
            }
        }

        //creating 2 RRSchedule objects
        //one to print the RR output
        RRSchedule sched = new RRSchedule(exeValues, quantum);
        //and the other to run the RR itself
        RRSchedule sched2 = new RRSchedule(exeValues2, quantum);

        sched2.RunRR();
        sched.printPerformance();
        inFile.close(); //closing the file

    }
}
