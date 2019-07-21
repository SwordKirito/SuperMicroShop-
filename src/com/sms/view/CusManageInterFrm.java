package com.sms.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sms.dao.CustomerDao;
import com.sms.dao.PhotoDao;
import com.sms.model.Customer;
import com.sms.util.DbUtil;
import com.sms.util.StringUtil;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class CusManageInterFrm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel m_picture_lable ;
	
	
	private JTextField s_ID_Text;
	private JTextField s_Username_Text;
	private JTable table;
	private JTextField m_ID_Text;
	private JTextField m_Username_Text;
	private JTextField m_Order_Num_Text;
	private JTextField m_Source_Text;
	private JTable m_Order_table;
	private JTable m_Source_table;
	private JTextField m_Power_text;
	DbUtil dbUtil = new DbUtil();
	PhotoDao photoDao = new PhotoDao();
	CustomerDao customerDao = new CustomerDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CusManageInterFrm frame = new CusManageInterFrm();
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
	public CusManageInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setToolTipText("");
		setTitle("客户管理面板");
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 377, 103);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("客户账号：");
		label.setBounds(26, 32, 80, 15);
		panel.add(label);
		
		s_ID_Text = new JTextField();
		s_ID_Text.setBounds(100, 29, 66, 21);
		panel.add(s_ID_Text);
		s_ID_Text.setColumns(10);
		
		JLabel label_1 = new JLabel("客户用户名：");
		label_1.setBounds(202, 32, 86, 15);
		panel.add(label_1);
		
		s_Username_Text = new JTextField();
		s_Username_Text.setBounds(282, 29, 66, 21);
		panel.add(s_Username_Text);
		s_Username_Text.setColumns(10);
		
		JButton button = new JButton("查找");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				customerSearchActionPerform(e);
				
				
				
				
			}
		});
		button.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\search_16px.png"));
		button.setBounds(142, 60, 93, 23);
		panel.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 123, 377, 304);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				customerTableMousePressed( e);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "ID", "\u7528\u6237\u540D", "\u8BA2\u5355\u6570", "\u6765\u6E90"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u5BA2\u6237\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(397, 10, 377, 450);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(25, 27, 141, 134);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		m_picture_lable = new JLabel("");
		m_picture_lable.setHorizontalAlignment(SwingConstants.CENTER);
		m_picture_lable.setBounds(0, 0, 140, 140);
		panel_2.add(m_picture_lable);
		m_picture_lable.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\employee_72px.net.png"));
		
		JLabel lblid = new JLabel("客户ID：");
		lblid.setBounds(186, 41, 54, 15);
		panel_1.add(lblid);
		
		m_ID_Text = new JTextField();
		m_ID_Text.setBounds(267, 38, 66, 21);
		panel_1.add(m_ID_Text);
		m_ID_Text.setColumns(10);
		
		JLabel label_2 = new JLabel("用户名：");
		label_2.setBounds(186, 79, 54, 15);
		panel_1.add(label_2);
		
		m_Username_Text = new JTextField();
		m_Username_Text.setBounds(267, 76, 66, 21);
		panel_1.add(m_Username_Text);
		m_Username_Text.setColumns(10);
		
		JLabel label_3 = new JLabel("订单数：");
		label_3.setBounds(186, 119, 54, 15);
		panel_1.add(label_3);
		
		m_Order_Num_Text = new JTextField();
		m_Order_Num_Text.setBounds(267, 116, 66, 21);
		panel_1.add(m_Order_Num_Text);
		m_Order_Num_Text.setColumns(10);
		
		JLabel label_4 = new JLabel("来源  ：");
		label_4.setBounds(186, 159, 54, 15);
		panel_1.add(label_4);
		
		m_Source_Text = new JTextField();
		m_Source_Text.setBounds(267, 156, 66, 21);
		panel_1.add(m_Source_Text);
		m_Source_Text.setColumns(10);
		
		JLabel label_5 = new JLabel("订单：");
		label_5.setBounds(10, 196, 54, 15);
		panel_1.add(label_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 226, 171, 174);
		panel_1.add(scrollPane_1);
		
		m_Order_table = new JTable();
		m_Order_table.setModel(new DefaultTableModel(
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
		scrollPane_1.setViewportView(m_Order_table);
		
		JLabel label_6 = new JLabel("影响力：");
		label_6.setBounds(186, 196, 54, 15);
		panel_1.add(label_6);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(186, 226, 181, 174);
		panel_1.add(scrollPane_2);
		
		m_Source_table = new JTable();
		m_Source_table.setModel(new DefaultTableModel(
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
		scrollPane_2.setViewportView(m_Source_table);
		
		JButton button_1 = new JButton("消费分析");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Analytics_16px.png"));
		button_1.setBounds(40, 410, 110, 23);
		panel_1.add(button_1);
		
		JButton button_2 = new JButton("上传头像");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				uploadPhoto();
				
				
				
				
			}
		});
		button_2.setHorizontalAlignment(SwingConstants.LEFT);
		button_2.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Upload_16px.png"));
		button_2.setBounds(40, 173, 110, 23);
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("修改");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				customerUpdateActionEvent(e);
				
				
				
				
				
			}
		});
		button_3.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\modify_16px.png"));
		button_3.setBounds(186, 410, 80, 23);
		panel_1.add(button_3);
		
		JButton button_4 = new JButton("删除");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				

				customerDeleteActionEvent(e);
				
				
				
				
			}
		});
		button_4.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\delete_16px.png"));
		button_4.setBounds(287, 410, 80, 23);
		panel_1.add(button_4);
		
		m_Power_text = new JTextField();
		m_Power_text.setBounds(267, 193, 66, 21);
		panel_1.add(m_Power_text);
		m_Power_text.setColumns(10);
		
		JButton button_5 = new JButton("按照客户影响力排序");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				
			}
		});
		button_5.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\sort_13.105263157895px.png"));
		button_5.setBounds(95, 437, 205, 23);
		getContentPane().add(button_5);
		
		fillTable(new Customer());

	}
	
	
	
	
/*	
	private JLabel m_picture_lable ;
	
	
	private JTextField s_ID_Text;
	private JTextField s_Username_Text;
	private JTable table;
	private JTextField m_ID_Text;
	private JTextField m_Username_Text;
	private JTextField m_Order_Num_Text;
	private JTextField m_Source_Text;
	private JTable m_Order_table;
	private JTable m_Source_table;
	private JTextField m_Power_text;*/
	

	protected void customerSearchActionPerform(ActionEvent e) {
		// TODO Auto-generated method stub
		String s_ID_Text = this.s_ID_Text.getText();
		String s_Username_Text = this.s_Username_Text.getText();
		Customer customer = new Customer();
		if(StringUtil.isNotEmpty(s_ID_Text)) {
			customer.setCus_id(Integer.parseInt(s_ID_Text));
		}
		customer.setCus_username(s_Username_Text);
		this.fillTable(customer);
	}

	protected void customerTableMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		Connection con=null;
		
		try {
			con=dbUtil.getCon();

			int row=table.getSelectedRow();
			
			String id = (String)table.getValueAt(row, 1);
			
			Customer customer = customerDao.searchByID(con, id);
		/*	for(int i=0;i<999;i++) {
				System.out.println("查找结束");
			}*/
			
		/*	    
				private JLabel 			m_picture_lable ;
				private JTextField 		m_ID_Text;
				private JTextField 		m_Username_Text;
				private JTextField 		m_Order_Num_Text;
				private JTextField 		m_Source_Text;
				private JTable 			m_Order_table;
				private JTable 			m_Source_table;
				private JTextField 		m_Power_text;			*/
			
			m_picture_lable.setIcon(customer.getCus_picture());
			m_ID_Text.setText(""+customer.getCus_id());
			m_Username_Text.setText(customer.getCus_username());
			m_Order_Num_Text.setText(""+customer.getCus_order_num());
			m_Source_Text.setText(""+customer.getCus_source());
			m_Power_text.setText(""+customerDao.checkPower(con, id));
			
			fillOrderTable(customer);
			fillPowerTable(customer);
			

			
			
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
	}
	
	

	private void fillOrderTable(Customer customer){
		
		DefaultTableModel dtm=(DefaultTableModel) m_Order_table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = customerDao.listForOrder(con, customer);
			//int i=1;
			while(rs.next()){
				Vector<String> v=new Vector<String>();
				//v.add(""+ i++);
				v.add(""+rs.getInt("order_id"));
				v.add(""+rs.getInt("order_total"));
				v.add(""+rs.getDate("order_btime"));
				
				dtm.addRow(v);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

private void fillPowerTable(Customer customer){
		
		DefaultTableModel dtm=(DefaultTableModel) m_Source_table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = customerDao.listForPower(con, customer);
			//int i=1;
			while(rs.next()){
				Vector<String> v=new Vector<String>();
				//v.add(""+ i++);
				v.add(""+rs.getInt("cus_id"));
				v.add(""+rs.getString("cus_username"));
				v.add(""+rs.getDate("cus_register_time"));
				
				dtm.addRow(v);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	

	private void fillTable(Customer customer){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = customerDao.list(con, customer);
			int i=1;
			while(rs.next()){
				Vector<String> v=new Vector<String>();
				v.add(""+ i++);
				v.add(""+rs.getInt("cus_id"));
				v.add(rs.getString("cus_username"));
				v.add(""+rs.getInt("cus_order_num"));
				v.add(""+rs.getInt("cus_source"));
				dtm.addRow(v);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	
	/*	    
	private JLabel 			m_picture_lable ;
	private JTextField 		m_ID_Text;
	private JTextField 		m_Username_Text;
	private JTextField 		m_Order_Num_Text;
	private JTextField 		m_Source_Text;
	private JTable 			m_Order_table;
	private JTable 			m_Source_table;
	private JTextField 		m_Power_text;			*/
	/**
	 * 宠物类别修改事件处理
	 * @param e
	 */
	private void customerUpdateActionEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		
	//	String cus_address = null;
		int cus_id = -1 ;
		int cus_order_num  = -1 ;
		ImageIcon cus_picture = null;
	//	String cus_pwd = null;
	//	Date cus_register_time = null;
	//	Long cus_telephone = null;
		String cus_username = null;

		
		
		//cus_address=rs.getString("cus_address");
		//cus_pwd=rs.getString("cus_pwd");
		//cus_register_time = rs.getDate("cus_register_time");
		//cus_telephone = rs .getLong("cus_telephone");
		
		
		
		String idstr=m_ID_Text.getText();
		cus_id=Integer.parseInt(idstr);
		cus_order_num=Integer.parseInt(m_Order_Num_Text.getText());
		cus_picture=(ImageIcon) m_picture_lable.getIcon();
		

		
		
		
		cus_username = m_Username_Text.getText();
		
		
		if(StringUtil.isEmpty(idstr)){
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return;
		}
		if(StringUtil.isEmpty(cus_username)){
			JOptionPane.showMessageDialog(null, "用户名不能为空");
			return;
		}
		//Customer customer = new Customer(class_id,class_name,class_remark);
		Connection con = null;
		try{
			con=dbUtil.getCon();
			
			Customer customer ;
			
			customer=customerDao.searchByID(con, idstr);
			customer.setCus_id(cus_id);
			customer.setCus_order_num(cus_order_num);
			customer.setCus_picture(cus_picture);
			customer.setCus_username(cus_username);
			
			
			
			
			int modifyNum=customerDao.update(con, customer);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetValue();
				this.fillTable(new Customer());
				this.fillOrderTable(customer);
				this.fillPowerTable(customer);
			}else{
				JOptionPane.showMessageDialog(null, "修改失败1111111111");
			}
		}catch (Exception e2) {
			e2.printStackTrace();

			JOptionPane.showMessageDialog(null, "修改失败222222222222");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	/*	    
	private JLabel 			m_picture_lable ;
	private JTextField 		m_ID_Text;
	private JTextField 		m_Username_Text;
	private JTextField 		m_Order_Num_Text;
	private JTextField 		m_Source_Text;
	private JTable 			m_Order_table;
	private JTable 			m_Source_table;
	private JTextField 		m_Power_text;			*/
	/**
	 * 图书类别删除事件处理
	 * @param e
	 */
	private void customerDeleteActionEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		String idstr=m_ID_Text.getText();
		if(StringUtil.isEmpty(idstr)){
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if(n==0){
			Connection con=null;
			try {
				con=dbUtil.getCon();
				int deleteNum=customerDao.delete(con,idstr);
				if(deleteNum==1){
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillTable(new Customer());
					this.fillOrderTable(new Customer());
					this.fillPowerTable(new Customer());
					
				}else{
					JOptionPane.showMessageDialog(null, "删除失败");
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

				JOptionPane.showMessageDialog(null, "删除失败");
			}finally{
				try{
					dbUtil.closeCon(con);
				}catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}
	/*	    
	private JLabel 			m_picture_lable ;
	private JTextField 		m_ID_Text;
	private JTextField 		m_Username_Text;
	private JTextField 		m_Order_Num_Text;
	private JTextField 		m_Source_Text;
	private JTable 			m_Order_table;
	private JTable 			m_Source_table;
	private JTextField 		m_Power_text;			*/

	private void resetValue() {
		// TODO Auto-generated method stub
		this.m_picture_lable.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\employee_72px.net.png"));
		this.m_ID_Text.setText("");
		this.m_Username_Text.setText("");
		this.m_Order_Num_Text.setText("");
		this.m_Source_Text.setText("");
		this.m_Power_text.setText("");
		this.fillOrderTable(new Customer());
		this.fillPowerTable(new Customer());
		this.fillTable(new Customer());
	}
	
	
	private void uploadPhoto() {
		ByteArrayOutputStream baos = null;
			
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JFileChooser f = new JFileChooser(); // 查找文件
		    f.showOpenDialog(null);
		   

			File file = new File(""+f.getSelectedFile());
			/*try {
				f = changeSize(100,100,f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			
			InputStream is;
			try {
				is = new FileInputStream(file);
				baos = new ByteArrayOutputStream();
				int b = 0;
				try {
					while ((b = is.read()) != -1) {
						baos.write(b);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			// 将数据流转为Icon数据
			ImageIcon IC = new ImageIcon(baos.toByteArray());
			m_picture_lable.setIcon(IC);
			
			
		
	}
	
	
}
