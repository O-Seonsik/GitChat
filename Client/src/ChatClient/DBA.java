package ChatClient;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DBA {

	private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;	
    private static String url = "jdbc:mysql://13.125.181.125/seonsik";
	
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    }

	public static boolean login(String id, String pw) {	// ���̵� ����� true ���� �� false;
		
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();
    		
    		String sql = "SELECT mem_PW FROM All_Member where mem_ID = " + "'" +id +"'";
    		rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
                // ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
                // �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
                String db_pw = rs.getString(1); 
                
                if(pw.equals(db_pw)) {
                	changeStat(id);
                	return true;
                }
        		else return false;
            }
    		
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }

		
		return false;
	}
	
	public static void changeStat(String mem_ID) {	//status toggle
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();
    		
    		String sql =" UPDATE All_Member SET status = NOT status WHERE mem_ID = '" + mem_ID + "'";
    		stmt.executeUpdate(sql); 
    		
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
		}
	}
	
	public static boolean check(String id) {	//id �ߺ����� ��� false ���� ��� true
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();
    		
    		String sql = "SELECT mem_ID FROM All_Member where mem_ID = " + "'" + id +"'";
    		rs = stmt.executeQuery(sql);
    		
    		if(rs.next())
    			if(rs.getString(1).equals(id))
    				return false;
    		
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }

		
		return true;
	}

	public static boolean join(String id, String name, String pw, String gitID) {	//ȸ������ ������ true ���н� false
		
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();

    		String sql = "INSERT INTO All_Member VALUES (NULL, '" + id + "', '" + pw + "', '" + name + "', '" +  gitID + "', '0')";
    		stmt.executeUpdate(sql); 
    		System.out.println("done");
    		return true;
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }

		
		return false;
	}
	
	public static void send(int roomNum, String description, String sender, String time, String date) {	//�޽��� DB��
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();
    		String sql = "INSERT INTO Chat_Info VALUES('" + roomNum + "', '" +  description + "', '" + sender + "', '" + time + "', '" + date + "');";
    		stmt.executeUpdate(sql); 
    		
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }
	}
	
	public static ArrayList<String[]> getFriend(String userID) {
		ArrayList<String[]> friends = new ArrayList<String[]>();
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();

    		String sql = "SELECT friend_ID FROM mem_friend_list WHERE user_ID = '" + userID + "'";
    		rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			String id = rs.getString(1);
    			friends.add(new String[]{ id , "", ""});
    		}
    		
    		for(int i = 0; i < friends.size(); i++) {
    			friends.get(i)[1] = getName(friends.get(i)[0]);
    			friends.get(i)[2] = String.valueOf(getStat(friends.get(i)[0]));
    		}
    		
    		return friends;
    		
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }
		
		return null;
	}
	
	public static String getName(String id) {
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();

    		String sql = "SELECT name FROM All_Member WHERE mem_ID = '" + id + "'";
    		rs = stmt.executeQuery(sql);
    		
    		if(rs.next()) {
    			String name = rs.getString(1);
    			return name;
    		}
    		
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }
		return null;
	}
	
	public static boolean getStat(String mem_ID) {
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();

    		String sql = "SELECT status FROM All_Member WHERE mem_ID = '" + mem_ID + "'";
    		rs = stmt.executeQuery(sql);
    		
    		if(rs.next()) return rs.getBoolean(1);
    		
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }
		
		return false;
	}

	public static boolean addFriend(String myID, String friendID) {
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();

    		String sql = "SELECT status FROM All_Member WHERE mem_ID = '" + friendID + "'";
    		rs = stmt.executeQuery(sql);
    		
    		if(!rs.next()) return false;
    		
    		sql = "INSERT INTO mem_friend_list VALUES('" + myID + "', '" + friendID + "')";
    		stmt.executeUpdate(sql);
    		return true;
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }
		return false;
	}
	
	public static ArrayList<String[]> getRooms() {
		ArrayList<String[]> room = new ArrayList<String[]>();
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();

    		String sql = "SELECT roomNum FROM Room_Member WHERE memID = '" + UserUI.userid + "'";
    		rs = stmt.executeQuery(sql);
    		
    		while(rs.next()) {
    			Statement stmt2 = conn.createStatement();
    			ResultSet rs2 = null;
    			rs2 = stmt2.executeQuery("SELECT title, repository FROM Room WHERE roomNum = '" + rs.getString(1) + "'");
    			if(rs2.next()) room.add(new String[]{rs.getString(1), rs2.getString(1), rs2.getString(2)});
    		} 
    		
    		
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }
		
		return room;
	}
	
	public static void getChat() {
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();
    		for(int i = 0; i < UserUI.rooms.size(); i++) {
        		String sql = "SELECT Description, Sender, Time FROM Chat_Info WHERE Room = " + UserUI.rooms.get(i)[0];
        		rs = stmt.executeQuery(sql);
        		List<ChatEntry> tmpChat = new LinkedList<>();
        		while(rs.next()) {
        			if(rs.getString(2).equals(UserUI.userid)) tmpChat.add(new ChatEntry(rs.getString(2), rs.getString(1), 0, rs.getString(3)));
        			else tmpChat.add(new ChatEntry(rs.getString(2), rs.getString(1), 1, rs.getString(3)));
        		}
        		UserUI.allChat.add(tmpChat);
        		UserUI.chatNum.add(new Integer[]{i, Integer.parseInt(UserUI.rooms.get(i)[0])});
    		}
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }
	}
	
	public static void addRoom(String title, String[] chatMemList) {
		try {
    		int roomNum = 0;
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();
    		
    		String sql = "INSERT INTO Room VALUES (null, '" + title + "', null)";	// �� ����
    		stmt.executeUpdate(sql);
    		
    		sql = "SELECT roomNum FROM Room WHERE title = '" + title + "'";	// �� roomNum��������
    		rs = stmt.executeQuery(sql);
    		if(rs.next()) roomNum = rs.getInt(1);
    		for(int i = 0; i < chatMemList.length; i++) {	// ä�ù� ������� ����
    			sql = "INSERT INTO Room_Member VALUES('" + chatMemList[i] +"', "+ roomNum +")";
    			stmt.executeUpdate(sql);
    		}
			SendThread.sendMsg("CREATE>SEND>" + roomNum + ">>>>>" + title);
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }
	}

	public static boolean addChat(String roomNum) {	//���� ������ true ������ false
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();
    		String sql = "SELECT roomNum FROM Room_Member WHERE memID = '" + UserUI.userid + "' AND roomNum = '" + roomNum + "'";
    		rs = stmt.executeQuery(sql);
    		if(rs.next()) return true;
    		else return false;
    		
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }
		
		return false;
	}
	
	public static void addRepository(int roomNum, String URL) {
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();
    		String sql = "UPDATE Room SET repository = '" + URL + "' WHERE roomNum = " + roomNum;
    		stmt.executeUpdate(sql);
    		
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }
	}
	public static ArrayList<String[]> getAllGit(int roomNum){

		ArrayList<String[]> member = new ArrayList<>();
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();

    		String sql = "SELECT memID FROM Room_Member WHERE roomNum = " + roomNum;
    		rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			sql = "SELECT github_ID FROM All_Member WHERE mem_ID = '" + rs.getString(1) + "'";
    			Statement stmt2 = conn.createStatement();
    			ResultSet rs2 = stmt2.executeQuery(sql);
    			if(rs2.next()){
    				sql = "SELECT commit FROM Github WHERE git_id = '" + rs2.getString(1) + "' AND repo_id = " + roomNum;
    				Statement stmt3 = conn.createStatement();
    				ResultSet rs3 = stmt3.executeQuery(sql);
    				if(rs3.next())member.add(new String[] {rs.getString(1), rs2.getString(1), rs3.getString(1), "0"});	//ID, gitID, commit, chat
    				else member.add(new String[] {rs.getString(1), rs2.getString(1), "0", "0"});
    			}
    		}
		}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }
		return member;
	}
	
	
	public static int getCommit(String git_id, int repoID) {
			try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		
	    		conn = DriverManager.getConnection(url, "root", "qwer1234");
	    		
	    		stmt = conn.createStatement();

	    		String sql = "SELECT commit FROM Github WHERE git_id = '" + git_id + "' AND repo_id = " + repoID;
	    		rs = stmt.executeQuery(sql);
	    		if(rs.next()) return rs.getInt(1);
	    	}catch( ClassNotFoundException e){
	            System.out.println("����̹� �ε� ����");
	        }catch( SQLException e){
	            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
	        }
			
		return 0;
	}
	
	public static boolean isURL(int roomNum) {
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, "root", "qwer1234");
    		
    		stmt = conn.createStatement();

    		String sql = "SELECT repository FROM Room WHERE roomNum = " + roomNum;
    		rs = stmt.executeQuery(sql);
    		if(rs.next()) 
    			if(rs.getString(1) == null) return false;
    			else return true;
    	}catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }catch( SQLException e){
            System.out.println("��Ʈ��ũ�� �Ҿ����Ͽ� ������ ������ �� �����ϴ�.");
        }
		return false;
	}
}