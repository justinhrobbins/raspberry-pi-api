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
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "piAction")
@XmlAccessorType(XmlAccessType.FIELD)
public class PiAction
{
	public String name;
	public String value;

	public PiAction()
	{}

	public PiAction(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(final String valie)
	{
		this.value = valie;
	}

    @Override
    public String toString() {
        return "PiAction{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
