package tetrisGame_01;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class RDMIDlet extends MIDlet implements CommandListener
{
	private List list;
	
	public RDMIDlet()
	{
		list = new List("����˹����", List.IMPLICIT);
		
		list.append("��ʼ��Ϸ", null);
		list.append("��Ϸ˵��", null);
		list.append("������Ϸ", null);
		
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
		
		if(command.getLabel().equals("����"))
		{
			Display.getDisplay(this).setCurrent(list);
		}
		
		if(list.getSelectedIndex()==1)
		{
			Form form = new Form("��Ϸ˵��");
			
			String str = "��ӭʹ��";
			
			form.append(str);
			
			form.addCommand(new Command("����",Command.EXIT,1));
			
			form.setCommandListener(this);
			
			Display.getDisplay(this).setCurrent(form);
		}
		
		if(list.getSelectedIndex()==2)
		{
			this.destroyApp(true);
			this.notifyDestroyed();
			
		}
		// TODO �Զ����ɷ������
		
	}
}

