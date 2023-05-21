package com.enndfp.controller;

import com.enndfp.entity.Moveout;
import com.enndfp.service.MoveoutService;
import com.enndfp.service.StudentService;
import com.enndfp.service.impl.MoveoutServiceImpl;
import com.enndfp.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/moveout")
public class MoveoutServlet extends HttpServlet {
    private StudentService studentService = new StudentServiceImpl();
    private MoveoutService moveoutService = new MoveoutServiceImpl();

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
                req.setAttribute("moveoutList", this.studentService.moveoutList());
                req.getRequestDispatcher("moveoutregister.jsp").forward(req, resp);
                break;
            case "moveout":
                String studentIdStr = req.getParameter("studentId");
                Integer studentId = Integer.parseInt(studentIdStr);
                String dormitoryIdStr = req.getParameter("dormitoryId");
                Integer dormitoryId = Integer.parseInt(dormitoryIdStr);
                String reason = req.getParameter("reason");
                this.moveoutService.save(new Moveout(studentId, dormitoryId, reason));
                resp.sendRedirect("/moveout?method=selectAll");
                break;
            case "select":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("moveoutList", this.studentService.selectMoveout(key, value));
                req.getRequestDispatcher("moveoutregister.jsp").forward(req, resp);
                break;
            case "record":
                req.setAttribute("moveoutList", this.moveoutService.list());
                req.getRequestDispatcher("moveoutrecord.jsp").forward(req, resp);
                break;
            case "recordSelect":
                key = req.getParameter("key");
                value = req.getParameter("value");
                req.setAttribute("moveoutList", this.moveoutService.select(key, value));
                req.getRequestDispatcher("moveoutrecord.jsp").forward(req, resp);
                break;
        }
    }
}
