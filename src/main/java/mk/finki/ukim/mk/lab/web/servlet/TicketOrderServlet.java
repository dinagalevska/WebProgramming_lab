package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
//
//@WebServlet(name = "ticketOrderServlet", urlPatterns = "/ticketOrder")
//@AllArgsConstructor
//public class TicketOrderServlet extends HttpServlet {
//    private final SpringTemplateEngine springTemplateEngine;
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange iWebExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req,resp);
//
//        WebContext context = new WebContext(iWebExchange);
//        //TicketOrder ticketOrder = new TicketOrder(req.getParameter("movieTitle"), req.getParameter("name"), req.getRemoteAddr(), req.getParameter("numTickets"));
//
//        String movieTitle = req.getParameter("movieTitle");
//        context.setVariable("ipAddress", req.getRemoteAddr());
//        context.setVariable("movieTitle", movieTitle);
//        context.setVariable("tickets", req.getParameter("tickets"));
//        context.setVariable("name", req.getParameter("name"));
//
//
//        springTemplateEngine.process("orderConfirmation.html", context, resp.getWriter());
//
//    }
//}
