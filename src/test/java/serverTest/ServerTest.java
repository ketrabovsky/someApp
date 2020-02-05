package serverTest;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import server.IServer;
import server.IServerConnection;
import server.Server;


import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class ServerTest {
    private IServer server;

    @Before
    public void setup() {
        server = new Server();
    }

    @Test
    public void addConnectionTest() throws Server.ServerConnectionExists {
        IServerConnection connection = mock(IServerConnection.class);
        final String connectionName = "connection1";
        final String connectionName2 = "connection2";

        server.add_connection(connectionName, connection);
        server.add_connection(connectionName2, connection);
        assertEquals(connection, server.getConnection(connectionName));
    }

    @Test(expected = Server.ServerConnectionExists.class)
    public void connectionThrowsErrorOnAddingConnectionWithExistingName() throws Server.ServerConnectionExists {
        IServerConnection connection1 = mock(IServerConnection.class);
        IServerConnection connection2 = mock(IServerConnection.class);
        final String connectionName = "connection1";
        IServer server = new Server();

        server.add_connection(connectionName, connection1);
        server.add_connection(connectionName, connection2);
    }

    @Test
    public void queringForConnectionShouldReturnNullIfDoesntExist() {
        final String connectionName = "connection1";
        IServer server = new Server();
        assertNull(server.getConnection(connectionName));
    }

    @Test
    public void getAllConnectionNames() throws Server.ServerConnectionExists {
        IServer server = new Server();
        IServerConnection conn = mock(IServerConnection.class);
        final String connectionName1 = "connection1";
        final String connectionName2 = "connection2";
        final Collection<String> namesCollection = new ArrayList<>();
        namesCollection.add(connectionName1);
        namesCollection.add(connectionName2);

        server.add_connection(connectionName1, conn);
        server.add_connection(connectionName2, conn);

        Collection<String> collectedNames = server.getConnectionNames();
        assertTrue(collectedNames.containsAll(namesCollection));
    }
}
