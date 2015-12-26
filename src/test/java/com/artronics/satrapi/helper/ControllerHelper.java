package com.artronics.satrapi.helper;

import org.apache.log4j.Logger;

public class ControllerHelper
{
    private final static Logger log = Logger.getLogger(ControllerHelper.class);


    public static String buildUri(Long netId, Long ctrlId,Long devConId){
        String s = buildUri(netId, ctrlId);
        s+="/device_connections/"+devConId.toString();
        return s;
    }
    public static String buildUri(Long netId, Long ctrlId){
        return "/rest/networks/"+netId.toString()+"/controllers/"+ctrlId.toString();
    }
}
