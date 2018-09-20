
package barrenlandanalysis;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**

 @author Peer-Anders C. Halvorsen
 */
public class LandGridTest
{

    public LandGridTest()
    {

    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    /**
     Test of getFertileAreas method, of class LandGrid.
     */
    @Test
    public void testGetFertileAreas()
    {
        System.out.println("getFertileAreas Test");
        LandGrid test1 = new LandGrid("0 292 399 307");
        String output = test1.getFertileAreas();
        assertEquals("116800 116800", output);

        LandGrid test2 = new LandGrid("48 192 351 207, 48 392 351 407, 120 52 135 547,"
                + " 260 52 275 547");
        output = test2.getFertileAreas();
        assertEquals("22816 192608", output);

        LandGrid test3 = new LandGrid("0 0 399 599");
        output = test3.getFertileAreas();
        assertEquals("No fertile land was found.", output);
    }

    /**
     Test of setBarren method.
     */
    @Test
    public void testSetBarren()
    {
        System.out.println("setBarren test");
        LandGrid test = new LandGrid("0 292 399 307");
        test.setBarren("0 0 399 599");
        String output = test.getFertileAreas();
        assertEquals("No fertile land was found.", output);

    }

    /**
     Test for clearBarren method
     */
    @Test
    public void testClearBarren()
    {
        System.out.println("clearBarren test");
        LandGrid test = new LandGrid("0 0 399 599");
        test.clearBarren();
        String output = test.getFertileAreas();
        assertEquals("240000", output);
    }

}
