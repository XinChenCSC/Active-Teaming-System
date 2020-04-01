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

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

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
import javafx.scene.control.MenuItem;
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
        createButton.setStyle("-fx-background-radius: 20;"
        					+ "-fx-border-radius: 20;"
        					+ "-fx-border-color: black;");
        //set the position
        StackPane.setAlignment(createButton, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(lb, Pos.CENTER);
        subScene.getChildren().addAll(createButton, lb);

        
        Scene secondScene = new Scene(subScene, subScene_width, subScene_height);
        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Group");
        newWindow.setScene(secondScene);
        newWindow.setResizable(false);
        
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(mainScene);
        
        //Set the window default position
        newWindow.setX(screen.getWidth()/2);
        newWindow.setY(screen.getHeight()/4);
        newWindow.show();
        //------------------------------------------------------------------
        //Group information-------------------------------------------------
        createButton.setOnAction(e->{
        	Pane pane = new Pane();
        	//Group title
        	TextField group_title =  new TextField();
        	group_title.setPrefWidth(300);
        	group_title.setPadding(new Insets(10,10,10,10));
        	group_title.setLayoutX(30);
        	group_title.setLayoutY(30);
        	group_title.setPromptText("Group name");
        	group_title.setFocusTraversable(false);
        	group_title.setStyle("-fx-border-color:#DEB887;"
        						+ "-fx-border-width: 5;");
        	//Group description
        	TextField group_description =  new TextField();
        	group_description.setPrefWidth(300);
        	group_description.setPadding(new Insets(10,10,300,10));
        	group_description.setLayoutX(30);
        	group_description.setLayoutY(100);
        	group_description.setPromptText("Group Description");
        	group_description.setFocusTraversable(false);
        	group_description.setStyle("-fx-border-color:#DEB887;"      						
        							+ "-fx-border-width: 5;");
        	//Friends List
        	ScrollPane friend_list = new ScrollPane();
        	friend_list.setPrefHeight(500);
        	friend_list.setPrefWidth(350);
        	friend_list.setLayoutX(350);
        	friend_list.setLayoutY(30);
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
            	b1.setStyle("-fx-background-radius: 20;"
            			+ "-fx-border-radius: 20;"
            			+ "-fx-border-color: black;");
            	GridPane.setHalignment(b1, HPos.LEFT);
            	friend_grid.add(b1, 1, i);
        	}
        	//Confirmed button
        	Button confirm_button = new Button("Confirm");
        	confirm_button.setPrefHeight(30);
        	confirm_button.setPrefWidth(100);
        	confirm_button.setLayoutX(300);
        	confirm_button.setLayoutY(550);
        	confirm_button.setFocusTraversable(false);
        	confirm_button.setStyle("-fx-background-radius: 20;"
        						+ "-fx-border-radius:20;" 
        						+ "-fx-border-color: black;");
        	friend_list.setContent(friend_grid);
        	pane.getChildren().addAll(group_title, group_description, friend_list, confirm_button);
        	
        	Scene thirdScene = new Scene(pane, 700, 600);
            // New window (Stage)
            Stage newWindow_2 = new Stage();
            newWindow_2.setTitle("Informations");
            newWindow_2.setScene(thirdScene);
            newWindow_2.setResizable(false);
            
            // Specifies the modality for new window.
            newWindow_2.initModality(Modality.WINDOW_MODAL);

            // Specifies the owner Window (parent) for new window
            newWindow_2.initOwner(newWindow);
            
            //Set the window default position
            newWindow_2.setX(screen.getWidth()/2);
            newWindow_2.setY(screen.getHeight()/4);
            newWindow_2.show();
            
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
        email_list.setPrefSize(default_w/4, default_h-70);
        email_list.setLayoutY(80);
    	email_list.setHbarPolicy(ScrollBarPolicy.NEVER);
    	email_list.setVbarPolicy(ScrollBarPolicy.NEVER);
    	email_list.setFitToHeight(true);
    	email_list.setFitToWidth(true);
    	
    	//Grid Pane
    	GridPane emails = new GridPane();
    	//ColumnConstraints column0 = new ColumnConstraints(30);
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
    		l1.setFocusTraversable(false);
    		l1.setAlignment(Pos.CENTER);
        	l1.setFont(new Font(16));
        	l1.setPrefSize(email_list.getPrefWidth(), 50);
        	l1.setStyle("-fx-background-color: white;"
    				+ "-fx-border-color: black;");

        	//Radio button
        	CheckBox delete_dot = new CheckBox("");
        	delete_dot.setPrefSize(column1.getPercentWidth(), 50);
        	delete_dot.setPadding(new Insets(4,4,4,4));
        	delete_dot.setFocusTraversable(false);
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
    	search_button.setPrefSize(50, 40);
    	search_button.setLayoutY(5);
    	search_button.setFocusTraversable(false);
    	search_button.setStyle("-fx-background-radius:20;"
    			+ "-fx-border-radius:20;"
    			+ "-fx-border-color: black;");
    	//Search field
    	TextField search = new TextField();
    	search.setPromptText("Search");
    	search.setPrefSize(default_w/4-50, 40);
    	search.setLayoutY(5);
    	search.setLayoutX(50);
    	search.setFocusTraversable(false);
    	search.setStyle("-fx-background-radius:20;"
    			+ "-fx-border-radius:20;"
    			+ "-fx-border-color: black;");
    	//Compose
    	Button compose = new Button("Compose");
    	compose.setPrefSize(100, 40);
    	compose.setLayoutY(5);
    	compose.setLayoutX(default_w-100);
    	compose.setFocusTraversable(false);
    	compose.setStyle("-fx-background-radius:20;"
    			+ "-fx-border-radius:20;"
    			+ "-fx-border-color: black;");    	
    	//Compose scene
    	compose.setOnAction(e->{
    		composeScene(default_w, default_h, mainScene);
    	});
		//////////////////////////////////////////////////////////////////////////////////////////////
		//Deletion Procedure
		//////////////////////////////////////////////////////////////////////////////////////////////
    	//Delete
    	Button delete = new Button("Delete");
    	delete.setPrefSize(80, 30);
    	delete.setLayoutY(47);
    	delete.setFocusTraversable(false);
    	delete.setStyle("-fx-background-radius: 20;"
				+ "-fx-border-radius: 20;"
				+ "-fx-border-color: black;");

    	// Confirm
    	Button okay = new Button("Ok");
    	okay.setPrefSize(80, 30);
    	okay.setLayoutY(47);
    	okay.setFocusTraversable(false);
    	okay.setVisible(false);
    	okay.setStyle("-fx-background-radius: 20;"
    					+ "-fx-border-radius: 20;"
    					+ "-fx-border-color: #DC143C;");
		//Cancel
		Button cancel = new Button("Cancel");
		cancel.setPrefSize(80, 30);
		cancel.setLayoutY(47);
		cancel.setLayoutX(160);
		cancel.setFocusTraversable(false);
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
        newWindow.setTitle("Email");
        newWindow.setScene(secondScene);
        newWindow.setResizable(false);
        
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(mainScene);
        
        //Set the window default position
        newWindow.setX(screen.getWidth()/4);
        newWindow.setY(screen.getHeight()/4);
        newWindow.show();
}
 
	//Email contents
    Pane emailContent(double w, double h) {
    	Pane content = new Pane();
    	content.setPrefSize(w - w/4+10, h-70);
    	content.setLayoutX(w/4);
    	content.setLayoutY(80);
    	content.setStyle("-fx-background-color:white;"
    					+ "-fx-border-color: black;");
    	
    	//Sender label
    	Label sender = new Label("Sender");
    	sender.setPrefSize(100, 30);
    	sender.setPadding(new Insets(23,20,20,20));
    	
    	//Sender text field
    	TextField senderField = new TextField();
    	senderField.setPrefSize(content.getPrefWidth()-200, 30);
    	senderField.setLayoutY(17);
    	senderField.setLayoutX(100);
    	senderField.setFocusTraversable(false);
    	senderField.setEditable(false);
    	
    	//subject label
    	Label subject = new Label("Subject");
    	subject.setPrefSize(100, 30);
    	subject.setPadding(new Insets(23,20,20,20));
    	subject.setLayoutY(80);
    	//subject text field
    	TextField subjectField = new TextField();
    	subjectField.setPrefSize(content.getPrefWidth()-200, 30);
    	subjectField.setLayoutX(100);
    	subjectField.setLayoutY(100);
    	subjectField.setFocusTraversable(false);
    	subjectField.setEditable(false);
    	//content field
    	TextArea contentArea = new TextArea();
    	contentArea.setPrefSize(content.getPrefWidth()-50, content.getPrefHeight()/1.5);
    	contentArea.setLayoutY(160);
    	contentArea.setLayoutX(20);
    	contentArea.setPadding(new Insets(20,20,20,20));
    	contentArea.setFocusTraversable(false);
    	contentArea.setEditable(false);
    	//Reply
    	Button reply = new Button("Reply");
    	reply.setPrefSize(80, 30);
    	reply.setFocusTraversable(false);
    	reply.setLayoutX(content.getPrefWidth()-110);
    	reply.setLayoutY(content.getPrefHeight()-40);
    	
    	content.getChildren().addAll(sender, senderField, subject, subjectField, contentArea, reply);
    	return content;
    }

    //Compose scene
    void composeScene(double w, double h, Stage s) {
    	Pane scene = emailContent(w,h);
    	scene.setLayoutX(0);
    	scene.setLayoutY(0);
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
        newWindow.setTitle("Compose");
        newWindow.setScene(secondScene);
        newWindow.setResizable(false);
        
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(s);
        
        //Set the window default position
        newWindow.setX(w-400);
        newWindow.setY(h-400);
        newWindow.show();
        
        //Send button event
        emailSent(send, w, h, newWindow);
    }
    
    //Reply
    void emailReply(double w, double h, Stage s) {
    	Pane scene = new Pane();
    	
    	Label object = new Label("To unknown");
    	object.setPadding(new Insets(20,20,20,20));
    	object.setPrefSize(w/4,40);
    	
    	TextArea content = new TextArea();
    	content.setPrefSize(w - w/4,h/2);
    	content.setFocusTraversable(false);
    	content.setLayoutY(80);
    	content.setLayoutX(10);
    	
    	Button send = new Button("Send");
    	send.setPrefSize(80, 30);
    	send.setFocusTraversable(false);
    	send.setLayoutY(content.getPrefHeight()+90);
    	send.setLayoutX(content.getPrefWidth()/2-40);
    	
    	scene.getChildren().addAll(object, content, send);
    	//Set new window
    	Scene secondScene = new Scene(scene,w - w/4+10, h-70);
        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Reply");
        newWindow.setScene(secondScene);
        newWindow.setResizable(false);
        
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(s);
        
        //Set the window default position
        newWindow.setX(w-400);
        newWindow.setY(h-400);
        newWindow.show();
        
        emailSent(send, w, h, newWindow);
    }
    
    //Send Event 
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
    
    //Create new window
    void NewWindow(String title, double w, double h) {
    }
    
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
    void Message_Click(ActionEvent event) {   
    	//Get the screen size
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();          
        //Get the main scene size
        Stage mainScene = (Stage) Profile.getScene().getWindow();
        //Create new stage
        Pane chatBorder = messageChat(screen.getWidth()/5, screen.getHeight()/2);
        
        
        Scene secondScene = new Scene(chatBorder, screen.getWidth()/5, screen.getHeight()/2);
        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Message");
        newWindow.setScene(secondScene);
        newWindow.setResizable(false);
        
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(mainScene);
        
        //Set the window default position
        newWindow.setX(screen.getWidth()/1.3);
        newWindow.setY(screen.getHeight()/2.5);
        newWindow.show();
    }
    
    Pane messageChat(double w, double h) {
    	Pane result = new Pane();
    	ScrollPane chat = new ScrollPane();
    	chat.setPrefSize(w+12, h-160);
    	chat.setLayoutY(30);
    	chat.setVbarPolicy(ScrollBarPolicy.NEVER);
    	chat.setHbarPolicy(ScrollBarPolicy.NEVER);
    	
    	//Text 
    	TextField text = new TextField();
    	text.setPrefSize(w-70, 127);
    	text.setLayoutY(h-120);
    	text.setAlignment(Pos.TOP_LEFT);
    	text.setFocusTraversable(false);
        	
    	//Send
    	Button send = new Button("Send");
    	send.setFocusTraversable(false);
    	send.setPrefSize(60, 25);
    	send.setLayoutX(w-60);
    	send.setLayoutY(h-20);
    	send.setPadding(new Insets(3,3,3,3));
    	
    	//Object
    	Label object = new Label("Unknown");
    	object.setPrefSize(70,20);
    	object.setLayoutX(w/2-35);
    	
    	//Delete all content
    	Button delete = new Button("Delete");
    	delete.setFocusTraversable(false);
    	delete.setPrefSize(60, 25);
    	delete.setLayoutX(w-60);
    	delete.setLayoutY(h-120);
    	delete.setPadding(new Insets(3,3,3,3));
   
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
    	result.getChildren().addAll(chat, text, send, object, delete);
    	return result;
    }
    
    @FXML
    void Logout(ActionEvent event)throws IOException {
    	Parent login_page = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Stage login_scene = (Stage) Profile.getScene().getWindow();
    
        login_scene.setScene(new Scene(login_page));
        login_scene.setTitle("Login");
        login_scene.show();
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