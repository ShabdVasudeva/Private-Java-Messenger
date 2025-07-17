import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_PORT = "Host's Ip Address here.....";
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_PORT, PORT);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader systemInput = new BufferedReader(new InputStreamReader(System.in));

        new Thread(() -> {
            try {
                String msg;
                while ((msg = input.readLine()) != null){
                    System.out.println(msg);
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }).start();

        String line;
        while ((line = systemInput.readLine()) != null) {
            output.println(line);
        }
    }
}
