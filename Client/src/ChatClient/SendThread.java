package ChatClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;

public class SendThread extends Thread{

	private Socket m_Socket;
	private static boolean sendChecker = true;
	private static String sendString = null;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			BufferedReader tmpbuf = new BufferedReader(new InputStreamReader(System.in));
			OutputStreamWriter sendoutWriter = new OutputStreamWriter(m_Socket.getOutputStream(), Charset.forName("UTF-8"));
			 PrintWriter sendWriter = new PrintWriter(sendoutWriter);
			 
			sendWriter.println("IDhighkrs12345" + UserUI.userid);
			sendWriter.flush();
			
			while(true){
				sendWriter.flush();
				if(sendChecker) {
					sendWriter.println(sendString);
					sendWriter.flush();
					sendChecker = false;
				}
				
				if(!UserUI.login) break;
			}
			sendWriter.close();
			tmpbuf.close();
			m_Socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			System.out.println("Logout");
		}

	}

	public void setSocket(Socket _socket)
	{
		m_Socket = _socket;
	}
	
	public static void sendMsg(String msg) {
		sendString = msg;
		sendChecker = true;
	}
}