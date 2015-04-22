package org.robbins.raspberry.pi.client;

import org.robbins.raspberry.pi.model.PiAction;

public interface PiActionClient
{
    void postAction(final PiAction action);
}
