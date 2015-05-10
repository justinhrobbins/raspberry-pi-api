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

    GpioController gpio;
    GpioPinDigitalOutput pin;

    @Override
    public void invokeAction(final PiAction action) throws RaspberryPiAppException {
        LOGGER.debug("Blinking Light service for action: {}", action);

        try {
            // create gpio controller
            gpio = GpioFactory.getInstance();

            // provision gpio pin #01 as an output pin and turn on
            pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);

            // set shutdown state for this pin
            pin.setShutdownOptions(true, PinState.LOW);

            blinkLight(pin, Integer.parseInt(action.getValue()));
        }
        catch (Exception e) {
            LOGGER.debug("Error while blinking: {}", e.getMessage());
            throw new RaspberryPiAppException(e.getMessage());
        }
        finally {
            // stop all GPIO activity/threads by shutting down the GPIO controller
            // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
            if (gpio != null) {
                LOGGER.debug("Shutting down GPIO");
                gpio.shutdown();
                gpio.unprovisionPin(pin);
            }
            else {
                LOGGER.debug("Unable to shutdown GPIO.  There is no GPIO instance");
            }
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
