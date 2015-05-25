package org.robbins.raspberry.pi.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "piSchedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class PiSchedule implements Serializable
{
	public String scheduleName;
    public String actionName;
	public String actionValue;
    public String cronTrigger;

    public PiSchedule() {
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getTrigger() {
        return cronTrigger;
    }

    public void setTrigger(String cronTrigger) {
        this.cronTrigger = cronTrigger;
    }

    public String getActionName()
	{
		return actionName;
	}

	public void setActionName(final String actionName)
	{
		this.actionName = actionName;
	}

	public String getActionValue()
	{
		return actionValue;
	}

	public void setActionValue(final String actionValue)
	{
		this.actionValue = actionValue;
	}

    @Override
    public String toString() {
        return "PiSchedule{" +
                "scheduleName='" + scheduleName + '\'' +
                ", actionName='" + actionName + '\'' +
                ", actionValue='" + actionValue + '\'' +
                ", cronTrigger='" + cronTrigger + '\'' +
                '}';
    }
}
