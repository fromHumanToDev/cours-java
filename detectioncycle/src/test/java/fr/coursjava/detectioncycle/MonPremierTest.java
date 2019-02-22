package fr.coursjava.detectioncycle;

import org.junit.Assert;
import org.junit.Test;

public class MonPremierTest {

    @Test
    public void doIt(){
        Assert.assertEquals("coucou", "coucou");
    }

    @Test
    public void doIt2(){
        Main.main(null);
        Assert.assertEquals("coucou", "coucou");
    }

}
