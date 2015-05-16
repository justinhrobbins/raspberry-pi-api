package org.robbins.raspberry.pi.service;

import com.pi4j.io.gpio.*;
import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("blinkLight")
public class BlinkLightActionImpl implements PiActionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlinkLightActionImpl.class);

    @Value("${blink.delay.in.milliseconds}")
    private long blinkDelayInMilliseconds;

    @Value("${blink.duration.in.seconds}")
    private long blinkDurationInSeconds;

    private static final String RED = "RED";
    private static final String GREEN = "GREEN";

    @Override
    public void invokeAction(final PiAction action) throws RaspberryPiAppException {
        LOGGER.debug("Blinking Light service for action: {}", action);

        GpioController gpio = null;
        GpioPinDigitalOutput pin = null;

        try {
            // create gpio controller
            gpio = GpioFactory.getInstance();

            // provision gpio pin  as an output pin
            pin = gpio.provisionDigitalOutputPin(convertActionValueToPin(action.getValue()));

            blinkLight(pin);
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

    private Pin convertActionValueToPin(final String value) throws RaspberryPiAppException {
        final String upperCaseValue = value.toUpperCase();

        switch (upperCaseValue) {
            case RED:
                return RaspiPin.GPIO_01;
            case GREEN:
                return RaspiPin.GPIO_02;
            default:
                throw new RaspberryPiAppException("Invalid light color: " + value);
        }
    }

    private void blinkLight(final GpioPinDigitalOutput pin) throws RaspberryPiAppException {

        LOGGER.debug("Blinking LED with delay: {}, and duration: {}", blinkDelayInMilliseconds, blinkDurationInSeconds);

        try {
            for (int i = 0; i < blinkDurationInSeconds; i++) {
                pin.high();

                Thread.sleep(blinkDelayInMilliseconds);

                pin.low();

                Thread.sleep(blinkDelayInMilliseconds);
            }
        }
        catch (InterruptedException e) {
            throw new RaspberryPiAppException(e.getMessage());
        }
    }
}
