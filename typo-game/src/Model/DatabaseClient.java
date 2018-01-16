package Model;

import Model.SaveProps.Settings;
import Model.Shared.Request;
import Model.Shared.RequestType;

import javax.net.SocketFactory;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class DatabaseClient implements Serializable{

    private Socket Socket;

    private ObjectOutputStream output;
    private ObjectInputStream input;

    public DatabaseClient(java.net.Socket socket) {
        if(socket != null) {
            Socket = socket;
        }else {
            SocketFactory socketFactory = (SocketFactory) SocketFactory.getDefault();
            try {
                this.Socket = (Socket) socketFactory.createSocket(Settings.GetIp(), 7676);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            output = new ObjectOutputStream(Socket.getOutputStream());

            BufferedInputStream socketRead = new BufferedInputStream(Socket.getInputStream());

            input = new ObjectInputStream(socketRead);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Set> getSet(Difficulty diff) {
        try {
            Request RequestSets = new Request(diff, RequestType.Sets);




            output.writeObject(RequestSets);

            Object response = input.readObject();
            List<Set> list = (List<Set>) response;
            //System.out.println(list.size());


            return list;


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }


        public List<Opportunity> getOpp (Difficulty diff){
            try {
                Request RequestSets = new Request(diff, RequestType.Opportunity);


                output = new ObjectOutputStream(Socket.getOutputStream());


                output.writeObject(RequestSets);
                BufferedInputStream socketRead = new BufferedInputStream(Socket.getInputStream());

                //input = new ObjectInputStream(socketRead);
                Object response = input.readObject();
                List<Opportunity> list = (List<Opportunity>) response;
                //System.out.println(list.size());

                return list;


            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }


        }




    public List<HighScore> getHighScore(){
        try {
            Request RequestSets = new Request(null, RequestType.HighScore);

            //output = new ObjectOutputStream(Socket.getOutputStream());


            output.writeObject(RequestSets);
            BufferedInputStream socketRead = new BufferedInputStream(Socket.getInputStream());

            //input = new ObjectInputStream(socketRead);
            Object response = input.readObject();
            List<HighScore> list = (List<HighScore>) response;
            //System.out.println(list.size());


            return list;


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }


    }




    public boolean SetHighScore(HighScore highScore){

        try {
        Request RequestSets = new Request(null, RequestType.SetHighScore,highScore);


        output.writeObject(RequestSets);
        output.flush();
        return true;



        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }

    public void flusj(){

        try {
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void close(){
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //input.close();
    }

    }
