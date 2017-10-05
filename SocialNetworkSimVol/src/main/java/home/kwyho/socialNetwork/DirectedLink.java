package home.kwyho.socialNetwork;

/**
 * This class is the edge of the social network.
 * 
 * @author Kwan-Yuet Ho, Ph.D.
 *
 */
public class DirectedLink {
	private String person1, person2, name;
	
	/**
	 * Returns the name of the edge.
	 */
	public String toString() {
		return name;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param name Name of the edge
	 * @param person1 Starting individual of the edge
	 * @param person2 Ending individual of the edge
	 */
	public DirectedLink(String name, String person1, String person2) {
		this.person1 = person1;
		this.person2 = person2;
		this.name = name;
	}
	
	/**
	 * Returns the starting individual of the edge.
	 * 
	 * @return Starting individual of the edge
	 */
	public String getPerson1() {
		return person1;
	}
	
	/**
	 * Returns the ending individual of the edge.
	 * 
	 * @return Ending individual of the edge
	 */
	public String getPerson2() {
		return person2;
	}
	
	/**
	 * Sets the starting individual of the edge.
	 * 
	 * @param person1 Starting individual of the edge
	 */
	public void setPerson1(String person1) {
		this.person1 = person1;
	}
	
	/**
	 * Sets the ending individual of the edge.
	 * 
	 * @param person2 Ending individual of the edge
	 */
	public void setPerson2(String person2) {
		this.person2 = person2;
	}
	
	/**
	 * Returns the name of the edge.
	 * 
	 * @return Name of the edge
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the edge.
	 * 
	 * @param name Name of the edge
	 */
	public void setName(String name) {
		this.name = name;
	}
}
