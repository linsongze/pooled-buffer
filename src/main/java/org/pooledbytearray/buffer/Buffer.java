package org.pooledbytearray.buffer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by lsz on 2017/1/8.
 */
public interface Buffer {
    public void write(byte b);

    public void write(byte b[], int off, int len);

    public void writeTo(OutputStream out) throws IOException;

    public void reset();

    public byte toByteArray()[];

    public String toString();

}


