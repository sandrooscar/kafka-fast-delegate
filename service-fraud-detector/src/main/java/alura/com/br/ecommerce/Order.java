package alura.com.br.ecommerce;

import java.math.BigDecimal;

public class Order {
	private final String userId, orderId;
	public String getUserId() {
		return userId;
	}

	private final BigDecimal amount;
	
	

	public Order(String userId, String orderId, BigDecimal amount) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.amount = amount;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "Order [userId=" + userId + ", orderId=" + orderId + ", amount=" + amount + "]";
	}
}
