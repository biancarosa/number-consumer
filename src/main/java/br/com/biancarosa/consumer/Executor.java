package br.com.biancarosa.consumer;

public class Executor {

    /**
     * Executes the Consumer application
     *
     * @param args should contain the number of threads
     */
    public static void main(String[] args) {
        int numberOfThreads = 0;
        String host = "";
        int port = 0;
        try {
            try {
                numberOfThreads = Integer.parseInt(args[0]);
                host = args[1];
                try {
                    port = Integer.parseInt(args[2]);
                    for (int i = 1; i <= numberOfThreads; ++i) {
                        new Consumer("Consumidor"+i, host, port).start();
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Porta precisa ser um número");
                }
            } catch (NumberFormatException e) {
                System.err.println("Número de threads precisa ser um número");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Passe como argumento o número de threads, o host do buffer e a porta");
        }
    }

}
