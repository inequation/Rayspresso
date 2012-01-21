<%-- 
    Document   : index
    Created on : 2011-12-15, 10:12:36
    Author     : inequation
--%>

<%@page import = "org.inequation.rayspresso.renderer.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rayspresso Web</title>
    </head>
    <body>
        <h1>Render options</h1>
        <a href="history.jsp">View history</a><br />
        <form method="post" action="render" target="preview">
            <table>
                <tr>
                    <td>Width (integer):</td>
                    <td><input type="text" value="640" name="width"></input></td>
                </tr>
                <tr>
                    <td>Height (integer):</td>
                    <td><input type="text" value="480" name="height"></input></td>
                </tr>
                <tr>
                    <td>Near plane Z (floating point):</td>
                    <td><input type="text" value="0.1" name="znear"></input></td>
                </tr>
                <tr>
                    <td>Far plane Z (floating point):</td>
                    <td><input type="text" value="64" name="zfar"></input></td>
                </tr>
                <tr>
                    <td>Render mode:</td>
                    <td>
                        <%
                        JspWriter wr = pageContext.getOut();
                        String s = "<select name=\"rendermode\" size=\"1\" value=\"" + RenderType.SHADED.toString() + "\">\n";
                        wr.write(s);
                        for (RenderType rt : RenderType.values()) {
                            s = "<option>" + rt.toString() + "</option>\n";
                            wr.write(s);
                        }
                        %>
                        </select>
                    </td>
                </tr>
            </table>
            <br />
            <input type="submit" name="render" value="Render!">
        </form>
        <iframe name="preview" src="about:blank" style="width: 95%; height: 600px">
    </body>
</html>
