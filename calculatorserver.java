/*
	Code: calculator server		CalculatorServer.java

	Server code for hosting the CalculatorImpl object
*/


import java.rmi.Naming;	//Import naming classes to bind to rmiregistry
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class calculatorserver {
	static int port = 1099;
   //calculatorserver constructor
   public calculatorserver() {

		 try {
			 System.out.println("Init server...\n");
		 	//Construct a new CalculatorImpl object and bind it to the local rmiregistry
     		//N.b. it is possible to host multiple objects on a server
			 
			 System.out.println("Reg RMI...\n");
			 Registry reg = LocateRegistry.createRegistry(port);
			 calculator c = new calculatorimpl(); // Instantiate the servant class into an object
			 
			 reg.rebind("CalculatorService", c);
			 Naming.rebind("rmi://10.0.0.6/CalculatorService", c); // Binding it to the naming service, the name will be used to call its method
			 //UnicastRemoteObject.exportObject(c, 1100);
			 System.out.println("Reg RMI completed!\n");
			 
			 System.out.println("Server Started!");
			 
     }
     catch (Exception e) {
       System.out.println("Server Error: " + e);
     }
   }

   public static void main(String args[]) {
	   
	    System.setProperty("java.rmi.server.hostname", "52.149.157.162");
	    //System.setProperty("java.rmi.server.hostname", "10.0.0.6");
     	//Create the new Calculator server
			if (args.length == 1)
				port = Integer.parseInt(args[0]);
				new calculatorserver();
   		}
}
