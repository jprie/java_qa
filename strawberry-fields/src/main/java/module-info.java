module strawberryfields {
	exports at.wifiwien.javaswe.strawberry_fields.application;
	
	opens at.wifiwien.javaswe.strawberry_fields.controller to javafx.fxml;
	opens at.wifiwien.javaswe.strawberry_fields.model;
	opens at.wifiwien.javaswe.strawberry_fields.service;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	
}