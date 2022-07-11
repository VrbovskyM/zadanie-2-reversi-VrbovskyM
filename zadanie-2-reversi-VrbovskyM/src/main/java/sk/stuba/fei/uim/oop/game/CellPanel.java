package sk.stuba.fei.uim.oop.game;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.*;
import lombok.Getter;
import lombok.Setter;

public class CellPanel extends JPanel {
    @Getter
    private final int xCord;
    @Getter
    private final int yCord;
    @Getter @Setter
    private boolean paintBlack;
    @Getter @Setter
    private boolean paintWhite;
    @Getter 
    private boolean highlight;
    @Getter @Setter
    private boolean up;
    @Getter @Setter
    private boolean upLeft;
    @Getter @Setter
    private boolean upRight;
    @Getter @Setter
    private boolean down;
    @Getter @Setter
    private boolean downLeft;
    @Getter @Setter
    private boolean downRight;
    @Getter @Setter
    private boolean left;
    @Getter @Setter
    private boolean right;
    public CellPanel(int x, int y) {
        this.setBackground(Color.GREEN);
        this.xCord = x;
        this.yCord = y;
        this.paintBlack = false;
        this.paintWhite = false;
        this.up = false;
        this.upLeft = false;
        this.upRight = false;
        this.down = false;
        this.downLeft = false;
        this.downRight = false;
        this.left = false;
        this.right = false;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        if(this.paintBlack) {
            g.setColor(Color.BLACK);
            g.fillOval(5,5, height/5*4, width/5*4);
        }
        else if(this.paintWhite) {
            g.setColor(Color.WHITE);
            g.fillOval(5,5, height/5*4, width/5*4);
        }
    }
    public void setStoneColor(String color) {
        if(color.equals("white")) {
            setPaintWhite(true);
            setPaintBlack(false);
        }
        else if (color.equals("black")) {
            setPaintWhite(false);
            setPaintBlack(true);
            
        }
        else if(color.equals("blank")) {
            setPaintWhite(false);
            setPaintBlack(false);
        }
        repaint();
    }
    
    public void setHighlight() {
        if(checkIfPlayable()) {
            this.setBorder(new LineBorder(Color.BLUE,5));
            this.highlight = true;
        }
        else{
            this.setBorder(null);
            this.highlight = false;
        } 
        repaint();  
    }

    public boolean checkIfPlayable() {
        if(this.up) return true;
        if(this.upLeft) return true;
        if(this.upRight) return true;
        if(this.down) return true;
        if(this.downLeft) return true;
        if(this.downRight) return true;
        if(this.left) return true;
        if(this.right) return true;
        return false;
    }

    public void resetHighlight() {
        this.up = false;
        this.upLeft = false;
        this.upRight = false;
        this.down = false;
        this.downLeft = false;
        this.downRight = false;
        this.left = false;
        this.right = false;
        this.setHighlight();
    }

    public String color() {
        if(isPaintBlack()) {
            return "black";
        }
        else if(isPaintWhite()) {
            return "white";
        }
        else return "blank";
    }
} 
