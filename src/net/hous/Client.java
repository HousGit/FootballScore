package net.hous;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.awt.Component;

public class Client extends JFrame
{
	static String url = "jdbc:mysql://qdm156187368.my3w.com:3306/qdm156187368_db";
	static String user = "qdm156187368";
	static String psd = "root55555";
	static Connection conn = null;
	static PreparedStatement pstm = null;
	static Statement  stm = null;
	static ResultSet rs = null;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Client frame = new Client();
					frame.setVisible(true);
					frame.addWindowListener(new WindowAdapter()
					{

						@Override
						public void windowClosing(WindowEvent e)
						{
							try
							{
								if (rs != null)
								{
									rs.close();
								}
								if (pstm != null)
								{
									pstm.close();
								}
								if (stm != null)
								{
									stm.close();
								}
								if (conn != null)
								{
									conn.close();
								}
								
							} catch (SQLException e1)
							{
								e1.printStackTrace();
							}
						}
					});
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client()
	{
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,psd);
			
		} catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		 
		
		
		
		
		
		
		
		
		//---------------------------------------------------------
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		JPanel panel_GM = new JPanel();
		contentPane.add(panel, "name_39138233497871");
		panel.setLayout(null);
		
		JPanel panel_SM = new JPanel();
		contentPane.add(panel_SM, "name_615937512401");
		
		JPanel panel_TM = new JPanel();
		contentPane.add(panel_TM, "name_618891652257");
		panel_TM.setLayout(null);
		
		JLabel label = new JLabel("\u7403\u961F\u7F16\u53F7");
		label.setBounds(10, 21, 54, 15);
		panel_TM.add(label);
		
		textField = new JTextField();
		textField.setBounds(63, 18, 66, 21);
		panel_TM.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u7403\u961F\u540D\u79F0");
		label_1.setBounds(135, 21, 54, 15);
		panel_TM.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(197, 18, 66, 21);
		panel_TM.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u6559\u7EC3");
		lblNewLabel.setBounds(273, 21, 42, 15);
		panel_TM.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(314, 18, 66, 21);
		panel_TM.add(textField_2);
		textField_2.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		JScrollPane JSPanel = new JScrollPane(textArea);
		JSPanel.setBounds(10, 82, 460, 309);
		panel_TM.add(JSPanel);
		
		JButton button_1 = new JButton("\u67E5\u8BE2");
		
		
		
		
		
		button_1.addActionListener(new ActionListener() {
			
//			球队查询按钮
			public void actionPerformed(ActionEvent e) {
				String str="";
				try
				{
					stm = conn.createStatement();
					rs = stm.executeQuery("select * from testTeam");
					while (rs.next())
					{
						str += "球队编号：" + rs.getString(1) + "  球队名称："
								+ rs.getString(2) + "  教练："
								+ rs.getString(3) + "\n";
					}
					textArea.setText(str);
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		button_1.setBounds(96, 49, 93, 23);
		panel_TM.add(button_1);
		
		JButton button_2 = new JButton("\u6DFB\u52A0");
		button_2.addActionListener(new ActionListener() {
			
			//球队添加按钮
			public void actionPerformed(ActionEvent e) {
				

				if (!(Pattern.matches("(\\d{1,10})", textField.getText())))
				{
					// System.out.print(textFieldID.getText());
					JOptionPane.showMessageDialog(null, "请输入正确的球队编号！");
					return;
				}
				if (!(Pattern.matches("(([\u4e00-\u9fa5]{2,128})|(\\w{1,20}))",
						textField_1.getText())))
				{
					// System.out.print(textFieldID.getText());
					JOptionPane.showMessageDialog(null, "请输入正确的球队名称！");
					return;
				}
				if (!(Pattern.matches("(([\u4e00-\u9fa5]{2,128})|\\w)",
						textField_2.getText())))
				{
					// System.out.println(textFieldsex.getText());
					JOptionPane.showMessageDialog(null, "请输入正确的教练姓名！ ");
					return;
				}
				try
				{
					pstm = conn.prepareStatement("insert into testTeam values (?,?,?);");
					pstm.setInt(1, Integer.parseInt(textField.getText()));
					pstm.setString(2, textField_1.getText());
					pstm.setString(3, textField_2.getText());
					pstm.executeUpdate();
				}catch (MySQLIntegrityConstraintViolationException e2)// 主键异常处理
				{
					textArea.setText("存储失败，该球队编号已存在！");
				} catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		button_2.setBounds(398, 17, 66, 23);
		panel_TM.add(button_2);
		
		JButton button_3 = new JButton("\u5220\u9664");
		button_3.addActionListener(new ActionListener() {
			
			//球队删除按钮
			public void actionPerformed(ActionEvent e) {
				
				if (!(Pattern.matches("(\\d{1,10})", textField.getText())))
				{
					JOptionPane.showMessageDialog(null, "请输入正确球队编号！");
					return;
				}

				try
				{
					pstm = conn
							.prepareStatement("delete  from testTeam where tID=?");
					pstm.setInt(1, Integer.parseInt(textField.getText()));
					
					int i = pstm.executeUpdate();
					if (1 == i)
					{
						textArea.setText("已删除编号为" + textField.getText()
								+ "的球队信息！");
					}
					if (0 == i)
					{
						textArea.setText("删除失败！该条信息不存在。");
					}
					// System.out.println("succeed");
				} catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		button_3.setBounds(234, 49, 93, 23);
		panel_TM.add(button_3);
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_TM.setVisible(false);
				panel.setVisible(true);
			}
		});
		btnNewButton.setBounds(371, 49, 93, 23);
		panel_TM.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("比分统计");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_SM.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(296, 58, 93, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("球队管理");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_TM.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(75, 158, 93, 23);
		panel.add(btnNewButton_2);
		
		JPanel panel_PM = new JPanel();
		contentPane.add(panel_PM, "name_88357878280210");
		panel_PM.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("球员管理");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_PM.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(296, 158, 93, 23);
		panel.add(btnNewButton_3);
		
		JButton button = new JButton("\u6BD4\u8D5B\u7BA1\u7406");
		
		Vector<String> vector = new Vector<String>();
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
			try
			{
				pstm = conn.prepareStatement("select tName from testTeam;");
				rs = pstm.executeQuery();
				while(rs.next())
				{
					vector.add(rs.getString(1));
				}
			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			
			panel.setVisible(false);
			panel_GM.setVisible(true);
			}
		});
		button.setBounds(75, 58, 93, 23);
		panel.add(button);
		
		
		contentPane.add(panel_GM, "name_39154750718673");
		panel_GM.setLayout(null);
		
		JLabel label_6 = new JLabel("\u9009\u62E9\u7403\u961F");
		label_6.setBounds(29, 34, 54, 15);
		panel_GM.add(label_6);
		
		JComboBox comboBox = new JComboBox(vector);
		comboBox.setBounds(107, 31, 78, 21);
		panel_GM.add(comboBox);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(107, 92, 78, 21);
		panel_GM.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(206, 92, 78, 21);
		panel_GM.add(comboBox_3);
		
				
		
		JComboBox comboBox_1 = new JComboBox(vector);
		comboBox_1.setBounds(206, 31, 78, 21);
		panel_GM.add(comboBox_1);
		
		JButton btnNewButton_4 = new JButton("\u786E\u5B9A");
		btnNewButton_4.addActionListener(new ActionListener() {
			
//			选择球队确认按钮
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox.getSelectedItem()==null || comboBox_1.getSelectedItem()==null || comboBox.getSelectedItem()==comboBox_1.getSelectedItem())
				{
					JOptionPane.showMessageDialog(null, "请选择正确的球队！");
					return;
				}
				
				comboBox.setEnabled(false);
				comboBox_1.setEnabled(false);
				String teamA =(String) comboBox.getSelectedItem();
				String teamB = (String) comboBox_1.getSelectedItem();
				Vector<String> vectorA = new Vector<String>();
				Vector<String> vectorB = new Vector<String>();
				
				try
				{
					pstm = conn.prepareStatement("select pName from testPlayer1 left join testTeam on tID= pTeam where  tName = ?;");
					pstm.setString(1, teamA);
					rs = pstm.executeQuery();
					vectorA.add(null);
					vectorB.add(null);
					while(rs.next())
					{
						vectorA.add(rs.getString(1));
					}
					
					
					pstm = conn.prepareStatement("select pName from testPlayer1 left join testTeam on tID= pTeam where  tName = ?;");
					pstm.setString(1, teamB);
					rs = pstm.executeQuery();
					while(rs.next())
					{
						vectorB.add(rs.getString(1));
					}
					
				} catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				
				for(int i=0; i<vectorA.size(); i++)
				{
					comboBox_2.addItem(vectorA.get(i));
				}
				

				for(int i=0; i<vectorB.size(); i++)
				{
					comboBox_3.addItem(vectorB.get(i));
				}
				
			}
		});
		btnNewButton_4.setBounds(330, 30, 62, 23);
		panel_GM.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("\u8FD4\u56DE");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			panel_GM.setVisible(false);
				panel.setVisible(true);
				
			}
		});
		btnNewButton_5.setBounds(402, 30, 62, 23);
		panel_GM.add(btnNewButton_5);
		
		JLabel label_7 = new JLabel("\u5F97\u5206\u7403\u5458");
		label_7.setBounds(29, 95, 54, 15);
		panel_GM.add(label_7);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(0, 154, 223, 247);
		panel_GM.add(textArea_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(233, 154, 223, 247);
		panel_GM.add(textArea_3);
		
		JButton button_8 = new JButton("\u8FDB\u7403\u8BA1\u5206");
		button_8.addActionListener(new ActionListener() {
			
			
//			计分按钮
			public void actionPerformed(ActionEvent e) {
				
				if((comboBox_2.getSelectedItem()== null && comboBox_3.getSelectedItem()== null)	||
						(comboBox_2.getSelectedItem()!= null && comboBox_3.getSelectedItem()!= null))
				{
					JOptionPane.showMessageDialog(null, "请选择正确的进球队员！");
				}
				String year="";
				String month="";
				String day="";
				String hour="";
				String min="";
				if(comboBox_2.getSelectedItem()!= null)
				{
					 Calendar ca = Calendar.getInstance();
					 year = Integer.toString(ca.get(Calendar.YEAR));
					 month = Integer.toString(ca.get(Calendar.MONTH));
					 day = Integer.toString(ca.get(Calendar.DAY_OF_MONTH));
					 hour = Integer.toString(ca.get(Calendar.HOUR_OF_DAY));
					 min = Integer.toString(ca.get(Calendar.MINUTE));
					 String datatime = year+"/"+month+"/"+day+"  "+hour+":"+min;
					 textArea_2.append(comboBox_2.getSelectedItem()+"进球！ "+"时间"+datatime+"\n");
					 
					 
				}
				if(comboBox_3.getSelectedItem()!= null)
				{
					 Calendar ca = Calendar.getInstance();
					 year = Integer.toString(ca.get(Calendar.YEAR));
					 month = Integer.toString(ca.get(Calendar.MONTH));
					 day = Integer.toString(ca.get(Calendar.DAY_OF_MONTH));
					 hour = Integer.toString(ca.get(Calendar.HOUR_OF_DAY));
					 min = Integer.toString(ca.get(Calendar.MINUTE));
					 String datatime = year+"/"+month+"/"+day+"  "+hour+":"+min;
					textArea_3.append(comboBox_3.getSelectedItem()+"进球！ "+"时间"+datatime+"\n");
				}
//				try
//				{ 
//					int teamA=0;
//					pstm = conn.prepareStatement ("select tID from testTeam where tName=?");
//					pstm.setString(1, (String)comboBox.getSelectedItem());
//					rs = pstm.executeQuery();
//					teamA = rs.getInt(1);
//					
//					int teamB=0;
//					pstm = conn.prepareStatement ("select tID from testTeam where tName=?");
//					pstm.setString(1, (String)comboBox_2.getSelectedItem());
//					rs = pstm.executeQuery();
//					teamB = rs.getInt(1);
//					
//					pstm = conn.prepareStatement("insert into testScore values(?,?,?,?,?);");
//					pstm.setString(1, null);
//					pstm.setInt(2, teamA);
//					pstm.setInt(3, teamB);
//					需要改表！
//					 
//					
//				} catch (SQLException e1)
//				{
//					e1.printStackTrace();
//				}
//				
				
				comboBox_2.setSelectedIndex(0);
				comboBox_3.setSelectedIndex(0);
				
				
				
			}
		});
		button_8.setBounds(348, 91, 93, 23);
		panel_GM.add(button_8);
		
		
		
		
		
		JLabel label_2 = new JLabel("\u7403\u5458\u7F16\u53F7");
		label_2.setBounds(0, 21, 54, 15);
		panel_PM.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(51, 18, 47, 21);
		textField_3.setColumns(10);
		panel_PM.add(textField_3);
		
		JLabel label_3 = new JLabel("\u6240\u5C5E\u7403\u961F ");
		label_3.setBounds(103, 21, 66, 15);
		panel_PM.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(158, 18, 47, 21);
		textField_4.setColumns(10);
		panel_PM.add(textField_4);
		
		JLabel label_4 = new JLabel("\u59D3\u540D");
		label_4.setBounds(209, 21, 47, 15);
		panel_PM.add(label_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(234, 18, 47, 21);
		textField_5.setColumns(10);
		panel_PM.add(textField_5);
		
		JTextArea textArea_1= new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea_1);
		scrollPane.setBounds(10, 82, 460, 309);
		panel_PM.add(scrollPane);
		
		JButton button_4 = new JButton("\u67E5\u8BE2");
		button_4.addActionListener(new ActionListener() {
			
//			球员查询按钮
			public void actionPerformed(ActionEvent e) {
				String str="";
				try
				{
					
					stm = conn.createStatement();
					rs = stm.executeQuery("select * from testPlayer1");
					while (rs.next())
					{
						str += "球员编号：" + rs.getString(1) + "  所属球队："
								+ rs.getString(3) + "  姓名："
								+ rs.getString(2) +  "  球衣号码："+ rs.getString(4) +"\n";
					}
					textArea_1.setText(str);
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button_4.setBounds(96, 49, 93, 23);
		panel_PM.add(button_4);
		
		JButton button_5 = new JButton("\u6DFB\u52A0");
		button_5.setBounds(398, 17, 66, 23);
		button_5.addActionListener(new ActionListener() {
			
//			球员添加按钮
			public void actionPerformed(ActionEvent e) {
				
				if (!(Pattern.matches("(\\d{1,10})", textField_3.getText())))
				{
					// System.out.print(textFieldID.getText());
					JOptionPane.showMessageDialog(null, "请输入正确的球员编号！");
					return;
				}
				
				
//				可改进！！
				if (!(Pattern.matches("(\\d{1,10})", textField_4.getText())))
				{
					JOptionPane.showMessageDialog(null, "请输入正确的球队编号！");
					return;
				}
				if (!(Pattern.matches("(([\u4e00-\u9fa5]{2,128})|\\w)",
						textField_5.getText())))
				{
					JOptionPane.showMessageDialog(null, "请输入正确的球员姓名！ ");
					return;
				}
				if (!(Pattern.matches("(\\d{1,10})", textField_6.getText())))
				{
					JOptionPane.showMessageDialog(null, "请输入正确的球衣号码！");
					return;
				}
				try
				{
					pstm = conn.prepareStatement("insert into testPlayer1 values (?,?,?,?);");
					pstm.setInt(1, Integer.parseInt(textField_3.getText()));
					pstm.setString(2, textField_5.getText());
					pstm.setString(3, textField_4.getText());
					pstm.setString(4, textField_6.getText());
					pstm.executeUpdate();
				}catch (MySQLIntegrityConstraintViolationException e2)// 主键异常处理
				{
					textArea.setText("存储失败，球员编号重复！");
				} catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		panel_PM.add(button_5);
		
		JButton button_6 = new JButton("\u5220\u9664");
		button_6.addActionListener(new ActionListener() {
			
//			 球员删除按钮
			public void actionPerformed(ActionEvent e) {
				
				if (!(Pattern.matches("(\\d{1,10})", textField_3.getText())))
				{
					JOptionPane.showMessageDialog(null, "请输入正确球员编号！");
					return;
				}

				try
				{
					pstm = conn
							.prepareStatement("delete  from testPlayer1 where pID=?");
					pstm.setInt(1, Integer.parseInt(textField_3.getText()));
					
					int i = pstm.executeUpdate();
					if (1 == i)
					{
						textArea_1.setText("已删除编号为" + textField_3.getText()
								+ "的球队信息！");
					}
					if (0 == i)
					{
						textArea_1.setText("删除失败！该条信息不存在。");
					}
					// System.out.println("succeed");
				} catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		button_6.setBounds(234, 49, 93, 23);
		panel_PM.add(button_6);
		
		JButton button_7 = new JButton("\u8FD4\u56DE");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_PM.setVisible(false);
				panel.setVisible(true);
			}
		});
		button_7.setBounds(371, 49, 93, 23);
		panel_PM.add(button_7);
		
		textField_6 = new JTextField();
		textField_6.setBounds(341, 18, 47, 21);
		textField_6.setColumns(10);
		panel_PM.add(textField_6);
		
		JLabel label_5 = new JLabel("\u7403\u8863\u53F7\u7801");
		label_5.setBounds(289, 21, 54, 15);
		panel_PM.add(label_5);
		
		
		
		
		//——————————————————————————
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
