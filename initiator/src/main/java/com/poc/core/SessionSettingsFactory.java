package com.poc.core;

import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.SessionID;
import quickfix.SessionSettings;

@Singleton
public class SessionSettingsFactory {

    private static final Logger LOG = LoggerFactory.getLogger(SessionSettingsFactory.class.getName());

    @ConfigProperty(name = "quickfix.BeginString")
    String beginString;

    @ConfigProperty(name = "quickfix.SenderCompID")
    String senderCompID;

    @ConfigProperty(name = "quickfix.TargetCompID")
    String targetCompID;

    @ConfigProperty(name = "quickfix.ConnectionType")
    String connectionType;

    @ConfigProperty(name = "quickfix.StartTime")
    String startTime;

    @ConfigProperty(name = "quickfix.EndTime")
    String endTime;

    @ConfigProperty(name = "quickfix.ReconnectInterval")
    String reconnectInterval;

    @ConfigProperty(name = "quickfix.HeartBtInt")
    long heartBtInt;

    @ConfigProperty(name = "quickfix.SocketConnectHost")
    String socketConnectHost;

    @ConfigProperty(name = "quickfix.SocketConnectPort")
    long socketConnectPort;

    // Logging

    @ConfigProperty(name = "quickfix.ScreenLogShowHeartBeats")
    String screenLogShowHeartBeats;


    public SessionSettingsFactory() {
        LOG.info("Constructor");
    }

    public SessionSettings getSessionSettings() {

        LOG.info("getSessionSettings");

        SessionID sessionID = new SessionID(beginString, senderCompID, targetCompID);

        LOG.info("SessionID: [" + sessionID.toString() + "]");

        SessionSettings sessionSettings = new SessionSettings();

        sessionSettings.setString(sessionID, "BeginString", beginString);

        sessionSettings.setString(sessionID, "SenderCompID", senderCompID);

        sessionSettings.setString(sessionID, "TargetCompID", targetCompID);

        sessionSettings.setString(sessionID, "ConnectionType", connectionType);

        sessionSettings.setString(sessionID, "StartTime", startTime);

        sessionSettings.setString(sessionID, "EndTime", endTime);

        sessionSettings.setString(sessionID, "ReconnectInterval", reconnectInterval);

        sessionSettings.setLong(sessionID, "HeartBtInt", heartBtInt);

        sessionSettings.setString(sessionID, "SocketConnectHost", socketConnectHost);

        sessionSettings.setLong(sessionID, "SocketConnectPort", socketConnectPort);

        // Logging
        sessionSettings.setString(sessionID, "ScreenLogShowHeartBeats", screenLogShowHeartBeats);

        return sessionSettings;

    }

}