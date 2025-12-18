package system;

import javax.swing.*;

public class OperatorGUI {

    public OperatorGUI(Operator op) {

        JFrame f = new JFrame("Operator Panel");
        f.setSize(720, 500);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea();
        area.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 13));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(20, 20, 670, 200);
        f.add(scroll);

        // ================= Collect Payment =================
        JLabel l1 = new JLabel("Bill ID");
        l1.setBounds(20, 240, 100, 25);
        f.add(l1);

        JTextField billID = new JTextField();
        billID.setBounds(120, 240, 100, 25);
        f.add(billID);

        JLabel l2 = new JLabel("Amount");
        l2.setBounds(240, 240, 100, 25);
        f.add(l2);

        JTextField amount = new JTextField();
        amount.setBounds(330, 240, 100, 25);
        f.add(amount);

        JButton collect = new JButton("Collect Payment");
        collect.setBounds(470, 240, 200, 25);
        f.add(collect);

        // ================= View Region Bills =================
        JLabel l3 = new JLabel("Region");
        l3.setBounds(20, 280, 100, 25);
        f.add(l3);

        JTextField region = new JTextField();
        region.setBounds(120, 280, 100, 25);
        f.add(region);

        JButton view = new JButton("View Region Bills");
        view.setBounds(470, 280, 200, 25);
        f.add(view);

        // ================= Define Tariff =================
        JLabel l4 = new JLabel("New Tariff");
        l4.setBounds(20, 320, 100, 25);
        f.add(l4);

        JTextField tariff = new JTextField();
        tariff.setBounds(120, 320, 100, 25);
        f.add(tariff);

        JButton setTariff = new JButton("Set Tariff");
        setTariff.setBounds(470, 320, 200, 25);
        f.add(setTariff);

        // ================= Cancel Subscription =================
        JLabel l5 = new JLabel("Meter Code");
        l5.setBounds(20, 360, 100, 25);
        f.add(l5);

        JTextField meterCode = new JTextField();
        meterCode.setBounds(120, 360, 100, 25);
        f.add(meterCode);

        JButton cancel = new JButton("Cancel Subscription");
        cancel.setBounds(470, 360, 200, 25);
        f.add(cancel);

        // ================= Actions =================
        collect.addActionListener(e -> {
            try {
                long id = Long.parseLong(billID.getText().trim());
                double amt = Double.parseDouble(amount.getText().trim());
                area.setText(op.collectPayment(id, amt));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Invalid Bill ID or Amount");
            }
        });

        view.addActionListener(e -> {
            String reg = region.getText().trim();
            if (reg.isEmpty()) {
                JOptionPane.showMessageDialog(f, "Please enter a region name");
                return;
            }
            String result = op.viewRegionBills(reg);
            if (result.isEmpty()) result = "No bills found in this region.";
            area.setText(result);
        });

        setTariff.addActionListener(e -> {
            try {
                double t = Double.parseDouble(tariff.getText().trim());
                area.setText(op.defineTariff(t));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Invalid tariff value");
            }
        });

        cancel.addActionListener(e -> {
            try {
                long m = Long.parseLong(meterCode.getText().trim());
                area.setText(op.cancelSubscription(m));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Invalid meter code");
            }
        });

        f.setVisible(true);
    }
}