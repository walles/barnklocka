package com.gmail.walles.johan.johansbarnklocka;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;

public class HourHandTest {
    private void testSetHour(int startHour, int setHour, int correctResult) {
        HourHand testMe = new HourHand(null, 0, 0);
        testMe.setTime(startHour, 0);
        testMe.setHour(setHour);
        Assert.assertThat(testMe.getHour(), is(correctResult));
    }

    @Test
    public void testSetHourEasy() {
        testSetHour(6, 5, 5);
    }

    @Test
    public void testSetHourAfternoon() {
        testSetHour(15, 4, 16);
    }
}
