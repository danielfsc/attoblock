package com.ardublock;

import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.ardublock.core.Context;
import com.ardublock.ui.ConsoleFrame;
import com.ardublock.ui.OpenblocksFrame;

public class Main
{
	private OpenblocksFrame openblocksFrame;

	public static void main(String args[]) throws SAXException, IOException, ParserConfigurationException
	{
		Main me = new Main();
		me.startArdublock();
	}
	
	public void startArdublock() throws SAXException, IOException, ParserConfigurationException
	{
		startOpenblocksFrame();
		//startConsoleFrame();
	}
	
	private void startOpenblocksFrame() throws SAXException, IOException, ParserConfigurationException
	{
		openblocksFrame = new OpenblocksFrame();
		openblocksFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Context context = Context.getContext();
		context.setInArduino(false);
		openblocksFrame.setVisible(true);
	}

	public void shutdown()
	{
		openblocksFrame.dispatchEvent(new WindowEvent(openblocksFrame, WindowEvent.WINDOW_CLOSING));
	}
	
	@SuppressWarnings("unused")
	private void startConsoleFrame()
	{
		ConsoleFrame consoleFrame = new ConsoleFrame();
		consoleFrame.setVisible(false);
	}
}
