package Controller;

import Model.BlogFacadeLocal;
import Model.BlogReaderFacade;
import Model.BlogReaderFacadeLocal;
import Model.ReaderFacadeLocal;
import entities.Blog;
import entities.BlogReader;
import entities.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Carlos Solis
 */
@ManagedBean(name = "blogReaderBean")
@SessionScoped
public class BlogReaderController {

    @EJB
    private BlogReaderFacadeLocal blogReaderFacade;

    @EJB
    private ReaderFacadeLocal readerFacadeLocal;
    @EJB
    private BlogFacadeLocal blogFacadeLocal;

    private Integer blogSeleccionado;

    private List<Integer> readSeleccionado = new ArrayList<>();
    ;

    private List<Blog> listaBlog;

    private List<Reader> listaReader;

    private List<BlogReader> blogReaders;

    private boolean habilitar = true;

    public List<BlogReader> getBlogReaders() {
        blogReaders = blogReaderFacade.findAll();
        return blogReaders;
    }

    public void setBlogReaders(List<BlogReader> blogReaders) {
        this.blogReaders = blogReaders;
    }

    public boolean isHabilitar() {
        return habilitar;
    }

    public void setHabilitar(boolean habilitar) {
        this.habilitar = habilitar;
    }

    public List<Integer> getReadSeleccionado() {
        return readSeleccionado;
    }

    public void setReadSeleccionado(List<Integer> readSeleccionado) {
        this.readSeleccionado = readSeleccionado;
    }

    public Integer getBlogSeleccionado() {
        return blogSeleccionado;
    }

    public void setBlogSeleccionado(Integer blogSeleccionado) {
        this.blogSeleccionado = blogSeleccionado;
    }

    public List<Blog> getListaBlog() {

        listaBlog = blogReaderFacade.getAvailableBlogs();
        return listaBlog;
    }

    public void setListaBlog(List<Blog> listaBlog) {
        this.listaBlog = listaBlog;
    }

    public List<Reader> getListaReader() {

        return listaReader;
    }

    public void setListaReader(List<Reader> listaReader) {
        this.listaReader = listaReader;
    }

    public void actualizarLectores(AjaxBehaviorEvent event) {

        if (blogSeleccionado != null) {
            listaReader = blogReaderFacade.obtenerLectoresDisponiblesParaBlog(blogSeleccionado);
        } else {
            listaReader = Collections.emptyList();
        }
    }

    /*public void guardar() {
        if (readSeleccionado == null || readSeleccionado.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar lectores"));
            return;
        }
        try {
            Blog blog = blogFacadeLocal.find(blogSeleccionado);
        for (Integer readerId : readSeleccionado) {
            BlogReader blogReader = new BlogReader();
            blogReader.setReader(readerFacadeLocal.find(readerId));
            blogReader.setBlog(blog);
            blogReaderFacade.create(blogReader);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Lectores asociados"));
        }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un error"));
        }

        // Limpiar la lista de lectores seleccionados
        readSeleccionado.clear();
    }*/
    public void guardar() {
        if (readSeleccionado == null || readSeleccionado.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar lectores"));
            return;
        }

        try {
            Blog blog = blogFacadeLocal.find(blogSeleccionado);

            for (Integer readerId : readSeleccionado) {
                Reader reader = readerFacadeLocal.find(readerId);
                if (!blog.getReaders().contains(reader)) {
                    blog.getReaders().add(reader);
                    reader.getBlogs().add(blog);
                }
            }

            blogFacadeLocal.edit(blog);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Lectores asociados"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un error"));
        }

        // Limpiar la lista de lectores seleccionados
        readSeleccionado.clear();
    }

    public List<Blog> getBlogs() {
        return blogFacadeLocal.findAll();
    }

    public List<Reader> getReadersByBlogId(int blogId) {
        Blog blog = blogFacadeLocal.find(blogId);
        if (blog != null) {
            return blog.getReaders();
        }
        return Collections.emptyList();
    }
    
    public void eliminarLectorDeBlog(int blogId, int readerId) {
    Blog blog = blogFacadeLocal.find(blogId);
    if (blog != null) {
        Reader reader = readerFacadeLocal.find(readerId);
        if (reader != null && blog.getReaders().contains(reader)) {
            blog.getReaders().remove(reader);
            reader.getBlogs().remove(blog);
            blogFacadeLocal.edit(blog);
        }
    }
}

}
