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
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "piSchedules")
@XmlAccessorType(XmlAccessType.FIELD)
public class PiSchedules
{
    @XmlElement(name="piSchedule")
    private Set<PiSchedule> piSchedules = new HashSet<>();

    public Set<PiSchedule> getPiSchedules() {
        return piSchedules;
    }

    public void setPiSchedules(Set<PiSchedule> piSchedules) {
        this.piSchedules = piSchedules;
    }
}
