package org.inequation.rayspresso;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Simple image-drawing panel component.
 * @author inequation
 * @version 24.11.2011
 */
public class ImagePanel extends JPanel {
    public ImagePanel() {
        super();
        setMinimumSize(new Dimension(100, 100));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        if (m_img != null)
            g.drawImage(m_img, 0, 0, null);
    }
    
    public void setImage(BufferedImage img) {
        m_img = img;
        setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        setSize(getPreferredSize());
        validate();
        repaint();
    }
    
    private BufferedImage m_img = null;
}
