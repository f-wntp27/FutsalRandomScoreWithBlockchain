package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import blockchain.Block;
import struct.League;
import struct.MatchMaking;
import util.BlockNameRenderer;
import util.Constant;

public class EditAndValidBlockchain extends JPanel implements ActionListener, ListSelectionListener {
    private JList<Block> blockList;
    private ListSelectionModel listSelectionModel;
    private JLabel homeTeamLb;
    private JLabel awayTeamLb;
    private JTextField homeScoreTf;
    private JTextField awayScoreTf;
    private JButton verifyButton;
    private JButton updateScoreButton;
    private JTextArea verifyStatus;
    private int index;

    public EditAndValidBlockchain() {
        setLayout(null);

        blockList = new JList<Block>(BlockchainListPanel.getListModel());
        blockList.setCellRenderer(new BlockNameRenderer());
        blockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel = blockList.getSelectionModel();
        listSelectionModel.addListSelectionListener(this);

        JScrollPane listSP = new JScrollPane(blockList);
        listSP.setSize(400, 80);
        listSP.setLocation(75, 15);

        add(listSP);

        homeTeamLb = new JLabel();
        homeTeamLb.setHorizontalAlignment(JLabel.RIGHT);
        homeTeamLb.setVerticalAlignment(JLabel.CENTER);
        homeTeamLb.setSize(100, 30);
        homeTeamLb.setLocation(110, 100);
        add(homeTeamLb);

        awayTeamLb = new JLabel();
        awayTeamLb.setHorizontalAlignment(JLabel.LEFT);
        awayTeamLb.setVerticalAlignment(JLabel.CENTER);
        awayTeamLb.setSize(100, 30);
        awayTeamLb.setLocation(340, 100);
        add(awayTeamLb);
        

        homeScoreTf = new JTextField();
        homeScoreTf.setHorizontalAlignment(JTextField.CENTER);
        homeScoreTf.setSize(40, 30);
        homeScoreTf.setLocation(225, 100);

        awayScoreTf = new JTextField();
        awayScoreTf.setHorizontalAlignment(JTextField.CENTER);
        awayScoreTf.setSize(40, 30);
        awayScoreTf.setLocation(285, 100);

        add(homeScoreTf);
        add(awayScoreTf);

        verifyButton = new JButton("Verify");
        verifyButton.addActionListener(this);
        verifyButton.setSize(150, 30);
        verifyButton.setLocation(100, 155);
        add(verifyButton);

        updateScoreButton = new JButton("Update Score");
        updateScoreButton.addActionListener(this);
        updateScoreButton.setSize(150, 30);
        updateScoreButton.setLocation(275, 155);
        add(updateScoreButton);
        
        verifyStatus = new JTextArea();
        verifyStatus.setFont(new Font("Consolas", Font.PLAIN, 12));
        verifyStatus.setEditable(false);
        verifyStatus.setSize(475, 110);
        verifyStatus.setLocation(25, 200);
        add(verifyStatus);
    }

    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();

        if (e.getValueIsAdjusting() == true) {
            return;
        }
        
        if (lsm.isSelectionEmpty()) {
            //blockDetail.setText("<none>");
        } else {
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
                    index = i;
                    showMatchScore(i);
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == verifyButton) {
            Block currentBlock;
            Block previousBlock;
            Boolean isValid = true;
            verifyStatus.setText("");

            for (int i = 1; i < Constant.blockchain.size(); i++) {
                currentBlock = Constant.blockchain.get(i);
                previousBlock = Constant.blockchain.get(i - 1);

                if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                    verifyStatus.append("Block " + i + ": Previous hashes are not equal\n");
                    isValid = false;
                    // System.out.println("Block " + i + ": Previous hashes are not equal");
                    // return false;
                }
    
                if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                    verifyStatus.append("Block " + i + ": Hashes are not equal\n");
                    isValid = false;
                    // System.out.println("Block " + i + ": Hashes are not equal");
                    // return false;
                }
            }

            if (isValid) {
                verifyStatus.append("Verification successfully...\n");
            }
        } else if (e.getSource() == updateScoreButton) {
            int newHomeScore;
            int newAwayScore;
            
            try {
                newHomeScore = Integer.parseInt(homeScoreTf.getText());
                newAwayScore = Integer.parseInt(awayScoreTf.getText());
            } catch (Exception e1) {
                verifyStatus.append("Score must be numberic\n");
                return;
            }

            Block currentBlock = Constant.blockchain.get(index);
            League currentLeague = currentBlock.getData();
            MatchMaking match = new MatchMaking(currentLeague.getMatchMaking().getHomeTeam(), 
                currentLeague.getMatchMaking().getAwayTeam(), newHomeScore, newAwayScore);
            currentLeague.setMatchMaking(match);
            currentLeague.updateLeagueCup();
            League newLeague = new League(currentLeague);

            currentBlock.setData(newLeague);
            Constant.blockchain.set(index, currentBlock);

            verifyStatus.append("Update completed\n");
        }
    }

    public void showMatchScore(int index) {
        MatchMaking match = Constant.blockchain.get(index).getData().getMatchMaking();
        if (match == null) {
            homeTeamLb.setText("");
            homeScoreTf.setText("");
            awayTeamLb.setText("");
            awayScoreTf.setText("");
            return;
        }
        homeTeamLb.setText("Team " + match.getHomeTeam().getName());
        homeScoreTf.setText(Integer.toString(match.getHomeScore()));
        awayTeamLb.setText("Team " +match.getAwayTeam().getName());
        awayScoreTf.setText(Integer.toString(match.getAwayScore()));

    }
}
