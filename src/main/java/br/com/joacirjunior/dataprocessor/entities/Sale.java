package br.com.joacirjunior.dataprocessor.entities;

import java.math.BigDecimal;
import java.util.List;

public class Sale extends DefaultLayout {

    private String saleId;
    private List<Item> item;
    private String salesmanName;
    private BigDecimal value;

    public Sale(String id, String saleId, List<Item> item, String salesmanName) {
        super();
        this.id = id;
        this.saleId = saleId;
        this.item = item;
        this.salesmanName = salesmanName;
        calculateValueForSale();
    }

    private void calculateValueForSale() {
        this.value = BigDecimal.ZERO;
        for (Item itemForSum : this.item) {
            this.value = this.value.add(itemForSum.getPrice());
        }

    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId='" + saleId + '\'' +
                ", item=" + item +
                ", salesmanName='" + salesmanName + '\'' +
                ", value=" + value +
                ", id='" + id + '\'' +
                '}';
    }

}
