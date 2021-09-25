package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class currentObjects extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea textArea;

    public currentObjects(Frame parent) {
        super(parent, "Текущие объекты");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        for (int i = 0; i < Control.list.size(); i++) {
            textArea.append(i + 1 + ". " + Control.list.get(i).getName() + " - ");

            textArea.append(Control.BornList.get(Control.list.get(i).getId()) + "\n");
        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        dispose();
    }

    public static void main(String[] args) {
        currentObjects dialog = new currentObjects(new JFrame());
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
