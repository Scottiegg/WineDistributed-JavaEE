package entity;

import entity.WcdOrders;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-04T21:23:58")
@StaticMetamodel(WcdCustomers.class)
public class WcdCustomers_ { 

    public static volatile SingularAttribute<WcdCustomers, String> addrState;
    public static volatile SingularAttribute<WcdCustomers, String> addr2;
    public static volatile SingularAttribute<WcdCustomers, String> addr1;
    public static volatile SingularAttribute<WcdCustomers, Integer> userId;
    public static volatile SingularAttribute<WcdCustomers, String> addrCity;
    public static volatile SingularAttribute<WcdCustomers, String> addrZipcode;
    public static volatile SingularAttribute<WcdCustomers, String> password;
    public static volatile SingularAttribute<WcdCustomers, String> phone;
    public static volatile SingularAttribute<WcdCustomers, String> appGroup;
    public static volatile SingularAttribute<WcdCustomers, String> name;
    public static volatile SingularAttribute<WcdCustomers, String> company;
    public static volatile SingularAttribute<WcdCustomers, String> email;
    public static volatile CollectionAttribute<WcdCustomers, WcdOrders> wcdOrdersCollection;

}