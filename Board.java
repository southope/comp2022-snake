import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;
import javax.swing.ImageIcon;
import java.awt.geom.AffineTransform;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Score score;
    private Snake head;
    private Snake food;
    private Snake floor;
    private int keyAnterior = KeyEvent.VK_LEFT;
    private int keyAnt = KeyEvent.VK_LEFT;
    private Lista inicio;
    private Lista list[];
    private int body = 3;
    private int ultX = 0;
    private int ultY = 0;
    private boolean isPlaying = true;
    private boolean isFood = false;
    

    private Font font;
       
    public Board() {

        addKeyListener(new TAdapter());
        
        setFocusable(true);        
        setDoubleBuffered(true);
        setBackground(Color.WHITE);

        score = new Score();
        head = new Snake();
        food = new Snake();
        list = new Lista[100];
       
        add(food);
        add(score);       
        add(head); 
       
        // add(floor);
        randFood();
        listGenerator();
        
            inserirFinal(list[0]);
            inserirFinal(list[1]);
            inserirFinal(list[2]);
            add(list[0]);
            add(list[1]);
            add(list[2]);
            list[0].setdX(head.getdX());
            list[0].setdY(head.getdY());
            list[1].setdX(head.getdX());
            list[1].setdY(head.getdY());
            list[2].setdX(head.getdX());
            list[2].setdY(head.getdY());
            list[0].setBX(head.getX()+45);
            list[0].setBY(head.getY());
            list[1].setBX(list[0].getBX()+20);
            list[1].setBY(list[0].getBY());
            list[2].setBX(list[1].getBX()+20);
            list[2].setBY(list[1].getBY());
         
        //System.out.println(KeyEvent.VK_LEFT+"");
        //System.out.println(KeyEvent.VK_RIGHT+"");
        //System.out.println(KeyEvent.VK_UP+"");
        //System.out.println(KeyEvent.VK_DOWN+"");
        
        timer = new Timer(10, this);
        timer.start();
    }
    
    public void moveBody(){
        if (inicio != null){
        Lista a = inicio;
        a.move();
        // System.out.println(a.getX()+" X: "+ a.getBX()+" Y: "+a.getBY());        
        while(a.getProximo() != null){
            a = a.getProximo();
            a.move();           
            //System.out.println(a.getX()+" X: "+ a.getBX()+" Y: "+a.getBY());
            //System.out.println(a.getX());
        }
       }
    }
    
    public void listGenerator(){
        for (int i = 0; i < list.length ; i++) { 
         list[i] = new Lista("body"+i);
        } 
    }
    
    public void randFood(){
        Random rand = new Random();
        food.setFXY(1 + (int)rand.nextInt(740),1 + (int)rand.nextInt(545));
    }
    
    public void inserirFinal(Lista _node){
        if(isEmpty()){
            inicio = _node;
        }else{
            Lista aux = inicio;
            while(aux.getProximo() != null){
                aux = aux.getProximo();
            }
            aux.setProximo(_node);
        }
    }
    
    
    public boolean isEmpty(){
        if(inicio == null){
            return true;
        }else{  
            return false;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        
        score.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g;        
         if (isPlaying == true){
             
             //ImageIcon ii = new ImageIcon(this.getClass().getResource("images/floor.jpg"));
              if (inicio != null){
              Lista a = inicio;
              g2d.drawImage(a.getImageBody(),a.getBX(),a.getBY(),this); 
              while(a.getProximo() != null){
               a = a.getProximo();
               g2d.drawImage(a.getImageBody(),a.getBX(),a.getBY(),this);
               //System.out.println(a.getX());
             }
           }  
           
           //
           
           //g2d.translate(1,1);    
           //g2d.rotate(45);    
           //g2d.translate(-(1), -1);    
           //g2d.drawImage(head.getImage(), head.getX()-1/2,head.getY()-1/2, this);   
           //g22.dispose();
           
           
            //Graphics2D g2d = (Graphics2D)g;
            //g2d.drawImage(head.getImage(),310,510,290,490,290,490,310,510,this);
            g2d.drawImage(head.getImage(),head.getX(),head.getY(),this); 
            
            g2d.drawImage(food.getImageFood(),food.getFX(),food.getFY(),this); 
            
            }else{               
            try{
            File file = new File("fonts/VT323-Regular.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, file);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            font = font.deriveFont(Font.PLAIN,36);
            g2d.setFont(font);
            }catch (Exception e){
                 System.out.println(e.toString());
            }   
           g2d.drawString("Game Over", 350, 250);
            }
        
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }


    public void paintIntro(Graphics g) {
        /*if(isPlaying){
            isPlaying = false;
            Graphics2D g2d = (Graphics2D) g;
            try{
                File file = new File("fonts/VT323-Regular.ttf");
                font = Font.createFont(Font.TRUETYPE_FONT, file);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
                font = font.deriveFont(Font.PLAIN,40);
                g2d.setFont(font);
            }catch (Exception e){
                System.out.println(e.toString());
            }   
            g2d.drawString("S N A K E: " + this.score, 300, 300);
        }
        */
    }
    
    public void bodyPosition(){
        
        if (body == 0){
         /*if (keyAnterior == KeyEvent.VK_LEFT ){
            list[body].setBX(head.getX()+45);
            list[body].setBY(head.getY());
         }else if (keyAnterior == KeyEvent.VK_RIGHT ){
            list[body].setBX(head.getX()-45);
            list[body].setBY(head.getY());
         }else if (keyAnterior == KeyEvent.VK_UP ){
            list[body].setBX(head.getX());
            list[body].setBY(head.getY()+45);
         }else if (keyAnterior == KeyEvent.VK_DOWN ){
            list[body].setBX(head.getX());
            list[body].setBY(head.getY()-45);
         }*/
       }else{
         if (keyAnterior == KeyEvent.VK_LEFT ){
            list[body].setBX(list[body-1].getBX()+20);
            list[body].setBY(list[body-1].getBY());
         }else if (keyAnterior == KeyEvent.VK_RIGHT ){
            list[body].setBX(list[body-1].getBX()-20);
            list[body].setBY(list[body-1].getBY());
         }else if (keyAnterior == KeyEvent.VK_UP ){
            list[body].setBX(list[body-1].getBX());
            list[body].setBY(list[body-1].getBY()+20);
         }else if (keyAnterior == KeyEvent.VK_DOWN ){
            list[body].setBX(list[body-1].getBX());
            list[body].setBY(list[body-1].getBY()-20);
         }
       }
        
    }
    
    public void recTurn(){
        
        
    }
    
    public void turnBody(){
        if (inicio != null){
              Lista a = inicio;  
              if ((a.getBX()== ultX && a.getBY() == ultY)){
                  a.setdX(head.getdX());
                  a.setdY(head.getdY());
                  //a.setDir(0);
                  //a.setCXY(ultX,ultY);
                }
                /*else if ((a.getBX()== a.getcX() && a.getBY() == a.getcY() && a.getDir() == KeyEvent.VK_RIGHT)){
                  a.setdX(1);
                  a.setdY(0);
                  a.setDir(0);
                  //a.setCXY(ultX,ultY);
                }else if ((a.getBX()== a.getcX() && a.getBY() == a.getcY() && a.getDir() == KeyEvent.VK_LEFT)){
                  a.setdX(0);
                  a.setdY(-1);
                  a.setDir(0);
                  //a.setCXY(ultX,ultY);
                }else if ((a.getBX()== a.getcX() && a.getBY() == a.getcY() && a.getDir() == KeyEvent.VK_LEFT)){
                  a.setdX(0);
                  a.setdY(1);
                  a.setDir(0);
                  //a.setCXY(ultX,ultY);
                }
                //else if ((keyAnterior == KeyEvent.VK_UP || keyAnterior == KeyEvent.VK_DOWN ) && a.getBY() != ultY ){
                  //a.setdX(head.getdX());
                  //a.setdY(head.getdY());
                  */
                
             while(a.getProximo() != null){  
                     Lista b  = a.getProximo();
                   if (ultX == b.getBX() && ultY == b.getBY() ){
                    b.setdX(a.getdX());
                    b.setdY(a.getdY());
                    //b.setDir(a.getDir());
                    b.setCXY(a.getcX(),a.getcY());
                  }
                  /*else if (b.getcX()== b.getBX() && b.getcY() == b.getBY() && b.getDir() == KeyEvent.VK_RIGHT ){
                    b.setdX(1);
                    b.setdY(0);
                    b.setDir(0);
                    //b.setCXY(a.getcX(),a.getcY());
                  //}else if (b.getcX()== b.getBX() && b.getcY() == b.getBY() && b.getDir() == KeyEvent.VK_UP ){
                    b.setdX(0);
                    b.setdY(-1);
                    b.setDir(0);
                    //b.setCXY(a.getcX(),a.getcY());
                 // }else if (b.getcX()== b.getBX() && b.getcY() == b.getBY() && b.getDir() == KeyEvent.VK_DOWN ){
                    b.setdX(0);
                    b.setdY(1);
                    b.setDir(0);
                    //b.setCXY(a.getcX(),a.getcY());
                  //}
                  
                  
                   //System.out.println(a.getX());
                  }
                  */
                 a = b;
                  }
           }     
        
    }
    
    public void actionPerformed(ActionEvent e) {
        if (head.getX() > 0 && head.getY() > 0 && head.getX() < 740 && head.getY() < 545){
        
        moveBody();   
        turnBody();
        head.move();  
        
        repaint(); 
        if (head.getX() >= food.getFX() && head.getX() <= food.getFX()+10 && head.getY() >= food.getFY() && head.getY() <= food.getFY()+10){
            randFood();
            score.addScore(10);
            inserirFinal(list[body]);
            add(list[body]);
            list[body].setdX(list[body-1].getdX());
            list[body].setdY(list[body-1].getdY());
            bodyPosition();
            body++;
        }
      }else{
          if (isPlaying == true){
              isPlaying = false;              
              repaint();
            }
      }
      
    }
    
    
    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            
            // Obtém o código da tecla
            int key =  e.getKeyCode();
            keyAnt = key;
            switch (key){
                case KeyEvent.VK_ENTER:
                    //score.addScore(100);
                    break;
                    
                case KeyEvent.VK_LEFT:
                    if (keyAnterior != key && keyAnterior != KeyEvent.VK_RIGHT ){
                    head.setdX(-1);                    
                    head.setdY(0);                    
                }
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    if (keyAnterior != key && keyAnterior != KeyEvent.VK_LEFT){
                    head.setdX(1);
                    head.setdY(0);                    
                }
                    break;
                    
                case KeyEvent.VK_UP:
                    //score.addScore(10);
                    if (keyAnterior != key && keyAnterior != KeyEvent.VK_DOWN ){
                    head.setdX(0);
                    head.setdY(-1);                   
                }
                    break;
                    
                case KeyEvent.VK_DOWN:
                    //score.subScore(-10);
                    if (keyAnterior != key && keyAnterior != KeyEvent.VK_UP ){
                    head.setdX(0);
                    head.setdY(1);                    
                }
                    break;
            }
            
                    ultX = head.getX();
                    ultY = head.getY();
                    
            keyAnterior = key;
            
              if (inicio != null){
              Lista a = inicio;
              if (a.getDir() <= 1){
              a.setDir(key);
              a.setCXY(ultX,ultY);
             }
              while(a.getProximo() != null){
               a = a.getProximo();
               if (a.getDir() == 0){
               a.setDir(key);
               a.setCXY(ultX,ultY);
               //System.out.println(a.getX());
              }
             }
           }  
        }
    }
    
}