package at.wifiwien.javaaufbau.rmi.chatserverclient.application;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServable extends Remote {

	public void postMessage(String message, ChatSession session) throws RemoteException;
	
	public ChatSession createSession(String username, ChatClientHandle client) throws RemoteException;
}
