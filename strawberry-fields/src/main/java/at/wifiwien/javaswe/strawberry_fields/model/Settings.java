package at.wifiwien.javaswe.strawberry_fields.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Settings {

	private StringProperty namePlayer1 = new SimpleStringProperty();
	private StringProperty namePlayer2 = new SimpleStringProperty();
	
	private IntegerProperty numColumns = new SimpleIntegerProperty();
	private IntegerProperty numRows = new SimpleIntegerProperty();
	private IntegerProperty numStrawberries = new SimpleIntegerProperty();
	
	
	
	
	public Settings(String namePlayer1, String namePlayer2, Integer numColumns,
			Integer numRows, Integer numStrawberries) {
		super();
		this.namePlayer1.set(namePlayer1);
		this.namePlayer2.set(namePlayer2);
		this.numColumns.set(numColumns);
		this.numRows.set(numRows);
		this.numStrawberries.set(numStrawberries);
	}

	public final StringProperty namePlayer1Property() {
		return this.namePlayer1;
	}
	
	public final String getNamePlayer1() {
		return this.namePlayer1Property().get();
	}
	
	public final void setNamePlayer1(final String namePlayer1) {
		this.namePlayer1Property().set(namePlayer1);
	}
	
	public final StringProperty namePlayer2Property() {
		return this.namePlayer2;
	}
	
	public final String getNamePlayer2() {
		return this.namePlayer2Property().get();
	}
	
	public final void setNamePlayer2(final String namePlayer2) {
		this.namePlayer2Property().set(namePlayer2);
	}
	
	public final IntegerProperty numColumnsProperty() {
		return this.numColumns;
	}
	
	public final int getNumColumns() {
		return this.numColumnsProperty().get();
	}
	
	public final void setNumColumns(final int numColumns) {
		this.numColumnsProperty().set(numColumns);
	}
	
	public final IntegerProperty numRowsProperty() {
		return this.numRows;
	}
	
	public final int getNumRows() {
		return this.numRowsProperty().get();
	}
	
	public final void setNumRows(final int numRows) {
		this.numRowsProperty().set(numRows);
	}
	
	public final IntegerProperty numStrawberriesProperty() {
		return this.numStrawberries;
	}
	
	public final int getNumStrawberries() {
		return this.numStrawberriesProperty().get();
	}
	
	public final void setNumStrawberries(final int numStrawberries) {
		this.numStrawberriesProperty().set(numStrawberries);
	}
	
	@Override
	public String toString() {
		
		return getNamePlayer1() + ", " + getNamePlayer2() + ", " + getNumColumns() + "x" + getNumRows() + ", " + getNumStrawberries(); 
	}
}
