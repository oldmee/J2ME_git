package tetrisGame_01;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class RDMIDlet extends MIDlet implements CommandListener
{
	private List list;
	
	public RDMIDlet()
	{
		list = new List("俄罗斯方块", List.IMPLICIT);
		
		list.append("开始游戏", null);
		list.append("游戏说明", null);
		list.append("结束游戏", null);
		
		list.setCommandListener(this);
		
	}
	
    public void startApp() 
    {
        RDGameCanvas RD= new RDGameCanvas();
        RD.start();
        Display.getDisplay(this).setCurrent(list);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
    private void writeArray(int[][] writearray){
      for(int i=0;i<writearray.length;i++){
         for(int j=0;j<writearray[i].length;j++){
            System.out.print(" "+ writearray[i][j])  ;
        }
           System.out.println();
     }
    }


	public void commandAction(Command command, Displayable dis) 
	{
		if(list.getSelectedIndex()==0)
		{
			RDGameCanvas RD= new RDGameCanvas();
	        RD.start();
	        Display.getDisplay(this).setCurrent(RD);
		}
		
		if(command.getLabel().equals("返回"))
		{
			Display.getDisplay(this).setCurrent(list);
		}
		
		if(list.getSelectedIndex()==1)
		{
			Form form = new Form("游戏说明");
			
			String str = "欢迎使用";
			
			form.append(str);
			
			form.addCommand(new Command("返回",Command.EXIT,1));
			
			form.setCommandListener(this);
			
			Display.getDisplay(this).setCurrent(form);
		}
		
		if(list.getSelectedIndex()==2)
		{
			this.destroyApp(true);
			this.notifyDestroyed();
			
		}
		// TODO 自动生成方法存根
		
	}
}

