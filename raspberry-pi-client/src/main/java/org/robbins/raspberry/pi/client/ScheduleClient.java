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
package org.robbins.raspberry.pi.client;

import org.robbins.raspberry.pi.model.PiSchedule;

public interface ScheduleClient
{
    void createSchedule(PiSchedule piSchedule);
    void deleteSchedule(String scheduleName);
}
