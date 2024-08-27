package clients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {
    private JTextArea textArea;
    private JTextField textField;
    private JButton sendButton;
    private JButton consultButton;
    private JTextArea messageHistoryArea;

    public ClientView() {
        setTitle("Cliente");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        messageHistoryArea = new JTextArea();
        messageHistoryArea.setEditable(false);
        add(new JScrollPane(messageHistoryArea), BorderLayout.EAST);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));


        textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 30));  
        panel.add(textField);

        sendButton = new JButton("Enviar");
        sendButton.setPreferredSize(new Dimension(300, 30));
        panel.add(sendButton);

        consultButton = new JButton("Consultar");
        consultButton.setPreferredSize(new Dimension(300, 30));  
        panel.add(consultButton);


        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        leftPanel.add(panel);

        add(leftPanel, BorderLayout.WEST);

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public String getMessage() {
        return textField.getText();
    }

    public void setMessage(String message) {
        textArea.append(message + "\n");
    }

    public void setMessageHistory(String messages) {
        messageHistoryArea.setText(messages);
    }

    public void addSendButtonListener(ActionListener listener) {
        sendButton.addActionListener(listener);
    }

    public void addConsultButtonListener(ActionListener listener) {
        consultButton.addActionListener(listener);
    }
}
