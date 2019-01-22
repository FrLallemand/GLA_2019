/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookies;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author francoislallemand
 */
public class CookieJar {
    
    private CookieJar(){        
    }
    
    private static CookieJar INSTANCE= new CookieJar();
    
    public static CookieJar getInstance() {
        return INSTANCE;
    }
    
    public void addCookie(String name, String value, int duration) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        
        //recherche si le cookie existe
        Cookie newCookie = findExistingCookie(name);
        
        if(newCookie == null){
            //n'existe pas, on crée
            newCookie = new Cookie(name, value);
            newCookie.setPath(request.getContextPath());
        } else {
            //existe, on reprend
            newCookie.setValue(value);
        }
         
        newCookie.setMaxAge(duration);

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.addCookie(newCookie);
    }
     
    public Cookie getCookie(String name){
        Cookie cookie = findExistingCookie(name);
        return cookie;        
    }
    
    public void removeCookie(String name){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setPath(request.getContextPath());
        
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.addCookie(cookie);    
    }
    
    /* helper method to get the id cookie, null if not defined*/
    public Cookie getIdCookie(){
        return getCookie("id");
    }
   
    /* helper method to get the nom cookie, null if not defined*/
    public Cookie getNomCookie(){
        return getCookie("nom");
    }

        /* helper method to get the prenom cookie, null if not defined*/
    public Cookie getPrenomCookie(){
        return getCookie("prenom");
    }

        /* helper method to get the login cookie, null if not defined*/
    public Cookie getLoginCookie(){
        return getCookie("login");
    }

    private Cookie findExistingCookie(String name){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        
        Cookie cookie = null;
        
        //recherche si le cookie existe
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c : cookies){
                if(c.getName().equals(name)) {
                    cookie = c;
                }
            }
        }
        return cookie;
    }
    
}
