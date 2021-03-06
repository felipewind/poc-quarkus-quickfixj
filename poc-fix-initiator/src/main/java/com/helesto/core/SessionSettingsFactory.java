package com.helesto.core;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.ConfigError;
import quickfix.SessionSettings;

@Singleton
public class SessionSettingsFactory {

    private static final Logger LOG = LoggerFactory.getLogger(SessionSettingsFactory.class.getName());

    @ConfigProperty(name = "quickfix")
    List<String> quickfixSessionSettings;

    public SessionSettingsFactory() {
        LOG.info("Constructor");
    }

    public SessionSettings getSessionSettings() throws ConfigError {

        LOG.info("getSessionSettings");

        LOG.info("quickfixSessionSettings - original parameter: \n" + quickfixSessionSettings.stream().collect(Collectors.joining("\n")));

        byte[] bytes = quickfixSessionSettings.stream().collect(Collectors.joining("\n")).getBytes();

        InputStream inputStream = new ByteArrayInputStream(bytes);

        SessionSettings sessionSettings = new SessionSettings(inputStream);

        return sessionSettings;

    }

}