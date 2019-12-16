package ChatClient;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ChatClient.UserUI.GhostText;

class createchattingUI extends JPanel{
	private UserUI win;
	private Font font;
	private JLabel myteamLabel, chatroomsIconLabel, friendsIconLabel;
	private ImageIcon friendsIcon, chatroomsIcon;
	private JTextField friendAdd;
	private JButton logoutBtn, addBtn;
	
	public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(2f));
        //g2.drawLine(100, 0, 100, 2000);
        g2.drawLine(0, 80, 3000, 80);
	}
	public static void createTable(JPanel addPanel, String userID) {
		
		JScrollPane scrollPane;
		Font tableFont;
		JTable friendsTable;
		JTextField chatTitle;
        GhostText ghostText;
        
		String[] columnNames = { "Name", "ID", "check"};
		ArrayList<String[]> friendarr = DBA.getFriend(userID);
		Object[][] data = new Object[friendarr.size()][];
		
		Object[] testarr = {"","", Boolean.FALSE};
		Arrays.fill(data,testarr);
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames) { 
			public Class<?> getColumnClass(int column) 
				{ 
					switch(column)
					{
					case 0:
						return String.class;
					case 1:
						return String.class;
					case 2:
						return Boolean.class;
					
						default:
							return String.class;
					}
				} 
		};
		chatTitle = new JTextField();
        chatTitle.setLocation(105,15);
        chatTitle.setSize(400,30);
        //chatTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        ghostText = new GhostText(chatTitle, "Please Insert chatroom title");
        addPanel.add(chatTitle);
        
		friendsTable = new TableBackroundPaint0(data, columnNames);
		friendsTable.setDefaultRenderer(String.class, new CustomRenderer());
		friendsTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		friendsTable.setColumnSelectionAllowed(false);
		friendsTable.setRowSelectionAllowed(true);
		friendsTable.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		friendsTable.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
		friendsTable.setShowGrid(false);
		friendsTable.setShowVerticalLines(false);
		friendsTable.setShowHorizontalLines(false);
		
		friendsTable.setModel(model);
		friendsTable.setRowHeight(80);
		friendsTable.setRowMargin(10);
		
		for(int i = 0; i < friendarr.size(); i++) {
			friendsTable.getModel().setValueAt(friendarr.get(i)[1], i, 0);
			friendsTable.getModel().setValueAt(friendarr.get(i)[0], i, 1);
		}
		JCheckBox checkbox = new JCheckBox("New check box");
        scrollPane = new JScrollPane(friendsTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setColumnHeaderView(checkbox);
        scrollPane.setSize(620,700);
        scrollPane.setLocation(0,80);
        scrollPane.getViewport().setBackground(Color.white);;
		addPanel.add(scrollPane, BorderLayout.CENTER);
		
		ImageIcon create_img = new ImageIcon("img/friendpuls.png");
	    Image resize_create = create_img.getImage();
	    Image changed_create_img = resize_create.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	    ImageIcon create_icon = new ImageIcon(changed_create_img);
	   
		JLabel createBtn = new JLabel(create_icon);
		createBtn.setLocation(520,15);
        createBtn.setSize(30,30);
		addPanel.add(createBtn);
		
		createBtn.addMouseListener(new MouseListener(){
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				ArrayList<String> friendList = new ArrayList<>();
				friendList.add(UserUI.userid);
				if (!chatTitle.getText().replaceAll(" ", "").equals("")&& !chatTitle.getText().equals("Please Insert chatroom title")) {
                    //chatTitle이 채팅방 이름입니다.
					for(int i = 0; i < friendsTable.getRowCount(); i++) {
						//Dear 선식 : 여기가 체크된 친구들을 뽑는 소스 입니다.
						Boolean checked = Boolean.valueOf(friendsTable.getValueAt(i, 2).toString());
						String col = friendsTable.getValueAt(i, 1).toString();
						if(checked) friendList.add((String) friendsTable.getValueAt(i, 1));
					}
					DBA.addRoom(chatTitle.getText(), friendList.toArray(new String[friendList.size()]));
					
					addPanel.removeAll();
					UserUI.win.change("chatroomsUI");
				}else {
					UIManager UI=new UIManager();
			        UI.put("OptionPane.background",new ColorUIResource(255,255,255));
			        UI.put("Panel.background",new ColorUIResource(255,255,255));
					final ImageIcon icon = new ImageIcon("img/a.png");
			        JOptionPane.showMessageDialog(null, "채팅방 제목을 입력해주세요 ><", "Info", JOptionPane.INFORMATION_MESSAGE, icon);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
	         public void mouseEntered(MouseEvent e) {
	            // TODO Auto-generated method stub
	            ImageIcon createchatonBtn_img = new ImageIcon("img/friendpulson.png");
	             Image resize_createchatonBtn = createchatonBtn_img.getImage();
	             Image changed_createchatonBtn_img = resize_createchatonBtn.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	             ImageIcon createchatonBtn_icon = new ImageIcon(changed_createchatonBtn_img);
	             createBtn.setIcon(createchatonBtn_icon);
	         }
	         @Override
	         public void mouseExited(MouseEvent e) {
	            // TODO Auto-generated method stub
	        	 createBtn.setIcon(create_icon);
	         }
		});
		
		
		
		
		
		ImageIcon out_img = new ImageIcon("img/back.png");
        Image resize_out = out_img.getImage();
        Image changed_out_img = resize_out.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        ImageIcon out_icon = new ImageIcon(changed_out_img);
		JLabel btnOut = new JLabel(out_icon);
        btnOut.setLocation(0,0);
        btnOut.setSize(50,50);
        addPanel.add(btnOut);
        btnOut.addMouseListener(new MouseListener(){
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				UserUI.win.change("chatroomsUI");
                addPanel.removeAll();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				ImageIcon outon_img = new ImageIcon("img/backon.png");
		        Image resize_outon = outon_img.getImage();
		        Image changed_outon_img = resize_outon.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		        ImageIcon outon_icon = new ImageIcon(changed_outon_img);
		        btnOut.setIcon(outon_icon);
		        
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnOut.setIcon(out_icon);
			}
		});
        

	}

	public createchattingUI(UserUI win) {
		this.win = win;
		setLayout(null);
		setBackground(Color.WHITE);
		
	}

	
	class FriendsActionListener implements MouseListener{
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			win.change("friendsUI");
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class ChatroomsActionListener implements MouseListener {
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			win.change("chatroomsUI");
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	public static void recvMsg(String msg) {
		System.out.println(msg);
	}
}