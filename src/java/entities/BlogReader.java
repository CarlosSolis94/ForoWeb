/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Carlos Solis
 */
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "blogs_readers")
public class BlogReader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "r_id", referencedColumnName = "id")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "b_id", referencedColumnName = "id")
    private Blog blog;

    @Transient
    private List<Reader> availableReaders;

    @Transient
    private List<Reader> selectedReaders;

    @Transient
    private int selectedBlogId;

    public int getSelectedBlogId() {
        return selectedBlogId;
    }

    public void setSelectedBlogId(int selectedBlogId) {
        this.selectedBlogId = selectedBlogId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Reader> getAvailableReaders() {
        return availableReaders;
    }

    public void setAvailableReaders(List<Reader> availableReaders) {
        this.availableReaders = availableReaders;
    }

    public List<Reader> getSelectedReaders() {
        return selectedReaders;
    }

    public void setSelectedReaders(List<Reader> selectedReaders) {
        this.selectedReaders = selectedReaders;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BlogReader other = (BlogReader) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BlogReader{" + "id=" + id + '}';
    }

}
