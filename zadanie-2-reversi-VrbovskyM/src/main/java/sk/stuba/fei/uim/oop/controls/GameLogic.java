package sk.stuba.fei.uim.oop.controls;

import java.awt.event.*;

import sk.stuba.fei.uim.oop.game.AI;
import sk.stuba.fei.uim.oop.game.CellPanel;
import sk.stuba.fei.uim.oop.game.Player;

import javax.swing.*;
import java.awt.*;
public class GameLogic extends UniversalAdapter {
    private JFrame frame;
    private CellPanel[][] cell;
    private JComboBox<String> sizeBox;
    private JPanel board;
    private AI ai;
    private Player player;
    private JLabel gridSize;
    private JPanel menu;
    private JLabel winner;
    public GameLogic(JFrame frame, JComboBox<String> sizeBox) {
        this.frame = frame;
        this.sizeBox = sizeBox;
        sizeBox.addItemListener(this);
        this.board = (JPanel) frame.getContentPane().getComponent(1);
        this.menu = (JPanel) frame.getContentPane().getComponent(0);
        this.gridSize = (JLabel) menu.getComponent(3);
        this.winner = (JLabel) menu.getComponent(1);
        this.frame.addKeyListener(this);
        createGrid(6);
    }

    public void createGrid(int size) {
        board.removeAll();
        this.board.setLayout(new GridLayout(size,size,5,5));
        this.cell = new CellPanel[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cell[i][j] = new CellPanel(j,i);
                cell[i][j].addMouseListener(this); 
                board.add(cell[i][j]);
            }
        }
        this.frame.repaint();
        this.frame.revalidate();
        this.gridSize.setText("Grid Size:   " + size + "x" + size);
        this.winner.setText("WINNER: ");
        paintFourMiddleStone(size);
        this.player = new Player(this.cell, this.winner);
        this.ai = new AI(this.cell, this.player, this.winner);
        
        player.play();
    } 
    
    public void paintFourMiddleStone(int size) {
        cell[size/2-1][size/2-1].setPaintWhite(true);
        cell[size/2][size/2].setPaintWhite(true);
        cell[size/2][size/2-1].setPaintBlack(true);
        cell[size/2-1][size/2].setPaintBlack(true);

    }
    
    public void resetGame() {
        String item =  (String) sizeBox.getSelectedItem();
        int size = Integer.parseInt(item);
        createGrid(size);
    }  
    
    @Override
    public void mouseClicked(MouseEvent e) {
        CellPanel currentCell = (CellPanel)e.getSource();
        int x = currentCell.getXCord();
        int y = currentCell.getYCord(); 
        if(currentCell.isHighlight()) {
            currentCell.setBackground(Color.GREEN);
            if(currentCell.isUp()){
                this.player.paintDown(x, y, "white");
            }
            if(currentCell.isUpLeft()) {
                this.player.paintDownRight(x, y, "white");
            }
            if(currentCell.isUpRight()) {
                this.player.paintDownLeft(x, y, "white");
            }
            if(currentCell.isDown()) {
                this.player.paintUp(x, y, "white");
            }
            if(currentCell.isDownLeft()) {
                this.player.paintUpRight(x, y, "white");
            }
            if(currentCell.isDownRight()){
                this.player.paintUpLeft(x, y, "white");
            }
            if(currentCell.isLeft()){
                this.player.paintRight(x, y, "white");
            }
            if(currentCell.isRight()) {
                this.player.paintLeft(x, y, "white");
            } 
            player.resetHighlights();
            if(!ai.canPlayAgainst("white")) {
                        player.play();
                    }
                    else ai.play();
            }
    }
    @Override
    public void actionPerformed(ActionEvent e) { //Reset button
            resetGame(); 
    }
    @Override
    public void itemStateChanged(ItemEvent e) { //Change Grid Size
        resetGame();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // Exit on ESC
            this.frame.dispose();
            System.exit(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {  // Reset on R
            resetGame();
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        CellPanel currentCell = (CellPanel)e.getSource();
        if(currentCell.isHighlight()) {
            currentCell.setBackground(new Color(0 -255- 51));
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        CellPanel currentCell = (CellPanel)e.getSource();
        if(currentCell.isHighlight()) {
            currentCell.setBackground(Color.GREEN);
        }
    }
}

