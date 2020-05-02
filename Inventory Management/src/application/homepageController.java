package application;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;


public class homepageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView Background;
    
    @FXML
    private MenuButton Events;

    @FXML
    private MenuButton Members;

    @FXML
    private MenuButton News;

    @FXML
    private MenuButton Projects;
    
    @FXML
    private MenuButton Profile;

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private AnchorPane anchorPane_child;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button Filter_Button;
    
    @FXML
    private GridPane Filter_Categories;
    
    @FXML
    private Button Filter_Ok;
    
    @FXML
    private ImageView Slipping_Image;
    
    @FXML
    private Pane Slipping_Pane;
    
    @FXML
    private RadioButton R1;

    @FXML
    private RadioButton R2;

    @FXML
    private RadioButton R3;

    @FXML
    private RadioButton R4;

    @FXML
    private RadioButton R5;
    
    @FXML
    private Button Notification;
    
    @FXML
    private Button Edit;
    
    @FXML
    private Button Claim;
    
    @FXML
    private Button Evaluation;
    
    @FXML
    private Pane NotificationPane;
    
	// Number of existing emails.
	private int emailNum = 5;
	// Number of existing messages
	private int messageNum = 5;
    //Window size
	private final Rectangle2D screen = Screen.getPrimary().getVisualBounds();   
	
	
    @FXML
    void initialize() throws IOException, InterruptedException {
    	//Resize the background image
        Background.setLayoutX(screen.getWidth());
        Background.setLayoutY(screen.getHeight()); 
        scrollPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        //--------------------------------------------
        //Adjust layout positions
        Profile.setLayoutX(anchorPane_child.getPrefWidth()-120);
        Profile.setId("profile");
        //Notificatino button
        Notification.setLayoutX(anchorPane_child.getPrefWidth()-240);
        Notification.setId("notification");
        //Notification pane
    	NotificationPane.setLayoutX(Notification.getLayoutX()-150);
    	NotificationPane.setStyle("-fx-background-color: #00FFFF;" +
    					"-fx-background-radius: 20;" +
    					"-fx-border-radius: 20;");
    	//Edit button
        Edit.setLayoutX(anchorPane_child.getPrefWidth()-360);
        Edit.setId("edit");
        
        //Claim button
        Claim.setLayoutX(anchorPane_child.getPrefWidth()-360);
        Claim.setId("claim");
        
        //Evaluation button
        Evaluation.setLayoutX(anchorPane_child.getPrefWidth()-510);
        Evaluation.setId("evaluation");
        //Image animation
        Images_Animation();
        
    }
    
    @FXML
    void Filter_Click(ActionEvent event) {
    	//If filter chart is open, close it, vice versa.
    	if(Filter_Categories.isVisible())
    		Filter_Categories.setVisible(false);
    	else
    		Filter_Categories.setVisible(true);
    	
    	Filter_Ok.setOnAction(e->{
    		Filter_Categories.setVisible(false);
    	});
    }

    @SuppressWarnings("unused")
	@FXML
    void Group_Click(ActionEvent event) {
    	//--------------------------Main Scene-----------------------
        //Get the main scene size
        Stage mainScene = (Stage) Profile.getScene().getWindow();
        double height = screen.getHeight()/3;
        double width = screen.getWidth()/5;
        StackPane scene = groupMainScene(width, height);
        
        Scene secondScene = new Scene(scene, width, height);
        // New window (Stage)
        Stage newWindow = new Stage();
        // set new window
        NewWindow(newWindow, secondScene, mainScene, "Group");    
        
        //Move to group
        ((Button)((HBox)scene.getChildren().get(1)).getChildren().get(1)).setOnAction(group->{
        	//Search group
        	if(((TextField)((HBox)scene.getChildren().get(1)).getChildren().get(2)).getText().isEmpty()) {
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("GroupPage.fxml"));
        		Parent group_page;
        		try {
        			newWindow.close();
        			group_page = loader.load();
        			//AccountPageController account = loader.getController();
        			Stage account_scene = (Stage) Profile.getScene().getWindow();
        			account_scene.setScene(new Scene(group_page));
        			account_scene.setTitle("Group page");
        			account_scene.show();
        		} catch (IOException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
        	}
        	else {
        		Alert alert = getAlert(AlertType.ERROR, "Wrong group ID.", ButtonType.OK, "Error");
        	}
        });
        //----------------------Get appeal scene--------------------------
        ((Button) scene.getChildren().get(2)).setOnAction(e->{
        	StackPane AppealScene = new StackPane();
        	groupAppealScene(AppealScene, width, height);
        	Scene appealScene = new Scene(AppealScene, width*0.7, height); //New scene
        	Stage newWindow_3 = new Stage();	// new stage
        	NewWindow(newWindow_3, appealScene, newWindow, "Appeal");  //Call new window
        	//If text area is empty, submission fail. 
        	((Button)AppealScene.getChildren().get(2)).setOnAction(h->{
        		if(((TextArea)AppealScene.getChildren().get(1)).getText().isEmpty()) {
        			Alert alert = getAlert(AlertType.ERROR, "Can't be Empty.", ButtonType.OK, "Error");
        		}
        		else {
        			//Submission completed, appeal button sets to invisible
        			Alert alert = getAlert(AlertType.CONFIRMATION, "Submission completed.", ButtonType.OK, "Confirmation");
        			if(alert.getResult() == ButtonType.OK) {
        				newWindow_3.close();
        				((Button)scene.getChildren().get(2)).setVisible(false);
        			}
        		}
        	});
        });
        
        //------------------------------------------------------------------
        //Group information-------------------------------------------------
        ((Button)((HBox)scene.getChildren().get(1)).getChildren().get(0)).setOnAction(e->{
        	//Create scene
        	Pane pane = groupCreateScene(width, height);
        	//Scene	
        	Scene thirdScene = new Scene(pane, 700, 600);
            // New window (Stage)
            Stage newWindow_2 = new Stage();
            // set new window
            NewWindow(newWindow_2, thirdScene, newWindow, "Informations"); 
            
            //Confirmed button click event
            ((Button)pane.getChildren().get(3)).setOnAction(c->{
            	if(((TextField)pane.getChildren().get(0)).getText().isEmpty() || ((TextField)pane.getChildren().get(1)).getText().isEmpty()) {
					Alert warning = getAlert(AlertType.WARNING, "Wrong inputs existed.", ButtonType.OK, "Caution!");
            	}
            	else {
                	Alert alert = getAlert(AlertType.INFORMATION, "Please wait for the final decision", ButtonType.OK, "Grouop confirmation");
                	if (alert.getResult() == ButtonType.OK)
                    	newWindow_2.close();
            	}
            });
        });
        //-----------------------------------------------------------------
    }
    
	@FXML
    void Email_Click(ActionEvent event) {
    	//Get the screen size-----------------------------------------------------------
        double w = screen.getWidth()/2;
        double h = screen.getHeight()/1.5;
        //Get the main scene size
        Stage mainStage = (Stage) Profile.getScene().getWindow();
        Stage newWindow = new Stage(); //New mini window
        Pane emailPane = getEmailPane(w, h);
        Scene secondScene = new Scene(emailPane, w, h); //New scene
        //Get the email list
        GridPane gridPane = (GridPane) ((ScrollPane) emailPane.getChildren().get(0)).getContent();
        //Get the compose button
        Button compose = ((Button) emailPane.getChildren().get(4));
        //Get the delete button
        Button delete = ((Button) emailPane.getChildren().get(5));
        //Get the email content
        Pane content = ((Pane) emailPane.getChildren().get(2));
    	//Compose scene
    	compose.setOnAction(e-> {
    		composeScene(gridPane, content, w*0.7, h*0.8, newWindow);
    	});
        //Set email list
    	getEmailList(w, h, newWindow, gridPane, content, 0);
        //Delete emails
        delete.setOnAction(e -> deleteEmail(emailPane, gridPane, content));
        //set new window
        NewWindow(newWindow, secondScene, mainStage, "Email"); 
}

    @FXML
    void Message_Click(ActionEvent event) {   
        //Get the main scene size
        Stage mainStage = (Stage) Profile.getScene().getWindow();     
        //Create new window
        Stage newWindow = new Stage();  
        //Create chat list
        Pane chatList = messageList(screen.getWidth()/5, screen.getHeight()/2);
        //create scene
        Scene MESSAGE_Scene = new Scene(chatList,screen.getWidth()/5, screen.getHeight()/2);
        //Create message board
        Pane messageChat = messageChat(screen.getWidth()/5, screen.getHeight()/2);
        //Message list
        GridPane gridPane = (GridPane) ((ScrollPane) chatList.getChildren().get(0)).getContent();
        //Message board 
        GridPane messageBoard = (GridPane) messageChat.getChildren().get(0);
        //Show original list
        getChatList(MESSAGE_Scene, chatList, messageChat, gridPane, 0);
        //first time message with sb
        ((ButtonBase) chatList.getChildren().get(2)).setOnAction(e-> {
        	((Label) messageBoard.getChildren().get(0)).setText("You");
        	MESSAGE_Scene.setRoot(messageChat);
        	firstMessage(MESSAGE_Scene, chatList, messageChat, (TextField) chatList.getChildren().get(5), gridPane);  
    	});
        //Delete messages
        ((Button) chatList.getChildren().get(1)).setOnAction(e-> deleteMessage(chatList, gridPane));  
        //Set new window
        NewWindow(newWindow, MESSAGE_Scene, mainStage, "Message");
    }
    
    @FXML
    void Notification_Click(ActionEvent event) {
    	getNotificationPane();
    	if(NotificationPane.isVisible()) 
    		NotificationPane.setVisible(false);
    	else
    		NotificationPane.setVisible(true);    		
    }
    
    @SuppressWarnings({ "unused", "unchecked" })
	@FXML
    void Edit_Click(ActionEvent event) {
    	 double w = screen.getWidth()*0.5;
         double h = screen.getHeight()*0.7;
         //Get the main scene size
         Stage mainStage = (Stage) Edit.getScene().getWindow();
         Stage newWindow = new Stage(); //New mini window
         TabPane tabPane = new TabPane();
         //Create tabs
         Tab tab1 = new Tab("General"); //General 
         Tab tab2 = new Tab("Group"); //Group 
         Tab tab3 = new Tab("Blacklist"); //Blacklist
         tabPane.getTabs().addAll(tab1, tab2, tab3);
         tab1.setClosable(false);
         tab2.setClosable(false);
         tab3.setClosable(false);
     	 //Import css file
     	 tabPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
     	 //-----------------------------------------------General-----------------------------------------------
     	 //Tab content methods
     	 generalTabContent(tab1, w, h);
     	 //---------------------------------------------------Group----------------------------------------------------------
         groupTabContent(tab2, w, h);
         GridPane gridPane = (GridPane) ((BorderPane) tab2.getContent()).getCenter();
         //hide all nodes
    	 for(Node node : gridPane.getChildren()) {
    		 node.setVisible(false);
    	 }
         HBox hbox = (HBox) ((BorderPane) tab2.getContent()).getTop();
         ((Button) hbox.getChildren().get(0)).setOnAction(e->{
        	 if(((TextField) hbox.getChildren().get(1)).getText().isEmpty()) {
        		 //display all ndoes if group is avaliable
        		 for(int i = 0; i < gridPane.getChildren().size(); ++i) {
        			 //Disable the last row if group is not closed.
        			 if(i >= gridPane.getChildren().size()-3)
        				 gridPane.getChildren().get(i).setDisable(true);
        			 gridPane.getChildren().get(i).setVisible(true);
        		 }
        		 ComboBox<String> member = (ComboBox<String>) gridPane.getChildren().get(1); //get the member combobox
                 for(int i = 0; i < gridPane.getChildren().size(); ++ i) {
                	 if(gridPane.getChildren().get(i) instanceof Button) {
                		 Button button = ((Button) gridPane.getChildren().get(i)); //Confirm button
                		 if(i == 4) {
                			 ComboBox<String> previous_node = (ComboBox<String>) gridPane.getChildren().get(i-1);
                			 button.setOnAction(t->{
                				 if(!member.getSelectionModel().isEmpty() && !previous_node.getSelectionModel().isEmpty()) {
                					 Alert alert = getAlert(AlertType.CONFIRMATION, "Done.", ButtonType.OK, "Confirmation");
                					 button.setDisable(true);        				 
                				 }
                				 else {
                					 Alert alert = getAlert(AlertType.ERROR, "Invalid input.", ButtonType.OK, "Error");
                				 }
                			 });		 
                		 }
                		 else if(i == 7 || i == 10 || i == 13) {
                			 CheckBox previous_node = (CheckBox) gridPane.getChildren().get(i-1);
                			 button.setOnAction(t->{
                				 if(!member.getSelectionModel().isEmpty() && previous_node.isSelected()) {
                					 Alert alert = getAlert(AlertType.CONFIRMATION, "Done.", ButtonType.OK, "Confirmation");
                					 button.setDisable(true);        				 
                				 }
                				 else {
                					 Alert alert = getAlert(AlertType.ERROR, "Invalid input.", ButtonType.OK, "Error");
                				 }
                			 });
                		 }
                	 }
                 }
        	 }
        	 //Check whether group is already closed, if yes disable nodes
        	 else if(!((TextField) hbox.getChildren().get(1)).getText().isEmpty()) {
        		 for(int i = 0; i < gridPane.getChildren().size(); ++i) {
        			 gridPane.getChildren().get(i).setDisable(true);
        			 if(i >= gridPane.getChildren().size()-3)
        				 gridPane.getChildren().get(i).setDisable(false);
        		 }
        	 }
         });
         //------------------------------------------Blacklist-------------------------------------------------------
         blacklistTabContent(tab3, w, h);
         Scene scene = new Scene(tabPane, w, h);
         NewWindow(newWindow, scene, mainStage, "Edit");
    }
 
    @SuppressWarnings({ "unused", "unchecked" })
	@FXML
    void Claim_Click(ActionEvent event) {
    	double width = screen.getWidth()*0.2;
    	double height = screen.getHeight()*0.5;
    	Stage mainStage = (Stage) scrollPane.getScene().getWindow();
    	GridPane pane = ClaimMainScene(width, height);
    	Scene mainScene = new Scene(pane, width, height);
    	Stage newWindow = new Stage();
    	NewWindow(newWindow, mainScene, mainStage, "Claim");
    	
    	((Button)pane.getChildren().get(4)).setOnAction(e->{
    		if(((ComboBox<String>)pane.getChildren().get(1)).getSelectionModel().isEmpty() ||
    				((TextField)pane.getChildren().get(2)).getText().isEmpty() ||
    				((TextArea)pane.getChildren().get(3)).getText().isEmpty()) {
    			Alert alert = getAlert(AlertType.ERROR, "Can't be empty.", ButtonType.OK, "Error");
    		}
    		else {
    			Alert alert = getAlert(AlertType.CONFIRMATION, "Claim submitted.", ButtonType.OK, "Confirmation");
    			newWindow.close();
    		}
    	});
    }
    
    @FXML
    void Evaluation_Click(ActionEvent event) {
    	double w = screen.getWidth()*0.5;
        double h = screen.getHeight()*0.7;
        //Get the main scene size
        Stage mainStage = (Stage) scrollPane.getScene().getWindow();
        Stage newWindow = new Stage(); //New mini window
        TabPane tabPane = new TabPane();
        Tab tab = new Tab("Information");
        tabPane.getTabs().add(tab);
        tab.setClosable(false);
    	//Import css file
    	tabPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	EvaluationTabContent(tab, w, h, newWindow);
        Scene scene = new Scene(tabPane, w, h);
        NewWindow(newWindow, scene, mainStage, "Evaluation");
    }
    
    //----------------------------Claim main scene------------------------------
    private GridPane ClaimMainScene(double w, double h) {
    	GridPane pane = new GridPane();
    	pane.setVgap(h*0.05);
    	pane.setPadding(new Insets(w*0.05,h*0.05,w*0.05,h*0.05));
    	pane.getColumnConstraints().add(new ColumnConstraints(w*0.9));
    	//Title
    	Label title = new Label("Fill up your claim");
    	title.setPrefSize(w*0.9, h*0.1);
    	title.setAlignment(Pos.CENTER);
    	title.setStyle("-fx-background-color: #FFA07A;");
    	pane.getRowConstraints().add(new RowConstraints(h*0.1));
    	pane.add(title, 0, 0);
    	//Claim types
    	ComboBox<String> claimType = new ComboBox<>();
    	claimType.getItems().addAll("Complaint", "Compliment");
    	claimType.setPromptText("Claim types");
    	claimType.setPrefWidth(w*0.5);
    	GridPane.setHalignment(claimType, HPos.CENTER);
    	pane.add(claimType, 0, 1);
    	//Target
    	TextField target = new TextField();
    	target.setFocusTraversable(false);
    	target.setPromptText("Member ID");
    	GridPane.setHalignment(target, HPos.CENTER);
    	pane.add(target, 0, 2);
    	//Reason 
    	TextArea reason = new TextArea();
    	reason.setPromptText("Reason");
    	reason.setFocusTraversable(false);
    	pane.add(reason, 0, 3);
    	//Button
    	Button submit = new Button("Submit");
    	GridPane.setHalignment(submit, HPos.CENTER);
    	pane.add(submit, 0, 4);
    	
    	return pane;
    }
    //**************************************************************************
    
    //----------------------------Group main scene------------------------------
    private StackPane groupMainScene(double w, double h) {
    	boolean group_status = false;
        StackPane subScene = new StackPane();
        subScene.setPadding(new Insets(h*0.1,w*0.05,h*0.05,w*0.05));
        //Create label
        Button lb = new Button();
        // Display group status
        if(!group_status) {
            lb.setFocusTraversable(false);
            lb.setDisable(true);
            lb.setOpacity(1);
            lb.setText("You don't have a group."); 
            lb.setStyle("-fx-background-color: #F0F8FF;"
            		+ "-fx-border-color: #F0F8FF;");
        }
        else {
        	lb.setText("Move to group page.");
        	lb.setFocusTraversable(false);
        }
        HBox hbox = new HBox();
        hbox.setPrefSize(w, h*0.4);
        //Move to target group button
        Button go = new Button("Go");
        getSetting(go, w*0.2, h*0.08, 0, 0);
        //Serach for group id
        TextField groupId = new TextField();
        groupId.setPromptText("Group ID");
        getSetting(groupId, w*0.4, h*0.08, 0, 0);
        //Create a button
        Button createButton = new Button();
        createButton.setText("Create");
        createButton.setFocusTraversable(false);
        
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().addAll(createButton, go,groupId);
        
        //Appeal button
        Button appeal = new Button("Appeal");
        appeal.setFocusTraversable(false);
        
        //set the position
        StackPane.setAlignment(appeal, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(hbox, Pos.TOP_CENTER);
        subScene.getChildren().addAll(lb, hbox, appeal);
        return subScene;
    }
    
    //Appeal scene
    private void groupAppealScene(StackPane pane, double w, double h) {
    	pane.setPadding(new Insets(h*0.05, w*0.1, h*0.05, w*0.1));
    	//Title
    	Label reason = new Label("Give a reason");
    	//TextField
    	TextArea reasonArea = new TextArea();
    	reasonArea.setMaxSize(w*0.8, h*0.7);
    	reasonArea.setFocusTraversable(false);
    	//Submit button
    	Button submit = new Button("Submit");
    	
    	StackPane.setAlignment(submit, Pos.BOTTOM_CENTER);
    	StackPane.setAlignment(reason, Pos.TOP_CENTER);
    	StackPane.setAlignment(reasonArea, Pos.CENTER);
    	pane.getChildren().addAll(reason, reasonArea, submit);
    }
    
    private Pane groupCreateScene(double w, double h) {
    	Pane pane = new Pane();
    	//Group title
    	TextField group_title =  new TextField();
    	getSetting(group_title, 300, 0, 30, 30);
    	group_title.setPadding(new Insets(10,10,10,10));
    	group_title.setPromptText("Group name");
    	group_title.setStyle("-fx-border-color:#DEB887;"
    						+ "-fx-border-width: 5;");
    	//Group description
    	TextField group_description =  new TextField();
    	getSetting(group_description, 300, 0, 30, 100);
    	group_description.setPadding(new Insets(10,10,300,10));
    	group_description.setPromptText("Group Description");
    	group_description.setStyle("-fx-border-color:#DEB887;"      						
    							+ "-fx-border-width: 5;");
    	//Friends List
    	ScrollPane friend_list = new ScrollPane();
    	getSetting(friend_list, 350, 500, 350, 30);
    	friend_list.setHbarPolicy(ScrollBarPolicy.NEVER);
    	friend_list.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    	friend_list.setStyle("-fx-border-color:#DEB887;"
    						+ "-fx-border-width: 5;");
    	//Grid Pane
    	GridPane friend_grid = new GridPane();
    	friend_grid.setVgap(10);
    	friend_grid.setHgap(120);
    	friend_grid.setPadding(new Insets(20,20,10,0));
    	ColumnConstraints column1 = new ColumnConstraints(100);
    	ColumnConstraints column2 = new ColumnConstraints(70);
    	friend_grid.getColumnConstraints().addAll(column1, column2);
    	//Show all avaliable friends
    	for(int i = 0; i < 30; ++i) {
        	//Label names
        	Label l1 = new Label("Unknown");
        	l1.setFont(new Font(16));
    		GridPane.setHalignment(l1, HPos.RIGHT);
        	friend_grid.add(l1, 0, i);
        	//set up invite button
        	Button b1 = new Button("Invite");
        	b1.setFocusTraversable(false);
        	getStyle(b1);
        	GridPane.setHalignment(b1, HPos.LEFT);
        	friend_grid.add(b1, 1, i);
    	}
    	//Confirmed button
    	Button confirm_button = new Button("Confirm");
    	getSetting(confirm_button, 100, 30, 300, 550);
    	getStyle(confirm_button);
    	friend_list.setContent(friend_grid);
    	pane.getChildren().addAll(group_title, group_description, friend_list, confirm_button);
    	return pane;
    }
    //----------------------------Evaluation Tab Pane---------------------------
    //Set up evaluation tab content
    private void EvaluationTabContent(Tab tab, double w, double h, Stage stage) {
    	BorderPane EvaluationPane = new BorderPane();
    	EvaluationPane.setPadding(new Insets(20,20,20,20));
    	//***********************Top**********************
    	VBox vbox = new VBox();
    	//Instruction
    	Label instruction = new Label("SU wants you to evaluate this group, please finish on time. Otherwise, you will get a penalty.");
    	instruction.setStyle("-fx-background-color: #FF7F50;");
    	//Project name
    	Label projectTitle = new Label("Unknown");
    	projectTitle.setFont(Font.font(40));
    	getSetting(projectTitle, w, h*0.1, 0, 0);
    	//***********************Left*********************
    	GridPane gridPane = new GridPane();
    	gridPane.setPadding(new Insets(h*0.05, 0, 0, 0));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w*0.2));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w*0.2));
    	gridPane.setVgap(h*0.05);
    	final String[] group_tags = {"Total members:", "Total warnings:", "Total praises:", "Total meetings:", "Total kicks:"};
    	for(int i = 0; i < group_tags.length; ++i) {
    		//Label
    		Label tag = new Label(group_tags[i]);
    		tag.setPrefWidth(w*0.15);
    		tag.setFont(Font.font(20));
    		GridPane.setHalignment(tag, HPos.CENTER);
    		gridPane.add(tag, 0, i);
    		//Value
    		Label value = new Label("Unknown" + i);
    		value.setPrefWidth(w*0.15);
    		tag.setFont(Font.font(20));
    		GridPane.setHalignment(value, HPos.CENTER);
    		gridPane.add(value, 1, i);
    	}
    	//**************************Center*************************
    	GridPane gridPane_2 = new GridPane();
    	gridPane_2.setPadding(new Insets(h*0.05, 0, 0, w*0.1));
    	gridPane_2.getColumnConstraints().add(new ColumnConstraints(w*0.25));
    	gridPane_2.getColumnConstraints().add(new ColumnConstraints(w*0.2));
    	gridPane_2.setVgap(h*0.05);
    	final String[] group_tags_2 = {"Choose name", "Meeting attendances:", "Meeting assignments:", "Warnings:", "Praises:"};
    	for(int i = 0; i < group_tags_2.length; ++i) {
    		if(i == 0) {
    			//Name label
    			Label name = new Label(group_tags_2[i]);
    			name.setPrefWidth(w*0.25);
    			name.setFont(Font.font(20));
    			GridPane.setHalignment(name, HPos.CENTER);
    			gridPane_2.add(name, 0, i);
    			//Combo names
    			ComboBox<String> combo = new ComboBox<>();
    			combo.setPromptText("Member");
    			combo.setPrefWidth(w*0.2);
    			for(int j = 0; j < 5; ++j) 
    				combo.getItems().add("Unknown"+ j);
    			GridPane.setHalignment(combo, HPos.CENTER);
    			gridPane_2.add(combo, 1, i);
    		}
    		else {
    			//Label
        		Label tag = new Label(group_tags_2[i]);
        		tag.setPrefWidth(w*0.25);
        		tag.setFont(Font.font(20));
        		GridPane.setHalignment(tag, HPos.CENTER);
        		gridPane_2.add(tag, 0, i);
        		//Value
        		Label value = new Label("Unknown" + i);
        		value.setPrefWidth(w*0.15);
        		tag.setFont(Font.font(20));
        		GridPane.setHalignment(value, HPos.CENTER);
        		gridPane_2.add(value, 1, i);
    		}
    	}
    	//****************************Bottom*************************
    	HBox eva_hbox = new HBox();
    	eva_hbox.setPrefWidth(w*0.3);
    	eva_hbox.setSpacing(20);
    	eva_hbox.setPadding(new Insets(0, w*0.1, h*0.1, w*0.1));
    	//Decrease or Increase ComboBox
    	ComboBox<String> evaluate = new ComboBox<>();
    	evaluate.setPromptText("Dec./Inc.");
    	evaluate.setPrefWidth(w*0.2);
    	evaluate.getItems().addAll("Increase", "Decrease");
    	//Score ComboBox
    	ComboBox<Integer> scores = new ComboBox<>();
    	scores.setPrefWidth(w*0.2);
    	scores.setPromptText("Scores");
    	scores.getItems().addAll(1,2,3,4,5);
    	//Confirm
    	Button confirm = new Button("Confirm");
    	confirm.setPrefWidth(w*0.2);
    	//Button event
    	confirm.setOnAction(e->{
    		if(evaluate.getSelectionModel().isEmpty() || scores.getSelectionModel().isEmpty()) {
    			@SuppressWarnings("unused")
				Alert alert = getAlert(AlertType.ERROR, "Invalid input.", ButtonType.OK, "Error");
    		}
    		else {
    			Alert alert = getAlert(AlertType.CONFIRMATION, "Evaluation completed.", ButtonType.OK, "Confirmation");
    			if(alert.getResult() == ButtonType.OK) {
    				stage.close();
    				Evaluation.setVisible(false);
    			}
    		}
    	});
    	//Set children
    	eva_hbox.getChildren().addAll(evaluate, scores, confirm);
    	eva_hbox.setAlignment(Pos.TOP_CENTER);
    	vbox.getChildren().addAll(instruction,projectTitle);
    	vbox.setAlignment(Pos.CENTER_LEFT);
    	EvaluationPane.setTop(vbox);
    	EvaluationPane.setLeft(gridPane);
    	EvaluationPane.setCenter(gridPane_2);
    	EvaluationPane.setBottom(eva_hbox);
    	tab.setContent(EvaluationPane);
    }
    //**************************************************************************
    //-------------------------------Edit---------------------------------------
    //set up group tab content
    private void groupTabContent(Tab tab, double w, double h) {
    	BorderPane groupPane = new BorderPane();
    	groupPane.setPadding(new Insets(20,20,20,20));
    	//*******************Top**********************
    	//Create hbox
    	HBox hbox = new HBox();
    	//Search Button
    	Button Search = new Button("Search");
    	getSetting(Search, w*0.15, h*0.05, 0, 0);
    	//Search Field
    	TextField searchField = new TextField();
    	searchField.setPromptText("Enter project ID");
    	getSetting(searchField, w*0.3, h*0.05, 0, 0);
    	//********************Center*******************
    	//GridPane
    	GridPane gridPane = new GridPane();
    	gridPane.setPadding(new Insets(h*0.1,w*0.25,h*0.05,w*0.25));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w*0.20));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w*0.20));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w*0.10));
    	gridPane.setVgap(h*0.05);
    	//Score reduction
    	final String[] tags = {"Choose member", "Choose score", "Group kick", "Close group", "System kick", "Group Evaluation"};
    	for(int i = 0; i < tags.length; ++i) {
    		//Choose the target member
    		Label target = new Label(tags[i]);
    		gridPane.getRowConstraints().add(new RowConstraints(h*0.05));
    		GridPane.setHalignment(target, HPos.CENTER);
    		GridPane.setValignment(target, VPos.CENTER);
    		gridPane.add(target, 0, i);
    		if(i == 0 || i == 1 || i == 5) {
    			//Choosing field
    			ComboBox<String> combo = new ComboBox<>();
    			combo.setPrefWidth(w*0.15);
    			if(i == 0) {
    				combo.setPromptText("Select one");
    				for(int j = 0; j < 5; ++j)
    					combo.getItems().add("Unknown" + j);    				
    			}
    			else if(i == 1) {
    				combo.setPromptText("Scores");
    				combo.getItems().addAll("1","2","3","4","5","6","7","8","9","10");    				
    			}
    			else {
    				combo.setPromptText("Pick OU");
    				for(int j = 0; j < 5; ++j)
    					combo.getItems().add("Unknown" + j); 
    			}
    	    	gridPane.getRowConstraints().add(new RowConstraints(h*0.05));
    	    	GridPane.setHalignment(combo, HPos.LEFT);
    	    	GridPane.setValignment(combo, VPos.CENTER);
    	    	gridPane.add(combo, 1, i);
    		}
    		else {
    			CheckBox checkBox = new CheckBox();
    	    	GridPane.setHalignment(checkBox, HPos.LEFT);
    	    	GridPane.setValignment(checkBox, VPos.CENTER);
    	    	gridPane.add(checkBox, 1, i);
    		}
    		if(i != 0) {
    			//Confirm button
    			Button confirm = new Button("Confirm");
    			GridPane.setHalignment(confirm, HPos.LEFT);
    			GridPane.setValignment(confirm, VPos.CENTER);
    			gridPane.add(confirm, 2, i);    			
    		}
	    	
    	}
    	
    	hbox.getChildren().addAll(Search, searchField);
    	hbox.setAlignment(Pos.CENTER);
    	groupPane.setTop(hbox);
    	groupPane.setCenter(gridPane);
    	tab.setContent(groupPane);
    }
    
    //Set up blacklist tab content
    private void blacklistTabContent(Tab tab, double w, double h) {
    	BorderPane blacklist = new BorderPane();
    	//Title
    	Label title = new Label("System blacklist");
    	getSetting(title, w, h*0.15, 0, 0);
    	title.setAlignment(Pos.CENTER);
    	title.setFont(Font.font(30));
    	//ScrollPane for list
    	ScrollPane scrollPane = new ScrollPane();
    	scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    	scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    	//GridPane for each member
    	GridPane gridPane = new GridPane();
    	gridPane.setPadding(new Insets(0, w*0.35, h*0.1, w*0.35));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w*0.28));
    	//Make a list
    	for(int i = 0; i < 15; ++i){
    		Label member = new Label("Unknown" + i);
    		gridPane.getRowConstraints().add(new RowConstraints(h*0.07));
    		GridPane.setHalignment(member, HPos.CENTER);
    		GridPane.setValignment(member, VPos.CENTER);
    		gridPane.add(member, 0, i);
    		
    		Separator separator = new Separator();
    		separator.setMinWidth(w*0.28);
    		GridPane.setHalignment(separator, HPos.CENTER);
    		GridPane.setValignment(separator, VPos.BOTTOM);
    		gridPane.add(separator, 0, i);
    	}
    	//Set children
    	scrollPane.setContent(gridPane);
    	blacklist.setTop(title);
    	blacklist.setCenter(scrollPane);
    	tab.setContent(blacklist);
    }
    
    //Set up general editing compoents
    private void generalTabContent(Tab tab, double w, double h) {
    	BorderPane pane = new BorderPane();
    	pane.setPadding(new Insets(h*0.05, w*0.05, h*0.15, w*0.05));
    	//*******************Top**********************
    	//Create hbox
    	HBox hbox = new HBox();
    	//Search Button
    	Button Search = new Button("Search");
    	getSetting(Search, w*0.15, h*0.05, 0, 0);
    	//Search Field
    	TextField searchField = new TextField();
    	searchField.setPromptText("Enter member ID");
    	getSetting(searchField, w*0.3, h*0.05, 0, 0);
    	//Get member information board
    	memberBoard(pane, w, h);
    	//Get member management board
    	memberManage(pane, w, h);
    	//Get new member registration board
    	newMemberBoard(pane, w, h);
    	//Set chidlren
    	hbox.setAlignment(Pos.TOP_CENTER);
    	hbox.getChildren().addAll(Search, searchField);
    	pane.setTop(hbox);
    	tab.setContent(pane);
    	//All event
    	generalTabEvent(pane);
    }
    
    @SuppressWarnings("unused")
	private void generalTabEvent(BorderPane pane) {
    	//Create random id and temporary password.
    	((Button)((HBox)pane.getBottom()).getChildren().get(0)).setOnAction(e->{
    		Random rand = new Random();
    		String id = "";
    		String password = "";
    		//Seven digits password
    		for(int i = 0; i < 7; ++i) {
    			int digit = rand.nextInt(10);
    			int character = rand.nextInt(94)+33;
    			id += Integer.toString(digit);
    			password += (char)character;
    		}
    	});
    }
    
    private void newMemberBoard(BorderPane pane, double w, double h) {
    	//HBox
    	HBox hbox = new HBox();
    	//Gather random password and random ID;
    	Button registration = new Button("Randomize");
    	//Email adress
    	TextField address = new TextField();
    	address.setPromptText("Email address");
    	
    	hbox.setAlignment(Pos.CENTER);
    	hbox.getChildren().addAll(registration, address);
    	pane.setBottom(hbox);
    }
    
    private void memberManage(BorderPane pane, double w, double h) {
    	//Frame
    	GridPane gridPane = new GridPane();
    	gridPane.setStyle("-fx-background-color: #F5F5DC;");
    	gridPane.setPadding(new Insets(h*0.1, 0, 0, 0));
    	gridPane.setVgap(h*0.05);
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w*0.2));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w*0.2));
    	final String[] tags = {"+ Score", "- Score", "System kick", "Member revise"};
    	for(int i = 0; i < tags.length; ++i) {
    		//Tag
    		Label tag = new Label(tags[i]);
    		GridPane.setHalignment(tag, HPos.CENTER);
    		gridPane.add(tag, 0, i);
    		if(i < 2) {
    			//score combobox
    			ComboBox<Integer> scores = new ComboBox<>();
    			scores.getItems().addAll(1,2,3,4,5,6,7,8,10);
    			GridPane.setHalignment(scores, HPos.CENTER);
    			gridPane.add(scores, 1, i);
    		}
    		else if(i < 3) {
    			//Check box
    			CheckBox checkBox = new CheckBox();
    			GridPane.setHalignment(checkBox, HPos.CENTER);
    			gridPane.add(checkBox, 1, i);
    		}
    		else{
    			//Textfield
    			Button memberName = new Button("Confirm");
    			GridPane.setHalignment(memberName, HPos.CENTER);
    			gridPane.add(memberName, 1, i);
    		}
    	}
    	pane.setCenter(gridPane);
    	BorderPane.setMargin(gridPane, new Insets(h*0.1, 0, 0, 0));
    }
    
    //Member information board
    private void memberBoard(BorderPane pane, double w, double h) {
    	//Frame
    	GridPane gridPane = new GridPane();
    	gridPane.setStyle("-fx-background-color: #F5F5DC;");
    	gridPane.setPadding(new Insets(h*0.1, 0, 0, 0));
    	gridPane.setVgap(h*0.05);
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w*0.2));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w*0.2));
    	final String[] tags = {"Name:", "ID:", "Position:", "Email:", "Password:"};
    	for(int i = 0; i < tags.length; ++i) {
    		//Tag
    		Label tag = new Label(tags[i]);
    		GridPane.setHalignment(tag, HPos.CENTER);
    		gridPane.add(tag, 0, i);
    		//Value
    		Label value = new Label("Unknown"+i);
    		value.setStyle("-fx-background-color: #00FFFF;");
    		GridPane.setHalignment(value, HPos.CENTER);
    		gridPane.add(value, 1, i);
    	}
    	pane.setLeft(gridPane);
    	BorderPane.setMargin(gridPane, new Insets(h*0.1, 0, 0, 0));
    }
    //**************************************************************************
    
    //-------------------------------Email--------------------------------------
    //set up all essential components in the email pane
    private Pane getEmailPane(double w, double h) {
        //Create new stage
        Pane pane = new Pane();
        pane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        pane.setPadding(new Insets(5,5,5,5));
        
        //Email list
        ScrollPane email_list = new ScrollPane();
        getSetting(email_list, w*0.25+3, h*0.9, 0, h*0.12+5);
    	email_list.setHbarPolicy(ScrollBarPolicy.NEVER);
    	email_list.setVbarPolicy(ScrollBarPolicy.NEVER);
    	
    	//Grid Pane
    	GridPane emails = new GridPane();
    	emails.getColumnConstraints().add(new ColumnConstraints(w*0.25));
    	//Email content
    	Pane content = emailContent("From", w, h);
    	
    	//Search button
    	Button search_button = new Button("Go");
    	getSetting(search_button, w*0.1, h*0.05, 0, 5);
    	getStyle(search_button);
    	
    	//Search field
    	TextField search = new TextField();
    	getSetting(search, w*0.8, h*0.05, w*0.1, 5);
    	search.setPromptText("Search");
    	getStyle(search);
    	
    	//Compose
    	Button compose = new Button("Compose");
    	getSetting(compose, w*0.1, h*0.05, w-w*0.1, 5);
    	getStyle(compose);  	

    	//Delete
    	Button delete = new Button("Delete");
    	getSetting(delete, w*0.1, h*0.05, 0, h*0.05+10);
    	getStyle(delete);

    	// Confirm
    	Button okay = new Button("Ok");
    	getSetting(okay, w*0.1, h*0.05, 0, h*0.05+10);
    	okay.setVisible(false);
    	okay.setStyle("-fx-background-radius: 20;"
    					+ "-fx-border-radius: 20;"
    					+ "-fx-border-color: #DC143C;");
		//Cancel
		Button cancel = new Button("Cancel");
		getSetting(cancel, w*0.1, h*0.05, w*0.1+5, h*0.05+10);
		cancel.setVisible(false);
		cancel.setStyle("-fx-background-radius: 20;"
				+ "-fx-border-radius: 20;"
				+ "-fx-border-color: #DC143C;");
    	
		//Vertical separator
		Separator ver_separator = new Separator();
		ver_separator.setOrientation(Orientation.VERTICAL);
		ver_separator.setMinHeight(h*0.9);
		ver_separator.setLayoutX(w*0.25);
		ver_separator.setLayoutY(h*0.12);
		ver_separator.setStyle("-fx-background-radius: 2;" +
							"-fx-background-color: #778899;");
		
    	//Set all children
    	email_list.setContent(emails);
    	pane.getChildren().addAll(email_list, search, content, search_button, compose, delete, okay, cancel, ver_separator);
    	return pane;
    }
    
    //List all existed emails and call email reply
    private void getEmailList(double w, double h, Stage mainStage, GridPane gridPane, Pane content, int start) {
    	//List all existed emails
    	for(int i = start; i < emailNum; ++i) {
    		//Hide content area
    		content.setVisible(false);
    		//Create button
    		Button email_label = new Button("Unknown"+i);
    		getSetting(email_label, gridPane.getColumnConstraints().get(0).getPrefWidth(), h*0.1, 0, 0);
    		email_label.setAlignment(Pos.CENTER);
    		email_label.setFont(new Font(16));
    		email_label.setStyle("-fx-background-color: white;"
    				+ "-fx-border-color: black;");

        	//Radio button
        	CheckBox delete_dot = new CheckBox("");
        	getSetting(delete_dot, gridPane.getColumnConstraints().get(0).getPrefWidth(), h*0.1, 0, 0);
        	delete_dot.setPadding(new Insets(4,4,4,4));
        	delete_dot.setVisible(false);
        	
        	//Set text to text fields
        	ObservableList<Node> children = ((GridPane) content.getChildren().get(0)).getChildren();
        	email_label.setOnAction(e->{
        		content.setVisible(true);
        		((TextField) children.get(1)).setText("Unknown");
        		((TextField) children.get(3)).setText("Unknown");
        		((TextArea) children.get(4)).setText("Unknown");
        	});
        	
        	//Reply
        	((Button)children.get(5)).setOnAction(e->{
        		emailReply(w*0.8, h*0.7, mainStage);
        	});
        	
        	//Hover effects
        	email_label.setOnMouseEntered(e->{
        		email_label.setStyle("-fx-background-color:#00CED1;"
        				+ "-fx-border-color:black;");
        	});
        	email_label.setOnMouseExited(c->{
        		email_label.setStyle("-fx-background-color: white;"
        				+ "-fx-border-color: black;");
        	});
        	     	
    		GridPane.setHalignment(email_label, HPos.CENTER);
        	gridPane.add(email_label, 0, i);
        	gridPane.add(delete_dot, 0, i);
    	}
    }
	
    //Delete emails
    private void deleteEmail(Pane pane, GridPane gridPane, Pane content) {
    	ArrayList<Integer> selectedNum = new ArrayList<Integer>();
		((Button) pane.getChildren().get(6)).setVisible(true);
		((Button) pane.getChildren().get(6)).setDisable(true);
		((Button) pane.getChildren().get(5)).setVisible(false);
		((Button) pane.getChildren().get(7)).setVisible(true);
		for (Node node : gridPane.getChildren()) {
			if (node instanceof CheckBox) {
				node.setVisible(true);
				((CheckBox) node).setSelected(false);
				//If no emails has been selected disable okay button, otherwise.
				((CheckBox) node).setOnMouseClicked(c->{
					if(((CheckBox) node).isSelected()) {
						selectedNum.add(GridPane.getRowIndex(node));
						((Button) pane.getChildren().get(6)).setDisable(false);		
					}
					else if (!((CheckBox) node).isSelected()){
						selectedNum.remove(GridPane.getRowIndex(node));
						if(selectedNum.size() == 0)
							((Button) pane.getChildren().get(6)).setDisable(true);
					}	
				});
			}
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		//Cancel event disable all checkboxes
		//////////////////////////////////////////////////////////////////////////////////////////////
		((Button) pane.getChildren().get(7)).setOnAction(c->{
			//Hide all radio button
    		for (Node node : gridPane.getChildren()) {
    			if (node instanceof CheckBox) {
    				node.setVisible(false);
    			}
    		}
    		((Button) pane.getChildren().get(6)).setVisible(false);
    		((Button) pane.getChildren().get(7)).setVisible(false);
			((Button) pane.getChildren().get(5)).setVisible(true);
		});
		//////////////////////////////////////////////////////////////////////////////////////////////
		//Confirm event, sort and remove emails
		//////////////////////////////////////////////////////////////////////////////////////////////
		((Button) pane.getChildren().get(6)).setOnAction(d->{  
			//Warning alart for deletion
			Alert alert = getAlert(AlertType.WARNING, "Delete all selected Emails?", ButtonType.YES, "Caution!");   			  		
			if(alert.getResult() == ButtonType.YES) {
				ObservableList<Node> children = gridPane.getChildren();	
				//Remove deleted emails
				int index = 0;
				for(int i = 0; i < emailNum; ++i) {
					if(!selectedNum.contains(i)) 
						index += 2;
					else {
						gridPane.getChildren().remove(index, index+2);
						content.setVisible(false);
					}
				}
				int current = 0;
				int rowIndex = 0;
				//Sort emails
				for(int i = 0; i < selectedNum.size(); ++i) {
					while(!selectedNum.contains(rowIndex) && current/2 == rowIndex) {
						++rowIndex;
						current += 2;
					}
					if(selectedNum.contains(rowIndex)) 
						++rowIndex;
					while(!selectedNum.contains(rowIndex) && current/2 != rowIndex && rowIndex < emailNum) {
						GridPane.setRowIndex(children.get(current), current/2);
						GridPane.setRowIndex(children.get(current+1), current/2);
						++rowIndex;
						current += 2;
					}
				}			
    			//Hide all radio button
        		for (Node node : gridPane.getChildren()) {
        			if (node instanceof CheckBox) 
        				node.setVisible(false);
        		}
        		((Button) pane.getChildren().get(6)).setVisible(false);
        		((Button) pane.getChildren().get(7)).setVisible(false);
        		((Button) pane.getChildren().get(5)).setVisible(true);
    			//Update existing emails
    			emailNum -= selectedNum.size();
			}
		});
    }
   
    //Email contents
    private Pane emailContent(String label, double w, double h) {
    	Pane content = new Pane();
    	getSetting(content, w*0.74, h*0.88, w*0.25+10, h*0.12+5);
    	content.setStyle("-fx-background-color:white;"
    					+ "-fx-border-color: black;");
    	
    	GridPane gridPane = new GridPane();
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w*0.74));
    
    	//Sender label
    	Label to = new Label("	" + label);
    	gridPane.getRowConstraints().add(new RowConstraints(h*0.1));
    	GridPane.setHalignment(to, HPos.LEFT);
    	gridPane.add(to, 0, 0);
    	
    	//Sender text field
    	TextField senderField = new TextField();
    	senderField.setStyle("-fx-background-color: #48D1CC;");
    	senderField.setAlignment(Pos.CENTER);
    	senderField.setMaxWidth(w*0.5);
    	senderField.setEditable(false);
    	senderField.setFocusTraversable(false);
    	GridPane.setHalignment(senderField, HPos.CENTER);
    	gridPane.add(senderField, 0, 0);
    	
    	//subject label
    	Label subject = new Label("	Subject");
    	gridPane.getRowConstraints().add(new RowConstraints(h*0.1));
    	GridPane.setHalignment(subject, HPos.LEFT);
    	gridPane.add(subject, 0, 1);

    	//subject text field
    	TextField subjectField = new TextField();
    	subjectField.setStyle("-fx-background-color: #48D1CC;");
    	subjectField.setAlignment(Pos.CENTER);
    	subjectField.setMaxWidth(w*0.5);
    	subjectField.setEditable(false);
    	subjectField.setFocusTraversable(false);
    	GridPane.setHalignment(subjectField, HPos.CENTER);
    	gridPane.add(subjectField, 0, 1);
    	
    	//content field
    	TextArea contentArea = new TextArea();
    	contentArea.setMaxHeight(h*0.55);
    	contentArea.setMaxWidth(w*0.7);
    	contentArea.setEditable(false);
    	gridPane.getRowConstraints().add(new RowConstraints(h*0.55));
    	GridPane.setHalignment(contentArea, HPos.CENTER);
    	gridPane.add(contentArea, 0, 2);
    	
    	//Reply
    	Button reply = new Button("Reply");
    	gridPane.getRowConstraints().add(new RowConstraints(h*0.1));
    	GridPane.setHalignment(reply, HPos.CENTER);
    	gridPane.add(reply, 0, 3);
 
    	content.getChildren().add(gridPane);
    	return content;
    }

    //Email compose scene
    private void composeScene(GridPane gridPane, Pane content, double w, double h, Stage s) {
    	Pane scene = emailContent("To", w*(1/0.73),h*(1/0.86));
    	getSetting(scene, w, h, 0, 0);
    	scene.setStyle(null);
    	//Adjustments
    	((TextField) ((GridPane) scene.getChildren().get(0)).getChildren().get(1)).setEditable(true);
    	((TextField) ((GridPane) scene.getChildren().get(0)).getChildren().get(3)).setEditable(true);
    	((TextArea) ((GridPane) scene.getChildren().get(0)).getChildren().get(4)).setEditable(true);
    	Button send = (Button) ((GridPane) scene.getChildren().get(0)).getChildren().get(5);
    	send.setText("Send");
   	 	//Send button event
    	send.setOnAction(e->{
  			//check whether object, subject, or content is empty.
    		if(!((TextArea) ((GridPane) scene.getChildren().get(0)).getChildren().get(4)).getText().isEmpty() &&
    				!((TextField) ((GridPane) scene.getChildren().get(0)).getChildren().get(1)).getText().isEmpty() &&
    				!((TextField) ((GridPane) scene.getChildren().get(0)).getChildren().get(3)).getText().isEmpty()) {
    			Alert alert = getAlert(AlertType.INFORMATION, "Email has been sent!", ButtonType.OK, "Confirmaton");			
				if(alert.getResult() == ButtonType.OK) {
					((Stage) scene.getScene().getWindow()).close(); 
					 //Add new email to the email list
		            ++emailNum;
		            getEmailList(w, h, s,gridPane, content, emailNum-1);
		            //set email to the top
		            emailMoveUp(gridPane);					
				}
    		}
    		else {
    			@SuppressWarnings("unused")
				Alert alert = getAlert(AlertType.ERROR, "Content can't be empty!", ButtonType.OK, "Error");
    		}
    	});
    	//Set new window
    	Scene secondScene = new Scene(scene, w, h);
        // New window (Stage)
        Stage newWindow = new Stage();
        //Set newWindow
        NewWindow(newWindow, secondScene, s, "Compose");    
    }

    //Set the new email to the top
    private void emailMoveUp(GridPane gridPane) {
    	ArrayList<Node> list = new ArrayList<>();
    	for(int i = 0; i <gridPane.getChildren().size(); i+=2) {
    		list.add(gridPane.getChildren().get(i));
    		list.add(gridPane.getChildren().get(i+1));
    	}
    	//Clear gridPane
    	gridPane.getChildren().clear();
    	//Sort
    	gridPane.add(list.get(list.size()-2), 0, 0);
    	gridPane.add(list.get(list.size()-1), 0, 0);
    	for(int i = 1; i < list.size()/2; ++i) {
    		gridPane.add(list.get(i*2-2), 0, i);
    		gridPane.add(list.get(i*2-1), 0, i);
    	}
    }
   
    //Email reply scene
    private void emailReply(double w, double h, Stage s) {
    	Pane pane = new Pane();
    	
    	//Object
    	Label object = new Label("To unknown");
    	getSetting(object, w*0.3, h*0.1,  w*0.1, 0);
    	
    	//Reply content
    	TextArea content = new TextArea();
    	getSetting(content, w*0.8, h*0.8, w*0.1, h*0.11);
    	
    	//send
    	Button send = new Button("Send");
    	getSetting(send, w*0.1, h*0.075, w*0.5-w*0.05, h*0.93);

    	//Set children
    	pane.getChildren().addAll(object, content, send);
    	//Set new window
    	Scene secondScene = new Scene(pane,w, h);
        // New window (Stage)
        Stage newWindow = new Stage();
        // set new window
        NewWindow(newWindow, secondScene, s, "Reply"); 
        //Send event
        emailSent(pane, send, w, h, newWindow);
    }
    
    //Emial send event 
    private void emailSent(Pane pane, Button b, double w, double h, Stage newWindow) {
    	 //Send button event
    	b.setOnAction(e->{
   			if(!((TextArea) pane.getChildren().get(1)).getText().isEmpty()) {
   				//Confirmation
   	    		Alert alert = getAlert(AlertType.INFORMATION, "Email has been sent!", ButtonType.OK, "Confirmaton");			
   				if(alert.getResult() == ButtonType.OK)
   					newWindow.close();
   			}
   			else {
   				@SuppressWarnings("unused")
				Alert alert = getAlert(AlertType.ERROR, "Content can't be empty!", ButtonType.OK, "Error");	
   			}
    	});
    }
    //**************************************************************************
    
    //--------------------------------Notification------------------------------
    //set up Notification pane
    private void getNotificationPane() {
    	GridPane gridPane = new GridPane();
    	getSetting(gridPane, NotificationPane.getPrefWidth()-15, NotificationPane.getPrefHeight(),
    			0, NotificationPane.getLayoutY());
    	gridPane.getColumnConstraints().add(new ColumnConstraints(285));
    	gridPane.setPadding(new Insets(5,5,5,5));
    	gridPane.setGridLinesVisible(true);
    	//Set up each row
    	for(int i = 0; i < 5; ++i) {
    		//Unread notification
    		Label unread = new Label("New");
    		unread.setId("unread");
    		unread.setPadding(new Insets(5,5,5,5));
    		GridPane.setHalignment(unread, HPos.LEFT);
    		GridPane.setValignment(unread, VPos.TOP);
    		gridPane.add(unread, 0, i);
    		//Content label
    		Label content = new Label("Unknown" + i);
    		content.setPadding(new Insets(5,5,5,5));
    		GridPane.setHalignment(content, HPos.CENTER);
    		GridPane.setValignment(content, VPos.CENTER);
    		gridPane.add(content, 0, i);
    		//Time 
    		String currentTime = getCurrentTime();
    		Label time = new Label(currentTime);
    		time.setPadding(new Insets(5,5,5,5));
    		GridPane.setHalignment(time, HPos.RIGHT);
    		GridPane.setValignment(time, VPos.BOTTOM);
    		gridPane.add(time, 0, i);
    		//Back pane
    		Pane pane = new Pane();
    		getSetting(pane, 285, 70, 0, 0);
    		pane.setId("back_pane");
    		gridPane.add(pane, 0, i);
    		//Delete event
    		pane.setOnMouseClicked(e->{
    			//Set up alert
    			ButtonType delete = new ButtonType("Delete", ButtonData.OK_DONE);
    			ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    			Alert alert = new Alert(AlertType.INFORMATION,
    			        content.getText(),
    			        delete,
    			        cancel);

    			alert.setTitle("Important");
    			alert.setHeaderText(null);
    			alert.setX(screen.getWidth()*0.7);
    			alert.setY(screen.getHeight()*0.2);
    			Optional<ButtonType> result = alert.showAndWait();
    			if (result.orElse(cancel) == delete)
    				gridPane.getChildren().removeAll(unread, content, time, pane);
    		});
    	}
    	((ScrollPane) NotificationPane.getChildren().get(1)).setContent(gridPane);
    }
    //**************************************************************************

    //-------------------------------Scene Transition---------------------------
    //Move to the login page
    @FXML
    private void Logout(ActionEvent event)throws IOException {
    	Parent login_page = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Stage login_scene = (Stage) Profile.getScene().getWindow();
    
        login_scene.setScene(new Scene(login_page));
        login_scene.setTitle("Login");
        login_scene.show();
    }
    
    //Move to the account page
    @FXML
    private void moveToAccount(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountPage.fxml"));
    	Parent account_page = loader.load();
    	
    	//AccountPageController account = loader.getController();
        Stage account_scene = (Stage) Profile.getScene().getWindow();
        account_scene.setScene(new Scene(account_page));
        account_scene.setTitle("Account page");
        account_scene.show();
    }
    //**************************************************************************

    
    //-------------------------------Message---------------------------------------------
    //Messaging scene
    private Pane messageChat(double w, double h) {
    	Pane result = new Pane();
    	//Import css file 
    	result.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	GridPane gridPane = new GridPane();
    	gridPane.setPadding(new Insets(0,15,0,15));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w-15));
    	
    	//Object
    	Label object = new Label("Unknown");
    	gridPane.getRowConstraints().add(new RowConstraints(h*0.1));
    	GridPane.setHalignment(object, HPos.CENTER);
    	GridPane.setValignment(object, VPos.CENTER);
    	gridPane.add(object, 0, 0);
    	
    	//Separator
    	Separator separator = new Separator();
    	separator.setMinWidth(w-15);
    	GridPane.setValignment(separator, VPos.BOTTOM);
    	gridPane.add(separator, 0, 0);
    	
    	//Display board
    	ScrollPane chat = new ScrollPane();
    	chat.setVbarPolicy(ScrollBarPolicy.NEVER);
    	chat.setHbarPolicy(ScrollBarPolicy.NEVER);
    	gridPane.getRowConstraints().add(new RowConstraints(h*0.6));
    	gridPane.add(chat, 0, 1);
    	
    	//Text area
    	TextField text = new TextField();
    	text.setMinWidth(w-15);
    	text.setMinHeight(h*0.2);
    	text.setAlignment(Pos.TOP_LEFT);
    	text.setId("messageTextField");
    	GridPane.setValignment(text, VPos.TOP);
    	gridPane.getRowConstraints().add(new RowConstraints(h*0.2));
    	gridPane.add(text, 0, 2);
    	
    	//Back Button
    	Button back = new Button("Back");
    	getSetting(back, w*0.2, h*0.05, 0, 0);
    	getStyle(back);
    	gridPane.getRowConstraints().add(new RowConstraints(h*0.1));
    	GridPane.setHalignment(back, HPos.LEFT);
    	GridPane.setValignment(back, VPos.CENTER);
    	gridPane.add(back, 0, 3);
    	
    	//Send button
    	Button send = new Button("Send");
    	getStyle(send);
    	getSetting(send, w*0.2, h*0.05, 0, 0);
    	GridPane.setHalignment(send, HPos.RIGHT);
    	GridPane.setValignment(send, VPos.CENTER);
    	gridPane.add(send, 0, 3);
    	
    	//Delete button
    	Button delete = new Button("Delete");
    	getStyle(delete);
    	getSetting(delete, w*0.2, h*0.05, 0, 0);
    	GridPane.setHalignment(delete, HPos.CENTER);
    	GridPane.setValignment(delete, VPos.CENTER);
    	gridPane.add(delete, 0, 3);

    	//dummy
    	HashMap<Integer, String> container = new HashMap<Integer, String>();

    	//Grid pane
    	GridPane messages = new GridPane();
    	messages.getColumnConstraints().add(new ColumnConstraints(w-45));
    	messages.setPadding(new Insets(0,0,0,15));
    	messages.setVgap(10);
    	//Always at the bottom of the scroll pane
    	messages.heightProperty().addListener(observable -> chat.setVvalue(1D));
    	
    	//Load previous messages
    	loadPreviousMessages(container, messages);
    	
    	//Send button event
    	MessageSendEvent(send, messages, text);
    	
    	//Delete event
    	delete.setOnAction(e->{
    		Alert alert = getAlert(AlertType.WARNING, "All contents will be deleted.", ButtonType.YES, "Caution!");
    		if(alert.getResult() == ButtonType.YES) {
    			messages.getRowConstraints().clear();
    			messages.getChildren().clear();
    		}
    	});

    	chat.setContent(messages);
    	result.getChildren().addAll(gridPane);
    	return result;
    }

    //Load previous messages
    private void loadPreviousMessages(HashMap<Integer, String> hashmap, GridPane messages) {
		for (Entry<Integer, String> m : hashmap.entrySet()) {
			// messages
			Label input = new Label(m.getValue());
			input.setWrapText(true);
			input.setAlignment(Pos.CENTER_RIGHT);
			// Get the width of string
			if (m.getKey() % 2 == 0) {
				input.setStyle("-fx-background-color: #7FFF00;");
				GridPane.setHalignment(input, HPos.RIGHT);
			} else {
				input.setStyle("-fx-background-color: #F0FFFF;");
				GridPane.setHalignment(input, HPos.LEFT);
			}
			// add row
        	messages.getRowConstraints().add(new RowConstraints());
        	messages.getRowConstraints().get(messages.getRowConstraints().size()-1).prefHeightProperty().bind(input.prefHeightProperty());
			messages.add(input, 0, messages.getRowConstraints().size() - 1);
		}
    }

    //Message send button event
    private void MessageSendEvent(Button send, GridPane messages, TextField text) {
    	send.setOnAction(e->{
    		// messages
    		Label input = new Label(text.getText());
    		input.setStyle("-fx-background-color: #7FFF00;");
    		input.setWrapText(true);
    		input.setAlignment(Pos.CENTER_RIGHT);
    		if(!text.getText().isEmpty()) {
            	text.clear();
            	//add row
            	messages.getRowConstraints().add(new RowConstraints());
            	//Resize the height of row.
            	messages.getRowConstraints().get(messages.getRowConstraints().size()-1).prefHeightProperty().bind(input.prefHeightProperty());
            	//set label to the right
            	GridPane.setHalignment(input, HPos.RIGHT);
            	messages.add(input, 0, messages.getRowConstraints().size()-1);
    		}
    	});
    }
    
    //Set up all essential components
    private Pane messageList(double w, double h) {
    	Pane result = new Pane();
    	result.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	ScrollPane list = new ScrollPane();
    	getSetting(list, w, h*0.8, 5, 50);
    	list.setVbarPolicy(ScrollBarPolicy.NEVER);
    	list.setHbarPolicy(ScrollBarPolicy.NEVER);
    	
    	//Delete
    	Button delete = new Button("Delete");
    	getSetting(delete, w*0.15, 30, 5, 10);
    	delete.setFont(Font.font(10));
    	getStyle(delete);
    	
    	//okay
    	Button okay = new Button("Ok");
    	getSetting(okay, w*0.15, 30, 5, 10);
    	okay.setFont(Font.font(10));
    	getStyle(okay);
    	okay.setVisible(false);
    	
    	//Text
    	Button text = new Button("Text");
    	getSetting(text, w*0.15, 30, w*0.15+5, 10);
    	text.setFont(Font.font(10));
    	getStyle(text);
    	
    	//Search bar
    	TextField search = new TextField();
    	getSetting(search, w*0.70, 30, w*0.3+5, 10);
    	search.setFont(Font.font(10));
    	getStyle(search);
    	
    	
    	//cancel
    	Button cancel = new Button("Cancel");
    	getSetting(cancel, w*0.15, 30, w*0.15+5, 10);
    	cancel.setFont(Font.font(10));
    	getStyle(cancel);
    	cancel.setVisible(false);
    	
    	//Grid pane
    	GridPane contacter = new GridPane();
    	ColumnConstraints column = new ColumnConstraints(w-2);
    	contacter.getColumnConstraints().add(column);
    	
    	list.setContent(contacter);
    	result.getChildren().addAll(list,delete,text, okay, cancel, search);
    	return result;
    }
    
    //Find the member and message to 
    private void firstMessage(Scene scene, Pane chatList, Pane messageChat, TextField search, GridPane gridPane) {
    	if(!search.getText().isEmpty())
    		getAlert(AlertType.WARNING, "Wrong input.", ButtonType.OK, "Caution!");
    	else {
    		messageNum += 1;
    		getChatList(scene, chatList, messageChat, gridPane, messageNum-1);
    		messageMoveUp(gridPane);
    	}
    }
  
    //Delete Events
    private void deleteMessage(Pane Chat, GridPane gridPane) {
    	ArrayList<Integer> selectedNum = new ArrayList<Integer>();
		((Button) Chat.getChildren().get(1)).setVisible(false); //delete button
		((Button) Chat.getChildren().get(2)).setVisible(false); //text button
		((Button) Chat.getChildren().get(3)).setVisible(true);  //okay button
		((Button) Chat.getChildren().get(3)).setDisable(true);  //okay button
		((Button) Chat.getChildren().get(4)).setVisible(true);  //cancel button
		
		for (Node node : gridPane.getChildren()) {
			if (node instanceof CheckBox) {
				node.setVisible(true);
				((CheckBox) node).setSelected(false);
				//If no emails has been selected disable okay button, otherwise.
				((CheckBox) node).setOnMouseClicked(c->{
					if(((CheckBox) node).isSelected()) {
						selectedNum.add(GridPane.getRowIndex(node));
						((Button) Chat.getChildren().get(3)).setDisable(false);		
					}
					else if (!((CheckBox) node).isSelected()){
						selectedNum.remove(GridPane.getRowIndex(node));
						if(selectedNum.size() == 0)
							((Button) Chat.getChildren().get(3)).setDisable(true);
					}	
				});
			}
		}
		//Cancel deletion
		((Button) Chat.getChildren().get(4)).setOnAction(c->{
			//Hide all radio button
    		for (Node node : gridPane.getChildren()) {
    			if (node instanceof CheckBox)
    				node.setVisible(false);
    		}
    		((Button) Chat.getChildren().get(1)).setVisible(true);
    		((Button) Chat.getChildren().get(2)).setVisible(true);
    		((Button) Chat.getChildren().get(3)).setVisible(false);
    		((Button) Chat.getChildren().get(4)).setVisible(false);
		});
		
		((Button) Chat.getChildren().get(3)).setOnAction(q->{
			Alert alert = getAlert(AlertType.WARNING, "Delete all selected messages?", ButtonType.YES, "Caution!");   			
			if(alert.getResult() == ButtonType.YES) {
				ObservableList<Node> children = gridPane.getChildren();	
				//Remove deleted emails
				int index = 0;
				for(int i = 0; i < messageNum; ++i) {
					if(!selectedNum.contains(i)) 
						index += 2;
					else {
						gridPane.getChildren().remove(index, index+2);
					}
				}
				int current = 0;
				int rowIndex = 0;
				//Sort emails
				for(int i = 0; i < selectedNum.size(); ++i) {
					while(!selectedNum.contains(rowIndex) && current/2 == rowIndex) {
						++rowIndex;
						current += 2;
					}
					if(selectedNum.contains(rowIndex)) 
						++rowIndex;
					while(!selectedNum.contains(rowIndex) && current/2 != rowIndex && rowIndex < messageNum) {
						GridPane.setRowIndex(children.get(current), current/2);
						GridPane.setRowIndex(children.get(current+1), current/2);
						++rowIndex;
						current += 2;
					}
				}			
    			//Hide all radio button
        		for (Node node : gridPane.getChildren()) {
        			if (node instanceof CheckBox) 
        				node.setVisible(false);
        		}
        		((Button) Chat.getChildren().get(1)).setVisible(true);
        		((Button) Chat.getChildren().get(2)).setVisible(true);
        		((Button) Chat.getChildren().get(3)).setVisible(false);
        		((Button) Chat.getChildren().get(4)).setVisible(false);
    			//Update existing messages
    			messageNum -= selectedNum.size();
			}
		});
    }
    
    //Create each row information of chat list
    private void getChatList(Scene scene, Pane chatList, Pane messageChat, GridPane gridPane, int start) {
    	for(int i = start; i < messageNum; ++i) {
    		//Create button
    		Button label = new Button("Unknown"+i);
    		getSetting(label, screen.getWidth()/5, 50, 0, 0);
    		label.setAlignment(Pos.CENTER);
        	label.setFont(new Font(16));
        	label.setStyle("-fx-background-color: white;"
    				+ "-fx-border-color: black;");

        	//Radio button
        	CheckBox delete_dot = new CheckBox("");
        	getSetting(delete_dot, screen.getWidth()/5, 50, 0, 0);
        	delete_dot.setPadding(new Insets(10,10,10,10));
        	delete_dot.setVisible(false);
            
            //Change to the content scene
            label.setOnAction(e->{
            	((Label) ((GridPane) messageChat.getChildren().get(0)).getChildren().get(0)).setText("You");
            	scene.setRoot(messageChat);
            });
            //Back to the messagelist if BACK button clicked.
            ((Button) ((GridPane) messageChat.getChildren().get(0)).getChildren().get(4)).setOnAction(e->scene.setRoot(chatList));
            
        	//Hover effects
        	label.setOnMouseEntered(e->{
        		label.setStyle("-fx-background-color:#00CED1;"
        				+ "-fx-border-color:black;");
        	});
        	label.setOnMouseExited(c->{
            	label.setStyle("-fx-background-color: white;"
        				+ "-fx-border-color: black;");
        	});
        	     	
    		GridPane.setHalignment(label, HPos.LEFT);
        	gridPane.add(label, 0, i);
        	gridPane.add(delete_dot, 0, i);  
    	}
    }
    
    //Set lastest massage row to the top
    private void messageMoveUp(GridPane gridPane) {
    	ArrayList<Node> list = new ArrayList<>();
    	for(int i = 0; i <gridPane.getChildren().size(); i+=2) {
    		list.add(gridPane.getChildren().get(i));
    		list.add(gridPane.getChildren().get(i+1));
    	}
    	//Clear gridPane
    	gridPane.getChildren().clear();
    	//Sort
    	gridPane.add(list.get(list.size()-2), 0, 0);
    	gridPane.add(list.get(list.size()-1), 0, 0);
    	for(int i = 1; i < list.size()/2; ++i) {
    		gridPane.add(list.get(i*2-2), 0, i);
    		gridPane.add(list.get(i*2-1), 0, i);
    	}
    }
   
    @SuppressWarnings("unused")
	//**************************************************************************
    
    //New user evaluation by their recommender
    void recommenderEvaluation() {
        Stage mainScene = (Stage) scrollPane.getScene().getWindow();       
        double height = screen.getHeight()*0.2;
        double width = screen.getWidth()*0.2;     
        StackPane scene = new StackPane();
        Scene secondScene = new Scene(scene, width, height);
        // New window (Stage)
        Stage newWindow = new Stage();
        scene.setPadding(new Insets(height*0.1, width*0.2, height*0.1, width*0.2));
        //Title 
        Label title = new Label("Quick Evaluation");
        title.setStyle("-fx-background-color: #FF7F50;");
        //ComboBox for scores
        ComboBox<Integer> scores = new ComboBox<>();
        scores.setPrefWidth(width*0.4);
        scores.setPromptText("Enter scores");
        scores.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
        //Confirm
        Button confirm = new Button("Confirm");
        confirm.setPrefWidth(width*0.3);
        
        StackPane.setAlignment(confirm, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(scores, Pos.CENTER);
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        scene.getChildren().addAll(title, scores, confirm);
        
        //confirm button event
        confirm.setOnAction(e->{
        	if(scores.getSelectionModel().isEmpty()) {
        		Alert alert = getAlert(AlertType.ERROR, "Scores can't be empty.", ButtonType.OK, "Error");
        	}
        	else {
        		Alert alert = getAlert(AlertType.INFORMATION, "Submission completed.", ButtonType.OK, "Confirmation");
        		newWindow.close();
        	}
        });
        // set new window
        NewWindow(newWindow, secondScene, mainScene, "New user evaluation"); 
    }
    
    //General style   
    private void getStyle(Node b) {
    	b.setStyle("-fx-background-radius: 20;"
				+ "-fx-border-radius: 20;"
				+ "-fx-border-color: black;");
    }
    
    //Get a button,textfield, label, pane and etc...
    //Paraeters: n : the name of conponent
    //			 w : width, h : height, x : x-axis, y : y-axis
    private void getSetting(Node n, double w, double h, double x, double y) {
    	((Region) n).setPrefSize(w,h);
    	n.setLayoutX(x);
    	n.setLayoutY(y);
    	n.setFocusTraversable(false);
    }
    
    //Create new window
    //Parameters, newWindow: the target window
    //			  scene: the scene in the newWindow
    //			  mainStage: the parent window of newWindow
    //			  title: the name of newWindow
    private void NewWindow(Stage newWindow, Scene scene, Stage mainStage, String title) {
        newWindow.setTitle(title);
        newWindow.setScene(scene);
        newWindow.setResizable(false);
        
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(mainStage);
        
        //Set the window default position
        newWindow.show();
    }
    
    //Get alert
    private Alert getAlert(AlertType at, String content, ButtonType bt, String title) {
    	Alert alert = new Alert(at, content, bt);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.showAndWait();    	
    	return alert;
    }
   
    //Get the current time
    private String getCurrentTime() {
    	DateFormat dateFormat =  new SimpleDateFormat("dd-M-yyyy");
  		Calendar cal = Calendar.getInstance();
  		String currentDate = dateFormat.format(cal.getTime()).toString();
  		return currentDate;
    }
    
    //Image animation
    private void Images_Animation() throws InterruptedException {
        ArrayList<File> image_files = new ArrayList<File>(6);
        ArrayList<Image> images = new ArrayList<Image>(6);
        
        image_files.add(new File("@../../Images/Book_1.jpg"));
        image_files.add(new File("@../../Images/Book_2.jpg"));
        image_files.add(new File("@../../Images/Book_3.jpg"));
        image_files.add(new File("@../../Images/Book_4.jpg"));
        image_files.add(new File("@../../Images/Book_5.jpg"));
        image_files.add(new File(""));

        //Add image path to the image
        for(int i = 0; i < 6; ++i) {
        	images.add(new Image(image_files.get(i).toURI().toString()));
        }
        
        // Image Transition 
        Transition animation = new Transition() {
            {
                setCycleCount(Animation.INDEFINITE);
                setCycleDuration(Duration.millis(20000)); // total time for animation
            }
                        
            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction*(images.size()-1));
                Slipping_Image.setImage(images.get(index));
                switch(index) {
                case 0:
                	R1.setSelected(true);
                	R2.setSelected(false);
                	R3.setSelected(false);
                	R4.setSelected(false);
                	R5.setSelected(false);
                	break;
                case 1:
                	R2.setSelected(true);
                	R1.setSelected(false);
                	R3.setSelected(false);
                	R4.setSelected(false);
                	R5.setSelected(false);
                	break;
                case 2:
                	R3.setSelected(true);
                	R1.setSelected(false);
                	R2.setSelected(false);
                	R4.setSelected(false);
                	R5.setSelected(false);
                	break;
                case 3:
                	R4.setSelected(true);
                	R1.setSelected(false);
                	R2.setSelected(false);
                	R3.setSelected(false);
                	R5.setSelected(false);
                	break;
                case 4:
                	R5.setSelected(true);
                	R1.setSelected(false);
                	R2.setSelected(false);
                	R3.setSelected(false);
                	R4.setSelected(false);
                	break;
                }
                R1.setOnAction(e->{
                	jumpTo(Duration.ZERO);
                });
                R2.setOnAction(e->{
                	jumpTo(Duration.millis(4000));
                });
                R3.setOnAction(e->{
                	jumpTo(Duration.millis(8000));
                });
                R4.setOnAction(e->{
                	jumpTo(Duration.millis(12000));
                });
                R5.setOnAction(e->{
                	jumpTo(Duration.millis(16000));
                });
            }
        };
        animation.play();   
    }

}