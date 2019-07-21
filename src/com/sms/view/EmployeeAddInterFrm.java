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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import com.sms.dao.EmployeeDao;
import com.sms.model.Employee;
import com.sms.util.DbUtil;
import com.sms.util.StringUtil;
import java.awt.event.ActionListener;

public class EmployeeAddInterFrm extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField m_name_text;
	private JTextField m_age_text;
	private JTextField m_salary_text;
	private JTextField m_telephone_text;
	private JTextField m_qq_text;
	private JLabel m_picture_lable ;
	
	
	DbUtil dbUtil = new DbUtil();
	EmployeeDao employeeDao = new EmployeeDao();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeAddInterFrm frame = new EmployeeAddInterFrm();
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
	public EmployeeAddInterFrm() {
		setTitle("员工添加面板");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 354, 269);
		getContentPane().setLayout(null);
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u8BE6\u7EC6\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 10, 319, 220);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_5 = new JLabel("姓名：");
		label_5.setBounds(170, 34, 58, 15);
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("年龄：");
		label_6.setBounds(170, 64, 58, 15);
		panel_2.add(label_6);
		
		JLabel label_7 = new JLabel("工资：");
		label_7.setBounds(170, 94, 58, 15);
		panel_2.add(label_7);
		
		JLabel label_8 = new JLabel("电话：");
		label_8.setBounds(170, 124, 76, 15);
		panel_2.add(label_8);
		
		JLabel lblQq = new JLabel("QQ：");
		lblQq.setBounds(170, 154, 76, 15);
		panel_2.add(lblQq);
		
		JButton button_1 = new JButton("添加");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeUpdateActionEvent(e);
			}
		});
		button_1.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\add_16px.png"));
		button_1.setBounds(170, 182, 122, 23);
		panel_2.add(button_1);
		
		m_name_text = new JTextField();
		m_name_text.setBounds(226, 33, 66, 21);
		panel_2.add(m_name_text);
		m_name_text.setColumns(10);
		
		m_age_text = new JTextField();
		m_age_text.setBounds(226, 61, 66, 21);
		panel_2.add(m_age_text);
		m_age_text.setColumns(10);
		
		m_salary_text = new JTextField();
		m_salary_text.setBounds(226, 91, 66, 21);
		panel_2.add(m_salary_text);
		m_salary_text.setColumns(10);
		
		m_telephone_text = new JTextField();
		m_telephone_text.setBounds(226, 121, 66, 21);
		panel_2.add(m_telephone_text);
		m_telephone_text.setColumns(10);
		
		m_qq_text = new JTextField();
		m_qq_text.setBounds(226, 151, 66, 21);
		panel_2.add(m_qq_text);
		m_qq_text.setColumns(10);
		
		m_picture_lable = new JLabel("");
		m_picture_lable.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\worker_72px.png"));
		m_picture_lable.setHorizontalAlignment(SwingConstants.CENTER);
		m_picture_lable.setBounds(20, 33, 140, 140);
		panel_2.add(m_picture_lable);
		
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

	}


	/*	    
	private JTextField m_name_text;
	private JTextField m_age_text;
	private JTextField m_salary_text;
	private JTextField m_telephone_text;
	private JTextField m_qq_text;
	private JLabel m_picture_lable ;			*/

	private void resetValue() {
		// TODO Auto-generated method stub
		this.m_picture_lable.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\employee_72px.net.png"));
		this.m_name_text.setText("");
		this.m_age_text.setText("");
		this.m_salary_text.setText("");
		this.m_telephone_text.setText("");
		this.m_qq_text.setText("");
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
			m_picture_lable.setIcon(IC);		
	}
	/**
	 * 宠物类别修改事件处理
	 * @param e
	 */	
	/*	    
	private JTextField m_name_text;
	private JTextField m_age_text;
	private JTextField m_salary_text;
	private JTextField m_telephone_text;
	private JTextField m_qq_text;
	private JLabel m_picture_lable ;			*/

	private void employeeUpdateActionEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//int emp_id=-1;
		String emp_name="张三";
		String emp_pwd="123";
		int emp_age=-1;
		int emp_salary=-1;
		int emp_work_time=-1;
		int shop_id=-1;
		String emo_tele;
		String emp_qq;
		ImageIcon emp_photo=null;
		
		
		emp_name = m_name_text.getText();
		emp_age = Integer.parseInt(m_age_text.getText());
		emp_salary = Integer.parseInt(m_salary_text.getText());
		emp_work_time = 0;
		shop_id = 0;///////////////////////////////////////////////////////////////////////需要后期修改的，这里没有考虑多店铺！！！！！！！！！！！！
		emo_tele = m_telephone_text.getText();
		emp_qq = m_qq_text.getText();
		emp_photo=(ImageIcon) m_picture_lable.getIcon();
		

		
		Employee employee = new Employee(emp_name,emp_pwd,emp_age,emp_salary,emp_work_time,shop_id,emo_tele,emp_qq,emp_photo);

		if(StringUtil.isEmpty(emp_name)){
			JOptionPane.showMessageDialog(null, "员工名字不能为空！");
			return;
		}
		/*
		 * 
		int emp_id
		String emp_name
		String emp_pwd
		int emp_age
		int emp_salary
		Date emp_work_time
		int shop_id
		String emo_tele
		String emp_qq
		ImageIcon emp_photo
		*/
		
		//Customer customer = new Customer(class_id,class_name,class_remark);
		
		
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int n=employeeDao.add(con, employee);
			if(n==1){
				JOptionPane.showMessageDialog(null, "添加成功");
				resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "添加失败");
			}
		}catch(Exception e1){
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "添加失败");
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
