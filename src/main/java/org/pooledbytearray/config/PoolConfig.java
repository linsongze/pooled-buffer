package org.pooledbytearray.config;

import org.apache.commons.pool2.PooledObjectFactory;
import org.pooledbytearray.buffer.bytes.Buffer;
import org.pooledbytearray.buffer.string.StrBuffer;
import org.pooledbytearray.factory.ByteArrayBufferFactory;
import org.pooledbytearray.factory.CharArrayBufferFactory;

/**
 * Created by lsz on 2017/1/8.
 */
public class PoolConfig<T> {
    private int maxTotal;
    private int maxIdle;
    private int bufferSize;
    private PooledObjectFactory<T> pooledObjectFactory;
    private PoolConfig(){};
    public  PoolConfig(int maxTotal
            ,int maxIdle,int bufferSize,PooledObjectFactory<T> pooledObjectFactory

            ){
        this.setMaxTotal(maxTotal)
                .setMaxIdle(maxIdle)
                .setBufferSize(bufferSize)
                .setPooledObjectFactory(pooledObjectFactory)
        ;

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

    public PooledObjectFactory<T> getPooledObjectFactory() {
        return pooledObjectFactory;
    }

    public PoolConfig setPooledObjectFactory(PooledObjectFactory pooledObjectFactory) {
        this.pooledObjectFactory = pooledObjectFactory;
        return this;
    }
    public static PoolConfig bsPoolDefault(){
        int bufferSize = 1024*1024;
        PoolConfig<Buffer> bufferPoolConfig = new PoolConfig<>(50,20,bufferSize,new ByteArrayBufferFactory(bufferSize));
        return bufferPoolConfig;
    }
    public static PoolConfig charsPoolDefault(){
        int bufferSize = 1024*1024;
        PoolConfig<StrBuffer> bufferPoolConfig = new PoolConfig<>(50,20,bufferSize,new CharArrayBufferFactory(bufferSize));
        return bufferPoolConfig;
    }
}
