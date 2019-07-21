package com.sms.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sms.dao.EmployeeDao;
import com.sms.model.Employee;
import com.sms.util.DbUtil;
import com.sms.util.StringUtil;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class EmployeeInforBossInterFrm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private JTextField s_id_text;
	private JTextField s_age_text;
	private JTextField s_work_time_text;
	private JTextField s_name_text;
	private JTextField s_salary_text;
	private JTextField s_tele_text;
	private JTextField s_qq_text;
	private JTextField m_id_text;
	private JTextField m_name_text;
	private JTextField m_age_text;
	private JTextField m_salary_text;
	private JTextField m_tele_text;
	private JTextField m_qq_text;
	private JTextField m_work_time_text;
	private JTable table;
	private JTextField m_shop_id_text;
	private JLabel m_photo_lable ;
	
	
	DbUtil dbUtil = new DbUtil();
	EmployeeDao employeeDao = new EmployeeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeInforBossInterFrm frame = new EmployeeInforBossInterFrm();
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
	public EmployeeInforBossInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("员工信息维护（老板端）");
		setBounds(100, 100, 670, 340);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 311, 141);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblid = new JLabel("员工id：");
		lblid.setBounds(22, 26, 58, 15);
		panel.add(lblid);
		
		JLabel label = new JLabel("员工年龄：");
		label.setBounds(22, 54, 72, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("工作时间：");
		label_1.setBounds(22, 82, 72, 15);
		panel.add(label_1);
		
		s_id_text = new JTextField();
		s_id_text.setBounds(81, 23, 66, 21);
		panel.add(s_id_text);
		s_id_text.setColumns(10);
		
		s_age_text = new JTextField();
		s_age_text.setBounds(81, 51, 66, 21);
		panel.add(s_age_text);
		s_age_text.setColumns(10);
		
		s_work_time_text = new JTextField();
		s_work_time_text.setBounds(81, 79, 66, 21);
		panel.add(s_work_time_text);
		s_work_time_text.setColumns(10);
		
		JLabel label_2 = new JLabel("员工姓名：");
		label_2.setBounds(157, 26, 72, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("员工工资：");
		label_3.setBounds(157, 54, 72, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("员工电话：");
		label_4.setBounds(157, 82, 72, 15);
		panel.add(label_4);
		
		JLabel lblqq = new JLabel("员工qq:");
		lblqq.setBounds(22, 110, 72, 15);
		panel.add(lblqq);
		
		s_name_text = new JTextField();
		s_name_text.setBounds(220, 23, 66, 21);
		panel.add(s_name_text);
		s_name_text.setColumns(10);
		
		s_salary_text = new JTextField();
		s_salary_text.setBounds(220, 51, 66, 21);
		panel.add(s_salary_text);
		s_salary_text.setColumns(10);
		
		s_tele_text = new JTextField();
		s_tele_text.setBounds(220, 79, 66, 21);
		panel.add(s_tele_text);
		s_tele_text.setColumns(10);
		
		s_qq_text = new JTextField();
		s_qq_text.setBounds(81, 107, 66, 21);
		panel.add(s_qq_text);
		s_qq_text.setColumns(10);
		
		JButton button = new JButton("查找");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				employeeSearchActionPerform(e);
				
				
				
				
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\search_16px.png"));
		button.setBounds(178, 107, 90, 21);
		panel.add(button);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u8BE6\u7EC6\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(331, 10, 319, 290);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(179, 36, 58, 15);
		panel_2.add(lblId);
		
		JLabel label_5 = new JLabel("姓名：");
		label_5.setBounds(179, 66, 58, 15);
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("年龄：");
		label_6.setBounds(179, 96, 58, 15);
		panel_2.add(label_6);
		
		JLabel label_7 = new JLabel("工资：");
		label_7.setBounds(179, 126, 58, 15);
		panel_2.add(label_7);
		
		JLabel label_8 = new JLabel("电话：");
		label_8.setBounds(179, 156, 76, 15);
		panel_2.add(label_8);
		
		JLabel lblQq = new JLabel("QQ：");
		lblQq.setBounds(179, 186, 76, 15);
		panel_2.add(lblQq);
		
		JButton button_1 = new JButton("修改");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				try {
					employeeUpdateActionEvent(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		
				
				
			}
		});
		button_1.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\modify_16px.png"));
		button_1.setBounds(63, 244, 97, 23);
		panel_2.add(button_1);
		
		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				employeeDeleteActionEvent(e);
				
				
				
				
			}
		});
		button_2.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\delete_16px.png"));
		button_2.setBounds(177, 244, 97, 23);
		panel_2.add(button_2);
		
		m_id_text = new JTextField();
		m_id_text.setBounds(235, 33, 66, 21);
		panel_2.add(m_id_text);
		m_id_text.setColumns(10);
		
		m_name_text = new JTextField();
		m_name_text.setBounds(235, 65, 66, 21);
		panel_2.add(m_name_text);
		m_name_text.setColumns(10);
		
		m_age_text = new JTextField();
		m_age_text.setBounds(235, 93, 66, 21);
		panel_2.add(m_age_text);
		m_age_text.setColumns(10);
		
		m_salary_text = new JTextField();
		m_salary_text.setBounds(235, 123, 66, 21);
		panel_2.add(m_salary_text);
		m_salary_text.setColumns(10);
		
		m_tele_text = new JTextField();
		m_tele_text.setBounds(235, 153, 66, 21);
		panel_2.add(m_tele_text);
		m_tele_text.setColumns(10);
		
		m_qq_text = new JTextField();
		m_qq_text.setBounds(235, 183, 66, 21);
		panel_2.add(m_qq_text);
		m_qq_text.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("工作时间：");
		lblNewLabel.setBounds(179, 216, 76, 15);
		panel_2.add(lblNewLabel);
		
		m_work_time_text = new JTextField();
		m_work_time_text.setBounds(235, 213, 66, 21);
		panel_2.add(m_work_time_text);
		m_work_time_text.setColumns(10);
		
		m_photo_lable = new JLabel("");
		m_photo_lable.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\worker_72px.png"));
		m_photo_lable.setHorizontalAlignment(SwingConstants.CENTER);
		m_photo_lable.setBounds(20, 33, 140, 140);
		panel_2.add(m_photo_lable);
		
		JButton button_3 = new JButton("上传照片");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				uploadPhoto();
				
				
				
				
			}
		});
		button_3.setHorizontalAlignment(SwingConstants.LEFT);
		button_3.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Upload_16px.png"));
		button_3.setBounds(30, 182, 113, 23);
		panel_2.add(button_3);
		
		JLabel label_10 = new JLabel("所属店铺：");
		label_10.setBounds(20, 216, 76, 15);
		panel_2.add(label_10);
		
		m_shop_id_text = new JTextField();
		m_shop_id_text.setBounds(94, 213, 66, 21);
		panel_2.add(m_shop_id_text);
		m_shop_id_text.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 161, 311, 139);
		getContentPane().add(scrollPane_1);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				
				employeeTableMousePressed(e);
				
				
				
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "\u59D3\u540D", "\u5E74\u9F84", "\u5DE5\u8D44", "\u5DE5\u4F5C\u65F6"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5910156844832988547L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(4).setPreferredWidth(109);
		scrollPane_1.setViewportView(table);
		
		
		fillTable(new Employee());
		

	}
	
	
	
	
	
	
	
	
	
	
	
/*	private JTextField s_id_text;
	private JTextField s_age_text;
	private JTextField s_work_time_text;
	private JTextField s_name_text;
	private JTextField s_salary_text;
	private JTextField s_tele_text;
	private JTextField s_qq_text;
	private JTextField m_id_text;
	private JTextField m_name_text;
	private JTextField m_age_text;
	private JTextField m_salary_text;
	private JTextField m_tele_text;
	private JTextField m_qq_text;
	private JTextField m_work_time_text;
	private JTable table_1;
	private JTextField m_shop_id_text;
	private JLabel m_photo_lable ;
	*/
	

	protected void employeeSearchActionPerform(ActionEvent e) {
		// TODO Auto-generated method stub
		String s_id_text = this.s_id_text.getText();
		String s_name_text = this.s_name_text.getText();
		String s_age_text = this.s_age_text.getText();
		String s_salary_text = this.s_salary_text.getText();
		String s_work_time_text = this.s_work_time_text.getText();
		String s_tele_text = this.s_tele_text.getText();
		String s_qq_text = this.s_qq_text.getText();
		Employee employee = new Employee();
		if(StringUtil.isNotEmpty(s_id_text)) {
			employee.setEmp_id(Integer.parseInt(s_id_text));
		}
		employee.setEmp_name(s_name_text);
		if(StringUtil.isNotEmpty(s_age_text)) {
			employee.setEmp_age(Integer.parseInt(s_age_text));
		}
		if(StringUtil.isNotEmpty(s_salary_text)) {
			employee.setEmp_salary(Integer.parseInt(s_salary_text));
		}
		if(StringUtil.isNotEmpty(s_work_time_text)) {
			employee.setEmp_work_time(Integer.parseInt(s_work_time_text));
		}
		employee.setEmp_tele(s_tele_text);
		employee.setEmp_qq(s_qq_text);
		this.fillTable(employee);
	}

	protected void employeeTableMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		Connection con=null;
		
		try {
			con=dbUtil.getCon();

			int row=table.getSelectedRow();
			
			String id = (String)table.getValueAt(row, 0);
			
			Employee employee = employeeDao.searchByID(con,id);
			
			
			/*	private JTextField s_id_text;
			private JTextField s_age_text;
			private JTextField s_work_time_text;
			private JTextField s_name_text;
			private JTextField s_salary_text;
			private JTextField s_tele_text;
			private JTextField s_qq_text;
			
			
			private JTextField m_id_text;
			private JTextField m_name_text;
			private JTextField m_age_text;
			private JTextField m_salary_text;
			private JTextField m_tele_text;
			private JTextField m_qq_text;
			private JTextField m_work_time_text;
			private JTable table_1;
			private JTextField m_shop_id_text;
			private JLabel m_photo_lable ;
			*/
			
			m_photo_lable.setIcon(employee.getEmp_photo());
			m_id_text.setText(""+employee.getEmp_id());
			m_name_text.setText(employee.getEmp_name());
			m_age_text.setText(""+employee.getEmp_age());
			m_salary_text.setText(""+employee.getEmp_salary());
			m_tele_text.setText(""+employee.getEmp_tele());
			m_qq_text.setText(employee.getEmp_qq());
			m_work_time_text.setText(""+employee.getEmp_work_time());
			m_shop_id_text.setText(""+employee.getShop_id());
	
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
	
	

	private void fillTable(Employee employee){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = employeeDao.list(con, employee);
			//int i=1;
			while(rs.next()){
				Vector<String> v=new Vector<String>();
				//v.add(""+ i++);
				v.add(""+rs.getInt("emp_id"));
				v.add(rs.getString("emp_name"));
				v.add(""+rs.getInt("emp_age"));
				v.add(""+rs.getInt("emp_salary"));
				v.add(""+rs.getInt("emp_work_time"));
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
	



	private void employeeUpdateActionEvent(ActionEvent e) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
		/*	
		
		private int emp_id;
		private String emp_name;
		private String emp_pwd;

		private int emp_age;
		private int emp_salary;
		private int emp_work_time;

		private int shop_id;
		private String emp_tele;
		private String emp_qq;

		private ImageIcon emp_photo ;
		
		
			private JTextField m_id_text;
			private JTextField m_name_text;
			private JTextField m_age_text;
			private JTextField m_salary_text;
			private JTextField m_tele_text;
			private JTextField m_qq_text;
			private JTextField m_work_time_text;
			private JTable table_1;
			private JTextField m_shop_id_text;
			private JLabel m_photo_lable ;
		*/
		
		int emp_id;
		String emp_name;
		String emp_pwd;
		int emp_age;
		int emp_salary;
		int emp_work_time;
		int shop_id;
		String emp_tele;
		String emp_qq;
		ImageIcon emp_photo ;
		
		
		String idstr=m_id_text.getText();
		String name=m_name_text.getText();
		String agestr=m_age_text.getText();
		String salarystr=m_salary_text.getText();
		String tele=m_tele_text.getText();
		String qq=m_qq_text.getText();
		String work_timestr=m_work_time_text.getText();
		String shop_idstr=m_shop_id_text.getText();
		emp_photo=(ImageIcon) m_photo_lable.getIcon();
		
		emp_id=Integer.parseInt(idstr);
		emp_name=name;
		emp_pwd=employeeDao.searchByID(dbUtil.getCon(), idstr).getEmp_pwd();
		emp_age=Integer.parseInt(agestr);
		emp_salary=Integer.parseInt(salarystr);
		emp_work_time=Integer.parseInt(work_timestr);
		shop_id=Integer.parseInt(shop_idstr);
		emp_tele=tele;
		emp_qq=qq;
		emp_photo=(ImageIcon) m_photo_lable.getIcon();
		
						
		if(StringUtil.isEmpty(idstr)){
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return;
		}
		if(StringUtil.isEmpty(shop_idstr)){
			JOptionPane.showMessageDialog(null, "所属店铺不能为空");
			return;
		}

		Connection con = null;
		try{
			con=dbUtil.getCon();
			
			Employee employee ;
			
			employee=employeeDao.searchByID(con, idstr);
			employee.setEmp_id(emp_id);
			employee.setEmp_name(emp_name);
			employee.setEmp_pwd(emp_pwd);
			employee.setEmp_age(emp_age);
			employee.setEmp_salary(emp_salary);
			employee.setEmp_work_time(emp_work_time);
			employee.setShop_id(shop_id);
			employee.setEmp_tele(emp_tele);
			employee.setEmp_qq(emp_qq);
			employee.setEmp_photo(emp_photo);
			
			
			
			
			int modifyNum=employeeDao.update(con, employee);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetValue();
				this.fillTable(new Employee());
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

	/**
	 * 图书类别删除事件处理
	 * @param e
	 */
	
	
	
	private void employeeDeleteActionEvent(ActionEvent e) {
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
				int deleteNum=employeeDao.delete(con,idstr);
				if(deleteNum==1){
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillTable(new Employee());
					
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


/*	private JTextField m_id_text;
	private JTextField m_name_text;
	private JTextField m_age_text;
	private JTextField m_salary_text;
	private JTextField m_tele_text;
	private JTextField m_qq_text;
	private JTextField m_work_time_text;
	private JTable table_1;
	private JTextField m_shop_id_text;
	private JLabel m_photo_lable ;*/
	private void resetValue() {
		// TODO Auto-generated method stub
		this.m_photo_lable.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\worker_72px"));
		this.m_id_text.setText("");
		this.m_name_text.setText("");
		this.m_age_text.setText("");
		this.m_salary_text.setText("");
		this.m_tele_text.setText("");
		this.m_qq_text.setText("");
		this.m_work_time_text.setText("");
		this.m_shop_id_text.setText("");
		this.fillTable(new Employee());
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
			m_photo_lable.setIcon(IC);
			
			
		
	}
	
	
	
	
	
	
	
}
