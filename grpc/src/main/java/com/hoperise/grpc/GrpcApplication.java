package com.hoperise.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GrpcApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
//		System.out.println("Starting GRPC server...");
//		Server server = ServerBuilder
//				.forPort(9090)
//				.addService(new EventService()).build();
//
//		server.start();
//
//		System.out.println("Server started at " + server.getPort());
//		server.awaitTermination();
		SpringApplication.run(GrpcApplication.class, args);
	}

}
