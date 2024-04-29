/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import entities.Blog;
import entities.BlogReader;
import entities.Reader;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Carlos Solis
 */
@Stateless
public class BlogReaderFacade extends AbstractFacade<BlogReader> implements BlogReaderFacadeLocal {

    @PersistenceContext(unitName = "ForoWebPU")
    private EntityManager em;
    
    private Reader selectedReader; // Variable para almacenar el lector seleccionado
    private int selectedBlogId;    // Variable para almacenar el ID del blog seleccionado

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BlogReaderFacade() {
        super(BlogReader.class);
    }
    
    public List<Blog> getAvailableBlogs() {
        TypedQuery<Blog> query = em.createQuery(
            "SELECT b FROM Blog b", Blog.class);
        return query.getResultList();
    }

    public String save() {
        BlogReader blogReader = new BlogReader();
        blogReader.setReader(selectedReader);
        blogReader.setBlog(em.find(Blog.class, selectedBlogId));
        em.persist(blogReader);
        return "success";
    }
    
    public List<Reader> obtenerLectoresDisponiblesParaBlog(int blogId) {
        TypedQuery<Reader> query = em.createQuery(
            "SELECT r FROM Reader r WHERE r.id NOT IN " +
            "(SELECT br.reader.id FROM BlogReader br WHERE br.blog.id = :blogId)",
            Reader.class);
        query.setParameter("blogId", blogId);
        return query.getResultList();
    }
    
     public List<Blog> obtenerBlogsSinLectoresAsociados() {
        TypedQuery<Blog> query = em.createQuery(
            "SELECT b FROM Blog b WHERE b.id NOT IN " +
            "(SELECT br.blog.id FROM BlogReader br)",
            Blog.class);
        return query.getResultList();
    }

    @Override
    public List<Reader> getReadersByBlogId(int blogId) {
        TypedQuery<Reader> query = em.createQuery(
        "SELECT br.reader FROM BlogReader br WHERE br.blog.id = :blogId", Reader.class);
        query.setParameter("blogId", blogId);
        return query.getResultList();
    }
    
}
