package com.sms.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;

import com.sms.dao.ClassDao;
import com.sms.model.Class;
import com.sms.util.DbUtil;
import com.sms.util.StringUtil;
import java.awt.event.MouseAdapter;

public class ClassManageInterFrm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
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
					ClassManageInterFrm frame = new ClassManageInterFrm();
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
	public ClassManageInterFrm() {
		setFrameIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\categories_inventory_16px.png"));
		setClosable(true);
		setIconifiable(true);
		setTitle("商品类别维护");
		setBounds(100, 100, 319, 433);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("商品类别名称：");
		label.setBounds(21, 31, 93, 15);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(114, 28, 79, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				classSearchActionPerform(e);
				
				
				
				
			}
		});
		button.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\search_16px.png"));
		button.setBounds(203, 27, 79, 23);
		getContentPane().add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 60, 262, 115);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				
				classTableMousePressed(e);
				
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5E8F\u53F7", "ID", "\u540D\u79F0", "\u63CF\u8FF0"
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u7C7B\u522B\u7EF4\u62A4", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 185, 262, 202);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblid = new JLabel("ID：");
		lblid.setBounds(24, 38, 65, 15);
		panel.add(lblid);
		
		textField_1 = new JTextField();
		textField_1.setBounds(55, 35, 66, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("名称：");
		label_1.setBounds(131, 38, 71, 15);
		panel.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(175, 35, 66, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_2 = new JLabel("描述：");
		label_2.setBounds(24, 78, 54, 15);
		panel.add(label_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(55, 78, 186, 77);
		panel.add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane_1.setViewportView(textArea);
		
		JButton button_1 = new JButton("修改");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				classUpdateActionEvent( e);
				
				
				
				
				
				
			}
			
		});
		button_1.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\modify_16px.png"));
		button_1.setBounds(24, 165, 93, 23);
		panel.add(button_1);
		
		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				classDeleteActionEvent( e);
				
				
				
				
			}
		});
		button_2.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\delete_16px.png"));
		button_2.setBounds(148, 165, 93, 23);
		panel.add(button_2);

		
		fillTable(new Class());
	}
	
	
	
	
	
/*	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextArea textArea ;*/
	
	
	
	

	protected void classTableMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row=table.getSelectedRow();
		textField_1.setText((String)table.getValueAt(row, 1));
		textField_2.setText((String)table.getValueAt(row,2));
		textArea.setText((String)table.getValueAt(row,3));
	}

	protected void classSearchActionPerform(ActionEvent e) {
		// TODO Auto-generated method stub
		String textField = this.textField.getText();
		Class cclass = new Class();
		cclass.setClass_name(textField);
		this.fillTable(cclass);
	}

	private void fillTable(Class cclass){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = classDao.list(con, cclass);
			int i=1;
			while(rs.next()){
				Vector<String> v=new Vector<String>();
				v.add(""+ i++);
				v.add(""+rs.getInt("class_id"));
				v.add(rs.getString("class_name"));
				v.add(rs.getString("class_remark"));
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
	

	
/*	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextArea textArea ;*/
	
	/**
	 * 宠物类别修改事件处理
	 * @param e
	 */
	private void classUpdateActionEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		String idstr=textField_1.getText();
		int class_id=Integer.parseInt(idstr);
		String class_name=textField_2.getText();
		String class_remark=textArea.getText();
		if(StringUtil.isEmpty(idstr)){
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return;
		}
		if(StringUtil.isEmpty(class_name)){
			JOptionPane.showMessageDialog(null, "宠物类别名称不能为空");
			return;
		}
		Class cclass = new Class(class_id,class_name,class_remark);
		Connection con = null;
		try{
			con=dbUtil.getCon();
			int modifyNum=classDao.update(con, cclass);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetValue();
				this.fillTable(new Class());
			}else{
				JOptionPane.showMessageDialog(null, "修改失败");
			}
		}catch (Exception e2) {
			e2.printStackTrace();

			JOptionPane.showMessageDialog(null, "修改失败");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
/*	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextArea textArea ;*/
	/**
	 * 图书类别删除事件处理
	 * @param e
	 */
	private void classDeleteActionEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		String idstr=textField_1.getText();
		if(StringUtil.isEmpty(idstr)){
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if(n==0){
			Connection con=null;
			try {
				con=dbUtil.getCon();
				int deleteNum=classDao.delete(con,idstr);
				if(deleteNum==1){
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillTable(new Class());
					
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
/*	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextArea textArea ;*/

	private void resetValue() {
		// TODO Auto-generated method stub
		this.textField_1.setText("");
		this.textField_2.setText("");
		this.textArea.setText("");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
