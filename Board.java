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
    //private int keyAnt = KeyEvent.VK_LEFT;
    private Lista inicio;
    //private Lista list[];
    private int body = 0;
    private int velocidade = 100;
    // private int ultX = 0;
    //private int ultY = 0;
    private boolean isPlaying = false;
    private boolean isFood = false;
    private String frase;
    

    private Font font;
       
    public Board() {

        addKeyListener(new TAdapter());
        setFocusable(true);        
        setDoubleBuffered(true);
        setBackground(Color.WHITE);
        score = new Score();
        head = new Snake();
        food = new Snake();
        floor = new Snake();
        add(food);
        add(score);       
        add(head); 
        head.setDir(KeyEvent.VK_LEFT);
        add(floor);
        randFood();
        

               
        timer = new Timer(velocidade, this);
        timer.start();
    }
    
    public void iniciar(){
        if (!isPlaying){
        
        isPlaying = true;
        score = new Score();
        head = new Snake();
        food = new Snake();
        floor = new Snake();
        add(food);
        add(score);       
        add(head); 
        head.setDir(KeyEvent.VK_LEFT);
        add(floor);
        randFood();
        velocidade = 100;
        inicio = null;
        frase = "Pressione ENTER para iniciar o jogo! "
        listGenerator(); 
        
       }
    }
    
    public void listGenerator(){         
            for (int i = 0; i < 4 ; i++) {           
           head.move();
           add(inserirFinal(new Lista("body"+body)));
           body++;           
           turnBody();
           atualizaDir();
         } 
    }
    
    public void atualizaDir(){
        if (inicio != null){
              Lista a = inicio;
              int dir2 = a.getDir();
              int dir = head.getDir();           
              a.setDir(dir);               
              dir = dir2;        
             while(a.getProximo() != null){  
                  a  = a.getProximo();                  
                  dir2 = a.getDir();                  
                  a.setDir(dir);
                  dir = dir2;
                  }
           } 
        
    }
    
    public void adcBody(){        
        add(inserirFinal(new Lista("body"+body)));
        body++;
    }
    
    public void randFood(){
        Random rand = new Random();
        food.setFXY(1 + (int)rand.nextInt(740),1 + (int)rand.nextInt(545));
    }
    
    public Lista inserirFinal(Lista _node){
        if(isEmpty()){
            inicio = _node;
        }else{
            Lista aux = inicio;
            while(aux.getProximo() != null){
                aux = aux.getProximo();
            }
            aux.setProximo(_node);
        }
        return _node;
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
         Graphics2D g2d = (Graphics2D)g; 
         if (isPlaying == true){
             g2d.drawImage(floor.getImageFloor(),0,2,this);
             score.paintComponent(g); 
              if (inicio != null){
              Lista a = inicio;
              g2d.drawImage(a.getImageBody(),a.getBX(),a.getBY(),this); 
              while(a.getProximo() != null){
               a = a.getProximo();
               g2d.drawImage(a.getImageBody(),a.getBX(),a.getBY(),this);
              
             }
           }  
           
            g2d.drawImage(head.getImage(),head.getX(),head.getY(),this); 
            
            g2d.drawImage(food.getImageFood(),food.getFX(),food.getFY(),this); 
              
            }else if (frase.Equals("Pressione ENTER para iniciar o jogo! ")){               
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
            g2d.drawImage(floor.getImageFloor(),0,2,this);
           g2d.drawString("Game Over", 350, 250);
            }
       
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }    
    
    public void turnBody(){
          if (inicio != null){
              Lista a = inicio;
               
               int aux2 = a.getBX();
               int auy2 = a.getBY();
               int aux = head.getX();
               int auy = head.getY();
              
               a.setBX(aux);
               a.setBY(auy);
               
               aux = aux2;
               auy = auy2; 
               
               
             while(a.getProximo() != null){  
                 a  = a.getProximo();
                  aux2 = a.getBX();
                  auy2 = a.getBY();
                
                  a.setBX(aux);
                  a.setBY(auy);
                  
                  aux = aux2;
                  auy = auy2; 
                   
                  }
             atualizaDir();
           }
           
    }
    public boolean baterCorpo(){
        if (inicio != null){
              Lista a = inicio;
              /*if (head.getX() == a.getBX() && head.getX() == a.getBX() && head.getY() == a.getBY() && head.getY() == a.getBY()){
                  return true;
                }
                */
              while(a.getProximo() != null){
               a = a.getProximo();
               if (head.getX() == a.getBX() && head.getX() == a.getBX() && head.getY() == a.getBY() && head.getY() == a.getBY()){
                  return true;
                }
             }
             
           }  
           return false;
    }
    public void actionPerformed(ActionEvent e) {
        if ((head.getX() > 0 && head.getY() > 0 && head.getX() < 740 && head.getY() < 545 && baterCorpo() == false && isPlaying)  ){
        
        //moveBody();
        //
        turnBody();
        head.move(); 
       
        
        
        
        if (head.getX() >= food.getFX()-10 && head.getX() <= food.getFX()+10 && head.getY() >= food.getFY()-10 && head.getY() <= food.getFY()+10){
            randFood();
            velocidade = velocidade - 2;
            score.addScore(10);           
            adcBody();
            turnBody();
            
            //inserirFinal(list[body]);
            //add(list[body]);
            //list[body].setdX(list[body-1].getdX());
            //list[body].setdY(list[body-1].getdY());
            //bodyPosition();
            //body++;
        }
        repaint(); 
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
            //keyAnt = key;
            switch (key){
                case KeyEvent.VK_ENTER:
                    //score.addScore(100);
                 // adcBody();
                  //turnBody()
                  iniciar();
                  //isPlaying = true;
                  
                    break;
                    
                case KeyEvent.VK_LEFT:
                    if (keyAnterior != key && keyAnterior != KeyEvent.VK_RIGHT ){
                    head.setdX(-20);                    
                    head.setdY(0); 
                    head.setDir(key);
                }
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    if (keyAnterior != key && keyAnterior != KeyEvent.VK_LEFT){
                    head.setdX(20);
                    head.setdY(0); 
                    head.setDir(key);
                }
                    break;
                    
                case KeyEvent.VK_UP:
                    //score.addScore(10);
                    if (keyAnterior != key && keyAnterior != KeyEvent.VK_DOWN ){
                    head.setdX(0);
                    head.setdY(-20);  
                    head.setDir(key);
                }
                    break;
                    
                case KeyEvent.VK_DOWN:
                    //score.subScore(-10);
                    if (keyAnterior != key && keyAnterior != KeyEvent.VK_UP ){
                    head.setdX(0);
                    head.setdY(20); 
                    head.setDir(key);
                }
                    break;
            }
            
                    //ultX = head.getX();
                    //ultY = head.getY();
                  
                    
            keyAnterior = key;
            
           /*   if (inicio != null){
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
           */
        }
    }
    
}