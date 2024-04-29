/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.utils.SessionUtils;
import Model.EcriptadorServicie;
import Model.UserFacadeLocal;
import entities.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private UserFacadeLocal userFacade;
    @EJB
    private EcriptadorServicie ecriptadorServicie;
    private List<User> listaUsuarios;
    private User usuarios;

    public List<User> getListaUsuarios() {
        this.listaUsuarios = userFacade.findAll();
        return listaUsuarios;
    }

    public void setListaUsuarios(List<User> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public User getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(User usuarios) {
        this.usuarios = usuarios;
    }

    @PostConstruct
    public void init() {
        this.usuarios = new User();
    }

    public void crearUsuario() {
        if (validarCampos()) {
            try {
                userFacade.create(usuarios);
                usuarios = new User();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario creado correctamente"));
                RequestContext.getCurrentInstance().execute("PF('dialogoAgregarUsuario').hide()");
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al crear usuario"));
            }
        }
    }

    private boolean validarCampos() {
        if (usuarios.getUser() == null || usuarios.getUser().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El usuario es obligatorio"));
            return false;
        }
        if (usuarios.getPassword() == null || usuarios.getPassword().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El password es obligatorio"));
            return false;
        } else {
            usuarios.setPassword(ecriptadorServicie.encriptarContrasena(usuarios.getPassword()));
        }
        return true;
    }

    //METODO PARA VALIDAR LA SESION
    public String login() {
        User usuario = userFacade.validar(usuarios.getUser(), usuarios.getPassword());
        if (usuario != null) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", usuario.getUser());
            return "success";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Error",
                            "Usuario o contraseña incorrecta"));
            return "login";
        }
    }
    
    //METODO PARA CERRAR SESSION
    public String logout() {
        System.out.println("Cerrando sesión...");
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
}
