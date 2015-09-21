/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nick.bpit.core;

import com.nick.bpit.CcsClient;
import com.nick.bpit.helper.MessageManager;
import com.nick.bpit.helper.RegIdManager;

/**
 *
 * @author nick
 */
public class RefreshProcessor extends PayloadProcessor {

    @Override
    public void handleMessage(CcsMessage msg) {
        RegIdManager idManager = RegIdManager.getInstance();
        MessageManager messageManager = MessageManager.getInstance();
        CcsClient client = CcsClient.getInstance();
        
    }

}
