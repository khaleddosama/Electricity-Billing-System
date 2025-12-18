package system;

import javax.swing.*;

public class OldCustomerGUI {

    public OldCustomerGUI(OldCustomer c) {

        JFrame f = new JFrame("Old Customer Panel");
        f.setSize(700, 550);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea();
        area.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 13));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(20, 20, 650, 200);
        f.add(scroll);

        /* ================= Pay Bill ================= */
        JLabel l1 = new JLabel("Meter Code");
        l1.setBounds(20, 240, 100, 25);
        f.add(l1);

        JTextField meter = new JTextField();
        meter.setBounds(120, 240, 120, 25);
        f.add(meter);

        JLabel l2 = new JLabel("Payment");
        l2.setBounds(260, 240, 100, 25);
        f.add(l2);

        JTextField payment = new JTextField();
        payment.setBounds(350, 240, 120, 25);
        f.add(payment);

        JButton pay = new JButton("Pay Bill");
        pay.setBounds(500, 240, 150, 25);
        f.add(pay);

        /* ================= Complaint ================= */
        JLabel l3 = new JLabel("Complaint");
        l3.setBounds(20, 280, 100, 25);
        f.add(l3);

        JTextField complaint = new JTextField();
        complaint.setBounds(120, 280, 350, 25);
        f.add(complaint);

        JButton complainBtn = new JButton("Send Complaint");
        complainBtn.setBounds(500, 280, 150, 25);
        f.add(complainBtn);

        /* ================= Unpaid Months ================= */
        JLabel l4 = new JLabel("Unpaid Months");
        l4.setBounds(20, 320, 120, 25);
        f.add(l4);

        JTextField months = new JTextField();
        months.setBounds(150, 320, 100, 25);
        f.add(months);

        JButton check = new JButton("Check Unpaid");
        check.setBounds(500, 320, 150, 25);
        f.add(check);

        /* ================= Monthly Reading ================= */
        JLabel l5 = new JLabel("Monthly Reading");
        l5.setBounds(20, 360, 120, 25);
        f.add(l5);

        JTextField reading = new JTextField();
        reading.setBounds(150, 360, 100, 25);
        f.add(reading);

        JButton setReading = new JButton("Set Reading");
        setReading.setBounds(500, 360, 150, 25);
        f.add(setReading);

        /* ================= Actions ================= */
        pay.addActionListener(e -> {
            try {
                long m = Long.parseLong(meter.getText());
                double p = Double.parseDouble(payment.getText());
                area.setText(c.payBill(m, p));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Please enter valid meter code and payment");
            }
        });

        complainBtn.addActionListener(e -> {
            try {
                String msg = complaint.getText().trim();
                if (msg.isEmpty()) throw new Exception();
                area.setText(c.complaintAboutBill(msg));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Please enter a complaint message");
            }
        });

        check.addActionListener(e -> {
            try {
                int m = Integer.parseInt(months.getText());
                area.setText(c.checkUnpaidMonths(m));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Please enter valid unpaid months");
            }
        });

        setReading.addActionListener(e -> {
            try {
                int r = Integer.parseInt(reading.getText());
                c.setMonthlyReading(r);
                DataStore.saveUsers();
                area.setText("Monthly reading set successfully to: " + r);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Please enter valid monthly reading");
            }
        });

        f.setVisible(true);
    }
}