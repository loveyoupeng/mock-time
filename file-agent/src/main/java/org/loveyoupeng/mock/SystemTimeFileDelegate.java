package org.loveyoupeng.mock;

import static net.bytebuddy.implementation.bytecode.assign.Assigner.Typing.DYNAMIC;

import java.io.File;
import java.io.RandomAccessFile;
import net.bytebuddy.asm.Advice.OnMethodExit;
import net.bytebuddy.asm.Advice.Return;

public class SystemTimeFileDelegate {

  @OnMethodExit
  public static void getTime(@Return(readOnly = false, typing = DYNAMIC) Object value)
      throws Exception {
    final File file = new File(
        System.getProperty("java.io.tmpdir") + File.separator + "mockTime.dat");
    if (!file.exists()) {
      file.createNewFile();
    }

    try (final RandomAccessFile raFile = new RandomAccessFile(file.getAbsoluteFile(), "rw")) {
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
      raFile.seek(0);
      if (raFile.readInt() > 0) {
        raFile.seek(4L);
        final long time = raFile.readLong();
        raFile.seek(20);
        long cached = raFile.readLong();
        if(cached < time) {
          cached = time;
        }
        value = cached;
        raFile.seek(12L);
        long step = raFile.readLong();
        step = step > 0 ? step : 10;
        raFile.seek(20);
        raFile.writeLong(cached + step);
      }
    }
  }

}
