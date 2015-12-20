package tetrisGame;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class Main extends MIDlet implements CommandListener
{
	Display display = Display.getDisplay(this);
	private List list;
	private Game game;
	public Main()
	{
		game = new Game(true);
		list = new List("Tetris", List.IMPLICIT);
		list.append("start", null);
		list.append("exit", null);
		list.setCommandListener(this);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException
	{
		// TODO Auto-generated method stub

	}

	protected void pauseApp()
	{
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException
	{
		display.setCurrent(list);
	}

	public void commandAction(Command command, Displayable dis)
	{
		if (list.getSelectedIndex() == 0)
		{
			display.setCurrent(game);
			Thread th = new Thread(game);
			th.start();
		}
		if (list.getSelectedIndex() == 1)
		{
			try
			{
				this.destroyApp(true);
				this.notifyDestroyed();
			}
			catch (MIDletStateChangeException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
