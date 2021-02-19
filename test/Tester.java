import org.csc133.a1.Bird;
import org.csc133.a1.Helicopter;
import org.csc133.a1.RefuelingBlimp;
import org.csc133.a1.SkyScraper;

public class Tester
{
    public static void main(String[] args)
    {
        // toString() tests
        Bird testBird = new Bird();
        System.out.println(testBird);

        SkyScraper testScraper = new SkyScraper();
        System.out.println(testScraper);

        RefuelingBlimp testBlimp = new RefuelingBlimp();
        System.out.println(testBlimp);
        testBlimp.setLocation(50, 50);
        testBlimp.setColor(400);
        System.out.println(testBlimp);

        Helicopter testHeli = new Helicopter();
        testHeli.setLocation(10, 5);
        System.out.println(testHeli);
        testHeli.setSpeed(2);
        testHeli.move();
        System.out.println(testHeli);
    }
}
