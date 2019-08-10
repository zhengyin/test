package com.izhengyin.test.rocketMQ;

import java.nio.ByteBuffer;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-08-09 14:47
 */
public class TypeUntils {
    private static ByteBuffer buffer = ByteBuffer.allocate(8);

    public static byte[] longToBytes(long x) {
        buffer.putLong(0, x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();//need flip
        return buffer.getLong();
    }

}
