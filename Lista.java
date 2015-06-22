import java.awt.Image;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
/**
 * Write a description of class Node here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lista extends JPanel
{
    // instance variables - replace the example below with your own
    private Object x;
    private Lista proximo;
    private int bx = 0;
    private int by = 0;
    private int dx = 0;
    private int dy = 0;
    private int cx = 0, cy = 0;
    private int dir;

    /**
     * Constructor for objects of class Node
     */
    public Lista()
    {

    }
    
    public void setDir(int dir){
        this.dir = dir;
    }
    
    public int getDir(){
        return dir;
    }
    
    public void setCXY(int cx, int cy){
        this.cx = cx;
        this.cy = cy;
    }
    
    public int getcX(){
        return cx;
    }
    
    public int getcY(){
        return cy;
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
    
    public Lista(String args)
    {
        this.x = args;
    }
    
    public int getBX(){
        return bx;
    }
    
    public int getBY(){
        return by;
    }
    
    public void setBX(int bx){
        this.bx = bx;
    }
    
    public void setBY(int by){
        this.by = by;
    }

    public void setX(Object _x){
        this.x = _x;
    }
    
    //public Object getX(){
     //   return this.x;
    //}
    
    public void setProximo(Lista _proximo){
        this.proximo = _proximo;
    }
    
    public Lista getProximo(){
        return this.proximo;
    }
    
    public Image getImageBody(){
        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/body.png"));
        return ii.getImage();        
    }
    
    public void move() {
        bx += dx;
        by += dy;
    }
    
}
