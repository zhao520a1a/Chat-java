

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
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.neuedu.guomy.business.LoginManager;

public class MainFrame extends JFrame {
	JPanel pnlMain = null;
	JPanel pnlBottom = null;
	JTextArea txtContent = null;
	JList lstUsers = null;
	JTextField txtSend = null;
	JButton btnSend = null;
	DataOutputStream dos = null;
	Socket socket = null;
	ServerSocket serverSocket = null;
	List<SocketThread> socketThreads = new ArrayList<SocketThread>();
	List users  = new ArrayList();

	private boolean stopped = false;

	public MainFrame() throws HeadlessException {
		init();
	}

	private void init() {
		this.setSize(new Dimension(400, 300));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("服务端");

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				//目的：停止服务端的主线程
				stopped = true;
				
				//告知客户端，服务端已关闭
				for (SocketThread socketThread : socketThreads) {
					socketThread.writeData("ServerClosed");
				}
				
				//目的：防止因serverSocket.accept();线程挂起阻塞，而建立一个连接，从而结束循环，结束线程；
				Socket socket1 = null;
				try {
					if (socket1 == null)
						socket1 = new Socket("127.0.0.1", 9028);
					DataOutputStream dos1 = new DataOutputStream(socket1.getOutputStream());
					dos1.writeUTF("ServerStop");
					dos1.flush();
//					System.out.println("ServerStop已发……");
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					try {
						if(socket1 != null) {
							socket1.close();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				
			
			
			}
		});

		pnlMain = new JPanel();
		pnlMain.setLayout(new BorderLayout());
		this.setContentPane(pnlMain);

		txtContent = new JTextArea();
		txtContent.setBackground(new Color(200, 200, 200));
		pnlMain.add(txtContent, BorderLayout.CENTER);

		//用户列表
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
				DataOutputStream dos = null;

				for (SocketThread socketThread : socketThreads) {
					socketThread.writeData(String.format("Server:%s:%s", txtSend.getText(),new Timestamp(System.currentTimeMillis()).toString()));
				}
				txtContent.setText(txtContent.getText() + "\nServer：" + txtSend.getText() );
				txtSend.setText("");
			}
		});

		pnlBottom.add(txtSend);
		pnlBottom.add(btnSend);
		pnlMain.add(pnlBottom, BorderLayout.SOUTH);
		this.setVisible(true);

		try {
			serverSocket = new ServerSocket(9028);

			while (!stopped) {
				socket = serverSocket.accept();	// 线程挂起阻塞
				//创建一个线程，形参：当前连接，文本框引用，在线用户列表
				SocketThread socketThread = new SocketThread(socket, txtContent, socketThreads,this);
				socketThreads.add(socketThread);
				Thread thread = new Thread(socketThread);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null){
					socket.close();
				}
				if (serverSocket != null){
					serverSocket.close();
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


	public void showUsers(){
		users = new ArrayList();
		for (SocketThread socketThread :  socketThreads) {
			users.add(socketThread.getName());
		}
		lstUsers.setListData(users.toArray());
		pnlMain.add(lstUsers, BorderLayout.EAST);
		lstUsers.repaint();
		pnlMain.repaint();
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
