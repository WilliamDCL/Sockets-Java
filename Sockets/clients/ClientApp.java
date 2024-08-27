package clients;

public class ClientApp {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 10000;
        String clientId = " ";

        ClientModel model = new ClientModel(serverAddress, serverPort,clientId );
        ClientView view = new ClientView();
        ClientPresenter presenter = new ClientPresenter(model, view);

        view.setVisible(true);
        presenter.start();
    }
}
