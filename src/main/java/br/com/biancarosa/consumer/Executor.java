package br.com.biancarosa.consumer;

public class Executor {

    /**
     * Executes the Consumer application
     *
     * @param args should contain the number of threads
     */
    public static void main(String[] args) {
        int numberOfThreads = 0;
        try {
            numberOfThreads = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong number of args provided");
        } catch (NumberFormatException e) {
            System.err.println("Number of threads must be a number");
        }

        for (int i = 1; i <= numberOfThreads; ++i) {
            new Consumer("Consumer"+i).start();
        }

    }

}
