package org.pooledbytearray;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pooledbytearray.bytes.ByteArrayBuffers;
import org.pooledbytearray.bytes.os.ByteArrayOutputStream;
import org.pooledbytearray.config.PoolConfig;
import org.pooledbytearray.factory.DirectByteArrayBufferFactory;

import java.nio.ByteBuffer;

/**
 * Created by lsz on 2017/1/8.
 */
public class DirectPoolByteArrayOutputStreamTest {
    ByteArrayBuffers buffers;

    @Before
    public void init(){
        PoolConfig poolConfig = PoolConfig.createDefault();
        poolConfig.setMaxTotal(1);
        poolConfig.setMaxIdle(1);
        poolConfig.setPooledObjectFactory(new DirectByteArrayBufferFactory(poolConfig.getBufferSize()));
        buffers=new ByteArrayBuffers(poolConfig);
    }
    @Test
    public void test() throws Exception {
        String s = "中华人民共和国";
        ByteArrayOutputStream outputStream = buffers.getByteArrayOutputStream();
        outputStream.write(s.getBytes());
        Assert.assertEquals(s,outputStream.toString());

        outputStream.close();

        outputStream = buffers.getByteArrayOutputStream();
        outputStream.write(s.getBytes());
        Assert.assertEquals(s,outputStream.toString());

        outputStream.close();
    }
}
