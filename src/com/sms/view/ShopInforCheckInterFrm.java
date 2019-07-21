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
import javax.swing.JTextArea;

public class ShopInforCheckInterFrm extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_11;
	private JTextField textField_14;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopInforCheckInterFrm frame = new ShopInforCheckInterFrm();
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
	public ShopInforCheckInterFrm() {
		setTitle("店铺信息查看（员工端）");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 457, 395);
		getContentPane().setLayout(null);

		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u8BE6\u7EC6\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 10, 421, 347);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(211, 36, 58, 15);
		panel_2.add(lblId);
		
		JLabel label_5 = new JLabel("名称：");
		label_5.setBounds(211, 66, 58, 15);
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("地址：");
		label_6.setBounds(211, 96, 58, 15);
		panel_2.add(label_6);
		
		JLabel label_7 = new JLabel("标语：");
		label_7.setBounds(211, 158, 58, 15);
		panel_2.add(label_7);
		
		JLabel label_8 = new JLabel("员工数量：");
		label_8.setBounds(211, 229, 76, 15);
		panel_2.add(label_8);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setText("0");
		textField_7.setBounds(267, 33, 129, 21);
		panel_2.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setText("逆风小铺");
		textField_8.setBounds(267, 65, 129, 21);
		panel_2.add(textField_8);
		textField_8.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setText("4");
		textField_11.setBounds(267, 226, 129, 21);
		panel_2.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("员工列表：");
		lblNewLabel.setBounds(211, 259, 76, 15);
		panel_2.add(lblNewLabel);
		
		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\LOGO_160x160.png"));
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(42, 33, 140, 140);
		panel_2.add(label_9);
		
		JLabel lblid = new JLabel("老板ID：");
		lblid.setBounds(20, 230, 76, 15);
		panel_2.add(lblid);
		
		textField_14 = new JTextField();
		textField_14.setEditable(false);
		textField_14.setText("0");
		textField_14.setBounds(94, 227, 97, 21);
		panel_2.add(textField_14);
		textField_14.setColumns(10);
		
		JLabel label = new JLabel("老板名称：");
		label.setBounds(20, 259, 76, 15);
		panel_2.add(label);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setText("楚程翔");
		textField.setColumns(10);
		textField.setBounds(94, 256, 97, 21);
		panel_2.add(textField);
		
		JLabel label_1 = new JLabel("老板电话：");
		label_1.setBounds(20, 287, 76, 15);
		panel_2.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText("18844097095");
		textField_1.setColumns(10);
		textField_1.setBounds(94, 284, 98, 21);
		panel_2.add(textField_1);
		
		JLabel lblqq = new JLabel("老板QQ：");
		lblqq.setBounds(20, 315, 76, 15);
		panel_2.add(lblqq);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setText("1215502776");
		textField_2.setColumns(10);
		textField_2.setBounds(95, 312, 97, 21);
		panel_2.add(textField_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(267, 96, 129, 52);
		panel_2.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setText("黑龙江省大庆市龙凤区厂西");
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(267, 158, 129, 58);
		panel_2.add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setLineWrap(true);
		textArea_1.setText("来逆风小铺买东西，物美价廉，方便实惠！");
		scrollPane_1.setViewportView(textArea_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(267, 254, 129, 79);
		panel_2.add(scrollPane_2);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"0", "\u695A\u7A0B\u7FD4"},
				{"1", "\u5F20\u4E00\u51E1"},
				{"2", "\u5F90\u4EAE"},
				{"3", "\u5F20\u9A70\u9A8B"},
			},
			new String[] {
				"ID", "\u59D3\u540D"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_2.setViewportView(table);

	}
}
