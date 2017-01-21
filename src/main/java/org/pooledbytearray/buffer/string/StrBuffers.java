package org.pooledbytearray.buffer.string;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.pooledbytearray.buffer.bytes.Buffer;
import org.pooledbytearray.config.PoolConfig;

/**
 * Created by lsz on 2017/1/21.
 */
public class StrBuffers {
    private PoolConfig<StrBuffer> poolConfig;
    private GenericObjectPool<StrBuffer> genericObjectPool  ;
    public StrBuffers(PoolConfig<StrBuffer> poolConfig){
        this.poolConfig = poolConfig;
        GenericObjectPoolConfig conf = new GenericObjectPoolConfig();
        conf.setMaxTotal(poolConfig.getMaxTotal());
        conf.setMaxIdle(poolConfig.getMaxIdle());
        genericObjectPool= new GenericObjectPool<StrBuffer>(poolConfig.getPooledObjectFactory(), conf);
    }
    public StrBuffer getBuffer() throws Exception {
        StrBuffer buffer= genericObjectPool.borrowObject();
        return buffer;
    }
    public void returnThis(StrBuffer strBuffer){
        genericObjectPool.returnObject(strBuffer);
    }
}
