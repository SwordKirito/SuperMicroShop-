package com.sms.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.sms.dao.GoodDao;
import com.sms.model.Good;
import com.sms.util.DbUtil;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GoodSetHotInterFrm extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextField id_text;
	private JTextField name_text;
	private JTextField sell_text;
	private JTextField hot_text;
	private JTable table_class;
	private JTable table_cus;
	private JTable table_hot;
	private JTable table_not_hot;
	
	private JLabel picture_lable;
	DbUtil dbUtil = new DbUtil();
	GoodDao goodDao = new GoodDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodSetHotInterFrm frame = new GoodSetHotInterFrm();
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
	public GoodSetHotInterFrm() {
		setFrameIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\hot_16px.png"));
		setClosable(true);
		setIconifiable(true);
		setTitle("标记热门商品");
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("热门商品：");
		label.setBounds(10, 10, 85, 15);
		getContentPane().add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 377, 162);
		getContentPane().add(scrollPane);
		
		table_hot = new JTable();
		table_hot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				goodHotTableMousePressed(e);
				
				
				
				
				
			}
		});
		table_hot.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "\u540D\u79F0", "\u9500\u91CF"
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
		scrollPane.setViewportView(table_hot);

		
		
		
		
		
		

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u5546\u54C1\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(397, 10, 377, 450);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(25, 27, 141, 134);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		picture_lable = new JLabel("");
		picture_lable.setHorizontalAlignment(SwingConstants.CENTER);
		picture_lable.setBounds(0, 0, 140, 140);
		panel_2.add(picture_lable);
		picture_lable.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\hot_64px.png"));
		
		JLabel lblid = new JLabel("ID：");
		lblid.setBounds(186, 41, 54, 15);
		panel_1.add(lblid);
		
		id_text = new JTextField();
		id_text.setBounds(267, 38, 66, 21);
		panel_1.add(id_text);
		id_text.setColumns(10);
		
		JLabel label_2 = new JLabel("商品名称：");
		label_2.setBounds(186, 79, 80, 15);
		panel_1.add(label_2);
		
		name_text = new JTextField();
		name_text.setBounds(267, 76, 66, 21);
		panel_1.add(name_text);
		name_text.setColumns(10);
		
		JLabel label_3 = new JLabel("销量：");
		label_3.setBounds(186, 119, 54, 15);
		panel_1.add(label_3);
		
		sell_text = new JTextField();
		sell_text.setBounds(267, 116, 66, 21);
		panel_1.add(sell_text);
		sell_text.setColumns(10);
		
		JLabel label_4 = new JLabel("热门商品：");
		label_4.setBounds(186, 159, 80, 15);
		panel_1.add(label_4);
		
		hot_text = new JTextField();
		hot_text.setBounds(267, 156, 66, 21);
		panel_1.add(hot_text);
		hot_text.setColumns(10);
		
		JLabel label_5 = new JLabel("分类：");
		label_5.setBounds(10, 201, 54, 15);
		panel_1.add(label_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 226, 171, 174);
		panel_1.add(scrollPane_1);
		
		table_class = new JTable();
		table_class.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7C7B\u522B"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table_class);
		
		JLabel label_6 = new JLabel("购买客户：");
		label_6.setBounds(186, 201, 80, 15);
		panel_1.add(label_6);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(186, 226, 181, 174);
		panel_1.add(scrollPane_2);
		
		table_cus = new JTable();
		table_cus.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "\u7528\u6237\u540D", "\u8D2D\u4E70\u6570\u91CF"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_2.setViewportView(table_cus);
		
		JButton button_3 = new JButton("标记/取消标记");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				
				try {
					goodSetOrCancelHot(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		button_3.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\modify_16px.png"));
		button_3.setBounds(109, 410, 150, 23);
		panel_1.add(button_3);
		
		JLabel label_1 = new JLabel("非热门商品：");
		label_1.setBounds(10, 207, 102, 15);
		getContentPane().add(label_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 232, 377, 228);
		getContentPane().add(scrollPane_3);
		
		table_not_hot = new JTable();
		table_not_hot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				goodNotHotTableMousePressed(e);
				
				
				
			}
		});
		table_not_hot.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "\u540D\u79F0", "\u9500\u91CF"
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
		scrollPane_3.setViewportView(table_not_hot);
		


		fillNotHotTable();

		fillHotTable();
		
		
		
		
		
		
	}
	
	
	
	
/*	
	private JTextField id_text;
	private JTextField name_text;
	private JTextField sell_text;
	private JTextField hot_text;
	private JTable table_class;
	private JTable table_cus;
	private JTable table_hot;
	private JTable table_not_hot;
*/
	private void fillHotTable(){
		
		DefaultTableModel dtm=(DefaultTableModel) table_hot.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = goodDao.listForHot(con);
			//int i=1;
			while(rs.next()){
				Vector<String> v=new Vector<String>();
				//v.add(""+ i++);
				v.add(""+rs.getInt("good_id"));
				v.add(""+rs.getString("good_name"));
				v.add(""+goodDao.getGoodSellNum(con, rs.getInt("good_id")));
				
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

private void fillNotHotTable(){
	DefaultTableModel dtm=(DefaultTableModel) table_not_hot.getModel();
	dtm.setRowCount(0);//设置成0行
	Connection con=null;
	try{
		con=dbUtil.getCon();
		ResultSet rs = goodDao.listForNotHot(con);
		while(rs.next()){
			Vector<String> v=new Vector<String>();
			//v.add(""+ i++);
			v.add(""+rs.getInt("good_id"));
			v.add(""+rs.getString("good_name"));
			v.add(""+goodDao.getGoodSellNum(con, rs.getInt("good_id")));
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

private void fillClassTable(Good good){
		
		DefaultTableModel dtm=(DefaultTableModel) table_class.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = goodDao.getItsClass(con, good);
			//int i=1;
			if(rs==null) {
				
				System.out.println("fillClassTable 的 rs 为空");
				
			}else {
				System.out.println("fillClassTable 的 rs 不为空");
				while(rs.next()){
					Vector<String> v=new Vector<String>();
					//v.add(""+ i++);
					System.out.println(rs.getString("class_name"));
					v.add(""+rs.getString("class_name"));
					
					dtm.addRow(v);
				}
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
	

	private void fillCusTable(Good good){
		DefaultTableModel dtm=(DefaultTableModel) table_cus.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = goodDao.listForCustomer(con, good);
			if(rs!=null) {
				//System.out.println("fillCusTable 的 rs 不为空");
				while(rs.next()){
					Vector<String> v=new Vector<String>();
					v.add(""+rs.getInt("cus_id"));
					v.add(rs.getString("cus_username"));
					v.add(""+rs.getInt("sum(good_num)"));
					dtm.addRow(v);
				}
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
	
	
	private void resetValue() {
		// TODO Auto-generated method stub
		this.picture_lable.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\good.png"));
		this.id_text.setText("");
		this.name_text.setText("");
		this.sell_text.setText("");
		this.hot_text.setText("");
		this.fillHotTable();
		this.fillNotHotTable();
		this.fillClassTable(new Good());
		this.fillCusTable(new Good());
	}

	
	protected void goodHotTableMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int row=table_hot.getSelectedRow();
			String id = (String)table_hot.getValueAt(row, 0);
			System.out.println(id);
			Good good = goodDao.searchByID(con,id);
			System.out.println(good);
			fillCusTable(good);
			fillClassTable(good);
			picture_lable.setIcon(good.getGood_picture());
			id_text.setText(""+good.getGood_id());
			name_text.setText(good.getGood_name());
			sell_text.setText(""+goodDao.getGoodSellNum(con, good));
			hot_text.setText(""+good.getGood_hot());
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
	protected void goodNotHotTableMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
				Connection con=null;
				try {
					con=dbUtil.getCon();
					int row=table_not_hot.getSelectedRow();
					String id = (String)table_not_hot.getValueAt(row, 0);
					System.out.println(id);
					Good good = goodDao.searchByID(con,id);
					System.out.println(good);
					fillCusTable(good);
					fillClassTable(good);
					picture_lable.setIcon(good.getGood_picture());
					id_text.setText(""+good.getGood_id());
					name_text.setText(good.getGood_name());
					sell_text.setText(""+goodDao.getGoodSellNum(con, good));
					hot_text.setText(""+good.getGood_hot());
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
	
	
	
	public void goodSetOrCancelHot(ActionEvent e) throws Exception {
		
		Connection con=null;
		
		con = dbUtil.getCon();
		
		String idstr = id_text.getText();

		int modifyNum = goodDao.setHotOrCancel(con, idstr);
		
		if(modifyNum==1){
			JOptionPane.showMessageDialog(null, "修改成功");
			this.resetValue();
		}else{
			JOptionPane.showMessageDialog(null, "修改失败1111111111");
		}
		
		
	}
	
	
	
	

}
