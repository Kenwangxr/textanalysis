package com.wangxr.textanlysis.ui;


import com.wangxr.textanlysis.ClientController;
import com.wangxr.textanlysis.entity.DataEntity;
import com.wangxr.textanlysis.util.TextAnalysisUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

/**
 * 程序主界面
 */
public class MainFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5289098005322195148L;
	ClientController clientController;
	Vector description = new Vector(Arrays.asList("字", "频率"));
	JTable table = new JTable(new Vector(new ArrayList()), description);


	JButton btnSelect=new JButton("选择文件");
	JButton btnCompute=new JButton("计算");
	JButton btnOut=new JButton("退出");
	JLabel lableKey = new JLabel("字词:");
	JTextField texKey = new JTextField(4);
	JLabel labelCount = new JLabel();
	JTextField texf=new JTextField(8);
	Panel p1=new Panel();
	Panel p2=new Panel();
	JFileChooser jFileChooser = new JFileChooser();
	JScrollPane scrollpane;
	public MainFrame()
	{
		super("词频统计");
		Container c=getContentPane();
		BorderLayout d=new BorderLayout();
		c.setLayout(d);
		table.setEnabled(false);
		scrollpane=new JScrollPane();
		scrollpane.setViewportView(table);
		jFileChooser.setCurrentDirectory(new File("C:\\"));
		btnSelect.addActionListener(this);
		btnCompute.addActionListener(this);
		btnOut.addActionListener(this);
		jFileChooser.addActionListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p1.add(texf);
		p1.add(btnSelect);
		p1.add(lableKey);
		p1.add(texKey);
		p1.add(btnCompute);
		p1.add(labelCount);
		p2.add(btnOut);

		c.add(scrollpane,BorderLayout.CENTER);
		c.add(p1,BorderLayout.NORTH);
		c.add(p2,BorderLayout.SOUTH);
		this.setSize(700,450);	
		this.setLocation(350,200);
		
	}

	/**
	 * 监听事件处理函数
	 */
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource()==btnSelect){

			 showFileChooser();

		 }
		else if(e.getSource()==btnCompute){
			 doCompute();
		 }
		else if(e.getSource()==btnOut) {//修改
			System.exit(0);
		 }
		 else if(e.getID() == 1001){
			 getFilePath();
		 }
			
			
		
	}

	/**
	 * 获取被选文件的路径，并显示到面板
	 */
	private void getFilePath() {
		String path = jFileChooser.getSelectedFile() == null? "" : jFileChooser.getSelectedFile().getAbsolutePath();
		texf.setText(path);
	}

	/**
	 * 执行数据计算，并显示结果
	 */
	private void doCompute() {
		String keyText = texKey.getText();
		String path = texf.getText();
		if(path == null || path.length()==0){
			JOptionPane.showMessageDialog(this, "请选择要处理的文件！", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		DataEntity dataEntity = new TextAnalysisUtil().getOccurrences(texf.getText(), keyText);
		labelCount.setText("出现次数："+dataEntity.getCount()+"");
		Map<String, Integer> cpMap = dataEntity.getCpMap();
		Iterator<String> iterator =cpMap.keySet().iterator();
		Vector vector = new Vector();
		while (iterator.hasNext()){
            Vector row = new Vector();
            String key = iterator.next();
            String val = cpMap.get(key).intValue() +"";
            row.add(key);
            row.add(val);
            vector.add(row);
        }
		table = new JTable(vector, description);
		table.setEnabled(false);
		scrollpane.setViewportView(table);
	}

	/**
	 * 显示文件选择器
	 */
	private void showFileChooser() {
		jFileChooser.setFileSelectionMode(0);
		int state = jFileChooser.showOpenDialog(null);
		if(state == 1){
            return;
        }else {
            String path = jFileChooser.getSelectedFile().getAbsolutePath();
            jFileChooser.setToolTipText(path);
        }
	}

	public void setClientController(ClientController clientController) {
		this.clientController = clientController;
	}            

}
