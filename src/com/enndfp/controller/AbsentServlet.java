package com.enndfp.controller;

import com.enndfp.entity.*;
import com.enndfp.service.AbsentService;
import com.enndfp.service.BuildingService;
import com.enndfp.service.DormitoryService;
import com.enndfp.service.StudentService;
import com.enndfp.service.impl.AbsentServiceImpl;
import com.enndfp.service.impl.BuildingServiceImpl;
import com.enndfp.service.impl.DormitoryServiceImpl;
import com.enndfp.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/absent")
public class AbsentServlet extends HttpServlet {
    private BuildingService buildingService = new BuildingServiceImpl();
    private DormitoryService dormitoryService = new DormitoryServiceImpl();
    private StudentService studentService = new StudentServiceImpl();
    private AbsentService absentService = new AbsentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String method = req.getParameter("method");
        switch (method) {
            case "init":
                List<Building> buildingList = this.buildingService.selectAll();
                List<Dormitory> dormitoryList = this.dormitoryService.findByBuildingId(buildingList.get(0).getId());
                List<Student> studentList = this.studentService.findByDormitoryId(dormitoryList.get(0).getId());
                req.setAttribute("buildingList", buildingList);
                req.setAttribute("dormitoryList", dormitoryList);
                req.setAttribute("studentList", studentList);
                req.getRequestDispatcher("absentregister.jsp").forward(req, resp);
                break;
            case "save":
                String buildingIdStr = req.getParameter("buildingId");
                Integer buildingId = Integer.parseInt(buildingIdStr);
                String dormitoryIdStr = req.getParameter("dormitoryId");
                Integer dormitoryId = Integer.parseInt(dormitoryIdStr);
                String studentIdStr = req.getParameter("studentId");
                Integer studentId = Integer.parseInt(studentIdStr);
                String reason = req.getParameter("reason");
                String date = req.getParameter("date");
                HttpSession session = req.getSession();
                DormitoryAdmin dormitoryAdmin = (DormitoryAdmin) session.getAttribute("dormitoryAdmin");
                this.absentService.save(new Absent(buildingId, dormitoryId, studentId, dormitoryAdmin.getId(), date, reason));
                resp.sendRedirect("absent?method=init");
                break;
            case "selectAll":
                req.setAttribute("absentList",this.absentService.selectAll());
                req.getRequestDispatcher("absentrecord.jsp").forward(req, resp);
                break;
            case "select":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("absentList",this.absentService.select(key,value));
                req.getRequestDispatcher("absentrecord.jsp").forward(req, resp);
                break;
        }
    }
}
