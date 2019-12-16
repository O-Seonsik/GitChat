package ChatClient;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.util.List;

import javax.swing.tree.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.AbstractTableModel;

public class UserUI extends JFrame {

	public static loginUI loginPanel = null;
	public static registerUI registerPanel = null;
	public static friendsUI friendsPanel = null;
	public static chatroomsUI chatroomsPanel = null;
	public static chattingUI chattingPanel = null;
	public static createchattingUI createchattingPanel = null;
	public static UserUI win;
	//customizing
	public static boolean login = false;
	public static String userid;
	public static String userName;
	public static Socket c_socket = null;
	
	public static ArrayList<String[]> rooms;
	
	public static ArrayList<List<ChatEntry>> allChat = new ArrayList<>();
	public static ArrayList<Integer[]> chatNum = new ArrayList<>();
	
	public void change(String panelName) {
	
		if(panelName.equals("loginUI")) { // 로그인
			getContentPane().removeAll();
			getContentPane().setBackground(Color.WHITE);
			getContentPane().add(loginPanel);
			revalidate();
			repaint();
			
		} else if(panelName.equals("registerUI")) { // 회원가입
			getContentPane().removeAll();
			getContentPane().setBackground(Color.WHITE);
			getContentPane().add(registerPanel);
			revalidate();
			repaint();
		} else if(panelName.equals("friendsUI")) { // 친구 목록
			getContentPane().removeAll();
			friendsUI.createTable(friendsPanel, UserUI.userid);
			getContentPane().setBackground(Color.WHITE);
			getContentPane().add(friendsPanel);
			revalidate();
			repaint();
		} else if(panelName.equals("chatroomsUI")) { //채팅 목록
			getContentPane().removeAll();
			chatroomsUI.createTable(chatroomsPanel, UserUI.userid);
			getContentPane().setBackground(Color.WHITE);
			getContentPane().add(chatroomsPanel);
			revalidate();
			repaint();
		} else if(panelName.equals("chattingUI")) { //채팅 
			getContentPane().removeAll();
			getContentPane().setBackground(Color.WHITE);
			getContentPane().add(chattingPanel);
			revalidate();
			repaint();
		} else if(panelName.equals("createchattingUI")) { //채팅 
			getContentPane().removeAll();
			createchattingUI.createTable(createchattingPanel, UserUI.userid);
			getContentPane().setBackground(Color.WHITE);
			getContentPane().add(createchattingPanel);
			revalidate();
			repaint();
		} 
	}
	public static class GhostText implements FocusListener, DocumentListener, PropertyChangeListener {
        private final JTextField textfield;
        private boolean isEmpty;
        private Color ghostColor;
        private Color foregroundColor;
        private final String ghostText;
		
        protected GhostText(final JTextField textfield, String ghostText) {
            super();
            this.textfield = textfield;
            this.ghostText = ghostText;
            this.ghostColor = Color.LIGHT_GRAY;
            textfield.addFocusListener(this);
            registerListeners();
            updateState();
            if (!this.textfield.hasFocus()) {
                focusLost(null);
            }
        }
        public void delete() {
            unregisterListeners();
            textfield.removeFocusListener(this);
        }

        private void registerListeners() {
            textfield.getDocument().addDocumentListener(this);
            textfield.addPropertyChangeListener("foreground", this);
        }

        private void unregisterListeners() {
            textfield.getDocument().removeDocumentListener(this);
            textfield.removePropertyChangeListener("foreground", this);
        }

        public Color getGhostColor() {
            return ghostColor;
        }

        public void setGhostColor(Color ghostColor) {
            this.ghostColor = ghostColor;
        }

        private void updateState() {
            isEmpty = textfield.getText().length() == 0;
            foregroundColor = textfield.getForeground();
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (isEmpty) {
                unregisterListeners();
                try {
                    textfield.setText("");
                    textfield.setForeground(foregroundColor);
                } finally {
                    registerListeners();
                }
            }

        }

        @Override
        public void focusLost(FocusEvent e) {
            if (isEmpty) {
                unregisterListeners();
                try {
                    textfield.setText(ghostText);
                    textfield.setForeground(ghostColor);
                } finally {
                    registerListeners();
                }
            }
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            updateState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateState();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            updateState();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateState();
        }

    }
	public static class GhostArea implements FocusListener, DocumentListener, PropertyChangeListener {
        private final JTextArea textfield;
        private boolean isEmpty;
        private Color ghostColor;
        private Color foregroundColor;
        private final String ghostText;
		
        protected GhostArea(final JTextArea textfield, String ghostText) {
            super();
            this.textfield = textfield;
            this.ghostText = ghostText;
            this.ghostColor = Color.LIGHT_GRAY;
            textfield.addFocusListener(this);
            registerListeners();
            updateState();
            if (!this.textfield.hasFocus()) {
                focusLost(null);
            }
        }
        public void delete() {
            unregisterListeners();
            textfield.removeFocusListener(this);
        }

        private void registerListeners() {
            textfield.getDocument().addDocumentListener(this);
            textfield.addPropertyChangeListener("foreground", this);
        }

        private void unregisterListeners() {
            textfield.getDocument().removeDocumentListener(this);
            textfield.removePropertyChangeListener("foreground", this);
        }

        public Color getGhostColor() {
            return ghostColor;
        }

        public void setGhostColor(Color ghostColor) {
            this.ghostColor = ghostColor;
        }

        private void updateState() {
            isEmpty = textfield.getText().length() == 0;
            foregroundColor = textfield.getForeground();
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (isEmpty) {
                unregisterListeners();
                try {
                    textfield.setText("");
                    textfield.setForeground(foregroundColor);
                } finally {
                    registerListeners();
                }
            }

        }

        @Override
        public void focusLost(FocusEvent e) {
            if (isEmpty) {
                unregisterListeners();
                try {
                    textfield.setText(ghostText);
                    textfield.setForeground(ghostColor);
                } finally {
                    registerListeners();
                }
            }
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            updateState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateState();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            updateState();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateState();
        }

    }
	public static void main(String[] args) {
		win = new UserUI();
		Dimension frameSize = win.getSize();
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    win.setLocation((screenSize.width - frameSize.width)/3, (screenSize.height - frameSize.height) / 10);
	    ImageIcon logoIcon = new ImageIcon("img/CaT.png");
		Image logoIcon_resize = logoIcon.getImage();
		Image logoIcon_resized = logoIcon_resize.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		 win.setIconImage(logoIcon_resized);
	    
		win.setTitle("Computer and Talk");
		win.loginPanel = new loginUI(win);
		win.registerPanel = new registerUI(win);
		win.friendsPanel = new friendsUI(win);
		win.chatroomsPanel = new chatroomsUI(win);
		win.chattingPanel = new chattingUI(win);
		win.createchattingPanel = new createchattingUI(win);
		
		win.add(win.loginPanel);
		//win.setUndecorated(true);
		win.setBackground(Color.WHITE);
		win.setResizable(false);
		win.setSize(600,800);
		win.setVisible(true);
		
	}
	
	public static void connectServer() {
		try {
			c_socket = new Socket("13.125.181.125", 8888);
			
			ReceiveThread rec_thread = new ReceiveThread();
			rec_thread.setSocket(c_socket);
			
			SendThread send_thread = new SendThread();
			send_thread.setSocket(c_socket);
			
			send_thread.start();
			rec_thread.start();
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}