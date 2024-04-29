package entities;

import entities.Blog;
import entities.Reader;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-29T12:44:45")
@StaticMetamodel(BlogReader.class)
public class BlogReader_ { 

    public static volatile SingularAttribute<BlogReader, Reader> reader;
    public static volatile SingularAttribute<BlogReader, Integer> id;
    public static volatile SingularAttribute<BlogReader, Blog> blog;

}