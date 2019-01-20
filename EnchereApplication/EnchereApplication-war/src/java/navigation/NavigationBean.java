/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author francoislallemand
 */
@Named("navigationBean")
@ManagedBean
public class NavigationBean {
    
    private List<NavigationElement> list; 
    
    public NavigationBean() {
        this.list = new ArrayList<NavigationElement>();
        
        this.list.add(new NavigationElement("Inscription", "signup.xhtml"));
    }
    
    public List<NavigationElement> getList() {
        System.out.println(list.get(0).getLink());
        return list;
    }

    public void setList(List<NavigationElement> list) {
        this.list = list;
    }    
}
