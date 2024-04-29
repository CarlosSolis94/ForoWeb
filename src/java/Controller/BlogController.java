/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BlogFacadeLocal;
import entities.Blog;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Carlos Solis
 */
@ManagedBean(name = "blogBean")
@SessionScoped
public class BlogController {

    @EJB
    private BlogFacadeLocal blogFacadeLocal;
    private List<Blog> listaBlogs;
    private Blog blog;
    private Blog blogParaEliminar ;

    @PostConstruct
    public void init() {
        this.blog = new Blog();
    }

    public Blog getBlogParaEliminar() {
        return blogParaEliminar;
    }

    public void setBlogParaEliminar(Blog blogParaEliminar) {
        this.blogParaEliminar = blogParaEliminar;
    }
    
    public List<Blog> getListaBlogs() {
        listaBlogs = blogFacadeLocal.findAll();
        return listaBlogs;
    }

    public void setListaBlogs(List<Blog> listaBlogs) {
        this.listaBlogs = listaBlogs;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public void crearBlog() {
        if (validarCampos()) {
            try {
                blogFacadeLocal.create(blog);
                blog = new Blog();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Blog creado correctamente"));
                RequestContext.getCurrentInstance().execute("PF('dialogoAgregarBlog').hide()");
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al crear blog"));
            }

        }
    }

    private boolean validarCampos() {
        if (blog.getTitle() == null || blog.getTitle().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El titulo es obligatorio"));
            return false;
        }
        if (blog.getDescription() == null || blog.getDescription().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La descripción es obligatoria"));
            return false;
        }
        return true;
    }

    public void editarBlog() {
        if (validarCampos()) {
            try {
                blogFacadeLocal.edit(blog);
                blog = new Blog();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Blog editado correctamente"));
                RequestContext.getCurrentInstance().execute("PF('dialogoEditarBlog').hide()");
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al editar blog"));
            }
        }
    }

    public void eliminarBlog(Blog blogElimiar) {
        try {
            blogFacadeLocal.remove(blogElimiar);
            blog = new Blog();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Blog eliminado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar blog"));
        }
    }

}
