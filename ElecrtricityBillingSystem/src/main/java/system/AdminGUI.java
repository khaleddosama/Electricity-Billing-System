package system;

import javax.swing.*;

public class AdminGUI {

    public AdminGUI(Admin admin) {

        JFrame f = new JFrame("Admin Panel");
        f.setSize(750, 550);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ================= Output Area =================
        JTextArea area = new JTextArea();
        area.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(20, 20, 700, 220);
        f.add(scroll);

        // ================= Inputs =================
        JLabel r = new JLabel("Region");
        r.setBounds(20, 260, 80, 25);
        f.add(r);

        JTextField region = new JTextField();
        region.setBounds(100, 260, 120, 25);
        f.add(region);

        JLabel uid = new JLabel("User ID");
        uid.setBounds(240, 260, 80, 25);
        f.add(uid);

        JTextField userID = new JTextField();
        userID.setBounds(320, 260, 120, 25);
        f.add(userID);

        JLabel mail = new JLabel("New Email");
        mail.setBounds(460, 260, 80, 25);
        f.add(mail);

        JTextField newEmail = new JTextField();
        newEmail.setBounds(550, 260, 160, 25);
        f.add(newEmail);

        // ================= Buttons =================
        JButton viewBills = new JButton("View Bills");
        viewBills.setBounds(20, 310, 150, 30);
        f.add(viewBills);

        JButton total = new JButton("Total Collected");
        total.setBounds(190, 310, 170, 30);
        f.add(total);

        JButton stats = new JButton("Consumption Stats");
        stats.setBounds(380, 310, 170, 30);
        f.add(stats);

        JButton delete = new JButton("Delete User");
        delete.setBounds(190, 360, 170, 30);
        f.add(delete);

        // ================= Add New User =================
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(20, 400, 80, 25);
        f.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(100, 400, 120, 25);
        f.add(nameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(240, 400, 80, 25);
        f.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(320, 400, 120, 25);
        f.add(emailField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(460, 400, 80, 25);
        f.add(passLabel);

        JTextField passField = new JTextField();
        passField.setBounds(550, 400, 120, 25);
        f.add(passField);

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setBounds(20, 440, 80, 25);
        f.add(typeLabel);

        String[] userTypes = {"NewCustomer", "OldCustomer", "Operator", "Admin"};
        JComboBox<String> typeBox = new JComboBox<>(userTypes);
        typeBox.setBounds(100, 440, 120, 25);
        f.add(typeBox);

        JButton addUserBtn = new JButton("Add User");
        addUserBtn.setBounds(240, 440, 150, 30);
        f.add(addUserBtn);

        // ================= Actions =================
        viewBills.addActionListener(e -> {
            if (region.getText().isEmpty()) {
                JOptionPane.showMessageDialog(f, "Enter region first");
                return;
            }
            area.setText(admin.viewBillsByRegion(region.getText()));
        });

        total.addActionListener(e ->
            area.setText(admin.viewTotalCollected())
        );

        stats.addActionListener(e -> {
            if (region.getText().isEmpty()) {
                JOptionPane.showMessageDialog(f, "Enter region first");
                return;
            }
            area.setText(admin.reportConsumptionStats(region.getText()));
        });

        delete.addActionListener(e -> {
            try {
                int id = Integer.parseInt(userID.getText());
                area.setText(admin.deleteUser(id));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Invalid User ID");
            }
        });

        addUserBtn.addActionListener(e -> {
            try {
                String n = nameField.getText().trim();
                String em = emailField.getText().trim();
                String pw = passField.getText().trim();
                String type = (String) typeBox.getSelectedItem();

                if (n.isEmpty() || em.isEmpty() || pw.isEmpty()) throw new Exception();

                User newUser;

                // ======== FIXED ID GENERATION (التعديل الوحيد) ========
                int newId = 1;
                for (User u : DataStore.users) {
                    if (u.getId() >= newId) {
                        newId = u.getId() + 1;
                    }
                }
                // =====================================================

                switch (type) {
                    case "NewCustomer":
                        newUser = new NewCustomer(newId, n, em, pw);
                        break;
                    case "OldCustomer":
                        long meterCode = (long) (Math.random() * 100000 + 1);
                        newUser = new OldCustomer(newId, n, em, pw, meterCode);
                        break;
                    case "Operator":
                        newUser = new Operator(newId, n, em, pw);
                        break;
                    case "Admin":
                        newUser = new Admin(newId, n, em, pw);
                        break;
                    default:
                        throw new Exception();
                }

                area.setText(admin.addUser(newUser));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Please enter valid Name, Email, Password and Type");
            }
        });

        f.setVisible(true);
    }
}
