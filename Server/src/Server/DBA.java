package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
	
	public static void updateRepo(int roomNum, String repoTitle) {
	    String url = "jdbc:mysql://13.125.181.125/seonsik";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, "root", "qwer1234");
			Statement stmt = conn.createStatement();
			
			String sql ="UPDATE Room SET repository = '" + repoTitle + "' WHERE id = '" + roomNum + "'";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setGitData(String gitID, String roomNum, String commit) {
		String url = "jdbc:mysql://13.125.181.125/seonsik";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, "root", "qwer1234");
			Statement stmt = conn.createStatement();
			String sql ="SELECT commit FROM Github WHERE git_id = '" + gitID + "' AND repo_id = '" + roomNum + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				if(rs.getInt(1) != Integer.parseInt(commit)) {
					sql = "UPDATE Github SET commit = '" + commit + "' WHERE git_id = '" + gitID + "' AND repo_id = '" + roomNum +  "'";	//commit 변경
					stmt.executeUpdate(sql);
				}
			}else if(!rs.next()){
	    		sql = "INSERT INTO Github VALUES ('" + gitID + "', '" + commit + "', '" + roomNum + "')";	//commit 추가
	    		stmt.executeUpdate(sql);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
