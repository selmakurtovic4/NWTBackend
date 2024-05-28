package com.hoperise.grpc.service;

import com.hoperise.grpc.model.Event;
import com.hoperise.grpc.repository.EventRepository;
import com.hoperise.proto.EventRequest;
import com.hoperise.proto.EventResponse;
import com.hoperise.proto.EventServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@GrpcService
public class EventService extends EventServiceGrpc.EventServiceImplBase {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void logEvent(EventRequest request, StreamObserver<EventResponse> responseObserver) {

        String timestamp = request.getTimestamp();
        String microserviceName = request.getMicroserviceName();
        String user = request.getUser();
        String action = request.getAction();
        String resource = request.getResource();
        String responseType = request.getResponseType();

        String detailedResponse = String.format(
                "Received event: Action=%s, Resource=%s, Service=%s, User=%s, Timestamp=%s, ResponseType=%s",
                action, resource, microserviceName, user, timestamp, responseType);
        System.out.println(detailedResponse);

        eventRepository.save(new Event(timestamp, microserviceName, user, action, resource, responseType));

        String responseMessage = "Event logged successfully";
        EventResponse response = EventResponse.newBuilder()
                .setResponse(responseMessage)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
