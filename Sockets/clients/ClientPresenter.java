package clients;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientPresenter {
    private ClientModel model;
    private ClientView view;

    public ClientPresenter(ClientModel model, ClientView view) {
        this.model = model;
        this.view = view;

        view.addSendButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = view.getMessage();
                model.sendMessage(message);
                view.setMessage("Cliente " + model.getClientId() + ": " + message);  
            }
        });

        view.addConsultButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.requestMessages();
            }
        });
    }

    public void start() {
        try {
            model.connect();
            new Thread(() -> {
                try {
                    String message;
                    while ((message = model.receiveMessage()) != null) {
                        if (message.equals("FIN")) {
                            break;
                        }
                        view.setMessage("Servidor: " + message);  
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            model.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
