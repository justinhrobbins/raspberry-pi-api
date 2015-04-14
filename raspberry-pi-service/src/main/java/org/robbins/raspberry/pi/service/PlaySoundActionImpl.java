package org.robbins.raspberry.pi.service;

import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

@Service("playSoundAction")
public class PlaySoundActionImpl implements PiActionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaySoundActionImpl.class);

    @Value("${base.sound.files.directory}")
    private String baseSoundDirectory;

    @Override
    public void invokeAction(PiAction action) throws RaspberryPiAppException {
        LOGGER.debug("Invoking action: {}", action);

        final String soundFile = baseSoundDirectory + action.getValue();
        LOGGER.debug("Playing sound file: '{}'", soundFile);

        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(soundFile));
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // play at maximum volume
            final FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(gainControl.getMaximum());

            clip.start();

        } catch (Exception e) {
            throw new RaspberryPiAppException(e.getMessage(), e);
        }
    }

    public void setBaseSoundDirectory(String baseSoundDirectory) {
        this.baseSoundDirectory = baseSoundDirectory;
    }
}
