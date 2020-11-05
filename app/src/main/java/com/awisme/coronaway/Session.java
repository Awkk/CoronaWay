package com.awisme.coronaway;

public class Session {
    private static String sessionId;
    private static String userRole;

    public static void setSessionId(String sessionId) {
        Session.sessionId = sessionId;
    }

    public static String getSessionId() {
        return sessionId;
    }

}
