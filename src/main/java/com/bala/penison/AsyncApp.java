package com.bala.penison;

import java.util.concurrent.*;

public class AsyncApp {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2); // Thread pool

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Child thread started...");
            try {
                Thread.sleep(5000);  
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  
                throw new RuntimeException(e);
            }
            System.out.println("Child thread completed.");
            return "Result from child thread";
        }, executor);

        System.out.println("Main thread continuing...");

        // Callback handling (without blocking):
        future.thenAccept(result -> {
            System.out.println("Callback received: " + result);
        });

        System.out.println("Main thread doing other work...");
        Thread.sleep(2000); // Main thread does some other work

        // Shutdown the executor when done
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES); // Wait for tasks to complete
    }
}