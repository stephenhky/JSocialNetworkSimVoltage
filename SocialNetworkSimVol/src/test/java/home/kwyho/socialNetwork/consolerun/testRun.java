package home.kwyho.socialNetwork.consolerun;

import home.kwyho.socialNetwork.DemonstratedSocialNetSimVol;



public class testRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DemonstratedSocialNetSimVol wn2 = new DemonstratedSocialNetSimVol();
		
		int numWords = wn2.getNumOfPeople();
		int i;
		
		for (i = 0; i < numWords; i++)
			System.out.println("Stephen -> "+wn2.getPerson(i)+" : "+wn2.getResistance("Stephen", wn2.getPerson(i)));
		for (i = 0; i < numWords; i++)
			System.out.println(wn2.getPerson(i)+" -> Stephen : "+wn2.getResistance(wn2.getPerson(i), "Stephen"));	
	}

}
