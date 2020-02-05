package server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Server implements IServer {
    public class ServerError extends Exception {}

    public class ServerConnectionExists extends ServerError {}

    Map<String, IServerConnection> connections;


    public Server() {
        connections = new HashMap<>();
    }

    @Override
    public void add_connection(String connectionName, IServerConnection connection) throws ServerConnectionExists {
        throwErrorIfConnectionExists(connectionName);
        connections.put(connectionName, connection);
    }

    @Override
    public IServerConnection getConnection(String connectionName) {
        return connections.get(connectionName);
    }

    @Override
    public Collection<String> getConnectionNames() {
        return this.connections.keySet();
    }

    @Override
    public int send(byte[] data) {
        return 0;
    }

    @Override
    public void start() {

    }

    private void throwErrorIfConnectionExists(String name) throws ServerConnectionExists {
        if (connections.containsKey(name)) {
            throw new ServerConnectionExists();
        }
    }

}
