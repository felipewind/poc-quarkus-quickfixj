package com.poc.core;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.Initiator;
import quickfix.LogFactory;
import quickfix.MemoryStoreFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.ScreenLogFactory;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;

@Singleton
public class Trader {

    @ConfigProperty(name = "quickfix.appVersion")
    String appVersion;

    @ConfigProperty(name = "quickfix.password")
    String password;

    @Inject
    SessionSettingsFactory sessionSettingsFactory;

    @Inject
    TraderApplication traderApplication;

    private static final Logger LOG = LoggerFactory.getLogger(Trader.class.getName());
    private boolean initiatorStarted;
    private SessionSettings sessionSettings;
    private Initiator initiator;

    public Trader() {
        LOG.info("Constructor");
    }

    public void init() {
        LOG.info("init");

        try {

            sessionSettings = sessionSettingsFactory.getSessionSettings();
            LOG.info("SessionSettings created:\n" + sessionSettings.toString());

            MessageStoreFactory messageStoreFactory = new MemoryStoreFactory();

            LogFactory logFactory = new ScreenLogFactory(sessionSettings);

            MessageFactory messageFactory = new DefaultMessageFactory();
            LOG.info("MessageFactory created - DefaultMessageFactory");

            initiator = new SocketInitiator(traderApplication, messageStoreFactory, sessionSettings, logFactory,
                    messageFactory);
            LOG.info("Initiator created - SocketInitiator");

            logon();

        } catch (ConfigError e) {
            LOG.error("ConfigError \n" + e);
            e.printStackTrace();
            if (initiatorStarted) {
                stop();
            }
        }
    }

    public synchronized void logon() throws ConfigError {
        LOG.info("logon");
        if (!initiatorStarted) {
            initiator.start();
            LOG.info("Initiator start - logon solicited \nSessionID created: " + initiator.getSessions() + "\n");
            initiatorStarted = true;
        } else {
            for (SessionID sessionID : initiator.getSessions()) {
                Session.lookupSession(sessionID).logon();
                LOG.info("Logon solicited \nSessionID created: " + initiator.getSessions() + "\n");
            }
        }

    }

    public synchronized void stop() {
        LOG.info("stop");
        if (initiatorStarted) {
            initiator.stop();
            initiatorStarted = false;
        }
    }

    public synchronized void logout() {
        LOG.info("logout");
        for (SessionID sessionId : initiator.getSessions()) {
            Session.lookupSession(sessionId).logout("user requested");
        }
    }

    public SessionID getSessionID() {
        if (initiatorStarted) {
            return initiator.getSessions().get(0);
        } else {
            throw new RuntimeException("Initiator stopped");
        }
    }

    public SessionSettings getSessionSettings() {
        return sessionSettings;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getPassword() {
        return password;
    }

}