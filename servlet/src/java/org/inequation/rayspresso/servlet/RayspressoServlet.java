/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inequation.rayspresso.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inequation.rayspresso.renderer.*;
import org.inequation.rayspresso.servlet.RenderHistoryController;

/**
 * Main servlet for the web interface to Rayspresso.
 * @author inequation
 */
public class RayspressoServlet extends HttpServlet {
    private static ServletContext m_context;
        
    /** Servlet initialization routine. */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        m_context = config.getServletContext();        
    }
    
    /** Servlet destruction routine. */
    @Override
    public void destroy() {
        RenderHistoryController.getInstance().destroy();
        super.destroy();
    }
    
    public static ServletContext getContext() {
        return m_context;
    }
    
    /** Populates the scene with traceables and lights. */
    private void populateScene() {
        Scene.getInstance().getTraceables().clear();
        Scene.getInstance().getLights().clear();
        
        BlinnPhongShader yellow = new BlinnPhongShader(
                new Colour(1.f, 0.5f, 0.f),
                new Colour(0.3f, 0.3f, 0.3f, 2.f));
        Plane floor = new Plane(new Vec3(0, 0, 1), -3.f, yellow);
	Plane wallL = new Plane(new Vec3(0, 1, 0), -10.f, yellow);
	Plane wallR = new Plane(new Vec3(0, -1, 0), -10.f, yellow);
	Plane wallB = new Plane(new Vec3(-1, 0, 0), -10.f, yellow/*mirror*/);
	Plane wallF = new Plane(new Vec3(1, 0, 0), -10.f, yellow);
	Plane ceil = new Plane(new Vec3(0, 0, -1), -10.f, yellow);
        
        Scene.getInstance().getTraceables().add(floor);
        Scene.getInstance().getTraceables().add(wallL);
        Scene.getInstance().getTraceables().add(wallR);
        Scene.getInstance().getTraceables().add(wallB);
        Scene.getInstance().getTraceables().add(wallF);
        Scene.getInstance().getTraceables().add(ceil);
        
        PointLight p1 = new PointLight(new Vec3(-2f, 3f, 2f), new Colour(1.f, 1.f, 1.f), 400.f);
	PointLight p2 = new PointLight(new Vec3(-3.f, -2.f, -2.5f), new Colour(0.8f, 1.f, 1.f), 100.f);
        
        Scene.getInstance().getLights().add(p1);
        Scene.getInstance().getLights().add(p2);
    }
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RenderType rt;
        float zNear, zFar;
        int w, h;
        
        try {
            rt = RenderType.parseRenderType(request.getParameter("rendermode"));
            zNear = Float.parseFloat(request.getParameter("znear"));
            zFar = Float.parseFloat(request.getParameter("zfar"));
            w = Integer.parseInt(request.getParameter("width"));
            h = Integer.parseInt(request.getParameter("height"));
        } catch (RenderTypeFormatException rtfex) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Rayspresso Web - Render error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Invalid render type " + request.getParameter("rendermode") + "</h1>");
                out.println("</body>");
                out.println("</html>");
            } finally {            
                out.close();
            }
            return;
        } catch (NumberFormatException nfe) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Rayspresso Web - Render error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Invalid number format - check your values</h1>");
                out.println("</body>");
                out.println("</html>");
            } finally {            
                out.close();
            }
            return;
        } catch (NullPointerException npe) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Rayspresso Web - Render error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Missing parameters - check your values</h1>");
                out.println("</body>");
                out.println("</html>");
            } finally {            
                out.close();
            }
            return;
        }
        
        // make an entry in the render history database
        RenderHistoryController.getInstance().insertEntry(
            new RenderHistoryEntry(
                new Timestamp(Calendar.getInstance().getTime().getTime()),
                rt, zNear, zFar, w, h));
        
        response.setContentType("image/png");
        OutputStream out = response.getOutputStream();
        try {
            populateScene();
            
            BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            
            Material override = null;
            if (rt == RenderType.NORMALS)
                override = new DebugNormalsMaterial();
            else if (rt == RenderType.DEPTH)
                override = new DebugDepthMaterial();

            Camera cam = new Camera(new Vec3(-2.5f, 0.8f, 1.5f),
                    new Vec3(1.f, 0.f, 0.f),
                    new Vec3(0.f, 1.f, 0.f),
                    new Vec3(0.f, 0.f, 1.f),
                    90.f / 180.f * (float)Math.PI, zNear, zFar);

            cam.render(bi, override);
            
            ImageIO.write(bi, "png", out);
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Rayspresso web interface";
    }// </editor-fold>
}
