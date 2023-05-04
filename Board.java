import java.awt.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.Rectangle2D;
public class Board extends JPanel implements ActionListener
{
  
  Platform p;
  LinkedList<Bullet> B;
  Level lev;
  Boolean gameover;
  //Bomb neut;
  int nb;
  private Ball balls[];Thread t[];
  private int width, height;
  private Timer timer;
  private Info inf;
  public Board()
  {
    width=1000;
    height=600;
    initBoard();
    timer= new Timer(50,this);
    nb=5;
    balls=new Ball[nb];
    balls[0]=new Ball(20.0,0.0,25,5,50,width,height,50,new Color(13,205,233),this);
   balls[1]=new Ball(500.0,0.0,40,5,70,width,height,120,new Color(215,109,238),this);
    balls[2]=new Ball(300.0,300.0,20,5,45,width,height,-45,new Color(136,255,55),this);
    balls[3]=new Ball(400.0,400.0,15,5,40,width,height,-160,new Color(249,135,65),this);
    balls[4]=new Ball(100.0,100.0,10,5,40,width,height,-30,new Color(245,239,102),this);
    p=new Platform(0,height-20,50,10,width,this);
    addMouseMotionListener(p); 
    addMouseListener(p); 
    inf=new Info(nb);
    //neut=new Bomb(500,0,p);
    B=new LinkedList<Bullet>();
    lev=new Level(inf,p);  
    gameover=false;  
              
  }
  
  public void initBoard()
  {
    setPreferredSize(new Dimension((int)width,(int)height));
    setBackground(Color.BLACK);
  }
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    if(gameover)
    {
       g.setFont(new Font("Nimbus Mono L",Font.BOLD,60));
       g.setColor(Color.white);
       g.drawString("GAME OVER",30,250);
       return;
    }
    for(int i=0;i<nb;i++)
     balls[i].drawBalls(g);
    p.drawPlatform(g);
    inf.drawinfo(g);
    //if(B!=null)
    for(int i=0;i<B.size();i++)
    {
      B.get(i).drawbullet(g);;  
    }
    //if(neut.alive)neut.drawbomb(g);
    lev.displaylevel(g);
  }
  

  public void addNotify()
  {
    super.addNotify();
    t=new Thread[nb];
    for(int i=0;i<nb;i++)
     {
       t[i]= new Thread(balls[i]);
       t[i].start();         
     } 
    timer.start();
    lev.start();
  }

  public void actionPerformed(ActionEvent e)
  {
      
    for(int i=0;i<B.size();i++)
    {
      if(B.get(i).alive==false)
       B.remove(i);
      else
       B.get(i).inc();  
    }

    //if(neut.speed!=0)neut.inc();
    if(!gameover)
     lev.move();
    else
    {
      lev.stop();
      timer.stop();
    }

    if(inf.ballsleft!=0)
     inf.scoreinc();
    else
     gameover=true;
    
    for(int i=0;i<nb;i++)
    {
      if(t[i]!=null && balls[i].alive==false)
      {         
        inf.balldied();
        t[i]=null;
      }
      
    }
    repaint();
  }
  
    
}
