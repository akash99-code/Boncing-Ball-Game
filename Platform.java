 import java.awt.*;
 import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
 class Platform implements MouseMotionListener,MouseListener
 {
   private double x, y, width, height, max_width;
    Board p;
   private Cursor inv;
   Thread t;
   public Platform(double x,double y,double width,double height,double max_width,Board p)
   {
    this.x=x;
    this.y=y ;
    this.width=width;
    this.height=height;
    this.max_width= max_width;
    this.p= p;
    Toolkit tk=Toolkit.getDefaultToolkit();
    Image img=tk.getImage("emptyImage.gif");
    inv=tk.createCustomCursor(img,new Point(10,10),"invisi");
   }
   public Double getx()
  {
    return(x);
  }

  public Double gety()
  {
    return(y);
  }

  public Double getw()
  {
    return(width);
  }

  public Double geth()
  {
    return(height);
  }
  public void drawPlatform(Graphics g)
  {
    Graphics2D g2= (Graphics2D)g;
    g2.setColor(new Color(209,211,209));
    Rectangle2D r=new Rectangle2D.Double(x,y,width,height);
    g2.fill(r); 
     
  }
   public void mouseMoved(MouseEvent e)
   {
    p.setCursor(inv);   
    x=e.getPoint().getX()-(width/2);    
    p.repaint(0,(int)y,(int)max_width,(int)y);
   }
  public void mouseDragged(MouseEvent e)
  {    
    x=e.getPoint().getX()-(width/2);   
    if(p.B.get(0).y<y-20)    
     p.B.push(new Bullet(e.getX(),(int)this.y,p.lev.Neut));
    p.repaint(0,(int)y,(int)max_width,(int)y);
  }
  public void mousePressed(MouseEvent e)
  {
     p.B.push(new Bullet(e.getX(),(int)this.y,p.lev.Neut));
  }
  public void mouseClicked(MouseEvent e)
  {        
     p.B.push(new Bullet(e.getX(),(int)this.y,p.lev.Neut));       
  }
  
  public void mouseExited(MouseEvent e)
  {
    
  }
  public void mouseEntered(MouseEvent e)
  {
    
  }
  public void mouseReleased(MouseEvent e)
  {
    
  }
 }
