package Server;

import java.io.IOException;

public class GithubThread extends Thread{
	public String url;
	public String ID;
	GithubThread(String getUrl, String getID){
		url = getUrl;
		ID = getID;
	}

	@Override
	public void run() {
		super.run();
		try {
			GetContribute test = new GetContribute(url);
			test.getCommit();
			for(int i = 0; i < test.contribution.size(); i++)
				DBA.setGitData(test.contribution.get(i)[1], ID, test.contribution.get(i)[2]);
			ClientManagerThread.git = true;
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			for(int i = 0; i < ChatServer.m_OutputList.size(); ++i) {
				ChatServer.m_OutputList.get(i).println("Git Test입니다.");
				ChatServer.m_OutputList.get(i).flush();
			}
			System.out.println();
			System.out.println("종료");
		}
	}
}
