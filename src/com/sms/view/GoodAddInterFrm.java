package com.sms.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.sms.dao.ClassDao;
import com.sms.dao.GoodDao;
import com.sms.model.Class;
import com.sms.model.Good;
import com.sms.util.DbUtil;
import com.sms.util.StringUtil;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GoodAddInterFrm extends JInternalFrame {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public int i=0;
	
	private JTextField m_id_text;
	private JTextField m_name_text;
	private JTextField m_place_text;
	private JTextField m_price_Text;
	private JTable table;
	private JTextField m_cost_text;
	private JTextField m_hot_text;
	private JTextArea m_descrip_area;
	private JLabel m_picture_lable;
	private JComboBox<Class> m_class_jcb;
	private DefaultTableModel dtm2;
	GoodDao goodDao = new GoodDao();
	ClassDao classDao = new ClassDao();
	DbUtil dbUtil = new DbUtil();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodAddInterFrm frame = new GoodAddInterFrm();
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
	public GoodAddInterFrm() {
		setFrameIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\categories_inventory_16px.png"));
		setTitle("商品添加");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 413, 450);
		getContentPane().setLayout(null);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u5BA2\u6237\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 10, 377, 397);
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
		
		m_price_Text = new JTextField();
		m_price_Text.setBounds(267, 152, 66, 21);
		panel_1.add(m_price_Text);
		m_price_Text.setColumns(10);
		
		JLabel label_5 = new JLabel("商品分类：");
		label_5.setBounds(10, 206, 80, 15);
		panel_1.add(label_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 266, 116, 72);
		panel_1.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
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
		scrollPane_1.setViewportView(table);
		
		JLabel label_6 = new JLabel("商品成本：");
		label_6.setBounds(186, 193, 80, 15);
		panel_1.add(label_6);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(267, 266, 85, 72);
		panel_1.add(scrollPane_2);
		
		m_descrip_area = new JTextArea();
		m_descrip_area.setLineWrap(true);
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
		
		JButton button_3 = new JButton("添加");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				goodAddActionPerformed(e);
				
				
				
				
				
			}
		});
		button_3.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\add_16px.png"));
		button_3.setBounds(140, 356, 80, 23);
		panel_1.add(button_3);
		
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
		
		m_class_jcb = new JComboBox<Class>();

		m_class_jcb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				
				
				Class cclass = (Class)m_class_jcb.getSelectedItem();
				if(cclass.getClass_name().equals("请选择"))
					return;
				System.out.println(cclass.getClass_name());
				dtm2=(DefaultTableModel) table.getModel();
				for(int j=0;j<i;j++) {
					if(dtm2.getValueAt(j, 0).equals(cclass.getClass_name()))
						return;
				}
				dtm2.setRowCount(i);//设置成i行
				i++;
				Vector<String> v=new Vector<String>();
				v.add(cclass.getClass_name());
				dtm2.addRow(v);
			}
		});
	
	
		m_class_jcb.setBounds(50, 231, 116, 19);
		panel_1.add(m_class_jcb);
		
		fillClassJCB();
		
		
	}
	
	
	
/*	
 *	private JTextField m_id_text;
	private JTextField m_name_text;
	private JTextField m_place_text;
	private JTextField m_price_Text;
	private JTable table;
	private JTextField m_cost_text;
	private JTextField m_hot_text;
	private JTextArea m_descrip_area;
	private JLabel m_picture_lable;
 * 
 * int good_id;
	String good_name;
	int good_cost;
	int good_price;
	String good_place;
	int good_hot;
	String good_descrip;
	ImageIcon good_picture;
	
	
	
	
	
	
	
	
	
	int good_id;
	String good_name;
	String good_descrip;
	int good_cost;
	int good_price;
	String good_place;
	ImageIcon good_picture;
	int good_hot;
	*/

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

	protected void goodAddActionPerformed(ActionEvent evt) {
		String idstr=this.m_id_text.getText();
		String namestr=this.m_name_text.getText();
		String placestr=this.m_place_text.getText();
		String pricestr=this.m_price_Text.getText();
		String coststr=this.m_cost_text.getText();
		String hotstr=this.m_hot_text.getText();
		String descripstr=this.m_descrip_area.getText();
		ImageIcon good_picture=null;
		int good_id = 0;
		String good_place="";
		if(StringUtil.isNotEmpty(idstr)) {
			good_id=Integer.parseInt(idstr);
		}
		
		String good_name=namestr;
		String good_descrip=descripstr;
		int good_cost=Integer.parseInt(coststr);
		int good_price=Integer.parseInt(pricestr);
		if(StringUtil.isNotEmpty(placestr)) {
			good_place=placestr;
		}
		
		good_picture=(ImageIcon) m_picture_lable.getIcon();;
		int good_hot=Integer.parseInt(hotstr);
		
		
		if(StringUtil.isEmpty(namestr)){
			JOptionPane.showMessageDialog(null, "商品名称不能为空！");
			return;
		}if(StringUtil.isEmpty(pricestr)){
			JOptionPane.showMessageDialog(null, "商品价格不能为空！");
			return;
		}if(StringUtil.isEmpty(coststr)){
			JOptionPane.showMessageDialog(null, "商品成本不能为空！");
			return;
		}
		Good good=new Good(good_id,good_name,good_descrip,good_cost,good_price,good_place,good_picture,good_hot);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			
			int n=goodDao.add(con, good);
			
			List<String> list = new ArrayList<String>();
			for(int j=0;j<i;j++) {
				list.add((String)dtm2.getValueAt(j, 0));
			}
			
			
			int cishu = goodDao.addClass(con,list,goodDao.getGoodIdByName(con, good.getGood_name()));
			
			if(cishu == i) {
				System.out.println("添加类别——商品成功");
			}

			if(n==1){
				JOptionPane.showMessageDialog(null, "添加成功");
				resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "添加失败111111");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "添加失败22222");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*	
	 *	private JTextField m_id_text;
		private JTextField m_name_text;
		private JTextField m_place_text;
		private JTextField m_price_Text;
		private JTable table;
		private JTextField m_cost_text;
		private JTextField m_hot_text;
		private JTextArea m_descrip_area;
		private JLabel m_picture_lable;
	 */
	private void resetValue() {
		// TODO Auto-generated method stub
		//private JTextField textField;
		//private JTextArea textArea ;
		this.m_id_text.setText("");
		this.m_name_text.setText("");
		this.m_place_text.setText("");
		this.m_price_Text.setText("");
		this.m_cost_text.setText("");
		this.m_hot_text.setText("");
		this.m_descrip_area.setText("");
		this.m_picture_lable.setIcon(new ImageIcon("E:\\程序试做\\软件开发综合实践\\SuperMicroShop\\image\\good.png"));
		this.m_id_text.setText("");
		this.dtm2.setRowCount(0);
	}
	
	private void fillClassJCB(){
		Connection con = null;
		Class cclass = new Class();
		ClassDao classDao = new ClassDao();
		try{
			con = dbUtil.getCon();
			
			Class cclass2 = new Class();
			cclass2.setClass_id(-1);
			cclass2.setClass_name("请选择");
			this.m_class_jcb.addItem(cclass2);
			
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
}
