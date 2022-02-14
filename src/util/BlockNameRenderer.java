package util;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class BlockNameRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(
        JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus
    ) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setText("Block " + Integer.toString(index) + ": " + value.toString());
        return this;
    }
}
