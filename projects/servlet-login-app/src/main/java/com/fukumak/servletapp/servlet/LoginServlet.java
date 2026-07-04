package com.fukumak.servletapp.servlet;

import com.fukumak.servletapp.model.User;
import com.fukumak.servletapp.store.UserStore;
import com.fukumak.servletapp.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = UserStore.getInstance().find(username);
        boolean valid = user != null
                && user.getPasswordHash().equals(PasswordUtil.hash(password == null ? "" : password));

        if (!valid) {
            req.setAttribute("error", "ユーザー名またはパスワードが正しくありません。");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("loginUser", user);

        resp.sendRedirect(req.getContextPath() + "/mypage.jsp");
    }
}
