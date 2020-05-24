package br.com.alura.ecommerce;

import java.math.BigDecimal;

public class Order {
	@Override
	public String toString() {
		return "Service Users - Order [userId=" + userId + ", orderId=" + orderId + ", amount=" + amount + ", email=" + email + "]";
	}

	private final String userId, orderId;
	private final BigDecimal amount;
	private String email;
	
	
	public Order(String userId, String orderId, BigDecimal amount, String email) {
		this.userId = userId;
		this.orderId = orderId;
		this.amount = amount;
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
	
	public String getUserId() {
		return userId;
	}
}
