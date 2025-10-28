package gc.garcol.totalorderingarchitecttimer.util;

import lombok.NoArgsConstructor;

/**
 * @author thaivc
 * @since 2025
 */
@NoArgsConstructor
public class ByteUtils {

    public static byte[] fromLong(long value) {
        byte[] bytes = new byte[Long.BYTES];

        for (int i = 7; i >= 0; i--) {
            bytes[i] = (byte) (value & 0xFF);
            value >>= Long.BYTES;
        }
        return bytes;
    }

    public static long toLong(byte[] bytes) {
        if (bytes == null || bytes.length != Long.BYTES) {
            throw new IllegalArgumentException("Byte array must be 8 bytes long");
        }

        long value = 0;
        for (int i = 0; i < Long.BYTES; i++) {
            value = (value << Long.BYTES) | (bytes[i] & 0xFF);
        }
        return value;
    }

}
