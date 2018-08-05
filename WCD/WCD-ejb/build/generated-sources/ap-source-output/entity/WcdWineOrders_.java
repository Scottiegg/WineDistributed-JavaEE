package entity;

import entity.WcdOrders;
import entity.WcdWineOrdersPK;
import entity.WcdWines;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-04T21:23:58")
@StaticMetamodel(WcdWineOrders.class)
public class WcdWineOrders_ { 

    public static volatile SingularAttribute<WcdWineOrders, BigDecimal> unitPrice;
    public static volatile SingularAttribute<WcdWineOrders, WcdWines> wcdWines;
    public static volatile SingularAttribute<WcdWineOrders, Integer> qty;
    public static volatile SingularAttribute<WcdWineOrders, WcdWineOrdersPK> wcdWineOrdersPK;
    public static volatile SingularAttribute<WcdWineOrders, WcdOrders> wcdOrders;

}