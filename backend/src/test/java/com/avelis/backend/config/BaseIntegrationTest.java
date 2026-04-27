//TODO: UNCOMMENT
//package com.avelis.backend.config;
//
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.utility.DockerImageName;
//
//@Testcontainers
//public abstract class BaseIntegrationTest {
//
//	private static final DockerImageName POSTGRES_IMAGE = DockerImageName.parse("postgres:15-alpine");
//
//	@Container
//	protected static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(POSTGRES_IMAGE)
//			.withDatabaseName("testdb").withUsername("test").withPassword("test");
//
//	@DynamicPropertySource
//	static void configureProperties(DynamicPropertyRegistry registry) {
//		registry.add("spring.datasource.url", postgres::getJdbcUrl);
//		registry.add("spring.datasource.username", postgres::getUsername);
//		registry.add("spring.datasource.password", postgres::getPassword);
//		registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
//
//		registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
//	}
//}