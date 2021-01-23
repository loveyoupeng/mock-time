package org.loveyoupeng.mock;

import static net.bytebuddy.implementation.bytecode.assign.Assigner.Typing.DYNAMIC;

import net.bytebuddy.asm.Advice.OnMethodExit;
import net.bytebuddy.asm.Advice.Return;

public class SystemTimeDelegate {

  @OnMethodExit
  public static void getTime(@Return(readOnly = false, typing = DYNAMIC) Object value) {
    if(!System.getProperties().containsKey("overwrite-time-cached")) {
      System.setProperty("overwrite-time-cached", String.valueOf(Long.MIN_VALUE));
    }

    if(!System.getProperties().containsKey("overwrite-time-step")) {
      System.setProperty("overwrite-time-step", String.valueOf(10L));
    }

    if(Boolean.getBoolean("overwrite-flag")) {
      final long overwrite  = Long.getLong("overwrite-time-value");
      final long cachedValue = Long.getLong("overwrite-time-cached");
      if(overwrite > cachedValue) {
        System.setProperty("overwrite-time-cached",String.valueOf(overwrite));
      }
      value = Long.getLong("overwrite-time-cached");
      final long step = Long.getLong("overwrite-time-step");
      System.setProperty("overwrite-time-cached", String.valueOf(Long.getLong("overwrite-time-cached") + step));
    }
  }

}
