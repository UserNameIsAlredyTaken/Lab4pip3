package lab4;

import lab4.Entitys.Points;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.jws.WebService;
import javax.ws.rs.*;
@WebService(serviceName = "soap")
@Singleton
public class SoapClass {
    @EJB
    private MainBean mainBean;
    @Path("/getTable")
    public String getTable(){
        return "";
    }
}
