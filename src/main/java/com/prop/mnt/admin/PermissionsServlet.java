package com.prop.mnt.admin;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.prop.mnt.admin.PermissionDAO;

public class PermissionsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1410409667108997337L;
	private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
	private PermissionDAO permBean;
  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
  }
  /**
   * Process the HTTP doGet and doPost request.
   */
  public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    response.setContentType(CONTENT_TYPE);
    System.out.println("In Permissionsservlet");
    HttpSession session = request.getSession();
    permBean = (PermissionDAO)session.getAttribute("permBean");
    if(permBean == null)
    {
      permBean = new PermissionDAO();
      session.setAttribute("permBean",permBean);
    }
    String actionParam = request.getParameter("actp");
    System.out.println("action param :"+actionParam);
    String msg = null;
    String userid = null;
    try{
      if(actionParam != null)
      {
        if(actionParam.equalsIgnoreCase("default"))
        {          
          permBean.setUsers();
          permBean.setModules();
          permBean.setPages();
        }
        else if(actionParam.equalsIgnoreCase("display"))
        {          
          userid = request.getParameter("userList");
          if(userid != null)
          {
            permBean.setUsers();
            permBean.setModules();
            permBean.setPages();
            permBean.setUserPerms(userid);
          }
        }
        else if(actionParam.equalsIgnoreCase("save"))
        {
          userid = request.getParameter("userList");
          if(userid != null)
          {
       //     permBean.saveData(request, userid);
            msg = "Permissions are set successfully.";
          }
        }
      }
    }
    catch(Exception ex)
    {
      System.out.println("Exception in Permissionsservlet :"+ex);
      msg = "Problem while fetching or updating data :"+ex;
      response.sendRedirect("common/showerr.jsp?err="+msg);
    }
    //response.sendRedirect("/remis/WEB-INF/views/permissions.jsp?actp="+actionParam+"&mess="+msg+"&user="+userid+"&from=servlet");
    RequestDispatcher dispatcher=request.getRequestDispatcher("setpermissions.action");
    dispatcher.include(request, response);
    }                   
}