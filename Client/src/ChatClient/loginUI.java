package ChatClient;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JDialog;

import ChatClient.chattingUI.SendActionListener;
import ChatClient.loginUI.LoginActionListener;
import ChatClient.registerUI.RegistActionListener;

class loginUI extends JPanel {
	private JButton loginButton, okButton;
	private UserUI win;
	private JTextField idText;
	private JPasswordField pwText;
	private JLabel idLabel, pwLabel, logoLabel, errorLabel;
	private ImageIcon logoIcon;
	private JDialog info;
	
	public loginUI(UserUI win) {
		this.win = win;
		setLayout(null);
		setBackground(Color.WHITE);
		
		logoIcon = new ImageIcon("img/CaT.png");
		Image logoIcon_resize = logoIcon.getImage();
		Image logoIcon_resized = logoIcon_resize.getScaledInstance(210, 210, Image.SCALE_SMOOTH);
		ImageIcon logoIconResizedIcon = new ImageIcon(logoIcon_resized);
		logoLabel = new JLabel(logoIconResizedIcon);
		logoLabel.setSize(500,500);
		logoLabel.setLocation(50,40);
		
		
		idLabel = new JLabel("loginLabel");
		idLabel.setText("I D ");
		idLabel.setSize(200, 200);
		idLabel.setLocation(150, 320);
		
		idText = new JTextField("");
		idText.setSize(200, 40);
		idText.setLocation(200, 400);
		
		pwLabel = new JLabel("passwdLabel");
		pwLabel.setText("P W ");
		pwLabel.setSize(200, 200);
		pwLabel.setLocation(150, 375);
		
		pwText = new JPasswordField("");
		pwText.setSize(200, 40);
		pwText.setLocation(200, 450);
		pwText.addKeyListener(new KeyAdapter() {
			  public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	loginButton.doClick();
			    }
			  }
		});
				
		
		
		
	    
		ImageIcon login_img = new ImageIcon("img/login_btn.png");
		Image resize_login = login_img.getImage();
		Image changed_login_img = resize_login.getScaledInstance(200,250,Image.SCALE_SMOOTH);
		
		ImageIcon login_icon = new ImageIcon(changed_login_img);
		loginButton = new JButton(login_icon);
		loginButton.setSize(200, 50);
		loginButton.setLocation(200, 500);
		loginButton.setBorder(null);
		loginButton.setFocusPainted(false);
		loginButton.setBorderPainted(false);
		loginButton.setContentAreaFilled(false);
		add(loginButton);
		loginButton.addActionListener(new LoginActionListener());

		
		ImageIcon register_img = new ImageIcon("img/resgister_btn.png");
		Image resize_reg = register_img.getImage();
		Image changed_reg_img = resize_reg.getScaledInstance(200, 250, Image.SCALE_SMOOTH);
		ImageIcon register_icon = new ImageIcon(changed_reg_img);
		JButton btnRegister = new JButton(register_icon);
		btnRegister.setSize(200, 50);
		btnRegister.setLocation(200, 550);
		btnRegister.setBorderPainted(false);
		btnRegister.setContentAreaFilled(false);
		add(btnRegister);

		btnRegister.addActionListener(new RegistActionListener());

		info = new JDialog();
		info.setLayout(null);
		info.setSize(400,200);
		info.setVisible(false);
		info.setBackground(Color.white);
		
		errorLabel = new JLabel();
		errorLabel.setText("회원정보가 올바르지 않습니다");
		errorLabel.setSize(400,100);
		errorLabel.setLocation(120,20);
		
		
		okButton = new JButton("확인");
		okButton.setSize(80, 30);
		okButton.setLocation(150,80);
		
		info.add(errorLabel);
		info.add(okButton);
		info.setLocationRelativeTo(this);

	    
	    
		
		add(logoLabel);

		add(idLabel);
		add(idText);
		add(pwLabel);
		add(pwText);
		
		okButton.addActionListener(new okActionListener());
	}
	class RegistActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("registerUI");
		}
	}
	class LoginActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			char[] charPw = pwText.getPassword();
			String pw = "";
			for(int i = 0; i < pwText.getPassword().length; i++) {
				pw += Character.toString(charPw[i]);
			}
			if(DBA.login(idText.getText(), pw)) {
				UserUI.login = true;
				UserUI.userid = idText.getText();
				UserUI.userName = DBA.getName(UserUI.userid);
				UserUI.connectServer();
				UserUI.rooms = DBA.getRooms();
				DBA.getChat();
				idText.setText("");
				pwText.setText("");
				win.change("friendsUI");
			}else {
				info.setVisible(true);
				//JOptionPane.showMessageDialog(null,"회원정보가 올바르지 않습니다");
			}
		}
		
	}
	class okActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			info.setVisible(false);
		}
	}
}