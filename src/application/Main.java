package application;
	
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import application.dialogs.AddTableDialog;
import application.dialogs.AddTableDialog.AddTableDialogListener;
import application.model.Table;
import application.model.Table.TableClick;


public class Main extends Application implements TableClick, AddTableDialogListener {
	
	private List<Table> tables = new ArrayList<Table>();
	private Group group;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,800,600);
			
			group = new Group();
			root.setCenter(group);
			root.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					AddTableDialog.showDialog(primaryStage, tables, event.getX(), event.getY(), Main.this, Main.this);
				}
			});
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void onTableClick(Table table) {
		
	}

	@Override
	public void onAddTableClick(Table newTable, Table secondTable) {
		newTable.getConnected().add(secondTable);
		group.getChildren().add(newTable.getGroup());
		if(secondTable != null){
			Line line = new Line(secondTable.getX()+secondTable.getWidth(), secondTable.getY()+secondTable.getHeight()/2, newTable.getX(), newTable.getY()+newTable.getHeight()/2);
			group.getChildren().add(line);
		}
		tables.add(newTable);
	}
}
