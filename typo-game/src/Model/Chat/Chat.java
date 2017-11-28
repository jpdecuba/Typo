package Model.Chat;

import Model.Publisher.IRemotePropertyListener;
import Model.Publisher.RemotePublisher;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Chat extends UnicastRemoteObject implements IChat {
    public RemotePublisher pub;

    protected Chat() throws RemoteException {
        pub = new RemotePublisher(new String[]{"chat"});
    }

    protected Chat(int port) throws RemoteException {
        super(port);
    }

    protected Chat(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void SendMessage(String message) throws RemoteException {
        pub.inform("chat", "", message);
    }

    // Client aan server verbinden
    @Override
    public void subscribeRemoteListener(IRemotePropertyListener iRemotePropertyListener, String s) throws RemoteException {
        pub.subscribeRemoteListener(iRemotePropertyListener, s);
    }

    @Override
    public void unsubscribeRemoteListener(IRemotePropertyListener iRemotePropertyListener, String s) throws RemoteException {
        pub.unsubscribeRemoteListener(iRemotePropertyListener, s);
    }
}
