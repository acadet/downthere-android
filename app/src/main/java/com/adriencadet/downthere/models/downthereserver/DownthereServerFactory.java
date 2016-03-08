package com.adriencadet.downthere.models.downthereserver;

/**
 * DownthereServerFactory
 * <p>
 */
public class DownthereServerFactory {
    private static IDownthereServer server;
    private static final Object serverLock = new Object();

    public IDownthereServer build() {
        if (server == null) {
            synchronized (serverLock) {
                if (server == null) {
                    server = new DownthereServer();
                }
            }
        }

        return server;
    }
}
