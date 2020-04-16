package at.wifiwien.javaswe.strawberry_fields.controller;

import at.wifiwien.javaswe.strawberry_fields.model.GameModel;
import at.wifiwien.javaswe.strawberry_fields.model.GameModelImpl;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CommonPropertiesController {

	
	public static GameModel model = new GameModelImpl();
	public static BooleanProperty fieldLayoutDone = new SimpleBooleanProperty();
	
}
