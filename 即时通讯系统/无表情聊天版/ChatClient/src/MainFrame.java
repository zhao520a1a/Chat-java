
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class MainFrame extends JFrame implements Runnable {
	JPanel pnlMain = null;
	JPanel pnlBottom = null;
	JTextArea txtContent = null;
	JList lstUsers = null;
	JTextField txtSend = null;
	JButton btnSend = null;
	Socket socket = null;
	DataOutputStream dos = null;
	String loginName = null;
	List users = new ArrayList();
	
	public MainFrame() throws HeadlessException {
		init();
	}
	public MainFrame(Socket socket, String loginName) throws HeadlessException {
		this.socket = socket;
		this.loginName = loginName;
		init();
	}

	private void init() {
		this.setSize(new Dimension(400, 300));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("用户名：" + loginName);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				writeData("Client_Request_Exit");
			}

		});


		pnlMain = new JPanel();
		pnlMain.setLayout(new BorderLayout());
		this.setContentPane(pnlMain);

		txtContent = new JTextArea();
		txtContent.setText("Let's begin Chatting！");
		txtContent.setBackground(new Color(100, 200, 100));
		pnlMain.add(txtContent, BorderLayout.CENTER);

		users.add("全部用户");
		lstUsers = new JList(users.toArray());
		pnlMain.add(lstUsers, BorderLayout.EAST);

		pnlBottom = new JPanel();
		txtSend = new JTextField();
		txtSend.setColumns(20);

		btnSend = new JButton("发送");
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (socket != null) {

					Object obj = lstUsers.getSelectedValue();
					String str  = null;
					if(obj == null || obj.toString().equals("全部用户")){
						//发送群聊信息， 格式为 ChatAll:内容 
						str =  String.format("ChatAll:%s", txtSend.getText());
					} else{
						//发送一对一单聊信息，格式为  Chat:接收方：内容 
						str =  String.format("Chat:%s:%s", obj.toString(), txtSend.getText());
					}
					writeData(str);

					txtSend.setText("");
				}
			}
		});

		pnlBottom.add(txtSend);
		pnlBottom.add(btnSend);
		pnlMain.add(pnlBottom, BorderLayout.SOUTH);
		this.setVisible(true);

		try {
			if (socket == null)
				socket = new Socket("127.0.0.1", 9028);
			Thread thread = new Thread(this);
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { }
	}

	@Override
	public void run() {
		DataInputStream dis = null;
		if (socket != null) {
			try {
				dis = new DataInputStream(socket.getInputStream());
				while (true) {
					String str = dis.readUTF();	// 线程挂起
					//System.out.println(str);
					String [] strs = str.split(":"); 

					//服务端推送消息
					if (strs[0].compareToIgnoreCase("Server") == 0) {
						txtContent.setText(txtContent.getText() + "\n服务端推送"  +":" + strs[1] + "——————" + strs[2]+":"+strs[3] );
					}
					//接受聊天（单聊和群聊）内容://Chat:发送方：内容：时间   ChatAll:发送方：内容：时间
					else if (strs[0].compareToIgnoreCase("Chat") == 0 || strs[0].compareToIgnoreCase("ChatAll") == 0) {
						txtContent.setText(txtContent.getText() + "\n用户" + strs[1] +":" + strs[2] + "——————" + strs[3]+":"+strs[4]);
					}
					//获取用户列表并添加
					else if (strs[0].compareToIgnoreCase("UserList") == 0) {
						for (int i = 1; i < strs.length; i++) {
							users.add(strs[i]);
						}
						lstUsers.setListData(users.toArray());
						pnlMain.add(lstUsers, BorderLayout.EAST);
						lstUsers.repaint();
						pnlMain.repaint();
					}
					//添加单个用户---将刚上线的客户端添加到用户列表中
					else if (strs[0].compareToIgnoreCase("AddUser") == 0) {
						users.add(strs[1]);
						lstUsers.setListData(users.toArray());
						pnlMain.add(lstUsers, BorderLayout.EAST);
						lstUsers.repaint();
						pnlMain.repaint();
					}
					//移除单个用户---将刚下线的客户端从用户列表移除 
					else if (strs[0].compareToIgnoreCase("RemoveUser") == 0) {
						for(Object userName: users){
							if(userName.toString().compareTo(strs[1]) == 0){
								users.remove(userName);
								break;
							}
						}
						lstUsers.setListData(users.toArray());
						pnlMain.add(lstUsers,BorderLayout.EAST);
						lstUsers.repaint();
						pnlMain.repaint();
					}
					else if (str.compareTo("Server_Agree_Exit") == 0) {
						break;   // 结束线程
					}
					//----
					else if (str.compareTo("ServerClosed") == 0)
					{
						writeData("ClientClosed");  //通知服务端关闭对应的SocketThead
						if( JOptionPane.showConfirmDialog(pnlMain,
								"是否关闭客户端？", "标题", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
							LoginFrame frame = new LoginFrame();
							frame.setVisible(true);
						} 
						this.dispose();
						break;  // 结束线程
					}
					//---
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try{
					if (socket != null)
						socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}


	/*
	 * 发数据
	 */
	public void writeData(String str) {
		
		if (socket != null) {	 
			try {
				dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
		}
	}



}
