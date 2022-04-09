package com.creativehub.backend.services;

import java.util.UUID;

public interface ConsumerService {

    void receivedMessage(UUID id);
}
