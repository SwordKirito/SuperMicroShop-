package com.sms.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sms.dao.CustomerDao;
import com.sms.dao.GoodDao;
import com.sms.dao.OrderDao;
import com.sms.model.Good;
import com.sms.model.Order;
import com.sms.util.DbUtil;
import com.sms.util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;

public class OrderCheckInterFrm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField s_id_text;
	private JTextField s_cus_id_text;
	private JTextField s_btime_text;
	private JTextField m_id_text;
	private JTextField m_cus_id_text;
	private JTextField m_cus_name_text;
	private JTextField m_total_text;
	private JTextField m_btime_text;
	private JTextField m_ftime_text;
	private JTextField m_state_text;
	
	private JComboBox<Good> s_good_jcb;
	
	
	private JTable table;//左边，查询订单结果
	private JTable table_1;//右边，订单商品
	
	
	
	DbUtil dbUtil = new DbUtil();
	OrderDao orderDao = new OrderDao();
	CustomerDao customerDao = new CustomerDao();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderCheckInterFrm frame = new OrderCheckInterFrm();
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
	public OrderCheckInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("查看订单");
		setBounds(100, 100, 800, 489);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "搜索条件", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 375, 171);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_5 = new JLabel("\u8BA2\u5355\u53F7\uFF1A");
		label_5.setBounds(14, 27, 72, 18);
		panel.add(label_5);
		
		s_id_text = new JTextField();
		s_id_text.setBounds(89, 24, 86, 24);
		panel.add(s_id_text);
		s_id_text.setColumns(10);
		
		JLabel lblid_1 = new JLabel("\u8BA2\u5355\u5BA2\u6237ID\uFF1A");
		lblid_1.setBounds(198, 29, 95, 18);
		panel.add(lblid_1);
		
		s_cus_id_text = new JTextField();
		s_cus_id_text.setBounds(274, 24, 86, 24);
		panel.add(s_cus_id_text);
		s_cus_id_text.setColumns(10);
		
		JLabel label_7 = new JLabel("\u8BA2\u5355\u65F6\u95F4\uFF1A");
		label_7.setBounds(14, 58, 95, 18);
		panel.add(label_7);
		
		s_btime_text = new JTextField();
		s_btime_text.setBounds(89, 55, 86, 24);
		panel.add(s_btime_text);
		s_btime_text.setColumns(10);
		

		
		JLabel label_8 = new JLabel("\u8BA2\u5355\u5546\u54C1\uFF1A");
		label_8.setBounds(198, 58, 86, 18);
		panel.add(label_8);
		
		s_good_jcb = new JComboBox<Good>();
		s_good_jcb.setModel(new DefaultComboBoxModel<Good>());
		s_good_jcb.setBounds(274, 57, 86, 19);
		panel.add(s_good_jcb);
		
		JLabel label_9 = new JLabel("\u8BA2\u5355\u72B6\u6001\uFF1A");
		label_9.setBounds(14, 86, 95, 18);
		panel.add(label_9);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				orderSearchActionPerform(e);
				
				
				
				
				
				
			}
		});
		button.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\search_16px.png"));
		button.setBounds(125, 137, 103, 24);
		panel.add(button);
		
		JRadioButton checkBox = new JRadioButton("\u672A\u786E\u8BA4");
		buttonGroup.add(checkBox);
		checkBox.setBounds(89, 84, 103, 23);
		panel.add(checkBox);
		
		JRadioButton checkBox_1 = new JRadioButton("\u5DF2\u786E\u8BA4\u3001\u672A\u5B8C\u6210");
		buttonGroup.add(checkBox_1);
		checkBox_1.setBounds(208, 84, 131, 23);
		panel.add(checkBox_1);
		
		JRadioButton checkBox_2 = new JRadioButton("\u5DF2\u5B8C\u6210");
		buttonGroup.add(checkBox_2);
		checkBox_2.setBounds(89, 110, 103, 23);
		panel.add(checkBox_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(395, 10, 379, 439);
		getContentPane().add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "订单详情", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("\u8BA2\u5355\u53F7\uFF1A");
		label.setBounds(31, 40, 72, 18);
		panel_1.add(label);
		
		JButton btnNewButton_1 = new JButton("删除");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				
				
				orderDeleteActionEvent(e);
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\delete_16px.png"));
		btnNewButton_1.setBounds(132, 396, 113, 27);
		panel_1.add(btnNewButton_1);
		
		m_id_text = new JTextField();
		m_id_text.setBounds(113, 40, 66, 21);
		panel_1.add(m_id_text);
		m_id_text.setColumns(10);
		
		JLabel lblid = new JLabel("\u4E0B\u5355\u5BA2\u6237ID\uFF1A");
		lblid.setBounds(31, 70, 72, 18);
		panel_1.add(lblid);
		
		m_cus_id_text = new JTextField();
		m_cus_id_text.setColumns(10);
		m_cus_id_text.setBounds(113, 70, 66, 21);
		panel_1.add(m_cus_id_text);
		
		JLabel label_2 = new JLabel("\u4E0B\u5355\u5BA2\u6237\u6635\u79F0\uFF1A");
		label_2.setBounds(203, 70, 90, 18);
		panel_1.add(label_2);
		
		m_cus_name_text = new JTextField();
		m_cus_name_text.setColumns(10);
		m_cus_name_text.setBounds(285, 70, 66, 21);
		panel_1.add(m_cus_name_text);
		
		JLabel label_10 = new JLabel("\u8BA2\u5355\u603B\u4EF7\uFF1A");
		label_10.setBounds(31, 130, 90, 18);
		panel_1.add(label_10);
		
		m_total_text = new JTextField();
		m_total_text.setColumns(10);
		m_total_text.setBounds(113, 130, 66, 21);
		panel_1.add(m_total_text);
		
		JLabel label_1 = new JLabel("\u4E0B\u5355\u65F6\u95F4\uFF1A");
		label_1.setBounds(31, 100, 72, 18);
		panel_1.add(label_1);
		
		m_btime_text = new JTextField();
		m_btime_text.setColumns(10);
		m_btime_text.setBounds(113, 100, 66, 21);
		panel_1.add(m_btime_text);
		
		JLabel label_3 = new JLabel("\u8BA2\u5355\u7ED3\u675F\u65F6\u95F4\uFF1A");
		label_3.setBounds(203, 100, 90, 18);
		panel_1.add(label_3);
		
		m_ftime_text = new JTextField();
		m_ftime_text.setColumns(10);
		m_ftime_text.setBounds(285, 100, 66, 21);
		panel_1.add(m_ftime_text);
		
		JLabel label_4 = new JLabel("\u8BA2\u5355\u72B6\u6001\uFF1A");
		label_4.setBounds(203, 40, 90, 18);
		panel_1.add(label_4);
		
		m_state_text = new JTextField();
		m_state_text.setColumns(10);
		m_state_text.setBounds(285, 40, 66, 21);
		panel_1.add(m_state_text);
		
		JLabel label_11 = new JLabel("\u8BA2\u5355\u5546\u54C1\uFF1A");
		label_11.setBounds(31, 160, 72, 15);
		panel_1.add(label_11);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(48, 185, 304, 201);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "\u5546\u54C1\u540D", "\u5355\u4EF7", "\u6570\u91CF"
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 191, 370, 256);
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
				"\u8BA2\u5355\u53F7", "\u4E0B\u5355\u7528\u6237", "\u4E0B\u5355\u65F6", "\u7ED3\u675F\u65F6\u95F4"
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
		scrollPane.setViewportView(table);
		fillOrderTableForInit(new Order());
		fillGoodJCB();
	}
	
	
	
	
	
	
	


	
	
	protected void orderSearchActionPerform(ActionEvent e) {
		
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
		String s_id_text = this.s_id_text.getText();
		String s_cus_id_text = this.s_cus_id_text.getText();
		String s_btime_text = this.s_btime_text.getText();
		System.out.println(s_btime_text);
		Good good = (Good)this.s_good_jcb.getSelectedItem();
		Order order = new Order();
		if(StringUtil.isNotEmpty(s_btime_text)) {
			try {
	        	date=simpleDateFormat.parse(s_btime_text);
	            order.setOrder_btime(date);
	        } catch(ParseException px) {
	            px.printStackTrace();
	        }	
		}
		if(StringUtil.isNotEmpty(s_cus_id_text)) {
			order.setCus_id(Integer.parseInt(s_cus_id_text));
		}
		if(StringUtil.isNotEmpty(s_id_text)) {
			order.setOrder_id(Integer.parseInt(s_id_text));
		}
		/*String dateString = simpleDateFormat.format(order.getOrder_btime());
        System.out.println(dateString);*/
		this.fillOrderTable(order,good);
	}

	protected void orderTableMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		Connection con=null;
		
		try {
			con=dbUtil.getCon();

			int row=table.getSelectedRow();
			
			String id = (String)table.getValueAt(row, 0);
			
			Order order = orderDao.searchByID(con,id);
			String stateStr = "";
			
			if(order.getOrder_confirm()==0) {
				stateStr+="未确认";
			}else if(order.getOrder_confirm()==1){
				if(order.getOrder_complete()==0) {
					stateStr+="已确认，未完成";
				}
				else {
					stateStr+="已完成";
				}
			}else if(order.getOrder_confirm()==-1){
				
				stateStr+="已取消";
				
			}
			
			
			
			m_id_text.setText(""+order.getOrder_id());
			m_cus_id_text.setText(""+order.getCus_id());
			m_cus_name_text.setText(customerDao.searchByID(con, ""+order.getCus_id()).getCus_username());
			m_total_text.setText(""+orderDao.getOrderTotal(con, order.getOrder_id()));
			m_btime_text.setText(""+order.getOrder_btime());
			m_ftime_text.setText(""+order.getOrder_ftime());
			m_state_text.setText(""+stateStr);

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
	

	private void fillOrderTable(Order order, Good good){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = orderDao.list(con, order, good);
			//int i=1;
			while(rs.next()){
				Vector<String> v=new Vector<String>();
				//v.add(""+ i++);
				v.add(""+rs.getInt("order_id"));
				v.add(rs.getString("cus_username"));
				v.add(""+rs.getDate("order_btime"));
				v.add(""+rs.getDate("order_ftime"));
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
	
	private void fillOrderTable(Order order){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = orderDao.list(con, order);
			//int i=1;
			while(rs.next()){
				Vector<String> v=new Vector<String>();
				//v.add(""+ i++);
				v.add(""+rs.getInt("order_id"));
				v.add(""+rs.getInt("cus_id"));
				v.add(""+rs.getDate("order_btime"));
				v.add(""+rs.getDate("order_ftime"));
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
	private void fillOrderTableForInit(Order order){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = orderDao.listForInit(con, order);
			//int i=1;
			while(rs.next()){
				Vector<String> v=new Vector<String>();
				//v.add(""+ i++);
				v.add(""+rs.getInt("order_id"));
				v.add(""+rs.getString("cus_username"));
				v.add(""+rs.getDate("order_btime"));
				v.add(""+rs.getDate("order_ftime"));
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
			//int i=1;
			while(rs.next()){
				Vector<String> v=new Vector<String>();
				//v.add(""+ i++);
				v.add(""+rs.getInt("good_id"));
				v.add(rs.getString("good_name"));
				v.add(""+rs.getInt("good_price"));
				v.add(""+rs.getInt("good_num"));
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


	/**
	 * 图书类别删除事件处理
	 * @param e
	 */
	
	
	
	private void orderDeleteActionEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		String idstr=m_id_text.getText();
		if(StringUtil.isEmpty(idstr)){
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if(n==0){
			Connection con=null;
			try {
				con=dbUtil.getCon();
				int deleteNum=orderDao.delete(con,idstr);
				if(deleteNum==1){
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillOrderTable(new Order());
					
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
/*	private JTextField s_id_text;
	private JTextField s_cus_id_text;
	private JTextField s_btime_text;
	private JTextField m_id_text;
	private JTextField m_cus_id_text;
	private JTextField m_cus_name_text;
	private JTextField m_total_text;
	private JTextField m_btime_text;
	private JTextField m_ftime_text;
	private JTextField m_state_text;*/
	
	private void resetValue() {
		// TODO Auto-generated method stub
		
		this.m_id_text.setText("");
		this.m_cus_id_text.setText("");
		this.m_cus_name_text.setText("");
		this.m_total_text.setText("");
		this.m_btime_text.setText("");
		this.m_ftime_text.setText("");
		this.m_state_text.setText("");
		
		this.fillOrderTableForInit(new Order());
		this.fillOrderTable(new Order());
	}
	
	
	
	
	private void fillGoodJCB(){
		Connection con = null;
		Good good = new Good();
		GoodDao goodDao = new GoodDao();
		try{
			con = dbUtil.getCon();
			ResultSet rs = goodDao.list(con, new Good());

	    	good = new Good();
	    	good.setGood_id(-1);
	    	good.setGood_name("请选择");
	    	this.s_good_jcb.addItem(good);
			
		    while( rs.next() ){
		    	good = new Good();
		    	good.setGood_id(rs.getInt("good_id"));
		    	good.setGood_name(rs.getString("good_name"));
		    	good.setGood_descrip(rs.getString("good_descrip"));
		    	good.setGood_cost(rs.getInt("good_cost"));
		    	good.setGood_price(rs.getInt("good_price"));
		    	good.setGood_place(rs.getString("good_place"));
		    	//good.setGood_picture(rs.getBinaryStream("good_picture"));
		    	good.setGood_hot(rs.getInt("good_hot"));
		    	this.s_good_jcb.addItem(good);
		     }
		 }catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	
	
	
}
