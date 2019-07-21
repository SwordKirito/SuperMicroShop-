package com.sms.view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class CusInformationInterFrm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table_1;
	private JTable table_2;
	private JTextField textField_6;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CusInformationInterFrm frame = new CusInformationInterFrm();
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
	public CusInformationInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\employee_16px.png"));
		setTitle("客户详细信息");
		setBounds(100, 100, 413, 500);
		getContentPane().setLayout(null);

		
		
		
		
		

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u5BA2\u6237\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 10, 377, 450);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(25, 27, 141, 134);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 140, 140);
		panel_2.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\employee_72px.net.png"));
		
		JLabel lblid = new JLabel("客户ID：");
		lblid.setBounds(186, 41, 54, 15);
		panel_1.add(lblid);
		
		textField_2 = new JTextField();
		textField_2.setBounds(267, 38, 66, 21);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_2 = new JLabel("用户名：");
		label_2.setBounds(186, 79, 54, 15);
		panel_1.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(267, 76, 66, 21);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label_3 = new JLabel("订单数：");
		label_3.setBounds(186, 119, 54, 15);
		panel_1.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(267, 116, 66, 21);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel label_4 = new JLabel("来源  ：");
		label_4.setBounds(186, 159, 54, 15);
		panel_1.add(label_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(267, 156, 66, 21);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel label_5 = new JLabel("订单：");
		label_5.setBounds(10, 196, 54, 15);
		panel_1.add(label_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 226, 171, 174);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u603B\u989D", "\u65E5\u671F"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JLabel label_6 = new JLabel("影响力：");
		label_6.setBounds(186, 196, 54, 15);
		panel_1.add(label_6);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(186, 226, 181, 174);
		panel_1.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "\u7528\u6237\u540D", "\u6CE8\u518C\u65F6\u95F4"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_2.setViewportView(table_2);
		
		JButton button_1 = new JButton("消费分析");
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Analytics_16px.png"));
		button_1.setBounds(40, 410, 110, 23);
		panel_1.add(button_1);
		
		JButton button_2 = new JButton("上传头像");
		button_2.setHorizontalAlignment(SwingConstants.LEFT);
		button_2.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Upload_16px.png"));
		button_2.setBounds(40, 173, 110, 23);
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("修改");
		button_3.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\modify_16px.png"));
		button_3.setBounds(186, 410, 80, 23);
		panel_1.add(button_3);
		
		JButton button_4 = new JButton("删除");
		button_4.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\delete_16px.png"));
		button_4.setBounds(287, 410, 80, 23);
		panel_1.add(button_4);
		
		textField_6 = new JTextField();
		textField_6.setBounds(267, 193, 66, 21);
		panel_1.add(textField_6);
		textField_6.setColumns(10);

	}

	
	
	
	
	
}
