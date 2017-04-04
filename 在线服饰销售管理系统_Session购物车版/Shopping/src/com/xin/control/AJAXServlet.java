package com.xin.control;

import com.xin.bean.ShopUser;
import com.xin.dao.UserDaoImpl;
import com.xin.service.IUserService;
import com.xin.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * Created by golden on 2016/11/25 0025.
 * 以纯文本的形式返回数据
 */
@WebServlet(
        name = "ajaxServlet", urlPatterns = {"/ajaxServlet"}
)
public class AJAXServlet extends HttpServlet {
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        String action = req.getParameter("action");
        switch (action) {
            case "login": {
                //1.取参数信息
                String username = URLDecoder.decode(req.getParameter("username"), "UTF-8");
                String password = URLDecoder.decode(req.getParameter("password"), "UTF-8");
                //2.检查参数是否有问题: 已经在前端检验过了！
                //if (username == null || username.length() == 0) {
                //    printWriter.println("用户名不能为空！");
                //} else if (password == null || password.length() == 0) {
                //    printWriter.println("密码不能为空！");
                //} else {
                //3.校验操作
                if (userService.login(username, password)) {
                    Integer userid = new UserDaoImpl().getUserid(username);
                    HttpSession session = req.getSession(false);
                    session.setAttribute("userid", userid);
                    session.setAttribute("username", username);
                } else {
                    printWriter.print("用户名或密码不正确！");
                }
            }
            break;
            case "register": {
                String username = URLDecoder.decode(req.getParameter("username"), "UTF-8");
                String password = URLDecoder.decode(req.getParameter("password"), "UTF-8");
                String realname = URLDecoder.decode(req.getParameter("realname"), "UTF-8");
                String userEmail = URLDecoder.decode(req.getParameter("userEmail"), "UTF-8");
                String userPhone = URLDecoder.decode(req.getParameter("userPhone"), "UTF-8");
                String userAddress = URLDecoder.decode(req.getParameter("userAddress"), "UTF-8");
                String sex = URLDecoder.decode(req.getParameter("sex"), "UTF-8");

                ShopUser user = new ShopUser(username, password, realname, sex, userEmail, userPhone, userAddress);

                String errorMsg = userService.register(user);
                if (errorMsg.equals("注册成功，返回首页!")) {
                    HttpSession session = req.getSession(false);
                    session.setAttribute("username", user.getUsername());
                    session.setAttribute("userid", user.getUserid());
                }
                printWriter.print(errorMsg);
            }
            break;
        }
        printWriter.close();
    }
}
