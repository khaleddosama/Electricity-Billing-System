package system;

import javax.swing.*;

public class LoginGUI {

    public LoginGUI() {

        DataStore.loadUsers(); 

        JFrame f = new JFrame("Login");
        f.setSize(300, 200);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null); 

        JLabel l1 = new JLabel("Email");
        l1.setBounds(20, 20, 80, 25);
        f.add(l1);

        JTextField email = new JTextField();
        email.setBounds(100, 20, 150, 25);
        f.add(email);

        JLabel l2 = new JLabel("Password");
        l2.setBounds(20, 60, 80, 25);
        f.add(l2);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(100, 60, 150, 25);
        f.add(pass);

        JButton login = new JButton("Login");
        login.setBounds(100, 100, 80, 30);
        f.add(login);

        login.addActionListener(e -> {

            String em = email.getText().trim();
            String pw = new String(pass.getPassword()).trim();

            boolean loggedIn = false;

            for (User u : DataStore.users) {
                if (u.getEmail().equalsIgnoreCase(em) && u.getPassword().equals(pw)) {
                    if (u instanceof Admin) new AdminGUI((Admin) u);
                    else if (u instanceof Operator) new OperatorGUI((Operator) u);
                    else if (u instanceof OldCustomer) new OldCustomerGUI((OldCustomer) u);
                    else if (u instanceof NewCustomer) new NewCustomerGUI((NewCustomer) u);

                    f.dispose();
                    loggedIn = true;
                    break;
                }
            }

            if (!loggedIn) {
                JOptionPane.showMessageDialog(f, "Invalid Email or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        f.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginGUI();
    }
}