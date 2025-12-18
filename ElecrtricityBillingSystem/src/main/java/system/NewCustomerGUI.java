package system;

import javax.swing.*;

public class NewCustomerGUI {

    public NewCustomerGUI(NewCustomer nc) {

        JFrame f = new JFrame("New Customer Panel");
        f.setSize(500, 400);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea infoArea = new JTextArea(nc.getAllInformation());
        infoArea.setBounds(20, 20, 440, 150);
        infoArea.setEditable(false);
        f.add(infoArea);

        JButton attachContract = new JButton("Attach Contract");
        attachContract.setBounds(20, 190, 150, 30);
        f.add(attachContract);

        JButton checkMeter = new JButton("Check Meter Code");
        checkMeter.setBounds(200, 190, 180, 30);
        f.add(checkMeter);

        attachContract.addActionListener(e -> {
            String path = JOptionPane.showInputDialog(f, "Enter contract path:");
            if (path != null && !path.isEmpty()) {
                nc.attachContract(path);
                infoArea.setText(nc.getAllInformation());
            }
        });

        checkMeter.addActionListener(e -> {
            long code = nc.checkMeterCode();
            JOptionPane.showMessageDialog(f, "Meter code: " + code);
            infoArea.setText(nc.getAllInformation());
        });

        f.setVisible(true);
    }
}