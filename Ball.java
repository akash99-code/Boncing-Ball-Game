import javax.swing.JPanel;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Graphics;
class Ball implements Runnable
{
  private double x, y;
  private int radius, speed, delay, max_width, max_height;
  private Board p;
  Image im;
  double angle;
  boolean alive;
  Color bcol;
  public Ball(double x,double y, int radius, int speed, int delay, int max_width, int max_height,int angle,Color c,Board p)
  {
    this.x= x;
    this.y= y;
    this.radius= radius;
    this.speed= speed;
    this.delay= delay;
    this.max_width= max_width;
    this.max_height= max_height;
    this.p=p;
    this.alive=true;
    this.angle=angle;
    this.bcol=c;
    //im= new ImageIcon("images3.jpeg").getImage();
  }

  public double getx()
  {
    return(x);
  }

  public double gety()
  {
    return(y);
  }

  public int getr()
  {
    return(radius);
  }
  
  public void drawBalls(Graphics g)
  {
    Graphics2D g2= (Graphics2D)g;
    g2.setColor(bcol);    
    g2.fillOval((int)getx(),(int)gety(),getr(),getr());
    //g2.drawImage(im,(int)x,(int)y,100);
    //g2.drawString("⚛",(int)getx(),(int)getx());
     
  }
  public void run()
  {
    double xstep,ystep;
    double Ssurface=(p.p.gety()-(radius));
    double RWall=max_width-radius;
    double LWall=0;
    double NWall=0;
    double tanco=0;
    while(true)
    {
      xstep=speed;
      tanco=Math.tan((angle/180)*Math.PI);
      ystep= Math.abs(tanco*speed);
      if(angle<0)
       ystep*=-1;
      if(Math.abs(angle)>90)
       xstep*=-1;
     
      if(y+ystep>Ssurface && alive)
      {
        xstep=Math.abs((Ssurface-y)/tanco);
        if(angle>90)
         xstep*=-1;
        y=Ssurface;
        ystep=0;        
      }
      else if(x+xstep>RWall)
      {
        ystep=Math.abs((RWall-x)*tanco);
        if(angle<0 )
         ystep*=-1;
        x=RWall;
        xstep=0;
      }
      else if(y+ystep<NWall)
      {
        xstep=Math.abs((y-NWall)/tanco);
        if(Math.abs(angle)>90)
         xstep*=-1;
        y=NWall;
        ystep=0;
      }
      else if(x+xstep<LWall)
      {
        ystep=Math.abs((x-LWall)*tanco);
        if(angle<0)
         ystep*=-1;
        x=LWall;
        xstep=0;
        //System.out.println(x);
      }
                  
      x+=xstep;       
      y+=ystep;
          
     if(y==Ssurface)
     { 
       if( x+radius>=p.p.getx() && x-radius<=p.p.getx()+p.p.getw()) 
       angle=-1*angle;
       else
        alive=false;
        //System.out.println(angle);
     }
            
    if(x==RWall || x==LWall)
    { 
      if(angle>0) angle=180-angle;
      else angle=-(angle+180);
      //System.out.println(angle);
   }
     
    if(y==NWall)
    {
     angle*=-1;
     //System.out.println(angle);
    }
    
    if(y==NWall && x==RWall)
     angle=135;
    if(x==LWall && y==NWall)
     angle=45;
     
    if(y>max_height)
     return;
     
      //System.out.println(x+","+y);
      //p.paintImmediately((int)x-20,(int)y-20,radius*4,radius*4);
      p.repaint();
      try
      {
        Thread.sleep(delay);
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
      
    }
  }
}
