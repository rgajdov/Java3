import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ChatClient {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8189;
    private static Socket socket = null;
    private static BufferedReader bufferReader = null;
    private static ObjectOutputStream oos = null;
    private static ObjectInputStream ois = null;
    private static Student student1;
    private static Student student2;

    public static void main(String[] args) {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            System.out.println("Подключен к серверу.");
            bufferReader = new BufferedReader(new InputStreamReader(System.in));
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            new Thread(() -> {
                System.out.println("Введите имя студента для сериализации и отправки объекта: ");
                try {
                    while (!socket.isOutputShutdown()) {
                        if (bufferReader.ready()) {
                            String studentName = bufferReader.readLine();
                            if (!studentName.isEmpty()) {
                                student1 = new Student(new Date().getTime(), studentName);
                                oos.writeObject(student1);
                                oos.flush();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    while (!socket.isOutputShutdown()) {
                        student2 = (Student) ois.readObject();
                        System.out.println("Десериализован объект: " + student2.info());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}



