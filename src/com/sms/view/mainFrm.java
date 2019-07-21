package com.sms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.sms.dao.EmployeeDao;
import com.sms.model.Employee;
import com.sms.util.DbUtil;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;


import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;

public class mainFrm extends JFrame {
	private DbUtil dbUtil=new DbUtil();
	private EmployeeDao employeeDao=new EmployeeDao();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static public JDesktopPane table=null;
	private JTextField textField;
	
	
	
	JLabel jLabel_Boss =null;
	JLabel jLabel_Employee =null;
	public static Employee currentEmployee=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrm frame = new mainFrm();
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
	
	public mainFrm(Employee employee) {
		currentEmployee = employee;
		mainFrm mainFrmWindow = new mainFrm();
		mainFrmWindow.setVisible(true);
		Connection con=null;
		try {
			con = dbUtil.getCon();
			System.out.println("连接数据库成功！");
			if(employeeDao.isBoss(con, employee)) {
				mainFrmWindow.setTitle("超级微店管理系统v1.0 （BOSS端）");
				mainFrmWindow.jLabel_Boss.setBounds(51, 10, 84, 39);
			}
			else {
				mainFrmWindow.setTitle("超级微店管理系统v1.0 （员工端）");
				mainFrmWindow.jLabel_Employee.setBounds(51, 10, 84, 39);
			}
			System.out.println(employee);

			mainFrmWindow.textField.setText(employee.getEmp_name());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("连接数据库失败！");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
		
		
	}
	
	
	
	
	public mainFrm() {
		
		Font font = new Font("宋体",Font.PLAIN,12);
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()){
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof javax.swing.plaf.FontUIResource){
				UIManager.put(key, font);
			}
		}

		setTitle("超级微店管理系统v1.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Shop_16px.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 394);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("基本数据维护");
		menu.setIcon(new ImageIcon("E:\\程序试做\\java课\\PetShop2.0\\image\\base.png"));
		menuBar.add(menu);
		
		JMenu menu_1 = new JMenu("商品管理");
		menu_1.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\goodsManager.png"));
		menu.add(menu_1);
		
		JMenuItem menuItem = new JMenuItem("商品添加");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GoodAddInterFrm goodAddInterFrm;
				try {
					goodAddInterFrm = new GoodAddInterFrm();//新建一个内部窗口
					goodAddInterFrm.setVisible(true);//显示内部窗口
					table.add(goodAddInterFrm);	//内部窗口添加到table上
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		menuItem.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\add_16px.png"));
		menu_1.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("商品维护");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GoodManageInterFrm goodManageInterFrm;
				try {
					goodManageInterFrm = new GoodManageInterFrm();//新建一个内部窗口
					goodManageInterFrm.setVisible(true);//显示内部窗口
					table.add(goodManageInterFrm);	//内部窗口添加到table上
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_1.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\edit_16px.png"));
		menu_1.add(menuItem_1);
		
		JMenuItem menuItem_5 = new JMenuItem("标记热门");
		menuItem_5.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\hot_16px.png"));
		menu_1.add(menuItem_5);
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GoodSetHotInterFrm goodSetHotInterFrm;
				try {
					goodSetHotInterFrm = new GoodSetHotInterFrm();//新建一个内部窗口
					goodSetHotInterFrm.setVisible(true);//显示内部窗口
					table.add(goodSetHotInterFrm);	//内部窗口添加到table上
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenu menu_2 = new JMenu("商品类别管理");
		menu_2.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\categories_inventory_16px.png"));
		menu.add(menu_2);
		
		JMenuItem menuItem_2 = new JMenuItem("商品类别添加");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ClassAddInterFrm classAddInterFrm;
				try {
					classAddInterFrm = new ClassAddInterFrm();//新建一个内部窗口
					classAddInterFrm.setVisible(true);//显示内部窗口
					table.add(classAddInterFrm);	//内部窗口添加到table上
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_2.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\add_16px.png"));
		menu_2.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("商品类别维护");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ClassManageInterFrm classManageInterFrm;
				try {
					classManageInterFrm = new ClassManageInterFrm();//新建一个内部窗口
					classManageInterFrm.setVisible(true);//显示内部窗口
					table.add(classManageInterFrm);	//内部窗口添加到table上
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_3.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\edit_16px.png"));
		menu_2.add(menuItem_3);
		
		JMenu menu_4 = new JMenu("客户管理");
		menu_4.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\userName.png"));
		menu.add(menu_4);
		
		JMenuItem menuItem_6 = new JMenuItem("客户信息维护");
		menuItem_6.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\edit_16px.png"));
		menu_4.add(menuItem_6);
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CusManageInterFrm cusManageInterFrm;
				try {
					cusManageInterFrm = new CusManageInterFrm();//新建一个内部窗口
					cusManageInterFrm.setVisible(true);//显示内部窗口
					table.add(cusManageInterFrm);	//内部窗口添加到table上
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		
		JMenuItem menuItem_7 = new JMenuItem("客户来源分析");
		menuItem_7.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Analytics_16px.png"));
		menu_4.add(menuItem_7);
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CusAnalysisInterFrm cusAnalysisInterFrm;
				try {
					cusAnalysisInterFrm = new CusAnalysisInterFrm();//新建一个内部窗口
					cusAnalysisInterFrm.setVisible(true);//显示内部窗口
					table.add(cusAnalysisInterFrm);	//内部窗口添加到table上
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenu menu_5 = new JMenu("员工管理");
		menu_5.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\employee_16px.png"));
		menu.add(menu_5);
		
		JMenuItem menuItem_9 = new JMenuItem("员工添加");
		menuItem_9.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\add_16px.png"));
		menu_5.add(menuItem_9);
		menuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				try {
					if(jLabel_Boss.getWidth()!=0) {
						EmployeeAddInterFrm employeeAddInterFrm = new EmployeeAddInterFrm();//新建一个内部窗口
						employeeAddInterFrm.setVisible(true);//显示内部窗口
						table.add(employeeAddInterFrm);	//内部窗口添加到table上
					}else {
						JOptionPane.showMessageDialog(null, "您无权添加员工", "ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem menuItem_8 = new JMenuItem("员工信息维护");
		menuItem_8.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\edit_16px.png"));
		menu_5.add(menuItem_8);
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(jLabel_Boss.getWidth()!=0) {
						EmployeeInforBossInterFrm employeeInforBossInterFrm = new EmployeeInforBossInterFrm();//新建一个内部窗口
						employeeInforBossInterFrm.setVisible(true);//显示内部窗口
						table.add(employeeInforBossInterFrm);	//内部窗口添加到table上
						
					}else {
						EmployeeInforEmployeeInterFrm employeeInforEmployeeInterFrm = new EmployeeInforEmployeeInterFrm();//新建一个内部窗口
						employeeInforEmployeeInterFrm.setVisible(true);//显示内部窗口
						table.add(employeeInforEmployeeInterFrm);	//内部窗口添加到table上
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenu menu_6 = new JMenu("订单管理");
		menu_6.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Order_16px.net.png"));
		menu.add(menu_6);
		
		
		JMenuItem menuItem_10 = new JMenuItem("订单确认");
		menuItem_10.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\confirmation_16px.png"));
		menu_6.add(menuItem_10);
		menuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				OrderAffirmInterFrm orderAffirmInterFrm;
				try {
					orderAffirmInterFrm = new OrderAffirmInterFrm();//新建一个内部窗口
					orderAffirmInterFrm.setVisible(true);//显示内部窗口
					table.add(orderAffirmInterFrm);	//内部窗口添加到table上
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem menuItem_11 = new JMenuItem("订单查看");
		menuItem_11.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\check_16px.png"));
		menu_6.add(menuItem_11);
		menuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				OrderCheckInterFrm orderCheckInterFrm;
				try {
					orderCheckInterFrm = new OrderCheckInterFrm();//新建一个内部窗口
					orderCheckInterFrm.setVisible(true);//显示内部窗口
					table.add(orderCheckInterFrm);	//内部窗口添加到table上
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenu menu_7 = new JMenu("店铺管理");
		menu_7.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Shop2_16px.png"));
		menu.add(menu_7);
		
		JMenuItem menuItem_12 = new JMenuItem("店铺信息维护");
		menuItem_12.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\edit_16px.png"));
		menu_7.add(menuItem_12);
		menuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ShopManageInterFrm shopManageInterFrm;
				try {
					shopManageInterFrm = new ShopManageInterFrm();//新建一个内部窗口
					shopManageInterFrm.setVisible(true);//显示内部窗口
					table.add(shopManageInterFrm);	//内部窗口添加到table上
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem menuItem_13 = new JMenuItem("经营情况分析");
		menuItem_13.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Analytics_16px.png"));
		menu_7.add(menuItem_13);
		menuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ShopAnalysisInterFrm shopAnalysisInterFrm;
				try {
					shopAnalysisInterFrm = new ShopAnalysisInterFrm();//新建一个内部窗口
					shopAnalysisInterFrm.setVisible(true);//显示内部窗口
					table.add(shopAnalysisInterFrm);	//内部窗口添加到table上
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem menuItem_4 = new JMenuItem("安全退出");
		menuItem_4.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\exit.png"));
		menu.add(menuItem_4);
		
		JMenu menu_3 = new JMenu("关于我们");
		menu_3.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\about.png"));
		menuBar.add(menu_3);
		
		JMenuItem mntmv = new JMenuItem("超级微店管理系统v1.0");
		mntmv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//IntroduceUsInterFrm introduceUsInterFrm = new IntroduceUsInterFrm();
				//introduceUsInterFrm.setVisible(true);
				//table.add(introduceUsInterFrm);
				
				
			}
		});
		mntmv.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\about.png"));
		menu_3.add(mntmv);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JDesktopPane();
		contentPane.add(table, BorderLayout.CENTER);
		table.setLayout(null);
		
		JLabel lblv = new JLabel("欢迎                       使用 超级微店管理系统v1.0");
		lblv.setFont(new Font("楷体", Font.BOLD, 19));
		lblv.setForeground(Color.GRAY);
		lblv.setBounds(10, 10, 736, 39);
		table.add(lblv);
		
		textField = new JTextField();
		textField.setFont(new Font("楷体", Font.BOLD, 20));
		textField.setBounds(130, 10, 162, 39);
		table.add(textField);
		textField.setColumns(10);
		
		jLabel_Employee = new JLabel("员工：");
		jLabel_Employee.setFont(new Font("楷体", Font.BOLD, 20));
		jLabel_Employee.setForeground(Color.BLACK);
		//jLabel_Employee.setBounds(51, 10, 84, 39);
		table.add(jLabel_Employee);
		jLabel_Employee.setBounds(51, 10, 0, 0);
		
		jLabel_Boss = new JLabel("老板：");
		jLabel_Boss.setForeground(Color.DARK_GRAY);
		jLabel_Boss.setFont(new Font("楷体", Font.BOLD, 20));
		//jLabel_Boss.setBounds(51, 10, 84, 39);
		table.add(jLabel_Boss);
		jLabel_Boss.setBounds(51, 10, 0, 0);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
	}
}
