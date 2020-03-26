package at.wifiwien.javaaufbau.rmi.chatserverclient.application;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;

public class ChatClientHandleImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ChatClient client;
	
	public ChatClientHandleImpl(ChatClient client) throws RemoteException {
		
		super();
		this.client = client;
	}
	


}
