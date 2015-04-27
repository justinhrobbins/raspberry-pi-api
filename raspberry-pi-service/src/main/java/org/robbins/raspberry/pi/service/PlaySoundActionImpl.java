package org.robbins.raspberry.pi.service;

import org.apache.commons.io.FilenameUtils;
import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiAction;
import org.robbins.raspberry.pi.model.SoundFiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service("playSoundAction")
public class PlaySoundActionImpl implements PiActionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaySoundActionImpl.class);

    @Value("${base.sound.files.directory}")
    private String baseSoundDirectory;

    @Value("#{'${valid.sound.file.extensions}'.split(',')}")
    private List<String> validFileExtensions;

    @Override
    public void invokeAction(final PiAction action) throws RaspberryPiAppException {
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

    public SoundFiles getSoundFiles() throws RaspberryPiAppException {
        final SoundFiles soundFiles = new SoundFiles();

        try {
            final Path directoryPath = Paths.get(new URI("file://" + baseSoundDirectory));

            if (Files.isDirectory(directoryPath)) {
                final DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath);
                for (Path path : stream) {
                    final String fileName = path.getFileName().toString();
                    if (validFileExtensions.contains(FilenameUtils.getExtension(fileName))) {
                        soundFiles.getSoundFiles().add(fileName);
                    }
                }
            }
        }
        catch (URISyntaxException|IOException e) {
            throw new RaspberryPiAppException(e.getMessage());
        }
        return soundFiles;
    }
}
