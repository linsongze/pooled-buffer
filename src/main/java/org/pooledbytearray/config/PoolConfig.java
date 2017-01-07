package org.pooledbytearray.config;

import org.apache.commons.pool2.PooledObjectFactory;
import org.pooledbytearray.buffer.Buffer;
import org.pooledbytearray.factory.ByteArrayBufferFactory;

/**
 * Created by lsz on 2017/1/8.
 */
public class PoolConfig {
    private int maxTotal;
    private int maxIdle;
    private int bufferSize;
    private PooledObjectFactory<Buffer> pooledObjectFactory;
    private PoolConfig(){};
    public static PoolConfig create(int maxTotal
            ,int maxIdle,int bufferSize,PooledObjectFactory<Buffer> pooledObjectFactory

            ){
        PoolConfig poolConfig = new PoolConfig();
        poolConfig.setMaxTotal(maxTotal)
                .setMaxIdle(maxIdle)
                .setBufferSize(bufferSize)
                .setPooledObjectFactory(pooledObjectFactory)
        ;

        return poolConfig;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public PoolConfig setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
        return this;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public PoolConfig setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
        return this;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public PoolConfig setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
        return this;
    }

    public PooledObjectFactory<Buffer> getPooledObjectFactory() {
        return pooledObjectFactory;
    }

    public PoolConfig setPooledObjectFactory(PooledObjectFactory<Buffer> pooledObjectFactory) {
        this.pooledObjectFactory = pooledObjectFactory;
        return this;
    }
    public static PoolConfig createDefault(){
        int bufferSize = 1024*1024;
        return create(50,20,bufferSize,new ByteArrayBufferFactory(bufferSize));
    }

}
