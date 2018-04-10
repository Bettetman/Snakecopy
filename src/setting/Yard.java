package setting;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardUpLeftHandler;

public class Yard extends Frame {
	public static  int x=40;
	public static  int y=40;
	public  static int BLOCK_SIZE=15;
    public static int Mark[]=new int[20];
    public static int total=0;
    public static int Checkpoint=1;
	
	public snake testsnake=new snake();
	public egg testegg=new egg();
	Image paintimage=null;

	public  void setMark() {
	    Yard.Mark[0]=0;
	    Yard.Mark[1]=10;
	    Yard.Mark[2]=20;
	    for(int i=3;i<Mark.length;i++)
	    {
	    	Yard.Mark[i]=Yard.Mark[i-1]+Yard.Mark[i-2];
	    }
	      
	}
	public int gettotal()
	{
		return (this.Checkpoint-1)*Mark[19];
	}

	public void lauch()
	{
		setMark();
		setBounds(200, 200, x * BLOCK_SIZE, y *BLOCK_SIZE);
		setBackground(Color.white);
		addKeyListener(new SnakeLister());
		addWindowListener(new CloseTheSnake());
		Thread xiaoxutest=new Thread(new paintTh());
		xiaoxutest.start();
		setVisible(true);
	}
	@Override
	public void update(Graphics g) {
		Color c=g.getColor();
		if(paintimage==null)
		{
			paintimage=createImage(x*BLOCK_SIZE, y*BLOCK_SIZE);
	     }
	    Graphics fi=paintimage.getGraphics();
	    paint(fi);
	    g.drawImage(paintimage, 0, 0, null);
	    g.setColor(c);
	}
	@Override
	public void paint(Graphics g) {
		Color c=g.getColor();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, x * BLOCK_SIZE, y *BLOCK_SIZE);
		//»­xÏß
	   g.setColor(Color.GRAY);
	   for(int i=1;i<=x;i++)
	   {
		  
		  g.drawLine(i * BLOCK_SIZE, 0, i*BLOCK_SIZE, y*BLOCK_SIZE);
	   }
	   for(int i=1;i<=y;i++)
	   {
		  
		  g.drawLine(0,i*BLOCK_SIZE,x*BLOCK_SIZE,i*BLOCK_SIZE);
	   }
	    g.setColor(Color.black);
	    String str="Score:"+Yard.Mark[testsnake.getSnakeSize()-1]+"    Checkpoint:"+Checkpoint+"    total:"+(gettotal()+Yard.Mark[testsnake.getSnakeSize()-1]);
	    g.drawString(str,10,60);
	    g.setColor(Color.yellow);
	    if(!testsnake.gameIsOver1())
	    {
	    	g.setFont(new Font("ËÎÌå", Font.ITALIC, 80));
	    	g.drawString("Game Is Over",100, 320);
	    }
		g.setColor(c);
		testsnake.snakeAndEggMeet(testegg);
		testsnake.draw(g);
		testegg.drawegg(g);
	}
	public class paintTh extends Thread
	{

		@Override
		public void run() {
			while (true) {
				repaint();
				if(!testsnake.gameIsOver1())
					break;
				try {
					Thread.sleep(200);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	
	}
	public class SnakeLister extends KeyAdapter
	{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_F2)
			{
				
			}
			testsnake.KeyPressed(e);
			}
		
		
	}
	public class CloseTheSnake extends WindowAdapter
	{

		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("Snake is closing");
			System.exit(0);
		}
		
	}
}
