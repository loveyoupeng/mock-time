package org.loveyoupeng.mock;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class SystemTimeFileUtils {

  private static File file;
  private static RandomAccessFile raFile;

  static {
    try {
      file = new File(
          System.getProperty("java.io.tmpdir") + File.separator + "mockTime.dat");
      if (!file.exists()) {
        file.createNewFile();
      }

      raFile = new RandomAccessFile(file.getAbsoluteFile(), "rw");
      if (raFile.length() < 28) {
        raFile.setLength(20);
        raFile.seek(0);
        raFile.writeInt(0);
        raFile.seek(4L);
        raFile.writeLong(0L);
        raFile.seek(12L);
        raFile.writeLong(10L);
        raFile.seek(20);
        raFile.writeLong(Long.MIN_VALUE);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void setTime(final long value) {
    try {
      raFile.seek(0);
      raFile.writeInt(1);
      raFile.seek(4L);
      raFile.writeLong(value);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void setTime(final long value, final long step) {
    try {
      raFile.seek(0);
      raFile.writeInt(1);
      raFile.seek(4L);
      raFile.writeLong(value);
      raFile.seek(12L);
      raFile.writeLong(step);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void setStep(final long step) {
    try {
      raFile.seek(12L);
      raFile.writeLong(step);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void useRealTime() {
    try {
      raFile.seek(0);
      raFile.writeInt(0);
      raFile.seek(4L);
      raFile.writeLong(0L);
      raFile.seek(12L);
      raFile.writeLong(10L);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void debug() {
    try {
      raFile.seek(0);
      final int flag = raFile.readInt();
      raFile.seek(4L);
      final long time = raFile.readLong();
      raFile.seek(12L);
      final long step = raFile.readLong();
      raFile.seek(20L);
      final long cached = raFile.readLong();
      System.out.printf("flag=%d, time=%d, step=%d, cached=%d\n", flag, time, step, cached);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
