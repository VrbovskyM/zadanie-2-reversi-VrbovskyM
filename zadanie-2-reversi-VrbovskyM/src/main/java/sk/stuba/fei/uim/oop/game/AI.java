package sk.stuba.fei.uim.oop.game;
import java.awt.*;

import javax.swing.JLabel;


public class AI extends BasePlayer{
    private int duplicates;
    private Player player;
    
    public AI(CellPanel[][] cell, Player player, JLabel winner) {
        super(cell, winner);
        this.duplicates = -1;
        this.player = player;
        
    }

    @Override
    public void play() {
        if(!canPlayAgainst("white") && !canPlayAgainst("black")) {
            endGame();
        }
        else {
            findPlayableCellsAgainst("white");
            highlightPlayableCells();
            findBestPlay();
            if(!canPlayAgainst("black")) {
                this.play();
            }
            player.play();
        }
    }
    public void findBestPlay() {
        int bestCandidate = 0;
        int x=0;
        int y=0;
        int candidate = 0;
        for (CellPanel[] cellRow : cell) {
            for (CellPanel cell : cellRow) {
                candidate = findBestCell(cell);
                if(candidate > bestCandidate){
                    bestCandidate = candidate;
                    x = cell.getXCord();
                    y =  cell.getYCord();
                }
            }
        }
        playBestCell(x,y);
    }
    
    public int findBestCell(CellPanel cell) {
    // Due to the nature of this algorithm for finding the best play for AI, we need to remove duplicate cells.
    // That occurs when one cell can be reached from multiple directions. duplicates++ for each direction
        duplicates = -1;
        int totalCells = 0;
        int x = cell.getXCord();
        int y = cell.getYCord();
        if(cell.isDown()) totalCells = totalCells + up(x, y);
        if(cell.isDownRight()) totalCells = totalCells +  upLeft(x, y);
        if(cell.isDownLeft()) totalCells = totalCells +  upRight(x, y);
        if(cell.isUp()) totalCells = totalCells +  down(x, y);
        if(cell.isUpLeft()) totalCells = totalCells +  downRight(x, y);
        if(cell.isUpRight()) totalCells = totalCells +  downLeft(x, y);
        if(cell.isLeft()) totalCells = totalCells +  right(x, y);
        if(cell.isRight()) totalCells =  totalCells + left(x, y);
        return totalCells - this.duplicates;
    }
        
    public void playBestCell(int x, int y) {
        cell[y][x].setBackground(Color.GREEN);
        if(cell[y][x].isUp()){
            paintDown(x, y, "black");
        }
        if(cell[y][x].isUpLeft()) {
            paintDownRight(x, y, "black");
        }
        if(cell[y][x].isUpRight()) {
            paintDownLeft(x, y, "black");
        }
        if(cell[y][x].isDown()) {
            paintUp(x, y, "black");
        }
        if(cell[y][x].isDownLeft()) {
            paintUpRight(x, y, "black");
        }
        if(cell[y][x].isDownRight()){
            paintUpLeft(x, y, "black");
        }
        if(cell[y][x].isLeft()){
            paintRight(x, y, "black");
        }
        if(cell[y][x].isRight()) {
            paintLeft(x, y, "black");
        } 
        resetHighlights();
    }
    

    public int down(int x, int y) {
        duplicates++;
        int i = 0;
        while(!cell[y+i][x].isPaintBlack()) {
            i++;
        }
        return i;
    }
    public int downRight(int x, int y) {
        duplicates++;
        int i = 0;
        while(!cell[y+i][x+i].isPaintBlack()) {
            i++;
        }
        return i;
    }
    public int downLeft(int x, int y) {
        duplicates++;
        int i = 0;
        while(!cell[y+i][x-i].isPaintBlack()) {
            i++;
        }
        return i;
    }
    public int up(int x, int y) {
        duplicates++;
        int i = 0;
        while(!cell[y-i][x].isPaintBlack()) {
            i++;
        }
        return i;
    }
    public int upRight(int x, int y) {
        duplicates++;
        int i = 0;
        while(!cell[y-i][x+i].isPaintBlack()) {
            i++;
        }
        return i;
    }
    public int upLeft(int x, int y) {
        duplicates++;
        int i = 0;
        while(!cell[y-i][x-i].isPaintBlack()) {
            i++;
        }
        return i;
    }
    public int right(int x, int y) {
        duplicates++;
        int i = 0;
        while(!cell[y][x+i].isPaintBlack()) {
            i++;
        }
        return i;
    }
    public int left(int x, int y) {
        duplicates++;
        int i = 0;
        while(!cell[y][x-i].isPaintBlack()) {
            i++;
        }
        return i;
    }

    
}
