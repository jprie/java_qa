package at.wifiwien.javaswe.strawberry_fields.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

import at.wifiwien.javaswe.strawberry_fields.model.Game;


public class GameFileIOService {

	public void save(Game game) {
		try (FileOutputStream out = new FileOutputStream("file");
				ObjectOutputStream oos = new ObjectOutputStream(out)) {

			oos.writeObject(game);
			System.out.println("Game stored successfully");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Optional<Game> load() {

		Game game = null;

		try (FileInputStream in = new FileInputStream("file"); ObjectInputStream ois = new ObjectInputStream(in)) {

			try {
				game = (Game) ois.readObject();
				System.out.println("Game loaded successfully");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(game);
	}
}
