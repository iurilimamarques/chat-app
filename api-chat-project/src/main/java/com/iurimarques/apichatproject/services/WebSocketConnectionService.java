package com.iurimarques.apichatproject.services;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.iurimarques.apichatproject.utils.ActiveUserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class WebSocketConnectionService {
    
    @Autowired
    private ActiveUserManager activeSessionManager;

    public String connect(HttpServletRequest request, String userName) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("Remote_Addr");
            if (StringUtils.isEmpty(remoteAddr)) {
                remoteAddr = request.getHeader("X-FORWARDED-FOR");
                if (remoteAddr == null || "".equals(remoteAddr)) {
                    remoteAddr = request.getRemoteAddr();
                }
            }
        }
        
        activeSessionManager.add(userName, remoteAddr);
        return remoteAddr;
    }

    public String remove(String userName) {
        activeSessionManager.remove(userName);
        return "disconnected";
    }

    public Set<String> getActiveUsersExceptCurrentUser(String userName) {
        return activeSessionManager.getActiveUsersExceptCurrentUser(userName);
    }
}
