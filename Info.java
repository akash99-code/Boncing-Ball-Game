import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.*;
import javax.swing.*;
class Info 
{
    double score;
    int ballsleft;
    int level;
    Font f;
    public Info(int bl)
    {
      score=0;
      ballsleft=bl;
      level=0;
      f=new Font("Nimbus Mono L",Font.BOLD,20);
    }
    void scoreinc()
    {
      score+=0.0625;
    }
    void balldied()
    {
      ballsleft--;
    }
    void drawinfo(Graphics g)
    {
      Graphics2D g2= (Graphics2D)g;
      g2.setColor(new Color(52,36,14,100));
      g2.fillRect(0,0,900,40);
      g2.setFont(f);
      g2.setColor(new Color(182,218,218));
      g2.drawString("SCORE-"+(int)score+"                        LEVEL-"+level+"                        "+"Balls Left-"+ballsleft,20,25);
    }
}
