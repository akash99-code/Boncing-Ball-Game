 import java.awt.*;
 import javax.swing.*;
import java.util.*;
class Bullet //implements Runnable
{
  int x,y,bx,by;
  boolean alive;
  LinkedList<Bomb> Neut;
  public Bullet(int x,int y,LinkedList<Bomb> Neut)
  {
    this.x=x;
    this.y=y;
    alive=true;
    this.Neut=Neut;
  }
  void inc()
  {
     y=y-5;
     for(int i=0;i<Neut.size();i++)
     {
       Bomb p=Neut.get(i);
       if(p.alive)
       { 
         bx=p.x;
         by=p.y;
        if(x>=bx && x<=bx+25 && y<=by+10)
        {
           System.out.println("blaaast");
           alive=false;
           p.alive=false;
           p.speed=0;
           return;
        }
       } 
     } 
     if(y<0)
      alive=false;
   }
  void drawbullet(Graphics g)
  {
    if(!alive)
     return;
    g.setColor(Color.white);
    g.fillRect(x,y,3,6);
  }
  
}

/**public void run()
  {
    y--;
    //System.out.println(y);
    p.repaint() ;
    if(y<0)
    {
     alive=false;
     return;
    }
      try
      {
        Thread.sleep(1000);
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
     
  }*/
  

