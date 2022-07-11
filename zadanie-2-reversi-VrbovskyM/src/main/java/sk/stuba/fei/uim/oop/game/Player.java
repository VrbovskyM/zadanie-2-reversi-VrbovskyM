package sk.stuba.fei.uim.oop.game;

import javax.swing.JLabel;

public class Player extends BasePlayer{      

    public Player(CellPanel[][] cell, JLabel winner) {
        super(cell, winner);
    }

    @Override
    public void play() {
        if(!canPlayAgainst("white") && !canPlayAgainst("black")) {
            endGame();
        }
        else {
            findPlayableCellsAgainst("black");
            highlightPlayableCells();
        }
    }
    
}
