module strawberryfields {
	exports at.wifiwien.javaswe.strawberry_fields.application;
	
	opens at.wifiwien.javaswe.strawberry_fields.controller to javafx.fxml;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires org.junit.jupiter.api;
}