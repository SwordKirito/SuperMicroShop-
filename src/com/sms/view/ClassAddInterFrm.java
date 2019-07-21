package com.sms.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sms.util.DbUtil;
import com.sms.util.StringUtil;
import com.sms.dao.ClassDao;
import com.sms.model.Class;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class ClassAddInterFrm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextArea textArea ;
	

	private DbUtil dbUtil=new DbUtil();
	private ClassDao classDao=new ClassDao();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassAddInterFrm frame = new ClassAddInterFrm();
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
	public ClassAddInterFrm() {
		setFrameIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\categories_inventory_16px.png"));
		setTitle("商品类别添加");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 413, 240);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("商品类别名称：");
		label.setBounds(30, 30, 102, 15);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("商品类别描述：");
		label_1.setBounds(30, 72, 102, 15);
		getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setBounds(123, 27, 115, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(123, 72, 242, 71);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				classAddActionPerformed(e);
				
				
			}
		});
		button.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\add_16px.png"));
		button.setBounds(87, 163, 93, 23);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				resetValue();
				
			}
		});
		button_1.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\reset_16px.png"));
		button_1.setBounds(223, 163, 93, 23);
		getContentPane().add(button_1);

	}
	
	
	protected void classAddActionPerformed(ActionEvent evt) {
		String textField=this.textField.getText();
		String textArea=this.textArea.getText();
		if(StringUtil.isEmpty(textField)){
			JOptionPane.showMessageDialog(null, "商品类别不能为空！");
			return;
		}
		Class cclass=new Class(textField,textArea);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int n=classDao.add(con, cclass);
			if(n==1){
				JOptionPane.showMessageDialog(null, "添加成功");
				resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "添加失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "添加失败");
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
		//private JTextField textField;
		//private JTextArea textArea ;
		this.textField.setText("");
		this.textArea.setText("");
	}
	
	
	
	
	
}
