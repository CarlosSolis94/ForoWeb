
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Carlos Solis
 */
@Entity
@Table(name = "readers")
public class Reader implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "CHAR(1) DEFAULT 'A'")
    private char status;
    
     @ManyToMany(mappedBy = "readers")
    private List<Blog> blogs;

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
     
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getState() {
        return status;
    }

    public void setState(char state) {
        this.status = state;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
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
        final Reader other = (Reader) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reader{" + "id=" + id + '}';
    }
    
    
}
