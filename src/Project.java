import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application; //provide krti hai basic
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;
import java.awt.*;

public class Project extends Application {

    private TextField itemid;
    private TextField itemname;
    private TextField qty;
    private TextField price;
    private TextField itemcountfield;


    private int[] itemids = new int[100];
    private String[] itemnames = new String[100];
    private int[] qtys = new int[100];
    private double[] prices = new double[100];

    private int itemcount = 0;
    private Label alertLabel;

    //for the table
    private TextArea summary;


    public void start(Stage stage) {
        Font.loadFont(getClass().getResourceAsStream("/fonts/RobotoMono-Regular.ttf"), 12); //loading font

        summary = new TextArea();
        summary.setPrefColumnCount(80); //width of table cell
        summary.setPrefRowCount(8);     //height of table cell
        summary.setStyle("-fx-font-family: " +
                "'Roboto Mono'; -fx-font-size: 15; " +
                "-fx-background-color: #42524b; -fx-control-inner-background: #42524b; " +
                "-fx-border-color: transparent; -fx-background-radius: 12;"
                + "-fx-border-radius: 12; -fx-background-insets: 0;");
        summary.setEditable(false);



        Label header = new Label("Inventory Management System");
        header.setStyle("-fx-font-size: 20px;" +  "-fx-font-weight: bold;" + "-fx-text-fill: white;"); //sets the header's appearance (bold and large)
        header.setAlignment(Pos.CENTER);

        //#2

        itemid = new TextField();
        itemid.setPromptText("Item ID"); //creates input boxes with prompts to store value in these variables

        itemname = new TextField();
        itemname.setPromptText("Item Name");

        qty = new TextField();
        qty.setPromptText("Quantity");

        price = new TextField();
        price.setPromptText("Item Cost");

        itemcountfield = new TextField();
        itemcountfield.setEditable(false);
        itemcountfield.setText(String.valueOf(itemcount));

        itemid.setPrefWidth(80);
        itemname.setPrefWidth(120);
        qty.setPrefWidth(80);
        price.setPrefWidth(80);
        itemcountfield.setPrefWidth(60);

        //region fields styling
        String fieldHover =
                "-fx-background-color: #ffffff;" +   // light neutral (change later)
                        "-fx-background-radius: 8;" +
                        "-fx-border-radius: 5;" +
                        "-fx-border-color: black;" +
                        "-fx-padding: 6 8 6 8;" +
                        "-fx-font-size: 14px;";

        String fieldNormal =
                "-fx-background-color: #ffffff;" +   // slightly darker on hover
                        "-fx-background-radius: 8;" +
                        "-fx-border-color: transparent;" +
                        "-fx-padding: 6 8 6 8;" +
                        "-fx-font-size: 14px;";

        TextField[] fields = { itemid, itemname, qty, price, itemcountfield };

        for (TextField tf : fields) {
            tf.setStyle(fieldNormal);                       // <-- THIS is what makes it change
            tf.setOnMouseEntered(e -> tf.setStyle(fieldHover));
            tf.setOnMouseExited(e -> tf.setStyle(fieldNormal));
        }

        VBox idBox = new VBox(3, new Label("ID"), itemid);
        VBox nameBox = new VBox(3, new Label("Name"), itemname);
        VBox qtyBox = new VBox(3, new Label("Qty"), qty);
        VBox priceBox = new VBox(3, new Label("Price"), price);
        VBox countBox = new VBox(3, new Label("Items"), itemcountfield);
        //#3
        Button additem = new Button("Add Item +");

        Button updateqty = new Button("Update Quantity");
        Button valuetotal = new Button("Total Value");
        Button lowstock = new Button("Low Stock");
        Button autoload = new Button("Auto Load"); //to automatically add 3 items to the inventory

        //region buttons Styling
        double buttonwidth = 120;
        additem.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +   // pill
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        );
        additem.setOnMouseEntered(e -> additem.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-text-fill: black;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        ));

        additem.setOnMouseExited(e -> additem.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        ));

        //2nd button
        updateqty.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +   // pill
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        );
        updateqty.setOnMouseEntered(e -> updateqty.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-text-fill: black;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        ));

        updateqty.setOnMouseExited(e -> updateqty.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        ));


        //3rd button
        valuetotal.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +   // pill
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        );
        valuetotal.setOnMouseEntered(e -> valuetotal.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-text-fill: black;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        ));

        valuetotal.setOnMouseExited(e -> valuetotal.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        ));

        //4th button
        lowstock.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +   // pill
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        );
        lowstock.setOnMouseEntered(e -> lowstock.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-text-fill: black;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        ));

        lowstock.setOnMouseExited(e -> lowstock.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        ));



        //5th button
        autoload.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +   // pill
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        );
        autoload.setOnMouseEntered(e -> autoload.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-text-fill: black;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        ));

        autoload.setOnMouseExited(e -> autoload.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 999;" +
                        "-fx-border-radius: 999;" +
                        "-fx-padding: 6 12 6 12;"
        ));



        additem.setPrefWidth(buttonwidth);
        updateqty.setPrefWidth(buttonwidth);
        valuetotal.setPrefWidth(buttonwidth);
        lowstock.setPrefWidth(buttonwidth);
        autoload.setPrefWidth(buttonwidth);
        //endregion

        // e means action (i.e button clicked) and -> means "do this" so when button clicked do print
        additem.setOnAction(e -> addItemf());
        updateqty.setOnAction(e -> updateqtyf());
        valuetotal.setOnAction(e -> showTotalValuef());
        autoload.setOnAction(e -> autoLoad());

        lowstock.setOnAction(e -> showlowqtyf());

        HBox buttons = new HBox(10, additem, updateqty, valuetotal,lowstock, autoload);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(8));

        alertLabel = new Label();
        alertLabel.setStyle("-fx-background-color: lightgreen; -fx-text-fill: black; -fx-padding: 8px;");
        alertLabel.setVisible(false);   // hidden by default



        HBox inputs = new HBox(12, idBox, nameBox, qtyBox, priceBox, countBox);  //adds a Horizontal box for all the input boxes
        inputs.setPadding(new Insets(8)); //padding set to 8 while space in between is 12
        inputs.setAlignment(Pos.CENTER);  //alligned with the center

        VBox center = new VBox (15, inputs,buttons, alertLabel, summary); // main center vertical box added
        center.setAlignment(Pos.TOP_CENTER);
        center.setPadding(new Insets(20)); //padding set for the vertical box called center

        StackPane toppane = new StackPane(); //Stackpane is a vertical layout system, added top container to it and centered
        toppane.setPadding(new Insets(10)); //padding set for this container to 10 , insets is a class
        toppane.setStyle("-fx-background-color: black;");
        toppane.getChildren().add(header);  //adding header to our pane

        BorderPane root = new BorderPane(); //added to structure it even more
        root.setStyle("-fx-background-color: #fff0c7;");
        root.setTop(toppane); //toppane assigned to top of this , root decides layout and setup
        root.setCenter(center);

        Scene scene = new Scene(root , 750, 450); //places root inside scene container with dimensions 500px wide
        stage.setScene(scene); // displays scene inside the window
        stage.setTitle("Inventory UI");
        stage.show(); //mandatory or else nothing will appear


    }

    private void showAlert(String message) {
        alertLabel.setText(message);
        alertLabel.setVisible(true);

        // disappear after 2 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        FadeTransition fadeOut = new FadeTransition(Duration.millis(600), alertLabel);
        fadeOut.setFromValue(1);   // start fully visible
        fadeOut.setToValue(0);     // fade to invisible

        // After fade → hide the label
        fadeOut.setOnFinished(e -> {
            alertLabel.setVisible(false);
            alertLabel.setOpacity(1); // reset for next alert
        });

        pause.setOnFinished(e -> fadeOut.play());
        pause.play();
    }


    private void addItemf(){

        if(itemid.getText().isEmpty() || itemname.getText().isEmpty() ||
                qty.getText().isEmpty() || price.getText().isEmpty()){
            showAlert("Please fill all the blanks");
            return;
        }

        int idVar;
        int qtyVar;
        double priceVar;

        try {
            idVar = Integer.parseInt(itemid.getText().trim());
            qtyVar = Integer.parseInt(qty.getText().trim());
            priceVar = Double.parseDouble(price.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("ID and Quantity must be whole numbers.\nPrice must be a number.");
            return;
        }

        if (qtyVar < 0 || priceVar < 0) {
            showAlert("Quantity and Price cannot be negative.");
            return;
        }

        int tempID = Integer.parseInt(itemid.getText());
        for (int count = 0; count < itemcount ; count++){
            if(tempID == itemids[count]){
                showAlert("ID already exists, please use a different ID");
                return;
            }
        }


        itemids[itemcount] = Integer.parseInt(itemid.getText());
        itemnames[itemcount] = itemname.getText();
        qtys[itemcount] = Integer.parseInt(qty.getText());
        prices[itemcount] = Double.parseDouble(price.getText());
        itemcount++; //only if sucessfully added

        showAlert("Item Added: " + itemnames[itemcount-1]);
        clearinputfields(); //defined below
        itemcountfield.setText(String.valueOf(itemcount));
        refreshsummary();
    }



    private void updateqtyf() {



        if (itemid.getText().isEmpty() ||
                qty.getText().isEmpty()) {
            showAlert("Please Enter Item's ID and new Quantity");
            return;
        }

        for (int count = 0 ; count < itemcount ;count++ ){
            int id = Integer.parseInt(itemid.getText());
            int qtyvar = Integer.parseInt(qty.getText());
            if (itemids[count] == id) {
                qtys[count] = qtyvar;
                showAlert("Quantity updated to: " + qtyvar + " for Item: " + itemnames[count]);
                clearinputfields();
                refreshsummary();
                return;
            }
        }
        showAlert("Item does not exist");
        return;
    }

    private void showTotalValuef() {
        double total = 0;

        // loop through all items that have been added
        for (int count = 0; count < itemcount; count++) {
            total += qtys[count] * prices[count];  //multiplies each item's quantity with it's respective price
        }

        showAlert("Total Inventory Value: " + total);
    }

    private void showlowqtyf(){
        String result = "Low Stock Items: ";
        boolean found = false;

        for (int count = 0 ; count < itemcount ; count++){
            if (qtys[count] < 4){
                result = result + "Item " + itemnames[count] + " Quantity left: " + qtys[count] + " ";
                found = true;
            }
        }


        if (!found){
            showAlert("No Items low on Stock!");
            return;
        }
        showAlert(result);


    }
    private void refreshsummary(){
        summary.clear();
        summary.appendText(String.format("%-5s %-10s %-5s %-8s%n", "ID", "Name", "Qty", "Price"));

        for (int i = 0; i < itemcount; i++) {
            summary.appendText(String.format("%-5d %-10s %-5d %-8.2f%n",
                    itemids[i], itemnames[i], qtys[i], prices[i]));
        }

    }

    private void autoLoad(){

        for(int count = 0; count < itemcount ; count++){
            if(itemnames[count] == "Sugar"){
                showAlert("These items already exist");
                return;
            }
        }
        int index = itemcount;
        itemnames[index] = "Sugar";
        itemnames[index+1] = "Apples";
        itemnames[index+2] = "Cheese";
        itemids[index] = 210;
        itemids[index+1] = 211;
        itemids[index+2] = 212;
        qtys[index] = 5;
        qtys[index+1] = 14;
        qtys[index+2] = 7;
        prices[index] = 5;
        prices[index+1] = 15;
        prices[index+2] = 9;
        index = index+3;

        itemcount = index;
        itemcountfield.setText(String.valueOf(itemcount));
        showAlert("3 Items have been autoloaded (210,211,212)");
        refreshsummary();


    }

    private void clearinputfields(){
        itemid.clear();
        itemname.clear();
        qty.clear();
        price.clear();
    }


    private void setButtonFont(Button b) {
        b.setFont(Font.font("Roboto", 14));
    }


    public static void main (String[] args){
        launch(args);
    }
}