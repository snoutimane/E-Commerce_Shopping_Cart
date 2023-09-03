package com.Product.model;

public class CartItemDTO {
	private int productId;
    private int quantity;
    
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public CartItemDTO(int productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	public CartItemDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
}
