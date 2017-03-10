package org.pooledbytearray.buffer.string;

import org.pooledbytearray.exception.BufferOutIndexException;

import java.util.Arrays;

/**
 * Created by lsz on 2017/1/21.
 */
public class StrBuffer  implements  AutoCloseable{
    private char[] values= null;
    private int count;
    private int arraySize;
    private StrBuffers strBuffers;
    public StrBuffer(char[] values){
        this.values = values;
        this.arraySize = values.length;
        this.count = 0 ;
    }

    public StrBuffer append(char c){
        ensureCapacityInternal(count + 1);
        values[count++] = c;
        return this;
    }
    public StrBuffer append(String str){
        int len = str.length();
        ensureCapacityInternal(count + len);
        str.getChars(0, len, values, count);
        count += len;
        return this;
    }
    public StrBuffer append(char str[], int offset, int len) {
        if (len > 0)                // let arraycopy report AIOOBE for len < 0
            ensureCapacityInternal(count + len);
        System.arraycopy(str, offset, values, count, len);
        count += len;
        return this;
    }
    public StrBuffer append(char[] str) {
        return this.append(str,0,str.length);
    }
    public char getChar(int index){
        if ((index < 0) || (index >= count))
            throw new StringIndexOutOfBoundsException(index);
        return values[index];
    }
    public StrBuffer setChar(int index,char c){
        if ((index < 0) || (index >= count))
            throw new StringIndexOutOfBoundsException(index);
        values[index] = c;
        return this;
    }
    public void reset() {
        count = 0 ;
    }
    public StrBuffer append(Object obj) {
        return append(String.valueOf(obj));
    }
    private void ensureCapacityInternal(int minimumCapacity) {
        // overflow-conscious code
//        if (minimumCapacity - arraySize > 0)
//        {
//            new BufferOutIndexException("out of array size limit :"+arraySize);
//        }
    }
    public char[] toCharArray(){
        return Arrays.copyOf(values, count);
    }
    public String toString(){
        return new String(values,0,count);
    }

    @Override
    public void close() throws Exception {
       if(strBuffers!=null){
           strBuffers.returnThis(this);
       }
    }
}
