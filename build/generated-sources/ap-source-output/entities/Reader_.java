package entities;

import entities.Blog;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-29T12:44:45")
@StaticMetamodel(Reader.class)
public class Reader_ { 

    public static volatile ListAttribute<Reader, Blog> blogs;
    public static volatile SingularAttribute<Reader, String> name;
    public static volatile SingularAttribute<Reader, Integer> id;
    public static volatile SingularAttribute<Reader, Character> status;

}