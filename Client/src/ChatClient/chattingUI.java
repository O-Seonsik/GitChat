package ChatClient;

import java.awt.Adjustable;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Scrollable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import ChatClient.UserUI.GhostArea;
import ChatClient.UserUI.GhostText;

class chattingUI extends JPanel{
	private UserUI win;
	public static JTextArea textField;
	public static JScrollPane chatScroll;
	private JScrollPane scrollPane;
	private JTextPane chat;
	public static JDialog contributeDialog, repositDialog;
	private GhostArea ghostText;
	private JButton btnOut, btnSend,  btnRepository;
	static public JButton btnContribution;
	ImageIcon icon;
	private Image background;
	public static JPanel mainPanel = new JPanel();
	Boolean checkenter = false;
	private static int nowRow;
	public static ArrayList<String[]> gitData;
	public static JLabel titleLabel = new JLabel("");
    static GridBagConstraints gbc = new GridBagConstraints();
    public static int nowRoomNum;
    private static int crown = 0;
	public void paintComponent(Graphics g) {
		
        
		icon = new ImageIcon("");
		Dimension d = getSize();
        g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
		setOpaque(false);
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(2f));
        g2.drawLine(0, 100, 3000, 100);
        g2.drawLine(0, 630, 3000, 630);
	}
	
	public chattingUI(UserUI win) {
		this.win = win;
	      setLayout(null);
	      
	      Font font = new Font("돋움", Font.PLAIN, 30);
	      titleLabel.setSize(200,200);
	      titleLabel.setLocation(100,-20);
	      titleLabel.setFont(font);
	      
	      add(titleLabel);
		  
		  ImageIcon contribute_img = new ImageIcon("img/chart.png");
	      Image resize_contribute = contribute_img.getImage();
	      Image changed_contribute_img= resize_contribute.getScaledInstance(30, 30, Image.SCALE_SMOOTH );
	      ImageIcon analytics = new ImageIcon(changed_contribute_img);
	      
	      btnContribution = new JButton(analytics);
	      btnContribution.setSize(40,40);
	      btnContribution.setLocation(550, 50);
	      btnContribution.setBorderPainted(false);
	      btnContribution.setContentAreaFilled(false);
	      add(btnContribution);
	      
	      btnContribution.addActionListener(new ActionListener() {
	    	  @Override
	          public void actionPerformed(ActionEvent e) {
	               //채팅방 최대인원으로 배열 크기 설정
	    	      //contributeDialog.add(chatTitle);
	             if(DBA.isURL(nowRoomNum)) {
	            	 contributeDialog.setSize(500,400);
	            	 
	            	 JPanel controlPanel = new JPanel(new FlowLayout());
	            	 ImageIcon revalidate_img = new ImageIcon("img/refresh_button.png");
		    		  Image resize_revalidate = revalidate_img.getImage();
		    		  Image changed_revalidate_img = resize_revalidate.getScaledInstance(30,30,Image.SCALE_SMOOTH);
		    		  ImageIcon revalidate_icon = new ImageIcon(changed_revalidate_img);
		    	      
		    	      JLabel revalidateBtn = new JLabel(revalidate_icon);
		    			revalidateBtn.setLocation(0,0);
		    	        revalidateBtn.setSize(30,30);
		    			revalidateBtn.addMouseListener(new MouseListener(){
		    				@Override
		    				public void mousePressed(MouseEvent e) {
		    					// TODO Auto-generated method stub
		    				}
		    				@Override
		    				public void mouseClicked(MouseEvent e) {
		    					// TODO Auto-generated method stub
		    					SendThread.sendMsg("GIT>SEND>" + UserUI.rooms.get(nowRow)[2] + ">>>>>" + UserUI.rooms.get(nowRow)[0]);
		    				}
		    				@Override
		    				public void mouseReleased(MouseEvent e) {
		    					// TODO Auto-generated method stub
		    				}
		    				@Override
		    				public void mouseEntered(MouseEvent e) {
		    					// TODO Auto-generated method stub
		    					ImageIcon revalidateon_img = new ImageIcon("img/refresh1.png");
		  		    		  	Image resize_revalidateon = revalidateon_img.getImage();
		  		    		  	Image changed_revalidateon_img = resize_revalidateon.getScaledInstance(30,30,Image.SCALE_SMOOTH);
		  		    		  	ImageIcon revalidateon_icon = new ImageIcon(changed_revalidateon_img);
		  		    		  	revalidateBtn.setIcon(revalidateon_icon);
		    				}
		    				@Override
		    				public void mouseExited(MouseEvent e) {
		    					// TODO Auto-generated method stub
		    					revalidateBtn.setIcon(revalidate_icon);
		    				}
		    			});
		    			
		    			
		    			
	            	 int[] chat = new int[gitData.size()];
		             int[] commit = new int[gitData.size()];
		             String[] names = new String[gitData.size()];
		             String[] git_id = new String[gitData.size()];
		             
		             for(int i = 0; i < gitData.size(); i++) {
		            	 names[i] = gitData.get(i)[0];
		            	 commit[i] = Integer.parseInt(gitData.get(i)[2]);
		            	 chat[i] = Integer.parseInt(gitData.get(i)[3]);
		            	 git_id[i] = gitData.get(i)[1];
		             }
		             
		             final contributeDialog contdialog = new contributeDialog(chat,commit, names, git_id, "기여도 보기");
		             contdialog.setPreferredSize(new Dimension(500,100 * gitData.size()));
		             JScrollPane contscroll = new JScrollPane(contdialog,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		             //contscroll.setSize(530,400);
		             //contscroll.setLocation(0,0);
		             revalidateBtn.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 20));
		             controlPanel.setLayout(new BorderLayout());
		             controlPanel.add(revalidateBtn, BorderLayout.EAST);
		             controlPanel.setBackground(Color.white);
		             
		             contributeDialog.getContentPane().setLayout(new BorderLayout());
		             
		             contributeDialog.getContentPane().add(contscroll, BorderLayout.CENTER);
		             contributeDialog.getContentPane().add(controlPanel, BorderLayout.NORTH);
		             
	             } else {
	            	 JTextField repositTitle = new JTextField();
	            	 repositTitle.setLocation(40,30);
	            	 repositTitle.setSize(300,30);
	            	 repositTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		    	      GhostText ghostText = new GhostText(repositTitle, "Please Insert Git Repository");
		    	      
		    	      ImageIcon create_img = new ImageIcon("img/register_button.png");
		    		  Image resize_create = create_img.getImage();
		    		  Image changed_create_img = resize_create.getScaledInstance(30,30,Image.SCALE_SMOOTH);
		    		  ImageIcon create_icon = new ImageIcon(changed_create_img);
		    	      
		    	      JButton createBtn = new JButton(create_icon);
		    			createBtn.setLocation(350,30);
		    	        createBtn.setSize(30,30);
		    	        contributeDialog.add(createBtn);
		    			createBtn.addActionListener(new ActionListener() {
		    				@Override
		    				public void actionPerformed(ActionEvent e) {
		    					DBA.addRepository(nowRoomNum, repositTitle.getText());
		    					UserUI.rooms.get(nowRow)[2] = repositTitle.getText();
		    					SendThread.sendMsg("GIT>SEND>" + UserUI.rooms.get(nowRow)[2] + ">>>>>" + UserUI.rooms.get(nowRow)[0]);
		    					contributeDialog.setVisible(false);
		    				}
		    			});
		    		  contributeDialog.setSize(400,100);
		    		  contributeDialog.setLayout(null);
		    	      contributeDialog.add(repositTitle);
		    	      contributeDialog.getContentPane().setBackground(Color.WHITE);
		    	      contributeDialog.add(createBtn);
		    	      
	             }
	             contributeDialog.setVisible(true);
	             
	             contributeDialog.addWindowListener(new WindowAdapter() 
	   	      {
	   	        public void windowClosed(WindowEvent e)
	   	        {
	   	          System.out.println("jdialog window closed event received");
	   	        }

	   	        public void windowClosing(WindowEvent e)
	   	        {
	   	          contributeDialog.getContentPane().removeAll();
	   	        }
	   	      });
	             //add(contributeDialog);
	          }
	      });
	      
	    //contributdialog
	      contributeDialog = new JDialog();
	      contributeDialog.setSize(500,400);
	      contributeDialog.setVisible(false);
	      contributeDialog.setLocationRelativeTo(this);
	      
	      ImageIcon send_img = new ImageIcon("img/sendbtn.png");
	      Image resize_send = send_img.getImage();
	      Image changed_send_img = resize_send.getScaledInstance(100,100,Image.SCALE_SMOOTH);
	      ImageIcon send_icon = new ImageIcon(changed_send_img);
	      btnSend = new JButton(send_icon);
	      btnSend.setLocation(480,650);
	      btnSend.setSize(100,100);
	      btnSend.setBorderPainted(false);
	      btnSend.setContentAreaFilled(false);
	      add(btnSend);
	      btnSend.addActionListener(new SendActionListener());
	      
	      ImageIcon out_img = new ImageIcon("img/back.png");
	      Image resize_out = out_img.getImage();
	      Image changed_out_img = resize_out.getScaledInstance(50,50,Image.SCALE_SMOOTH);
	      ImageIcon out_icon = new ImageIcon(changed_out_img);
	      JLabel btnOut = new JLabel(out_icon);
	      btnOut.setLocation(0,0);
	      btnOut.setSize(50,50);
	      add(btnOut);
	      
	      btnOut.addMouseListener(new MouseListener(){
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					UserUI.win.change("chatroomsUI");
					contributeDialog.setVisible(false);
					contributeDialog.getContentPane().removeAll();
					mainPanel.removeAll();
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

		  mainPanel.setLayout(new GridBagLayout());
		  gbc.weightx = 1.0;
		  gbc.gridwidth = GridBagConstraints.REMAINDER;
		  gbc.fill = GridBagConstraints.HORIZONTAL;
	        
	      chatScroll = new JScrollPane(new VerticalScrollPane(mainPanel));
	      chatScroll.getViewport().setBackground(Color.white);
	      chatScroll.setBackground(Color.WHITE);
	      chatScroll.setLocation(0, 100);
	      chatScroll.setSize(610,530);
	      add(chatScroll); 
	      
	      textField = new JTextArea();
	      textField.setLineWrap(true);
	      textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	      //여기가 문제의 엔터 부분입니다.
	      textField.addKeyListener(new KeyAdapter() {
	    	  public void keyPressed(KeyEvent e) {
				  if (e.getKeyCode()==KeyEvent.VK_ENTER){
					  btnSend.doClick();
					  textField.getDocument().putProperty("filterNewlines", Boolean.TRUE);
				  }
			  }
	      });
	      ///////////////////////////////////////////
	      ghostText = new GhostArea(textField, "Type your chatting in here...");
	      scrollPane = new JScrollPane(textField);
	      scrollPane.setSize(450,130);
	      scrollPane.setLocation(10,640);
	      scrollPane.getViewport().setBackground(Color.white);
	      scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());//invisible edge
	      add(scrollPane);
	            
	      
	      
	      EventQueue.invokeLater(new Runnable() {//textField focus
			   @Override
			     public void run() {
			         textField.grabFocus();
			         textField.requestFocus();
			     }
			});
	      
	}
	
	class OutActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			UserUI.win.change("chatroomsUI");
			contributeDialog.setVisible(false);
			contributeDialog.getContentPane().removeAll();
			mainPanel.removeAll();
		}
	}
	class ContributionActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			contributeDialog.setVisible(true);
			add(contributeDialog);
		}
	}
	
	   public class contributeDialog extends JPanel{
		      
		      private int[] chat;
		      private int[] commit;
		      private int[] point;
		      private String[] name;
		      private String[] git_id;
		      private String title;
		      
		      public contributeDialog(int[] c1, int[]c2, String[] n, String[] g, String t) {
		         name = n;
		         chat = c1;
		         commit = c2;
		         title = t;
		         git_id = g;
		      }
		      
		      public void paintComponent(Graphics g) { 
		         super.paintComponent(g); 
		         
		         if (name == null || name.length == 0)   //채팅방 인원이 아무도 없으면 return 
		            return;
		         
		         Dimension d = new Dimension(400,550);
		         int width = d.width;
		         int height = d.height;
		         
		         setBackground(Color.white);
		         Font sansSerif = new Font("SansSerif", Font.BOLD,15);
		         g.setFont(sansSerif);
		         
		         int maxlen = 0;
		         for(int i=0; i<name.length; i++) {
		            if(name[i]==null) break;
		            if(maxlen<name[i].length())
		               maxlen=10*name[i].length();
		         }
		         //선식이가 말한 1등을 100%로 만들고 기준을 짜보려고했음
		         
		         
		         /*그래프 위치 조절*/
		         
		         int valueX = width/4 + maxlen;
		         int valueY = height/6;
		         int x = width/2;
		         int y = height/10;
		         
		         g.drawString(title,x,y);
		         
		         x=width/8;
		         y=height/6;
		         for(int i = 0; i<name.length;i++) {
		            
		            if(name[i]==null) break;
		            
		            int chatcalc = chat[i] * 3 /10 + 33;
		            int commitcalc = commit[i] * 7 / 10;

		             g.drawString(name[i],x,y+10);      //name쓰기
		             g.setColor(Color.GRAY); 
		             g.drawString(git_id[i], x, y+30); //git id 쓰기
		             g.setColor(Color.pink);
		             
		             g.drawString("Chat", valueX, valueY - 5);
		             //여기야
		             g.fillRect(valueX, valueY, chatcalc ,20 ); 
		             
		             g.setColor(Color.black); 
		             g.drawString(Integer.toString(chat[i]), valueX, valueY+40);
		             
		             //g.drawRect(valueX, valueY, 50+chat[i],20); 
		             g.setColor(Color.orange); 
		             
		             g.drawString("Commit", valueX + chatcalc, valueY - 5);
		             //여기야 
		             g.fillRect(valueX + chatcalc, valueY , commitcalc, 20); 
		             
		             
		             g.setColor(Color.black); 
		             g.drawString(Integer.toString(commit[i]),valueX + chatcalc, valueY + 40);
		             //g.drawRect(valueX+ chat[i]+50, valueY , 50+commit[i], 20); 
		             
		             
		            
		             valueY += height/6; // 그 다음 사람 그래프 위치조절
		             y+=height/6; // 그 다음 사람 이름 쓰기위해서 위치조절
		            
		         }
		      } 
		      
		      
	}
	   
	   
	   //영락아 이거봐라 String title이 타이틀이니까 받아서 물고씹고뜯고맛보고 해서 잘 해주면 타이틀 만들 수 있겠지?
	public static void start(int roomNum, String title) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        nowRow = roomNum;
        nowRoomNum = UserUI.chatNum.get(roomNum)[1]; 
        gitData = DBA.getAllGit(nowRoomNum);
        
        //ID, gitID, commit, chat
        int firstPoint = 0;
        
        //ID, gitID, commit, chat
        for(int i = 0; i < UserUI.allChat.get(nowRow).size(); i++) 
        	for(int j = 0; j < gitData.size(); j++) 
        		if(UserUI.allChat.get(nowRow).get(i).name.equals(gitData.get(j)[0])) 
        			gitData.get(j)[3] = (Integer.parseInt(gitData.get(j)[3]) + 1)+"";
        
        for(int i = 0 ; i <gitData.size(); i++){
        	int tmp = Integer.parseInt(gitData.get(i)[3]) * 3 + Integer.parseInt(gitData.get(i)[2]) * 7;
        	if(firstPoint <= tmp){
        		firstPoint = tmp;
        		crown = i;
        	}
        }
        
        
        for (int i = 0; i < UserUI.allChat.get(roomNum).size(); i++) {
        	List<ChatEntry> allChatList = new ArrayList<ChatEntry>();
        	allChatList.add(UserUI.allChat.get(roomNum).get(i));
        	mainPanel.add(buildChatUI(allChatList), gbc);
        }
        
        
	      titleLabel.setText(title);
	      
        //mainPanel.add(buildChatUI(UserUI.allChat.get(roomNum)), gbc);
        mainPanel.revalidate();
        mainPanel.repaint();
	}
	
	public static void changeValued(int roomNum) {
        nowRow = roomNum;
        nowRoomNum = UserUI.chatNum.get(roomNum)[1]; 
        gitData = DBA.getAllGit(nowRoomNum);
        
		//ID, gitID, commit, chat
        int firstPoint = 0;
        
        //ID, gitID, commit, chat
        for(int i = 0; i < UserUI.allChat.get(nowRow).size(); i++) 
        	for(int j = 0; j < gitData.size(); j++) 
        		if(UserUI.allChat.get(nowRow).get(i).name.equals(gitData.get(j)[0])) 
        			gitData.get(j)[3] = (Integer.parseInt(gitData.get(j)[3]) + 1)+"";
        
        for(int i = 0 ; i <gitData.size(); i++){
        	int tmp = Integer.parseInt(gitData.get(i)[3]) * 3 + Integer.parseInt(gitData.get(i)[2]) * 7;
        	if(firstPoint <= tmp){
        		firstPoint = tmp;
        		crown = i;
        	}
        	System.out.println(gitData.get(i)[3]);
        }
        
        //mainPanel.add(buildChatUI(UserUI.allChat.get(roomNum)), gbc);
        mainPanel.revalidate();
        mainPanel.repaint();
	}
	
	
	class SendActionListener implements ActionListener{	//전송버튼 클릭
		@Override
		public void actionPerformed(ActionEvent e) {	
			
			String today = LocalDate.now().toString();
			String present = LocalTime.now().toString();
			
			if(!textField.getText().equals("Type your chatting in here...") && !textField.getText().replaceAll(" ", "").equals("") && !textField.getText().replaceAll("\n", "").equals("")) {
				SendThread.sendMsg("SEND>SEND>" + textField.getText() + ">>>>>" + UserUI.userName + ">>>>>" + present + ">>>>>" + today + ">>>>>" + nowRoomNum);
				List<ChatEntry> newchat = new LinkedList<>();
				GridBagConstraints gbc = new GridBagConstraints();
		        gbc.weightx = 1.0;
		        gbc.gridwidth = GridBagConstraints.REMAINDER;
		        gbc.fill = GridBagConstraints.HORIZONTAL; 
		        UserUI.allChat.get(nowRow).add(new ChatEntry(UserUI.userid, textField.getText(), 0, present));
		        newchat.add(new ChatEntry(UserUI.userid, textField.getText(), 0, present));
		        chattingUI.mainPanel.add(chattingUI.buildChatUI(newchat), gbc);
		        chattingUI.mainPanel.revalidate();
		        scrollToBottom(chattingUI.chatScroll);

				DBA.send(UserUI.chatNum.get(nowRow)[1], textField.getText(), UserUI.userid, present, today);
			}
			textField.setText("");
		}
	}
	
	
	
	public static void scrollToBottom(JScrollPane scrollPane) {
	    JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
	    AdjustmentListener downScroller = new AdjustmentListener() {
	        @Override
	        public void adjustmentValueChanged(AdjustmentEvent e) {
	        	//e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	            Adjustable adjustable = e.getAdjustable();
	            adjustable.setValue(adjustable.getMaximum());
	            verticalBar.removeAdjustmentListener(this);
	        }
	    };
	    verticalBar.addAdjustmentListener(downScroller);
	}
	
	
	
	public static JPanel buildChatUI(List<ChatEntry> chatContentList) {
	    JPanel chatPanel = new JPanel();
	    chatPanel.setLayout(new GridBagLayout());
	    chatPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    chatPanel.setBackground(Color.WHITE);
	   
	    ImageIcon profileIcon = new ImageIcon("img/profile.png");
		Image profileIcon_resize = profileIcon.getImage();
		Image profileIcon_resized = profileIcon_resize.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon profileResizedIcon = new ImageIcon(profileIcon_resized);
		JLabel profileIconLabel = new JLabel(profileResizedIcon);
		profileIconLabel.setSize(30,30);
		
		ImageIcon crownprofileIcon = new ImageIcon("img/crownprofile.png");
		Image crownprofileIcon_resize = crownprofileIcon.getImage();
		Image crownprofileIcon_resized = crownprofileIcon_resize.getScaledInstance(30, 39, Image.SCALE_SMOOTH);
		ImageIcon crownprofileResizedIcon = new ImageIcon(crownprofileIcon_resized);
		JLabel crownprofileIconLabel = new JLabel(crownprofileResizedIcon);
		crownprofileIconLabel.setSize(30,30);
		
	    GridBagConstraints gbc = new GridBagConstraints();

	    for (ChatEntry chatEntry : chatContentList) {
	        JLabel nameLabel = new JLabel(chatEntry.name);
	        //
	        JLabel timeLabel = new JLabel(chatEntry.time);
	        timeLabel.setForeground(Color.LIGHT_GRAY);
	        BubblePane bubble = new BubblePane(chatPanel, chatEntry.content);

	        // Arrange each chat entry based on the user.
	        if (chatEntry.type == 1) {
	            bubble.setBackground(Color.pink);
	            gbc.anchor = GridBagConstraints.WEST;
	        }
	        else {
	            bubble.setBackground(Color.orange);
	            gbc.anchor = GridBagConstraints.EAST;
	        }

	        gbc.insets.set(0, 0, 0, 0);
	        gbc.weightx = 1.0;
	        gbc.gridwidth = GridBagConstraints.REMAINDER;
	        gbc.fill = GridBagConstraints.NONE;
	        

	        
	        if (gbc.anchor == GridBagConstraints.WEST) {
	        	//여기에서 왕관을 씌울지 결정
	        	if(gitData.get(crown)[0].equals(chatEntry.name)){
	        		chatPanel.add(crownprofileIconLabel, gbc);
	        	}
	        	else {
		        	chatPanel.add(profileIconLabel, gbc);
	        	}
	        	chatPanel.add(nameLabel, gbc);
	            gbc.fill = GridBagConstraints.HORIZONTAL;
	            gbc.insets.set(0, 0, 10, 40);
	            chatPanel.add(bubble, gbc);
	            chatPanel.add(timeLabel, gbc);
	        }
	        else {
	            gbc.fill = GridBagConstraints.HORIZONTAL;
	            gbc.insets.set(0, 40, 10, 0);
	            chatPanel.add(bubble, gbc);
	            
	            gbc.insets.set(0, 40, 10, 0);
	            gbc.weightx = 1.0;
	            gbc.gridwidth = GridBagConstraints.REMAINDER;
	            gbc.fill = GridBagConstraints.BELOW_BASELINE;
	  
	            chatPanel.add(timeLabel, gbc);
	            
	        }
	    }

	    return chatPanel;
	}
}



class BubblePane extends JTextArea {

    private static final long serialVersionUID = -6113801969569504295L;

    private int radius = 10;
    private int strokeThickness = 3;
    private int padding = strokeThickness / 2;
    private JPanel mParent;

    public BubblePane(JPanel parent, String text) {
        mParent = parent;

        setOpaque(false);
        setLineWrap(true);
        setWrapStyleWord(true);
        setEditable(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setText(text);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        int x = padding + strokeThickness;
        int width = getWidth() - (strokeThickness * 2);
        int bottomLineY = getHeight() - strokeThickness;
        g2d.fillRect(x, padding, width, bottomLineY);
        g2d.setRenderingHints(new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON));
        g2d.setStroke(new BasicStroke(strokeThickness));
        RoundRectangle2D.Double rect = new RoundRectangle2D.Double(x, padding,
                width, bottomLineY, radius, radius);
        Area area = new Area(rect);
        g2d.draw(area);


        int lc = countLines(this);
        GridBagLayout gbl = (GridBagLayout) mParent.getLayout();
        GridBagConstraints constraints = gbl.getConstraints(this);
        if (lc == 1) {
            if (constraints.fill == GridBagConstraints.HORIZONTAL) {
                constraints.fill = GridBagConstraints.NONE;
                gbl.setConstraints(this, constraints);
                this.setSize(
                        getFontMetrics(getFont()).stringWidth(getText()) + 
                        this.getBorder().getBorderInsets(this).left +
                        this.getBorder().getBorderInsets(this).right, 
                        getHeight() +
                        this.getBorder().getBorderInsets(this).top + 
                        this.getBorder().getBorderInsets(this).bottom);
            }
        } else {
            if (constraints.fill == GridBagConstraints.NONE) {
                constraints.fill = GridBagConstraints.HORIZONTAL;
                gbl.setConstraints(this, constraints);

            }
        }

        super.paintComponent(g);
    }

    private int countLines(JTextArea textArea) {
        AttributedString text = new AttributedString(textArea.getText());
        FontRenderContext frc = textArea.getFontMetrics(textArea.getFont()).getFontRenderContext();
        AttributedCharacterIterator charIt = text.getIterator();
        LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(charIt, frc);
        float formatWidth = (float) textArea.getSize().width;
        lineMeasurer.setPosition(charIt.getBeginIndex());

        int noLines = 0;
        while (lineMeasurer.getPosition() < charIt.getEndIndex()) {
            lineMeasurer.nextLayout(formatWidth);
            noLines++;
        }

        return noLines;
    }
}


/**
 * This class is used to make the JTextArea lines wrap every time the window
 * is resized. Without this, the JTextArea lines will not shrink back if the
 * parent window shrinks. This is achieved by returning true on getScrollableTracksViewportWidth();
 */
class VerticalScrollPane extends JPanel implements Scrollable {

    private static final long serialVersionUID = 7477168367035025136L;

    public VerticalScrollPane() {
        this(new GridLayout(0, 1));
    }

    public VerticalScrollPane(LayoutManager lm) {
        super(lm);
    }

    public VerticalScrollPane(Component comp) {
        this();
        add(comp);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect,
            int orientation, int direction) {
        return 10;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect,
            int orientation, int direction) {
        return 100;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}

/**
 * Class structure for storing a single chat entry in a full conversation.
 */
class ChatEntry {
    public String name;
    public String content;
    public String time;
    // For type 0=sent, 1=received.
    public int type;

    public ChatEntry(String name, String content, int type, String time) {
    	SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
    	Calendar cal = Calendar.getInstance();
    	time = format1.format(cal.getTime());
        this.name = name;
        this.content = content;
        this.type = type;
        this.time = time;
    }
}