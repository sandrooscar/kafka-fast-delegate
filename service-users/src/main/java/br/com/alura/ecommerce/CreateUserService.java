package br.com.alura.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import alura.com.br.ecommerce.KafkaService;

public class CreateUserService {
	
	private Connection connection;

	public CreateUserService() throws SQLException {
		String url = "jdbc:sqlite:target/users_database.db";
		this.connection = DriverManager.getConnection(url);
		connection.createStatement().execute("create table IF NOT EXISTS users ( "
				+ "uuid varchar(200) primary key, "
				+ "email varchar(200) )");
	}

	public static void main(String[] args) throws SQLException {
		executarCreateUser();
	}

	public static void executarCreateUser() throws SQLException {
		CreateUserService userService = new CreateUserService();
		try (KafkaService<Order> service = new KafkaService<>(Order.class, userService.getClass().getSimpleName(), "ECOMMERCE_NEW_ORDER",
				userService::parse,
				new HashMap<>())) {
			service.run();
		}
	}

	protected void parse(ConsumerRecord<String, Order> record) throws SQLException {
		System.out.println("----------------------------------------");
		System.out.println("Processing new order, checking for new user");
		System.out.println(record.value());
		Order order = record.value();
		if (isNewUser(order.getEmail())){
			insertNewUser(order.getUserId(), order.getEmail());
		}
	}

/**
 * Necessary treatment for userid, in the next class this problem will be corrected 
 * @param email
 * @param userId 
 * @throws SQLException
 */
	private void insertNewUser(String email, String userId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("insert into users (uuid, email) "
				+ "values (?, ?) ");
		ps.setString(1, userId);
		ps.setString(2, email);
		ps.execute();
		System.out.println("New user "+email+" insert be sucessful!");
	}

	private boolean isNewUser(String email) throws SQLException {
		PreparedStatement psExists = connection.prepareStatement("select uuid from users "
				+ "where email = ? limit 1");
		psExists.setString(1, email);
		ResultSet results = psExists.executeQuery();
		return !results.next();
	}

}
