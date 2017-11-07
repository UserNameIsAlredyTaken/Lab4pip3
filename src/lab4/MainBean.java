package lab4;
import com.sun.deploy.net.HttpRequest;
import lab4.Entitys.Persons;
import lab4.Entitys.Points;
import sun.misc.Request;

import javax.ejb.*;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.TimeUnit;
@LocalBean
@Stateless
public class MainBean{
    private BDManager bdManager = new BDManager();
    public int check(String login, String password){
        return bdManager.checkPerson(login,password);
    }
    public void addPerson(String login, String password){
        bdManager.addPerson(new Persons(login,password));
    }
    public void addPoint(Points points,String login) {
        points.setPerosonsLogin(login);
        bdManager.addPoints(points);
    }
    public void delAllPoints(String login){
        bdManager.delAllPersonPoints(login);
    }
    public String getAllPoints(String login){
        List<Points> list = bdManager.getAllPersonPoints(login);
        String s = "<tr><td>X</td> <td>Y</td> <td> R </td> <td>Result</td></tr>";
        for(int i = 0;i<list.size();i++){
            s+="<tr><td>" + list.get(i).getX() + "</td> <td>" + list.get(i).getY() + "</td> <td>" +
                    list.get(i).getR() + "</td> <td>" + list.get(i).getRes() + "</td></tr>";
        }
        return  s;
    }
}
