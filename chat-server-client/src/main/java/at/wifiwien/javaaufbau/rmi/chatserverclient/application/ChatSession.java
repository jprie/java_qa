package at.wifiwien.javaaufbau.rmi.chatserverclient.application;

import java.io.Serializable;

public class ChatSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private ChatClientHandle client;
	
	

	public ChatSession(String username, ChatClientHandle client) {
		super();
		this.username = username;
		this.client = client;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ChatClientHandle getClient() {
		return client;
	}

	public void setClient(ChatClientHandle client) {
		this.client = client;
	}

	
}
