package threadingapp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    //Server -> Client1
    //       -> Client2
    public static void main(String[] args) {


        // conversation

        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            ServerSocket serverSocket = new ServerSocket(7500);
            System.out.println("Server starts!!!");

            while(true) {
                Socket socket = serverSocket.accept(); // waits clients
                System.out.println("Client Connected");

                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                ObjectInputStream finalObjectInputStream = objectInputStream;
                ObjectOutputStream finalObjectOutputStream = objectOutputStream;
                new Thread(() -> {
                    try {
                        ApiJsonData apiJsonData = (ApiJsonData) finalObjectInputStream.readObject();
                        finalObjectOutputStream.writeObject(apiJsonData.getResult());
                        finalObjectOutputStream.flush();

                        finalObjectOutputStream.close();
                        finalObjectInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
