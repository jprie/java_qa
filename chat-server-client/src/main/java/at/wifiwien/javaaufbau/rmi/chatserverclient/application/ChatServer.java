package at.wifiwien.javaaufbau.rmi.chatserverclient.application;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends UnicastRemoteObject implements ChatServable, Unreferenced {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8511686111626209386L;
	private static ChatServer server;
	private static Registry registry;
	private boolean doStop;

	List<ChatSession> sessions = new ArrayList<>();

	public ChatServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) throws InterruptedException, MalformedURLException {

		try {
			server = new ChatServer();
			registry = LocateRegistry.createRegistry(1314);
			registry.rebind(Constants.SERVER_NAME, server);

			server.start();

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void start() throws InterruptedException {

		System.out.println("Started server...");

		while (!doStop) {

			Thread.yield();
			Thread.sleep(10000);
		}

	}

	public void shutDown() throws NoSuchObjectException {

		doStop = true;
		System.out.println("Quit signal from client... stopping");
		try {
			registry.unbind(Constants.SERVER_NAME);
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		registry = null;

		for (ChatSession s : sessions) {

			s.setClient(null);
		}
		UnicastRemoteObject.unexportObject(server, true);

	}

	@Override
	public void postMessage(String message, ChatSession session) throws RemoteException {

		// System.out.println("Received message from: " + session.getUsername());

		// remote shutdown
		if (message.equals("qs"))
			shutDown();

		System.out.println(message + " received");
		if (sessions.size() > 0) {

			// distribute to all connected clients
			for (ChatSession s : sessions) {

				// do not send back to sender!!!
//				if (!s.getUsername().equals(session.getUsername())) {

					s.getClient().receiveMessage(session.getUsername(), message);
//				}

			}
		}

	}

	@Override
	public ChatSession createSession(String username, ChatClientHandle client) {

		ChatSession session = new ChatSession(username, client);
		sessions.add(session);
		System.out.println("New chat user logged in: " + username);
		return session;

	}

	@Override
	public void unreferenced() {

		System.out.println("Server: unreferenced");

	}


}
