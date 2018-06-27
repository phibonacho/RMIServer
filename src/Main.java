import RMIForum.Server.*;
import RMIForum.user.User;

import java.io.*;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import static RMIForum.Server.RMIServer.printInfo;
import static java.io.File.createTempFile;

public class Main{
    public static void main(String args[]) throws UnknownHostException {
        //create Policy file:
        File policy = null;
        try {
           policy = createTempFile("RMIServer", ".policy");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println(policy.getAbsolutePath());
        System.out.println("Detected: "+System.getProperty("user.name")+"@"+System.getProperty("os.name")+" with temporary directory: "+System.getProperty("java.io.tmpdir"));
        RMIServer server = new RMIServer(args[0]);
        server.start();
        Scanner sc = new Scanner(System.in);
        System.out.println("You typed "+sc.next());
        System.out.println("Server status at shutdown:");
        printInfo(server);
        try {
            server.shutDown();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

       // User u = new User(args[0]);

    }
}