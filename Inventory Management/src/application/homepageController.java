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
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
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
import javafx.scene.layout.StackPane;
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
        //--------------------------------------------
        scrollPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        //Adjust layout positions
        Profile.setLayoutX(anchorPane_child.getPrefWidth()-120);
        Profile.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Profile.setId("profile");
        //Notificatino button
        Notification.setLayoutX(anchorPane_child.getPrefWidth()-240);
        Notification.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Notification.setId("notification");
        //Notification pane
    	NotificationPane.setLayoutX(Notification.getLayoutX()-150);
    	NotificationPane.setStyle("-fx-background-color: #00FFFF;");
    	//Edit button
        Edit.setLayoutX(anchorPane_child.getPrefWidth()-360);
        Edit.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Edit.setId("edit");
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

    @FXML
    void Group_Click(ActionEvent event) {
    	//Group main scene--------------------------------------------------------
    	boolean group_status = false;
    	//Get the screen size
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();          
        //Get the main scene size
        Stage mainScene = (Stage) Profile.getScene().getWindow();
        //Create new stage
        StackPane subScene = new StackPane();
        subScene.setPadding(new Insets(10,10,10,10));
        double subScene_height = screen.getHeight()/3;
        double subScene_width = screen.getWidth()/5;
        //Create label
        Button lb = new Button();
        // Display group status
        if(!group_status) {
            lb.setFocusTraversable(false);
            lb.setDisable(true);
            lb.setOpacity(1);
            lb.setText("Currently no group avaliable!"); 
            lb.setStyle("-fx-background-color: #F0F8FF;"
            		+ "-fx-border-color: #F0F8FF;");
        }
        else {
        	lb.setText("Move to group page.");
        	lb.setFocusTraversable(false);
        }
        
        //Create a button
        Button createButton = new Button();
        createButton.setText("Create");
        createButton.setFocusTraversable(false);
        getStyle(createButton);
        
        //set the position
        StackPane.setAlignment(createButton, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(lb, Pos.CENTER);
        subScene.getChildren().addAll(createButton, lb);

        
        Scene secondScene = new Scene(subScene, subScene_width, subScene_height);
        // New window (Stage)
        Stage newWindow = new Stage();
        // set new window
        NewWindow(newWindow, secondScene, mainScene, "Group", screen.getWidth()/2, screen.getHeight()/4); 
        
        //------------------------------------------------------------------
        //Group information-------------------------------------------------
        createButton.setOnAction(e->{
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
        	
        	Scene thirdScene = new Scene(pane, 700, 600);
            // New window (Stage)
            Stage newWindow_2 = new Stage();
            // set new window
            NewWindow(newWindow_2, thirdScene, newWindow, "Informations", screen.getWidth()/2, screen.getHeight()/4); 
            
            //Confirmed button click event
            confirm_button.setOnAction(c->{
            	if(group_title.getText().isEmpty() || group_description.getText().isEmpty()) {
            		@SuppressWarnings("unused")
					Alert warning = getAlert(AlertType.WARNING, "Wrong inputs existed.", ButtonType.OK, "Caution!", newWindow_2);
            	}
            	else {
                	Alert alert = getAlert(AlertType.INFORMATION, "Please wait for the final decision", ButtonType.OK, "Grouop confirmation", newWindow_2);
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
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();    
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
    		composeScene(gridPane, content, w, h, newWindow);
    	});
        //Set email list
    	getEmailList(w, h, gridPane, content, 0);
        //Delete emails
        delete.setOnAction(e -> deleteEmail(emailPane, gridPane, content, newWindow));
        //set new window
        NewWindow(newWindow, secondScene, mainStage, "Email", screen.getWidth()/4, screen.getHeight()/4); 
}

    @FXML
    void Message_Click(ActionEvent event) {   
    	//Get the screen size
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();          
        //Get the main scene size
        Stage mainStage = (Stage) Profile.getScene().getWindow();     
        //Create new window
        Stage newWindow = new Stage();  
        //Create chat list
        Pane chatList = messageList(screen.getWidth()/5, screen.getHeight()/2);
        //create scene
        Scene MESSAGE_Scene = new Scene(chatList,screen.getWidth()/5, screen.getHeight()/2);
        //Create message board
        Pane messageChat = messageChat(screen.getWidth()/5, screen.getHeight()/2, newWindow);
        //Message list
        GridPane gridPane = (GridPane) ((ScrollPane) chatList.getChildren().get(0)).getContent();
        //Message board 
        GridPane messageBoard = (GridPane) messageChat.getChildren().get(0);
        //Show original list
        getChatList(MESSAGE_Scene, chatList, messageChat, gridPane, 0);
        //first time message with sb
        ((ButtonBase) chatList.getChildren().get(2)).setOnAction(e-> {
        	((Label) messageBoard.getChildren().get(1)).setText("You");
        	MESSAGE_Scene.setRoot(messageChat);
        	firstMessage(MESSAGE_Scene, chatList, messageChat, (TextField) chatList.getChildren().get(5), gridPane, newWindow);  
    	});
        //Delete messages
        ((Button) chatList.getChildren().get(1)).setOnAction(e-> deleteMessage(chatList, gridPane, newWindow));  
        //Set new window
        NewWindow(newWindow, MESSAGE_Scene, mainStage, "Message", screen.getWidth()/1.5, screen.getHeight()/2.5);
    }
    
    @FXML
    private void Notification_Click(ActionEvent event) {
    	getNotificationPane();
    	if(NotificationPane.isVisible()) 
    		NotificationPane.setVisible(false);
    	else
    		NotificationPane.setVisible(true);    		
    }
    
    @FXML
    private void Edit_Click(ActionEvent event) {
    	
    }
    
    //-------------------------------Email--------------------------------------
    //set up all essential components in the email pane
    private Pane getEmailPane(double w, double h) {
        //Create new stage
        Pane pane = new Pane();
        pane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        pane.setPadding(new Insets(5,5,5,5));
        //Email list
        ScrollPane email_list = new ScrollPane();
        getSetting(email_list, w*0.25+3, h*0.9, 0, h*0.1+15);
    	email_list.setHbarPolicy(ScrollBarPolicy.NEVER);
    	email_list.setVbarPolicy(ScrollBarPolicy.NEVER);
    	
    	//Grid Pane
    	GridPane emails = new GridPane();
    	emails.getColumnConstraints().add(new ColumnConstraints(w*0.25));
    	//Email content
    	Pane content = emailContent(w, h);
    	
    	//Search button
    	Button search_button = new Button("Go");
    	getSetting(search_button, w*0.1, h*0.05, 0, 5);
    	getStyle(search_button);
    	
    	//Search field
    	TextField search = new TextField();
    	getSetting(search, w*0.5, h*0.06, w*0.1, 5);
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
    	getSetting(okay, w*0.1, h*0.05, 0, h*0.05+5);
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
    	
    	//Set all children
    	email_list.setContent(emails);
    	pane.getChildren().addAll(email_list, search, content, search_button, compose, delete, okay, cancel);
    	return pane;
    }
    
    //List all existed emails
    private void getEmailList(double w, double h, GridPane gridPane, Pane content, int start) {
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
        		emailReply(screen.getWidth()/2, screen.getHeight()/1.5, (Stage) Profile.getScene().getWindow());
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
    private void deleteEmail(Pane pane, GridPane gridPane, Pane content, Stage stage) {
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
			Alert alert = getAlert(AlertType.WARNING, "Delete all selected Emails?", ButtonType.YES, "Caution!", stage);   			  		
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
    private Pane emailContent(double w, double h) {
    	Pane content = new Pane();
    	getSetting(content, w*0.74, h*0.88, w*0.25+10, h*0.12);
    	content.setStyle("-fx-background-color:white;"
    					+ "-fx-border-color: black;");
    	
    	GridPane gridPane = new GridPane();
    	gridPane.setPadding(new Insets(0,20,0,0));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w*0.74));
    
    	//Sender label
    	Label to = new Label("	Receiver");
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
    	Pane scene = emailContent(w,h);
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
  				//Confirmation
    		Alert alert = getAlert(AlertType.INFORMATION, "Email has been sent!", ButtonType.OK, "Confirmaton", s);			
				if(alert.getResult() == ButtonType.OK) {
					((Stage) scene.getScene().getWindow()).close(); 
					 //Add new email to the email list
		            ++emailNum;
		            getEmailList(w, h, gridPane, content, emailNum-1);
		            //set email to the top
		            emailMoveUp(gridPane);					
				}
    	});
    	//Set new window
    	Scene secondScene = new Scene(scene,w - w/4+10, h-70);
        // New window (Stage)
        Stage newWindow = new Stage();
        //Set newWindow
        NewWindow(newWindow, secondScene, s, "Compose", w-400, h-400);    
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
    	Pane scene = new Pane();
    	
    	//Object
    	Label object = new Label("To unknown");
    	getSetting(object, w/4, 40, 0, 0);
    	object.setPadding(new Insets(20,20,20,20));
    	
    	//Reply content
    	TextArea content = new TextArea();
    	getSetting(content, w-w/4, h/2, 10, 80);
    	
    	//send
    	Button send = new Button("Send");
    	getSetting(send, 80, 30, content.getPrefWidth()/2-40, content.getPrefHeight()+90);

    	//Set children
    	scene.getChildren().addAll(object, content, send);
    	//Set new window
    	Scene secondScene = new Scene(scene,w - w/4+10, h-70);
        // New window (Stage)
        Stage newWindow = new Stage();
        // set new window
        NewWindow(newWindow, secondScene, s, "Reply", w-400, h-400); 
        //Send event
        emailSent(send, w, h, newWindow);
    }
    
    //Emial send event 
    private void emailSent(Button b, double w, double h, Stage newWindow) {
    	 //Send button event
    	b.setOnAction(e->{
   			//Confirmation
    		Alert alert = getAlert(AlertType.INFORMATION, "Email has been sent!", ButtonType.OK, "Confirmaton", newWindow);			
			if(alert.getResult() == ButtonType.OK)
				newWindow.close();
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
    	gridPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
    		Label content = new Label("~~~~~~~~~~~~~~~~~~~" + i);
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
    		ContextMenu menu = new ContextMenu();
    		menu.getItems().addAll(new MenuItem("Delete"));
    		//Back pane
    		Pane pane = new Pane();
    		getSetting(pane, 285, 70, 0, 0);
    		pane.setId("back_pane");
    		gridPane.add(pane, 0, i);
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
    			alert.setX(screen.getWidth()/2);
    			alert.setY(100);
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
    	Parent account_page = FXMLLoader.load(getClass().getResource("AccountPage.fxml"));
        Stage account_scene = (Stage) Profile.getScene().getWindow();
        account_scene.setScene(new Scene(account_page));
        account_scene.setTitle("Account page");
        account_scene.show();
    }
    //**************************************************************************

    
    //-------------------------------Message---------------------------------------------
    //Messaging scene
    private Pane messageChat(double w, double h, Stage stage) {
    	Pane result = new Pane();
    	//Import css file 
    	result.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	GridPane gridPane = new GridPane();
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w+12));
    	
    	//Back Button
    	Button back = new Button("Back");
    	back.setFocusTraversable(false);
    	gridPane.getRowConstraints().add(new RowConstraints(h*0.1));
    	GridPane.setHalignment(back, HPos.LEFT);
    	GridPane.setValignment(back, VPos.CENTER);
    	gridPane.add(back, 0, 0);
    	
    	//Object
    	Label object = new Label("Unknown");
    	GridPane.setHalignment(object, HPos.CENTER);
    	GridPane.setValignment(object, VPos.CENTER);
    	gridPane.add(object, 0, 0);
    	
    	//Separator
    	Separator separator = new Separator();
    	separator.setMinWidth(screen.getWidth());
    	GridPane.setHalignment(separator, HPos.CENTER);
    	GridPane.setValignment(separator, VPos.BOTTOM);
    	gridPane.add(separator, 0, 0);
    	
    	//Display board
    	ScrollPane chat = new ScrollPane();
    	chat.setViewportBounds(null);
    	chat.setLayoutY(40);
    	chat.setVbarPolicy(ScrollBarPolicy.NEVER);
    	chat.setHbarPolicy(ScrollBarPolicy.NEVER);
    	gridPane.getRowConstraints().add(new RowConstraints(h*0.6));
    	gridPane.add(chat, 0, 1);
    	
    	//Text area
    	TextField text = new TextField();
    	getSetting(text, w, 100, 0, 0);
    	text.setMinWidth(w+12);
    	text.setMinHeight(h*0.2);
    	text.setAlignment(Pos.TOP_LEFT);
    	GridPane.setValignment(text, VPos.TOP);
    	gridPane.getRowConstraints().add(new RowConstraints(h*0.2));
    	gridPane.add(text, 0, 2);
    	
    	Button send = new Button("Send");
    	send.setFocusTraversable(false);
    	GridPane.setHalignment(send, HPos.RIGHT);
    	GridPane.setValignment(send, VPos.CENTER);
    	gridPane.getRowConstraints().add(new RowConstraints(h*0.1));
    	gridPane.add(send, 0, 3);
    	
    	Button delete = new Button("Delete");
    	delete.setFocusTraversable(false);
    	GridPane.setHalignment(delete, HPos.LEFT);
    	GridPane.setValignment(delete, VPos.CENTER);
    	gridPane.add(delete, 0, 3);
    	
    	//Grid pane
    	GridPane messages = new GridPane();
    	messages.setVgap(10);
    	messages.getColumnConstraints().add(new ColumnConstraints(w));
    	//Always at the bottom of the scroll pane
    	chat.vvalueProperty().bind(messages.heightProperty());
    	send.setOnAction(e->{
    		text.getText().length();
    		Label t = new Label(text.getText());
        	t.setWrapText(true);
    		t.setEllipsisString(null);
    		t.setPrefWidth(w/1.5);
        	t.setAlignment(Pos.CENTER_RIGHT);
        	//Get the widthe of string
        	Font font = text.getFont();
        	double fontSize = font.getSize();
        	int rowHeight = (int) (fontSize*text.getText().length()/(w/1.5));
        	text.clear();
        	//add row
        	RowConstraints row = new RowConstraints(30+rowHeight*20);
        	messages.getRowConstraints().add(row);
        	//set label to the right
        	GridPane.setHalignment(t, HPos.RIGHT);
        	messages.add(t, 0, messages.getRowConstraints().size()-1);
    	});
    	
    	//Delete event
    	delete.setOnAction(e->{
    		Alert alert = getAlert(AlertType.WARNING, "All contents will be deleted.", ButtonType.YES, "Caution!", stage);
    		if(alert.getResult() == ButtonType.YES) {
    			messages.getChildren().clear();
    		}
    	});
    	
    	chat.setContent(messages);
    	result.getChildren().addAll(gridPane);
    	return result;
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
    	getSetting(search, w*0.65, 30, w*0.3+5, 10);
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
    private void firstMessage(Scene scene, Pane chatList, Pane messageChat, TextField search, GridPane gridPane, Stage stage) {
    	if(!search.getText().isEmpty())
    		getAlert(AlertType.WARNING, "Wrong input.", ButtonType.OK, "Caution!", stage);
    	else {
    		messageNum += 1;
    		getChatList(scene, chatList, messageChat, gridPane, messageNum-1);
    		messageMoveUp(gridPane);
    	}
    }
  
    //Delete Events
    private void deleteMessage(Pane Chat, GridPane gridPane, Stage stage) {
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
			Alert alert = getAlert(AlertType.WARNING, "Delete all selected messages?", ButtonType.YES, "Caution!", stage);   			
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
            	((Label) ((GridPane) messageChat.getChildren().get(0)).getChildren().get(1)).setText("You");
            	scene.setRoot(messageChat);
            });
            //Back to the messagelist if BACK button clicked.
            ((Button) ((GridPane) messageChat.getChildren().get(0)).getChildren().get(0)).setOnAction(e->scene.setRoot(chatList));
            
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
    //**************************************************************************
    
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
    
    //Get alert
    private Alert getAlert(AlertType at, String content, ButtonType bt, String title, Stage stage) {
    	Alert alert = new Alert(at, content, bt);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.setX(stage.getX()+100);
    	alert.setY(stage.getY()+100);
    	alert.showAndWait();    	
    	return alert;
    }
    
    //Get the current time
    private String getCurrentTime() {
    	DateFormat dateFormat =  new SimpleDateFormat("dd-M-yyyy hh:mm");
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