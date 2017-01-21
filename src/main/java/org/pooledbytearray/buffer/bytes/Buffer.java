package org.pooledbytearray.buffer.bytes;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by lsz on 2017/1/8.
 */
public interface Buffer {
    void write(byte b);

    void write(byte b[], int off, int len);

    void writeTo(OutputStream out) throws IOException;

    void reset();

    byte toByteArray()[];

    String toString();

}


