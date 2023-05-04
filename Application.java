import java.awt.EventQueue;
import java.awt.Color;
import javax.swing.JFrame;

class Application extends JFrame
{
  public Application()
  {
    init();
  }

  public void init()
  {
    setSize(450,450);
    add(new Board());
    pack();    
    setTitle("Bouncing Ball");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
  }

  public static void main(String[] args)
  {
    EventQueue.invokeLater(
      new Runnable(){
        public void run()
        {
          Application a= new Application();
          a.setVisible(true);
        }
      }
    );
  }
}
