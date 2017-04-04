
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JTextArea;

import com.neuedu.guomy.business.LoginManager;


/**
 * 
 * 在服务端专门用来同一个客户端交互的线程；
 * @author ttc
 *
 */
public class SocketThread implements Runnable {

	private Timestamp now = new Timestamp(System.currentTimeMillis());	// 时间

	Socket socket;
	JTextArea txtContent;
	List<SocketThread> socketThreads;
	String name;
	MainFrame mainFrame;
	DataOutputStream dos;

	String outStr;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 
	public SocketThread(Socket socket, JTextArea txtContent,
			List<SocketThread> socketThreads) {
		super();
		this.socket = socket;
		this.txtContent = txtContent;
		this.socketThreads = socketThreads;
	}

	public SocketThread(Socket socket, JTextArea txtContent,
			List<SocketThread> socketThreads, MainFrame mainFrame) {
		super();
		this.socket = socket;
		this.txtContent = txtContent;
		this.socketThreads = socketThreads;
		this.mainFrame = mainFrame;

	}
	@Override
	public void run() {
		DataInputStream dis = null;
		if (socket != null) { 
			try {
				dis = new DataInputStream(socket.getInputStream());
				while (true) {
					String str = dis.readUTF();	// 线程挂起

					txtContent.setText(str + ":" + now.toString() + "\n" + txtContent.getText());	// 参数 txtContent

					String [] strs = str.split(":");
					//处理用户登陆
					if (strs[0].compareToIgnoreCase("Login") == 0) {
						if (new LoginManager().isLogin(strs[1], strs[2]) != null) {
							boolean canLogin = true;
							if(socketThreads != null) {
								for (SocketThread socketThread : socketThreads) {
									if(socketThread.getName() != null) {
										if (socketThread.getName().compareTo(strs[1]) == 0) {
											canLogin = false;
										}
									}
								}
							}

							if(canLogin) {
								// 获取客户的登陆名
								name = strs[1];
								//返回登陆成功消息
								writeData("Successed");
								this.mainFrame.showUsers();
								//向该客户端发送其登陆前已在线用户列表
								String listStr = "UserList";
								for (int i = 0; i < socketThreads.size(); i++){
									listStr += ":" + socketThreads.get(i).getName();
								}
								writeData(listStr);

								//向该客户端发送：将该客户端自己添加要在线用户列表的信息
								for (SocketThread socketThread : socketThreads) {
									if (socketThread.getName().compareTo(name) != 0) {
										outStr = String.format("AddUser:%s:%s",strs[1],now.toString());
										socketThread.writeData(outStr);
									}
								}
							} else {
								writeData("Refused");
							}
						}
						else {
							//返回登陆失败消息
							writeData("Failed");
						}
					}
					//处理用户聊天：将聊天信息转发该其他客户端
					else if (strs[0].compareToIgnoreCase("Chat") == 0) {
						for (SocketThread socketThread : socketThreads) {
							if (socketThread.getName().compareTo(name) == 0 ||
									socketThread.getName().compareTo(strs[1]) == 0 
							) {
								//Chat:发送方：内容：时间
								outStr = String.format("Chat:%s:%s:%s",name,strs[2],now.toString());
								socketThread.writeData(outStr);
							}
						}
					}
					else if (strs[0].compareToIgnoreCase("ChatAll") == 0) {
						for (SocketThread socketThread : socketThreads) {
							//ChatAll:发送方：内容：时间
							outStr = String.format("ChatAll:%s:%s:%s",name,strs[1],now.toString());
							socketThread.writeData(outStr);
						}
					}
					//处理用户下线：将聊天信息转发该其他客户端
					else if (str.compareToIgnoreCase("Client_Request_Exit") == 0) {
						for (SocketThread socketThread : socketThreads) {
							if (socketThread.getName().compareTo(name) != 0) {
								outStr = String.format("RemoveUser:%s:%s", name, now.toString());
								socketThread.writeData(outStr);  //通知其他客户端，移除下线客户
							}  
						}

						for (SocketThread socketThread : socketThreads) {
							if (socketThread.getName().compareTo(name) == 0) {
								socketThread.writeData("Server_Agree_Exit");
								socketThreads.remove(socketThread); //将该用户从服务端用户列表中删除
								break;
							}  
						}
						mainFrame.showUsers();  //刷新服务端的用户列表
						break;
					}
					//因服务端关闭而导致的客户端关闭，进而关闭SocketThread
					else if (strs[0].compareToIgnoreCase("ClientClosed") == 0) {
//						System.out.println("正在关闭SocketThread!");
						break;
					}
					else if (strs[0].compareToIgnoreCase("ServerStop") == 0) {
//						System.out.println("正在关闭最后一个销毁服务端的socket!");
						break;
					}

				}
			} catch (IOException e) {

			} finally {
				try{
					if(dos != null) {
						dos.close();
					}
					if(socket != null) {
						socket.close();
					}
				} catch(IOException e) {
					e.printStackTrace();
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
