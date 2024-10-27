package dev.gustavorh.gestioninventario.domain.models;

import java.util.Date;

public class InventoryMovement {
    private Long id;
    private Long productId;
    private Long pointOfSaleId;
    private String movementType;
    private Integer quantity;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPointOfSaleId() {
        return pointOfSaleId;
    }

    public void setPointOfSaleId(Long pointOfSaleId) {
        this.pointOfSaleId = pointOfSaleId;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
