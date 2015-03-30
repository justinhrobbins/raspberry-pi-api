package org.robbins.raspberry.pi.service;

import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class PlaySoundAction implements PiActionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaySoundAction.class);

    @Value("${base.sound.files.directory}")
    private String BASE_SOUND_DIRECTORY;

    @Override
    public void invokeAction(PiAction action) throws RaspberryPiAppException {
        LOGGER.debug("Invoking action: {}", action);

        try {
            InputStream in = new FileInputStream(BASE_SOUND_DIRECTORY + action.getValue());

            // create an audiostream from the inputstream
            AudioStream audioStream = new AudioStream(in);

            // play the audio clip with the audioplayer class
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            throw new RaspberryPiAppException(e.getMessage(), e);
        }
    }
}
