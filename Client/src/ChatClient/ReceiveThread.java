package ChatClient;

import java.awt.Adjustable;
import java.awt.GridBagConstraints;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;


public class ReceiveThread extends Thread{

	private Socket m_Socket;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		String ID = UserUI.userid;
		
		try {
	BufferedReader tmpbuf = new BufferedReader(new InputStreamReader(m_Socket.getInputStream(), "UTF-8"));
			
			String receiveString;
			String[] split;
			
			while(true)
			{
				if(!UserUI.login) break;
				receiveString =tmpbuf.readLine();
				split = receiveString.split(">SEND>");
				if(split[0].equals("SEND")) {
					split = split[1].split(">>>>>");
					if(split[1].equals(UserUI.userName)) continue;
					for(int i = 0; i < UserUI.chatNum.size(); i++) {
						if(Integer.parseInt(split[4]) == UserUI.chatNum.get(i)[1]) {
							
							List<ChatEntry> newchat = new LinkedList<>();
							GridBagConstraints gbc = new GridBagConstraints();
					        gbc.weightx = 1.0;
					        gbc.gridwidth = GridBagConstraints.REMAINDER;
					        gbc.fill = GridBagConstraints.HORIZONTAL;
					        newchat.add(new ChatEntry(split[1], split[0], 1,split[2]));
					        UserUI.allChat.get(UserUI.chatNum.get(i)[0]).add(new ChatEntry(split[1], split[0], 1,split[2]));
					        if(chattingUI.nowRoomNum == UserUI.chatNum.get(i)[1]) {
						        chattingUI.mainPanel.add(chattingUI.buildChatUI(newchat), gbc);
						        chattingUI.mainPanel.revalidate();
						        chattingUI.scrollToBottom(chattingUI.chatScroll);
					        }
							break;
						}
					}
				}else if(split[0].equals("LOG")) {
					split = split[1].split(">>>>>");
					if(!split[1].equals(UserUI.userid)) {
						for(int i = 0; i < friendsUI.friendarr.size(); i++) {
							if(split[1].equals(friendsUI.friendarr.get(i)[0]) && split[0].equals("IN"))	//로그인
								friendsUI.friendsTable.getModel().setValueAt(friendsUI.statonResizedIcon, i, 3);
							else if(split[1].equals(friendsUI.friendarr.get(i)[0]) && split[0].equals("OUT"))	//로그아웃 
								friendsUI.friendsTable.getModel().setValueAt(friendsUI.statoffResizedIcon, i, 3);
						}
					}
				}else if(split[0].equals("CREATE")) {
					split = split[1].split(">>>>>");	//0 채팅넘버, 1 채팅 타이틀
					//채팅방 개설
					if(DBA.addChat(split[0])) {
			    		List<ChatEntry> tmpChat = new LinkedList<>();
						UserUI.rooms.add(new String[] {split[0], split[1], null});
						UserUI.chatNum.add(new Integer[]{UserUI.allChat.size(), Integer.parseInt(split[0])});
			    		UserUI.allChat.add(tmpChat);
			    		
			    		chatroomsUI.model.addRow(new Object[] {"abc"});
			    		chatroomsUI.model.setValueAt(UserUI.rooms.get(UserUI.rooms.size()-1)[1], UserUI.rooms.size()-1, 0);
					}
				}else if(split[0].equals("GIT")) {
					if(DBA.addChat(split[1])) { //본인이 채팅방에 있으면
						// for 영락, 여기에 새로고침 후 새로고침 완료됐다는 메세지 수신 시에 소스 삽입하면 됨.
						for(int i = 0; i < UserUI.rooms.size(); i++) {
							if(UserUI.rooms.get(i)[0].equals(split[1])) {
								if(chattingUI.nowRoomNum == Integer.parseInt(split[1])) {
									chattingUI.changeValued(i);
									chattingUI.contributeDialog.getContentPane().removeAll();
			    					chattingUI.contributeDialog.setVisible(false);
			    					chattingUI.btnContribution.doClick();
									break;
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	public void setSocket(Socket _socket)
	{
		m_Socket = _socket;
	}

}