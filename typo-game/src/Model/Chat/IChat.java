package Model.Chat;

import Model.Publisher.IRemotePublisherForListener;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IChat extends Remote, IRemotePublisherForListener {

        /**
         * Send message to listeners
         * @throws java.rmi.RemoteException
         */
        public void SendMessage(String message) throws RemoteException;
    }
