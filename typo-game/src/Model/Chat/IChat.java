package Model.Chat;

import fontyspublisher.IRemotePublisherForListener;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IChat extends Remote, IRemotePublisherForListener {

        /**
         * Update de koersen
         * @throws java.rmi.RemoteException
         */
        public void SendMessage(String message) throws RemoteException;
    }
