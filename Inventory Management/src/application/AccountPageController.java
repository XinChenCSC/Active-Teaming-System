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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
	double width = screen.getWidth()/2.5;
	double height = screen.getHeight()/1.5;
	
    @FXML
    void initialize() {
    	//Resize the background image
        Image_View.setLayoutX(screen.getWidth());
        Image_View.setLayoutY(screen.getHeight()); 
        
        
        //Profile 
        Pane profile = new Pane();
        getProfilePane(profile, width, height);
        profile.setOpacity(0);
        
        //Remainder
        Pane remainder = new Pane();
        getRemainderPane(remainder, width, height);
        remainder.setOpacity(0);
        //Status
        Pane status = new Pane();
        getStatusPane(status, width, height);
        status.setOpacity(0);
        
        //Friend
        SplitPane friend = new SplitPane(new Pane(), new Pane());
        getFriendPane(friend, width, height);
        friend.setOpacity(0);
        
        Anchor_Pane_2.getChildren().addAll(profile, remainder, status, friend);
        
        Profile.setOnAction(e-> {
        	profile.setOpacity(1);
        	status.setOpacity(0);
        	friend.setOpacity(0);
        	remainder.setOpacity(0);
        });
        Remainder.setOnAction(e-> {
        	remainder.setOpacity(1);
        	profile.setOpacity(0);
        	status.setOpacity(0);
        	friend.setOpacity(0);
        });
        Status.setOnAction(e-> {
        	status.setOpacity(1);
        	profile.setOpacity(0);
        	friend.setOpacity(0);
        	remainder.setOpacity(0);
        });
        Friend.setOnAction(e->{
        	friend.setOpacity(1);
        	profile.setOpacity(0);
        	status.setOpacity(0);
        	remainder.setOpacity(0);
        });
    }
    
    @FXML
    void homePage(ActionEvent event) throws IOException {
    	Parent home_page = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Stage home_scene = (Stage) Profile.getScene().getWindow();
    
        home_scene.setScene(new Scene(home_page));
        home_scene.setTitle("Homepage");
        home_scene.show();
    }

    //Create remainder contents
    void getRemainderPane(Pane remainder, double width, double height) {
    	contentPane(remainder, width, height);
    	
    }
    
    //Create friend contents
    void getFriendPane(SplitPane friend, double width, double height) {
    	contentPane(friend, width, height);
    	Pane pane_1 = (Pane) friend.getItems().get(0);
    	Pane pane_2 = (Pane) friend.getItems().get(1);
    	
    	pane_1.setMinSize(width/2, height);
    	pane_2.setMinSize(width/2, height);
    	
    	//Titles
    	Label tag_1 = new Label("Friend");
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
    	getSetting(scrollPane, width/2, height-140, 0, 75);
    	scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
    	scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    	//Grid pane for saving each friends
    	GridPane gridPane = new GridPane();
    	gridPane.setVgap(10);
    	gridPane.setGridLinesVisible(false);
    	gridPane.setStyle("-fx-background-color: #f8f8ff;");
    	gridPane.getColumnConstraints().add(new ColumnConstraints(width/2-10));
    	for(int i = 0; i < 4; ++i) {
    		//MenuButton for each friend
    		Label friendName = new Label("Unknown" + i);
    		friendName.setAlignment(Pos.CENTER);
    		getSetting(friendName, width/2, 40, 0, 0);
    		
    		//Create context menu
    		ContextMenu contentMenu = new ContextMenu();
    		contentMenu.getItems().addAll(new MenuItem("Inspect"),
    				new MenuItem("Message"),
    				new MenuItem("Email"),
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
    		contentMenu.getItems().get(4).setOnAction(e-> gridPane.getChildren().remove(friendName));
    	}
    	
    	//Add button
    	Button add = new Button("Add");
    	getSetting(add, 80, 30, 10, height-50);
    	getStyle(add,"-fx-border-color:#000000;");
    	//Add button event
    	add.setOnAction(e-> addFriend());
    	
    	//Blacklist
    	ScrollPane scrollPane_2 = new ScrollPane();
    	getSetting(scrollPane_2, width/2, height-140, 0, 75);
    	scrollPane_2.setVbarPolicy(ScrollBarPolicy.NEVER);
    	scrollPane_2.setHbarPolicy(ScrollBarPolicy.NEVER);
    	//Grid pane for saving each friends
    	GridPane gridPane_2 = new GridPane();
    	gridPane_2.setVgap(10);
    	gridPane_2.setStyle("-fx-background-color: #f8f8ff;");
    	gridPane_2.getColumnConstraints().add(new ColumnConstraints(width/2-10));

    	for(int j = 0; j < 5; ++j) {
    		//MenuButton for each friend
    		Label blacklister = new Label("Unknown");
    		blacklister.setAlignment(Pos.CENTER);
    		getSetting(blacklister, width/2, 40, 0, 0);
    		
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
    void getStatusPane(Pane status, double width, double height) {
    	contentPane(status, width, height);
    	GridPane gridPane = new GridPane();
    	gridPane.getColumnConstraints().add(new ColumnConstraints(200));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(300));
    	gridPane.setPadding(new Insets(100,200,100,200));
    	gridPane.setVgap(20);
    	
    	String[] tags = {"Project completed: ", "Group engaged: ", "Penalty received: ", "Reputation scores: ",
    			"Status: ", "Evaluation: ", "Date of join: "};
    	for(int i = 0; i < 7; ++i) {
    		//Create tags
    		Label tag = new Label(tags[i]);
    		getSetting(tag, 200, 40, 0, 0);
    		tag.setAlignment(Pos.CENTER);
    		gridPane.add(tag, 0, i);
    		//set numbers
    		Label number = new Label("null");
    		getSetting(number, 200, 40, 0, 0);
    		number.setStyle("-fx-background-color: #00ffff;" 
    						+ "-fx-border-color: #00ffff;");
    		number.setAlignment(Pos.CENTER);
    		gridPane.add(number, 1, i);
    	}
    	status.getChildren().add(gridPane);
    }

    //Create profile contents
    void getProfilePane(Pane profile, double width, double height) {
    	contentPane(profile, width, height);
    	ColumnConstraints column = new ColumnConstraints(width/2.5);
    	GridPane Grid_Pane = new GridPane();
    	Grid_Pane.setLayoutX(width-360);
    	Grid_Pane.getColumnConstraints().add(column);
    	Grid_Pane.setPadding(new Insets(50,10,10,10));
    	Grid_Pane.setVgap(20);
    	
    	//Create the frame of image
    	Rectangle rectangle = new Rectangle(0,0,width/3,250);
    	rectangle.setArcWidth(50);
    	rectangle.setArcHeight(50);
    	//Get the image from the folder
    	File f = new File("@../../Images/Image_of_none.png");
        Image image = new Image(f.toURI().toString());
        //Set image to the imagview
    	ImageView Image_View = new ImageView(image);
    	Image_View.setFitWidth(width/3);
    	Image_View.setFitHeight(250);
    	Image_View.setClip(rectangle);
    	Image_View.setLayoutX(20);
    	Image_View.setLayoutY(100);
    	
    	//Change protrait
    	Button protrait = new Button("Edit protrait");
    	getSetting(protrait, width/3, 30, 20, 370);
    	getStyle(protrait,"-fx-border-color:#000000;");
    	
    	//Edit protrait button event
    	protrait.setOnAction(e-> {
			try {
				editProtrait(Image_View);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
    	
    	
    	//Set personal informations
    	String[] tags = {"Name :", "ID :", "Email :", "Position :", "Password :"};
    	for(int i = 0; i < 5; ++i) {
    		Label title = new Label(tags[i]);
    		title.setAlignment(Pos.CENTER_RIGHT);
    		title.setStyle("-fx-border-color:#fffaf0");
    		getSetting(title,width/7,50,0,0);
    		GridPane.setHalignment(title, HPos.LEFT);
    		Grid_Pane.add(title, 0, i);
    		
    		Label info = new Label("Unknown");
    		info.setAlignment(Pos.CENTER);
    		info.setStyle("-fx-border-color:#fffaf0");
    		getSetting(info,width/4,50,0,0);
    		GridPane.setHalignment(info, HPos.RIGHT);
    		Grid_Pane.add(info, 0, i);
    	}
    	
    		
    	//Button for change password
    	Button changePassword = new Button("Reset password");
    	getSetting(changePassword, 200, 30, 0, 0);
    	getStyle(changePassword,"-fx-border-color:#000000;");
    	GridPane.setHalignment(changePassword, HPos.CENTER);
    	Grid_Pane.add(changePassword, 0, 5);
    	
    	//Change password button event
    	changePassword.setOnAction(e-> passwordChangeScene(Grid_Pane.getChildren().get(1)));
    	
    	
    	//Personal abstract field
    	TextArea personalAbstract = new TextArea();
    	personalAbstract.setPadding(new Insets(5,5,5,5));
    	personalAbstract.setPromptText("Abstract");
    	Grid_Pane.add(personalAbstract, 0, 6);
    	
    	profile.getChildren().addAll(Image_View, protrait, Grid_Pane);		
    }
    
    //New window for change password
    void passwordChangeScene(Node n) {
    	Stage mainScene = (Stage) Scroll_Pane.getScene().getWindow();
    	Pane pane = new Pane();
    	pane.setPrefSize(350, 200);
    	Scene secondScene = new Scene(pane, 350, 300);
    	Stage newWindow = new Stage();
    	
    	//Create gridpane 
    	GridPane gridpane = new GridPane();
    	gridpane.setVgap(5);
    	gridpane.getColumnConstraints().add(new ColumnConstraints(300));
    	gridpane.setPadding(new Insets(10,20,10,20));
    	
    	String[] tags = {"Old password", "New password", "Repeat password", "Confirm"};
    	for(int i = 0; i < 7; ++i) {
    		if (i == 6) {
    			//create confirm button
    			Button confirm = new Button("Confirm");
    			getSetting(confirm, 100, 30, 0 ,0);
    			getStyle(confirm,"-fx-border-color:#000000;");
    			GridPane.setHalignment(confirm, HPos.CENTER);
    			gridpane.add(confirm, 0, i+3);
    		}
    		else if(i%2 == 0) {
    			//create tag
    			Label tag = new Label(tags[i/2]);
    			getSetting(tag,150,30,0,0);
    			GridPane.setHalignment(tag, HPos.LEFT);
    			gridpane.add(tag, 0, i);
    		}
    		else {
    			//Textfield
    			PasswordField field = new PasswordField();
    			getSetting(field, 300, 30, 0, 0);
    			getStyle(field,"-fx-border-color:#000000;");
    			GridPane.setHalignment(field, HPos.RIGHT);
    			gridpane.add(field, 0, i);
    		}
    	}
    	
    	//set children
    	pane.getChildren().add(gridpane);
    	//Call new window
    	NewWindow(newWindow, secondScene, mainScene, "Password change", screen.getWidth()/2, screen.getHeight()/3);
    	
    	//Confirm button event
    	((Button) gridpane.getChildren().get(6)).setOnAction(e->{
    		boolean check = true;
    		//Check old password
    		if(((TextField) gridpane.getChildren().get(1)).getText().compareTo(((Label) n).getText()) != 0) {
    			getErrorStyle(((TextField) gridpane.getChildren().get(1)));
    			check = false;
    		}
    		else
    			getStyle(((TextField) gridpane.getChildren().get(1)),"-fx-border-color:#000000;");
    		//Check new password and repeated password
    		if(((TextField) gridpane.getChildren().get(3)).getText().compareTo(((TextField) gridpane.getChildren().get(5)).getText()) != 0 && 
    				((TextField) gridpane.getChildren().get(3)).getText().isEmpty() || ((TextField) gridpane.getChildren().get(5)).getText().isEmpty()) {
    			getErrorStyle(((TextField) gridpane.getChildren().get(5)));
    			check = false;
    		}
    		else
    			getStyle(((TextField) gridpane.getChildren().get(5)),"-fx-border-color:#000000;");
    		
    		if(check) {
    			getAlert(newWindow);    		    			
    		}
    	});
    	
    }
    
	//Edit protrait
    void editProtrait(ImageView iv) throws IOException {
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
    void addFriend() {
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
    		if(textField.getText().compareTo("") == 0) {
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
    void getAlert(Stage stage) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "New password created.", ButtonType.OK);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText(null);
    	alert.showAndWait();
    	alert.setX(screen.getWidth()/2);
    	alert.setY(screen.getHeight()/3);
    	stage.close();
    }
    
    
    //Create new window
    //Parameters, newWindow: the target window
    //			  scene: the scene in the newWindow
    //			  mainStage: the parent window of newWindow
    //			  title: the name of newWindow
    //			  x : x-axis, y : y-axis
    void NewWindow(Stage newWindow, Scene scene, Stage mainStage, String title, double x, double y) {
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
    void contentPane(Node n, double w, double h) {
    	((Region) n).setPrefSize(w, h);
    	n.setStyle("-fx-background-color: #fffaf0;");
    	n.setLayoutX(100);
    	n.setLayoutY(150);
    }
    
    //Set up general style
    void getStyle(Node n, String str) {
    	n.setStyle("-fx-background-radius: 20;"
    				+ "-fx-border-radius:20;"
    				+ str);
    }
    
    //Set up error style
    void getErrorStyle(Node n) {
    	n.setStyle("-fx-border-color: #dc143c;" 
					+ "-fx-border-radius: 20;" 
					+ "-fx-background-radius: 20;");
    }
    
    //Set up general setting of component
    void getSetting(Node n, double w, double h, double x, double y) {
    	((Region) n).setPrefSize(w,h);
    	n.setLayoutX(x);
    	n.setLayoutY(y);
    	n.setFocusTraversable(false);
    }
}
