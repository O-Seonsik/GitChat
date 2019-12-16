package ChatClient;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ChatClient.UserUI.GhostText;

class chatroomsUI extends JPanel{
	private UserUI win;
	
	GhostText ghostText;
	public static JTable chatroomTable;
	public static DefaultTableModel model;
	static public void createTable(JPanel addPanel, String userID) {
		Font font;
		JLabel myteamLabel, chatroomsIconLabel, friendsIconLabel;
		ImageIcon friendsIcon, chatroomsIcon;
		JTextField friendAdd;
		JButton logoutBtn, addBtn;
		JScrollPane scrollPane;
		Font tableFont;
		String[] columnNames = {"Chatrooms"};
		
		Object[][] data = new Object[UserUI.rooms.size()][];
		Object[] testarr = {""};
		Arrays.fill(data,testarr);
		
		model = new DefaultTableModel(data, columnNames) { 
			
			public Class getColumnClass(int column) 
				{ 
					return getValueAt(0, column).getClass(); 
				} 
			public boolean isCellEditable(int rowIndex, int mColIndex) {
	            return false;
	        }
		};
		
		
		chatroomTable = new TableBackroundPaint0(data, columnNames);
		chatroomTable.setDefaultRenderer(String.class, new CustomRenderer());
		chatroomTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		chatroomTable.setColumnSelectionAllowed(false);
		chatroomTable.setRowSelectionAllowed(true);
		chatroomTable.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		chatroomTable.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
		chatroomTable.setShowGrid(false);
		chatroomTable.setShowVerticalLines(false);
		chatroomTable.setShowHorizontalLines(false);
		
		chatroomTable.setModel(model);
		chatroomTable.setRowHeight(80);
		chatroomTable.setRowMargin(10);
		
		//값을 넣는 부분
		for(int i = 0; i < UserUI.rooms.size(); i++) chatroomTable.getModel().setValueAt(UserUI.rooms.get(i)[1], i, 0);
		
		scrollPane = new JScrollPane(chatroomTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setSize(520,700);
		scrollPane.setLocation(100,80);
		scrollPane.getViewport().setBackground(Color.white);
		addPanel.add(scrollPane, BorderLayout.CENTER);
		chatroomTable.addMouseListener(new ChatroomtableActionListener());
		
		
		ImageIcon createchatBtn_img = new ImageIcon("img/makechat.png");
	    Image resize_createchatBtn = createchatBtn_img.getImage();
	    Image changed_createchatBtn_img = resize_createchatBtn.getScaledInstance(70,70,Image.SCALE_SMOOTH);
	    ImageIcon createchatBtn_icon = new ImageIcon(changed_createchatBtn_img);
	    JLabel createchatBtn = new JLabel(createchatBtn_icon);
	    createchatBtn.setSize(70,70);
	    createchatBtn.setLocation(10, 680);
		addPanel.add(createchatBtn);
		createchatBtn.addMouseListener(new MouseListener(){
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				UserUI.win.change("createchattingUI");
				addPanel.removeAll();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
	         public void mouseEntered(MouseEvent e) {
	            // TODO Auto-generated method stub
	            ImageIcon createchatonBtn_img = new ImageIcon("img/makechat1.png");
	             Image resize_createchatonBtn = createchatonBtn_img.getImage();
	             Image changed_createchatonBtn_img = resize_createchatonBtn.getScaledInstance(70,70,Image.SCALE_SMOOTH);
	             ImageIcon createchatonBtn_icon = new ImageIcon(changed_createchatonBtn_img);
	             createchatBtn.setIcon(createchatonBtn_icon);
	         }
	         @Override
	         public void mouseExited(MouseEvent e) {
	            // TODO Auto-generated method stub
	            createchatBtn.setIcon(createchatBtn_icon);
	         }
		});
		
		friendsIcon = new ImageIcon("img/friends.png");
		Image friendsIcon_resize = friendsIcon.getImage();
		Image friendsIcon_resized = friendsIcon_resize.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		ImageIcon friendsResizedIcon = new ImageIcon(friendsIcon_resized);
		friendsIconLabel = new JLabel(friendsResizedIcon);
		friendsIconLabel.setSize(70,70);
		friendsIconLabel.setLocation(10,10);
		
		chatroomsIcon = new ImageIcon("img/chattingroom.png");
		Image chatroomsIcon_resize = chatroomsIcon.getImage();
		Image chatroomsIcon_resized = chatroomsIcon_resize.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		ImageIcon chatroomsResizedIcon = new ImageIcon(chatroomsIcon_resized);
		chatroomsIconLabel = new JLabel(chatroomsResizedIcon);
		chatroomsIconLabel.setSize(70,70);
		chatroomsIconLabel.setLocation(10,100);
		
		font = new Font("돋움", Font.PLAIN, 30);
		myteamLabel = new JLabel();
		myteamLabel.setText("My teams");
		myteamLabel.setSize(200,200);
		myteamLabel.setLocation(105,-40);
		myteamLabel.setFont(font);

		addPanel.add(myteamLabel);
		addPanel.add(friendsIconLabel);
		addPanel.add(chatroomsIconLabel);
		friendsIconLabel.addMouseListener(new MouseListener(){
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				UserUI.win.change("friendsUI");
				addPanel.removeAll();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
	         public void mouseEntered(MouseEvent e) {
	            // TODO Auto-generated method stub
	            ImageIcon friendsonIcon = new ImageIcon("img/friendson.png");
	            Image friendsonIcon_resize = friendsonIcon.getImage();
	            Image friendsonIcon_resized = friendsonIcon_resize.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	            ImageIcon friendsonResizedIcon = new ImageIcon(friendsonIcon_resized);
	            friendsIconLabel.setIcon(friendsonResizedIcon);
	         }
	         @Override
	         public void mouseExited(MouseEvent e) {
	            // TODO Auto-generated method stub
	            friendsIconLabel.setIcon(friendsResizedIcon);
	         }
		});
		chatroomsIconLabel.addMouseListener(new MouseListener(){
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
	         public void mouseEntered(MouseEvent e) {
	            // TODO Auto-generated method stub
	            ImageIcon chatroomsonIcon = new ImageIcon("img/chatroom1.png");
	            Image chatroomsonIcon_resize = chatroomsonIcon.getImage();
	            Image chatroomsonIcon_resized = chatroomsonIcon_resize.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	            ImageIcon chatroomsonResizedIcon = new ImageIcon(chatroomsonIcon_resized);
	            chatroomsIconLabel.setIcon(chatroomsonResizedIcon);
	         }
	         @Override
	         public void mouseExited(MouseEvent e) {
	            // TODO Auto-generated method stub
	            chatroomsIconLabel.setIcon(chatroomsResizedIcon);
	         }
		});
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(2f));
		g2.drawLine(100, 0, 100, 2000);
        g2.drawLine(100, 80, 3000, 80);
	}
	
	
	
	
	public chatroomsUI(UserUI win) {
		this.win = win;
		setLayout(null);
	}

	public static void recvMsg(String msg) {
		System.out.println(msg);
	}
}
class ChatroomtableActionListener extends JTable implements MouseListener {
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("click");
		JTable jtable = (JTable) e.getSource();
		// TODO Auto-generated method stub
		int row = jtable.getSelectedRow();
		int col = jtable.getSelectedColumn();
		Object value = jtable.getValueAt(row, col);
		System.out.println(row + " " + col);
		chattingUI.start(row, UserUI.rooms.get(row)[1]);
		UserUI.win.change("chattingUI");
		//아래 소스를 넣으면 바로 그거 뭐냐 깃허브 리로드 해요 
		//SendThread.sendMsg("GIT>SEND>" + UserUI.rooms.get(row)[2] + ">>>>>" + UserUI.rooms.get(row)[0]);	//url, id
		
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
