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
import com.nick.bpit.helper.MessageManager;
import com.nick.bpit.helper.RegIdManager;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles an echo request.
 */
public class BroadcastProcessor extends PayloadProcessor {

    @Override
    public void handleMessage(CcsMessage msg) {
        MessageManager messageManager = MessageManager.getInstance();
        CcsClient client = CcsClient.getInstance();
        RegIdManager idManager = RegIdManager.getInstance();

        Map<String, String> payload = msg.getPayload();
        String body = msg.getPayload().get(Config.MESSAGE);
        String timestamp = msg.getPayload().get(Config.TIMESTAMP);
        System.out.println(body);

        messageManager.writeMsg(body, timestamp);
        Map<String, String> regIdMap = idManager.readFromFile();
        String admin = regIdMap.get(ADMIN_EMAIL);
        
   //     if (msg.getFrom().equalsIgnoreCase(admin)) {
            Map<String, String> notification = notification_builder("New Announcement", body, Config.ICON);
            sendToAll(regIdMap, client, payload, null);
 /*       } else {
            Map<String, String> notification = notification_builder("New Information", body, Config.ICON);
            sendToMember(admin, client, payload, null);
        }
*/
        System.out.println("BROADCAST ACTION COMPLETE");
    }

}
