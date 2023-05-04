import javax.swing.JPanel;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.*;
class Level extends Thread
{
  
  Info inf;
  Platform p;
  LinkedList<Bomb> Neut;
  public Level(Info inf,Platform p)
  {
    Neut=new LinkedList<Bomb>();
    this.p=p;
    this.inf=inf;
  }

  public void displaylevel(Graphics g)
  {
    Graphics2D g2= (Graphics2D)g;
    for(int i=0;i<Neut.size();i++)
    {
       if(Neut.get(i).alive)Neut.get(i).drawbomb(g);
     }
     
  }
  public void move()
  {
     for(int i=0;i<Neut.size();i++)
     {
        if(Neut.get(i).speed!=0)Neut.get(i).inc();
      }
   }
   public void plantbomb(int ln)
   {
      switch(ln)
      {
         case 1:
          Neut.push(new Bomb(500,0,8,p));
         break;
         case 2:
          Neut.push(new Bomb(250,-20,8,p));
          Neut.push(new Bomb(750,-20,8,p));
         break;
         case 3:
          Neut.push(new Bomb(250,-40,8,p));
          Neut.push(new Bomb(750,-40,8,p));
          Neut.push(new Bomb(500,0,8,p));
          Neut.push(new Bomb(150,-60,8,p));
          Neut.push(new Bomb(850,-60,8,p));
         break;
         case 4:
         break;
         case 5:
         break;
       }
   }
  public void run()
  {
    while(true)
    {
       if(inf.score>=28 && inf.level==0)
        plantbomb(++inf.level);
       if(inf.score>=40 && inf.level==1)
       {
         plantbomb(++inf.level);
         plantbomb(1);
       }
       if(inf.score>=50 && inf.level==2)
        plantbomb(++inf.level);

       else
        yield();
     }

  }
}
