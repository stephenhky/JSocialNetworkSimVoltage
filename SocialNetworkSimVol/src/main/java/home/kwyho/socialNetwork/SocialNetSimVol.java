package home.kwyho.socialNetwork;


import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This class encapsulates the social network and methods that calculate the closeness between people.
 * This class can be inherited to have a different sets of social network, or user can import their own
 * social networks while calling the constructor.
 * 
 * @author Kwan-Yuet Ho, Ph.D.
 *
 */
public class SocialNetSimVol {
	protected DirectedSparseMultigraph<String, DirectedLink> socialNet;
	protected DijkstraShortestPath<String, DirectedLink> pathAlg;
	protected String[] nodes;
	protected DirectedLink[] edges;
	private double errTol = 1e-4;
	private int maxStep = 10000;
	
	/**
	 * Constructor. Initializes with the default set of nodes and edges.
	 */
	public SocialNetSimVol() {
		String[] nodes = {"Stephen", "Sinnie", "Zoe"};
		DirectedLink[] edges = new DirectedLink[4];
		edges[0] = new DirectedLink("1", "Stephen", "Sinnie");
		edges[1] = new DirectedLink("2", "Sinnie", "Stephen");
		edges[2] = new DirectedLink("3", "Sinnie", "Zoe");
		edges[3] = new DirectedLink("4", "Zoe", "Sinnie");
		constructSocialNetwork(nodes, edges);
		pathAlg = new DijkstraShortestPath<String, DirectedLink>(socialNet);
		setErrTol(1e-4);
		setMaxStep(10000);
	}
	
	/**
	 * Constructor. Initializes with user-given set of nodess and edges.
	 * 
	 * @param nodes People in the social network
	 * @param edges Edges that connect people in the network
	 */
	public SocialNetSimVol(String[] nodes, DirectedLink[] edges) {
		constructSocialNetwork(nodes, edges);
		pathAlg = new DijkstraShortestPath<String, DirectedLink>(socialNet);
		setErrTol(1e-4);
		setMaxStep(10000);
	}
	
	/**
	 * Initializes the social network with the given people and edges.
	 * 
	 * @param nodes People being added to the social network.
	 * @param edges Edges that connect people.
	 */
	protected void constructSocialNetwork(String[] nodes, DirectedLink[] edges) {
		this.nodes = nodes.clone();
		this.edges = edges.clone();
		socialNet = new DirectedSparseMultigraph<String, DirectedLink>();
		for (String node : this.nodes)
			socialNet.addVertex(node);
		for (DirectedLink edge : this.edges)
			socialNet.addEdge(edge, edge.getPerson1(), edge.getPerson2());
	}
	
	/**
	 * Returns the person with index <code>idx</code>.
	 * 
	 * @param idx Index of the individual
	 * @return Person with index <code>idx</code>
	 */
	public String getPerson(int idx) {
		return nodes[idx];
	}
	
	/**
	 * Returns the index of the given individual.
	 * 
	 * @param person Individual
	 * @return Index of the person.
	 */
	public int getPersonID(String person) {
		int i;
		for (i = 0; i < nodes.length; i++)
			if (nodes[i].equals(person))
				return i;
		return -1;
	}
	
	/**
	 * Returns the shortest path, in terms of a <code>List</code> of <code>DirectedLink</code>s, from
	 * <code>person1</code> to <code>person2</code>. Returns <code>null</code> if there is no path between
	 * the two people.
	 * 
	 * @param person1 Starting individual
	 * @param person2 Ending individual
	 * @return Shortest path from <code>person1</code> to <code>person2</code> in terms of <code>List<DirectedLink></code>
	 */
	public List<DirectedLink> getShortestPath(String person1, String person2) {
		return pathAlg.getPath(person1, person2);
	}
	
	/**
	 * Returns the shortest path distance from <code>person1</code> to <code>person2</code>. 
	 * Returns <code>Integer.MAX_VALUE</code> (similar to infinity) if there is no path between
	 * the two people.
	 * 
	 * @param person1 Starting individual
	 * @param person2 Ending individual
	 * @return Shortest path distance from <code>person1</code> to <code>person2</code>, <code>Integer.MAX_VALUE</code> if no path exists.
	 */
	public int getShortestPathDistance(String person1, String person2) {
		List<DirectedLink> shortestPath;
		shortestPath = getShortestPath(person1, person2);
		int pathDistance = shortestPath.size();
		if (pathDistance != 0)
			return pathDistance;
		else if (person1.equals(person2))
			return 0;
		else
			return Integer.MAX_VALUE;
	}

	/**
	 * Check if the existence of <code>person</code> in the social network would affect
	 * the closeness from <code>person1</code> to <code>person2</code>.
	 * 
	 * @param person The person we want to check if it is relevant to the path between <code>person1</code> and <code>person2</code>
	 * @param person1 Starting individual
	 * @param person2 Ending individual
	 * @return <code>true</code> if relevant, and <code>false</code> if irrelevant
	 */
	protected boolean checkPersonIrrelevant(String person, String person1, String person2) {
		List<DirectedLink> path1, path2;
		List<String> path1nodes = new ArrayList<String>();
		List<String> path2nodes = new ArrayList<String>();
		path1 = getShortestPath(person1, person);
		path2 = getShortestPath(person, person2);
		
		path1nodes.add(path1.get(0).getPerson1());
		for (int i = 0; i < path1.size(); i++)
			path1nodes.add(path1.get(i).getPerson2());
		path2nodes.add(path2.get(0).getPerson1());
		for (int i = 0; i < path2.size(); i++)
			path2nodes.add(path2.get(i).getPerson2());
		//System.out.println(path1nodes);
		//System.out.println(path2nodes);
		path1nodes.retainAll(path2nodes);
		//System.out.println(path1nodes);
		
		return (path1nodes.size() != 1);
	}
	
	/**
	 * Calculates the closeness from <code>person1</code> to <code>person2</code>, using analogy of electrical
	 * resistance in physics.
	 * 
	 * @param person1 Starting individual
	 * @param person2 Ending individual
	 * @return Closeness from <code>person1</code> to <code>person2</code>
	 */
	public double getResistance(String person1, String person2) {
		double[] volDict = new double[nodes.length];
		double[] tempVolDict = new double[nodes.length];
		int word1idx = getPersonID(person1);
		int word2idx = getPersonID(person2);
		int i;
		int step = 0;
		boolean converged = false;
		
		if (person1 == person2)
			return 0.0;
		if (getShortestPathDistance(person1, person2) == Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		for (i = 0; i < nodes.length; i++)
			if (i == word1idx)
				volDict[i] = 1.0;
			else if (i==word2idx)
				volDict[i] = 0.0;
			else if (checkPersonIrrelevant(getPerson(i), person1, person2))
				volDict[i] = 10.0;
			else {
				int dist1 = getShortestPathDistance(person1, getPerson(i));
				int dist2 = getShortestPathDistance(getPerson(i), person2);
				volDict[i] = (double)dist2/(dist1 + dist2);
			}
		tempVolDict = volDict.clone();

		while ((!converged) && (step < getMaxStep())) {
			boolean tempConverged = true;
			for (i = 0; i < nodes.length; i++) {
				if (i == word1idx)
					tempVolDict[i] = 1.0;
				else if (i == word2idx)
					tempVolDict[i] = 0.0;
				else if ((volDict[i] < 0.0)||(volDict[i] > 1.0))
					tempVolDict[i] = 10.0;
				else {
					Object[] predNodes = socialNet.getPredecessors(getPerson(i)).toArray();
					Object[] succNodes = socialNet.getSuccessors(getPerson(i)).toArray();
					double inCurrent = 0.0;
					double outCurrent = 0.0;
					for (int j = 0; j < predNodes.length; j++) {
						int predidx = getPersonID((String)predNodes[j]);
						if ((volDict[predidx] >= 0.0)&&(volDict[predidx] <= 1.0))
							if (volDict[predidx] > volDict[i])
								inCurrent += volDict[predidx] - volDict[i];
					}
					for (int j = 0; j < succNodes.length; j++) {
						int succidx = getPersonID((String)succNodes[j]);
						if ((volDict[succidx] >= 0.0)&&(volDict[succidx] <= 1.0))
							if (volDict[i] > volDict[succidx])
								outCurrent += volDict[i] - volDict[succidx];
					}
					if (Math.abs(inCurrent-outCurrent) > getErrTol()) {
						double sumVol = 0.0;
						int numNodes = 0; 
						for (int j = 0; j < predNodes.length; j++) {
							int predidx = getPersonID((String)predNodes[j]);
							if ((volDict[predidx] >= 0.0)&&(volDict[predidx] <= 1.0))
								if (volDict[predidx] > volDict[i]) {
									sumVol += volDict[predidx];
									numNodes++;
								}
						}
						for (int j = 0; j < succNodes.length; j++) {
							int succidx = getPersonID((String)succNodes[j]);
							if ((volDict[succidx] >= 0.0)&&(volDict[succidx] <= 1.0))
								if (volDict[i] > volDict[succidx]) {
									sumVol += volDict[succidx];
									numNodes++;
								}
						}
						if (numNodes == 0)
							tempVolDict[i] = 0.0;
						else
							tempVolDict[i] = sumVol / numNodes;
						tempConverged = false;
					} else
						tempConverged = tempConverged && true;
				}
			}
			converged = tempConverged;
			volDict = tempVolDict.clone();
			step++;
		}

		double startCurrent = 0.0;
		Collection<String> rootSucc = socialNet.getSuccessors(person1);
		Iterator<String> rootSuccIter = rootSucc.iterator(); 
		while (rootSuccIter.hasNext()) {
			String thisSuccNode = rootSuccIter.next();
			int thisSuccNodeIdx = getPersonID(thisSuccNode);
			if (volDict[thisSuccNodeIdx] <= 1.0)
				startCurrent += (1.0 - volDict[thisSuccNodeIdx]);
		}
		return (1.0 / startCurrent);
	}
	
	/**
	 * Sets the tolerance of error. This defines the stopping
	 * condition of the numerical computation.
	 * 
	 * @param errTol Error tolerance of current
	 */
	public void setErrTol(double errTol) {
		this.errTol = errTol;
	}
	
	/**
	 * Retrieves the tolerance of error. This defines one of 
	 * the stopping condition of the numerical computations.
	 * 
	 * @return Error tolerance of current
	 */
	public double getErrTol() {
		return errTol;
	}
	
	/**
	 * Sets the maximum number of steps for numerical computation. This defines one of
	 * the stopping condition of the numerical computation.
	 * 
	 * @param maxStep Maximum number of steps
	 */
	public void setMaxStep(int maxStep) {
		this.maxStep = maxStep;
	}
	
	/**
	 * Retrieves the maximum number of steps for numerical computation. This defines one of
	 * the stopping condition of the numerical computation.
	 * 
	 * @return Maximum number of steps
	 */
	public int getMaxStep() {
		return maxStep;
	}
	
	/**
	 * Gets the total number of people in the social network.
	 * 
	 * @return Total number of people in the social network
	 */
	public int getNumOfPeople() {
		return nodes.length;
	}
	
	/**
	 * Gets an array of people in the social network.
	 * 
	 * @return An array of people in the social network (in <code>String[]</code>)
	 */
	public String[] getRosterList() {
		return nodes;
	}
	
	/**
	 * Gets the social network graph.
	 * 
	 * @return Social network graph (in <code>Graph&lt;String, DirectedLink&gt;</code>)
	 */
	public Graph<String, DirectedLink> getSocialNet() {
		return socialNet;
	}
}
