package org.pooledbytearray.buffer.bytes;

import org.pooledbytearray.exception.BufferOutIndexException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * Created by lsz on 2017/1/8.
 */
public class DirectByteArrayBuffer implements Buffer{
    private int bufferSize;
    private ByteBuffer byteBuffer;
    private int count;
    public DirectByteArrayBuffer(int bufferSize){
        this.bufferSize = bufferSize;
        this.byteBuffer = ByteBuffer.allocateDirect(bufferSize);
    }

    @Override
    public void write(byte b) {
        int tmpCount = count+1;
        if(tmpCount>bufferSize){throw new BufferOutIndexException("out of buffer size limit :"+bufferSize);}
        byteBuffer.put(b);
        count++;
    }

    @Override
    public void write(byte[] b, int off, int len) {
        if ((off < 0) || (off > b.length) || (len < 0) ||
                ((off + len) - b.length > 0)) {
            throw new IndexOutOfBoundsException();
        }
        if(count+len > bufferSize) {throw new BufferOutIndexException("out of buffer size limit :"+bufferSize);}
        byteBuffer.put(b,off,len);
        count+=len;
    }

    @Override
    public void writeTo(OutputStream out) throws IOException {
        out.write(get());
    }

    @Override
    public void reset() {
        count = 0;
        byteBuffer.clear();
    }
    public String toString(){
        return new String(get());
    }
    @Override
    public byte[] toByteArray() {
        return get();
    }
    private byte[] get(){
        byte[] bytes = new byte[count];
        byteBuffer.flip();
        byteBuffer.get(bytes,0,count);
        return bytes;
    }
}
