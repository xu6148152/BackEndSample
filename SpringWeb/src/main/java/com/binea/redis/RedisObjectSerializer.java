package com.binea.redis;

/**
 * Created by binea
 * Date: 4/11/2017
 * TIME: 7:08 PM
 */

public class RedisObjectSerializer {

//    private Converter<Object, byte[]> serializer = new SerializingConverter();
//    private Converter<byte[], Object> deserializer = new DeserializingConverter();
//
//    static final byte[] EMPTY_ARRAY = new byte[0];
//
//    @Override
//    public byte[] serialize(Object o) throws SerializationException {
//        if (o == null) {
//            return EMPTY_ARRAY;
//        }
//
//        try {
//            return serializer.convert(o);
//        } catch (Exception e) {
//            return EMPTY_ARRAY;
//        }
//    }
//
//    @Override
//    public Object deserialize(byte[] bytes) throws SerializationException {
//        if (isEmpty(bytes)) {
//            return null;
//        }
//
//        try {
//            return deserializer.convert(bytes);
//        } catch (Exception e) {
//            throw new SerializationException("Cannot deserialize", e);
//        }
//    }
//
//    private boolean isEmpty(byte[] data) {
//        return data == null || data.length == 0;
//    }
}
