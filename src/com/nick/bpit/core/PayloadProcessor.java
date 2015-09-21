/*
 * Copyright 2014 Wolfram Rittmeyer.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nick.bpit.core;

import com.nick.bpit.CcsClient;
import com.nick.bpit.helper.Config;
import java.util.HashMap;
import java.util.Map;

/**
 * All messages from the user have a specific format. The Action field defines,
 * what the action is about. An example is the action
 * com.grokkingandroid.sampleapp.samples.gcm.action.REGISTER, used to tell the
 * server about a newly registered user.
 * <br>
 * Any further fields are specific for the given action.
 */
public abstract class PayloadProcessor implements Config {

    public abstract void handleMessage(CcsMessage msg);

    void sendToAll(Map<String, String> regIdMap, CcsClient client, Map<String, String> payload, Map<String, String> notification) {

        for (String key : regIdMap.keySet()) {
            sendToMember(regIdMap.get(key), client, payload, notification);
        }
    }

    void sendToMember(String member, CcsClient client, Map<String, String> payload, Map<String, String> notification) {
        String jsonRequest
                = CcsClient.createJsonMessage(
                        member,
                        client.getRandomMessageId(),
                        payload,
                        notification,
                        null,
                        null, // TTL (null -> default-TTL)
                        false);
        client.send(jsonRequest);
    }

    Map<String, String> notification_builder(String title, String body, String icon) {

        Map<String, String> notification = new HashMap<>();
        notification.put("click_action", "MAIN");
        notification.put("title", "Nick");
        notification.put("body", "Server Test Message");
        notification.put("icon", Config.ICON);
        if (title != null) {
            notification.put("title", title);
        }
        if (body != null) {
            notification.put("body", body);
        }
        
        if (icon != null) {
            notification.put("icon", icon);
        }

        return notification;
    }
    
    Map<String, String> notification_builder(String body) {

        Map<String, String> notification = new HashMap<>();
        notification.put("click_action", "MAIN");
        notification.put("title", "Nick");
        notification.put("body", body);
        notification.put("icon", Config.ICON);
    return notification;
    }
}
