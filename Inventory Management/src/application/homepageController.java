package application;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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
    
	// Number of existing emails.
	int emailNum = 5;
	// Number of existing messages
	int messageNum = 5;
    
    @FXML
    void initialize() throws IOException, InterruptedException {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();   
    	//Resize the background image
        Background.setLayoutX(screen.getWidth());
        Background.setLayoutY(screen.getHeight()); 
        //--------------------------------------------     
        Profile.setLayoutX(anchorPane_child.getPrefWidth()-120);
        //--------------------------------------------
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
            	Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("Group confirmation");
            	alert.setHeaderText(null);
            	alert.setContentText("Please wait for the final decision.");
            	alert.setX(newWindow_2.getX()+150);
            	alert.setY(newWindow_2.getY()+150);
            	alert.showAndWait();
            	
            	if (alert.getResult() == ButtonType.OK) {
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
        //Get the main scene size
        Stage mainScene = (Stage) Profile.getScene().getWindow();
        double default_w = screen.getWidth()/2;
        double default_h = screen.getHeight()/1.5;
        //Create new stage
        Pane main_scene = new Pane();
        main_scene.setPadding(new Insets(3,3,3,3));
        //Email list
        ScrollPane email_list = new ScrollPane();
        getSetting(email_list, default_w/4, default_h-70, 0, 80);
    	email_list.setHbarPolicy(ScrollBarPolicy.NEVER);
    	email_list.setVbarPolicy(ScrollBarPolicy.NEVER);
    	email_list.setFitToHeight(true);
    	email_list.setFitToWidth(true);
    	
    	//Grid Pane
    	GridPane emails = new GridPane();
    	ColumnConstraints column1 = new ColumnConstraints(email_list.getPrefWidth()-5);
    	emails.getColumnConstraints().add(column1);
    	//Email content
    	Pane content = emailContent(default_w, default_h);
    	//Create all existed emails
    	for(int i = 0; i < emailNum; ++i) {
    		//Hide content area
    		content.setVisible(false);
    		//Create button
    		Button l1 = new Button("Unknown"+i);
    		getSetting(l1, email_list.getPrefWidth(), 50, 0, 0);
    		l1.setAlignment(Pos.CENTER);
        	l1.setFont(new Font(16));
        	l1.setStyle("-fx-background-color: white;"
    				+ "-fx-border-color: black;");

        	//Radio button
        	CheckBox delete_dot = new CheckBox("");
        	getSetting(delete_dot, column1.getPrefWidth(), 50, 0, 0);
        	delete_dot.setPadding(new Insets(4,4,4,4));
        	delete_dot.setVisible(false);
        	
        	//Set text to text fields
        	ObservableList<Node> children = content.getChildren();
        	l1.setOnAction(e->{
        		content.setVisible(true);
        		((TextField) children.get(1)).setText("Unknown");
        		((TextField) children.get(3)).setText("Unknown");
        		((TextArea) children.get(4)).setText("Unknown");
        	});
        	
        	//Reply
        	((Button)children.get(5)).setOnAction(e->{
        		emailReply(default_w, default_h, mainScene);
        	});
        	
        	//Hover effects
        	l1.setOnMouseEntered(e->{
        		l1.setStyle("-fx-background-color:#00CED1;"
        				+ "-fx-border-color:black;");
        	});
        	l1.setOnMouseExited(c->{
            	l1.setStyle("-fx-background-color: white;"
        				+ "-fx-border-color: black;");
        	});
        	     	
    		GridPane.setHalignment(l1, HPos.CENTER);
        	RowConstraints row1 = new RowConstraints(50);
        	emails.getRowConstraints().add(row1);
        	emails.add(l1, 0, i);
        	emails.add(delete_dot, 0, i);
    	}
    	//Search button
    	Button search_button = new Button("Go");
    	getSetting(search_button, 50, 40, 0, 5);
    	getStyle(search_button);
    	
    	//Search field
    	TextField search = new TextField();
    	getSetting(search, default_w/4-50, 40, 50, 5);
    	search.setPromptText("Search");
    	getStyle(search);
    	
    	//Compose
    	Button compose = new Button("Compose");
    	getSetting(compose, 100, 40, default_w-100, 5);
    	getStyle(compose);  	
    	//Compose scene
    	compose.setOnAction(e->{
    		composeScene(default_w, default_h, mainScene);
    	});
		//////////////////////////////////////////////////////////////////////////////////////////////
		//Deletion Procedure
		//////////////////////////////////////////////////////////////////////////////////////////////
    	//Delete
    	Button delete = new Button("Delete");
    	getSetting(delete, 80, 30, 0, 47);
    	getStyle(delete);

    	// Confirm
    	Button okay = new Button("Ok");
    	getSetting(okay, 80, 30, 0, 47);
    	okay.setVisible(false);
    	okay.setStyle("-fx-background-radius: 20;"
    					+ "-fx-border-radius: 20;"
    					+ "-fx-border-color: #DC143C;");
		//Cancel
		Button cancel = new Button("Cancel");
		getSetting(cancel, 80, 30, 160, 47);
		cancel.setVisible(false);
		cancel.setStyle("-fx-background-radius: 20;"
				+ "-fx-border-radius: 20;"
				+ "-fx-border-color: #DC143C;");
		//////////////////////////////////////////////////////////////////////////////////////////////
		//Deletion event; record all selected numbers
		//////////////////////////////////////////////////////////////////////////////////////////////
    	delete.setOnAction(e->{
			ArrayList<Integer> selectedNum = new ArrayList<Integer>();
    		okay.setVisible(true);
    		okay.setDisable(true);
    		delete.setVisible(false);
    		cancel.setVisible(true);
    		for (Node node : emails.getChildren()) {
    			if (node instanceof CheckBox) {
    				node.setVisible(true);
    				((CheckBox) node).setSelected(false);
    				//If no emails has been selected disable okay button, otherwise.
    				((CheckBox) node).setOnMouseClicked(c->{
    					if(((CheckBox) node).isSelected()) {
    						selectedNum.add(GridPane.getRowIndex(node));
    						okay.setDisable(false);		
    					}
    					else if (!((CheckBox) node).isSelected()){
    						selectedNum.remove(GridPane.getRowIndex(node));
    						if(selectedNum.size() == 0)
    							okay.setDisable(true);
    					}	
    				});
    			}
    		}
    		//////////////////////////////////////////////////////////////////////////////////////////////
    		//Cancel event disable all checkboxes
    		//////////////////////////////////////////////////////////////////////////////////////////////
    		cancel.setOnAction(c->{
    			//Hide all radio button
        		for (Node node : emails.getChildren()) {
        			if (node instanceof CheckBox) {
        				node.setVisible(false);
        			}
        		}
    			okay.setVisible(false);
    			cancel.setVisible(false);
    			delete.setVisible(true);
    		});
    		//////////////////////////////////////////////////////////////////////////////////////////////
    		//Confirm event, sort and remove emails
    		//////////////////////////////////////////////////////////////////////////////////////////////
    		okay.setOnAction(d->{  
    			//Warning alart for deletion
    			Alert alert = new Alert(AlertType.WARNING, 
    									"Are you sure to delete all selected emails?",
    									ButtonType.NO,
    									ButtonType.YES);
    			alert.setTitle("Confirmation");
    			alert.setHeaderText(null);
    			alert.setX(screen.getWidth()/4+150);
    			alert.setY(screen.getHeight()/4+150);
    			alert.showAndWait();
        		
    			if(alert.getResult() == ButtonType.YES) {
    				ObservableList<Node> children = emails.getChildren();	
    				//Remove deleted emails
    				int index = 0;
    				for(int i = 0; i < emailNum; ++i) {
    					if(!selectedNum.contains(i)) 
    						index += 2;
    					else {
    						emails.getChildren().remove(index, index+2);
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
            		for (Node node : emails.getChildren()) {
            			if (node instanceof CheckBox) 
            				node.setVisible(false);
            		}
        			okay.setVisible(false);
        			cancel.setVisible(false);
        			delete.setVisible(true);
    			}
    			//Update existing emails
    			emailNum -= selectedNum.size();
    		});
    	});
    	
    	//Set all children
    	email_list.setContent(emails);
        main_scene.getChildren().addAll(email_list, search, content, search_button, compose, delete, okay, cancel);
        Scene secondScene = new Scene(main_scene, default_w, default_h);
        // New window (Stage)
        Stage newWindow = new Stage();
        // set new window
        NewWindow(newWindow, secondScene, mainScene, "Email", screen.getWidth()/4, screen.getHeight()/4); 
}

    @FXML
    void Message_Click(ActionEvent event) {   
    	//Get the screen size
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();          
        //Get the main scene size
        Stage mainScene = (Stage) Profile.getScene().getWindow();
        //Create chat list
        Pane chatList = messageList(screen.getWidth()/5, screen.getHeight()/2);
        //create scene
        Scene secondScene = new Scene(chatList,screen.getWidth()/5, screen.getHeight()/2);
        //Create message chat
        Pane messageChat = messageChat(screen.getWidth()/5, screen.getHeight()/2);
        GridPane dummy = (GridPane) ((ScrollPane) chatList.getChildren().get(0)).getContent();
        for (int i = 0 ; i < messageNum; ++i) {
        	((Button) dummy.getChildren().get(i*2)).setOnAction(e->{
        		((Label) messageChat.getChildren().get(0)).setText("Qichen");
        		secondScene.setRoot(messageChat);
        	});
        }
        ((Button) messageChat.getChildren().get(5)).setOnAction(r->{
        	secondScene.setRoot(chatList);
        });  
        
        //Create new window
        Stage newWindow = new Stage();
        //Set new window
        NewWindow(newWindow, secondScene, mainScene, "Message", screen.getWidth()/1.3, screen.getHeight()/2.5);
    }
    
	//Email contents
    Pane emailContent(double w, double h) {
    	Pane content = new Pane();
    	getSetting(content, w-w/4+10, h-70, w/4, 80);
    	content.setStyle("-fx-background-color:white;"
    					+ "-fx-border-color: black;");
    	
    	//Sender label
    	Label sender = new Label("Sender");
    	getSetting(sender, 100, 30, 0, 0);
    	sender.setPadding(new Insets(23,20,20,20));
    	
    	//Sender text field
    	TextField senderField = new TextField();
    	getSetting(senderField, content.getPrefWidth()-200, 30, 100, 17);
    	senderField.setEditable(false);
    	
    	//subject label
    	Label subject = new Label("Subject");
    	getSetting(subject, 100, 30, 0, 80);
    	subject.setPadding(new Insets(23,20,20,20));

    	//subject text field
    	TextField subjectField = new TextField();
    	getSetting(subjectField, content.getPrefWidth()-200, 30, 100, 100);
    	subjectField.setEditable(false);
    	
    	//content field
    	TextArea contentArea = new TextArea();
    	getSetting(contentArea, content.getPrefWidth()-50, content.getPrefHeight()/1.5, 20, 160);
    	contentArea.setPadding(new Insets(20,20,20,20));
    	contentArea.setEditable(false);
    	
    	//Reply
    	Button reply = new Button("Reply");
    	getSetting(reply, 80, 30, content.getPrefWidth()-110, content.getPrefHeight()-40);
    	
    	content.getChildren().addAll(sender, senderField, subject, subjectField, contentArea, reply);
    	return content;
    }

    //Email compose scene
    void composeScene(double w, double h, Stage s) {
    	Pane scene = emailContent(w,h);
    	getSetting(scene, w, h, 0, 0);
    	scene.setStyle(null);
    	//Adjustments
    	((TextField)scene.getChildren().get(1)).setEditable(true);
    	((TextField)scene.getChildren().get(3)).setEditable(true);
    	((TextArea)scene.getChildren().get(4)).setEditable(true);
    	Button send = (Button)scene.getChildren().get(5);
    	send.setText("Send");
    	
    	//Set new window
    	Scene secondScene = new Scene(scene,w - w/4+10, h-70);
        // New window (Stage)
        Stage newWindow = new Stage();
        //Set newWindow
        NewWindow(newWindow, secondScene, s, "Compose", w-400, h-400);    
        //Send button event
        emailSent(send, w, h, newWindow);
    }
    
    //Email reply scene
    void emailReply(double w, double h, Stage s) {
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
    void emailSent(Button b, double w, double h, Stage newWindow) {
    	 //Send button event
    	b.setOnAction(e->{
   			//Confirmation
			Alert alert = new Alert(AlertType.INFORMATION, 
									"Email has been sent!");
			alert.setTitle("Confirmation");
			alert.setHeaderText(null);
			alert.setX(w-150);
			alert.setY(h-150);
			alert.showAndWait();
			
			if(alert.getResult() == ButtonType.OK)
				newWindow.close();
    	});
    }
    
    //Conversation scene
    Pane messageChat(double w, double h) {
    	Pane result = new Pane();
    	ScrollPane chat = new ScrollPane();
    	chat.setPrefSize(w+12, h-160);
    	chat.setLayoutY(30);
    	chat.setVbarPolicy(ScrollBarPolicy.NEVER);
    	chat.setHbarPolicy(ScrollBarPolicy.NEVER);
    	
    	//Text 
    	TextField text = new TextField();
    	getSetting(text, w-70, 127, 0, h-120);
    	text.setAlignment(Pos.TOP_LEFT);
        	
    	//Send
    	Button send = new Button("Send");
    	getSetting(send, 60, 25, 2-60, h-20);
    	send.setPadding(new Insets(3,3,3,3));
    	
    	//Object
    	Label object = new Label("Unknown");
    	object.setPrefSize(70,20);
    	object.setLayoutX(w/2-35);
    	
    	//Delete all content
    	Button delete = new Button("Delete");
    	getSetting(delete, 60, 25, w-60, h-120);
    	delete.setPadding(new Insets(3,3,3,3));
   
    	//back
    	Button back = new Button("Back");
    	getSetting(back, 60, 25, 0, 0);
    	back.setFont(Font.font(11));
    	
    	//Grid pane
    	GridPane messages = new GridPane();
    	messages.setVgap(10);
    	ColumnConstraints column = new ColumnConstraints(w);
    	messages.getColumnConstraints().add(column);
    	//Always at the bottom of the scroll pane
    	chat.vvalueProperty().bind(messages.heightProperty());
    	send.setOnAction(e->{
    		text.getText().length();
    		Label t = new Label(text.getText());
        	t.setWrapText(true);
    		t.setEllipsisString(null);
    		t.setPrefWidth(w/1.5);
        	t.setWrapText(true);
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
    		Alert alert = new Alert(AlertType.WARNING,
    							"All content will be deleted.",
    							ButtonType.YES,
    							ButtonType.NO);
    		alert.setTitle("Confirmation");
    		alert.setHeaderText(null);
    		alert.setX(w*3);
    		alert.setY(h);
    		alert.showAndWait();
    		
    		if(alert.getResult() == ButtonType.YES) {
    			messages.getChildren().clear();
    		}
    	});
    	
    	chat.setContent(messages);
    	result.getChildren().addAll(object, chat, text, send, delete, back);
    	return result;
    }
    
    //A list of contacted people
    @FXML
    void Logout(ActionEvent event)throws IOException {
    	Parent login_page = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Stage login_scene = (Stage) Profile.getScene().getWindow();
    
        login_scene.setScene(new Scene(login_page));
        login_scene.setTitle("Login");
        login_scene.show();
    }
    
    @FXML
    void moveToAccount(ActionEvent event) throws IOException {
    	Parent account_page = FXMLLoader.load(getClass().getResource("AccountPage.fxml"));
        Stage account_scene = (Stage) Profile.getScene().getWindow();
    
        account_scene.setScene(new Scene(account_page));
        account_scene.setTitle("Account page");
        account_scene.show();
    }
    
    Pane messageList(double w, double h) {
    	Pane result = new Pane();
    	ScrollPane list = new ScrollPane();
    	getSetting(list, w, h-50, 5, 50);
    	list.setVbarPolicy(ScrollBarPolicy.NEVER);
    	list.setHbarPolicy(ScrollBarPolicy.NEVER);
    	
    	//Delete
    	Button delete = new Button("Delete");
    	getSetting(delete, 60, 30, 0, 10);
    	delete.setFont(Font.font(10));
    	getStyle(delete);
    	
    	//okay
    	Button okay = new Button("Ok");
    	getSetting(okay, 60, 30, 0, 10);
    	okay.setFont(Font.font(10));
    	getStyle(okay);
    	okay.setVisible(false);
    	
    	//Text
    	Button text = new Button("Text");
    	getSetting(text, 60, 30, 65, 10);
    	text.setFont(Font.font(10));
    	getStyle(text);
    	
    	//cancel
    	Button cancel = new Button("Cancel");
    	getSetting(cancel, 60, 30, 65, 10);
    	cancel.setFont(Font.font(10));
    	getStyle(cancel);
    	cancel.setVisible(false);
    	
    	//Grid pane
    	GridPane contacter = new GridPane();
    	ColumnConstraints column = new ColumnConstraints(w);
    	contacter.getColumnConstraints().add(column);
        
    	for(int i = 0; i < messageNum; ++i) {
    		//Create button
    		Button l1 = new Button("Unknown"+i);
    		getSetting(l1, w, 50, 0, 0);
    		l1.setAlignment(Pos.CENTER);
        	l1.setFont(new Font(16));
        	l1.setStyle("-fx-background-color: white;"
    				+ "-fx-border-color: black;");

        	//Radio button
        	CheckBox delete_dot = new CheckBox("");
        	getSetting(delete_dot, column.getPrefWidth(), 50, 0, 0);
        	delete_dot.setPadding(new Insets(10,10,10,10));
        	delete_dot.setVisible(false);
            
        	//Hover effects
        	l1.setOnMouseEntered(e->{
        		l1.setStyle("-fx-background-color:#00CED1;"
        				+ "-fx-border-color:black;");
        	});
        	l1.setOnMouseExited(c->{
            	l1.setStyle("-fx-background-color: white;"
        				+ "-fx-border-color: black;");
        	});
        	     	
    		GridPane.setHalignment(l1, HPos.LEFT);
        	RowConstraints row1 = new RowConstraints(50);
        	contacter.getRowConstraints().add(row1);
        	contacter.add(l1, 0, i);
        	contacter.add(delete_dot, 0, i);      	
    	}
    	
    	delete.setOnAction(e->{
    		ArrayList<Integer> selectedNum = new ArrayList<Integer>();
    		delete.setVisible(false);
    		okay.setVisible(true);
    		okay.setDisable(true);
    		text.setVisible(false);
    		cancel.setVisible(true);
    		
    		for (Node node : contacter.getChildren()) {
    			if (node instanceof CheckBox) {
    				node.setVisible(true);
    				((CheckBox) node).setSelected(false);
    				//If no emails has been selected disable okay button, otherwise.
    				((CheckBox) node).setOnMouseClicked(c->{
    					if(((CheckBox) node).isSelected()) {
    						selectedNum.add(GridPane.getRowIndex(node));
    						okay.setDisable(false);		
    					}
    					else if (!((CheckBox) node).isSelected()){
    						selectedNum.remove(GridPane.getRowIndex(node));
    						if(selectedNum.size() == 0)
    							okay.setDisable(true);
    					}	
    				});
    			}
    		}
    		//Cancel deletion
    		cancel.setOnAction(c->{
    			//Hide all radio button
        		for (Node node : contacter.getChildren()) {
        			if (node instanceof CheckBox)
        				node.setVisible(false);
        		}
        		delete.setVisible(true);
        		okay.setVisible(false);
        		text.setVisible(true);
        		cancel.setVisible(false);
    		});
    		
    		okay.setOnAction(q->{
    			//Warning alart for deletion
    			Alert alert = new Alert(AlertType.WARNING, 
    									"Delete all selected messages?",
    									ButtonType.NO,
    									ButtonType.YES);
    			alert.setTitle("Confirmation");
    			alert.setHeaderText(null);
    			alert.setX(w*3);
    			alert.setY(h);
    			alert.showAndWait();
    			
    			if(alert.getResult() == ButtonType.YES) {
    				ObservableList<Node> children = contacter.getChildren();	
    				//Remove deleted emails
    				int index = 0;
    				for(int i = 0; i < messageNum; ++i) {
    					if(!selectedNum.contains(i)) 
    						index += 2;
    					else {
    						contacter.getChildren().remove(index, index+2);
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
            		for (Node node : contacter.getChildren()) {
            			if (node instanceof CheckBox) 
            				node.setVisible(false);
            		}
        			okay.setVisible(false);
        			cancel.setVisible(false);
        			delete.setVisible(true);
        			text.setVisible(true);
    			}
    			//Update existing messages
    			messageNum -= selectedNum.size();
    		});
    	});
    	list.setContent(contacter);
    	result.getChildren().addAll(list,delete,text, okay, cancel);
    	return result;
    }
    
    //Find the member and message him at the first time
    void firstTimeText(Button b, double w, double h) {
    	
    }
    //General style
    void getStyle(Node b) {
    	b.setStyle("-fx-background-radius: 20;"
				+ "-fx-border-radius: 20;"
				+ "-fx-border-color: black;");
    }
    
    //Get a button,textfield, label, pane and etc...
    //Paraeters: n : the name of conponent
    //			 w : width, h : height, x : x-axis, y : y-axis
    void getSetting(Node n, double w, double h, double x, double y) {
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
    
    void Images_Animation() throws InterruptedException {
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