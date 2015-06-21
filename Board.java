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

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Score score;
    private Snake head;
    private Snake food;
    private Snake floor;
    private int keyAnterior = KeyEvent.VK_LEFT;
    
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
       
        add(food);
        add(score);       
        add(head);   
        // add(floor);
        randFood();
        
        timer = new Timer(15, this);
        timer.start();
    }
    
    public void randFood(){
        Random rand = new Random();
        food.setFXY(1 + (int)rand.nextInt(740),1 + (int)rand.nextInt(545));
    }

    public void paint(Graphics g) {
        super.paint(g);
        
        score.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g;        
         if (isPlaying == true){
             //ImageIcon ii = new ImageIcon(this.getClass().getResource("images/floor.jpg"));
           
            //g2d.drawImage(ii.getImage(),0,0,this); 
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
    
    public void actionPerformed(ActionEvent e) {
        if (head.getX() > 0 && head.getY() > 0 && head.getX() < 740 && head.getY() < 545){
        head.move();        
        repaint(); 
        if (head.getX() >= food.getFX() && head.getX() <= food.getFX()+10 && head.getY() >= food.getFY() && head.getY() <= food.getFY()+10){
            randFood();
            score.addScore(10);
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
            
            keyAnterior = key;
        }
    }
    
}