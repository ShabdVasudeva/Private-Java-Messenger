import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {
    private static final int PORT = 12345;
    private static final Map<String, ClientHandler> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started on port " + PORT);

        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new ClientHandler(socket)).start();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private String username;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println("Enter your username:");
                username = in.readLine();
                clients.put(username, this);
                out.println("Welcome, " + username + "! Type @username message to chat.");

                String line;
                while ((line = in.readLine()) != null) {
                    if (line.startsWith("@")) {
                        int spaceIndex = line.indexOf(" ");
                        if (spaceIndex != -1) {
                            String recipient = line.substring(1, spaceIndex);
                            String msg = line.substring(spaceIndex + 1);
                            ClientHandler client = clients.get(recipient);
                            if (client != null) {
                                client.out.println("[Private] " + username + ": " + msg);
                            } else {
                                out.println("User '" + recipient + "' not found.");
                            }
                        }
                    } else {
                        out.println("Use @username <message> format to send private messages.");
                    }
                }
            } catch (IOException e) {
                System.out.println("Connection error: " + e.getMessage());
            } finally {
                try {
                    if (username != null) clients.remove(username);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
