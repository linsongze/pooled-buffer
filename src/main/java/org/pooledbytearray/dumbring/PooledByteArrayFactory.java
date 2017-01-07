package org.pooledbytearray.dumbring;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class PooledByteArrayFactory
{
    private Queue<byte[]> pool;
    private final int arraySize;

    public PooledByteArrayFactory(int arraySize, int preAllocSize)
    {
        this.arraySize = arraySize;

        pool = new ArrayBlockingQueue<>(preAllocSize);
        for(int i=0; i<preAllocSize; i++)
        {
            pool.offer(new byte[arraySize]);
        }
    }

    /**
     * @return Ready to use PooledByteArray. Be careful not reset!
     */
    public PooledByteArray getByteArray()
    {
        byte[] bytes = pool.poll();
        return new PooledByteArray(bytes,this);
    }

    protected void returnByteArray(byte[] bytes){
        if( bytes == null || bytes.length != arraySize){
            throw new ArraySizeException("array size is wrong");
        }
       pool.add(bytes);
    }


}