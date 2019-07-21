package com.sms.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import com.sms.dao.EmployeeDao;
import com.sms.model.Employee;
import com.sms.util.DbUtil;
import com.sms.util.StringUtil;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;

public class EmployeeInforEmployeeInterFrm extends JInternalFrame {
	/**
	 * 
	 */
	private Employee thisEmployee;
	private static final long serialVersionUID = 1L;
	private JTextField m_id_text;
	private JTextField m_name_text;
	private JTextField m_age_text;
	private JTextField m_tele_text;
	private JTextField m_qq_text;
	private JTextField m_work_time_text;
	private JTextField m_salary_text;
	private JTextField m_shop_id_text;
	private JTextField m_pwd_text;
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
					EmployeeInforEmployeeInterFrm frame = new EmployeeInforEmployeeInterFrm();
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
	public EmployeeInforEmployeeInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("员工信息维护（员工端）");
		setBounds(100, 100, 370, 340);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 341, 288);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("姓名：");
		label_2.setBounds(174, 57, 82, 15);
		panel.add(label_2);
		
		m_name_text = new JTextField();
		m_name_text.setBounds(240, 55, 66, 21);
		panel.add(m_name_text);
		m_name_text.setColumns(10);
		
		JLabel label_3 = new JLabel("年龄：");
		label_3.setBounds(174, 87, 58, 15);
		panel.add(label_3);
		
		m_age_text = new JTextField();
		m_age_text.setBounds(240, 84, 66, 21);
		panel.add(m_age_text);
		m_age_text.setColumns(10);
		
		JLabel label_4 = new JLabel("电话：");
		label_4.setBounds(174, 117, 58, 15);
		panel.add(label_4);
		
		m_tele_text = new JTextField();
		m_tele_text.setBounds(240, 114, 66, 21);
		panel.add(m_tele_text);
		m_tele_text.setColumns(10);
		
		JLabel lblQq = new JLabel("QQ：");
		lblQq.setBounds(174, 147, 58, 15);
		panel.add(lblQq);
		
		m_qq_text = new JTextField();
		m_qq_text.setBounds(240, 144, 66, 21);
		panel.add(m_qq_text);
		m_qq_text.setColumns(10);
		
		JLabel label_5 = new JLabel("工作时间：");
		label_5.setBounds(174, 177, 82, 15);
		panel.add(label_5);
		
		m_work_time_text = new JTextField();
		m_work_time_text.setBounds(240, 174, 66, 21);
		panel.add(m_work_time_text);
		m_work_time_text.setColumns(10);
		
		JLabel label_6 = new JLabel("工资：");
		label_6.setBounds(174, 207, 58, 15);
		panel.add(label_6);
		
		m_salary_text = new JTextField();
		m_salary_text.setBounds(240, 204, 66, 21);
		panel.add(m_salary_text);
		m_salary_text.setColumns(10);
		
		m_id_text = new JTextField();
		m_id_text.setBounds(240, 24, 66, 21);
		panel.add(m_id_text);
		m_id_text.setColumns(10);
		
		JLabel lblid = new JLabel("员工id：");
		lblid.setBounds(174, 27, 82, 15);
		panel.add(lblid);
		
		m_photo_lable = new JLabel("");
		m_photo_lable.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\worker_72px.png"));
		m_photo_lable.setHorizontalAlignment(SwingConstants.CENTER);
		m_photo_lable.setBounds(24, 22, 140, 140);
		panel.add(m_photo_lable);
		
		JButton button_1 = new JButton("上传照片");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				uploadPhoto();
				
				
				
				
				
			}
		});
		button_1.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Upload_16px.png"));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setBounds(34, 173, 108, 23);
		panel.add(button_1);
		
		JLabel label_1 = new JLabel("所属店铺：");
		label_1.setBounds(34, 203, 75, 23);
		panel.add(label_1);
		
		m_shop_id_text = new JTextField();
		m_shop_id_text.setColumns(10);
		m_shop_id_text.setBounds(98, 204, 66, 21);
		panel.add(m_shop_id_text);
		
		JButton button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				try {
					employeeUpdateActionEvent(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		button.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\modify_16px.png"));
		button.setBounds(186, 243, 97, 23);
		panel.add(button);
		
		JLabel label = new JLabel("密码：");
		label.setBounds(34, 243, 75, 23);
		panel.add(label);
		
		m_pwd_text = new JTextField();
		m_pwd_text.setColumns(10);
		m_pwd_text.setBounds(98, 244, 66, 21);
		panel.add(m_pwd_text);
		loadInfor();

	}
	
	
	
	
	
	
	
	
	
	

	
	

	void employeeUpdateActionEvent(ActionEvent e) throws SQLException, Exception {
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
		String pwd=m_pwd_text.getText();
		String agestr=m_age_text.getText();
		String salarystr=m_salary_text.getText();
		String tele=m_tele_text.getText();
		String qq=m_qq_text.getText();
		String work_timestr=m_work_time_text.getText();
		String shop_idstr=m_shop_id_text.getText();
		emp_photo=(ImageIcon) m_photo_lable.getIcon();
		
		emp_id=Integer.parseInt(idstr);
		emp_name=name;
		emp_pwd=pwd;
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
				this.loadInfor();
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
	private void loadInfor() {
		// TODO Auto-generated method stub
		
		int id = mainFrm.currentEmployee.getEmp_id();
		
		
		
		Connection con=null;
		
		try {
			con=dbUtil.getCon();
			thisEmployee = employeeDao.searchByID(con, ""+id);
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			
			try {
				dbUtil.closeCon(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		

		this.m_photo_lable.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\worker_72px"));
		this.m_id_text.setText(""+thisEmployee.getEmp_id());
		this.m_name_text.setText(""+thisEmployee.getEmp_name());
		this.m_age_text.setText(""+thisEmployee.getEmp_age());
		this.m_salary_text.setText(""+thisEmployee.getEmp_salary());
		this.m_tele_text.setText(""+thisEmployee.getEmp_tele());
		this.m_qq_text.setText(""+thisEmployee.getEmp_qq());
		this.m_work_time_text.setText(""+thisEmployee.getEmp_work_time());
		this.m_shop_id_text.setText(""+thisEmployee.getShop_id());
		this.m_pwd_text.setText(""+thisEmployee.getEmp_pwd());
		this.m_photo_lable.setIcon(thisEmployee.getEmp_photo());
		
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
