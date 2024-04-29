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
import javax.ejb.Local;

/**
 *
 * @author Carlos Solis
 */
@Local
public interface BlogReaderFacadeLocal {

    void create(BlogReader blogReader);

    void edit(BlogReader blogReader);

    void remove(BlogReader blogReader);

    BlogReader find(Object id);

    List<BlogReader> findAll();

    List<BlogReader> findRange(int[] range);

    int count();
    
    public List<Blog> getAvailableBlogs();
    
    public List<Reader> getReadersByBlogId(int blogId);
    
    List<Reader> obtenerLectoresDisponiblesParaBlog(int blogId);
    
}
