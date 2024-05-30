package com.hoperise.patient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

public class EventInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        ManagedChannel channel= ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();
        com.hoperise.proto.EventServiceGrpc.EventServiceBlockingStub stub = com.hoperise.proto.EventServiceGrpc.newBlockingStub(channel);
        com.hoperise.proto.EventResponse eventResponse = stub.logEvent(
                com.hoperise.proto.EventRequest.newBuilder()
                        .setTimestamp(LocalDateTime.now().toString())
                        .setMicroserviceName("patient")
                        .setUser("admin")
                        .setAction(request.getMethod())
                        .setResource(request.getRequestURI())
                        .setResponseType(Integer.toString(response.getStatus()))
                        .build()
        );
        System.out.println(eventResponse.getResponse());
        channel.shutdown();
    }
}
