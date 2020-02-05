package server;

import java.util.Collection;

public interface IServer {
    void add_connection(String connectionName, IServerConnection connection) throws Server.ServerConnectionExists;

    IServerConnection getConnection(String connectionName);

    Collection<String> getConnectionNames();

    int send(byte[] data);

    void start();
}
