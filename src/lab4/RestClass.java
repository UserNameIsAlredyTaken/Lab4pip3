package lab4;

import lab4.Entitys.Points;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Path("/main")
public class RestClass {

    @EJB private MainBean mainBean;
    @POST
    public Response login(@FormParam("login") String login,@FormParam("password") String password,@Context HttpServletRequest req){
        try {
            int checked = mainBean.check(login,password);
            if(checked==0)
                return Response.ok("0", MediaType.APPLICATION_JSON).build();
            if(checked==1) {
                req.getSession(true).setAttribute("login",login);
                return Response.ok("1", MediaType.APPLICATION_JSON).build();
            }
            return Response.ok("2", MediaType.APPLICATION_JSON).build();
        }catch (Exception e){ return null;}
    }
    @GET
    public Response checkPerson(@Context HttpServletRequest req) {
        if(req.getSession(true).getAttribute("login")!=null) {
            return Response.ok("1", MediaType.APPLICATION_JSON).build();
        }
        else
            return Response.ok("0", MediaType.APPLICATION_JSON).build();
    }
    @Path("/getTable")
    @GET
    public Response getTable(@Context HttpServletRequest req){
        if(req.getSession(true).getAttribute("login")!=null) {
            return Response.ok(mainBean.getAllPoints((String)req.getSession(true).getAttribute("login")), MediaType.TEXT_HTML).build();
        }
        return Response.ok("0", MediaType.TEXT_HTML).build();
    }
    @Path("/removeTable")
    @POST
    public void removeTable(@Context HttpServletRequest req){
        mainBean.delAllPoints((String)req.getSession(true).getAttribute("login"));
    }
    @Path("/out")
    @POST
    public void endSession(@Context HttpServletRequest req){
        req.getSession(true).removeAttribute("login");
    }
    @Path("/registration")
    @POST
    public void newPerson(@FormParam("login") String login,@FormParam("password") String password,@Context HttpServletRequest req){
        req.getSession(true).setAttribute("login",login);
        mainBean.addPerson(login,password);
    }
    @Path("/newPoint")
    @POST
    public void newPoint(@QueryParam("x") Double x,@QueryParam("y") Double y,@QueryParam("r") Double r,@Context HttpServletRequest req){
        if(req.getSession(true).getAttribute("login")!=null) {
            mainBean.addPoint(new Points(x, y, r, ""),(String)req.getSession(true).getAttribute("login"));
        }
    }
}