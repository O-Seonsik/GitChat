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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ChatClient.UserUI.GhostText;

class friendsUI extends JPanel{
	private UserUI win;
	static JTextField friendAdd;
	static GhostText ghostText;
	public static JTable friendsTable;
	public static DefaultTableModel model;
	public static ArrayList<String[]> friendarr;
	private static Icon logoResizedIcon;
	public static Icon statonResizedIcon;
	public static Icon statoffResizedIcon;
	
	public static void createTable(JPanel addPanel, String userID) {
		Font font;
		JLabel myteamLabel, chatroomsIconLabel, friendsIconLabel;
		ImageIcon friendsIcon, chatroomsIcon;
		
		
		JScrollPane scrollPane;
		Font tableFont;
		String[] columnNames = {"My Profile", "Name", "ID", "on/off"};
		friendarr = DBA.getFriend(userID);
		Object[][] data = new Object[friendarr.size()][];
		Object[] testarr = {"","","",""};
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
		
		
		ImageIcon logoIcon = new ImageIcon("img/logo.png");
		Image logoIcon_resize = logoIcon.getImage();
		Image logoIcon_resized = logoIcon_resize.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		logoResizedIcon = new ImageIcon(logoIcon_resized);
		
		ImageIcon statonIcon = new ImageIcon("img/staton.png");
		Image statonIcon_resize = statonIcon.getImage();
		Image statonIcon_resized = statonIcon_resize.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		statonResizedIcon = new ImageIcon(statonIcon_resized);
		
		ImageIcon statoffIcon = new ImageIcon("img/statoff.png");
		Image statoffIcon_resize = statoffIcon.getImage();
		Image statoffIcon_resized = statoffIcon_resize.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		statoffResizedIcon = new ImageIcon(statoffIcon_resized);
		
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
		friendsTable.getColumn("My Profile").setPreferredWidth(5);
		friendsTable.setRowHeight(80);
		friendsTable.setRowMargin(10);
		
		for(int i = 0; i < friendarr.size(); i++) {
			String friendinfo[] = friendarr.get(i);
			
			friendsTable.getModel().setValueAt(logoResizedIcon, i, 0);
			friendsTable.getModel().setValueAt(friendinfo[1], i, 1);
			friendsTable.getModel().setValueAt(friendinfo[0], i, 2);
			
			if(friendinfo[2] == "false") {
				friendsTable.getModel().setValueAt(statoffResizedIcon, i, 3);
				//data[i][3] = friendinfo[2];
			}
			else if (friendinfo[2] == "true") {
				friendsTable.getModel().setValueAt(statonResizedIcon, i, 3);
				//data[i][3] = friendinfo[2];
			}
			/*
			data[i][0] = logoResizedIcon;
			data[i][1] = friendinfo[1];
			data[i][2] = friendinfo[0];
			*/
		}
		
		scrollPane = new JScrollPane(friendsTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setSize(520,700);
		scrollPane.setLocation(100,80);
		scrollPane.getViewport().setBackground(Color.white);

		addPanel.add(scrollPane, BorderLayout.CENTER);
		
		ImageIcon logout_img = new ImageIcon("img/exit.png");
	    Image resize_logout = logout_img.getImage();
	    Image changed_logout_img = resize_logout.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	    ImageIcon logout_icon = new ImageIcon(changed_logout_img);
	    JLabel logoutBtn = new JLabel(logout_icon);
	    logoutBtn.setSize(30,30);
	    logoutBtn.setLocation(550, 15);
	    addPanel.add(logoutBtn);
	    logoutBtn.addMouseListener(new MouseListener(){
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				UserUI.win.change("loginUI");    
				final ImageIcon icon = new ImageIcon("img/a.png");
				UserUI.login = false;
				UIManager UI=new UIManager();
		        UI.put("OptionPane.background",new ColorUIResource(255,255,255));
		        UI.put("Panel.background",new ColorUIResource(255,255,255));
				final ImageIcon aicon = new ImageIcon("img/a.png");
		        JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.", "Info", JOptionPane.INFORMATION_MESSAGE, aicon);
		        addPanel.removeAll();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
	         public void mouseEntered(MouseEvent e) {
	            // TODO Auto-generated method stub
	         
	            ImageIcon logouton_img = new ImageIcon("img/exit1.png");
	             Image resize_logouton = logouton_img.getImage();
	             Image changed_logouton_img = resize_logouton.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	             ImageIcon logouton_icon = new ImageIcon(changed_logouton_img);
	             logoutBtn.setIcon(logouton_icon);
	         }
	         @Override
	         public void mouseExited(MouseEvent e) {
	            // TODO Auto-generated method stub
	            logoutBtn.setIcon(logout_icon);
	         }
		});
		
		
		
		ImageIcon add_img = new ImageIcon("img/friendpuls.png");
	    Image resize_add = add_img.getImage();
	    Image changed_add_img = resize_add.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	    ImageIcon add_icon = new ImageIcon(changed_add_img);
	    JLabel addfriendBtn = new JLabel(add_icon);
	    addfriendBtn.setSize(30,30);
	    addfriendBtn.setLocation(320, 15);
	    addPanel.add(addfriendBtn);
	    addfriendBtn.addMouseListener(new MouseListener(){
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String friendID = friendAdd.getText();
				if(!friendID.equals("") && !friendID.equals("Friend ID")) {
					friendAdd.setText("");
					
					if(DBA.addFriend(UserUI.userid, friendID)) {
						if(DBA.getStat(friendID)) model.addRow(new Object[] {logoResizedIcon, DBA.getName(friendID), friendID, statonResizedIcon});
						else model.addRow(new Object[] {logoResizedIcon, DBA.getName(friendID), friendID, statoffResizedIcon});	
					}
					else {
						UIManager UI=new UIManager();
				        UI.put("OptionPane.background",new ColorUIResource(255,255,255));
				        UI.put("Panel.background",new ColorUIResource(255,255,255));
						final ImageIcon icon = new ImageIcon("img/a.png");
				        JOptionPane.showMessageDialog(null, "일치하는 아이디가 없습니다.", "Info", JOptionPane.INFORMATION_MESSAGE, icon);
					}
					
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
	         public void mouseEntered(MouseEvent e) {
	            // TODO Auto-generated method stub
	            ImageIcon onadd_img = new ImageIcon("img/friendpulson.png");
	             Image resize_onadd = onadd_img.getImage();
	             Image changed_onadd_img = resize_onadd.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	             ImageIcon onadd_icon = new ImageIcon(changed_onadd_img);
	             addfriendBtn.setIcon(onadd_icon);
	         }
	         @Override
	         public void mouseExited(MouseEvent e) {
	            // TODO Auto-generated method stub
	            addfriendBtn.setIcon(add_icon);
	         }
		});
		
		
		friendAdd = new JTextField();
		friendAdd.setLocation(105,15);
		friendAdd.setSize(200,30);
		//friendAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ghostText = new GhostText(friendAdd, " Friend ID");
		addPanel.add(friendAdd);
		
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
		myteamLabel.setText("Friends");
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
	
	
	
	
	public friendsUI(UserUI win) {
		this.win = win;
		setLayout(null);
		
	}
}

class TableBackroundPaint0 extends JTable {

    private static final long serialVersionUID = 1L;

    TableBackroundPaint0(Object[][] data, Object[] head) {
        super(data, head);
        setOpaque(false);
        ((JComponent) getDefaultRenderer(Object.class)).setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        Color background = new Color(255, 255, 255);
        Color controlColor = new Color(255, 255, 255);
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        Paint oldPaint = g2.getPaint();
        g2.setPaint(new GradientPaint(0, 0, background, width, 0, controlColor));
        g2.fillRect(0, 0, width, height);
        g2.setPaint(oldPaint);
        /*
        for (int row : getSelectedRows()) {
            Rectangle start = getCellRect(row, 0, true);
            Rectangle end = getCellRect(row, getColumnCount() - 1, true);
            g2.setPaint(new GradientPaint(start.x, 0, controlColor, (int) ((end.x + end.width - start.x) * 1.25), 0, Color.white));
            g2.fillRect(start.x, start.y, end.x + end.width - start.x, start.height);
        }*/
        super.paintComponent(g);
    }
}
class CustomRenderer extends DefaultTableCellRenderer 
{
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
    	this.setHorizontalAlignment(SwingConstants.CENTER);
    	Font tableFont = new Font("돋움", Font.PLAIN, 12);
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(new java.awt.Color(255, 255, 255));
        c.setFont(tableFont);
        if (hasFocus) {
            // this cell is the anchor and the table has the focus
        	table.setSelectionBackground(Color.LIGHT_GRAY);

        } else {
        	table.setSelectionBackground(Color.LIGHT_GRAY);

        }
        if (column == 1)
            ((JComponent) c).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.white));
        else if (column == 2) {
            ((JComponent) c).setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.white));
            //((JLabel) c).setText(null);
        } else
            ((JComponent) c).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));

        c.setForeground(Color.BLACK);
        return c;
    }
}
