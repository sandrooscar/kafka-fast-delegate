package alura.com.br.ecommerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

	public static <V> void main(String[] args) throws InterruptedException, ExecutionException {
		try (KafkaDispatcher<Order> orderDispatcher = new KafkaDispatcher<>()) {
			String email = Math.random()+"@teste.com";
			try (KafkaDispatcher<Email> emailDispatcher = new KafkaDispatcher<>()) {
				for (int i = 0; i < 10; i++) {

					String orderId = UUID.randomUUID().toString();
					BigDecimal amount = new BigDecimal(Math.random() * 5000 + 1);
					Order order = new Order(orderId, amount, email);
					System.out.println(order);
					orderDispatcher.send("ECOMMERCE_NEW_ORDER", email, order);

					Email emailCode = new Email("Order", "Thank you for your order! We are processing your order!");
					emailDispatcher.send("ECOMMERCE_SEND_EMAIL", email, emailCode);
				}
			}
		}
	}

}
