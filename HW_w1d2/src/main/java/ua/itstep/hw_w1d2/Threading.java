/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ua.itstep.hw_w1d2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author pronc
 */
public class Threading {
    
    public void demo() {
        demoRandomString();
    }
    
static class RandomCharTask implements Callable<Character> {
        private static final char[] ALPHABET 
                = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        @Override
        public Character call() throws Exception {
            Thread.sleep(ThreadLocalRandom.current().nextInt(2, 15));
            return ALPHABET[ThreadLocalRandom.current().nextInt(ALPHABET.length)];
        }
    }

    public String generateRandomString(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length must be >= 0");
        }
        if (length == 0) {
            return "";
        }
        int threads = Math.min(length, Runtime.getRuntime().availableProcessors());
        ExecutorService pool = Executors.newFixedThreadPool(threads);

        try {
            List<Future<Character>> futures = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                futures.add(pool.submit(new RandomCharTask()));
            }

            char[] out = new char[length];
            for (int i = 0; i < length; i++) {
                try {
                    out[i] = futures.get(i).get();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted while waiting char #" + i, e);
                } catch (ExecutionException e) {
                    throw new RuntimeException("Char task failed at index " + i, e.getCause());
                }
            }
            return new String(out);
        } finally {
            pool.shutdown();
        }
    }

    private void demoRandomString() {
        long t = System.nanoTime();
        String s = generateRandomString(24);
        System.out.printf("Generated in %.1f ms: %s%n", (System.nanoTime() - t) / 1e6, s);
    }
}
