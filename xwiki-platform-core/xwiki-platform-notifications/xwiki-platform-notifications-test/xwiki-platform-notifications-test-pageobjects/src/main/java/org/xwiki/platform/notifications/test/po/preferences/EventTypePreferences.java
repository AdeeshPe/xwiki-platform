/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.platform.notifications.test.po.preferences;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.xwiki.test.ui.po.BootstrapSwitch;
import org.xwiki.stability.Unstable;

/**
 * Wrap the notification preferences of a given event type.
 *
 * @version $Id$
 * @since 9.7RC1
 */
@Unstable
public class EventTypePreferences
{
    private WebElement webElement;

    private String eventType;

    private BootstrapSwitch alertSwitch;

    private BootstrapSwitch emailSwitch;

    /**
     * Construct an EventTypePreferences.
     * @param webElement table row of the event type
     */
    public EventTypePreferences(WebElement webElement)
    {
        this.webElement           = webElement;
        this.eventType            = webElement.getAttribute("data-eventtype");
        this.alertSwitch          = getSwitch("alert");
        this.emailSwitch          = getSwitch("email");
    }

    /**
     * @return the id of the event type
     */
    public String getEventType()
    {
        return eventType;
    }

    /**
     * @return the description of the event type
     * @throws Exception if the event type is not displayed
     */
    public String getEventTypeDescription() throws Exception
    {
        if (!webElement.isDisplayed()) {
            throw new Exception(String.format(
                    "The event type [%s] is not displayed, so we cannot read its description.", eventType));
        }
        return webElement.findElement(By.cssSelector("td:first-child")).getText();
    }

    /**
     * Set the state of the alert switch.
     * @param state the expected state
     * @throws Exception if the state is not valid
     */
    public void setAlertState(BootstrapSwitch.State state) throws Exception
    {
        this.alertSwitch.setState(state);
    }

    /**
     * Set the state of the email switch.
     * @param state the expected state
     * @throws Exception if the state is not valid
     */
    public void setEmailState(BootstrapSwitch.State state) throws Exception
    {
        this.emailSwitch.setState(state);
    }

    /**
     * @return the state of the alert switch
     */
    public BootstrapSwitch.State getAlertState()
    {
        return alertSwitch.getState();
    }

    /**
     * @return the state of the email switch
     */
    public BootstrapSwitch.State getEmail()
    {
        return emailSwitch.getState();
    }

    private BootstrapSwitch getSwitch(String format)
    {
        return new BootstrapSwitch(
                webElement.findElement(
                        By.cssSelector(
                                String.format(
                                        "td.notificationTypeCell[data-format='%s'] .bootstrap-switch",
                                        format
                                )
                        )
                )
        );
    }
}