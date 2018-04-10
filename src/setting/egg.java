package setting;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class egg {
	private int i_x,i_y;
	private static final int Block_Size=Yard.BLOCK_SIZE;
	private boolean flagCol=true;
	egg(int i_x,int i_y)
	{
		this.i_x=i_x;
		this.i_y=i_y;
	}
	public void setNewEgg()
	{
		this.i_x=(int)(Math.random()*(Yard.x-1))+1;
		this.i_y=(int)(Math.random()*(Yard.y-1))+1;
	}
	egg()
	{
		this((int)(Math.random()*(Yard.x-1))+1,(int)(Math.random()*(Yard.y-1))+1);
	}
	public Point getPoint()
	{
		return new Point(i_x*Block_Size, i_y*Block_Size);
	}
	public void drawegg(Graphics g)
	{
		if(flagCol){
		g.setColor(Color.green);	
		g.fillOval(i_x*Block_Size, i_y*Block_Size, Block_Size, Block_Size);
		flagCol=false;
		}
		else {
			g.setColor(Color.red);
			g.fillOval(i_x*Block_Size, i_y*Block_Size, Block_Size, Block_Size);
			flagCol=true;
		}
	}

}
