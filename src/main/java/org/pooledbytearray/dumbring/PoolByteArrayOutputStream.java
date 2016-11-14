package org.pooledbytearray.dumbring;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 *
 * Created by lsz on 2016/11/14.
 */
public class PoolByteArrayOutputStream extends OutputStream {
    private byte[] bytes = null;
    private PooledByteArrayFactory pooledByteArrayFactory;
    private int count;
    private int arraySize;
    public PoolByteArrayOutputStream(byte[] bytes, PooledByteArrayFactory pooledByteArrayFactory) {
        this.bytes = bytes;
        this.pooledByteArrayFactory = pooledByteArrayFactory;
        this.arraySize = bytes.length;
    }

    @Override
    public synchronized void write(int b) throws IOException {
        int tmpCount = count+1;
        if(tmpCount>arraySize){throw new ArraySizeException("out of array size limit :"+arraySize);}
        bytes[count] = (byte) b;
        count = tmpCount;
    }
    public synchronized void write(byte b[], int off, int len) {
        if ((off < 0) || (off > b.length) || (len < 0) ||
                ((off + len) - b.length > 0)) {
            throw new IndexOutOfBoundsException();
        }
        if(count+len > arraySize) {throw new ArraySizeException("out of array size limit :"+arraySize);}
        System.arraycopy(b, off, bytes, count, len);
        count += len;
    }

    public synchronized void writeTo(OutputStream out) throws IOException {
        out.write(bytes, 0, count);
    }


    public synchronized void reset() {
        count = 0;
    }
    public synchronized byte toByteArray()[] {
        return Arrays.copyOf(bytes, count);
    }
    @Override
    public synchronized void close() throws IOException {
        if(bytes == null)return;
        pooledByteArrayFactory.returnByteArray(bytes);
        this.bytes = null;
    }
    public synchronized String toString() {
        return new String(bytes, 0, count);
    }
}
