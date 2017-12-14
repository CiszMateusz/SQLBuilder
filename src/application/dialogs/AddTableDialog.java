package application.dialogs;

import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import application.model.Pair;
import application.model.Table;
import application.model.TableData;
import application.model.Table.TableClick;

public class AddTableDialog {
	
	public interface AddTableDialogListener{
		public void onAddTableClick(Table newTable, Table secondTable);
	}
	
	public static void showDialog(Stage stage, List<Table> tables, double x, double y, AddTableDialogListener listener, TableClick tableClickListener){
		Dialog<Table> dialog = new Dialog<>();
		dialog.setTitle("Dodawanie tabeli");
		dialog.setHeaderText("Dodawanie tablei");
		
		ButtonType add = new ButtonType("Dodaj", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(add,ButtonType.CANCEL);
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));

		TextField type = new TextField();
		TextField size = new TextField();
		TextField name = new TextField();
		
		TextField tableName = new TextField();
		
		ComboBox<Table> otherTables = new ComboBox<Table>(FXCollections.observableArrayList(tables));
		
		ObservableList<Pair> valList = FXCollections.observableArrayList();
		ListView<Pair> rowList = new ListView<Pair>(valList);
		rowList.setEditable(true);
		rowList.setPrefWidth(300);
		
		Button save = new Button("Dodaj parametr");
		save.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Pair p = new Pair(type.getText(), size.getText(), name.getText());
				valList.add(p);
			}
		});

		grid.add(new Label("Typ:"), 0, 0);
		grid.add(type, 1, 0);
		grid.add(new Label("Wielkość:"), 0, 1);
		grid.add(size, 1, 1);
		grid.add(new Label("Nazwa:"), 0, 2);
		grid.add(name, 1, 2);
		grid.add(save, 1, 3);
		grid.add(rowList, 1, 4);
		grid.add(new Label("Nazwa tabeli"), 0, 5);
		grid.add(tableName, 1, 5);
		grid.add(otherTables, 1, 6);
		
		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> type.requestFocus());
		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == add) {
		    	TableData data = new TableData(tableName.getText());
		    	data.getParams().addAll(rowList.getItems());
		    	Table table = new Table(x, y, data, tableClickListener);
		    	return table;
		    }
		    return null;
		});

		Optional<Table> result = dialog.showAndWait();

		result.ifPresent(part -> {
			if(part != null)
				listener.onAddTableClick(part, otherTables.getSelectionModel().getSelectedItem());
		});
	}

}
