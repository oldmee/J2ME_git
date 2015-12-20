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
	private int picChangeState,currentX,currentY;
	private TiledLayer tr;
	private LayerManager manager = new LayerManager();
	
	protected Game()
	{
		super(true);
		try
		{
			g = this.getGraphics();
//			init();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void init()
	{
		try
		{
			image_2 = Image.createImage("/0.png");
			tr = new TiledLayer(16,17,image_2,image_2.getWidth()/2,image_2.getHeight());
			picChangeState = 0;
			randSprite();
			manager.append(tr);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void clearScreen()
	{
		g.setColor(255, 255, 255);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		manager.paint(g,0,0);
	}
	
	private void randSprite()
	{
		try
		{
			currentX = tr.getWidth()/2;
			currentY = 10;
			int picNum = Math.abs(new Random().nextInt())%7;
			String picName="/"+String.valueOf(picNum+1)+".png";
			image_1 = Image.createImage(picName);
			sprite_1 = new Sprite(image_1);
			sprite_1.defineReferencePixel(image_1.getWidth()/2,image_1.getHeight()/2);
			sprite_1.setPosition(currentX,0);
			manager.append(tr);
			manager.append(sprite_1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		init();
		while (true)
		{
			keyPressed();
			tr.fillCells(0,15,15,1,1);
			tr.fillCells(0,0,1,15,1);
			tr.fillCells(15,0,1,16,1);
			tr.paint(g);
			sprite_1.setPosition(currentX,currentY++);
			if(sprite_1.collidesWith(tr,true))
			{
				randSprite();
			}
			clearScreen();
			try
			{
				Thread.sleep(100);
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
			picChangeState++;
			picChangeState %= 4;
			changePic(picChangeState);
			if(sprite_1.collidesWith(tr,true)&&picChangeState>0)
			{
				picChangeState--;
				picChangeState %= 4;
				changePic(picChangeState);
			}
		}
		if(this.getKeyStates() == this.LEFT_PRESSED)
		{
			if(currentX  >  10)
			{
				currentX -= 10;
				sprite_1.setPosition(currentX,currentY);
			}
		}
		if(this.getKeyStates() == this.RIGHT_PRESSED)
		{
			if(currentX < (tr.getWidth()-sprite_1.getWidth()-10))
			{
				currentX += 10;
				sprite_1.setPosition(currentX,currentY);
			}
		}
	}
	private void changePic(int picChangeState)
	{
		if(picChangeState == 0)
		{
			sprite_1.setTransform(sprite_1.TRANS_NONE);
		}
		if(picChangeState == 1)
		{
			sprite_1.setTransform(sprite_1.TRANS_ROT90);
		}
		if(picChangeState == 2)
		{
			sprite_1.setTransform(sprite_1.TRANS_ROT180);
		}
		if(picChangeState == 3)
		{
			sprite_1.setTransform(sprite_1.TRANS_ROT270);
		}
	}

}
