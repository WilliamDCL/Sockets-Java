package clients;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientModel {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String serverAddress;
    private int serverPort;
    private String clientId;  
    private List<String> messages;

    public ClientModel(String serverAddress, int serverPort, String clientId) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.clientId = clientId;
        this.messages = new ArrayList<>();
    }

    public String getClientId() {
        return clientId;
    }

    public void connect() throws IOException {
        socket = new Socket(serverAddress, serverPort);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(clientId + ": " + message); 
        }
    }

    public void requestMessages() {
        if (out != null) {
            out.println("CONSULTAR");
        }
    }

    public void disconnect() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public String receiveMessage() throws IOException {
        return in.readLine();
    }
}
