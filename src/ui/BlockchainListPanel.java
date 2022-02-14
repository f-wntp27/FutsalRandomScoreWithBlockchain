package ui;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import blockchain.Block;
import struct.TeamTable;
import util.BlockNameRenderer;
import util.Constant;

public class BlockchainListPanel extends JPanel implements ListSelectionListener {
    private JList<Block> blockList;
    private static DefaultListModel<Block> listModel;
    private JTextArea blockDetail;
    private JTable leagueTable;
    private DefaultTableModel model;
    private ListSelectionModel listSelectionModel;
    
    public BlockchainListPanel() {
        setLayout(null);

        listModel = new DefaultListModel<Block>();

        blockList = new JList<Block>(listModel);
        blockList.setCellRenderer(new BlockNameRenderer());
        blockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel = blockList.getSelectionModel();
        listSelectionModel.addListSelectionListener(this);

        JScrollPane listSP = new JScrollPane(blockList);
        listSP.setSize(400, 80);
        listSP.setLocation(75, 15);
        add(listSP);

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

        JScrollPane tableSP = new JScrollPane(leagueTable);
        tableSP.setSize(400, 87);
        tableSP.setLocation(75, 100);
        tableSP.setViewportView(leagueTable);

        add(tableSP);

        blockDetail = new JTextArea();
        blockDetail.setFont(new Font("Consolas", Font.PLAIN, 10));
        blockDetail.setEditable(false);
        blockDetail.setSize(475, 110);
        blockDetail.setLocation(25, 200);
        add(blockDetail);
    }

    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();

        if (e.getValueIsAdjusting() == true) {
            return;
        }
        
        if (lsm.isSelectionEmpty()) {
            blockDetail.setText("<none>");
        } else {
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
                    // output.append(" " + i);
                    showBlockDetail(i);
                    showBlockTeamTable(i);
                }
            }
        }
    }
    
    public static void appendBlockList(Block block) {
        listModel.addElement(block);
    }

    public void showBlockDetail(int index) {
        String newline = "\n";
        Block block = Constant.blockchain.get(index);
        blockDetail.setText("" + newline);
        if (block.getData().getMatchMaking() != null) {
            blockDetail.setText(block.getData().getMatchMaking().getHomeTeam().getName());
            blockDetail.append(" " + block.getData().getMatchMaking().getHomeScore());
            blockDetail.append("-" + block.getData().getMatchMaking().getAwayScore());
            blockDetail.append(" " + block.getData().getMatchMaking().getAwayTeam().getName() + newline);
        }
        blockDetail.append("block_detail" + newline);
        blockDetail.append("block_index: " + block.getIndex() + newline);
        blockDetail.append("previous_hash: " + block.getPreviousHash() + newline);
        blockDetail.append("data_object_id: " + block.getData() + newline);
        blockDetail.append("timestamp: " + block.getTimeStamp() + newline);
        blockDetail.append("hash: " + block.getHash() + newline);
    }

    public void showBlockTeamTable(int index) {
        ArrayList<TeamTable> teamTables = Constant.blockchain.get(index).getData().getTeamTables();
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

    public static DefaultListModel<Block> getListModel() {
        return listModel;
    }
}
