package org.loveyoupeng.mock;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;

public class Process {
  @Ignore
  @Test
  public void print() throws Exception {
   for(int i = 0;i < 1000;i++) {
      System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
      Thread.sleep(1000L);
    }
  }
}
