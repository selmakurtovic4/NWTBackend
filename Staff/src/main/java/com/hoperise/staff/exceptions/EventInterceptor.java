package com.hoperise.staff.exceptions;

import com.hoperise.proto.EventRequest;
import com.hoperise.proto.EventResponse;
import com.hoperise.proto.EventServiceGrpc;
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
        EventServiceGrpc.EventServiceBlockingStub stub = EventServiceGrpc.newBlockingStub(channel);
        EventResponse eventResponse = stub.logEvent(
                EventRequest.newBuilder()
                        .setTimestamp(LocalDateTime.now().toString())
                        .setMicroserviceName("appointment")
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
