package br.com.biancarosa.consumer;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Consumer extends Thread  {

    private String name;
    static boolean wait = false;

    public Consumer(String name) {
        this.name = name;
    }

    private Integer getNumberFromBuffer() {
        String host = "127.0.0.1";
        try {
            Socket echoSocket = new Socket(host, 8000);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            out.println(name);
            System.out.println("Asked number from buffer");

            //Get the return message from the server
            InputStream is = echoSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("Message received from the server : " + message);

            String[] messArgs = message.split("\\?");

            Integer number = null;
            // Checks if empty
            if (messArgs[1].equals("empty")) {
                Consumer.wait = true;
            } else {
                try {
                    number = Integer.parseInt(messArgs[1]);
                } catch (NumberFormatException e) {
                    System.err.println("Not a number");
                }
            }

            echoSocket.close();

            return number;
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host);
        }

        return null;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("Asking number to buffer");
            Integer n = getNumberFromBuffer();
            if (n != null) {
                System.out.println("Got "+n+" from buffer");
            }
            try {
                if (Consumer.wait) {
                    Thread.sleep(60000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
