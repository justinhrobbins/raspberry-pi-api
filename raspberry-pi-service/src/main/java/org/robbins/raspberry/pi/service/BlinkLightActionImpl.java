package org.robbins.raspberry.pi.service;

import com.pi4j.io.gpio.*;
import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("blinkLight")
public class BlinkLightActionImpl implements PiActionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlinkLightActionImpl.class);

    @Override
    public void invokeAction(final PiAction action) throws RaspberryPiAppException {
        LOGGER.debug("Blinking Light service for action: {}", action);

        try {
            // create gpio controller
            final GpioController gpio = GpioFactory.getInstance();

            // provision gpio pin #01 as an output pin and turn on
            final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);

            // set shutdown state for this pin
            pin.setShutdownOptions(true, PinState.LOW);

            blinkLight(pin, Integer.getInteger(action.getValue()));

            // stop all GPIO activity/threads by shutting down the GPIO controller
            // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
            gpio.shutdown();
        }
        catch (Exception e) {
            throw new RaspberryPiAppException(e.getMessage());
        }
    }

    private void blinkLight(final GpioPinDigitalOutput pin, int blinkCount) throws RaspberryPiAppException {

        try {
            for (int i = 0; i < blinkCount; i++) {
                LOGGER.debug("--> GPIO state should be: ON");

                Thread.sleep(5000);

                // turn off gpio pin #01
                pin.low();
                LOGGER.debug("--> GPIO state should be: OFF");

                Thread.sleep(5000);
            }
        }
        catch (InterruptedException e) {
            throw new RaspberryPiAppException(e.getMessage());
        }
    }
}
