package org.inequation.rayspresso;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import org.inequation.rayspresso.renderer.RenderType;
import org.inequation.rayspresso.renderer.Scene;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;

/**
 * View class for the main frame.
 * @author inequation
 * @version 24.11.2011
 */
public class MainFrameView extends FrameView {
    public MainFrameView(SingleFrameApplication app) {
        super(app);

        initComponents();
    }
     
    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setMinimumSize(new Dimension(100, 100));
        mainPanel.setLayout(new BorderLayout());
                
        m_imgPanel = new ImagePanel();
        
        JPanel renderPanel = new JPanel();
        renderPanel.setLayout(new BorderLayout());
        
        m_settingsPanel = new JPanel();
        m_settingsPanel.setMinimumSize(new Dimension(300, 300));
        m_settingsPanel.setLayout(new GridLayout(5, 2));
        
        m_w = new JSpinner(new SpinnerNumberModel(640, 1, 2048, 1));
        m_h = new JSpinner(new SpinnerNumberModel(480, 1, 2048, 1));
        m_zNear = new JFormattedTextField(NumberFormat.getNumberInstance());
        m_zNear.setValue(0.1f);
        m_zFar = new JFormattedTextField(NumberFormat.getNumberInstance());
        m_zFar.setValue(64.f);
        m_mode = new JComboBox(RenderType.values());
        
        m_settingsPanel.add(new JLabel("Width"));
        m_settingsPanel.add(m_w);
        m_settingsPanel.add(new JLabel("Height"));
        m_settingsPanel.add(m_h);
        m_settingsPanel.add(new JLabel("Near plane Z"));
        m_settingsPanel.add(m_zNear);
        m_settingsPanel.add(new JLabel("Far plane Z"));
        m_settingsPanel.add(m_zFar);
        m_settingsPanel.add(new JLabel("Render mode"));
        m_settingsPanel.add(m_mode);
        
        renderPanel.add(m_settingsPanel, BorderLayout.WEST);
        renderPanel.add(m_imgPanel, BorderLayout.CENTER);
        
        m_sceneTable = new JTable(new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return Scene.getInstance().getTraceables().size()
                    + Scene.getInstance().getLights().size();
            }

            @Override
            public int getColumnCount() {
                return 2;
            }

            @Override
            public Object getValueAt(int i, int i1) {
                Object o = null;
                if (i < Scene.getInstance().getTraceables().size())
                    o = Scene.getInstance().getTraceables().get(i);
                else {
                    i -= Scene.getInstance().getTraceables().size();
                    o = Scene.getInstance().getLights().get(i);
                }
                if (i1 == 0)
                    return o.toString();
                return o.hashCode();
            }
            
            @Override
            public String getColumnName(int col) {
                if (col == 0)
                    return "Type";
                return "Hashcode";
            }
            
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        });
        m_sceneTable.setMinimumSize(new Dimension(400, 100));
        
        m_tabbedPane = new JTabbedPane();
        m_tabbedPane.setMinimumSize(new Dimension(400, 100));
        m_tabbedPane.addTab("Render", renderPanel);
        m_tabbedPane.addTab("Scene objects", m_sceneTable);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem miq = new JMenuItem("Quit");
        miq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Rayspresso.confirmQuit();
            }
        });
        JMenuItem mia = new JMenuItem("About");
        mia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AboutDialog ad = new AboutDialog(
                    ((Rayspresso)Rayspresso.getInstance()).getMainFrame());
                ad.setVisible(true);
            }
        });
        menu.add(mia);
        menu.add(miq);
        menuBar.add(menu);
        
        JToolBar toolbar = new JToolBar();
        m_btn = new JButton("Render!");
        m_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                BufferedImage bi = new BufferedImage(
                        (Integer)m_w.getValue(),
                        (Integer)m_h.getValue(),
                        BufferedImage.TYPE_INT_ARGB);
                float zNear, zFar;
                try {
                    zNear = (Float)m_zNear.getValue();
                } catch (ClassCastException ex) {
                    zNear = ((Long)m_zNear.getValue()).floatValue();
                }
                try {
                    zFar = (Float)m_zFar.getValue();
                } catch (ClassCastException ex) {
                    zFar = ((Long)m_zFar.getValue()).floatValue();
                }
                Rayspresso.render(zNear, zFar,
                    (RenderType)m_mode.getSelectedItem(), bi);
                m_imgPanel.setImage(bi);
            }
        });
        toolbar.add(m_btn);
        
        mainPanel.add(toolbar, BorderLayout.PAGE_START);
        mainPanel.add(m_tabbedPane, BorderLayout.CENTER);
        
        setMenuBar(menuBar);
        setComponent(mainPanel);
        
        getFrame().setTitle("Rayspresso");
        getFrame().setDefaultCloseOperation(0);
        getFrame().addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent we) {
            }

            @Override
            public void windowClosing(WindowEvent we) {
                Rayspresso.confirmQuit();
            }

            @Override
            public void windowClosed(WindowEvent we) {
            }

            @Override
            public void windowIconified(WindowEvent we) {
            }

            @Override
            public void windowDeiconified(WindowEvent we) {
            }

            @Override
            public void windowActivated(WindowEvent we) {
            }

            @Override
            public void windowDeactivated(WindowEvent we) {
            }            
        });
    }
    
    private JTabbedPane m_tabbedPane;
    private JTable m_sceneTable;
    private ImagePanel m_imgPanel;
    private JPanel m_settingsPanel;
    private JSpinner m_w;
    private JSpinner m_h;
    private JComboBox m_mode;
    private JFormattedTextField m_zNear;
    private JFormattedTextField m_zFar;
    private JButton m_btn;
}
