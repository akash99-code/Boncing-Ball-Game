import java.awt.*;
import javax.swing.*;
import java.util.*;
class Bomb
{
  int x,y;
  int speed;
  boolean alive;
  Platform p;
  Font f;
  public Bomb(int x,int y,int spe,Platform p)
  {
    this.x=x;
    this.y=0;
    alive=true;
    this.p=p;
    speed=spe;
    f=new Font("Symbola",Font.BOLD,30);
  }
  void inc()
  {
     y=y+speed;
     if(y+5>=Math.floor(p.gety()) && Math.floor(p.getx())<=x && x<=Math.floor(p.getx()+p.getw()))
      {
        System.out.println("blast");
        y=(int)Math.floor(p.gety());
        speed=0;
        alive=false;
        p.p.gameover=true;
      }

     if(y>p.gety()+40)
     {
       alive=false;
       speed=0;
     }
   }
  void drawbomb(Graphics g)
  {
    g.setColor(Color.red);
    g.setFont(f);
    g.drawString("⚛",x,y);
  }
}
