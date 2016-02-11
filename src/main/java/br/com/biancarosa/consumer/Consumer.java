package br.com.biancarosa.consumer;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class Consumer extends Thread  {

    private String name;
    private String host;
    private int port;
    static boolean wait = false;

    public Consumer(String name, String host, int port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    private void getNumberFromBuffer() {
        try {
            Date startDate = new Date();
            Socket echoSocket = new Socket(this.host, this.port);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            out.println(this.name);

            //Get the return message from the server
            InputStream is = echoSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();

            String[] messArgs = message.split("\\?");
            // Checks if empty
            if (messArgs[1].equals("empty")) {
                Consumer.wait = true;
                System.out.println(name + " tentou retirar item do Buffer vazio");
            } else {
                int number = Integer.parseInt(messArgs[1]);
                Date endDate = new Date();
                long timeDiff = endDate.getTime() - startDate.getTime();
                System.out.println("Retirado o valor " + number  + " no Buffer pelo " + name + " em " + timeDiff + "ms");
            }
            echoSocket.close();
        }  catch (UnknownHostException e) {
            System.err.println("Host desconhecido : " + host);
        } catch (IOException e) {
            System.err.println("Não foi possível conectar ao host " + host);
        }

    }

    @Override
    public void run() {
        while(true) {
            getNumberFromBuffer();
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
