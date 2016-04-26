package com.springapp.mvc;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MaintenanceInterceptor extends HandlerInterceptorAdapter{
	private String maintenanceMapping;


	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
	    throws Exception {

		//Calendar cal = Calendar.getInstance();
        String URI=request.getRequestURI();
        System.out.println(URI);
        HttpSession x=request.getSession();
        System.out.println("your session in maintainance class "+ x.getAttribute("Userid"));
        //HttpSession x=request.getSession();
        //System.out.println(URI[0]+URI[1]+"**\n");
        String y = (String)(x.getAttribute("userid"));
        String str2="api";

		if(x.getAttribute("Userid")!=null)
        {
           return true;
        }
        else if(URI.toLowerCase().contains(str2.toLowerCase()))
        {
            System.out.println("here again\n");
            return true;
        }
        else
        {
//            System.out.println("wrong way here\n"+URI);
//            DatatObject object=new DatatObject();
//            ArrayList<String> err = null;
//            ArrayList<Product> s = null;
//            err.add()
//            object.data=s;
//            object.error=err;
//            object.access=false;
//            object.message="you need to login first";
            response.sendRedirect("/api/noAccess");
            return false;
        }
	}
}

