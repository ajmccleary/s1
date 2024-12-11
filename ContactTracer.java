/**************************
* Author: Christian Duncan (Starting Code)
* Modified by: <TEAM MEMBERS>
* 
* Fall 2024, CSC215
* Given a graph of contact points between individuals, a list of infected individuals, 
* and a distance D, determine how many individuals are with D contacts of an infected individual.
*/

import java.io.*;
import java.util.*;
 
public class ContactTracer {
    public static final String DEFAULT_NAME = "simple0.input";

    //maps name to node number
    public static HashMap<String, Integer> idMap = new HashMap<String, Integer>();

    //maps node number to list of connected nodes
    public static HashMap<Integer, ArrayList<Integer>> graphMap = new HashMap<Integer, ArrayList<Integer>>();

    //keeps track of which nodes already visited
    public static ArrayList<Integer> visitedList = new ArrayList<Integer>();

    //keeps track of number of sickos
    public static int sickos = 0;
 
    public static void main(String[] args) {
        String fileName = DEFAULT_NAME;
 
        if (args.length >= 1) {
            fileName = args[0];
        }
 
        processFile(fileName);
    }
 
    // Read in the problem and produce the output
    public static void processFile(String fileName) {
        try {
            String[] line;
 
            // Open up the file for parsing
            Scanner sc = new Scanner(new FileReader(fileName));
 
            //add patient 0 to idMap for future usage
            idMap.put("OG", -1);

            // Get the number of names (IDs)
            int n = Integer.parseInt(sc.nextLine());

            for (int i = 0; i < n; i++) {
                String id = sc.nextLine();
                //System.out.println("DEBUG: Node " + i + ": ID=" + id);
 
                // You will want to store this ID. 
                // Using a Hashmap, I would map ID to i, call it the id number.
                idMap.put(id, i);
            }
 
            // You will probably want to create an undirected graph G with n nodes
            // Initially with no edges but add a method to add an edge between two nodes
 
            // Get the various connections
            int m = Integer.parseInt(sc.nextLine());
 
            for (int e = 0; e < m; e++) {
                line = sc.nextLine().split(" ");
                String idA = line[0];
                String idB = line[1];
 
                //System.out.println("DEBUG: Contact between " + idA + " and " + idB);
                // You might want to get the id number of idA and idB from the Hashmap
                // Then add the edge between idA and idB to the graph
                graphMap.get(idMap.get(idA)).add(idMap.get(idB));
                graphMap.get(idMap.get(idB)).add(idMap.get(idA));
            }
 
            // Get how many contacts have been infected and how far to report exposure from
            // an infected individual
            line = sc.nextLine().split(" ");
            int numInfected = Integer.parseInt(line[0]);
            int distance = Integer.parseInt(line[1]);
 
            ArrayList<String> infected = new ArrayList<String>(numInfected);
            for (int c = 0; c < numInfected; c++) {
                String idA = sc.nextLine();
                System.out.println("DEBUG: Infected: " + idA);
                infected.add(idA);

                //add both infecteds as contacts of patient 0
                graphMap.get(idMap.get("OG")).add(idMap.get(idA));
            }
 
            // Now process the information to get the results...
            // Use the Graph, infected list, and distance to get the result and print the number of
            // exposed individuals.            

            for (int runs = 0; runs <= distance; runs++) { //for depth
                for (int contact : graphMap.get(idMap.get("OG"))) { //for each contact of patient 0
                    if (!visitedList.contains(contact)) {
                        visitedList.add(contact);
                        sickos++;
                    }
                }
            }

            sc.close();
 
         } catch (IOException e) {
             System.err.println("Error reading in the graph: " + e.getMessage());
         }
     }
 }
 