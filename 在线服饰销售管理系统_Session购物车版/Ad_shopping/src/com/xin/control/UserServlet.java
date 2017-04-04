package com.xin.control;

import com.xin.bean.ShopUser;
import com.xin.service.IUserService;
import com.xin.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by golden on 2016/11/25 0025.
 * 以纯文本的形式返回数据
 */
@WebServlet(
        name = "UserServlet", urlPatterns = {"/UserServlet"}
)
public class UserServlet extends HttpServlet {
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "login": {
                login(req, resp);
            }
            case "showAllUsers": {
                showAllUsers(req, resp);
            }
            break;
            case "delUser": {
                delUser(req, resp);
            }
            break;
            case "updateUser": {
                updateUser(req, resp);
            }
            break;
            case "showOneUser": {
                showOneUser(req, resp);
            }
            break;

        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        String username =  filter(req.getParameter("username"));
        String password =  filter(req.getParameter("password"));
        if( userService.login(username,password)) {
            printWriter.print("登录成功");
        } else {
            printWriter.print("用户和密码不正确");
        }
        printWriter.close();
    }

    private void showOneUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        int userId = Integer.parseInt(filter(req.getParameter("userId")));
        ShopUser user = userService.queryUserInfo(userId);
        if( user !=null) {
            req.getSession().setAttribute("user",user);
            printWriter.print("获取单个用户信息成功");
        } else {
            printWriter.print("获取单个用户信息失败");
        }
        printWriter.close();
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        int userId = Integer.parseInt(filter(req.getParameter("userId")));
        String userName = filter(req.getParameter("userName"));
        String password =  filter(req.getParameter("password"));
        String realName = filter(req.getParameter("realName"));
        String sex = filter(req.getParameter("sex"));
        String userEmail = filter(req.getParameter("userEmail"));
        String userPhone = filter(req.getParameter("userPhone"));
        String userAddress = filter(req.getParameter("userAddress"));
        //Timestamp regTime = Timestamp.valueOf(filter(req.getParameter("regTime")));
        ShopUser user = new ShopUser(userId,userName, password, realName, sex, userEmail, userPhone, userAddress);

        if(userService.updateUser(user)) {
            printWriter.print("修改用户成功");
        } else {
            printWriter.print("修改用户失败");
        }
        printWriter.close();
    }

    private void delUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        int userId = Integer.parseInt(filter(req.getParameter("userId")));
        if(userService.delUser(userId)) {
            printWriter.print("删除用户成功");
        } else {
            printWriter.print("此用户有订单关联，删除用户失败");
        }
    }

    private void showAllUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        List<ShopUser> userList = userService.queryAllUsers();
        if (userList != null && userList.size() != 0) {
            req.getSession().setAttribute("userList", userList);
            printWriter.print("获取所有用户信息成功");
        } else {
            printWriter.print("没有用户信息可供查询！");
        }
        printWriter.close();

    }

    public String filter(String date) throws UnsupportedEncodingException {
        return URLDecoder.decode(date, "UTF-8");
    }

}
