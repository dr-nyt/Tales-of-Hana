package me.drnyt.talesofhana;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Launcher {

		public static void main(String[] args)
		{
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) screenSize.getWidth();
			int height = (int) screenSize.getHeight();
			
			Game game = new Game("HaNa", width, height);
			game.start();
		}
}
