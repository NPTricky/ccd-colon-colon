package j3chess;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewGameDialog extends JFrame {

    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    private static final Insets INSETS = new Insets(3, 3, 3, 3);
    private static final int TEXTFIELDCOLUMNWIDTH = 15;

    public NewGameDialog(){
        super("Start new Game");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Fenstergroesse festlegen
        //setPreferredSize(new Dimension(800,1000));

        // Fensterposition festlegen
        setLocation(150, 150);

        //HauptGridLayout 2 columns
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{150,150};
        setLayout(gridBagLayout);


        JLabel labelPlayer1 = new JLabel("Player 1");
        JLabel labelPlayer2 = new JLabel("Player 2");
        JLabel labelPlayer3 = new JLabel("Player 3");
        JTextField textFieldPlayer1 = new JTextField();
        textFieldPlayer1.setColumns(TEXTFIELDCOLUMNWIDTH);
        JTextField textFieldPlayer2 = new JTextField();
        textFieldPlayer2.setColumns(TEXTFIELDCOLUMNWIDTH);
        JTextField textFieldPlayer3 = new JTextField();
        textFieldPlayer3.setColumns(TEXTFIELDCOLUMNWIDTH);
        JButton jButtonOk = new JButton("OK");
        JButton jButtonCancel = new JButton("Cancel");

        jButtonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Set Player Names
                //Start Game
                dispose();
            }
        });

        jButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        int row = 0;
        addComponentToGrid(labelPlayer1, LEFT, row);
        addComponentToGrid(textFieldPlayer1, RIGHT, row);
        row++;
        addComponentToGrid(labelPlayer2, LEFT, row);
        addComponentToGrid(textFieldPlayer2, RIGHT, row);
        row++;
        addComponentToGrid(labelPlayer3, LEFT, row);
        addComponentToGrid(textFieldPlayer3, RIGHT, row);
        row++;
        addComponentToGrid(jButtonOk, LEFT, row);
        addComponentToGrid(jButtonCancel, RIGHT, row);

        setVisible(true);
        pack();
    }

    private void addComponentToGrid(JComponent c, int column, int row){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = INSETS;
        gbc.gridx = column;
        gbc.gridy = row;
        add(c, gbc);
        gbc.fill = GridBagConstraints.BOTH;
    }
}
