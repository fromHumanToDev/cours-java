package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import principal.Main;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FactorielTest{

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 0, 1 },
                { 1, 1 },
                { 2, 2 },
                { 3, 6 },
                { 5, 120 }
        });
    }

    private Integer nb;
    private Integer resultatAttendu;

    public FactorielTest(Integer nb, Integer resultatAttendu){
        this.nb = nb;
        this.resultatAttendu = resultatAttendu;
    }


    @Test
    public void testFactoriel(){
        Assert.assertEquals(resultatAttendu, Main.fact(nb));
    }
}