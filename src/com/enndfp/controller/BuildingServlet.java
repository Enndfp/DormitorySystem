package com.enndfp.controller;

import com.enndfp.entity.Building;
import com.enndfp.service.BuildingService;
import com.enndfp.service.DormitoryAdminService;
import com.enndfp.service.impl.BuildingServiceImpl;
import com.enndfp.service.impl.DormitoryAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/building")
public class BuildingServlet extends HttpServlet {
    private BuildingService buildingService = new BuildingServiceImpl();
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
                req.setAttribute("buildingList", this.buildingService.selectAll());
                req.setAttribute("adminList", this.dormitoryAdminService.selectAll());
                req.getRequestDispatcher("buildingmanager.jsp").forward(req, resp);
                break;
            case "select":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("buildingList", this.buildingService.select(key, value));
                req.setAttribute("adminList", this.dormitoryAdminService.selectAll());
                req.getRequestDispatcher("buildingmanager.jsp").forward(req, resp);
                break;
            case "save":
                String name = req.getParameter("name");
                String introduction = req.getParameter("introduction");
                String adminIdStr = req.getParameter("adminId");
                int adminId = Integer.parseInt(adminIdStr);
                this.buildingService.save(new Building(name, introduction, adminId));
                resp.sendRedirect("/building?method=selectAll");
                break;
            case "update":
                String idStr = req.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                name = req.getParameter("name");
                introduction = req.getParameter("introduction");
                adminIdStr = req.getParameter("adminId");
                adminId = Integer.parseInt(adminIdStr);
                this.buildingService.update(new Building(id, name, introduction, adminId));
                resp.sendRedirect("/building?method=selectAll");
                break;
            case "delete":
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                this.buildingService.delete(id);
                resp.sendRedirect("/building?method=selectAll");
                break;
        }
    }
}
