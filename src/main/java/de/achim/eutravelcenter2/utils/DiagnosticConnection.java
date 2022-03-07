package de.achim.eutravelcenter2.utils;

import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DiagnosticConnection extends HttpConnection {
    static final Logger LOG = LoggerFactory.getLogger(DiagnosticConnection.class);

    @Override
    public Connection.Response execute() throws IOException {
        log(this.request());
        Connection.Response response = super.execute();
        log(response);

        return response;
    }

    public static Connection connect(String url) {
        Connection connection = new DiagnosticConnection();
        connection.url(url);
        return connection;
    }

    private static void log(Connection.Request request) {
        LOG.info("========================================");
        LOG.info("[url] {}", request.url());
        LOG.info("== REQUEST ==");
        logBase(request);
        LOG.info("[method] {}", request.method());
        LOG.info("[data] {}", request.data());
        LOG.info("[request body] {}", request.requestBody());
    }

    private static void log(Connection.Response response) {
        LOG.info("== RESPONSE ==");
        logBase(response);
        LOG.info("[code] {}", response.statusCode());
        LOG.info("[status msg] {}", response.statusMessage());
        LOG.info("[body] {}", response.body());
        LOG.info("========================================");
    }

    private static void logBase(Connection.Base<?> base) {
        LOG.info("[headers] {}", base.headers());
        LOG.info("[cookies] {}", base.cookies());
    }

}