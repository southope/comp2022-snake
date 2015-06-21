import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends JPanel
{
    private String snake = "images/head.png";

    private int dx = 0;
    private int dy = 0;
    private int x;
    private int y;
    private int fx;
    private int fy;
    private Image image;
    
    public Snake() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(snake));
        image = ii.getImage();
        x = 300;
        y = 500;
        
    }
    
    public void setdX(int dx){
        this.dx = dx;
    }
    
    public void setdY(int dy){
        this.dy = dy;
    }
   
    public int getdX(){
        return dx;
    }
    
    public int getdY(){
        return dy;
    }
    
    public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
     public int getFX() {
        return fx;
    }

    public int getFY() {
        return fy;
    }
    
    public void setFXY(int fx, int fy){
        this.fx = fx;
        this.fy = fy;
    }

    public Image getImage() {
        return image;
    }
    
    public Image getImageFood(){
        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/fries.png"));
        return ii.getImage();        
    }
    
    public Image getImageFloor(){
        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/floor.jpg"));
        return ii.getImage();        
    }

}
