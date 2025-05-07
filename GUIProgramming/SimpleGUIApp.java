import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUIApp {

    public static void main(String[] args) {

        JFrame frame = new JFrame("My Simple GUI App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

  
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout()); 


        JLabel greetingLabel = new JLabel("Hello, User!");
        JButton myButton = new JButton("OK");
        JTextField inputField = new JTextField(15); 
        JLabel messageLabel = new JLabel(""); 


        panel.add(greetingLabel);
        panel.add(myButton);
        panel.add(inputField);
        panel.add(messageLabel);

     
        frame.getContentPane().add(panel);

      
        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText();
                if (userInput.isEmpty()) {
                    messageLabel.setText("Please enter your name!");
                } else {
                    messageLabel.setText("Nice to meet you madafaqa, " + userInput + "!");
                }
                inputField.setText(""); 
            }
        });


        frame.setVisible(true);
    }
}
