package com.springapp.mvc;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class HelloController
{

     @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model)
    {
        model.addAttribute("message", "Hello world!");
        return "/User/hello";
    }
    @RequestMapping(value ="/noAccess", method = RequestMethod.GET)
    public
    @ResponseBody String noAccess(HttpServletRequest request,ModelMap model)throws SQLException
    {
        Product p=new Product();
        ArrayList<Product> s = new ArrayList<Product>();
        ArrayList<String>err = new ArrayList<String>();

//        {"errors":["authentication error, c"],"access":"false","message":"Authentication errors","data":"null"}

        s.add(null);
        err.add("authentication error, user not logged in!!");
        Gson gson=new Gson();
        DatatObject object=new DatatObject();
        object.data=s;
        object.error=err;
        object.access=false;
        object.message="Authentication errors";
        String json= gson.toJson(object);

//        model.addAttribute("message",json);
//        return "/hello";
        return json;
    }
    @RequestMapping(value ="/register",method = RequestMethod.GET)
    public @ResponseBody String RegisterForm(ModelMap model)
    {
        return "/User/create";
    }


    @RequestMapping(value ="/register" , method=RequestMethod.POST)
    public
    String Register(HttpServletRequest request, ModelMap model) throws SQLException, ClassNotFoundException {
        String username= request.getParameter("username");
        String name= request.getParameter("name");
        String password = request.getParameter("password");
        DatabaseUtil d=new DatabaseUtil();
        UserLogin s = new UserLogin();
        s.username=username;
        s.name=name;
        s.password=password;
        ArrayList<String>err = new ArrayList<String>();
        Gson gson=new Gson();
        UserDataObject object=new UserDataObject();
        UserLogin userLogin=new UserLogin();
//        {"errors":["authentication error, c"],"access":"false","message":"Authentication errors","data":"null"}





        int IfuserExist=d.checkIfUsernameExist(username);
        //user already exist
        if(IfuserExist==1)
        {

            String x="Please Register with a new username!!";
            String y="This Username Already Exists!!\n"+x;
            err.add(y);
            object.data=null;
            object.error=err;
            object.access=false;
            object.message="This Username Already Exists in our database !!";

        }
        else
        {
            d.insertU(name, username, password);

            //String y="\n Hello ,"+username+" Welcome to Wingify";
            err.add("no error");
            object.data=s;
            object.error=err;
            object.access=false;
            object.message="User Successfully registered";
       }
        String json= gson.toJson(object);
//        model.addAttribute("message",json);
//        return "/hello";
        return json;
    }
    @RequestMapping(value ="/login",method = RequestMethod.GET)
    public String loginForm(ModelMap model)
    {
        return "/User/login";
    }

    @RequestMapping(value ="/logout",method = RequestMethod.GET)
    public @ResponseBody String logOut(HttpServletRequest request,ModelMap model)
    {
        HttpSession session=request.getSession(true);

        session.setAttribute("Userid", null);

        ArrayList<String>err = new ArrayList<String>();
        Gson gson=new Gson();
        UserDataObject object=new UserDataObject();
        String y="User logged out succesfully";
        err.add("no error");
        object.data=null;
        object.error=err;
        object.access=false;
        object.message=y;
        String json=gson.toJson(gson);
//        model.addAttribute("message",json);
//        return "/hello";
          return json;
    }
    @RequestMapping(value ="/login" , method=RequestMethod.POST)
    public
     @ResponseBody String Login(HttpServletRequest request, ModelMap model) throws SQLException
    {
        String username= request.getParameter("username");
        String password = request.getParameter("password");
        UserLogin s = new UserLogin();
        s.username=username;
        s.password=password;
        int flag;
        DatabaseUtil d=new DatabaseUtil();
        flag = d.IfAuth(username, password);
       // System.out.println("you "+userid);
        ArrayList<String>err = new ArrayList<String>();
        Gson gson=new Gson();
        UserDataObject object=new UserDataObject();
        String json = null;

        System.out.println("yes"+flag);
        if(flag==1)
        {
            HttpSession session=request.getSession(true);
            //String userid = (String)request.getAttribute("username");
            System.out.println("you "+username);
            session.setAttribute("Userid", username);
            System.out.println(username+"your session now "+session.getAttribute("Userid"));



            if(session.getAttribute("Userid")!= null)
            {
                String y="User logged in succesfully";
                err.add("no error");
                object.data=s;
                object.error=err;
                object.access=true;
                object.message=y;
                 json=gson.toJson(object);
            }

//            model.addAttribute("message",json);
//            return "/hello";
            return json;
        }
        else
        {

            int IfuserExist=d.checkIfUsernameExist(username);
            //user already exist
            if(IfuserExist==0)
            {
                String x="The username you entered does not exist!!";
                String y="Username does not exist";
                err.add(y);
                object.data=null;
                object.error=err;
                object.access=false;
                object.message=x;
                json=gson.toJson(object);

            }
            else{
                String x="The username or password you entered is incorrect!!";
                String y="Username or password is incorrect";
                err.add(y);
                object.data=null;
                object.error=err;
                object.access=false;
                object.message=x;
                json=gson.toJson(object);
            }

//            model.addAttribute("message",json);
//            return "/hello";
            return json;
        }

    }
}
