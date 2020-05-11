package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import Clients.Client;
import Group.Group_List;
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


public class AccountpageController {

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
        
    //User list 
    private UserList userList = new UserList();
    //User information
    private Information_List Info_List = new Information_List();
    //User
    private Client target;
    //Group list
    private Group_List group_List = new Group_List();
    //Image path
    private String ImagePath = "@../../Images/";
	// Target index in the user list
	private int UserIndex = 0;
    
    //Get the size of the current window
    private Rectangle2D screen = Screen.getPrimary().getVisualBounds();   

    //Pane size
	private final double width = screen.getWidth()/2.5;
	private final double height = screen.getHeight()/1.5;
	
	//Blacklist size & whitebox size
	private int BlacklistSize = 0;
	private int WhiteboxSize = 0;
	private int AppointmentSize = 0;
	
    @FXML
    void initialize() {
    	//Resize the background image
        Image_View.setLayoutX(screen.getMaxX());
        Image_View.setLayoutY(screen.getMaxY()); 
        
        //Import css file
        Anchor_Pane_2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    }
    
    //Return to the homepage
    @FXML
    private void homePage(ActionEvent event) throws IOException {
    	FXMLLoader Loader = sceneSwitch("Homepage.fxml", "Home");
    	HomepageController hc = Loader.getController();
    	hc.AccountToHome(this.userList, this.Info_List, this.target, this.group_List);
    }

    //Create remainder contents
    private void getRemainderPane(BorderPane remainder, double width, double height) {
    	this.UserIndex = getUserIndex(); //Refresh 
    	
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
    	for(int i = 0; i < this.AppointmentSize; ++i) {
    		//Check if appointment is expired then remove it.
    		if(this.Info_List.getInfo_Con().get(UserIndex).getAppointment().get(i).getDate().compareTo(getCurrentTime()) < 0) {
    			this.Info_List.removeAppointment(this.target.getID(), this.Info_List.getInfo_Con().get(UserIndex).getAppointment().get(i));
    		}
    		else {
    			//Labels of appointments
    			String str = "Meeting time: " + this.Info_List.getInfo_Con().get(UserIndex).getAppointment().get(i).getDate() +
    					"\nDuration: " + Double.toString(this.Info_List.getInfo_Con().get(UserIndex).getAppointment().get(i).getDuration()) + " hours.";
    			Label appointments = new Label(str);
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
    	
    	//Add button
    	Button add = new Button("Add");
    	getSetting(add, width*0.1, 30, 10, height-50);
    	getStyle(add,"-fx-border-color:#000000;");
    	
    	GridPane gridPane = new GridPane();	//Firend gridpane
    	GridPane gridPane_2 = new GridPane(); //Blacklist gridpane
    	
    	//Friend list
    	ScrollPane scrollPane = getScrollPane(gridPane, width, height);
    	//Blacklist
    	ScrollPane scrollPane_2 = getScrollPane(gridPane_2, width, height);
    	
    	//Get blackList
    	getBlackList(gridPane_2, 0);

    	//Get whitebox 
    	getWhiteBox(gridPane, gridPane_2, 0);
    	
    	//Add button event
    	add.setOnAction(e-> addFriend(gridPane, gridPane_2));
    	
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
    		Label number = new Label();
    		number.setFont(Font.font(null, FontWeight.BOLD, 14));    		
    		GridPane.setHalignment(number, HPos.CENTER);
    		gridPane.add(number, 1, i);
    		
    		//Load data from the database
    		if(i == 0)	 	number.setText(Integer.toString(this.target.getTotal_Project_Completed()));
    		else if(i == 1) number.setText(Integer.toString(this.target.getTotal_Group_Engaged()));
    		else if(i == 2) number.setText(Integer.toString(this.target.getTotal_Penalty_Received()));
    		else if(i == 3) number.setText(Integer.toString(this.target.getReputationScore()));
    		else if(i == 4) number.setText(this.target.getStatus());
    		else if(i == 5) number.setText(this.target.getEvaluation());
    		else 	   		number.setText(this.target.getDate_Of_Join());
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
    	File f;
    	
    	//Check whether the user setup the protrait
    	if(this.target.getImageURL().isEmpty()) {
    		f = new File("@../../Images/Image_of_none.png");    		
    	}
    	else
    		f = new File(this.target.getImageURL());
    	
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
    		
    		Label val = new Label();		
    		val.setStyle("-fx-border-color:#fffaf0");
    		val.setFont(Font.font(null, FontWeight.BOLD, 14));
    		GridPane.setHalignment(val, HPos.CENTER);
    		gridPane2.add(val, 1, i);
    		
    		//Display user information on the profile
    		if(i == 0) val.setText(this.target.getName());
    		else if(i == 1) val.setText(this.target.getID());
    		else if(i == 2) val.setText(this.target.getEmail());
    		else 			val.setText(this.target.getPosition());
    		
    		//separator
        	Separator appSeparator = new Separator();
        	appSeparator.setMaxWidth(width/2);
        	GridPane.setValignment(appSeparator, VPos.BOTTOM);
        	GridPane.setHalignment(appSeparator, HPos.CENTER);
        	gridPane2.add(appSeparator, 1, i);
    	}
    	
    	//Change password button event
    	changePassword.setOnAction(e-> passwordChangeScene(width*0.4, height*0.4));
    	
    	profile.setLeft(gridPane1);
    	profile.setCenter(gridPane2);
    	profile.setTop(title);
    }
    
    //New window for change password

    //------------------------------Profile Pane--------------------------------------
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
    	
    	String[] tags = {"New password", "Repeat password", "Confirm"};
    	for(int i = 0; i < 5; ++i) {
    		gridpane.getRowConstraints().add(new RowConstraints(h*0.12));
    		if (i == 4) {
    			//create confirm button
    			Button confirm = new Button("Confirm");
    			getStyle(confirm,"-fx-border-color:#000000;");
    			gridpane.getRowConstraints().get(4).setPrefHeight(h*0.3);
    			GridPane.setValignment(confirm, VPos.CENTER);
    			GridPane.setHalignment(confirm, HPos.CENTER);
    			gridpane.add(confirm, 0, i);
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
    	//Confirm event
    	((Button) gridpane.getChildren().get(4)).setOnAction(e->{
    		if(((PasswordField) gridpane.getChildren().get(1)).getText().isEmpty() || 
    				((PasswordField) gridpane.getChildren().get(3)).getText().isEmpty() ||
        			((PasswordField) gridpane.getChildren().get(1)).getText().compareTo(
        					((PasswordField) gridpane.getChildren().get(3)).getText()) != 0 ||
        					this.target.getPassword().compareTo(((PasswordField) gridpane.getChildren().get(3)).getText()) == 0) {
        		getAlert(AlertType.ERROR, "Password reset failed.", ButtonType.OK, "Error");
        	}
    		else {
    			//Save password to the database
    			this.target.setPassword(((PasswordField) gridpane.getChildren().get(3)).getText());
    			//Confirmation alert
    			getAlert(AlertType.CONFIRMATION, "Your password has been resetted.", ButtonType.OK, "Information");
    			newWindow.close();
    		}
    	});
    	//set children
    	pane.getChildren().add(gridpane);
    	//Call new window
    	NewWindow(newWindow, secondScene, mainScene, "Password change");    	
    }
    //**********************************************************************************
    
    
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
    	        Path to = Paths.get(this.ImagePath + selectedFile.getName());
    	        //Save new image to the database
    	        this.target.setImageURL(this.ImagePath + selectedFile.getName());
    	        CopyOption[] options = new CopyOption[]{
    	                StandardCopyOption.REPLACE_EXISTING,
    	                StandardCopyOption.COPY_ATTRIBUTES
    	        };
    	        Files.copy(from, to, options);
    	 }
    }
    
    //Add friend
    private void addFriend(GridPane whitebox, GridPane blacklist) {
    	this.UserIndex = getUserIndex();    	
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
    	
    	Label name = new Label();
    	name.setAlignment(Pos.CENTER);
    	getSetting(name, 200, 30, 0, 0);
    	gridPane.add(name, 1, 1);
    	name.setVisible(false);
    	
    	search.setOnAction(e->{
    		if(!textField.getText().isEmpty() && isValidID(textField.getText())) {
    			name.setText(textField.getText());
    			add.setVisible(true);
    			name.setVisible(true);
    			invalidId.setVisible(false);
    			getStyle(add, "-fx-background-color:#7cfc00;");
    			add.setOnAction(a->{
    				//Add new friend to the whitebox
    				for(int i = 0; i < this.userList.getAll_Size(); ++i) {
    					if(this.userList.getAll_User().get(i).getID().compareTo(textField.getText()) == 0) {
    						this.Info_List.addFriend(this.target.getID(), this.userList.getAll_User().get(i));
    						break;
    					}
    				}
    				//refresh whitebox 
    				whitebox.getChildren().clear();
    				whitebox.getRowConstraints().clear();
    				getWhiteBox(whitebox, blacklist, 0);
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
    	NewWindow(newWindow, scene, mainScene, "Search");
    }
   
    //Inspect content
    private void inspectContent(ContextMenu contentMenu, int j, double w, double h) {
		contentMenu.getItems().get(0).setOnAction(e-> {
			Stage mainScene = (Stage) Scroll_Pane.getScene().getWindow();
			Pane pane = new Pane();
			pane.setPrefSize(w, h);
			Scene secondScene = new Scene(pane, w, h);
			Stage newWindow = new Stage();
			
			//GridPane
			GridPane gridPane = new GridPane();
			gridPane.setPadding(new Insets(10,5,10,5));
			gridPane.getColumnConstraints().add(new ColumnConstraints(w*0.3));
			gridPane.getColumnConstraints().add(new ColumnConstraints(w*0.7));
			gridPane.setVgap(10);    	 
			String[] tags = {"Name:", "ID:", "Position:", "Email:"};
			//list member's informations
			for(int i = 0; i < tags.length; ++i) {
				Label tag = new Label(tags[i]);
				GridPane.setHalignment(tag, HPos.CENTER);
				gridPane.getRowConstraints().add(new RowConstraints(h*0.2));
				gridPane.add(tag, 0, i);
				
				Label value = new Label();
				GridPane.setHalignment(value, HPos.CENTER);
				gridPane.add(value, 1, i);
				
				if(i == 0) value.setText(this.Info_List.getInfo_Con().get(UserIndex).getWhitebox().get(j).getName());
				else if(i == 1) value.setText(this.Info_List.getInfo_Con().get(UserIndex).getWhitebox().get(j).getID());
				else if(i == 2) value.setText(this.Info_List.getInfo_Con().get(UserIndex).getWhitebox().get(j).getPosition());
				else			value.setText(this.Info_List.getInfo_Con().get(UserIndex).getWhitebox().get(j).getEmail());
			}	
			
			pane.getChildren().add(gridPane);
			//Call new window
			NewWindow(newWindow, secondScene, mainScene, "Information");  
		});
    }
    
    //Blacklist member
    //---------------------------------Friend Pane-----------------------------------------
    private void getBlackList(GridPane gridPane, int start) {
    	//Get the user index in the user list
    	this.UserIndex = getUserIndex();
    	
    	for(int j = start; j < this.BlacklistSize; ++j) {
    		//MenuButton for each friend
    		Label blacklister = new Label(this.Info_List.getInfo_Con().get(UserIndex).getPersonal_Blacklist().get(j).getName());
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
    		gridPane.add(blacklister, 0, j);
    		
    		//Remove event
    		deleteBlacklistMember(gridPane, blacklister, contentMenu, this.Info_List.getInfo_Con().get(UserIndex).getPersonal_Blacklist().get(j));
    	}
    }
     
    private void getWhiteBox(GridPane gridPane, GridPane gridPane_2, int start) {
    	//Get the user index in the user list
    	this.UserIndex = getUserIndex();
    	
    	for(int i = 0; i < this.WhiteboxSize; ++i) {
    		//MenuButton for each friend
    		Label friendName = new Label(this.Info_List.getInfo_Con().get(UserIndex).getWhitebox().get(i).getName());
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
    		
    		//Target client
    		Client client = this.Info_List.getInfo_Con().get(UserIndex).getWhitebox().get(i);
    		//Delete event
    		deleteWhiteboxMember(gridPane, friendName, contentMenu, client);
    		
        	//Blacklist event
        	WhiteboxToBlacklist(gridPane, gridPane_2, friendName, contentMenu, client);
    		
    		//Inspect event
    		inspectContent(contentMenu, i, width*0.4, height*0.4);
    	}
    }
    
    private void deleteWhiteboxMember(GridPane gridPane, Label friendName, ContextMenu contentMenu, Client client) {
		contentMenu.getItems().get(2).setOnAction(e-> {
			this.Info_List.removeFriend(this.target.getID(), client);
			friendName.getContextMenu().getItems().clear();
			gridPane.getChildren().remove(friendName);
		});
    }
    
    private void deleteBlacklistMember(GridPane gridPane, Label blacklister, ContextMenu contentMenu, Client client) {
    	contentMenu.getItems().get(0).setOnAction(e-> {
    		this.Info_List.removeBlacklist(this.target.getID(), client);
    		blacklister.getContextMenu().getItems().clear();
    		gridPane.getChildren().remove(blacklister);
    	});
    }
    
    private void WhiteboxToBlacklist(GridPane whitebox, GridPane blacklist, Label friendName, ContextMenu contentMenu, Client client) {
		contentMenu.getItems().get(1).setOnAction(e->{
			this.Info_List.addBlacklist(this.target.getID(), client);
			
			//Remove from the whitebox
			this.Info_List.removeFriend(this.target.getID(), client);
			friendName.getContextMenu().getItems().clear();
			whitebox.getChildren().remove(friendName);
			
			getBlackList(blacklist, 0); //Refresh
		});
    }
    
    private boolean isValidID(String id) {
    	boolean result = false;
    	//Check is valid id
    	for(int i = 0; i < this.userList.getAll_Size(); ++i) {
    		if(this.userList.getAll_User().get(i).getID().compareTo(id) == 0)
    			result = true;
    	}
    	//Check if the already existed in the whitebox
    	for(int i = 0; i < this.Info_List.getInfo_Con().get(UserIndex).getWhitebox().size(); ++i) {
    		if(this.Info_List.getInfo_Con().get(UserIndex).getWhitebox().get(i).getID().compareTo(id) == 0)
    			result = false;
    	}
    	//Check that the blacklist doesn't contain the new friend
    	for(int i = 0; i < this.Info_List.getInfo_Con().get(UserIndex).getPersonal_Blacklist().size(); ++i) {
    		if(this.Info_List.getInfo_Con().get(UserIndex).getPersonal_Blacklist().get(i).getID().compareTo(id) == 0)
    			result = false;
    	}
    	return result;
    }
    
    private ScrollPane getScrollPane(GridPane gridPane, double w, double h) {
    	ScrollPane scrollPane = new ScrollPane();
    	getSetting(scrollPane, w*0.5, h*0.8, 0, 75);
    	scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
    	scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    	gridPane.setStyle("-fx-background-color: #f8f8ff;");
    	gridPane.getColumnConstraints().add(new ColumnConstraints(width*0.5));
    	return scrollPane;
    }
    //************************************************************************************* 
 
    //Create alert
    private void getAlert(AlertType at, String content, ButtonType bt, String title) {
    	Alert alert = new Alert(at, content, bt);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.showAndWait();
    }
    
    
    //Create new window
    //Parameters, newWindow: the target window
    //			  scene: the scene in the newWindow
    //			  mainStage: the parent window of newWindow
    //			  title: the name of newWindow
    //			  x : x-axis, y : y-axis

    private void NewWindow(Stage newWindow, Scene scene, Stage mainStage, String title) {
        newWindow.setTitle(title);
        newWindow.setScene(scene);
        newWindow.setResizable(false);
        
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(mainStage);
        
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

    private int getUserIndex() {
    	int result = 0;
    	for(int i = 0; i < this.Info_List.getInfo_Con().size(); ++i) {
    		if(this.target.getID().compareTo(this.Info_List.getInfo_Con().get(i).getID()) == 0) {
    			this.WhiteboxSize = this.Info_List.getInfo_Con().get(i).getWhitebox().size();
    			this.BlacklistSize = this.Info_List.getInfo_Con().get(i).getPersonal_Blacklist().size();
    			this.AppointmentSize = this.Info_List.getInfo_Con().get(i).getAppointment().size();
    			result = i;
    			break;
    		}
    	}
    	return result;
    }
    
    //Get the current time
    private String getCurrentTime() {
    	DateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd hh:mm");
  		Calendar cal = Calendar.getInstance();
  		String currentDate = dateFormat.format(cal.getTime()).toString();
  		return currentDate;
    }
    
	public void HomeToAccount(UserList userList, Information_List info_List, Client client, Group_List gl) {
		this.userList = userList;
		this.Info_List = info_List;
		this.target = client;
		this.group_List = gl;
		
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
	
	private FXMLLoader sceneSwitch(String url, String title) throws IOException {
		FXMLLoader Loader = new FXMLLoader(getClass().getResource(url));
        Parent home_pane = Loader.load();
        Stage stage = (Stage) Profile.getScene().getWindow();
        stage.setScene(new Scene(home_pane));
        stage.setTitle(title);
        return Loader;
	}
}
