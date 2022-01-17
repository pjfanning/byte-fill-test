package org.example;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Arrays;

@State(Scope.Benchmark)
public class Bench {
    private final int byteLength = 10000000;
    private final byte[] bytes = new byte[byteLength];
    private final byte fillValue = (byte) 1;

    @Benchmark
    public void arrayFill() {
        Arrays.fill(bytes, fillValue);
    }

    @Benchmark
    public void systemCopy() {
        if (byteLength > 0) {
            bytes[0] = fillValue;
        }

        for (int i = 1; i < byteLength; i += i) {
            System.arraycopy(bytes, 0, bytes, i, Math.min(byteLength - i, i));
        }
    }
}