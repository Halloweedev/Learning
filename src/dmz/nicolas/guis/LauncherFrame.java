package dmz.nicolas.guis;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class LauncherFrame extends JFrame implements ActionListener {

    public static int widht = 600;
    public static int height = 600;
    public static String imgURL = "icefeeling.png";

    public static JFrame frame = new JFrame("IceFeeling");
    public static JButton buttonSite = new JButton("Site");
    public static JPanel panel = new JPanel();

    public LauncherFrame() {


        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(widht, height);

        panel.setSize(new Dimension(widht, height));
        panel.setBackground(Color.darkGray);

       // buttonSite.setForeground(new Color(0, 0, 0));
        //buttonSite.setBounds(25, 25, 100, 100);
       // buttonSite.setOpaque(true);
        buttonSite.setBackground(Color.RED);

        buttonSite.setBorderPainted(true);
        buttonSite.setBounds(25, 25, 100, 100);
        //buttonSite.setFocusPainted(false);
        //buttonSite.setContentAreaFilled(false);
        buttonSite.addActionListener(this);

        frame.add(panel);
        panel.add(buttonSite);


        //Display the window
        panel.setVisible(true);
        frame.setVisible(true);

        //Display buttons
        //buttonSite.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (buttonSite.isEnabled())
        {
            System.out.println("Clicked");
        }
    }
}
