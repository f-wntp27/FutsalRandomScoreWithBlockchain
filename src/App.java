import java.awt.BorderLayout;

import javax.swing.JFrame;

import ui.TabbedWindowPanel;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Futsal e-Sport Blockchain");
        frame.setBounds(25, 25, 550, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(new TabbedWindowPanel(), BorderLayout.CENTER);
    }
}
