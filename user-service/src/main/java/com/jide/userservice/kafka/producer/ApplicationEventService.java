package com.jide.userservice.kafka.producer;

public interface ApplicationEventService {
    void publishEvent(EventType eventType, EventModel<?> domain);

    enum EventType {
        USER_REGISTRATION("USER_CREATED_TOPIC");

        private final String topic;

        EventType(String topic) {
            this.topic = topic;
        }

        public String getTopic() {
            return topic;
        }
    }
}
