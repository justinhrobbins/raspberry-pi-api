package org.robbins.raspberry.pi.service;

import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiAction;
import org.robbins.raspberry.pi.model.RegisteredActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PiActionServiceDelegator {

    @Autowired
    private Map<String, PiActionService> actionServiceMap;

    @Async
    public void invokeAction(final PiAction action) throws RaspberryPiAppException {
        PiActionService service = actionServiceMap.get(action.getName());
        service.invokeAction(action);
    }

    public RegisteredActions listRegisteredActions() {
        RegisteredActions registeredActions = new RegisteredActions();
        registeredActions.setActions(actionServiceMap.keySet());
        return registeredActions;
    }
}
