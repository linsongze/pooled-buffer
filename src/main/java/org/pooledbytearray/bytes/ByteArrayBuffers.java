package org.pooledbytearray.bytes;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.pooledbytearray.buffer.Buffer;
import org.pooledbytearray.bytes.os.ByteArrayOutputStream;
import org.pooledbytearray.bytes.os.PoolByteArrayOutputStream;
import org.pooledbytearray.config.PoolConfig;

public class ByteArrayBuffers
{
    private PoolConfig poolConfig;
    GenericObjectPool<Buffer> genericObjectPool  ;

    public ByteArrayBuffers(PoolConfig poolConfig){
        this.poolConfig = poolConfig;
        GenericObjectPoolConfig conf = new GenericObjectPoolConfig();
        conf.setMaxTotal(poolConfig.getMaxTotal());
        conf.setMaxIdle(poolConfig.getMaxIdle());
        genericObjectPool= new GenericObjectPool<Buffer>(poolConfig.getPooledObjectFactory(), conf);

    }

    public ByteArrayOutputStream getByteArrayOutputStream() throws Exception {
        Buffer buffer= genericObjectPool.borrowObject();
        return new PoolByteArrayOutputStream(buffer,this);
    }
    public void returnByteArray(Buffer buffer){
        buffer.reset();
        genericObjectPool.returnObject(buffer);
    }


}
