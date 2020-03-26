package at.wifiwien.javaaufbau.rmi.chatserverclient.application;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientHandle extends Remote {

	void receiveMessage(String username, String message) throws RemoteException;
}
