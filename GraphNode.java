/*
 * Needed Functionality
 *  - Needs to know which nodes are linked, and their respective values (distance d)
 *  - Needs to store it's ID
 *  - Needs to be able to access it's ID in other classes
 *  - Rather than directly going down the chain to figure out connections, it seems significantly 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GraphNode {
    private ArrayList<GraphNode> connectedNodes;
    //private HashMap connectedNodes; 
    private String ID;
    private int nodeNum;
    private boolean isInfected; 
    public GraphNode(String ID, int nodeNum, boolean isInfected) {
        this.ID = ID;
        this.nodeNum = nodeNum; 
        this.isInfected = isInfected;
        //connectedNodes = new HashMap(); 
        connectedNodes = new ArrayList<GraphNode>();
    }
    public void addNewConnection(GraphNode node/* , int d*/) {
        //connectedNodes.put(node, d);
        connectedNodes.add(node);
    }
    public String getID() {
        return this.ID; 
    }
    public int getNum() {
        return this.nodeNum; 
    }
    public ArrayList<GraphNode> getConnections() {
        return connectedNodes; 
    }
    public ArrayList<GraphNode> getPotentialContacts(ArrayList<GraphNode> conList, int depth, HashSet<GraphNode> visited) {
        ArrayList<GraphNode> tempList = new ArrayList<GraphNode>(); 
        if(depth > 0) { 
            for(GraphNode node : conList) {
                if(!visited.contains(node)) {
                    visited.add(node);
                    tempList.add(node); 
                    tempList.addAll(getPotentialContacts(node.getConnections(), depth-1, visited)); //Goes through each connected nodes connected peoples lists, checking down to the necessary depth, then appends the arraylist onto the current arraylist, keeps sending that up the chain until completed
                }
            }
        }
        return tempList;
    }
}