package com.wangxr.textanlysis;
import com.wangxr.textanlysis.ui.MainFrame;

/**
 * 程序入口
 */
public class MainStart {
	public static void main(String[] args){
	MainFrame mainFrame = new MainFrame();
	ClientController clientController = new ClientController();
	clientController.setMainFrame(mainFrame);
	mainFrame.setClientController(clientController);
	clientController.show();
	}

}
