package com.sms.view;
import org.jfree.chart.ChartFactory;




import javax.swing.JFrame;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFrame;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShopAnalysisInterFrm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2664044170424332345L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtdec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopAnalysisInterFrm frame = new ShopAnalysisInterFrm();
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
	public ShopAnalysisInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("店铺经营情况");
		setBounds(100, 100, 751, 500);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u6700\u8FD1\u4E00\u5929\u6536\u652F\u60C5\u51B5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 353, 119);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("收入：");
		label.setBounds(22, 34, 54, 15);
		panel.add(label);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setText("1268");
		textField.setBounds(86, 32, 78, 18);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("支出：");
		label_1.setBounds(174, 34, 54, 15);
		panel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText("1200");
		textField_1.setColumns(10);
		textField_1.setBounds(257, 31, 78, 18);
		panel.add(textField_1);
		
		JLabel label_2 = new JLabel("出售商品数：");
		label_2.setBounds(174, 61, 85, 15);
		panel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setText("2");
		textField_2.setColumns(10);
		textField_2.setBounds(257, 59, 78, 18);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setText("1");
		textField_3.setColumns(10);
		textField_3.setBounds(86, 60, 78, 18);
		panel.add(textField_3);
		
		JLabel label_3 = new JLabel("订单数：");
		label_3.setBounds(22, 62, 54, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("日期");
		label_4.setBounds(205, 86, 54, 15);
		panel.add(label_4);
		
		txtdec = new JTextField();
		txtdec.setEditable(false);
		txtdec.setText("26-DEC-18");
		txtdec.setBounds(236, 86, 99, 18);
		panel.add(txtdec);
		txtdec.setColumns(10);
		 
	  
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u7ECF\u8425\u60C5\u51B5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 139, 353, 321);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		 
		JButton button = new JButton("显示饼图");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 new JFreeChart_pie();
			}
		});
		button.setBounds(28, 288, 134, 23);
		panel_1.add(button);
		
		
		JButton button_1 = new JButton("显示折线图");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 new JFreeChart_line();
			}
		});
		button_1.setBounds(192, 288, 134, 23);
		panel_1.add(button_1);
		
		
     
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u70ED\u5356\u5546\u54C1\u5206\u6790", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(373, 10, 353, 450);
		getContentPane().add(panel_2);

	}
}
