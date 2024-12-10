/*
 * Needed Functionality
 *  - Needs to know which nodes are linked, and their respective values (distance d)
 *  - Needs to store it's ID
 *  - Needs to be able to access it's ID in other classes
 *  - Rather than directly going down the chain to figure out connections, it seems significantly 
 */
import java.util.ArrayList;
import java.util.HashMap;

public class GraphNode {
    private ArrayList<GraphNode> connectedNodes;
    //private HashMap connectedNodes; 
    private String ID;
    private boolean isInfected; 
    public GraphNode(String ID, boolean isInfected) {
        this.ID = ID;
        this.isInfected = isInfected; 
        //connectedNodes = new HashMap(); 
        connectedNodes = new ArrayList();
    }
    public void addNewConnection(GraphNode node/* , int d*/) {
        //connectedNodes.put(node, d);
        connectedNodes.add(node);
    }
    public String getID() {
        return this.ID; 
    }
    public ArrayList getConnections() {
        return connectedNodes; 
    }
    public ArrayList getPotentialContacts(ArrayList conList, int depth) {
        ArrayList<GraphNode> tempList = new ArrayList(); 
        if(depth != 0) { 
            for(int i = 0; i<conList.size(); i++) {
                tempList.addAll(getPotentialContacts(conList, depth-1)); //Goes through each connected nodes connected peoples lists, checking down to the necessary depth
            }
        }
        return tempList;
    }
}