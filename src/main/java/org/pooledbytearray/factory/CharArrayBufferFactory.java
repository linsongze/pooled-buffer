package org.pooledbytearray.factory;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.pooledbytearray.buffer.string.StrBuffer;

/**
 * Created by lsz on 2017/1/7.
 */
public class CharArrayBufferFactory extends BasePooledObjectFactory<StrBuffer> implements PooledObjectFactory<StrBuffer> {
    private int bufferSize;
    public CharArrayBufferFactory(int bufferSize){
        this.bufferSize = bufferSize;
    }
    @Override
    public StrBuffer create() throws Exception {
        return new StrBuffer(new char[bufferSize]);
    }
    @Override
    public PooledObject<StrBuffer> wrap(StrBuffer buffer) {
        return new DefaultPooledObject<StrBuffer>(buffer);
    }

}