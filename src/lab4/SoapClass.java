package lab4;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.concurrent.ExecutionException;

@WebService(serviceName = "soap")
@Singleton
public class SoapClass {
    @Resource
    private WebServiceContext webServiceContext;
    @EJB
    private MainBean mainBean;
    @WebMethod
    public String login(String login,String password){
        try {
            MessageContext mc = webServiceContext.getMessageContext();
            HttpSession session = ((javax.servlet.http.HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST)).getSession();
            int checked = mainBean.check(login, password);
            if (checked == 0)
                return "0";
            if (checked == 1) {
                session.setAttribute("login", login);
                return "1";
            }
            return "2";
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
