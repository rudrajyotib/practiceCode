package f2finterviews.raghavendra;

import org.junit.Test;

import static org.junit.Assert.*;

public class FantaDrinkTest {

    @Test
    public void shouldCalculateCalorieFor100Ml()
    {
        FantaDrink fantaDrink=new FantaDrink(100, 10, 1);
        assertEquals(295, fantaDrink.calculateCalorie());
    }


}