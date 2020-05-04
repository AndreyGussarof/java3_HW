package homework2.Client2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//implements Initializable
public class Controller  {

    @FXML
    public Button Enter;
    @FXML
    public TextArea textArea;
    @FXML
    public TextField textField;
    @FXML
    public HBox upperPanel;
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public HBox bottomPanel;

    private boolean isAuthorized ;

    Socket socket;
    DataOutputStream out;
    DataInputStream in;

    final String IP_ADRESS = "localhost";
    final int PORT = 8189;

    public void setAuthorized(boolean isAuthorized){
    this.isAuthorized = isAuthorized;
    if(isAuthorized){
        upperPanel.setVisible(false);
        upperPanel.setManaged(false);
        bottomPanel.setVisible(true);
        bottomPanel.setVisible(true);
    }else{
        upperPanel.setVisible(true);
        upperPanel.setManaged(true);
        bottomPanel.setVisible(false);
        bottomPanel.setVisible(false);
    }
    }


    public void connect() {
        try {
            socket = new Socket(IP_ADRESS,PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //цикл авторизации
                        while (true){
                            String str =in.readUTF();
                            if(str.equals("/authok")){
                             setAuthorized(true);
                                break;
                            }
                        textArea.appendText(str + "\n");
                        }
                      // цикл работы
                        while (true){
                        String str =in.readUTF();

                        if(str.equals("/end")){
                            System.out.println("Клиент отключился");
                            break;
                        }

                        textArea.appendText(str + "\n");
                    }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthorized(false);
                    }
                }

            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onActionTextField(ActionEvent actionEvent) {

        try {
            out.writeUTF(textField.getText() + "\n");
            textField.setText("");
            textField.requestFocus();

        } catch (IOException e) {
            e.printStackTrace();
        }
            }

    public void tryToAuth(ActionEvent actionEvent) {
        if(socket==null||socket.isClosed()){
            connect();
        }

        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickClientList(MouseEvent mouseEvent) {
    }

    public void tryToReg(ActionEvent actionEvent) {

    }

    public void sendMsg(ActionEvent actionEvent) {

    }
}