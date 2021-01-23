package org.loveyoupeng.mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.loveyoupeng.mock.SystemTimeUtils.setStep;
import static org.loveyoupeng.mock.SystemTimeUtils.setTime;
import static org.loveyoupeng.mock.SystemTimeUtils.useRealTime;

import java.util.Calendar;
import java.util.Date;
import org.junit.Test;

public class SystemTimeAgentTest {

  @Test
  public void test() {
    final Date date = new Date();
    assertTrue(date.getTime() <= System.currentTimeMillis());
    System.out.println(System.currentTimeMillis());
    final long realStart = System.currentTimeMillis();

    setTime(1000);
    assertEquals(1000L, System.currentTimeMillis());
    assertEquals(1010L, System.currentTimeMillis());
    assertEquals(1020L, new Date().getTime());

    setTime(2000, 20);
    assertEquals(2000L, System.currentTimeMillis());
    assertEquals(2020L, System.currentTimeMillis());

    setStep(50);
    assertEquals(2040L, System.currentTimeMillis());
    assertEquals(2090L, new Date().getTime());

    useRealTime();
    assertTrue(realStart < System.currentTimeMillis());

  }
}
