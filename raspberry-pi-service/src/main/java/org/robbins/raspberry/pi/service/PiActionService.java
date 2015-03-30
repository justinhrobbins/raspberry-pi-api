package org.robbins.raspberry.pi.service;

import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiAction;

public interface PiActionService {

    void invokeAction(final PiAction action) throws RaspberryPiAppException;
}
