package org.pooledbytearray.buffer.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pooledbytearray.buffer.bytes.ByteArrayBuffers;
import org.pooledbytearray.config.PoolConfig;
import org.pooledbytearray.factory.CharArrayBufferFactory;
import org.pooledbytearray.factory.DirectByteArrayBufferFactory;

import static org.junit.Assert.*;

/**
 * Created by lsz on 2017/1/21.
 */
public class StrBufferTest {
    StrBuffers buffers;
    @Before
    public void init(){
        PoolConfig<StrBuffer> poolConfig = PoolConfig.charsPoolDefault();
        poolConfig.setMaxTotal(1);
        poolConfig.setMaxIdle(1);
        poolConfig.setPooledObjectFactory(new CharArrayBufferFactory(poolConfig.getBufferSize()));
        buffers=new StrBuffers(poolConfig);
    }
    @Test
    public void append() throws Exception {
        StrBuffer strBuffer = buffers.getBuffer();
        strBuffer.append('c');
        strBuffer.append('c');
        strBuffer.append('c');
        Assert.assertEquals("ccc",strBuffer.toString());
        buffers.returnThis(strBuffer);
    }

}