package Model.Chat;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
    private static final int portNumber = 1099;
    // Set binding name for student administration
    private static final String bindingName = "chat";
    // References to registry and student administration
    private Registry registry = null;
    private Chat chat = null;

    // Constructor
    public Server() {
        System.out.println("Server: Port number " + portNumber);
        try {
            chat = new Chat();
            System.out.println("Server: Chat created");
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create Chat");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            chat = null;
        }
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }
        try {
            registry.rebind(bindingName, chat);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind Chat");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
    }
    // Print IP addresses and network interfaces
    private static void printIPAddresses() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("Server: IP Address: " + localhost.getHostAddress());
            // Just in case this host has multiple IP addresses....
            InetAddress[] allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null && allMyIps.length > 1) {
                System.out.println("Server: Full list of IP addresses:");
                for (InetAddress allMyIp : allMyIps) {
                    System.out.println("    " + allMyIp);
                }
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server: Cannot get IP address of local host");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }
        try {
            System.out.println("Server: Full list of network interfaces:");
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                System.out.println("    " + intf.getName() + " " + intf.getDisplayName());
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    System.out.println("        " + enumIpAddr.nextElement().toString());
                }
            }
        } catch (SocketException ex) {
            System.out.println("Server: Cannot retrieve network interface list");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Welcome message
        System.out.println("SERVER USING REGISTRY");
        // Print IP addresses and network interfaces
        printIPAddresses();
        // Create server
        Server server = new Server();
    }
}