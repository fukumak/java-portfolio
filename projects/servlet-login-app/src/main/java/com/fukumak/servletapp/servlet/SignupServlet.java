package com.fukumak.servletapp.servlet;

import com.fukumak.servletapp.model.User;
import com.fukumak.servletapp.store.UserStore;
import com.fukumak.servletapp.util.PasswordUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = trim(req.getParameter("username"));
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        String error = validate(username, password, confirmPassword);
        if (error != null) {
            req.setAttribute("error", error);
            req.setAttribute("username", username);
            forwardToSignup(req, resp);
            return;
        }

        User user = new User(username, PasswordUtil.hash(password));
        UserStore.getInstance().save(user);

        resp.sendRedirect(req.getContextPath() + "/login?registered=1");
    }

    private String validate(String username, String password, String confirmPassword) {
        if (username == null || username.isEmpty()) {
            return "ユーザー名を入力してください。";
        }
        if (password == null || password.isEmpty()) {
            return "パスワードを入力してください。";
        }
        if (!password.equals(confirmPassword)) {
            return "パスワードが一致しません。";
        }
        if (UserStore.getInstance().exists(username)) {
            return "そのユーザー名は既に使用されています。";
        }
        return null;
    }

    private void forwardToSignup(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/signup.jsp");
        dispatcher.forward(req, resp);
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
