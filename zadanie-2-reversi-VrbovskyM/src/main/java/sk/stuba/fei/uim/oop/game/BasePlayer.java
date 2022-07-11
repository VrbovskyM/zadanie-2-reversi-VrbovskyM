package sk.stuba.fei.uim.oop.game;

import javax.swing.JLabel;

//                  Searching and Painting algorithm explained
// It takes every cell, and looks in 8 directions for possible play.
// That is, when currentCell is color of whose turn it is -> it goes over at least
// one enemy color -> and there is blank cell after enemy stone.
// Then, it marks the cell with boolean from which direction it can be connected with.
// A new playable cell can connect multiple directions at once. Therefore, cell has 8 booleans 
// that represtens directions. Any cell that has at least one boolean(representing direction) true,
// will be highlighted as playable cell.
// When cell is selected to be played, it will paint in all OPPOSITE directions it is "labeled" with.
// e.g. If you select highlighted cell that has booleans up and upRight true,
// It will paint stone in direction down and downLeft to connect with existing stones.

public abstract class BasePlayer {
    protected CellPanel[][] cell;
    protected JLabel winner;
    public BasePlayer(CellPanel[][] cell, JLabel winner) {
        this.cell = cell;
        this.winner = winner;

    }
    public abstract void play();
    
    public void findPlayableCellsAgainst(String color) {
        for (CellPanel[] cellRow : cell){
            for (CellPanel cell : cellRow) {
                up(cell, color);
                upLeft(cell, color);
                upRight(cell, color);
                down(cell, color);
                downLeft(cell, color);
                downRight(cell, color); 
                left(cell, color);
                right(cell, color);
            }
        }
    }

    public void highlightPlayableCells() {
        for (CellPanel[] cellRow : cell) {
            for (CellPanel cell : cellRow) {
                    cell.setHighlight();
            }
        }
    }

    public boolean checkPlayableCells() {
        for (CellPanel[] cellRow : cell) {
            for(CellPanel cell: cellRow) {
                if(cell.isHighlight()){
                    return true;
                } 
            }
        }
        return false;
    }
    
    public void resetHighlights() {
        for (CellPanel[] cellRow : cell) {
            for (CellPanel cell : cellRow) {
                    cell.resetHighlight();
            }
        }
   
    }

    public boolean canPlayAgainst(String color) {
        findPlayableCellsAgainst(color);
        highlightPlayableCells();
        if(checkPlayableCells()) {
            resetHighlights();
            return true;
        }
        return false;
    }
 
    public void endGame() {
        int whiteStones = countStones("white");
        int blackStones = countStones("black");
        System.out.println("GAME ENDED");
        System.out.println("White: " + whiteStones + "\nBlack: " + blackStones);
        String victorString = whiteStones > blackStones ? "White":"Black";
        int victorInt = whiteStones > blackStones ? whiteStones:blackStones;
        if(whiteStones == blackStones) victorString = "TIE";
        winner.setText("WINNER: " + victorString + " " + victorInt);
        
    }

    public int countStones(String color) {
        int stoneCounter = 0;
        for (CellPanel[] cellRow : cell) {
            for (CellPanel cell : cellRow) {
                    if(cell.color().equals(color)) stoneCounter++;
            }
        }
        return stoneCounter;
    }


    public boolean up(CellPanel currentCell, String color) {
        int x = currentCell.getXCord();
        int y = currentCell.getYCord();
        int i = 1;
        while((y-i) >= 0) {
            if(currentCell.color().equals(color)) {
                return false;
            }
            else if(cell[y-i][x].color().equals(currentCell.color())) {
                return false;
            }
            else if(cell[y-i][x].color().equals("blank") && i > 1) {
                cell[y-i][x].setUp(true);
                return true;
            }
            else if(cell[y-i][x].color().equals("blank")) {
                return false;
            }
            i++;
        }
        return false;
    }
    public boolean upLeft(CellPanel currentCell, String color) {
        int x = currentCell.getXCord();
        int y = currentCell.getYCord();
        int i = 1;
        while(y-i >= 0 && x-i >= 0) {
            if(currentCell.color().equals(color)) {
                return false;
            }
            else if(cell[y-i][x-i].color().equals(currentCell.color())) {
                return false;
            }
            else if(cell[y-i][x-i].color().equals("blank") && i > 1) {
                cell[y-i][x-i].setUpLeft(true);
                return true;
            }
            else if(cell[y-i][x-i].color().equals("blank")){
                return false;
            }
            i++;
        }
        return false;
    }
    public boolean upRight(CellPanel currentCell, String color) {
        int x = currentCell.getXCord();
        int y = currentCell.getYCord();
        int i = 1;
        while(y-i >= 0 && x+i < cell.length) {
            if(currentCell.color().equals(color)) {
                return false;
            }
            else if(cell[y-i][x+i].color().equals(currentCell.color())) {
                return false;
            }
            else if(cell[y-i][x+i].color().equals("blank") && i > 1) {
                cell[y-i][x+i].setUpRight(true);
                return true;
            }
            else if(cell[y-i][x+i].color().equals("blank")) {
                return false;
            }
            i++;
        }
        return false;
    }
    public boolean down(CellPanel currentCell, String color) {
        int x = currentCell.getXCord();
        int y = currentCell.getYCord();
        int i = 1;
        while(y+i < cell.length) {
            if(currentCell.color().equals(color)) {
                return false;
            }
            else if(cell[y+i][x].color().equals(currentCell.color())) {
                return false;
            }
            else if(cell[y+i][x].color().equals("blank") && i > 1) {
                cell[y+i][x].setDown(true);
                return true;
            }
            else if(cell[y+i][x].color().equals("blank")) {
                return false;
            }
            i++;
        }
        return false;
    }
    public boolean downLeft(CellPanel currentCell, String color) {
        int x = currentCell.getXCord();
        int y = currentCell.getYCord();
        int i = 1;
        while(y+i < cell.length && x-i >= 0) {
            if(currentCell.color().equals(color)) {
                return false;
            }
            else if(cell[y+i][x-i].color().equals(currentCell.color())) {
                return false;
            }
            else if(cell[y+i][x-i].color().equals("blank") && i > 1) {
                cell[y+i][x-i].setDownLeft(true);
                return true;
            }
            else if(cell[y+i][x-i].color().equals("blank")) {
                return false;
            }
            i++;
        }
        return false;
    }
    public boolean downRight(CellPanel currentCell, String color) {
        int x = currentCell.getXCord();
        int y = currentCell.getYCord();
        int i = 1;
        while(y+i < cell.length && x+i < cell.length) {
            if(currentCell.color().equals(color)) {
                return false;
            }
            else if(cell[y+i][x+i].color().equals(currentCell.color())) {
                return false;
            }
            else if(cell[y+i][x+i].color().equals("blank") && i > 1) {
                cell[y+i][x+i].setDownRight(true);
                return true;
            }
            else if(cell[y+i][x+i].color().equals("blank")) {
                return false;
            }
            i++;
        }
        return false;
    }
    public boolean left(CellPanel currentCell, String color) {
        int x = currentCell.getXCord();
        int y = currentCell.getYCord();
        int i = 1;
        while(x-i >= 0) {
            if(currentCell.color().equals(color)) {
                return false;
            }
            else if(cell[y][x-i].color().equals(currentCell.color())) {
                return false;
            }
            else if(cell[y][x-i].color().equals("blank") && i > 1) {
                cell[y][x-i].setLeft(true);
                return true;
            }
            else if(cell[y][x-i].color().equals("blank")) {
                return false;
            }
            i++;
        }
        return false;
    }
    public boolean right(CellPanel currentCell, String color) {
        int x = currentCell.getXCord();
        int y = currentCell.getYCord();
        int i = 1;
        while(x+i < cell.length) {
            if(currentCell.color().equals(color)) {
                return false;
            }
            else if(cell[y][x+i].color().equals(currentCell.color())) {
                return false;
            }
            else if(cell[y][x+i].color().equals("blank") && i > 1) {
                cell[y][x+i].setRight(true);
                return true;
            }
            else if(cell[y][x+i].color().equals("blank")) {
                return false;
            }
            i++;
        }
        return false;
    }


    public void paintDown(int x, int y, String color) {
        int i = 0;
        while(!cell[y+i][x].color().equals(color) || !cell[y+1][x].color().equals(color)) {
            cell[y+i][x].setStoneColor(color);
            i++;
        }
    }
    public void paintDownRight(int x, int y, String color) {
        int i = 0;
        while(!cell[y+i][x+i].color().equals(color) || !cell[y+1][x+1].color().equals(color)) {
            cell[y+i][x+i].setStoneColor(color);
            i++;
        }
    }
    public void paintDownLeft(int x, int y, String color) {
        int i = 0;
        while(!cell[y+i][x-i].color().equals(color) || !cell[y+1][x-1].color().equals(color)) {
            cell[y+i][x-i].setStoneColor(color);
            i++;
        }
    }
    public void paintUp(int x, int y, String color) {
        int i = 0;
        while(!cell[y-i][x].color().equals(color) || !cell[y-1][x].color().equals(color)) {
            cell[y-i][x].setStoneColor(color);
            i++;
        }
    }
    public void paintUpRight(int x, int y, String color) {
        int i = 0;
        while(!cell[y-i][x+i].color().equals(color) || !cell[y-1][x+1].color().equals(color)) {
            cell[y-i][x+i].setStoneColor(color);
            i++;
        }
    }
    public void paintUpLeft(int x, int y, String color) {
        int i = 0;
        while(!cell[y-i][x-i].color().equals(color) || !cell[y-1][x-1].color().equals(color)) {
            cell[y-i][x-i].setStoneColor(color);
            i++;
        }
    }
    public void paintRight(int x, int y, String color) {
        int i = 0;
        while(!cell[y][x+i].color().equals(color) || !cell[y][x+1].color().equals(color)) {
            cell[y][x+i].setStoneColor(color);
            i++;
        }
    }
    public void paintLeft(int x, int y, String color) {
        int i = 0;
        while(!cell[y][x-i].color().equals(color) || !cell[y][x-1].color().equals(color)) {
            cell[y][x-i].setStoneColor(color);
            i++;
        }
    }
    

    
}