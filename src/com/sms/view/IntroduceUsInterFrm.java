package com.sms.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import java.awt.Color;

public class IntroduceUsInterFrm extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntroduceUsInterFrm frame = new IntroduceUsInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IntroduceUsInterFrm() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setClosable(true);
		setIconifiable(true);
		setTitle("关于超级微店管理系统v1.0");
		setBounds(100, 100, 450, 300);
		
		JLabel label = new JLabel("超级微店管理系统v1.0");
		label.setIcon(new ImageIcon("E:\\程序试做\\java课\\PetShop2.0\\image\\1143917.png"));
		label.setFont(new Font("宋体", Font.BOLD, 30));
		
		JLabel label_1 = new JLabel("客户端程序：");
		
		JTextPane txtpnmysql = new JTextPane();
		txtpnmysql.setBackground(Color.LIGHT_GRAY);
		txtpnmysql.setEditable(false);
		txtpnmysql.setText("①将宠物店的程序改造成图形界面系统，可以在界面中添加宠物，查询宠物，修改宠物，删除宠物。宠物信息保存到数据库中。\r\n②可以使用mysql作为数据库。         ");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(txtpnmysql, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_1))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addComponent(label)
					.addGap(27)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnmysql, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(51, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
