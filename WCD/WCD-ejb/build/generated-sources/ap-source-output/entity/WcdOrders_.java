package entity;

import entity.WcdCustomers;
import entity.WcdWineOrders;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-04T21:23:58")
@StaticMetamodel(WcdOrders.class)
public class WcdOrders_ { 

    public static volatile SingularAttribute<WcdOrders, Date> delDate;
    public static volatile CollectionAttribute<WcdOrders, WcdWineOrders> wcdWineOrdersCollection;
    public static volatile SingularAttribute<WcdOrders, Integer> orderId;
    public static volatile SingularAttribute<WcdOrders, Date> orderDate;
    public static volatile SingularAttribute<WcdOrders, WcdCustomers> userId;

}