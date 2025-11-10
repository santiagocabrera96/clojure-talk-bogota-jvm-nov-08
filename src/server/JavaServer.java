package server;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.ee9.servlet.ServletContextHandler;
import org.eclipse.jetty.ee9.servlet.ServletHolder;
import org.eclipse.jetty.server.*;

import java.io.IOException;
import java.util.Collections;


public class JavaServer {
    static void main(String[] args) throws Exception {
        var server = new Server(8080);

        var context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new HelloWorldServlet()), "/hello");

        server.start();
        server.join();
    }
}

class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        response.setStatus(200);
        var out = response.getWriter();
        out.print("<h1>Hello World!</h1>");
        out.print("<h2>headers</h2>");
        out.print("<table>");
        for (String name : Collections.list(request.getHeaderNames())) {
            out.println("<tr><td>" + name + "</td><td>" + request.getHeader(name) + "</td></tr>");
        }
        out.print("</table>");
    }
}