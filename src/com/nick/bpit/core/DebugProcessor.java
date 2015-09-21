/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nick.bpit.core;

import com.nick.bpit.CcsClient;
import java.util.Map;

/**
 *
 * @author nick
 */
class DebugProcessor extends PayloadProcessor {

    @Override
    public void handleMessage(CcsMessage msg) {
        CcsClient client = CcsClient.getInstance();
        Map<String, String> payload = msg.getPayload();
        System.out.println("Incoming Payload");
        for(String key:payload.keySet())
            System.out.println(key+" = "+payload.get(key));
        
        notification_builder(payload.get(MESSAGE));
        sendToMember(msg.getFrom(), client, payload, null);
        
        System.out.println("DEBUG ACTION COMPLETE");
    }

}
