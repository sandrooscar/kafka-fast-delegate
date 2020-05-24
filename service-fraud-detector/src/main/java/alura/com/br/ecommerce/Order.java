package alura.com.br.ecommerce;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
	private final String orderId;
	private final BigDecimal amount;
	private final String email;
	private final LocalDateTime orderDate;

	public Order(String orderId, BigDecimal amount, String email, LocalDateTime orderDate) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.email = email;
		this.orderDate = orderDate;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", amount=" + amount + ", email=" + email + ", orderDate=" + orderDate
				+ "]";
	}
	
}
