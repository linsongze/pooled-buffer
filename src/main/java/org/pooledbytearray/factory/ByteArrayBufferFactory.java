package org.pooledbytearray.factory;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.pooledbytearray.buffer.Buffer;
import org.pooledbytearray.buffer.ByteArrayBuffer;

/**
 * Created by lsz on 2017/1/7.
 */
public class ByteArrayBufferFactory extends BasePooledObjectFactory<Buffer> {
    private int bufferSize;
    public ByteArrayBufferFactory(int bufferSize){
        this.bufferSize = bufferSize;
    }
    @Override
    public Buffer create() throws Exception {
        return new ByteArrayBuffer(new byte[bufferSize]);
    }
    @Override
    public PooledObject<Buffer> wrap(Buffer buffer) {
        return new DefaultPooledObject<Buffer>(buffer);
    }

}