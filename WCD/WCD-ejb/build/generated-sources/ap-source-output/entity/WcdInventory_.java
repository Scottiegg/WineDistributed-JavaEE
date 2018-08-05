package entity;

import entity.WcdWines;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-04T21:23:58")
@StaticMetamodel(WcdInventory.class)
public class WcdInventory_ { 

    public static volatile SingularAttribute<WcdInventory, WcdWines> wcdWines;
    public static volatile SingularAttribute<WcdInventory, Integer> stockQty;
    public static volatile SingularAttribute<WcdInventory, Integer> qtySold;
    public static volatile SingularAttribute<WcdInventory, BigDecimal> currPrice;
    public static volatile SingularAttribute<WcdInventory, Integer> wineId;

}