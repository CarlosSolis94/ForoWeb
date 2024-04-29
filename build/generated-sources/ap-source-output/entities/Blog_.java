package entities;

import entities.Reader;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-29T12:44:45")
@StaticMetamodel(Blog.class)
public class Blog_ { 

    public static volatile ListAttribute<Blog, Reader> readers;
    public static volatile SingularAttribute<Blog, String> description;
    public static volatile SingularAttribute<Blog, Integer> id;
    public static volatile SingularAttribute<Blog, String> title;
    public static volatile SingularAttribute<Blog, Character> status;

}