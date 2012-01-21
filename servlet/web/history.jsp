<%-- 
    Document   : history
    Created on : 2012-01-21, 15:27:14
    Author     : inequation
--%>

<%@page import = "java.util.ArrayList" %>
<%@page import = "java.sql.*" %>
<%@page import = "org.inequation.rayspresso.servlet.RenderHistoryEntry" %>
<%@page import = "org.inequation.rayspresso.servlet.RenderHistoryController" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rayspresso Web - Render history</title>
    </head>
    <body>
        <h1>Render history</h1>
        <a href="index.jsp">Back to renderer</a><br />
        <table>
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Timestamp</td>
                    <td>Render mode</td>
                    <td>Near plane Z</td>
                    <td>Far plane Z</td>
                    <td>Width</td>
                    <td>Height</td>
                </tr>
            </thead>
            <tbody>
            <%
            JspWriter wr = pageContext.getOut();
            ArrayList<RenderHistoryEntry> history =
                RenderHistoryController.getInstance().getEntries();
            for (RenderHistoryEntry e : history) {
                String s = "\t\t\t\t<tr>\n"
                    + "\t\t\t\t\t<td>" + e.getTimestamp().toString() + "</td>"
                    + "<td>" + e.getRenderMode().toString() + "</td>"
                    + "<td>" + ((Float)e.getZNear()).toString() + "</td>"
                    + "<td>" + ((Float)e.getZFar()).toString() + "</td>"
                    + "<td>" + e.getWidth() + "</td>"
                    + "<td>" + e.getHeight() + "</td>\n"
                    + "\t\t\t\t</tr>\n";
                wr.write(s);
            }
            %>
            </tbody>
        </table>
        <%
        String s = "<br>\n" + history.size() + " records\n";
        wr.write(s);
        %>
    </body>
</html>
