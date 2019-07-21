package com.sms.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sms.dao.CustomerDao;
import com.sms.model.Customer;
import com.sms.util.DbUtil;

public class CusAnalysisInterFrm extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9115122797301131620L;
	private JTable table;

	private DbUtil dbUtil=new DbUtil();
	private CustomerDao customerDao=new CustomerDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CusAnalysisInterFrm frame = new CusAnalysisInterFrm();
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
	public CusAnalysisInterFrm() {
		setFrameIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Analytics_16px.png"));
		setClosable(true);
		setIconifiable(true);
		setTitle("客户来源分析");
		setBounds(100, 100, 413, 500);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("影响力排行榜");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(110, 26, 170, 63);
		getContentPane().add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 377, 373);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5E8F\u53F7", "ID", "\u7528\u6237\u540D", "\u5F71\u54CD\u529B"
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
		fillTable(new Customer());
	}
	private void fillTable(Customer customer){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = customerDao.listForSource(con, customer);
			int i=1;
			while(rs.next()){
				Vector<String> v=new Vector<String>();
				v.add(""+ i++);
				v.add(""+rs.getInt("id"));
				v.add(rs.getString("username"));
				v.add(rs.getString("times"));
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
}
