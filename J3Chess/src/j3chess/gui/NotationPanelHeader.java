package j3chess.gui;

import j3chess.Game;
import j3chess.J3ChessApp;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

//import sun.swing.table.DefaultTableCellHeaderRenderer;

public class NotationPanelHeader extends DefaultTableCellRenderer {
    @Override
    public final Component getTableCellRendererComponent(final JTable table,
            final Object value, final boolean selected, final boolean focused,
            final int row, final int column) {
        super.getTableCellRendererComponent(table, value, selected, focused,
                row, column);

        Color bg = UIManager.getColor("TableHeader.background");
        final Game g = J3ChessApp.getInstance().getGame();
        if (g != null && g.getCurrentPlayerID() == column) {
            bg = UIManager.getColor("Table.selectionBackground");
        }
        setBackground(bg);

        return this;
    }

}
