package com.enndfp.controller;

import com.enndfp.entity.DormitoryAdmin;
import com.enndfp.service.DormitoryAdminService;
import com.enndfp.service.impl.DormitoryAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dormitoryAdmin")
public class DormitoryAdminServlet extends HttpServlet {
    private DormitoryAdminService dormitoryAdminService = new DormitoryAdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String method = req.getParameter("method");
        switch (method) {
            case "selectAll":
                req.setAttribute("dormitoryAdminList", this.dormitoryAdminService.selectAll());
                req.getRequestDispatcher("adminmanager.jsp").forward(req, resp);
                break;
            case "select":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("dormitoryAdminList", this.dormitoryAdminService.select(key, value));
                req.getRequestDispatcher("adminmanager.jsp").forward(req, resp);
                break;
            case "save":
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String name = req.getParameter("name");
                String gender = req.getParameter("gender");
                String telephone = req.getParameter("telephone");
                this.dormitoryAdminService.save(new DormitoryAdmin(username, password, name, gender, telephone));
                resp.sendRedirect("/dormitoryAdmin?method=selectAll");
                break;
            case "update":
                String s = req.getParameter("id");
                Integer id = Integer.parseInt(s);
                username = req.getParameter("username");
                password = req.getParameter("password");
                name = req.getParameter("name");
                gender = req.getParameter("gender");
                telephone = req.getParameter("telephone");
                this.dormitoryAdminService.update(new DormitoryAdmin(id, username, password, name, gender, telephone));
                resp.sendRedirect("/dormitoryAdmin?method=selectAll");
                break;
            case "delete":
                s = req.getParameter("id");
                id = Integer.parseInt(s);
                this.dormitoryAdminService.delete(id);
                resp.sendRedirect("/dormitoryAdmin?method=selectAll");
                break;
        }
    }
}
