import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ChatServer {
    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;
    private static BufferedReader bufferReader = null;
    private static ObjectOutputStream oos = null;
    private static ObjectInputStream ois = null;
    private static Student studentIn;
    private static Student studentOut;

    public static void main(String[] args) {
        // write your code here
        try {
            serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");
            clientSocket = serverSocket.accept();
            System.out.println("Клиент подключился.");
            bufferReader = new BufferedReader(new InputStreamReader(System.in));
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());

            new Thread(() -> {
                System.out.println("Введите имя студента для сериализации и отправки объекта: ");
                try {
                    while (true) {
                        if (bufferReader.ready()) {
                            String studentName = bufferReader.readLine();
                            if (!studentName.isEmpty()) {
                                studentOut = new Student(new Date().getTime(), studentName);
                                oos.writeObject(studentOut);
                                oos.flush();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    oos.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    while (true) {
                        studentIn = (Student) ois.readObject();
                        System.out.println("Десериализован объект: " + studentIn.info());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    ois.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера!");
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
