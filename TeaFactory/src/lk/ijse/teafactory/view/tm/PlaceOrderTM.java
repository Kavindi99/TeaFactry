package lk.ijse.teafactory.view.tm;

import javafx.scene.control.Button;

/**
 * author - kavindi
 * version - 1.0.0 10:28 PM 11/29/2022
 **/
public class PlaceOrderTM {
    private String productCode;
    private String description;
    private int qty;
    private double unitPrice;
    private double total;
    private Button btnDelete;

    public PlaceOrderTM() {
    }

    public PlaceOrderTM(String productCode, String description, int qty, double unitPrice, double total, Button btnDelete) {
        this.productCode = productCode;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
        this.btnDelete = btnDelete;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }


    @Override
    public String toString() {
        return "PlaceOrderTM{" +
                "productCode='" + productCode + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
