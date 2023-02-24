import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SortDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ArrayList<SortableRect> rectangles = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            double x = i * 8;
            double height = i + 10;
            double  y = 200 - height;
            double width = 2;
            SortableRect rect = new SortableRect(x, y, width, height);
            rect.setValue(i);
            rectangles.add(rect);
        }

        BorderPane borderPane = new BorderPane();

        Pane pane = new Pane();
        for (SortableRect r : rectangles)
            pane.getChildren().add(r);

        HBox buttonBox = new HBox();

        Button shuffle = new Button("Shuffle");
        shuffle.setOnAction(actionEvent -> {
            Algorithms.fisherYates(rectangles);
        });
        buttonBox.getChildren().add(shuffle);

        Button reverse = new Button("Reverse");
        reverse.setOnAction(actionEvent -> {
            Algorithms.reverse(rectangles);
        });
        buttonBox.getChildren().add(reverse);

        Button selectionSort = new Button("Selection Sort");
        selectionSort.setOnAction(actionEvent -> {
            Algorithms.selectionSort(rectangles);
        });
        buttonBox.getChildren().add(selectionSort);

        Button quickSort = new Button ("Quick Sort");
        quickSort.setOnAction(actionEvent -> {
            Algorithms.quickSort(rectangles);
        });
        buttonBox.getChildren().add(quickSort);

        borderPane.setTop(buttonBox);
        borderPane.setCenter(pane);
        Scene scene = new Scene(borderPane, 800, 225);
        primaryStage.setTitle("Sort Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

class SortableRect extends Rectangle{
    private int value;
    private double lastX;

    public SortableRect(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getLastX() {
        return this.lastX;
    }

    public void setLastX(double lastX) {
        this.lastX += lastX;
    }
}
