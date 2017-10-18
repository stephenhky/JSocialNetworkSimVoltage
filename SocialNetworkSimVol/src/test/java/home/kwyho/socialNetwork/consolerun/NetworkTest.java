package home.kwyho.socialNetwork.consolerun;

import home.kwyho.socialNetwork.DemonstratedSocialNetSimVol;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class NetworkTest {

    @Test
    public void testValue() {
        Map<String, Double> referencedAnswer = new HashMap<String, Double>();
        referencedAnswer.put("Stephen -> Stephen", 0.0);
        referencedAnswer.put("Stephen -> Sinnie", 0.6666666666666666);
        referencedAnswer.put("Stephen -> Andrea", 1.6664642640282887);
        referencedAnswer.put("Stephen -> Elaine", 1.3333333333333333);
        referencedAnswer.put("Stephen -> Bonnie", 1.6664642640282887);
        referencedAnswer.put("Stephen -> King", 1.6664642640282887);
        referencedAnswer.put("Stephen -> Clive", 1.0);
        referencedAnswer.put("Stephen -> Kevin", 0.4666835270965549);
        referencedAnswer.put("Stephen -> Barack", 0.5);
        referencedAnswer.put("Stephen -> Dietrich", 0.4666835270965549);
        referencedAnswer.put("Stephen -> Ted", 0.6666666666666666);
        referencedAnswer.put("Stephen -> Shirley", 1.6662322620601902);
        referencedAnswer.put("Stephen -> Vincent", 1.6662322620601902);
        referencedAnswer.put("Stephen -> Stephen", 0.0);
        referencedAnswer.put("Sinnie -> Stephen", 1.0);
        referencedAnswer.put("Andrea -> Stephen", 1.6664642640282887);
        referencedAnswer.put("Elaine -> Stephen", 2.0);
        referencedAnswer.put("Bonnie -> Stephen", 2.0);
        referencedAnswer.put("King -> Stephen", 2.0);
        referencedAnswer.put("Clive -> Stephen", 0.6666666666666666);
        referencedAnswer.put("Kevin -> Stephen", 0.4);
        referencedAnswer.put("Barack -> Stephen", 0.4);
        referencedAnswer.put("Dietrich -> Stephen", 0.4);
        referencedAnswer.put("Ted -> Stephen", 0.4);
        referencedAnswer.put("Shirley -> Stephen", 1.4000275567423994);
        referencedAnswer.put("Vincent -> Stephen", 1.4000275567423994);

        DemonstratedSocialNetSimVol wn2 = new DemonstratedSocialNetSimVol();
        int numWords = wn2.getNumOfPeople();
        int i;

        for (i = 0; i < numWords; i++) {
            System.out.println("Stephen -> "+wn2.getPerson(i)+" : "+wn2.getResistance("Stephen", wn2.getPerson(i)));
            Assert.assertEquals(referencedAnswer.get("Stephen -> "+wn2.getPerson(i)), wn2.getResistance("Stephen", wn2.getPerson(i)), 1e-6);
        }

        for (i = 0; i < numWords; i++) {
            System.out.println(wn2.getPerson(i)+" -> Stephen : "+wn2.getResistance(wn2.getPerson(i), "Stephen"));
            Assert.assertEquals(referencedAnswer.get(wn2.getPerson(i)+" -> Stephen"), wn2.getResistance(wn2.getPerson(i), "Stephen"), 1e-6);
        }


    }
}
