package com.gmail.walles.johan.johansbarnklocka;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;

public class HourHandTest {
    @Test
    public void testSetHourEasy() {
        HourHand testMe = new HourHand(null, 0, 0);
        testMe.setTime(6, 0);
        testMe.setHour(5.0);
        Assert.assertThat(testMe.getHour(), is(5));
    }
}
