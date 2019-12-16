package Server;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientManagerThread extends Thread{
	private Socket m_socket;
	private String m_ID;
	public static boolean git = false;
	@Override
	public void run() {
		super.run();
		try {
			BufferedReader tmpbuffer = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
			String text;
			
			while(true) {
				text = tmpbuffer.readLine();
				if(text == null) {
					for(int i = 0; i < ChatServer.m_OutputList.size(); ++i) {
						ChatServer.m_OutputList.get(i).println("LOG>SEND>OUT>>>>>" + m_ID);
						ChatServer.m_OutputList.get(i).flush();
					}
					break;
				}
				
				String[] split = text.split("highkrs12345");
				if(split.length == 2 && split[0].contentEquals("ID")) {
					m_ID = split[1];
					for(int i = 0; i < ChatServer.m_OutputList.size(); ++i) {
						ChatServer.m_OutputList.get(i).println("LOG>SEND>IN>>>>>" + m_ID);
						ChatServer.m_OutputList.get(i).flush();
					}
					continue;
				}
				
				String[] sendMsg = text.split(">SEND>");
				if(sendMsg[0].equals("SEND")) {
					for(int i = 0; i < ChatServer.m_OutputList.size(); i++) {
						
						ChatServer.m_OutputList.get(i).println(text);
						ChatServer.m_OutputList.get(i).flush();
					}
				}else if(sendMsg[0].equals("GIT")) {
					System.out.println(sendMsg[1]);
					sendMsg = sendMsg[1].split(">>>>>");
					System.out.println(sendMsg[0] + " " + sendMsg[1]);
					GithubThread Git = new GithubThread(sendMsg[0], sendMsg[1]);
					Git.start();
				}else if(sendMsg[0].equals("CREATE")) {
					for(int i = 0; i < ChatServer.m_OutputList.size(); i++) {
						
						ChatServer.m_OutputList.get(i).println(text);
						ChatServer.m_OutputList.get(i).flush();
					}
				}
			}
			m_socket.close();
		} catch(IOException e) {
			e.printStackTrace();
		}finally {
			changeStat(m_ID);
		}
	}
	
	public void setSocket(Socket _socket) {
		m_socket = _socket;
	}
	
	public static void changeStat(String mem_ID) {	//status toggle
		try {
		    String url = "jdbc:mysql://13.125.181.125/seonsik";
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		Connection conn = DriverManager.getConnection(url, "root", "qwer1234");
    		Statement stmt = conn.createStatement();
    		
    		String sql =" UPDATE All_Member SET status = NOT status WHERE mem_ID = '" + mem_ID + "'";
    		stmt.executeUpdate(sql); 
    		
    	}catch( ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }catch( SQLException e){
            System.out.println("네트워크가 불안정하여 서버와 연결할 수 없습니다.");
		}
	}
	
	
	
}
