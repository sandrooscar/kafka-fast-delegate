package alura.com.br.ecommerce;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorService {
	private final KafkaDispatcher<Order> orderDispatcher = new KafkaDispatcher<>();
	
	public static void main(String[] args) {
		executarFraud();
	}

	public static void executarFraud() {
		FraudDetectorService fraudService = new FraudDetectorService();
		try (KafkaService<Order> service = new KafkaService<>(Order.class, fraudService.getClass().getSimpleName(), "ECOMMERCE_NEW_ORDER",
				fraudService::parse,
				new HashMap<>())) {
			service.run();
		}
	}

	protected void parse(ConsumerRecord<String, Order> record) throws InterruptedException, ExecutionException {
		System.out.println("----------------------------------------");
		System.out.println("Processing new order, checking for fraud" + " " + new Date());
		System.out.println(record.key());
		System.out.println(record.value());
		System.out.println(record.partition());
		System.out.println(record.offset());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Order order = record.value();
		if (isFraud(order)){
			//pretenting that the fraud happens when the amount is >- 4500
			System.out.println("Order is a fraud !!! "+order);
			orderDispatcher.send("ECOMMERCE_ORDER_REJECTED", order.getEmail(), order);
		}else {
			System.out.println("Approved: "+order);
			orderDispatcher.send("ECOMMERCE_ORDER_APPROVED", order.getEmail(), order);
		}
	}

	private boolean isFraud(Order order) {
		return order.getAmount().compareTo(new BigDecimal("4500")) >= 0;
	}

}
