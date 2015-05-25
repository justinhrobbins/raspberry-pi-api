package org.robbins.raspberry.pi.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "soundFiles")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoundFiles
{
    @XmlElement(name="soundFile")
    private List<String> soundFiles = new ArrayList<>();

    public List<String> getSoundFiles() {
        return soundFiles;
    }

    public void setSoundFiles(List<String> soundFiles) {
        this.soundFiles = soundFiles;
    }
}
