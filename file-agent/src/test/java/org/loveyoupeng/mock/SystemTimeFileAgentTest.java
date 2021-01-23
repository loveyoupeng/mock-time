package org.loveyoupeng.mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.loveyoupeng.mock.SystemTimeFileUtils.debug;
import static org.loveyoupeng.mock.SystemTimeFileUtils.setStep;
import static org.loveyoupeng.mock.SystemTimeFileUtils.setTime;
import static org.loveyoupeng.mock.SystemTimeFileUtils.useRealTime;

import java.io.File;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.Test;

public class SystemTimeFileAgentTest {

  @AfterClass
  public static void afterClass() {
    new File(System.getProperty("java.io.tmpdir") + File.separator + "mockTime.dat").delete();
  }

  @Test
  public void test() {
    final Date date = new Date();
    assertTrue(date.getTime() <= System.currentTimeMillis());
    System.out.println(System.currentTimeMillis());
    final long realStart = System.currentTimeMillis();
    debug();
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
    assertTrue(realStart <= System.currentTimeMillis());
  }
}
