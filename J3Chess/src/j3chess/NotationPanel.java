package j3chess;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @brief Handels the Notationpanel
 *
 */
public class NotationPanel extends JPanel {

    private final int LEFT = 0;
    private final int MIDDLE = 1;
    private final int RIGHT = 2;

    private JTable notationTable;


    public NotationPanel(int sizeX, int sizeY, String Player1, String Player2, String Player3) {
        setPreferredSize(new Dimension(sizeX, sizeY));

        //Layoutmanager initialisieren
        GridBagLayout gridBagLayout = new GridBagLayout();

        setLayout(gridBagLayout);


        this.setLayout(new GridBagLayout());
        JComponent notationTable = createNatationTable(Player1, Player2, Player3);
        int row = 0;
        addComponentToGrid(notationTable, LEFT, row, 3, 1, GridBagConstraints.NORTH, 250, 500);
    }

    private void addComponentToGrid(JComponent c, final int x, final int y, final int width, final int height, final int anchor, final int weightx, final int weighty)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(3, 3, 3, 3);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.anchor = anchor;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        add(c, gbc);
    }

    
    private JComponent createNatationTable(final String Player1, final String Player2,final String Player3){
        String[] columnNames = {Player1, Player2, Player3};
        notationTable = new JTable(new DefaultTableModel(null, columnNames));
        JScrollPane tableSrcollPane = new JScrollPane(notationTable);
        notationTable.setFillsViewportHeight(true);
        return tableSrcollPane;
    }

    /**
     * @brief adds a move at the end of the Notationtable
     * @param move the move to add
     */
    public final void addMove(final String move) {
        DefaultTableModel tableModel = (DefaultTableModel) notationTable.getModel();
        int moveCounter=J3ChessApp.getInstance().getGame().getMoveCounter();
        if (moveCounter % J3ChessApp.NUMBEROFPLAYERS == 0) {
            tableModel.addRow(new Object[]{move});
        } else {
            tableModel.setValueAt(move, (Integer) moveCounter / J3ChessApp.NUMBEROFPLAYERS, moveCounter % J3ChessApp.NUMBEROFPLAYERS);
        }
    }
}
