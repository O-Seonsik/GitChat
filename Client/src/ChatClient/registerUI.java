package ChatClient;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

class registerUI extends JPanel{
	private UserUI win;
	private JFrame frame;
	private JTextField idText;
	private JTextField nameText;
	private JTextField gitText;
	private JPasswordField pwText;
	private JLabel lblId, lblPw, lblName, lblGithub, errorLabel;
	private JDialog info;
	private JButton okButton;
	private boolean checked = false;

	public registerUI(UserUI win) {
		this.win = win;
		setLayout(null);
		setBackground(Color.WHITE);
		
		ImageIcon logoIcon = new ImageIcon("img/CaT.png");
		Image logoIcon_resize = logoIcon.getImage();
		Image logoIcon_resized = logoIcon_resize.getScaledInstance(210, 210, Image.SCALE_SMOOTH);
		ImageIcon logoIconResizedIcon = new ImageIcon(logoIcon_resized);
		JLabel logoLabel = new JLabel(logoIconResizedIcon);
		logoLabel.setSize(500,500);
		logoLabel.setLocation(50,-80);
		add(logoLabel);
		
		
		
		lblId = new JLabel("ID");
		lblId.setSize(200, 40);
		lblId.setLocation(140, 300);
		add(lblId);
		
		idText = new JTextField();
		idText.setSize(200, 40);
		idText.setLocation(200, 300);
		add(idText);
		idText.setColumns(10);
	    
	    lblPw = new JLabel("PW");
		lblPw.setSize(200, 40);
		lblPw.setLocation(140, 370);
		add(lblPw);
		
		pwText = new JPasswordField();
		pwText.setSize(200, 40);
		pwText.setLocation(200, 370);
	    add(pwText);
	    
		lblName = new JLabel("NAME");
		lblName.setSize(200, 40);
		lblName.setLocation(130, 440);
		add(lblName);
		
		nameText = new JTextField();
		nameText.setSize(200, 40);
		nameText.setLocation(200, 440);
		add(nameText);
		nameText.setColumns(10);
		  
		lblGithub = new JLabel("github ID");
		lblGithub.setSize(200, 40);
		lblGithub.setLocation(120, 510);
		add(lblGithub);
		
		gitText = new JTextField();
		gitText.setSize(200, 40);
		gitText.setLocation(200, 510);
		add(gitText);
		gitText.setColumns(10);

		ImageIcon register_img = new ImageIcon("img/resgister_btn.png");
		Image resize_reg = register_img.getImage();
		Image changed_reg_img = resize_reg.getScaledInstance(200, 250, Image.SCALE_SMOOTH);
		ImageIcon register_icon = new ImageIcon(changed_reg_img);
		JButton btnRegister = new JButton(register_icon);
		btnRegister.setSize(200, 50); 
		btnRegister.setLocation(200,565);
		add(btnRegister);
		
		btnRegister.setBorderPainted(false);
		btnRegister.setContentAreaFilled(false);
		btnRegister.addActionListener(new RegistActionListener());
		  
		
		
		ImageIcon back_img = new ImageIcon("img/back.png");
		Image resize_back = back_img.getImage();
		Image changed_back_img = resize_back.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon back_icon = new ImageIcon(changed_back_img);
		JButton btnBack = new JButton(back_icon);
		btnBack.setSize(200, 100);
		btnBack.setLocation(-50,0);
		
		add(btnBack);
		btnBack.setBorderPainted(false);
		btnBack.setContentAreaFilled(false);
		  
		btnBack.addActionListener(new BackActionListener());
		 
		
		info = new JDialog();
		info.setBackground(Color.white);
		info.setLayout(null);
		info.setSize(400,200);
		info.setVisible(false);
		
		errorLabel = new JLabel();
		
		errorLabel.setSize(400,100);
		errorLabel.setLocation(120,20);
		
		
		
		
		okButton = new JButton("확인");
		okButton.setSize(80, 30);
		okButton.setLocation(150,80);
		
		info.add(errorLabel);
		info.add(okButton);
		info.setLocationRelativeTo(this);
		
		okButton.addActionListener(new okActionListener());
	}
	class RegistActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			char[] charPw = pwText.getPassword();
			String pw = "";
			for(int i = 0; i < pwText.getPassword().length; i++) {
				pw += Character.toString(charPw[i]);
			}
			if(!nameText.getText().equals("") && !idText.getText().equals("") && !pw.equals("") && !gitText.getText().equals("")) {
				if(DBA.check(idText.getText())) { 
					DBA.join(idText.getText(), nameText.getText(), pw, gitText.getText());
					errorLabel.setText("회원가입이 완료되었습니다");
					checked = true;
					if(checked) info.setVisible(true);
				}else {
					errorLabel.setText("이미 존재하는 ID입니다.");
					checked = false;
					if(!checked) info.setVisible(true);
				}
			}else {
				UIManager UI=new UIManager();
		        UI.put("OptionPane.background",new ColorUIResource(255,255,255));
		        UI.put("Panel.background",new ColorUIResource(255,255,255));
				final ImageIcon icon = new ImageIcon("img/a.png");
		        JOptionPane.showMessageDialog(null, "정보를 모두 입력하지 않으셨습니다.", "Info", JOptionPane.INFORMATION_MESSAGE, icon);
		        
			}
			
			
		}
	}
	class BackActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("loginUI");
		}
	}
	class okActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			info.setVisible(false);
			if(checked) win.change("loginUI");
			else {
				idText.setText("");
				nameText.setText("");
				pwText.setText("");;
				gitText.setText("");
			}
		}
	}

}