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
