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
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import com.sms.dao.ClassDao;
import com.sms.dao.GoodDao;
import com.sms.model.Class;
import com.sms.model.Good;
import com.sms.util.DbUtil;
import com.sms.util.StringUtil;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class GoodManageInterFrm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField s_name_text;
	private JTextField s_place_text;
	private JComboBox<Class> s_class_jcb ;
	private JTable table;
	private JTextField m_id_text;
	private JTextField m_name_text;
	private JTextField m_place_text;
	private JTextField m_price_text;
	private JTable table_1;
	private JTextField m_cost_text;
	private JTextField m_hot_text;
	private JTextArea m_descrip_area ;
	private JLabel m_picture_lable;
	GoodDao goodDao = new GoodDao();
	DbUtil dbUtil = new DbUtil();
	private JTable table_2;
	private JComboBox<Class> m_class_jcb ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodManageInterFrm frame = new GoodManageInterFrm();
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
	public GoodManageInterFrm() {
		setFrameIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Goods_16px.png"));
		setClosable(true);
		setIconifiable(true);
		setToolTipText("");
		setTitle("商品信息维护面板");
		setBounds(100, 100, 800, 450);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 377, 132);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("商品名称：");
		label.setBounds(26, 32, 80, 15);
		panel.add(label);
		
		s_name_text = new JTextField();
		s_name_text.setBounds(100, 29, 66, 21);
		panel.add(s_name_text);
		s_name_text.setColumns(10);
		
		JLabel label_1 = new JLabel("商品出售地：");
		label_1.setBounds(202, 32, 86, 15);
		panel.add(label_1);
		
		s_place_text = new JTextField();
		s_place_text.setBounds(282, 29, 66, 21);
		panel.add(s_place_text);
		s_place_text.setColumns(10);
		
		JButton button = new JButton("查找");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				
				goodSearchActionPerform(e);
				
				
				
				
				
				
				
			}
		});
		button.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\search_16px.png"));
		button.setBounds(139, 92, 93, 23);
		panel.add(button);
		
		JLabel label_7 = new JLabel("商品类别：");
		label_7.setBounds(26, 67, 80, 15);
		panel.add(label_7);
		
		s_class_jcb = new JComboBox<Class>();
		s_class_jcb.setToolTipText("");
		s_class_jcb.setBounds(100, 63, 66, 19);
		panel.add(s_class_jcb);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 152, 377, 255);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				
				
				
				goodTableMousePressed(e);
				
				
				
				
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u540D\u79F0", "\u51FA\u552E\u5730", "\u63CF\u8FF0"
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u5546\u54C1\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(397, 10, 377, 397);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(25, 27, 141, 134);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		m_picture_lable = new JLabel("");
		m_picture_lable.setHorizontalAlignment(SwingConstants.CENTER);
		m_picture_lable.setBounds(0, 0, 140, 140);
		panel_2.add(m_picture_lable);
		m_picture_lable.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\good.png"));
		
		JLabel lblid = new JLabel("商品编号：");
		lblid.setBounds(186, 41, 80, 15);
		panel_1.add(lblid);
		
		m_id_text = new JTextField();
		m_id_text.setBounds(267, 38, 66, 21);
		panel_1.add(m_id_text);
		m_id_text.setColumns(10);
		
		JLabel label_2 = new JLabel("商品名称：");
		label_2.setBounds(186, 79, 80, 15);
		panel_1.add(label_2);
		
		m_name_text = new JTextField();
		m_name_text.setBounds(267, 76, 66, 21);
		panel_1.add(m_name_text);
		m_name_text.setColumns(10);
		
		JLabel label_3 = new JLabel("商品出售地：");
		label_3.setBounds(186, 117, 80, 15);
		panel_1.add(label_3);
		
		m_place_text = new JTextField();
		m_place_text.setBounds(267, 114, 66, 21);
		panel_1.add(m_place_text);
		m_place_text.setColumns(10);
		
		JLabel label_4 = new JLabel("商品价格：");
		label_4.setBounds(186, 155, 80, 15);
		panel_1.add(label_4);
		
		m_price_text = new JTextField();
		m_price_text.setBounds(267, 152, 66, 21);
		panel_1.add(m_price_text);
		m_price_text.setColumns(10);
		
		JLabel label_5 = new JLabel("商品分类：");
		label_5.setBounds(10, 206, 80, 15);
		panel_1.add(label_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 226, 80, 112);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7C7B\u522B"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		JLabel label_6 = new JLabel("商品成本：");
		label_6.setBounds(186, 193, 80, 15);
		panel_1.add(label_6);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(267, 266, 100, 72);
		panel_1.add(scrollPane_2);
		
		m_descrip_area = new JTextArea();
		m_descrip_area.setLineWrap(true);
		m_descrip_area.setWrapStyleWord(true);
		scrollPane_2.setViewportView(m_descrip_area);
		
		JButton button_2 = new JButton("上传图片");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				uploadPhoto();
				
				
				
				
				
				
			}
		});
		button_2.setHorizontalAlignment(SwingConstants.LEFT);
		button_2.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\Upload_16px.png"));
		button_2.setBounds(40, 173, 110, 23);
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("修改");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				
				
				
				
				try {
					goodUpdateActionEvent( e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
				
				
			}
		});
		button_3.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\modify_16px.png"));
		button_3.setBounds(86, 356, 80, 23);
		panel_1.add(button_3);
		
		JButton button_4 = new JButton("删除");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				
				
				
				
				goodDeleteActionEvent(e);
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		button_4.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\delete_16px.png"));
		button_4.setBounds(222, 356, 80, 23);
		panel_1.add(button_4);
		
		m_cost_text = new JTextField();
		m_cost_text.setBounds(267, 190, 66, 21);
		panel_1.add(m_cost_text);
		m_cost_text.setColumns(10);
		
		JLabel label_8 = new JLabel("热门商品：");
		label_8.setBounds(186, 231, 80, 15);
		panel_1.add(label_8);
		
		m_hot_text = new JTextField();
		m_hot_text.setColumns(10);
		m_hot_text.setBounds(267, 228, 66, 21);
		panel_1.add(m_hot_text);
		
		JLabel label_9 = new JLabel("商品描述：");
		label_9.setBounds(186, 269, 80, 15);
		panel_1.add(label_9);
		
		JLabel label_10 = new JLabel("新增类别：");
		label_10.setBounds(86, 206, 80, 15);
		panel_1.add(label_10);
		
		m_class_jcb = new JComboBox<Class>();
		m_class_jcb.setBounds(96, 229, 80, 19);
		panel_1.add(m_class_jcb);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(96, 250, 80, 88);
		panel_1.add(scrollPane_3);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u65B0\u589E\u7C7B\u522B"
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
		scrollPane_3.setViewportView(table_2);

		fillClassJCB();
		fillTable(new Good());
		fillNewClassJCB();
	}
	
	

	
	
	
	
	
	
	protected void goodSearchActionPerform(ActionEvent e) {
		// TODO Auto-generated method stub
		//String s_id_text = this.s_id_text.getText();
		String s_name_text = this.s_name_text.getText();
		String s_place_text = this.s_place_text.getText();
		String s_class_jcb = ((Class)this.s_class_jcb.getSelectedItem()).getClass_name();
		
		Good good = new Good(s_name_text,s_place_text);
		this.fillTable(good,s_class_jcb);
	}

	protected void goodTableMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		Connection con=null;
		
		try {
			con=dbUtil.getCon();

			int row=table.getSelectedRow();
			
			String id = (String)table.getValueAt(row, 0);
			
			Good good = goodDao.searchByID(con,id);
			m_picture_lable.setIcon(good.getGood_picture());
			m_id_text.setText(""+good.getGood_id());
			m_name_text.setText(good.getGood_name());
			m_place_text.setText(""+good.getGood_place());
			m_price_text.setText(""+good.getGood_price());
			m_cost_text.setText(""+good.getGood_cost());
			m_hot_text.setText(""+goodDao.isHot(con, good));
			m_descrip_area.setText(""+good.getGood_descrip());

			this.fillClassTable(good);
	
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
	
	/*	
	 * 
	 * 
	 * 
int good_id;
String good_name;
String good_descrip;
int good_cost;
int good_price;
String good_place;
ImageIcon good_picture;
int good_hot;



	
private JTextField s_name_text;
private JTextField s_place_text;
private JComboBox<Class> s_class_jcb ;
private JTable table;
private JTextField m_id_text;
private JTextField m_name_text;
private JTextField m_place_text;
private JTextField m_price_text;
private JTable table_1;
private JTextField m_cost_text;
private JTextField m_hot_text;
private JTextArea m_descrip_area ;
private JLabel m_picture_lable;
	*/

	private void fillTable(Good good, String cclass){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = goodDao.list(con, good, cclass);
			//int i=1;
			while(rs.next()){
				Vector<String> v=new Vector<String>();
				//v.add(""+ i++);
				v.add(""+rs.getInt("good_id"));
				v.add(rs.getString("good_name"));
				v.add(""+rs.getString("good_place"));
				v.add(""+rs.getString("good_descrip"));
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
	
	private void fillTable(Good good){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = goodDao.list(con, good);
			//int i=1;
			while(rs.next()){
				Vector<String> v=new Vector<String>();
				//v.add(""+ i++);
				v.add(""+rs.getInt("good_id"));
				v.add(rs.getString("good_name"));
				v.add(""+rs.getString("good_place"));
				v.add(""+rs.getString("good_descrip"));
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


	private void goodUpdateActionEvent(ActionEvent e) throws SQLException, Exception {
		// TODO Auto-generated method stub

		//int good_id;
		String good_name;
		String good_place;
		int good_price;
		int good_cost;
		int good_hot;
		
		String good_descrip;
		ImageIcon good_photo ;
		
		
		String idstr=m_id_text.getText();
		String namestr=m_name_text.getText();
		String placestr=m_place_text.getText();
		String pricestr=m_price_text.getText();
		String coststr=m_cost_text.getText();
		String hotstr=m_hot_text.getText();
		String descripstr=m_descrip_area.getText();
		
		//good_id=Integer.parseInt(idstr);
		good_name=namestr;
		
		good_place=placestr;
		good_price=Integer.parseInt(pricestr);
		
		good_cost=Integer.parseInt(coststr);
		
		if(hotstr.equals("是")) {
			good_hot=1;
		}else {

			good_hot=0;
		}
		
		good_descrip=descripstr;
		good_photo=(ImageIcon) m_picture_lable.getIcon();
		
						
		if(StringUtil.isEmpty(idstr)){
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return;
		}
		if(StringUtil.isEmpty(namestr)){
			JOptionPane.showMessageDialog(null, "商品名称不能为空");
			return;
		}
		if(StringUtil.isEmpty(pricestr)){
			JOptionPane.showMessageDialog(null, "商品价格不能为空");
			return;
		}
		if(StringUtil.isEmpty(coststr)){
			JOptionPane.showMessageDialog(null, "商品成本不能为空");
			return;
		}
		Connection con = null;
		try{
			con=dbUtil.getCon();
			
			Good good ;
			good=goodDao.searchByID(con, idstr);
			good.setGood_name(good_name);
			good.setGood_place(good_place);
			good.setGood_price(good_price);
			good.setGood_cost(good_cost);
			good.setGood_hot(good_hot);
			good.setGood_descrip(good_descrip);
			good.setGood_picture(good_photo);
			
			
			
			
			int modifyNum=goodDao.update(con, good);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetValue();
				this.fillTable(new Good());
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
	
	
	
	private void goodDeleteActionEvent(ActionEvent e) {
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
				int deleteNum=goodDao.delete(con,idstr);
				if(deleteNum==1){
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillTable(new Good());
					
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

	/*	
	 * 
	 * 
	 * 
int good_id;
String good_name;
String good_descrip;
int good_cost;
int good_price;
String good_place;
ImageIcon good_picture;
int good_hot;



	
private JTextField s_name_text;
private JTextField s_place_text;
private JComboBox<Class> s_class_jcb ;
private JTable table;
private JTextField m_id_text;
private JTextField m_name_text;
private JTextField m_place_text;
private JTextField m_price_text;
private JTable table_1;
private JTextField m_cost_text;
private JTextField m_hot_text;
private JTextArea m_descrip_area ;
private JLabel m_picture_lable;
	*/


	private void resetValue() {
		// TODO Auto-generated method stub
		this.m_picture_lable.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\good.png"));
		this.m_id_text.setText("");
		this.m_name_text.setText("");
		this.m_place_text.setText("");
		this.m_price_text.setText("");
		this.m_cost_text.setText("");
		this.m_hot_text.setText("");
		this.m_descrip_area.setText("");
		
		this.fillClassTable(new Good());
		this.fillTable(new Good());
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
	
	
	private void fillClassJCB(){
		Connection con = null;
		Class cclass = new Class();
		ClassDao classDao = new ClassDao();
		try{
			con = dbUtil.getCon();
			ResultSet rs = classDao.list(con, new Class());
		    while( rs.next() ){
		    	cclass = new Class();
		    	cclass.setClass_id(rs.getInt("class_id"));
		    	cclass.setClass_name(rs.getString("class_name"));
		    	cclass.setClass_remark(rs.getString("class_remark"));
		    	this.s_class_jcb.addItem(cclass);
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
	
	
	private void fillNewClassJCB(){
		Connection con = null;
		Class cclass = new Class();
		ClassDao classDao = new ClassDao();
		try{
			con = dbUtil.getCon();
			ResultSet rs = classDao.list(con, new Class());
		    while( rs.next() ){
		    	cclass = new Class();
		    	cclass.setClass_id(rs.getInt("class_id"));
		    	cclass.setClass_name(rs.getString("class_name"));
		    	cclass.setClass_remark(rs.getString("class_remark"));
		    	this.m_class_jcb.addItem(cclass);
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

	private void fillClassTable(Good good){
		
		DefaultTableModel dtm=(DefaultTableModel) table_1.getModel();
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
	
	
}
