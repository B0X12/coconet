package com.coconet.server.define;

import org.springframework.stereotype.Repository;

@Repository
public class LogTag {

    public final String TAG_LOGIN = "Login";
    public final String TAG_LOGOUT = "Login";
    public final String TAG_SIGNUP = "Signup";
    public final String TAG_CERTIFICATION = "Certification";
    public final String TAG_STATUS = "Status";

    public final String TAG_USER_STATUS = "Status";
    public final String TAG_USER_STATUS_WITH_ADMIN = "@@Status";

}
