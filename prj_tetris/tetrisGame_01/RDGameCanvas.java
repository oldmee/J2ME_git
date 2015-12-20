package tetrisGame_01;

import javax.microedition.lcdui.game.*;
import javax.microedition.lcdui.*;

public class RDGameCanvas extends GameCanvas implements Runnable 
{
    private NewLayerManager lyManager = new NewLayerManager();
    private TiledLayer RDTiledLayer;
    private spriteArray tempSpriteArray = new spriteArray();
    private Sprite RDSprite;
    Image image;
    int picChangeState, picChangeNum;
    int arrayX, arrayY;
    int score=0;
    Graphics g;
    int currenX = 0;
    int currenY = 0;
    int[][] RDSpriteArray;
    int[][] TLMap;
    int[] isRow=new int[16];
    java.util.Random rand = new java.util.Random(System.currentTimeMillis());
    
    
    public RDGameCanvas() 
    {
        super(true);
        g = getGraphics();
    }

    private void equalzaro() 
    {
        picChangeNum = picChangeState = arrayY = arrayX = currenY = currenX = 0;
    }

    public void run() 
    {
        init();
        while (true) 
        {
            keyPressed();
            RDSprite.setPosition(currenX, currenY++);
            arrayY = currenX / 10;
            arrayX = currenY / 10;

            if (isHit()) 
            {
                if (currenY <= RDSprite.getHeight())
                {
                    break;
                } 
                else 
                {
//                	clearnBoard();
                    changeTiledLayer();
                    equalzaro();
                    randSprite();
                }
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
        }
    }

    //TiledLayer初始化，及添加层管理面板
    private void init() 
    {
        try 
        {
            image = Image.createImage("/0.png");
            
            RDTiledLayer = new TiledLayer(12, 16, image, 10, 10);
            
            TLMap = new int[][] { {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1}
            };
            
            for (int i = 0; i < 12; i++) 
            {
                for (int j = 0; j < 16; j++) 
                {
                    RDTiledLayer.setCell(i, j, TLMap[j][i]);
                }
            }
            
            randSprite();
            lyManager.append(RDSprite);
            lyManager.append(RDTiledLayer);

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    //处理用户按键
    private void keyPressed() 
    {
        if ((this.getKeyStates() == this.UP_PRESSED)) 
        {
            picChangeState++;
            picChangeState %= 4;
            
            changePic(picChangeNum, picChangeState);
            
            if ((isHit() == true)&(picChangeState>0)) 
            {
                picChangeState--;
                picChangeState %= 4;
                changePic(picChangeNum, picChangeState);
            }
        }

        if ((this.getKeyStates() == this.LEFT_PRESSED)) 
        {
            if (currenX > 10) 
            {
                currenX = currenX - 10;
                RDSprite.setPosition(currenX, currenY);
            }
        }
        
        if ((this.getKeyStates() == this.RIGHT_PRESSED)) 
        {

            if (currenX < RDTiledLayer.getWidth() - RDSprite.getWidth() - 10) 
            {
                currenX = currenX + 10;
                RDSprite.setPosition(currenX, currenY);
            }

        }
        if ((this.getKeyStates() == this.DOWN_PRESSED)) 
        {

            if (currenY < RDTiledLayer.getHeight() - RDSprite.getHeight() - 10) 
            {
                currenY = currenY + 10;
                RDSprite.setPosition(currenX, currenY);
            }
        }

    }

    private void randSprite() 
    {
        int picNum = Math.abs(rand.nextInt()) % 7;
        int picState = Math.abs(rand.nextInt()) % 4;
        picChangeState = picState;
        picChangeNum = picNum;
        String picName = "/" + String.valueOf(picNum + 1) + ".png";
  
        try 
        {
            image = Image.createImage(picName);
            RDSprite = new Sprite(image);
            currenX = RDTiledLayer.getWidth() / 2;
            currenY = RDSprite.getHeight();
            RDSprite.setPosition(currenX, currenY);
            RDSprite.defineReferencePixel(RDSprite.getWidth() / 2, 0);
            lyManager.append(RDSprite);
            changePic(picNum, picState);
        } 
        catch (Exception e) 
        {
            System.out.println(e.toString());
        }
        
    }

    public void start() 
    {
        Thread t = new Thread(this);
        t.start();
    }

    private void writeArray(int[][] writearray) 
    {
        for (int i = 0; i < writearray.length; i++) 
        {
            for (int j = 0; j < writearray[i].length; j++) 
            {
                System.out.print(" " + writearray[i][j]);
            }
            System.out.println();
        }
    }

    private void writeArray(int[] writearray) 
    {
	    for (int i = 0; i < writearray.length; i++) 
	    {
              System.out.print(" " + writearray[i]);
        }
	    
	    System.out.println();
}


    private boolean isHit() 
    {
        if (RDSprite.collidesWith(RDTiledLayer, true) == true) 
        {
            return true;
        }
        return false;
    }

    private void changePic(int picChangeNum, int picChangeState) 
    {
        if (picChangeState == 0) 
        {
            RDSprite.setTransform(RDSprite.TRANS_NONE);
        }
        if (picChangeState == 1) 
        {
            RDSprite.setTransform(RDSprite.TRANS_ROT270);
        }
        if (picChangeState == 2) 
        {
            RDSprite.setTransform(RDSprite.TRANS_ROT180);
        }
        if (picChangeState == 3) 
        {
            RDSprite.setTransform(RDSprite.TRANS_ROT90);
        }
        
        RDSpriteArray = tempSpriteArray.getSpriteArray(picChangeNum * 4 +picChangeState);
       // writeArray(RDSpriteArray);

    }

    private void clearScreen() 
    {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(), getHeight());
        lyManager.paint(g, 10, 10);
         flushGraphics();

    }

    private void changeTiledLayer() 
    {
        for (int i = 0; i < RDSpriteArray.length; i++) 
        {
            if (arrayX + i < 16) 
            {
                for (int j = 0; j < RDSpriteArray[i].length; j++) 
                {
                    if (((arrayY + j) < 12) && (RDSpriteArray[i][j] == 1)) 
                    {
                        TLMap[arrayX + i][arrayY + j] = RDSpriteArray[i][j] + 1;
                    }
                }
            }
        }
      
        listTLMap();
        isROW();
       
        removeD();
     
        lyManager.remove(RDSprite);
    }
    
    private void listTLMap()
    {
        for (int i = 0; i < 12; i++) 
        {
           for (int j = 0; j < 16; j++) 
           {
               RDTiledLayer.setCell(i, j, TLMap[j][i]);
           }
       }
    }
    
    private void isROW()
    {
        int i,j;
        isRow[0]=isRow[15]=0;
        for (j= 1; j< 15; j++) 
        {
        	for (i= 1; i < 11; i++) 
        	{
        		if(TLMap[j][i]==2)
        		{
        			if(i>=10)isRow[j]=1;
        			continue;
        		}
        		else
        		{
					 isRow[j]=0;
					 break;
        		}
        	}
        }

    }

    private void removeD()
    {
          int j=0;
          
          for(int i=0;i<isRow.length-1;i++ )
          {
            if(isRow[i]==1)
            {
	             changeArray(i);
	             listTLMap();
	             j++;
           
            }
        }
        score+=j/2*10;
    }
    
    private void changeArray(int i)
    {
          int j;
          for(j=i;j>1;j--)
          {
        	  TLMap[j]=TLMap[j-1];
          }
          TLMap[1]=new int[]{1,0,0,0,0,0,0,0,0,0,0,1};
          score+=10;
          System.out.println("你现在的得分是："+score);
    }
    
    class NewLayerManager extends LayerManager
    {
    	public NewLayerManager()
    	{
    		super();
    	}
    	
    	public void paint (Graphics g,int i,int j)
    	{
    		super.paint(g,i,j);
    		g.setColor(0,0,0);
    		g.drawString("得分:"+String.valueOf(score),RDTiledLayer.getWidth()+10 ,10,Graphics.LEFT|Graphics.TOP );
    	}
    }
    
	private void clearnBoard()
	{
	     for(int j=0;j<12;j++)
	     {
	          TLMap[0][j]=1;
	          TLMap[15][j]=1;
	     }
	    
         for(int i=0;i<16;i++)
         {
           TLMap[i][0]=1;
           TLMap[i][11]=1;
         }
	}
}
