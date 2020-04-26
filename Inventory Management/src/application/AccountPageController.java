package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class AccountPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Friend;

    @FXML
    private Button Remainder;

    @FXML
    private ImageView Image_View;

    @FXML
    private Button Profile;
    
    @FXML
    private Button History;

    @FXML
    private ScrollPane Scroll_Pane;

    @FXML
    private SplitPane Split_Pane;

    @FXML
    private Button Status;
    
    @FXML
    private AnchorPane Anchor_Pane;
    
    @FXML
    private AnchorPane Anchor_Pane_1;

    @FXML
    private AnchorPane Anchor_Pane_2;
        
    
    //Get the size of the current window
    Rectangle2D screen = Screen.getPrimary().getVisualBounds();   

    //Pane size
	final double width = screen.getWidth()/2.5;
	final double height = screen.getHeight()/1.5;
	
    @FXML
    void initialize() {
    	//Resize the background image
    	
        Image_View.setLayoutX(screen.getMaxX());
        Image_View.setLayoutY(screen.getMaxY()); 
        
        //Import css file
        Anchor_Pane_2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        //Profile 
        BorderPane profile = new BorderPane();
        profile.setId("A_profile");
        getProfilePane(profile, width, height);
        
        //Remainder
        BorderPane remainder = new BorderPane();
        remainder.setId("remainder");
        getRemainderPane(remainder, width, height);
        
        //Status
        BorderPane status = new BorderPane();
        status.setId("status");
        getStatusPane(status, width, height);
        
        //Friend
        SplitPane friend = new SplitPane(new Pane(), new Pane());
        friend.setId("friend");
        getFriendPane(friend, width, height);
        
        //History
        Pane history = new Pane();
        history.setId("history");
        getHistoryPane(history, width, height);
        
        
        //Change output pane
        Profile.setOnAction(e-> {
        	if(Anchor_Pane_2.getChildren().size() > 2)
        		Anchor_Pane_2.getChildren().remove(2);
        	Anchor_Pane_2.getChildren().add(profile);
        });
        Remainder.setOnAction(e-> {      	
        	if(Anchor_Pane_2.getChildren().size() > 2)
        		Anchor_Pane_2.getChildren().remove(2);
        	Anchor_Pane_2.getChildren().add(remainder);
        });
        Status.setOnAction(e-> {
        	if(Anchor_Pane_2.getChildren().size() > 2)
        		Anchor_Pane_2.getChildren().remove(2);
        	Anchor_Pane_2.getChildren().add(status);
        });
        Friend.setOnAction(e->{
        	if(Anchor_Pane_2.getChildren().size() > 2)
        		Anchor_Pane_2.getChildren().remove(2);
        	Anchor_Pane_2.getChildren().add(friend);
        });
        History.setOnAction(e->{
        	if(Anchor_Pane_2.getChildren().size() > 2)
        		Anchor_Pane_2.getChildren().remove(2);
        	Anchor_Pane_2.getChildren().add(history);
        });
    }
    
    //Return to the homepage
    @FXML
    private void homePage(ActionEvent event) throws IOException {
    	Parent home_page = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Stage home_scene = (Stage) Profile.getScene().getWindow();
        home_scene.setScene(new Scene(home_page));
        home_scene.setTitle("Homepage");
        home_scene.show();
    }

    //Create remainder contents
    private void getRemainderPane(BorderPane remainder, double width, double height) {
    	contentPane(remainder, width, height);
    	remainder.setPadding(new Insets(10,10,10,10));
    	//Create scroll pane
    	ScrollPane scrollPane = new ScrollPane();
    	getSetting(scrollPane, width, height*0.82, 0, height*0.15);
    	scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
    	scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    	
    	//Create GridPane
    	GridPane gridPane = new GridPane();
    	gridPane.setVgap(20);
    	gridPane.setPadding(new Insets(10,10,10,10));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(width));
    	
    	//Create title
    	Label title = new Label("Scheduled Appointments");
    	getSetting(title, width, height*0.15, 0, 0);
    	title.setAlignment(Pos.CENTER);
    	title.setFont(Font.font("Verdana",30));
    	title.setStyle("-fx-background-color: #FFA07A;");
    	
    	//List all avaliable appointments
    	for(int i = 0; i < 10; ++i) {
    		//Labels of appointments
    		Label appointments = new Label("Meeting time\nXXXX/XX/XX");
    		getSetting(appointments, width, height*0.1, 0, 0);
    		appointments.setAlignment(Pos.CENTER);
    		GridPane.setHalignment(appointments, HPos.CENTER);
    		gridPane.add(appointments, 0, i);
    		
    		//separator
        	Separator appSeparator = new Separator();
        	appSeparator.setMaxWidth(width*0.5);
        	GridPane.setValignment(appSeparator, VPos.BOTTOM);
        	GridPane.setHalignment(appSeparator, HPos.CENTER);
        	gridPane.add(appSeparator, 0, i);
    	}
    	
    	scrollPane.setContent(gridPane);
    	remainder.setTop(title);
    	remainder.setCenter(scrollPane);
    }
    
    //Create history contents
    private void getHistoryPane(Pane history, double width, double height) {
    	contentPane(history, width, height);
    }
    
    //Create friend contents
    private void getFriendPane(SplitPane friend, double width, double height) {
    	contentPane(friend, width, height);
    	friend.setPadding(new Insets(10,10,10,10));
    	//Create whitebox and blacklist
    	Pane pane_1 = (Pane) friend.getItems().get(0);
    	Pane pane_2 = (Pane) friend.getItems().get(1);
    	
    	pane_1.setMinSize(width/2, height);
    	pane_2.setMinSize(width/2, height);
    	
    	//Titles
    	Label tag_1 = new Label("WhiteBox");
    	tag_1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
    	tag_1.setStyle("-fx-background-color: #00ff00;");
    	tag_1.setAlignment(Pos.CENTER);
    	getSetting(tag_1, width/2, 70, 0, 0);
    	
    	Label tag_2 = new Label("Blacklist");
    	tag_2.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
    	tag_2.setStyle("-fx-background-color:#a9a9a9;");
    	tag_2.setAlignment(Pos.CENTER);
    	getSetting(tag_2, width/2, 70, 0, 0);
    	
    	
    	//Friend list
    	ScrollPane scrollPane = new ScrollPane();
    	getSetting(scrollPane, width*0.5, height*0.8, 0, 75);
    	scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
    	scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    	//Grid pane for saving each friends
    	GridPane gridPane = new GridPane();
    	gridPane.setStyle("-fx-background-color: #f8f8ff;");
    	gridPane.getColumnConstraints().add(new ColumnConstraints(width*0.5));
    	for(int i = 0; i < 5; ++i) {
    		//MenuButton for each friend
    		Label friendName = new Label("Unknown" + i);
    		friendName.setAlignment(Pos.CENTER);
    		getSetting(friendName, width/2, 40, 0, 0);
    		
    		//Create context menu
    		ContextMenu contentMenu = new ContextMenu();
    		contentMenu.getItems().addAll(new MenuItem("Inspect"),
    				new MenuItem("BlackList"),
    				new MenuItem("Delete"));
    		friendName.setContextMenu(contentMenu);
    
    		//Mouse enter and exit
    		friendName.setOnMouseEntered(e->friendName.setStyle("-fx-background-color: #00bfff;"));	
    		friendName.setOnMouseExited(e-> friendName.setStyle("-fx-background-color: #f8f8ff;"));
    		
    		//Set to each column
    		GridPane.setHalignment(friendName, HPos.CENTER);
    		gridPane.add(friendName, 0, i);
    		
    		//Delete event
    		contentMenu.getItems().get(2).setOnAction(e-> gridPane.getChildren().remove(friendName));
    	}
    	
    	//Add button
    	Button add = new Button("Add");
    	getSetting(add, width*0.1, 30, 10, height-50);
    	getStyle(add,"-fx-border-color:#000000;");
    	//Add button event
    	add.setOnAction(e-> addFriend());
    	
    	//Blacklist
    	ScrollPane scrollPane_2 = new ScrollPane();
    	getSetting(scrollPane_2, width*0.5, height*0.8, 0, 75);
    	scrollPane_2.setVbarPolicy(ScrollBarPolicy.NEVER);
    	scrollPane_2.setHbarPolicy(ScrollBarPolicy.NEVER);
    	//Grid pane for saving each friends
    	GridPane gridPane_2 = new GridPane();
    	gridPane_2.setStyle("-fx-background-color: #f8f8ff;");
    	gridPane_2.getColumnConstraints().add(new ColumnConstraints(width*0.5));

    	for(int j = 0; j < 5; ++j) {
    		//MenuButton for each friend
    		Label blacklister = new Label("Unknown");
    		blacklister.setAlignment(Pos.CENTER);
    		getSetting(blacklister, width*0.5, 40, 0, 0);
    		
    		//Create context menu
    		ContextMenu contentMenu = new ContextMenu();
    		contentMenu.getItems().addAll(new MenuItem("Remove"));
    		blacklister.setContextMenu(contentMenu);
    		
    		//Mouse enter and exit
    		blacklister.setOnMouseEntered(e-> blacklister.setStyle("-fx-background-color: #00bfff;"));	
    		blacklister.setOnMouseExited(e-> blacklister.setStyle("-fx-background-color: #f8f8ff;"));
    		
    		//Set to each column
    		GridPane.setHalignment(blacklister, HPos.CENTER);
    		gridPane_2.add(blacklister, 0, j);
    		
    		//Remove event
    		contentMenu.getItems().get(0).setOnAction(e-> gridPane_2.getChildren().remove(blacklister));
    	}
    	
    	scrollPane.setContent(gridPane);
    	scrollPane_2.setContent(gridPane_2);
    	pane_1.getChildren().addAll(tag_1, scrollPane, add);
    	pane_2.getChildren().addAll(tag_2, scrollPane_2);
    }
    
    //Create status contents
    private void getStatusPane(BorderPane status, double width, double height) {
    	contentPane(status, width, height);
    	status.setPadding(new Insets(10,10,10,10));
    	//Title
    	Label title = new Label("Status");
    	title.setAlignment(Pos.CENTER);
    	title.setFont(Font.font ("Verdana", 30));
    	title.setPrefSize(width, height*0.15);
    	
    	title.setStyle("-fx-background-color: #FFA07A;");
    	status.setTop(title);
    	//Contents
    	GridPane gridPane = new GridPane();
    	gridPane.getColumnConstraints().add(new ColumnConstraints(width*0.3));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(width*0.3));
    	gridPane.setPadding(new Insets(height*0.05,0,0,width*0.2));
    	final String[] tags = {"Project completed: ", "Group engaged: ", "Penalty received: ", "Reputation scores: ",
    			"Status: ", "Evaluation: ", "Date of join: "};
    	for(int i = 0; i < 7; ++i) {
    		gridPane.getRowConstraints().add(new RowConstraints(height*0.1));
    		//Create tags
    		Label tag = new Label(tags[i]);
    		gridPane.add(tag, 0, i);
    		GridPane.setHalignment(tag, HPos.CENTER);
    		//Separator
    		Separator separator = new Separator();
    		separator.setMinWidth(width*0.3);
    		GridPane.setValignment(separator, VPos.BOTTOM);
    		gridPane.add(separator, 1, i);
    		//set numbers
    		Label number = new Label("Unknown" + i);
    		number.setFont(Font.font(null, FontWeight.BOLD, 14));
    		getSetting(number, width*0.1, height*0.05, 0, 0);
    		number.setStyle("-fx-background-color: #00ffff;" 
    						+ "-fx-border-color: #00ffff;");
    		GridPane.setHalignment(number, HPos.CENTER);
    		gridPane.add(number, 1, i);
    	}
    	
    	status.setCenter(gridPane);
    }

    //Create profile contents
    private void getProfilePane(BorderPane profile, double width, double height) {
    	contentPane(profile, width, height);
    	profile.setPadding(new Insets(10,10,10,10));
    	//Title
    	Label title = new Label("Profile");
    	getSetting(title, width, height*0.15, 0, 0);
    	title.setAlignment(Pos.CENTER);
    	title.setFont(Font.font("Verdana",30));
    	title.setStyle("-fx-background-color: #FFA07A;");
    	
    	//Create the frame of image
    	Rectangle rectangle = new Rectangle(0,0,width*0.35,height*0.35);
    	rectangle.setArcWidth(40);
    	rectangle.setArcHeight(40);
    	//Get the image from the folder
    	File f = new File("@../../Images/Image_of_none.png");
        Image image = new Image(f.toURI().toString());
        //Set image to the imagview
    	ImageView Image_View = new ImageView(image);
    	Image_View.setFitWidth(width*0.35);
    	Image_View.setFitHeight(height*0.35);
    	Image_View.setClip(rectangle);
    	Image_View.setLayoutX(width*0.05);
    	Image_View.setLayoutY(height*0.2);
    	
    	//Change protrait
    	Button protrait = new Button("Edit protrait");
    	getSetting(protrait, width*0.15, height*0.05, width*0.05+ width*0.35/2-width*0.075, height*0.56);
    	getStyle(protrait,"-fx-border-color:#000000;");
    	
    	GridPane gridPane1 = new GridPane();
    	gridPane1.setPadding(new Insets(width*0.1,0,0,0));
    	gridPane1.setVgap(10);
    	gridPane1.setLayoutX(width*0.45);
    	gridPane1.getColumnConstraints().add(new ColumnConstraints(width*0.5));
    	
    	//Set protrait button and image
    	gridPane1.getRowConstraints().add(new RowConstraints());
    	GridPane.setHalignment(Image_View, HPos.CENTER);
    	GridPane.setHalignment(protrait, HPos.CENTER);
    	gridPane1.add(Image_View, 0, 0);
    	gridPane1.add(protrait, 0, 1);
    	
    	//Button for change password
    	Button changePassword = new Button("Reset password");
    	getSetting(changePassword, width*0.25, height*0.05, 0, 0);
    	getStyle(changePassword,"-fx-border-color:#000000;");
    	gridPane1.getRowConstraints().add(new RowConstraints(height*0.1));
    	GridPane.setHalignment(changePassword, HPos.CENTER);
    	gridPane1.add(changePassword, 0, 2);
    	
    	//Edit protrait button event
    	protrait.setOnAction(e-> {
			try {
				editProtrait(Image_View);
			} catch (IOException el) {
				// TODO Auto-generated catch block
				el.printStackTrace();
			}
		});
    	
    	GridPane gridPane2 = new GridPane();
    	gridPane2.setPadding(new Insets(width*0.1,0,0,0));
    	gridPane2.getColumnConstraints().add(new ColumnConstraints(width*0.25));
    	gridPane2.getColumnConstraints().add(new ColumnConstraints(width*0.25));
       	//Set personal informations
    	final String[] tags = {"Name :", "ID :", "Email :", "Position :"};
    	for(int i = 0; i < tags.length; ++i) {
    		Label tag = new Label(tags[i]);
    		tag.setStyle("-fx-border-color:#fffaf0");
    		gridPane2.getRowConstraints().add(new RowConstraints(height*0.1));
    		GridPane.setHalignment(tag, HPos.CENTER);
    		gridPane2.add(tag, 0, i);
    		
    		Label val = new Label("Unknown");
    		val.setStyle("-fx-border-color:#fffaf0");
    		val.setFont(Font.font(null, FontWeight.BOLD, 14));
    		val.setStyle("-fx-background-color: #00ffff;" 
					+ "-fx-border-color: #00ffff;");
    		GridPane.setHalignment(val, HPos.CENTER);
    		gridPane2.add(val, 1, i);
    		
    		//separator
        	Separator appSeparator = new Separator();
        	appSeparator.setMaxWidth(width/2);
        	GridPane.setValignment(appSeparator, VPos.BOTTOM);
        	GridPane.setHalignment(appSeparator, HPos.CENTER);
        	gridPane2.add(appSeparator, 1, i);
    	}
    	
    	//Change password button event
    	changePassword.setOnAction(e-> passwordChangeScene(width*0.4, height*0.5));
    	
    	profile.setLeft(gridPane1);
    	profile.setCenter(gridPane2);
    	profile.setTop(title);
    }
    
    //New window for change password
    private void passwordChangeScene(double w, double h) {
    	Stage mainScene = (Stage) Scroll_Pane.getScene().getWindow();
    	Pane pane = new Pane();
    	pane.setPrefSize(w, h);
    	Scene secondScene = new Scene(pane, w, h);
    	Stage newWindow = new Stage();
    	
    	//Create gridpane 
    	GridPane gridpane = new GridPane();
    	gridpane.getColumnConstraints().add(new ColumnConstraints(w*0.8));
    	gridpane.setPadding(new Insets(10,w*0.1,10,w*0.1));
    	
    	String[] tags = {"Old password", "New password", "Repeat password", "Confirm"};
    	for(int i = 0; i < 7; ++i) {
    		gridpane.getRowConstraints().add(new RowConstraints(h*0.12));
    		if (i == 6) {
    			//create confirm button
    			Button confirm = new Button("Confirm");
    			getStyle(confirm,"-fx-border-color:#000000;");
    			GridPane.setHalignment(confirm, HPos.CENTER);
    			gridpane.add(confirm, 0, i+3);
    		}
    		else if(i%2 == 0) {
    			//create tag
    			Label tag = new Label(tags[i/2]);
    			GridPane.setValignment(tag, VPos.BOTTOM);
    			GridPane.setHalignment(tag, HPos.LEFT);
    			gridpane.add(tag, 0, i);
    		}
    		else {
    			//Textfield
    			PasswordField field = new PasswordField();
    			field.setMaxWidth(w*0.8);
    			getStyle(field,"-fx-border-color:#000000;");
    			GridPane.setValignment(field, VPos.TOP);
    			GridPane.setHalignment(field, HPos.LEFT);
    			gridpane.add(field, 0, i);
    		}
    	}
    	
    	//set children
    	pane.getChildren().add(gridpane);
    	//Call new window
    	NewWindow(newWindow, secondScene, mainScene, "Password change", screen.getWidth()/2, screen.getHeight()/3);    	
    }
    
	//Edit protrait
    private void editProtrait(ImageView iv) throws IOException {
    	//Open file chooser
    	 FileChooser fileChooser = new FileChooser();
    	 fileChooser.setTitle("Open Resource File");
    	 fileChooser.getExtensionFilters().add(
    	         new ExtensionFilter("Image Files", "*.png", "*.jpg"));
    	 File selectedFile = fileChooser.showOpenDialog(Anchor_Pane_2.getScene().getWindow());
    	 if(selectedFile != null) {
    		 //Set new protrait
    		 Image image = new Image(selectedFile.toURI().toString());
    		 iv.setImage(image);
    		 //Save new protrait to the source folder
    		 Path from = Paths.get(selectedFile.toURI());
    	        Path to = Paths.get("@../../Images/" + selectedFile.getName());
    	        CopyOption[] options = new CopyOption[]{
    	                StandardCopyOption.REPLACE_EXISTING,
    	                StandardCopyOption.COPY_ATTRIBUTES
    	        };
    	        Files.copy(from, to, options);
    	 }
    }
    
    //Add friend
    private void addFriend() {
    	Stage mainScene = (Stage) Scroll_Pane.getScene().getWindow();
    	Pane pane = new Pane();
    	pane.setPrefSize(300,150);
    	Scene scene = new Scene(pane, 300, 150);
    	Stage newWindow = new Stage();
    	
    	GridPane gridPane = new GridPane();
    	gridPane.setPadding(new Insets(5,5,5,5));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(80));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(200));
    	gridPane.getRowConstraints().add(new RowConstraints(70));
    	
    	//Search button
    	Button search = new Button("Search");
    	getSetting(search, 80, 30, 0, 0);
    	getStyle(search,"-fx-border-color:#000000;");
    	gridPane.add(search, 0, 0);
    	
    	//Textfield
    	TextField textField = new TextField();
    	textField.setPromptText("ID");
    	getSetting(textField, 200, 30, 0, 0);
    	getStyle(textField, "-fx-border-color:#000000;");
    	gridPane.add(textField, 1, 0);
    	
    	//Search result (Invalid ID)
    	Label invalidId = new Label("Invalid ID");
    	invalidId.setAlignment(Pos.CENTER);
    	invalidId.setTextFill(Color.web("#dc143c"));
    	getSetting(invalidId, 300, 30, 0, 60);
    	invalidId.setVisible(false);
    	
    	//Search result Valid ID
    	Button add = new Button("Add");
    	getSetting(add, 80, 30, 0, 0);
    	getStyle(add, "-fx-border-color:#000000;");
    	gridPane.add(add, 0, 1);
    	add.setVisible(false);
    	
    	Label name = new Label("Unknown");
    	name.setAlignment(Pos.CENTER);
    	getSetting(name, 200, 30, 0, 0);
    	gridPane.add(name, 1, 1);
    	name.setVisible(false);
    	
    	search.setOnAction(e->{
    		if(textField.getText().isEmpty()) {
    			add.setVisible(true);
    			name.setVisible(true);
    			invalidId.setVisible(false);
    			getStyle(add, "-fx-background-color:#7cfc00;");
    			add.setOnAction(a->{
    				getStyle(add, "-fx-background-color:#dcdcdc;");
    			});
    		}
    		else {
    			add.setVisible(false);
    			name.setVisible(false);
    			invalidId.setVisible(true);
    		}
    	});
    	pane.getChildren().addAll(gridPane, invalidId);
    	NewWindow(newWindow, scene, mainScene, "Search", width/2, height/3);
    }
        
    //Create alert
   /* private void getAlert(Stage stage) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "New password created.", ButtonType.OK);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText(null);
    	alert.showAndWait();
    	alert.setX(screen.getWidth()/2);
    	alert.setY(screen.getHeight()/3);
    	stage.close();
    }*/
    
    
    //Create new window
    //Parameters, newWindow: the target window
    //			  scene: the scene in the newWindow
    //			  mainStage: the parent window of newWindow
    //			  title: the name of newWindow
    //			  x : x-axis, y : y-axis
    private void NewWindow(Stage newWindow, Scene scene, Stage mainStage, String title, double x, double y) {
        newWindow.setTitle(title);
        newWindow.setScene(scene);
        newWindow.setResizable(false);
        
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(mainStage);
        
        //Set the window default position
        newWindow.setX(x);
        newWindow.setY(y);
        newWindow.show();
    }
    
    //Get the content pane
    private void contentPane(Node n, double w, double h) {
    	((Region) n).setPrefSize(w, h);
    	n.setStyle("-fx-background-color: #fffaf0;");
    	n.setLayoutX(100);
    	n.setLayoutY(150);
    }
    
    //Set up general style
    private void getStyle(Node n, String str) {
    	n.setStyle("-fx-background-radius: 20;"
    				+ "-fx-border-radius:20;"
    				+ str);
    }
    
    //Set up error style
    /*private void getErrorStyle(Node n) {
    	n.setStyle("-fx-border-color: #dc143c;" 
					+ "-fx-border-radius: 20;" 
					+ "-fx-background-radius: 20;");
    }*/
  
    //Set up general setting of component
    private void getSetting(Node n, double w, double h, double x, double y) {
    	((Region) n).setPrefSize(w,h);
    	n.setLayoutX(x);
    	n.setLayoutY(y);
    	n.setFocusTraversable(false);
    }    
}
