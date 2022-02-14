package ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import blockchain.Block;
import struct.League;
import struct.MatchMaking;
import struct.Team;
import struct.TeamTable;
import util.Constant;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RandomFutsalScorePanel extends JPanel implements ActionListener {
    private JButton generateScoreButton;
    private JLabel indexLabel;
    private JLabel scoreDisplay;
    private JTable leagueTable;
    private DefaultTableModel model;
    private static JTextArea transactionStatus;

    private int index = 0;
    
    public RandomFutsalScorePanel() {
        setLayout(null);

        generateScoreButton = new JButton("Generate score");
        generateScoreButton.setSize(150, 30);
        generateScoreButton.setLocation(200, 20);
        generateScoreButton.addActionListener(this);
        add(generateScoreButton);

        indexLabel = new JLabel("Match 0");
        indexLabel.setHorizontalAlignment(JLabel.CENTER);
        indexLabel.setVerticalAlignment(JLabel.CENTER);
        indexLabel.setSize(50, 30);
        indexLabel.setLocation(250, 50);
        add(indexLabel);

        scoreDisplay = new JLabel("");
        scoreDisplay.setHorizontalAlignment(JLabel.CENTER);
        scoreDisplay.setVerticalAlignment(JLabel.CENTER);
        scoreDisplay.setSize(300, 30);
        scoreDisplay.setLocation(125, 70);
        add(scoreDisplay);

        leagueTable = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; 

        String[] columnNames = {"Name", "P", "W", "L",  "D", "GF", "GA", "GD", "Pts"};
        model = (DefaultTableModel)leagueTable.getModel();
        
        for (String name : columnNames) {
            model.addColumn(name);
        }
        insertIntoLeagueTable(Constant.teamTables);


        JScrollPane tableSP = new JScrollPane(leagueTable);
        tableSP.setSize(400, 87);
        tableSP.setLocation(75, 100);
        tableSP.setViewportView(leagueTable);

        add(tableSP);

        transactionStatus = new JTextArea();
        transactionStatus.setFont(new Font("Consolas", Font.PLAIN, 10));
        transactionStatus.setEditable(false);
        transactionStatus.setSize(475, 110);
        transactionStatus.setLocation(25, 200);
        add(transactionStatus);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateScoreButton) {
            Constant.awayIndex++;
            index++;

            if (Constant.awayIndex == Constant.homeIndex) {
                Constant.awayIndex++;
            } else if (Constant.awayIndex > Constant.MAX_INDEX && Constant.homeIndex < Constant.MAX_INDEX) {
                Constant.awayIndex = 0;
                Constant.homeIndex++;
            }

            if (Constant.awayIndex >= Constant.MAX_INDEX && Constant.homeIndex >= Constant.MAX_INDEX) {
                // for (Block block : Constant.blockchain) {
                //     block.printBlock();
                // }
                generateScoreButton.setEnabled(false);
                TabbedWindowPanel.tabbedPane.addTab("Test Verify Blockchain", new EditAndValidBlockchain());
                return;
            }

            int randomHomeScore = (int)Math.floor(Math.random() * 3);
            int randomAwayScore = (int)Math.floor(Math.random() * 3);
            Team homeTeam = Constant.teamTables.get(Constant.homeIndex).getTeam();
            Team awayTeam = Constant.teamTables.get(Constant.awayIndex).getTeam();
            MatchMaking match = new MatchMaking(homeTeam, awayTeam, randomHomeScore, randomAwayScore);
            
            Block previousBlock = new Block(Constant.blockchain.get(Constant.blockchain.size() - 1));
            ArrayList<TeamTable> previousTeamTables = new ArrayList<TeamTable>(previousBlock.getData().getTeamTables());
            League league = new League(index, previousTeamTables, match);
            league.updateLeagueCup();

            Constant.blockchain.add(new Block(index, league, Constant.blockchain.get(Constant.blockchain.size() - 1).getHash()));

            scoreDisplay.setText(homeTeam.getName() + " (" + 
                Integer.toString(randomHomeScore) + ")-(" + Integer.toString(randomAwayScore) + ") " + awayTeam.getName());
            indexLabel.setText("Match " + Integer.toString(index));
        
            insertIntoLeagueTable(Constant.blockchain.get(Constant.blockchain.size() - 1).getData().getTeamTables());
            updateTransactionStatus(Constant.blockchain.get(Constant.blockchain.size() - 1));
            BlockchainListPanel.appendBlockList(Constant.blockchain.get(Constant.blockchain.size() - 1));
        }
    }

    public void insertIntoLeagueTable(ArrayList<TeamTable> teamTables) {
        int tIndex = 0;
        model.setRowCount(0);
        for (TeamTable table : teamTables) {
            model.addRow(new Object[0]);
            model.setValueAt(table.getTeam().getName(), tIndex, 0);
            model.setValueAt(table.getPlayed(), tIndex, 1);
            model.setValueAt(table.getWon(), tIndex, 2);
            model.setValueAt(table.getLoss(), tIndex, 3);
            model.setValueAt(table.getDraw(), tIndex, 4);
            model.setValueAt(table.getGoalFor(), tIndex, 5);
            model.setValueAt(table.getGoalAgainst(), tIndex, 6);
            model.setValueAt(table.getGoalDiff(), tIndex, 7);
            model.setValueAt(table.getPoint(), tIndex, 8);
            tIndex++;
        }
    }

    public static void updateTransactionStatus(Block block) {
        String newline = "\n";
        transactionStatus.setText("" + newline);
        transactionStatus.append("transaction_completed..." + newline);
        transactionStatus.append("block_index: " + block.getIndex() + newline);
        transactionStatus.append("previous_hash: " + block.getPreviousHash() + newline);
        transactionStatus.append("data_object_id: " + block.getData() + newline);
        transactionStatus.append("timestamp: " + block.getTimeStamp() + newline);
        transactionStatus.append("hash: " + block.getHash() + newline);
    }
}
