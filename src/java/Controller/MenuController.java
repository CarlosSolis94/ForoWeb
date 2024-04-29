/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.utils.SessionUtils;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos Solis
 */
@ManagedBean(name = "menu")
@ViewScoped
public class MenuController {

    private Map<String, String> viewMap = new HashMap<>();
    private String currentView;

    public MenuController() {
        viewMap.put("usuarios", "Usuarios.xhtml");
        viewMap.put("blogs", "blogs.xhtml");
        viewMap.put("readers", "readers.xhtml");
        viewMap.put("ReaderBlog", "ReaderBlog.xhtml");
    }

    public void setCurrentView(String viewKey) {
        currentView = viewMap.get(viewKey);
    }

    public String getCurrentView() {
        return currentView;
    }
    
     public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
}
