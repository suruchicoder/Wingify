package com.springapp.mvc;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

/**
* Created by suruchi on 23/4/16.
*/
@Controller
@RequestMapping("/Product")
public class productController
{


    @RequestMapping(value ="/" , method=RequestMethod.GET)
    public @ResponseBody String ALLtheProduct(HttpServletRequest request, ModelMap model) throws SQLException, ClassNotFoundException {DatabaseUtil d=new DatabaseUtil();
        ArrayList<Product>s=new ArrayList<Product>();
        ArrayList<String>err = new ArrayList<String>();
        Gson gson=new Gson();
        s=d.AllProduct();
        err.add("no error");
        DatatObject object=new DatatObject();
        object.data=s;
        object.error=err;
        object.access=true;
        object.message="error not found";

    String json= gson.toJson(object);
//        model.addAttribute("message",json);
//        return "/hello";
        return json;

}
 @RequestMapping(value ="/check", method = RequestMethod.GET)
 public String createCheckProduct(HttpServletRequest request,ModelMap model)throws SQLException
 {
     String message="hey running";
    return "hello";
 }

    @RequestMapping(value ="/create", method = RequestMethod.GET)
    public String createProduct(HttpServletRequest request,ModelMap model)throws SQLException
    {

        return "/Product/create";
    }
        @RequestMapping(value ="/create", method = RequestMethod.POST)
    public @ResponseBody String createProductstore(HttpServletRequest request,ModelMap model)throws SQLException
    {
        String name= request.getParameter("name");
        String description = request.getParameter("description");
        ArrayList<String>err = new ArrayList<String>();
        DatabaseUtil d=new DatabaseUtil();
        System.out.println(description);
        Product p=new Product();
        d.addProduct(name, description);
        System.out.println("id of created product done");
        p=d.lastCreated(-1);
        ArrayList<Product>s = new ArrayList<Product>();
        s.add(p);
        Gson gson=new Gson();
        DatatObject object=new DatatObject();
        err.add("no error");
        object.data=s;
        object.error=err;
        object.access=true;
        object.message="Prodcut created successfully";
        String json= gson.toJson(object);
//        model.addAttribute("message",json);
//        return "/hello";
        return json;
    }
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public @ResponseBody String UpdateProduct(HttpServletRequest request,ModelMap model)throws SQLException
    {
        String name= request.getParameter("name");
        String idx= request.getParameter("id");
        String description = request.getParameter("description");
        DatabaseUtil d=new DatabaseUtil();
        System.out.println(description);
        int id=Integer.parseInt(idx);
        ArrayList<Product>s=new ArrayList<Product>();
        ArrayList<String>err=new ArrayList<String>();
        Gson gson=new Gson();
        Product pro= new Product();
        DatatObject object=new DatatObject();



        pro=d.findProduct(id);
        if(pro==null)
        {
            err.add("Product you are searching for does not exist!!");
            s.add(pro);
            object.data=s;
            object.error=err;
            object.access=true;
            object.message="Product does not exist!!";
           // model.addAttribute("message","Product with id ="+id+" does not exist!!");
            //return "/Product/hello";
        }
        else
        {
            err.add("no error");
            pro.name=name;
            pro.discription=description;

            s.add(pro);
            object.data=s;
            object.error=err;
            object.access=true;
            object.message="Product updated successfully";
            Product p=new Product();
            p.id=id;
            p.name=name;
            p.discription=description;
            d.updateProduct(id,name,description);

        }
        String json= gson.toJson(object);
//        model.addAttribute("message",json);
//        return "/hello";
        return json;
    }
@RequestMapping(value ="/update", method = RequestMethod.GET)
public String updateProduct(HttpServletRequest request,ModelMap model)throws SQLException
{

        return "/Product/update";


}

    @RequestMapping(value ="/delete/{id}" , method=RequestMethod.GET)
    public
    @ResponseBody String  deleteProduct(@PathVariable("id") Integer id,HttpServletRequest request, ModelMap model) throws SQLException, ClassNotFoundException {
        DatabaseUtil d=new DatabaseUtil();
        Product pro= new Product();
        Gson gson=new Gson();
        pro=d.findProduct(id);

        ArrayList<Product>s=new ArrayList<Product>();
        ArrayList<String>err=new ArrayList<String>();
        DatatObject object=new DatatObject();


//
        if(pro==null)
        {
            err.add("Product you are searching for does not exist!!");
            object.data=s;
            object.error=err;
            object.access=true;
            object.message="Product does not exist!!";

        }
        else
        {


             d.deleteProduct(id);
            err.add("no error");
            s.add(pro);
            object.data=s;
            object.error=err;
            object.access=true;
            object.message="Product deleted successfully!!";



        }
        String json= gson.toJson(object);
//        model.addAttribute("message",json);
//        return "/hello";
        return json;
    }
    @RequestMapping(value ="/find/{id}", method = RequestMethod.GET)
    public  @ResponseBody String findProduct(@PathVariable("id") Integer id,HttpServletRequest request,ModelMap model)throws SQLException
    {
        Gson gson=new Gson();
        Product pro= new Product();
        DatabaseUtil d=new DatabaseUtil();
        pro=d.findProduct(id);
        ArrayList<Product>s=new ArrayList<Product>();
        ArrayList<String>err=new ArrayList<String>();
        DatatObject object=new DatatObject();
     if(pro==null)
        {
            err.add("Product you are searching for does not exist!!");
            object.data=s;
            object.error=err;
            object.access=true;
            object.message="Product does not exist!!";

        }
        else
        {
            err.add("no error");
            s.add(pro);
            object.data=s;
            object.error=err;
            object.access=true;
            object.message="error not fount";


        }
        String json= gson.toJson(object);

//        model.addAttribute("message",json);
//        return "/hello";
        return json;
    }


}
