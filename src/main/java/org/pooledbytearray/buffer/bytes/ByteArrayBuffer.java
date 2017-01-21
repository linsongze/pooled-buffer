package org.pooledbytearray.buffer.bytes;

import org.pooledbytearray.exception.BufferOutIndexException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Created by lsz on 2017/1/8.
 */
public class ByteArrayBuffer implements Buffer{
    private byte[] bytes = null;
    private int count;
    private int arraySize;
    public ByteArrayBuffer(byte[] bytes){
        this.bytes = bytes;
        this.arraySize = bytes.length;
    }

    @Override
    public void write(byte b) {
        int tmpCount = count+1;
        if(tmpCount>arraySize){throw new BufferOutIndexException("out of array size limit :"+arraySize);}
        bytes[count] = (byte) b;
        count = tmpCount;

    }

    @Override
    public void write(byte[] b, int off, int len) {
        if ((off < 0) || (off > b.length) || (len < 0) ||
                ((off + len) - b.length > 0)) {
            throw new IndexOutOfBoundsException();
        }
        if(count+len > arraySize) {throw new BufferOutIndexException("out of array size limit :"+arraySize);}
        System.arraycopy(b, off, bytes, count, len);
        count += len;
    }

    @Override
    public void writeTo(OutputStream out) throws IOException {
        out.write(bytes, 0, count);
    }

    @Override
    public void reset() {
        count = 0 ;
    }

    @Override
    public byte[] toByteArray() {
        return Arrays.copyOf(bytes, count);
    }
    public synchronized String toString() {
        return new String(bytes, 0, count);
    }
}
