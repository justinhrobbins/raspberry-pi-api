/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
package org.robbins.raspberry.pi.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "SoundFiles")
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
