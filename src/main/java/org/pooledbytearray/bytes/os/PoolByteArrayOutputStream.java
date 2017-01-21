package org.pooledbytearray.bytes.os;

import org.pooledbytearray.buffer.bytes.Buffer;
import org.pooledbytearray.buffer.bytes.ByteArrayBuffers;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * Created by lsz on 2016/11/14.
 */
public class PoolByteArrayOutputStream  extends OutputStream implements ByteArrayOutputStream{

    private ByteArrayBuffers pooledByteArrayFactory;
    private Buffer buffer;
    public PoolByteArrayOutputStream(Buffer buffer, ByteArrayBuffers pooledByteArrayFactory) {
        this.buffer = buffer;
        this.pooledByteArrayFactory = pooledByteArrayFactory;
    }
    @Override
    public synchronized void write(int b) throws IOException {
        buffer.write((byte) b);
    }
    public synchronized void write(byte b[], int off, int len) {
        buffer.write(b,off,len);
    }

    public synchronized void writeTo(OutputStream out) throws IOException {
        buffer.writeTo(out);
    }
    public synchronized void write(byte[] b){
        this.write(b,0,b.length);
    }
    public synchronized void reset() {
        buffer.reset();
    }
    public synchronized byte toByteArray()[] {
        return  buffer.toByteArray();
    }
    @Override
    public synchronized void close() throws IOException {
        pooledByteArrayFactory.returnByteArray(buffer);
        this.buffer= null;
    }
    public synchronized String toString() {
        return buffer.toString();
    }
}
