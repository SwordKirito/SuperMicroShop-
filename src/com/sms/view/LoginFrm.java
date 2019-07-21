package com.sms.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.sms.dao.EmployeeDao;
import com.sms.model.Employee;
import com.sms.util.DbUtil;
import com.sms.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.JPasswordField;

public class LoginFrm extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userNameTxt;
	private EmployeeDao employeeDao=new EmployeeDao();
	private DbUtil dbUtil=new DbUtil();
	private JPasswordField passwordTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
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
	public LoginFrm() {
		
		//修改字体
		Font font = new Font("宋体",Font.PLAIN,12);
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()){
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof javax.swing.plaf.FontUIResource){
				UIManager.put(key, font);
			}
		}
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Shop_16px.png"));
		setTitle("超级微店管理系统v1.0");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblv = new JLabel("超级微店管理系统v1.0");
		lblv.setFont(new Font("宋体", Font.PLAIN, 30));
		lblv.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Shop_64px.net.png"));
		
		JLabel label_1 = new JLabel("用户名  ：");
		
		JLabel label_2 = new JLabel("密码    ：");
		
		userNameTxt = new JTextField();
		userNameTxt.setText("楚程翔");
		userNameTxt.setColumns(10);
		
		JButton button = new JButton("登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				loginActionPerformed(e);
				
			}
		});
		
		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				userNameTxt.setText("");
				
				passwordTxt.setText("");
				
				
			}
		});
		
		passwordTxt = new JPasswordField();
		passwordTxt.setText("123");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(110)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1)
								.addComponent(label_2))
							.addGap(34)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(1))
								.addComponent(passwordTxt))))
					.addContainerGap(47, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(30, Short.MAX_VALUE)
					.addComponent(lblv)
					.addGap(26))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addComponent(lblv)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		//设置jframe居中显示
		this.setLocationRelativeTo(null);
		
	}

	private void loginActionPerformed(ActionEvent e){
		// TODO Auto-generated method stub
		String username=userNameTxt.getText();
		String password=new String(passwordTxt.getPassword());
		
		System.out.println(password);
		
		if(StringUtil.isEmpty(username)){
			JOptionPane.showMessageDialog(null, "请输入用户名！");
			return;
		}
		if(StringUtil.isEmpty(password)){
			JOptionPane.showMessageDialog(null, "请输入密码！");
			return;
		}
		
		Employee employee=new Employee(username,password);
		
		Connection con=null;
		try {
			con = dbUtil.getCon();
			System.out.println("连接数据库成功！");
			Employee currentUser=employeeDao.UserLogin(con, employee);
			if(currentUser!=null){
				JOptionPane.showMessageDialog(null, "登陆成功！");
				dispose();
				//new mainFrm().setVisible(true);
				new mainFrm(currentUser);

			}else{
				JOptionPane.showMessageDialog(null, "用户名或密码错误！");
				
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("连接数据库失败！222");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

}
