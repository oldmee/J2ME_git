package tetrisGame;
import java.util.Random;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.game.TiledLayer;

public class Game extends GameCanvas implements Runnable
{
	private Graphics g;
	private Sprite sprite_1, sprite_2;
	private Image image_1, image_2;
	private boolean flag = true, temp = true;
	private int picChangeState,count=1;
	private TiledLayer tr;
	private LayerManager manager;
	
	protected Game(boolean arg0)
	{
		super(arg0);
		try
		{
			g = this.getGraphics();
			manager = new LayerManager();
			image_2 = Image.createImage("/0.png");
			tr = new TiledLayer(this.getWidth()/10,this.getHeight()/10,image_2,image_2.getWidth()/2,image_2.getHeight());
			int picNum = Math.abs(new Random().nextInt())%7;
			String picName="/"+String.valueOf(picNum+1)+".png";
			image_1 = Image.createImage(picName);
			sprite_1 = new Sprite(image_1);
			sprite_1.setPosition(this.getWidth()/2, 0);
			picChangeState = 0;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void run()
	{
		while (flag)
		{
			keyPressed();
			g.setColor(255, 255, 255);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			tr.fillCells(0,this.getHeight()/10-2,this.getWidth()/10,1,1);
			tr.paint(g);
			sprite_1.paint(g);
			manager.append(tr);
			manager.paint(g,0,0);
			
			sprite_1.setPosition(0,count++);
			if (sprite_1.getRefPixelX() < 0 || sprite_1.getRefPixelX() > this.getWidth())
			{
				temp = true;
			}
			if(sprite_1.collidesWith(tr,true))
			{
				flag = false;
				
				Thread.currentThread().run();
				flag = true;
			}
			try
			{
				Thread.sleep(10);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			this.flushGraphics();
		}
	}
	
	private void keyPressed()
	{
		if(this.getKeyStates() == this.UP_PRESSED)
		{
			System.out.println("up");
			picChangeState++;
			picChangeState %= 4;
			changePic(picChangeState);
		}
		if(this.getKeyStates() == this.LEFT_PRESSED)
		{
			System.out.println("left");
			if(sprite_1.getX()>0)
				sprite_1.setPosition(sprite_1.getX()-10,sprite_1.getY());
		}
		if(this.getKeyStates() == this.RIGHT_PRESSED)
		{
			System.out.println("right");
			if(sprite_1.getX()<this.getWidth()-sprite_1.getWidth())
				sprite_1.setPosition(sprite_1.getX()+10,sprite_1.getY());
		}
	}
	private void changePic(int picChangeState)
	{
		sprite_1.defineReferencePixel(image_1.getWidth()/2,image_1.getHeight()/2);
		if(picChangeState == 0)
		{
			sprite_1.setTransform(Sprite.TRANS_NONE);
		}
		if(picChangeState == 1)
		{
			sprite_1.setTransform(Sprite.TRANS_ROT90);
		}
		if(picChangeState == 2)
		{
			sprite_1.setTransform(Sprite.TRANS_ROT180);
		}
		if(picChangeState == 3)
		{
			sprite_1.setTransform(Sprite.TRANS_ROT270);
		}
	}
	
	
	
}
