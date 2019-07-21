package com.sms.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sms.dao.CustomerDao;
import com.sms.dao.OrderDao;
import com.sms.model.Order;
import com.sms.util.DbUtil;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

public class OrderAffirmInterFrm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JTextField m_id_text;
	private JTextField m_cus_name_text;
	private JTextField m_date_text;
	private JTextField m_sum_text;
	private JTable table;
	private JTable table_1;
	
	
	
	private DbUtil dbUtil = new DbUtil();
	private OrderDao orderDao = new OrderDao();
	private CustomerDao customerDao = new CustomerDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderAffirmInterFrm frame = new OrderAffirmInterFrm();
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
	public OrderAffirmInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("确认订单");
		setBounds(100, 100, 801, 435);
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "订单详情", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(405, 13, 369, 385);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BA2\u5355\u53F7\uFF1A");
		lblNewLabel.setBounds(28, 39, 72, 18);
		panel_1.add(lblNewLabel);
		
		m_id_text = new JTextField();
		m_id_text.setBounds(87, 39, 86, 24);
		panel_1.add(m_id_text);
		m_id_text.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u4E0B\u5355\u5BA2\u6237\uFF1A");
		lblNewLabel_1.setBounds(201, 39, 93, 18);
		panel_1.add(lblNewLabel_1);
		
		m_cus_name_text = new JTextField();
		m_cus_name_text.setBounds(259, 37, 86, 24);
		panel_1.add(m_cus_name_text);
		m_cus_name_text.setColumns(10);
		
		JLabel label = new JLabel("\u4E0B\u5355\u65F6\u95F4\uFF1A");
		label.setBounds(28, 82, 86, 18);
		panel_1.add(label);
		
		m_date_text = new JTextField();
		m_date_text.setBounds(87, 82, 86, 24);
		panel_1.add(m_date_text);
		m_date_text.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8BA2\u5355\u603B\u4EF7\uFF1A");
		label_1.setBounds(201, 82, 93, 18);
		panel_1.add(label_1);
		
		m_sum_text = new JTextField();
		m_sum_text.setBounds(259, 80, 86, 24);
		panel_1.add(m_sum_text);
		m_sum_text.setColumns(10);
		
		JLabel label_3 = new JLabel("\u8BA2\u5355\u5546\u54C1\uFF1A");
		label_3.setBounds(10, 123, 126, 15);
		panel_1.add(label_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(28, 148, 331, 191);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "\u540D\u79F0", "\u5355\u4EF7", "\u6570\u91CF"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JButton button = new JButton("\u786E\u8BA4\u8BA2\u5355");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				try {
					orderConfirm( e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setIcon(new ImageIcon("E:\\\u7A0B\u5E8F\u8BD5\u505A\\\u8F6F\u4EF6\u5F00\u53D1\u7EFC\u5408\u5B9E\u8DF5\\SuperMicroShop\\image\\confirmation_16px.png"));
		button.setBounds(64, 349, 108, 23);
		panel_1.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88\u8BA2\u5355");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				try {
					orderCancel(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		button_1.setIcon(new ImageIcon("E:\\\u7A0B\u5E8F\u8BD5\u505A\\\u8F6F\u4EF6\u5F00\u53D1\u7EFC\u5408\u5B9E\u8DF5\\SuperMicroShop\\image\\delete_16px.png"));
		button_1.setHorizontalAlignment(SwingConstants.RIGHT);
		button_1.setBounds(202, 349, 108, 23);
		panel_1.add(button_1);
		
		JLabel label_2 = new JLabel("\u5F85\u786E\u8BA4\u8BA2\u5355\uFF1A");
		label_2.setBounds(10, 13, 177, 15);
		getContentPane().add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 385, 351);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				
				
				
				orderTableMousePressed(e);
				
				
				
				
	
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BA2\u5355\u53F7", "\u5BA2\u6237", "\u4E0B\u5355\u65F6\u95F4"
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
		scrollPane.setViewportView(table);

		this.fillGoodTable(new Order());
		this.fillOrderTable(new Order());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	private JTextField m_id_text;
	private JTextField m_order_name_text;
	private JTextField m_date_text;
	private JTextField m_sum_text;
	private JTable table; - 左边
	private JTable table_1;- 右边*/
	
	private void fillOrderTable(Order order){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = orderDao.list(con, order);

			while(rs.next()){
				Vector<String> v=new Vector<String>();
				v.add(""+rs.getInt("order_id"));
				v.add(customerDao.searchByID(con, ""+rs.getInt("order_id")).getCus_username());
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
	private void fillGoodTable(Order order){
		DefaultTableModel dtm=(DefaultTableModel) table_1.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = orderDao.listForOrdGood(con, order);
			if(rs!=null) {
				System.out.println("fillGoodTable rs不为空");
				
				
				while(rs.next()){
					Vector<String> v=new Vector<String>();
					v.add(""+rs.getInt("good_id"));
					v.add(rs.getString("good_name"));
					v.add(""+rs.getInt("good_price"));
					v.add(""+rs.getInt("good_num"));
					dtm.addRow(v);
				}
				
				
			}else {
				

				System.out.println("fillGoodTable rs为空");
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
	
	
	
public void orderConfirm(ActionEvent e) throws Exception {
		
		Connection con=null;
		
		con = dbUtil.getCon();
		
		String idstr = m_id_text.getText();

		int modifyNum = orderDao.setOrderConfirm(con, idstr);
		
		if(modifyNum==1){
			JOptionPane.showMessageDialog(null, "已确认");
			this.resetValue();
		}else{
			JOptionPane.showMessageDialog(null, "确认失败");
		}
		
		
	}
	public void orderCancel(ActionEvent e) throws Exception {
		
		Connection con=null;
		
		con = dbUtil.getCon();
		
		String idstr = m_id_text.getText();
	
		int modifyNum = orderDao.setOrderCancel(con, idstr);
		
		if(modifyNum==1){
			JOptionPane.showMessageDialog(null, "已取消");
			this.resetValue();
		}else{
			JOptionPane.showMessageDialog(null, "取消失败");
		}
		
		
	}

	private void resetValue() {
		// TODO Auto-generated method stub
	
		this.m_id_text.setText("");
		this.m_cus_name_text.setText("");
		this.m_date_text.setText("");
		this.m_sum_text.setText("");
		this.fillGoodTable(new Order());
		this.fillOrderTable(new Order());
	}
	
	
	
	
	
	protected void orderTableMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		Connection con=null;

		/*private JTextField m_id_text;
		private JTextField m_cus_name_text;
		private JTextField m_date_text;
		private JTextField m_sum_text;
		private JTable table;
		private JTable table_1;*/
		try {
			
			
			con=dbUtil.getCon();

			int row=table.getSelectedRow();
			
			String id = (String)table.getValueAt(row, 0);
			
			Order order = orderDao.searchByID(con,id);
			
			System.out.println(order);
		
			m_id_text.setText(""+order.getOrder_id());
			m_cus_name_text.setText(customerDao.searchByID(con, ""+order.getCus_id()).getCus_username());
			m_date_text.setText(""+order.getOrder_btime());
			m_sum_text.setText(""+orderDao.getOrderTotal(con, order.getOrder_id()));
			this.fillGoodTable(order);
	
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
	
	
	
	
	
	
	
}
