package application.model;

import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Table {
	
	public interface TableClick{
		public void onTableClick(Table table);
	}

	private double x;
	private double y;
	private double width = 200;
	private double height;
	private Group group;
	private TableData data;
	private List<Table> connected = new ArrayList<Table>();
	
	public Table(double x, double y, TableData data, TableClick listener){
		this.x = x;
		this.y = y;
		this.data = data;
		height = 95+data.getParams().size()*25+10;
		
		group = new Group();
		Rectangle table = new Rectangle(x, y, width, height);
		table.setFill(Color.WHITE);
		table.setStroke(Color.BLACK);
		
		Rectangle header = new Rectangle(x, y, width, 50);
		header.setFill(Color.WHITE);
		header.setStroke(Color.BLACK);
		
		Text name = new Text(x+5, y+30, data.getTableName());
		name.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		
		group.getChildren().add(table);
		group.getChildren().add(header);
		group.getChildren().add(name);
		
		double currentY = y+75;
		for(Pair d : data.getParams()){
			Text t = new Text(x+5, currentY, d.toString());
			currentY += 25;
			group.getChildren().add(t);
		}
		
		table.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				System.out.println("click");
			}
		});
	}
	
	public Group getGroup() {
		return group;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public TableData getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return data.getTableName();
	}
	
	public List<Table> getConnected() {
		return connected;
	}
	
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
}
