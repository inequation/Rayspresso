package org.inequation.rayspresso;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Program about dialog.
 * @author inequation
 * @version 24.11.2011
 */
public final class AboutDialog extends JDialog {
    public AboutDialog(JFrame owner) {
        super(owner, "Rayspresso", true);
        
        initComponents();
    }
    
    public void initComponents() {
        setLayout(new BorderLayout());
        add(new JLabel("<html><center>"
            + "<b><font size=+3>Rayspresso</font></b>"
            + "<br><br>Written by Leszek Godlewski"
            + "</center></html>"),
            BorderLayout.NORTH);
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JButton b = (JButton)ae.getSource();
                JDialog d = (JDialog)SwingUtilities.getRoot(b);
                if (d == null)
                    return;
                d.setVisible(false);
            }
        });
        add(ok, BorderLayout.SOUTH);
        pack();
        this.setLocationRelativeTo(null);
    }
}
