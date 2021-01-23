package org.loveyoupeng.mock;

public interface SystemTimeUtils {

  String OVERWRITE_FLAG = "overwrite-flag";
  String OVERWRITE_TIME_VALUE = "overwrite-time-value";
  String OVERWRITE_TIME_STEP = "overwrite-time-step";
  static void setTime(final long value) {
    System.setProperty(OVERWRITE_FLAG, "true");
    System.setProperty(OVERWRITE_TIME_VALUE, String.valueOf(value));
  }

  static void setTime(final long value, final long step) {
    System.setProperty(OVERWRITE_FLAG, "true");
    System.setProperty(OVERWRITE_TIME_VALUE, String.valueOf(value));
    System.setProperty(OVERWRITE_TIME_STEP, String.valueOf(step));
  }

  static void setStep(final long step) {
    System.setProperty(OVERWRITE_TIME_STEP, String.valueOf(step));
  }

  static  void useRealTime() {
    System.setProperty(OVERWRITE_FLAG, "false");
  }
}
