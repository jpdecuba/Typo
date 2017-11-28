package Model.Chat;

import Controller.HighScoreController;
import Model.HighScore;
import fontyspublisher.IRemotePropertyListener;

import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client extends UnicastRemoteObject implements IRemotePropertyListener {
    private static String bindingName = "chat";

    // References to registry and student administration
    private Registry registry = null;
    private IChat chat = null;
    private HighScoreController controller;

    public Client(String ipAddress, int portNumber, HighScoreController controller) throws RemoteException {

        this.controller = controller;
        // Locate registry at IP address and port number
        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
            //registry = LocateRegistry.getRegistry();
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Print result locating registry
        if (registry != null) {
            System.out.println("Client: Registry located");
        } else {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }

        // Print contents of registry
        if (registry != null) {
            printContentsRegistry();
        }

        // Bind student administration using registry
        if (registry != null) {
            try {
                chat = (IChat) registry.lookup(bindingName);
                chat.subscribeRemoteListener(this, bindingName);
            } catch (RemoteException ex) {
                System.out.println("Client: Cannot bind Effectennbeurs");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                chat = null;
            } catch (NotBoundException ex) {
                System.out.println("Client: Cannot bind Effectennbeurs");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
                chat = null;
            }
        }

        // Print result binding student administration
        if (chat != null) {
            System.out.println("Client: Effectennbeurs bound");
        } else {
            System.out.println("Client: Effectennbeurs is null pointer");
        }
    }

    // Print contents of registry
    private void printContentsRegistry() {
        try {
            String[] listOfNames = registry.list();
            System.out.println("Client: list of names bound in registry:");
            if (listOfNames.length != 0) {
                for (String s : registry.list()) {
                    System.out.println(s);
                }
            } else {
                System.out.println("Client: list of names bound in registry is empty");
            }
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot show list of names bound in registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }
    }

    public void sendMessage(String message) throws RemoteException {
        chat.SendMessage(message);
    }

    //Nieuw Koers aan de client doorgeven
    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) throws RemoteException {
        controller.receiveMessage((String)propertyChangeEvent.getNewValue());
    }
}
