package sk.stuba.fei.uim.oop.gui;

import javax.swing.*;
import sk.stuba.fei.uim.oop.controls.GameLogic;
import java.awt.*;

public class Gui {
    private JFrame frame;
    public Gui() {
        this.frame = new JFrame("Reversi");
        this.frame.setFocusable(true);
        this.frame.setMinimumSize(new Dimension(700,700));
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton restartButton = new JButton("Restart");
        JComboBox<String> sizeBox= new JComboBox<String>(new String[] {"6","8","10","12"});

        restartButton.setFocusable(false);
        sizeBox.setFocusable(false);

        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(1,5));
        menu.add(restartButton);
        menu.add(new JLabel("WINNER: "));
        menu.add(new JLabel("Turn: White"));
        menu.add(new JLabel("Grid Size: "));
        menu.add(sizeBox);
        
        JPanel board = new JPanel();
        board.setBackground(Color.GRAY);

        this.frame.add(menu, BorderLayout.NORTH);
        this.frame.add(board, BorderLayout.CENTER);
        GameLogic logic = new GameLogic(this.frame, sizeBox);
        restartButton.addActionListener(logic);
        this.frame.setVisible(true);
        
    }
}

