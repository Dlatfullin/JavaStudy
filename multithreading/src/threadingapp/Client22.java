package threadingapp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        try {
            Socket socket = new Socket("127.0.0.1", 7500); // 1 - localadress\

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());


            System.out.println("Dollar to");
            System.out.println("[1] KZT");
            System.out.println("[2] RUB");
            System.out.println("[3] EUR");

            int choice = scanner.nextInt();
            String currency = null;
            ApiJsonData apiJsonData = null;
            switch (choice) {
                case 1:
                    currency = "USDKZT";
                    break;
                case 2:
                    currency = "USDRUB";
                    break;
                case 3:
                    currency = "USDEUR";
                    break;
                default:
                    System.out.println("Error");
                    break;
            }

            apiJsonData = new ApiJsonData(currency);

            objectOutputStream.writeObject(apiJsonData);
            objectOutputStream.flush();

            ReqAndREsponse result = (ReqAndREsponse) objectInputStream.readObject();

            System.out.println("Res: " + result.getMoney());

            objectOutputStream.close();
            objectInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
