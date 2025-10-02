import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class EditorFormas extends Application {

    private ColorPicker fillColorPicker = new ColorPicker(Color.LIGHTBLUE);
    private ColorPicker strokeColorPicker = new ColorPicker(Color.BLACK);
    private Slider strokeWidthSlider = new Slider(1, 10, 2);

    private double startX, startY;

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        // aba 1: formas pré-definidas
        Tab tabShapes = new Tab("Formas Prontas");
        tabShapes.setContent(criarAbaFormas());
        tabShapes.setClosable(false);

        // aba 2: canvas para desenhar
        Tab tabCanvas = new Tab("Canvas Livre");
        tabCanvas.setContent(criarAbaCanvas());
        tabCanvas.setClosable(false);

        tabPane.getTabs().addAll(tabShapes, tabCanvas);

        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setTitle("Editor de Formas Geométricas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ===================================== aba 1 =====================================
    private BorderPane criarAbaFormas() {
        BorderPane pane = new BorderPane();

        Pane drawingArea = new Pane();
        drawingArea.setStyle("-fx-background-color: white;");

        // botões de formas

        // retângulo
        Button btnRect = new Button("Retângulo");
        btnRect.setOnAction(e -> {
            Rectangle r = new Rectangle(100, 60);
            configurarShape(r);
            r.setX(50);
            r.setY(50);
            drawingArea.getChildren().add(r);
        });

        // círculo
        Button btnCircle = new Button("Círculo");
        btnCircle.setOnAction(e -> {
            Circle c = new Circle(40);
            configurarShape(c);
            c.setCenterX(100);
            c.setCenterY(100);
            drawingArea.getChildren().add(c);
        });

        // eclipse
        Button btnEllipse = new Button("Elipse");
        btnEllipse.setOnAction(e -> {
            Ellipse e1 = new Ellipse(60, 40);
            configurarShape(e1);
            e1.setCenterX(150);
            e1.setCenterY(150);
            drawingArea.getChildren().add(e1);
        });

        // linha
        Button btnLine = new Button("Linha");
        btnLine.setOnAction(e -> {
            Line l = new Line(50, 50, 150, 150);
            configurarShape(l);
            drawingArea.getChildren().add(l);
        });

        // polígono -> triângulo
        Button btnPolygon = new Button("Polígono");
        btnPolygon.setOnAction(e -> {
            Polygon p = new Polygon();
            p.getPoints().addAll(
                    200.0, 200.0,
                    250.0, 200.0,
                    225.0, 250.0
            );
            configurarShape(p);
            drawingArea.getChildren().add(p);
        });

        // barra de ferramentas
        HBox controls = new HBox(10, btnRect, btnCircle, btnEllipse, btnLine, btnPolygon, fillColorPicker, strokeColorPicker, strokeWidthSlider);
        controls.setStyle("-fx-padding: 10; -fx-background-color: #ddd;");

        pane.setTop(controls);
        pane.setCenter(drawingArea);

        return pane;
    }

    // configurações dos desenhos 
    private void configurarShape(Shape shape) {
        shape.setFill(fillColorPicker.getValue());
        shape.setStroke(strokeColorPicker.getValue());
        shape.setStrokeWidth(strokeWidthSlider.getValue());
    }

    // ===================================== aba 2 =====================================
    private BorderPane criarAbaCanvas() {
        BorderPane pane = new BorderPane();

        // cria o canvas
        Canvas canvas = new Canvas(800, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(2);

        // botões para limpar
        Button btnClear = new Button("Limpar");
        btnClear.setOnAction(e -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });

        // barra de ferramentas
        HBox controls = new HBox(10, new Label("Preenchimento:"), fillColorPicker, new Label("Borda:"), strokeColorPicker, new Label("Espessura:"), strokeWidthSlider, btnClear);
        controls.setStyle("-fx-padding: 10; -fx-background-color: #ddd;");

        // eventos do mouse para desenho livre

        // mouse está pressionado
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            startX = e.getX();
            startY = e.getY();
            gc.setStroke(strokeColorPicker.getValue());
            gc.setFill(fillColorPicker.getValue());
            gc.setLineWidth(strokeWidthSlider.getValue());
        });

        // mouse é largado
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            double endX = e.getX();
            double endY = e.getY();
            gc.strokeLine(startX, startY, endX, endY);
            startX = endX;
            startY = endY;
        });

        pane.setTop(controls);
        pane.setCenter(canvas);

        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
