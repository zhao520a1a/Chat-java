package com.neuedu.guomy.common;

import java.io.*;
import java.net.*;

import com.neuedu.guomy.business.LoginManager;

public class Demo {
	
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(9999);
			while (true) {
				DataInputStream dis = null;
				DataOutputStream dos = null;

				try {
					socket = server.accept();	// Ïß³Ì¹ÒÆð
					dis = new DataInputStream(socket.getInputStream());
					dos = new DataOutputStream(socket.getOutputStream());
	
					String loginName = dis.readUTF();
					String password = dis.readUTF();
					System.out.printf("IP:%s;Port:%s;LoginName:%s;Password:%s;", 
							socket.getInetAddress(), socket.getPort(), loginName, password);
					if (new LoginManager().isLogin(loginName, password) != null) {
						System.out.println("State:Successed.");
						dos.writeUTF("scott");
						dos.flush();
						dos.writeUTF("tiger");
						dos.flush();
					}
					else {
						System.out.println("State:Failed.");
						dos.writeUTF("400");
						dos.flush();
						dos.writeUTF("ERROR");
						dos.flush();
					}
				} catch (IOException e) {
					
				} finally {
					try {
						if (socket != null)
							socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// close...
			try {
				if (server != null)
					server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
