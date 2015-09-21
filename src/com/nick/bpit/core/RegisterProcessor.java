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
import com.nick.bpit.helper.RegIdManager;
import java.util.Map;

/**
 * Handles a user registration.
 */
public class RegisterProcessor extends PayloadProcessor {

    @Override
    public void handleMessage(CcsMessage msg) {
        RegIdManager idManager = RegIdManager.getInstance();
        CcsClient client = CcsClient.getInstance();
        Map<String, String> notification;
        Map<String, String> payload = msg.getPayload();
        String body = payload.get("NAME") + " has joined us.";

        String email = payload.get(Config.EMAIL);
        System.out.println("Email = " + email);

        String isNewUser = idManager.writeToFile(email, msg.getFrom());
        payload.put(NEW_MEMBER, isNewUser);

        Map<String, String> regIdMap = idManager.readFromFile();
/*
        if (isNewUser.equals("T")) {
            notification = notification_builder("New Member", body, Config.ICON);
        } else {
            notification = null;
        }
*/
        sendToAll(regIdMap, client, payload, null);

        System.out.println("REGISTER ACTION COMPLETE");
    }

}
