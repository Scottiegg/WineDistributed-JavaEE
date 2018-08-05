package entity;

import entity.WcdInventory;
import entity.WcdWineOrders;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-04T21:23:58")
@StaticMetamodel(WcdWines.class)
public class WcdWines_ { 

    public static volatile SingularAttribute<WcdWines, String> vineyard;
    public static volatile SingularAttribute<WcdWines, String> stateOrigin;
    public static volatile CollectionAttribute<WcdWines, WcdWineOrders> wcdWineOrdersCollection;
    public static volatile SingularAttribute<WcdWines, WcdInventory> wcdInventory;
    public static volatile SingularAttribute<WcdWines, String> variety;
    public static volatile SingularAttribute<WcdWines, String> vintage;
    public static volatile SingularAttribute<WcdWines, String> name;
    public static volatile SingularAttribute<WcdWines, String> countryOrigin;
    public static volatile SingularAttribute<WcdWines, String> region;
    public static volatile SingularAttribute<WcdWines, Integer> wineId;

}