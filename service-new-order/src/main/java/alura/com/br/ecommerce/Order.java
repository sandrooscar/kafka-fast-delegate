package alura.com.br.ecommerce;

import java.math.BigDecimal;

public class Order {
	@Override
	public String toString() {
		return "New Order Main - Order [userId=" + userId + ", orderId=" + orderId + ", amount=" + amount + ", email=" + email + "]";
	}


	private final String userId, orderId;
	private final BigDecimal amount;
	private final String email;
	
	
	public Order(String userId, String orderId, BigDecimal amount, String email) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.amount = amount;
		this.email = email;
	}

}
