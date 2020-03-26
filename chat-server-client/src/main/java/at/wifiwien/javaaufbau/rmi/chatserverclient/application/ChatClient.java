package at.wifiwien.javaaufbau.rmi.chatserverclient.application;

import java.io.Serializable;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements Serializable, ChatClientHandle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Scanner scanner = new Scanner(System.in);
	transient ChatServable server;
	transient ChatSession session;
	String username;
	
	public static void main(String[] args) {
		
		try {
			new ChatClient();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ChatClient() throws RemoteException  {
	
		try {
			Registry registry = LocateRegistry.getRegistry(1314);
			server = (ChatServable)registry.lookup(Constants.SERVER_NAME);
			
			
			System.out.println("Enter username: ");
			username = scanner.nextLine();
				
			
			session = server.createSession(username, this);
			
			handleInput();
			
			
		} catch (NotBoundException|RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	public void handleOutput(String message) {
		
		System.out.println(message);
	}
	
	@Override
	public void receiveMessage(String username, String message) {
		
		handleOutput(username + " says: " + message);
		
	}

	private void handleInput() throws NoSuchObjectException {
		
		
		boolean doStop = false;
		
		
		while(!doStop) {
			
			System.out.println("Enter chat message: ");
			
			String message = scanner.nextLine();
			
			if (!message.equals("q")) {
				try {
				server.postMessage(message, session);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			} else {
				doStop = true;
				server = null;
				//session.setClient(null);
				UnicastRemoteObject.unexportObject(this, true);
				
			}
			
			
			
		}
		
		scanner.close();
		
	}
}
