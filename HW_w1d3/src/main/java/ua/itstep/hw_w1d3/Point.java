/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ua.itstep.hw_w1d3;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * @author pronc
 */
public class Point {
    private final int x;
    private final int y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    private long startTime;
    private final Random randGen = new Random();


    public void demo() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        startTime = System.nanoTime();

        Future<?> task = CompletableFuture
                .supplyAsync(coordsSupplier, threadPool)
                .thenApply(pointBuilder)
                .thenApply(signAnalyzer)
                .thenAccept(printer);

        try {
            task.get();
            threadPool.shutdown();
            threadPool.awaitTermination(3, TimeUnit.SECONDS);
            threadPool.shutdownNow();
        } catch (Exception e) {
            log("Error 0v0000015f1782!!!" + e.getMessage());
        }
    }


    private final Supplier<int[]> coordsSupplier = () -> {
        log("Supplier - Start");
        pause();
        int coordX = randGen.nextInt(-10, 11);
        int coordY = randGen.nextInt(-10, 11);
        log("Supplier - End: coordinates=[" + coordX + ", " + coordY + "]");
        return new int[]{coordX, coordY};
    };

    private final Function<int[], Point> pointBuilder = coords -> {
        log("PointBuilder: building =[" + coords[0] + ", " + coords[1] + "]");
        pause();
        Point point = new Point(coords[0], coords[1]);
        log("PointBuilder: completed - point=" + point);
        return point;
    };

    private final Function<Point, String> signAnalyzer = point -> {
        log("SignAnalyzer: analyzing point=" + point);
        pause();

        int px = point.getX();
        int py = point.getY();
        String analysis;

        if (px == 0 && py == 0) {
            analysis = point + " -> origin";
        } else if (px == 0) {
            analysis = point + " -> on Y axis";
        } else if (py == 0) {
            analysis = point + " -> on X axis";
        } else if (px > 0 && py > 0) {
            analysis = point + " -> Quadrant 1";
        } else if (px < 0 && py > 0) {
            analysis = point + " -> Quadrant 2";
        } else if (px < 0 && py < 0) {
            analysis = point + " -> Quadrant 3";
        } else {
            analysis = point + " -> Quadrant 4";
        }

        log("SignAnalyzer: result - " + analysis);
        return analysis;
    };

    private final Consumer<String> printer = output -> {
        log("Printer: begin");
        pause();
        log("Printer: end - " + output);
    };

    private void log(String message) {
        System.out.printf("%.1f ms  %s%n", elapsedMs(), message);
    }

    private double elapsedMs() {
        return (System.nanoTime() - startTime) / 1e6;
    }

    private void pause() {
        try {
            Thread.sleep(randGen.nextInt(123, 555));
        } catch (InterruptedException ex) {
            log("Interupted!!" + ex.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
