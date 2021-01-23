package org.loveyoupeng.mock;

import static org.loveyoupeng.mock.SystemTimeFileUtils.setTime;

import org.junit.Ignore;
import org.junit.Test;

public class TimeSetter {
  @Ignore
  @Test
  public void control() throws Exception {
    setTime(1000L);
    Thread.sleep(1000L);
    setTime(24324342342L, 10000L);
  }
}
