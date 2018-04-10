package setting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
public class snake {

	private Node snakeTail=null;
    private Node snakeHead=null;
    private static int snakeSize;
    private boolean gameisover=true;
    private Node  snakestart=new Node(20, 20, Dir.R);
    public snake() {
		this.snakeTail = snakestart;
		this.snakeHead = snakestart;
		this.snakeSize = 1;
	}
    public void AddTheTail()
    {
    	Node head=null;
    	switch (snakeHead.DIR) {
		case U:
			head=new Node(snakeHead.s_x,snakeHead.s_y-1,snakeHead.DIR);
			break;
        case D:
        	head=new Node(snakeHead.s_x,snakeHead.s_y+1,snakeHead.DIR);
			break;
        case R:
        	head=new Node(snakeHead.s_x+1,snakeHead.s_y,snakeHead.DIR);
			break;
        case L:
        	head=new Node(snakeHead.s_x-1,snakeHead.s_y,snakeHead.DIR);
			break;
	   
		}
        head.next=snakeHead;
        snakeHead.prev=head;
        snakeHead=head;
        snakeSize++;
    }
	public static int getSnakeSize() {
		return snakeSize;
	}
	public void draw(Graphics g)
	{
		if(snakeSize<=0)  return;
		move();
		for(Node n=snakeHead;n!=null;n=n.next)
		{
			n.Draw(g);
		}

	}
	public void move() 
	{
	  AddTheTail();	
	  deleteFromTail();
	 
	}
	public Point getSnakeHeadPonit()
	{
		return  new Point(this.snakeHead.s_x*Yard.BLOCK_SIZE,this.snakeHead.s_y*Yard.BLOCK_SIZE);
	}
	public void snakeAndEggMeet(egg e)
	{
		
		if(this.getSnakeHeadPonit().equals(e.getPoint()))
		{
			System.out.println("add a new head");
			addATatil();
			e.setNewEgg();
			
		}
		return;
	}
	private void setgameIsOver()
	{
		gameisover=false;
	}
	public boolean gameIsOver1()
	{
		if(snakeHead.s_x<=0||snakeHead.s_y<=0||snakeHead.s_x>=Yard.x||snakeHead.s_y>=Yard.y) 
		setgameIsOver();
		for(Node n=snakeHead.next;n!=null;n=n.next)
			if(n.equals(snakeHead))
				setgameIsOver();
		return gameisover;
	}
	
	public void addATatil()
	{
		AddTheTail();	
	}
	private void deleteFromTail() {
		if(snakeSize == 0) return;
		System.out.println(snakeSize);
		snakeTail=snakeTail.prev;
        snakeTail.next=null;
        snakeSize--;
	}
	
	private class Node
	{
		private int s_x,s_y;
	    private final int SnakeBlock_SIZE=Yard.BLOCK_SIZE;
	    private Dir DIR=Dir.L;
	    Node next=null;
	    Node prev=null;
	    
	    public Node(int s_x,int s_y,Dir DIR) {
			this.s_x=s_x;
			this.s_y=s_y;
			this.DIR=DIR;
		}
	    
		void Draw(Graphics g){
			Color c=g.getColor();
			g.setColor(Color.BLUE);
			g.fillRect(s_x*Yard.BLOCK_SIZE, s_y*Yard.BLOCK_SIZE, SnakeBlock_SIZE,SnakeBlock_SIZE);
			g.setColor(c);
		}
		@Override
		public boolean equals(Object obj) {
			Node c=(Node)obj;
			if(this.s_x==c.s_x&&this.s_y==c.s_y)
			     return true;
			return false;
			
		}
		
	}
	public void KeyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_RIGHT:
		//	System.out.println("The Snake is moving rightly");
			if(snakeHead.DIR!=Dir.L)
			snakeHead.DIR=Dir.R;
			break;
        case KeyEvent.VK_LEFT:
        	//System.out.println("The Snake is moving leftly");
        	if(snakeHead.DIR!=Dir.R)
        	snakeHead.DIR=Dir.L;
			break;
        case KeyEvent.VK_UP:
        	//System.out.println("The Snake is moving uply");
        	if(snakeHead.DIR!=Dir.D)
        	snakeHead.DIR=Dir.U;
			break;
        case KeyEvent.VK_DOWN:
        	//System.out.println("The Snake is moving downly");
        	if(snakeHead.DIR!=Dir.U)
        	snakeHead.DIR=Dir.D;
			break;
		case KeyEvent.VK_F2:
			break;
		}
	}
    
}
