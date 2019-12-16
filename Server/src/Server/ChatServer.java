package Server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	
	public static ArrayList<PrintWriter> m_OutputList;

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		m_OutputList = new ArrayList<PrintWriter>();
		
		try {
			ServerSocket s_socket = new ServerSocket(8888);
			
			while(true) {
				Socket c_socket = s_socket.accept();
				ClientManagerThread c_thread = new ClientManagerThread();
				c_thread.setSocket(c_socket);
				
				m_OutputList.add(new PrintWriter(c_socket.getOutputStream()));
				
				c_thread.start();
			}
		}catch(IOException e) {
			// TODO Auto-generated method stub
			e.printStackTrace();
		}
	}
}
