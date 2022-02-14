package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import blockchain.Block;
import struct.League;
import struct.Team;
import struct.TeamTable;
import util.Constant;

public class FillTeamNamePanel extends JPanel implements ActionListener {
    private JLabel title;
    private JLabel lb_team_a;
    private JLabel lb_team_b;
    private JLabel lb_team_c;
    private JLabel lb_team_d;
    private JTextField tf_team_a;
    private JTextField tf_team_b;
    private JTextField tf_team_c;
    private JTextField tf_team_d;
    private JButton startButton;

    private JLabel warning;

    public FillTeamNamePanel() {
        setLayout(null);

        title = new JLabel("Fill Futsal Team Name");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 20));
        title.setSize(300, 50);
        title.setLocation(175, 0);
        add(title);

        lb_team_a = new JLabel("Enter Team A:");
        lb_team_a.setFont(new Font(lb_team_a.getFont().getName(), Font.PLAIN, 15));
        lb_team_a.setSize(120, 20);
        lb_team_a.setLocation(120, 80);

        lb_team_b = new JLabel("Enter Team B:");
        lb_team_b.setFont(new Font(lb_team_a.getFont().getName(), Font.PLAIN, 15));
        lb_team_b.setSize(120, 20);
        lb_team_b.setLocation(120, 120);

        lb_team_c = new JLabel("Enter Team C:");
        lb_team_c.setFont(new Font(lb_team_a.getFont().getName(), Font.PLAIN, 15));
        lb_team_c.setSize(120, 20);
        lb_team_c.setLocation(120, 160);

        lb_team_d = new JLabel("Enter Team D:");
        lb_team_d.setFont(new Font(lb_team_a.getFont().getName(), Font.PLAIN, 15));
        lb_team_d.setSize(120, 20);
        lb_team_d.setLocation(120, 200);

        add(lb_team_a);
        add(lb_team_b);
        add(lb_team_c);
        add(lb_team_d);

        tf_team_a = new JTextField("A");
        tf_team_a.setSize(200, 30);
        tf_team_a.setLocation(250, 75);

        tf_team_b = new JTextField("B");
        tf_team_b.setSize(200, 30);
        tf_team_b.setLocation(250, 115);

        tf_team_c = new JTextField("C");
        tf_team_c.setSize(200, 30);
        tf_team_c.setLocation(250, 155);

        tf_team_d = new JTextField("D");
        tf_team_d.setSize(200, 30);
        tf_team_d.setLocation(250, 195);

        add(tf_team_a);
        add(tf_team_b);
        add(tf_team_c);
        add(tf_team_d);

        warning = new JLabel("Please fill every field");
        warning.setForeground(Color.RED);
        warning.setVisible(false);
        warning.setSize(200, 20);
        warning.setLocation(200, 255);
        add(warning);

        startButton = new JButton("Start");
        startButton.setSize(100, 30);
        startButton.setLocation(400, 250);
        startButton.addActionListener(this);

        add(startButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // check textfield should not empty
            if (tf_team_a.getText().isEmpty()) {
                warning.setVisible(true);
                return;
            }
            if (tf_team_b.getText().isEmpty()) {
                warning.setVisible(true);
                return;
            }
            if (tf_team_c.getText().isEmpty()) {
                warning.setVisible(true);
                return;
            }
            if (tf_team_d.getText().isEmpty()) {
                warning.setVisible(true);
                return;
            }

            // set team's string into Team object, then append to TeamTable
            Constant.teamTables.add(new TeamTable(new Team(tf_team_a.getText())));
            Constant.teamTables.add(new TeamTable(new Team(tf_team_b.getText())));
            Constant.teamTables.add(new TeamTable(new Team(tf_team_c.getText())));
            Constant.teamTables.add(new TeamTable(new Team(tf_team_d.getText())));
            
            /* init blockchain */
            Constant.blockchain.add(new Block(0, new League(0, Constant.teamTables), "0"));

            // disable text field and button
            tf_team_a.setEnabled(false);
            tf_team_b.setEnabled(false);
            tf_team_c.setEnabled(false);
            tf_team_d.setEnabled(false);
            startButton.setEnabled(false);

            TabbedWindowPanel.tabbedPane.addTab("Random Score", new RandomFutsalScorePanel());
            TabbedWindowPanel.tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
            RandomFutsalScorePanel.updateTransactionStatus(Constant.blockchain.get(Constant.blockchain.size() - 1));

            TabbedWindowPanel.tabbedPane.addTab("League Statistics", new BlockchainListPanel());
            TabbedWindowPanel.tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
            BlockchainListPanel.appendBlockList(Constant.blockchain.get(Constant.blockchain.size() - 1));
            
            TabbedWindowPanel.tabbedPane.setEnabledAt(1, true);
            TabbedWindowPanel.tabbedPane.setEnabledAt(2, true);
            TabbedWindowPanel.tabbedPane.setSelectedIndex(1);
            TabbedWindowPanel.tabbedPane.setEnabledAt(0, false);
            
        }
    }
}
