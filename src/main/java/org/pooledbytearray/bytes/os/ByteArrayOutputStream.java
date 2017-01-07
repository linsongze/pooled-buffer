package org.pooledbytearray.bytes.os;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by lsz on 2017/1/8.
 */
public interface ByteArrayOutputStream {
    public void write(int b) throws IOException;
    public void write(byte b[], int off, int len);
    public void write(byte[] b);

    public void writeTo(OutputStream out) throws IOException;


    public void reset();
    public byte toByteArray()[] ;
    public void close() throws IOException ;
    public String toString();
}
