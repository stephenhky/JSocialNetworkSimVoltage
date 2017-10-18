package home.kwyho.socialNetwork;


import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;

/**
 * This class inherits from the class <code>WordNetSimVol</code>, with a default set of words
 * surrounding the word "Stephen". The network is adapted from Princeton WordNet Database.
 * 
 * @author Kwan-Yuet Ho, Ph.D.
 * @see {@link http://wordnet.princeton.edu/}
 *
 */
public class DemonstratedSocialNetSimVol extends SocialNetSimVol {
	/**
	 * Constructor, initializing the word network with a default set of words surrounding the word "Stephen".
	 */
	public DemonstratedSocialNetSimVol() {
		String[] nodes_i = {"Stephen", "Sinnie", "Andrea", "Elaine",
				"Bonnie", "King", "Clive", "Kevin", "Barack",
				"Dietrich", "Ted", "Shirley", "Vincent"};
		DirectedLink[] edges_i = {	
				new DirectedLink("1", "Stephen", "Sinnie"),
				new DirectedLink("2", "Sinnie", "Stephen"),
				new DirectedLink("3", "Sinnie", "Andrea"),
				new DirectedLink("4", "Andrea", "Sinnie"),
				new DirectedLink("5", "Andrea", "Elaine"),
				new DirectedLink("6", "Elaine", "Sinnie"),
				new DirectedLink("7", "Sinnie", "Elaine"),
				new DirectedLink("8", "Sinnie", "Bonnie"),
				new DirectedLink("9", "Bonnie", "Sinnie"),
				new DirectedLink("10", "Sinnie", "King"),
				new DirectedLink("11", "King", "Sinnie"),
				new DirectedLink("12", "Stephen", "Barack"),
				new DirectedLink("13", "Barack", "Stephen"),
				new DirectedLink("14", "Stephen", "Dietrich"),
				new DirectedLink("15", "Dietrich", "Stephen"),
				new DirectedLink("16", "Barack", "Dietrich"),
				new DirectedLink("17", "Dietrich", "Barack"),
				new DirectedLink("18", "Barack", "Ted"),
				new DirectedLink("19", "Ted", "Barack"),
				new DirectedLink("20", "Ted", "Dietrich"),
				new DirectedLink("21", "Dietrich", "Ted"),
				new DirectedLink("22", "Ted", "Stephen"),
				new DirectedLink("23", "Ted", "Shirley"),
				new DirectedLink("24", "Shirley", "Ted"),
				new DirectedLink("25", "Ted", "Vincent"),
				new DirectedLink("26", "Vincent", "Ted"),
				new DirectedLink("27", "Ted", "Kevin"),
				new DirectedLink("28", "Kevin", "Ted"),
				new DirectedLink("29", "Kevin", "Barack"),
				new DirectedLink("30", "Barack", "Kevin"),
				new DirectedLink("31", "Clive", "Stephen"),
				new DirectedLink("32", "Stephen", "Clive"),
				new DirectedLink("33", "Clive", "Sinnie"),
				new DirectedLink("34", "Stephen", "Kevin"),
				new DirectedLink("35", "Kevin", "Stephen"),
				new DirectedLink("36", "Kevin", "Dietrich"),
				new DirectedLink("37", "Dietrich", "Kevin")};	
		constructSocialNetwork(nodes_i, edges_i);
		pathAlg = new DijkstraShortestPath<String, DirectedLink>(socialNet);
	}
}
