package com.wangxr.textanlysis;

import com.wangxr.textanlysis.ui.MainFrame;

/**
 * 各界面上下文，用于界面之间数据传递和交替显示
 */
public class ClientController {

    MainFrame mainFrame;

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * 显示程序界面
     */
    public void show(){
        mainFrame.setVisible(true);
    }




}
