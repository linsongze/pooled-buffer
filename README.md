Buffer ByteArrayOutputStream
=================

byte[] pool
  
## use default Buffer
```
   @Before
    public void init(){
        PoolConfig poolConfig = PoolConfig.createDefault();
        buffers=new ByteArrayBuffers(poolConfig);
    }
    @Test
    public void test() throws Exception {
        String s = "缓冲区";
        ByteArrayOutputStream outputStream = buffers.getByteArrayOutputStream();
        outputStream.write(s.getBytes());
        Assert.assertEquals(s,outputStream.toString());

        outputStream.close();

    }
    
```

## use direct memory Buffer
```
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
    
```
