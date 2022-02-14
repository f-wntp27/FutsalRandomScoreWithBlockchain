package ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class TabbedWindowPanel extends JPanel {
    public static JTabbedPane tabbedPane;
    public TabbedWindowPanel() {
        super(new GridLayout(1, 1));
        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Fill Team Name", new FillTeamNamePanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
}
