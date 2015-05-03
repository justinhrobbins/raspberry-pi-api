package org.robbins.raspberry.pi.service;

import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("log")
public class LoggingActionImpl implements PiActionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingActionImpl.class);

    @Override
    public void invokeAction(final PiAction action) throws RaspberryPiAppException {
        LOGGER.debug("Logging service for action: {}", action);
    }
}
