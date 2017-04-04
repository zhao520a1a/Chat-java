package com.xin.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.xin.dao.UserDAOImpl;
import com.xin.vo.User;

public class UserServiceImpl implements IUserService {

	private UserDAOImpl dao = new UserDAOImpl();
	
	//存头像图片类型信息
	private Map<String, String> types = new HashMap<String, String>(); 
	private static final String TMPDIR = "tmp";  	//临时保存图片的文件名
	private static final String SAVEGIR = "upload";   //保存图片的文件名
	
	
	public UserServiceImpl() {
		//为头像上传做准备
		//限制只能上传以下的图片类型
		types.put("image/jpeg", ".jpg");
		types.put("image/gif", ".gif");
		types.put("image/x-ms-bmp", ".bmp");
		types.put("image/png",".png");
	}
 
	@Override
	public User uploadPic(HttpServletRequest request) {
		User user = new User();
		
		//得到当前项目所在的绝对路径；
		//C:\Software\web-server\apache-tomcat-8.0.15-windows-x64\apache-tomcat-8.0.15\wtpwebapps\C8
		String tpath = request.getServletContext().getRealPath("/");
		String tmpPath =  System.getProperty("file.separator") + TMPDIR ;  
		String savePath = tpath + System.getProperty("file.separator") + SAVEGIR; 
		
		File tmpDir = new File(tmpPath);    //文件上传时的临时保存目录
		File saveDir = new File(savePath); //文件上传后的保存目录
		if(!tmpDir.isDirectory()) {      //若没有临时或保存目录，新建目录；
			tmpDir.mkdir();
		}
		if(!saveDir.isDirectory()) {
			saveDir.mkdir();
		}
		// 创建一个基于磁盘文件解析的对象
		DiskFileItemFactory dff = new DiskFileItemFactory();	
		dff.setRepository(tmpDir);//设置临时存储目录
		dff.setSizeThreshold( 1024* 1024 * 10);//设置临时存储文件的缓存大小
		// 创建一个文件上传处理程序  
		ServletFileUpload sfu = new ServletFileUpload(dff);
		sfu.setFileSizeMax(1024 * 1024 *5);//限制上传单个文件大小
		sfu.setSizeMax(1024 * 1024 * 50);//限制一次请求上传多个文件总大小
		try {
			//解析request请求，并执行相应操作
			//创建一个遍历所有上传组件的遍历器
			FileItemIterator fileItems = sfu.getItemIterator(request);
			while(fileItems.hasNext()) {
				//从集合中得到一个文件流
				FileItemStream fileItemStream = fileItems.next();
				String name = fileItemStream.getFieldName();	  //获得前端界面输入框中的名字： file0,reusername,repassword;
				InputStream stream = fileItemStream.openStream(); //从集合中得到一个文件输入流
				
				//当前组件是文件域而且用户已经选择了头像
				if(!fileItemStream.isFormField() && fileItemStream.getName().length()>0) {  
					String contentType = fileItemStream.getContentType();//上传的文件后缀类型
					if(!types.containsKey(contentType)){//检查是否符合文件后缀
						break;//不符合，就没必要上传了
					}
					//若符合条件，则准备上传文件
					BufferedInputStream bin = new BufferedInputStream(stream);
					
					//保证每一张图片名称唯一，防止覆盖发生；
					UUID id = UUID.randomUUID();//生成全球唯一的id号;
					String filename = id.toString() + types.get(contentType);//生成文件名字（名称+后缀）
					//得到和tomcat路径相连的绝对上传路径+文件名
					String uploadFilepath =  savePath + System.getProperty("file.separator") + filename;
					
					BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(uploadFilepath));
					Streams.copy(bin, bout, true);// 开始把文件写到指定的上传文件夹 ,并自动关闭输入输出流；
					user.setPath(uploadFilepath);
				} else { //当前组件是非文件域
					//将注册的username和password存到vo中
					switch(name)  {
					case "reusername":
						String username = Streams.asString(stream, "utf-8");
						user.setUsername(username);
						break;
					case "repassword":
						String password = Streams.asString(stream,"utf-8");
						user.setPassword(password);
					    break;
					default: 
						break;
					}
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
			}  
			
		return user;
	}
	
	
	@Override
	public User login(User user) {
		return dao.login(user);
	}

	@Override
	public boolean register(User user) {
		return dao.register(user);
	}
	
	@Override
	public byte[] getPic(int id) {
		return dao.getPic(id);
	}

	@Override
	public boolean editPageNum(int rowsPerPage, int userid) {
		return dao.editPageNum(rowsPerPage, userid);
	}

}
