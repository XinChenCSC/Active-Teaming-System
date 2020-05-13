package application;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import Notice.Notice;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import Group.Group;
import Clients.Client;
import Clients.Guest;
import Clients.OU;
import Clients.SU;
import Clients.VIP;
import Email.Email;
import Group.Group_Status;
import Message.Message_Container;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
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


public class HomepageController{

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
    private MenuButton Group;
    
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
	private int emailNum = 0;
	// Number of existing messages
	private int messageNum = 0;
	// Number of existing notification
	private int notificationNum;
	// Target index in the infomation list
	private int UserIndex = 0;
	// Group index which the user belongs to
	private int GroupIndex = -1;
	// User's group index
	private int UserGroupIndex = -1;
    //Window size
	private final Rectangle2D screen = Screen.getPrimary().getVisualBounds();   
	//is guest
	private boolean isGuest = false;
	//Userlist
	private UserList userList = new UserList();
	//User information
	private Information_List Info_List = new Information_List();
	//Target
	private Client target;
	//Group container
	private Group_List G_List = new Group_List();
	
	private String guest_g = "";

	
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

    @FXML
    void Group_Click(ActionEvent event) throws IOException {
    	if(!this.isGuest)
    		findGroupIndex(); //Refresh
    	
        // New window (Stage)
        Stage newWindow = new Stage();
        
    	//--------------------------Main Scene-----------------------
        //Get the main scene size
        Stage mainScene = (Stage) Profile.getScene().getWindow();
        
        double height = screen.getHeight()/3;
        double width = screen.getWidth()/5;
        
        StackPane scene = groupMainScene(newWindow, width, height);
        
        //Is guest?
        if(this.isGuest || this.target instanceof SU) {    
        	((Button)scene.getChildren().get(2)).setVisible(false);
        	((Button)((HBox)scene.getChildren().get(1)).getChildren().get(0)).setDisable(true);
        	((Button)scene.getChildren().get(0)).setVisible(false);
        }
        //SU are not allowed to create a group
        else if((this.target instanceof OU && ((OU) this.target).isNeedAppeal()) ||
        		(this.target instanceof VIP && ((VIP) this.target).isNeedAppeal())) {
        	((Button)scene.getChildren().get(2)).setVisible(true);
        }
        
//      Check whether the cancel button needs to disable 
        if(this.GroupIndex != -1 && !this.isOpen()) {
    		if(this.target instanceof OU) {
    			if(((OU)this.target).isCreatingGroup())
    				((Button)scene.getChildren().get(3)).setVisible(true);        	
    		}
    		else if(this.target instanceof VIP && !this.isOpen()) {
    			if(((VIP)this.target).isCreatingGroup())
    				((Button)scene.getChildren().get(3)).setVisible(true);        	
    		}
        }
        
        Scene secondScene = new Scene(scene, width, height);
        // set new window
        NewWindow(newWindow, secondScene, mainScene, "Group");    
        
        //Move to group
        ((Button)((HBox)scene.getChildren().get(1)).getChildren().get(1)).setOnAction(group->{
        	//Search group
        	if(isValidGroupID(((TextField)((HBox)scene.getChildren().get(1)).getChildren().get(2)).getText())) {
        		try {
        			newWindow.close();
        			if(this.isGuest) {
        				FXMLLoader Loader = sceneSwitch("GroupPage.fxml", "Group");
        				GrouppageController GC = Loader.getController();
        				GC.GuestToGroup(this.G_List.getGroup_List().get(this.GroupIndex), this.G_List, this.Info_List, this.userList);
        			}
        			else {
        				FXMLLoader Loader = sceneSwitch("GroupPage.fxml", "Group");
        				GrouppageController GC = Loader.getController();
        				GC.UserToGroup(this.target, this.userList, this.G_List, this.Info_List, this.G_List.getGroup_List().get(this.GroupIndex));
        			}
				} catch (IOException e1) {e1.printStackTrace();}
        	}
        	else {
        		getAlert(AlertType.ERROR, "Wrong group ID.", ButtonType.OK, "Error");
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
        			getAlert(AlertType.ERROR, "Can't be Empty.", ButtonType.OK, "Error");
        		}
        		else {
        			//Submission completed, appeal button sets to invisible
        			Alert alert = getAlert(AlertType.CONFIRMATION, "Submission completed.", ButtonType.OK, "Confirmation");	
        			if(alert.getResult() == ButtonType.OK) {
        				newWindow_3.close();
        				((Button)scene.getChildren().get(2)).setVisible(false);
            			//set needappeal button to false
            	        if(this.target instanceof OU) {
            	        	if(((OU)this.target).isNeedAppeal()) 
            	        		((OU)this.target).setNeedAppeal(false);
            	        }
            	        else if(this.target instanceof VIP) {
            	        	if(((VIP)this.target).isNeedAppeal()) 
            	        		((VIP)this.target).setNeedAppeal(false);
            	        }
            	        ((Button) scene.getChildren().get(2)).setVisible(false);
            	        findGroupIndex(); //Refresh
            	        String str = "Group ID: " + this.G_List.getGroup_List().get(GroupIndex).getGroup_ID() + "\nMy name is: " +
            	        			this.target.getName();
                    	Email email = new Email("Group appeal", str, this.target.getEmail());
                    	this.Info_List.CreateEmail(userList.getSU_User().get(0).getID(), email);
                    	
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
            	if(((TextField)pane.getChildren().get(0)).getText().isEmpty() || ((TextField)pane.getChildren().get(1)).getText().isEmpty() || 
            			!sendInvitation((GridPane)((ScrollPane)pane.getChildren().get(2)).getContent())) {
					getAlert(AlertType.WARNING, "Wrong inputs existed.", ButtonType.OK, "Caution!");
            	}
            	else {
            		//Disable the create button because you can't create two group at the same time
        			this.userList.setCreatingGroup(this.target.getID(), true);
        			
        			//Disable the create button
        			((Button)((HBox)scene.getChildren().get(1)).getChildren().get(0)).setDisable(true);
            		
        			//Create a un-open group 
            		getNewGroup(((TextField) pane.getChildren().get(0)).getText(), ((TextField) pane.getChildren().get(1)).getText());
            		
            		//Automatic add to the group if the invitee is in your whitebox
            		automaticAdd((GridPane)((ScrollPane)pane.getChildren().get(2)).getContent());
            		
            		//Activate the cancel create button
            		if(this.G_List.getGroup_List().get(GroupIndex).getA_Group().size() < 4)
            			//If the current group >= 4 members, the cancel button will appear.
            			((Button)scene.getChildren().get(3)).setVisible(true);
            		else {
            			//Set open variable to true
            			this.G_List.getGroup_List().get(GroupIndex).setOpen(true);
            			
            	       	((Button)scene.getChildren().get(0)).setDisable(false);
            	       	((Button)scene.getChildren().get(0)).setText("Group Page");
                    	//Switch to group page
            	       	((Button)scene.getChildren().get(0)).setOnAction(w->{
                    		FXMLLoader Loader;
                    		try {
                    			Loader = sceneSwitch("GroupPage.fxml", this.G_List.getGroup_List().get(GroupIndex).getTitle());
                    			GrouppageController gc = Loader.getController();
                    			findUserGroupIndex();
                    			gc.HomeToGroup(this.G_List.getGroup_List().get(GroupIndex).getA_Group().get(UserGroupIndex),
                    					this.G_List.getGroup_List().get(GroupIndex), this.Info_List, this.userList, this.G_List);  
                    			newWindow.close();
                    		} catch (IOException e1) {
                    			e1.printStackTrace();
                    		}
                    	});
            		}
            		
            		//Set confirmation alert
                	Alert alert = getAlert(AlertType.INFORMATION, "Recruiting users...", ButtonType.OK, "Confirmation");
                	
                	//Set move to group page button
                	((Button)scene.getChildren().get(0)).setText("Recruiting");
                	if (alert.getResult() == ButtonType.OK)
                    	newWindow_2.close();
            	}
            });
        });
        //-------------------------------------------------------------------------------------------
//        //Cancel group
        ((Button)scene.getChildren().get(3)).setOnAction(e->{
        	Alert alert = getAlert(AlertType.WARNING, "Are you sure to cancel the recruiting?", ButtonType.YES, "Warning");
        	if(alert.getResult() == ButtonType.YES) {
        		//Remove the recruiting group
        		freeAll();
        		
        		//Remove the group
        		this.G_List.removeGroup(this.G_List.getGroup_List().get(GroupIndex));

        		((Button)scene.getChildren().get(0)).setText("You don't have a group");
        		
        		//Enable the create button
        		((Button)((HBox)scene.getChildren().get(1)).getChildren().get(0)).setDisable(false);
        		
        		//Disable the cancel group button
        		((Button)scene.getChildren().get(3)).setVisible(false);
        	}
        });
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
        delete.setOnAction(e ->{      	
        	deleteEmail(emailPane, gridPane, content);
        });
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
        Scene scene = new Scene(chatList,screen.getWidth()/5, screen.getHeight()/2);
        //Create message board
        Pane chatContent = chatContent(scene, chatList, screen.getWidth()/5, screen.getHeight()/2);
        //Message list
        GridPane gridPane = (GridPane) ((ScrollPane) chatList.getChildren().get(0)).getContent();
        //Show original list
        getChatList(scene, chatList, chatContent, gridPane, 0);
        //first time message with sb
        firstMessage(scene, chatList, chatContent, (TextField) chatList.getChildren().get(5), gridPane);  
        
        //Delete messages
        ((Button) chatList.getChildren().get(1)).setOnAction(e-> deleteMessage(chatList, gridPane));  
        //Set new window
        NewWindow(newWindow, scene, mainStage, "Message");
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
         Tab tainvite = new Tab("General"); //General 
         Tab tab2 = new Tab("Group"); //Group 
         Tab tab3 = new Tab("Blacklist"); //Blacklist
         tabPane.getTabs().addAll(tainvite, tab2, tab3);
         tainvite.setClosable(false);
         tab2.setClosable(false);
         tab3.setClosable(false);
     	 //Import css file
     	 tabPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
     	 //-----------------------------------------------General-----------------------------------------------
     	 //Tab content methods
     	 generalTabContent(tainvite, w, h);
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
 
	@SuppressWarnings("unchecked")
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
    			getAlert(AlertType.ERROR, "Can't be empty.", ButtonType.OK, "Error");
    		}
    		else if (!isValidID(((TextField)pane.getChildren().get(2)).getText())) {
    			getAlert(AlertType.ERROR, "Invalid ID", ButtonType.OK, "Error");
    		}
    		else {
    			//Save claim as email format to the SU
    			String content = "ID: " + ((TextField)pane.getChildren().get(2)).getText() + "\nReason: " + 
    						((TextArea)pane.getChildren().get(3)).getText();
    			Email email = new Email(((ComboBox<String>)pane.getChildren().get(1)).getSelectionModel().getSelectedItem().toString(),
    					content, this.target.getName());
    			this.Info_List.CreateEmail(this.userList.getSU_User().get(0).getID(), email);
    			
    			//Confirmation alert
    			getAlert(AlertType.CONFIRMATION, "Claim submitted.", ButtonType.OK, "Confirmation");
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
    private StackPane groupMainScene(Stage stage, double w, double h) throws IOException {
    	if(!this.isGuest)
    		findGroupIndex();	//Refresh
        StackPane subScene = new StackPane();
        subScene.setPadding(new Insets(h*0.1,w*0.05,h*0.05,w*0.05));
        //Create label
        Button lb = new Button();
        lb.setFocusTraversable(false);
        
        //If the group has enough users to open
        if(this.GroupIndex != -1 && isOpen() && isInGroup() && !checkAppeal()){
        	lb.setDisable(false);
        	lb.setText("Group Page");
        	//Switch to group page
        	lb.setOnAction(e->{
        		FXMLLoader Loader;
        		try {
        			Loader = sceneSwitch("GroupPage.fxml", this.G_List.getGroup_List().get(GroupIndex).getTitle());
        			GrouppageController gc = Loader.getController();
        			findUserGroupIndex();
        			gc.HomeToGroup(this.G_List.getGroup_List().get(GroupIndex).getA_Group().get(UserGroupIndex),
        					this.G_List.getGroup_List().get(GroupIndex), this.Info_List, this.userList, this.G_List);  
        			stage.close();
        		} catch (IOException e1) {
        			e1.printStackTrace();
        		}
        	});
        }
        // Display group status
        else {
            lb.setDisable(true);
            lb.setText("You don't have a group."); 
            lb.setStyle("-fx-background-color: #F0F8FF;"
            		+ "-fx-border-color: #F0F8FF;");
        }
        
        HBox hbox = new HBox();
        hbox.setMaxSize(w, h*0.2);
        
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
        
        //Check whether the create button needs to disable
		if(this.target instanceof OU) {
			if(((OU) this.target).isCreatingGroup() || ((OU)this.target).isInGroup())
				createButton.setDisable(true);
		}
		else if(this.target instanceof VIP) {
			if(((VIP) this.target).isCreatingGroup() || ((VIP)this.target).isInGroup())
				createButton.setDisable(true);
		}
        
        //Cancel creating group
        Button cancelButton = new Button();
        cancelButton.setText("Cancel group");
        cancelButton.setStyle("-fx-text-fill: #FF0000;");
        cancelButton.setFocusTraversable(false);
        cancelButton.setVisible(false);  
        
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().addAll(createButton, go, groupId);  
        
        //Appeal button
        Button appeal = new Button("Appeal");
        appeal.setVisible(false);
        appeal.setFocusTraversable(false);
			
        
        //set the position
        StackPane.setAlignment(appeal, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(cancelButton, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(hbox, Pos.TOP_CENTER);
        
        subScene.getChildren().addAll(lb, hbox, appeal, cancelButton);
        return subScene;
    }
    
    //Check the group is open or not
    private boolean isOpen() {
    	if(this.G_List.getGroup_List().get(GroupIndex).isOpen())
    		return true;
    	return false;
    }
    
    //Check the user is in a group or not;
    private boolean isInGroup() {
    	if(this.target instanceof OU) {
    		return ((OU)this.target).isInGroup();
    	}
        else if(this.target instanceof VIP) {
        	return ((VIP)this.target).isInGroup();
        }
    	return false;
    }
    
    private boolean checkAppeal() {
    	if(this.target instanceof OU) {
    		return ((OU)this.target).isNeedAppeal();
    	}
        else if(this.target instanceof VIP) {
        	return ((VIP)this.target).isNeedAppeal();
        }
    	return false;
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
    	this.UserIndex = getUserIndex(); //refresh
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
    	friend_grid.setVgap(20);
    	friend_grid.setHgap(20);
    	friend_grid.setPadding(new Insets(20,10,20,10));
    	ColumnConstraints column1 = new ColumnConstraints(170);
    	ColumnConstraints column2 = new ColumnConstraints(100);
    	friend_grid.getColumnConstraints().addAll(column1, column2);
    	
    	//Show all avaliable friends
    	for(int i = 0, j = 0; i < this.userList.getAll_Size(); ++i) {
        	if(this.target.getID().compareTo(this.userList.getAll_User().get(i).getID()) != 0 &&
        			!this.Info_List.getInfo_Con().get(UserIndex).getPersonal_Blacklist().contains(this.userList.getAll_User().get(i)) &&
        			!(this.userList.getAll_User().get(i) instanceof SU)) {
        		//Label names
        		Label Name = new Label();
        		Name.setFont(new Font(16));
        		GridPane.setHalignment(Name, HPos.CENTER);
        		friend_grid.add(Name, 0, j);
        		//set up invite button
        		Name.setText(this.userList.getAll_User().get(i).getName());
        		Button invite = new Button("Invite");
        		invite.setFocusTraversable(false);
        		getStyle(invite);
        		GridPane.setHalignment(invite, HPos.CENTER);
        		friend_grid.add(invite, 1, j++);   
        		
        		//Each user can be invited only once.
        		invite.setOnAction(e->{
        			invite.setDisable(true);
        		});
        	}
    	}
    	//Confirmed button
    	Button confirm_button = new Button("Confirm");
    	getSetting(confirm_button, 100, 30, 300, 550);
    	getStyle(confirm_button);
    	friend_list.setContent(friend_grid);
    	pane.getChildren().addAll(group_title, group_description, friend_list, confirm_button);
    	return pane;
    }
       
    private boolean sendInvitation(GridPane gridPane) {
    	int total = 0;
    	for(int i = 1; i < gridPane.getChildren().size(); i+=2) {
    		if(((Button)gridPane.getChildren().get(i)).isDisable()) {
    			++total;
    		}
    	}
    	if(total >= 5) 
    		return true;
    	return false;
    }
    
	//Automatic add to the group if the invitee is in your whitebox
    private void automaticAdd(GridPane gridPane) {
    	for(int i = 1; i < gridPane.getChildren().size(); i+=2) {
    		if(((Button)gridPane.getChildren().get(i)).isDisable()) {
    			//Search for the target user, null will return if no result
    			Client client = this.Info_List.getInfo_Con().get(UserIndex).findFriend(((Label)gridPane.getChildren().get(i-1)).getText());
    			if(client != null) {
    				if(client instanceof VIP)
    					((VIP)client).setInGroup(true);
    				else if(client instanceof OU)
    					((OU)client).setInGroup(true);
    		    	Group_Status GS = new Group_Status(client); //Create a group member object
    				this.G_List.getGroup_List().get(GroupIndex).addGroupMember(GS);	//Add the group member to the object
    				//Send the confirmation notification
    				Notice notification = new Notice(true, getCurrentTime(), 
        					"You have been joined to " + this.target.getName() + "'s group.");
    				this.Info_List.CreateNotice(client.getID(), notification);
    			}
    			else {
    				//Send the invitation notification
    				Notice notification2 = new Notice(Integer.parseInt(this.G_List.getGroup_List().get(GroupIndex).getGroup_ID()), getCurrentTime(), 
        					this.target.getName() + " invite you to join his/her group.", true);
        			client = this.userList.findUser(((Label)gridPane.getChildren().get(i-1)).getText());
        			this.Info_List.CreateNotice(client.getID(), notification2);
    			}
    		}
    	}
    }
    
	private void findUserGroupIndex() {
    	for(int i = 0; i < this.G_List.getGroup_List().get(GroupIndex).getA_Group().size(); ++i) {
    		if(this.G_List.getGroup_List().get(GroupIndex).getA_Group().get(i).getUser().getID().compareTo(this.target.getID()) == 0)
    			this.UserGroupIndex = i;
    	}
    }
    
    private void freeAll() {
		if(this.target instanceof OU) {
			((OU)this.target).setCreatingGroup(false);
			((OU)this.target).setInGroup(false);
		}
		else if(this.target instanceof VIP) {
			((VIP)this.target).setCreatingGroup(false);
			((VIP)this.target).setInGroup(false);
		}
		for(int i = 0; i < this.G_List.getGroup_List().get(GroupIndex).getA_Group().size(); ++i) {
			if(this.G_List.getGroup_List().get(GroupIndex).getA_Group().get(i).getUser() instanceof OU) {
				((OU)this.G_List.getGroup_List().get(GroupIndex).getA_Group().get(i).getUser()).setInGroup(false);
			}
			else if(this.G_List.getGroup_List().get(GroupIndex).getA_Group().get(i).getUser() instanceof VIP) {
				((VIP)this.G_List.getGroup_List().get(GroupIndex).getA_Group().get(i).getUser()).setInGroup(false);
			}
		}
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
    	for(int i = 0; i < this.Info_List.getSystem_Blacklist().size(); ++i){
    		Label member = new Label(this.Info_List.getSystem_Blacklist().get(i).getName());
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
    }
    
    @SuppressWarnings("unused")
	private String generalTabEvent() {
    	//Create random id and temporary password.
		Random rand = new Random();
		String id = "";
		do {
			id = "";
			int digit = rand.nextInt(100000) + 10000;
			id = Integer.toString(digit);	
		}while(!newIDCheck(id));
    	return id;
    }
    
    private void newMemberBoard(BorderPane pane, double w, double h) {
    	//HBox
    	HBox hbox = new HBox();
    	//Gather random password and random ID;
    	Button registration = new Button("Randomize");
    	Button Blacklist = new Button("Blacklist");
    	Button Reject = new Button("Reject");
    	SU su = new SU();
    
    	//Email adress
    	TextField address = new TextField();
    	address.setPromptText("Email address");
    	
    	registration.setOnMouseClicked(e->{
    		
    		if(address.getText().compareTo(this.userList.getGuest().getEmail()) == 0) {
    			
//    			String account = su.Generate_Acc();
    			
    			String account = generalTabEvent();
    			String password = su.Generate_Pass();
    	
    			this.userList.getGuest().setID(account);
    			this.userList.getGuest().setPassword(password);
    			this.userList.getGuest().setActivate(true);
    			if(this.userList.getGuest().getNumRegister() < 3) {
    				
    				this.userList.getGuest().setNumRegister(1);
    		
    				getAlert(AlertType.INFORMATION, "New Guest register completed", ButtonType.OK, "Confirmaton");		
    				this.userList.getGuest().setActivate(true);
    		}
    				
    		}
    	});
    	hbox.setAlignment(Pos.CENTER);
    	hbox.getChildren().addAll(Reject,Blacklist,registration, address);
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
    	//Get the index of email list
    	this.UserIndex = getUserIndex();
    	//List all existed emails
    	for(int i = emailNum-1, j = 0; i >= start ; --i, ++j) {
    		//Hide content area
    		content.setVisible(false);
    		//Create button and assign subject to it
    		Button email_label = new Button(this.Info_List.getInfo_Con().get(UserIndex).getEmail_Content().get(i).getSubject());
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
        	setEmailContent(children, email_label, content, i);
        	
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
        	gridPane.add(email_label, 0, j);
        	gridPane.add(delete_dot, 0, j);
    	}
    }
	
    //Set email content
    private void setEmailContent(ObservableList<Node> children, Button label, Pane pane, int i) {
    	//Set text to text fields
    	label.setOnAction(e->{
    		pane.setVisible(true);
    		((TextField) children.get(1)).setText(this.Info_List.getInfo_Con().get(UserIndex).getEmail_Content().get(i).getTarget());
    		((TextField) children.get(3)).setText(this.Info_List.getInfo_Con().get(UserIndex).getEmail_Content().get(i).getSubject());
    		((TextArea) children.get(4)).setText(this.Info_List.getInfo_Con().get(UserIndex).getEmail_Content().get(i).getContent());
    	});
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
			Alert alert = getAlert(AlertType.WARNING, "Delete all selected Emails?", ButtonType.YES, "Caution");   			  		
			if(alert.getResult() == ButtonType.YES) {
				ObservableList<Node> children = gridPane.getChildren();	
				//Remove deleted emails
				int index = 0;
				this.UserIndex = getUserIndex();
				for(int i = 0; i < emailNum; ++i) {
					if(!selectedNum.contains(i)) 
						index += 2;
					else {
						//Remove email
						this.Info_List.removeEmail(this.target.getID(), this.Info_List.getInfo_Con().get(UserIndex).getEmail_Content().get(emailNum-1-i));
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
    	//Activate compose scene 
    	((TextField) ((GridPane) scene.getChildren().get(0)).getChildren().get(1)).setEditable(true);
    	((TextField) ((GridPane) scene.getChildren().get(0)).getChildren().get(3)).setEditable(true);
    	((TextArea) ((GridPane) scene.getChildren().get(0)).getChildren().get(4)).setEditable(true);
    	Button send = (Button) ((GridPane) scene.getChildren().get(0)).getChildren().get(5);
    	send.setText("Send");
   	 	//Send button event
    	send.setOnAction(e->{
  			//check whether object, subject, or content is empty, and whether the email address is valid
    		if(!((TextArea) ((GridPane) scene.getChildren().get(0)).getChildren().get(4)).getText().isEmpty() &&
    				!((TextField) ((GridPane) scene.getChildren().get(0)).getChildren().get(1)).getText().isEmpty() &&
    				!((TextField) ((GridPane) scene.getChildren().get(0)).getChildren().get(3)).getText().isEmpty() &&
    				isValidEmail(((TextField) ((GridPane) scene.getChildren().get(0)).getChildren().get(1)).getText())) {
    			//Save email to yourself
    			Email email = new Email(((TextField) ((GridPane) scene.getChildren().get(0)).getChildren().get(3)).getText(),
    					((TextArea) ((GridPane) scene.getChildren().get(0)).getChildren().get(4)).getText(),
    					((TextField) ((GridPane) scene.getChildren().get(0)).getChildren().get(1)).getText());
    			this.Info_List.CreateEmail(this.target.getID(), email);
    			//Save email to the receiver email box
    			Email email2 = new Email(((TextField) ((GridPane) scene.getChildren().get(0)).getChildren().get(3)).getText(),
    					((TextArea) ((GridPane) scene.getChildren().get(0)).getChildren().get(4)).getText(),
    					this.target.getEmail());
    			this.Info_List.CreateEmail(getUserID(((TextField) ((GridPane) scene.getChildren().get(0)).getChildren().get(1)).getText()), email2);
    			//Confirmation Alert
    			Alert alert = getAlert(AlertType.INFORMATION, "Email has been sent!", ButtonType.OK, "Confirmaton");			
				if(alert.getResult() == ButtonType.OK) {
					((Stage) scene.getScene().getWindow()).close(); 
					
					//Add new email to the email list, clear the email list and reload again
					gridPane.getChildren().clear();
					gridPane.getRowConstraints().clear();
		            ++this.emailNum;
		            getEmailList(w, h, s, gridPane, content, 0);				
				}
    		}
    		else {
				getAlert(AlertType.ERROR, "Invalid email address or empty field existed.", ButtonType.OK, "Error");
    		}
    	});
    	//Set new window
    	Scene secondScene = new Scene(scene, w, h);
        // New window (Stage)
        Stage newWindow = new Stage();
        //Set newWindow
        NewWindow(newWindow, secondScene, s, "Compose");    
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
    	this.UserIndex = getUserIndex(); //Refresh
    	
    	((ScrollPane) NotificationPane.getChildren().get(1)).prefWidthProperty().bind(this.NotificationPane.prefWidthProperty());
    	GridPane gridPane = new GridPane();
    	ColumnConstraints column = new ColumnConstraints();
    	column.prefWidthProperty().bind(this.NotificationPane.prefWidthProperty());
    	gridPane.getColumnConstraints().add(column);
    	gridPane.setGridLinesVisible(true);
    	
    	//Set up each row
    	for(int i = this.notificationNum-1, j = 0; i >= 0; --i, ++j) {
        	//Temp
    		Notice temp = this.Info_List.getInfo_Con().get(UserIndex).getNotice().get(i);
    		//Unread notification
    		Label unread = new Label();
    		unread.setId("unread");
    		unread.setPadding(new Insets(5,5,5,5));
    		GridPane.setHalignment(unread, HPos.LEFT);
    		GridPane.setValignment(unread, VPos.TOP);
    		gridPane.add(unread, 0, j);
    		if(this.Info_List.getInfo_Con().get(UserIndex).getNotice().get(i).isNewIcon())
    			unread.setText("New");
    		
    		//Content label
    		Label content = new Label(this.Info_List.getInfo_Con().get(UserIndex).getNotice().get(i).getMessage());
    		content.setPadding(new Insets(5,5,5,5));
    		GridPane.setHalignment(content, HPos.CENTER);
    		GridPane.setValignment(content, VPos.CENTER);
    		gridPane.add(content, 0, j);
    		
    		//Time 
    		Label time = new Label(this.Info_List.getInfo_Con().get(UserIndex).getNotice().get(i).getDate());
    		time.setPadding(new Insets(5,5,5,5));
    		GridPane.setHalignment(time, HPos.RIGHT);
    		GridPane.setValignment(time, VPos.BOTTOM);
    		gridPane.add(time, 0, j);
    		
    		//Back pane
    		Pane pane = new Pane();
    		getSetting(pane, 285, 70, 0, 0);
    		pane.setId("back_pane");
    		gridPane.add(pane, 0, j);
    		    		
    		//Delete event
    		pane.setOnMouseClicked(e->{
    			//Remove new after the first click
    			if(temp.isNewIcon())
    			{
    				temp.setNewIcon(false);
    				getNotificationPane(); //Refresh the notification pane    				
    			}   			
    			
    			//Set up alert
    	    	ButtonType confirm = new ButtonType("Confirm", ButtonData.OK_DONE);
    			ButtonType delete = new ButtonType("Delete", ButtonData.FINISH);
    			Alert alert = getAlert(AlertType.CONFIRMATION, content.getText(), confirm, delete, "Invitation");
    			
    			Optional<ButtonType> result = alert.showAndWait();
    			if (result.get() == delete) {
    				this.Info_List.removeNotice(this.target.getID(), temp);
    				gridPane.getChildren().clear();
    				gridPane.getRowConstraints().clear();
    				getNotificationPane(); //Refresh the notification pane
    			}
    			//Is a invitatinoal notification
    			else if(result.get() == confirm && temp.getIndex() > -1) {
    				String id = Integer.toString(temp.getIndex());
    				//Check whether the group exist
    				if(!isValidGroup(id)){
						getAlert(AlertType.ERROR, "The group is no longer exist.", ButtonType.OK, "Error");
    				}
    				else {
    					ButtonType accept = new ButtonType("Accept", ButtonData.FINISH);
    					ButtonType later = new ButtonType("Later", ButtonData.CANCEL_CLOSE);
    					Alert in_alert = getAlert(AlertType.CONFIRMATION, "Accept or Reject.", accept, later, "Important");
    					
    					Optional<ButtonType> result2 = in_alert.showAndWait();
    					if(result2.get() == accept) {
    						for(int r = 0 ; r < this.G_List.getGroup_List().size(); ++r) {
    							if(this.G_List.getGroup_List().get(r).getGroup_ID().compareTo(id) == 0) {
    								//If the member of group is greater than 7, can't join anymore
    								if(this.G_List.getGroup_List().get(r).getA_Group().size() > 7)
    									getAlert(AlertType.ERROR, "The group is already full.", ButtonType.OK, "Error");
    								else if(this.G_List.getGroup_List().get(r).isGroupMember(this.target.getID()))
    									getAlert(AlertType.WARNING, "You already in the group.", ButtonType.OK, "Warning");
    								else if(!this.G_List.getGroup_List().get(r).isOpen())
    									getAlert(AlertType.ERROR, "The group is closed.", ButtonType.OK, "Error");
    								else {
    									Group_Status GS = new Group_Status(this.target);	//New group member object
    									
    									if(this.target instanceof OU)
    										((OU)this.target).setInGroup(true);
    									else if(this.target instanceof VIP)
    										((VIP)this.target).setInGroup(true);
    									
    									this.G_List.getGroup_List().get(r).addGroupMember(GS);	//Add the group member to the object 
    									
    									//Open group if the total members are greater than or equals 4
    									if(this.G_List.getGroup_List().get(r).getA_Group().size() >= 4)
    										this.G_List.getGroup_List().get(r).setOpen(true);
    									
    									getAlert(AlertType.INFORMATION, "You are in the group now.", ButtonType.OK, "Important"); //Alert
    									
    								}
    							}
    						}
    					}
    				}
    			}
    		});
    	}
    	((ScrollPane) NotificationPane.getChildren().get(1)).setContent(gridPane);
    }
    
    private boolean isValidGroup(String ID) {
    	for(Group group : this.G_List.getGroup_List()) {
    		if(group.getGroup_ID().compareTo(ID) == 0) {
    			return true;
    		}
    	}
    	return false;
    }
    
    //**************************************************************************

    //-------------------------------Scene Transition---------------------------
    //Move to the login page
    @FXML
    private void Logout(ActionEvent event)throws IOException {
    	FXMLLoader loader = sceneSwitch("LoginPage.fxml", "Login");
    	LoginController lc = loader.getController();
    	lc.HomeToLogin(this.userList, this.Info_List, this.G_List);
    }
    
    //Move to the account page
    @FXML
    private void moveToAccount(ActionEvent event) throws IOException {
    	FXMLLoader Loader = sceneSwitch("Accountpage.fxml", "Account");
    	AccountpageController apc = Loader.getController();
    	apc.HomeToAccount(this.userList, this.Info_List, this.target, this.G_List);
    }
    //**************************************************************************
    
    //-------------------------------Message---------------------------------------------
    //Messaging scene
    private Pane chatContent(Scene scene, Pane chatList, double w, double h) {
    	Pane result = new Pane();
    	//Import css file 
    	result.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	GridPane gridPane = new GridPane();
    	gridPane.setPadding(new Insets(0,15,0,15));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(w-15));
    	
    	//Object
    	Label object = new Label();
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

    	//Grid pane
    	GridPane messages = new GridPane();
    	messages.getColumnConstraints().add(new ColumnConstraints(w-45));
    	messages.setPadding(new Insets(0,0,0,15));
    	messages.setVgap(10);
    	
    	//Back button
    	back.setOnAction(e-> scene.setRoot(chatList));

    	chat.setContent(messages);
    	result.getChildren().addAll(gridPane);
    	return result;
    }

    //Load previous messages
    private void loadPreviousMessages(String[][] container, int i, GridPane messages) {
		for (int j = 0; j < this.Info_List.getInfo_Con().get(UserIndex).getMessage_Content().get(i).getPosition(); ++j) {
			// messages
			Label input = new Label(container[j][1]);
			input.setWrapText(true);
			input.setAlignment(Pos.CENTER_RIGHT);
			// Get the width of string
			if (container[j][0].compareTo("0") == 0) {
				input.setStyle("-fx-background-color: #7FFF00;");
				GridPane.setHalignment(input, HPos.RIGHT);
			} else {
				input.setStyle("-fx-background-color: #F0FFFF;");
				GridPane.setHalignment(input, HPos.LEFT);
			}
			// add row 
        	messages.getRowConstraints().add(new RowConstraints());
        	messages.getRowConstraints().get(messages.getRowConstraints().size()-1).prefHeightProperty().bind(input.prefHeightProperty());
			messages.add(input, 0, j);
		}
    }

    //Message send button event
    private void MessageSendEvent(Button send, GridPane messages, TextField text, int i) {
    	send.setOnAction(e->{
    		// messages
    		Label input = new Label(text.getText());
    		input.setStyle("-fx-background-color: #7FFF00;");
    		input.setWrapText(true);
    		input.setAlignment(Pos.CENTER_RIGHT);
    		if(text.getText().trim().length() > 0) {
            	//add row
            	messages.getRowConstraints().add(new RowConstraints());
            	//Resize the height of row.
            	messages.getRowConstraints().get(messages.getRowConstraints().size()-1).prefHeightProperty().bind(input.prefHeightProperty());
            	//set label to the right
            	GridPane.setHalignment(input, HPos.RIGHT);
            	messages.add(input, 0, this.Info_List.getInfo_Con().get(UserIndex).getMessage_Content().get(i).getPosition());
            	//Save message
            	int userIndex = getTargetIndex(this.Info_List.getInfo_Con().get(UserIndex).getMessage_Content().get(i).getID());
            	int messIndex = getTargetMessageIndex(this.target.getID(), userIndex);
            	//Create a new chat board for target user
            	if(messIndex == -1) {
            		Message_Container mc = new Message_Container(this.target.getID());
            		mc.addContent("1", text.getText());
            		this.Info_List.CreateMessage(this.Info_List.getInfo_Con().get(userIndex).getID(), mc);
            	}
            	else
            		this.Info_List.getInfo_Con().get(userIndex).getMessage_Content().get(messIndex).addContent("1", text.getText());
        		this.Info_List.getInfo_Con().get(UserIndex).getMessage_Content().get(i).addContent("0", text.getText());  	
            	text.clear();
    		}
    	});
    }
    
    private int getTargetMessageIndex(String id, int userIndex) {
    	for(int i = 0; i < this.Info_List.getInfo_Con().get(userIndex).getMessage_Content().size(); ++i) {
    		if(this.Info_List.getInfo_Con().get(userIndex).getMessage_Content().get(i).getID().compareTo(id) == 0) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    private int getTargetIndex(String id) {
    	for(int i = 0; i < this.Info_List.getInfo_Con().size(); ++i) {
    		if(this.Info_List.getInfo_Con().get(i).getID().compareTo(id) == 0) {
    			return i;
    		}
    	}
    	return -1;
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
    	result.getChildren().addAll(list, delete, text, okay, cancel, search);
    	return result;
    }
    
    //Find the member and message to 
    private void firstMessage(Scene scene, Pane chatList, Pane chatContent, TextField search, GridPane gridPane) {
    	((Button)chatList.getChildren().get(2)).setOnAction(e->{
    		if(search.getText().isEmpty() || !isValidID(search.getText()) || !isRepeatID(search.getText())) {
    			search.setStyle("-fx-border-color: #FF0000;" + "-fx-border-radius: 20;" + "-fx-background-radius: 20;");}
    		else {
    			//Save message target for the current user
    			Message_Container mc = new Message_Container(search.getText());
    			this.Info_List.CreateMessage(this.target.getID(), mc);
    			//Save message for the target user
//    			Message_Container mc2 = new Message_Container(this.target.getID());
//    			this.Info_List.CreateMessage(search.getText(), mc2);
    			
    			search.clear();
    			search.setStyle("-fx-border-color: #000000;" + "-fx-border-radius: 20;" + "-fx-background-radius: 20;");
    			gridPane.getChildren().clear();
    			gridPane.getRowConstraints().clear();
    			//Increase total number of messages
    			++this.messageNum;
    			//Refresh chat list
    			getChatList(scene, chatList, chatContent, gridPane, 0);
    			//scene.setRoot(chatContent);
    		}
    	});
    }
  
    private boolean isRepeatID(String id) {
    	for(int i = 0; i < this.Info_List.getInfo_Con().get(UserIndex).getMessage_Content().size(); ++i) {
    		if(this.Info_List.getInfo_Con().get(UserIndex).getMessage_Content().get(i).getID().compareTo(id) == 0) {
    			return false;
    		}
    	}
    	return true;
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
				this.UserIndex = getUserIndex();
				for(int i = 0; i < messageNum; ++i) {
					if(!selectedNum.contains(i)) 
						index += 2;
					else {
						this.Info_List.removeMessage(this.target.getID(), this.Info_List.getInfo_Con().get(UserIndex).getMessage_Content().get(messageNum-1-i));
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
    private void getChatList(Scene scene, Pane chatList, Pane chatContent, GridPane gridPane, int start) {
    	//Get the user index in the userlist
    	this.UserIndex = getUserIndex();
    	for(int i = this.messageNum-1, j = 0; i >= start ; --i, ++j) {
    		//Set the label in chat list as target's ID
    		Button label = new Button(this.Info_List.getInfo_Con().get(UserIndex).getMessage_Content().get(i).getID());
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
            
        	//Set chat content
        	setChatContent(scene, chatList, chatContent, label, i);
            
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
        	gridPane.add(label, 0, j);
        	gridPane.add(delete_dot, 0, j);  
    	}
    }
       
    private void setChatContent(Scene scene, Pane chatList, Pane chatContent, Button label, int i) {
    	//Get the components from the parameters
    	ScrollPane scrollPane = (ScrollPane)((GridPane)chatContent.getChildren().get(0)).getChildren().get(2);
    	GridPane gridPane = (GridPane) scrollPane.getContent();
    	TextField textField = (TextField) ((GridPane)chatContent.getChildren().get(0)).getChildren().get(3);
    	Button send = (Button) ((GridPane)chatContent.getChildren().get(0)).getChildren().get(5);
    	//Always at the bottom of the scroll pane
    	gridPane.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
    	
    	//Change to the content scene
        label.setOnAction(e->{
        	
        	//Set the ID of target user
        	((Label) ((GridPane) chatContent.getChildren().get(0)).getChildren().get(0)).setText(this.Info_List.getInfo_Con().get(UserIndex).getMessage_Content().get(i).getID());
        	
        	//Get previous messages container
        	String[][] container = this.Info_List.getInfo_Con().get(UserIndex).getMessage_Content().get(i).getContent();

        	//Load previous messages
        	loadPreviousMessages(container, i, gridPane);
        	
        	//Send button event
        	MessageSendEvent(send, gridPane, textField, i);
        	
        	scene.setRoot(chatContent);
        });
        
        //Back to the messagelist if BACK button clicked.
        ((Button) ((GridPane) chatContent.getChildren().get(0)).getChildren().get(4)).setOnAction(e->{
        	//Clear gridPane
        	gridPane.getChildren().clear();
        	gridPane.getRowConstraints().clear();
        	scene.setRoot(chatList);
        });
    }
	//**************************************************************************
    
    //----------------------------------Password change-------------------------------------
    private void passwordChangeScene() {
    	double w = screen.getWidth()*0.2;
    	double h = screen.getHeight()*0.2;
    	Stage mainScene = (Stage) scrollPane.getScene().getWindow();
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
    		gridpane.getRowConstraints().add(new RowConstraints(h*0.15));
    		if (i == 4) {
    			//create confirm button
    			Button confirm = new Button("Confirm");
    			getStyle(confirm);
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
    			getStyle(field);
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
        					((PasswordField) gridpane.getChildren().get(3)).getText()) != 0) {
        		getAlert(AlertType.ERROR, "Please reset your password.", ButtonType.OK, "Error");
        	}
    		else {
    			//Save the password to the database
    			this.target.setPassword(((PasswordField) gridpane.getChildren().get(3)).getText());
    			//If the new user reset their password, their recommender needs to evaluate them.
    			((Guest) this.target).setLogin(true);
    			((Guest) this.target).setActivate(true);
    			//Promote the new user up to OU
    			OU ou = new OU(this.target.getName(), this.target.getID(), this.target.getEmail(),
    					"OU", this.target.getInterest(), this.target.getRecommender(),
    					this.target.getPassword(), 0, 0, 0, 0, "Good", "On", getCurrentTime());
    			userList.addOU_User(ou);
    			userList.addAll_User(ou);
    			getAlert(AlertType.CONFIRMATION, "Your password has been resetted.", ButtonType.OK, "Information");
    			this.userList.getGuest().setLogin(true);
    			
    			newWindow.close();
    		}
    	});
    	newWindow.setOnCloseRequest(e->{
    		getAlert(AlertType.WARNING, "Please click the confirm button when you entered the new password.", ButtonType.OK, "Warning");
			e.consume();
    	});
    	//set children
    	pane.getChildren().add(gridpane);
    	//Call new window
    	NewWindow(newWindow, secondScene, mainScene, "Password change");    
    }
    //**************************************************************************************
    
//    ---------------------------------------Scene Switch Method----------------------------------------------
    //New user evaluation by their recommender
	@SuppressWarnings("unchecked")
	private void recommenderEvaluation() {
        Stage mainScene = (Stage) scrollPane.getScene().getWindow();       
        double height = screen.getHeight()*0.2;
        double width = screen.getWidth()*0.2;     
        StackPane scene = new StackPane();
        Scene secondScene = new Scene(scene, width, height);
        // New window (Stage)
        Stage newWindow = new Stage();
        scene.setPadding(new Insets(height*0.1, width*0.2, height*0.1, width*0.2));
        //Title 
        Label title = new Label("Quick Evaluation" + "(" + this.userList.getGuest().getName() + ")");
        title.setAlignment(Pos.CENTER);
        title.setPrefSize(width, height*0.2);
        title.setStyle("-fx-background-color: #FF7F50;");
        //ComboBox for scores
        ComboBox<Integer> scores = new ComboBox<>();
        scores.setPrefWidth(width*0.4);
        scores.setPromptText("Enter scores");
        //Get the scores range
        if(this.target instanceof OU)
        	 for(int i = 0; i < OU.getEvaluationScoreRange(); ++i)
        		 scores.getItems().add(i+1);
        else if(this.target instanceof VIP)
        	for(int i = 0; i < VIP.getEvaluationScoreRange(); ++i)
       		 	scores.getItems().add(i+1);
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
        		getAlert(AlertType.ERROR, "Scores can't be empty.", ButtonType.OK, "Error");
        	}
        	else {
        		
        		incScore(this.guest_g, ((ComboBox<Integer>) scene.getChildren().get(1)).getSelectionModel().getSelectedItem());
        		//Remove the presentee
        		if(this.target instanceof OU)
        			((OU)this.target).removePresentee(this.userList.getGuest());
        		else if(this.target instanceof VIP)
        			((VIP)this.target).removePresentee(this.userList.getGuest());
        		//Make guest empty 
        		this.userList.setGuest(new Guest());
        		getAlert(AlertType.INFORMATION, "Submission completed.", ButtonType.OK, "Confirmation");
        		newWindow.close();
        	}
        });
    	newWindow.setOnCloseRequest(e->{
    		getAlert(AlertType.WARNING, "Please select a proper initial scores for your presentee.", ButtonType.OK, "Warning");
			e.consume();
    	});
    	this.userList.setGuest(null);
        // set new window
        NewWindow(newWindow, secondScene, mainScene, "New user evaluation"); 
    }
    
    void LoginToHome(String name, boolean evaluation, boolean passwordChange, UserList ul, Client target, Information_List il, Group_List g_List) {
    	this.userList = ul;	//set userList
    	this.target = target; //set target user
    	this.Info_List = il; //set user information
    	this.G_List = g_List;
    	this.guest_g = name;
    	if(evaluation)
    		recommenderEvaluation();
    	if(passwordChange)
    		passwordChangeScene();
    	if(this.target instanceof SU)
    		SUMode();
    	
        //List top three members
        ListTopMember();
    }
      
    private void SUMode() {
    	this.Evaluation.setDisable(true);
    	this.Claim.setVisible(false);
    }
    
    void GuestMode(Group_List g_List2, Information_List info_list, UserList user_list) {
    	this.Info_List = info_list;
    	this.userList = user_list;
    	this.G_List = g_List2;
    	this.Edit.setDisable(true);
    	this.Evaluation.setDisable(true);
    	this.Notification.setDisable(true);
    	this.Profile.getItems().get(1).setDisable(true);
    	this.Profile.getItems().get(2).setDisable(true);
    	this.Profile.getItems().get(3).setDisable(true);
    	this.isGuest = true;
    }
    
	public void GuestToHome(UserList user_List, Group_List g_List2, Information_List info_List2) {
		this.userList = user_List;
		this.G_List = g_List2;
		this.Info_List = info_List2;
	}

	private FXMLLoader sceneSwitch(String url, String title) throws IOException {
		FXMLLoader Loader = new FXMLLoader(getClass().getResource(url));
        Parent home_pane = Loader.load();
        Stage stage = (Stage) Profile.getScene().getWindow();
        stage.setScene(new Scene(home_pane));
        stage.setTitle(title);
        return Loader;
	}
	
	public void AccountToHome(UserList ul, Information_List il, Client client, Group_List gl) {
		this.userList = ul;
		this.Info_List = il;
		this.target = client;
		this.G_List = gl;
		if(this.target instanceof SU)
			SUMode();
	}
	
	public void GroupToHome(Group_Status target2, Group group2, Information_List info_List2, UserList user_List,
			Group_List g_List2) {
		this.G_List = g_List2;
		this.target = target2.getUser();
		this.Info_List = info_List2;
		this.userList = user_List;
	}
	
	public void GroupToUser(Client visitor, UserList user_List, Group_List g_List2, Information_List info_List2) {
		this.target = visitor;
		this.userList = user_List;
		this.G_List = g_List2;
		this.Info_List = info_List2;
	}
    //  ******************************************************************************************************************
    
//  List three top members
    private void ListTopMember() {
    	int HighestThreeIndex[] = new int[3];
    	HighestThreeIndex[0] = 1;
    	for(int i = 2; i < this.userList.getAll_Size(); ++i) {
    		if(this.userList.getAll_User().get(i).getReputationScore() > this.userList.getAll_User().get(HighestThreeIndex[0]).getReputationScore()) {
    			HighestThreeIndex[1] = HighestThreeIndex[0];
    			HighestThreeIndex[2] = HighestThreeIndex[1];
    			HighestThreeIndex[0] = i;
    		}
    		else if(this.userList.getAll_User().get(i).getReputationScore() > this.userList.getAll_User().get(HighestThreeIndex[1]).getReputationScore()) {
    			HighestThreeIndex[2] = HighestThreeIndex[1];
    			HighestThreeIndex[1] = i;
    		}
    		else if(this.userList.getAll_User().get(i).getReputationScore() > this.userList.getAll_User().get(HighestThreeIndex[2]).getReputationScore()) {
    			HighestThreeIndex[2] = i;
    		}
    	}
    	for(int i = 0; i < 3; ++i) {
    		this.Members.getItems().get(i).setText(this.userList.getAll_User().get(HighestThreeIndex[i]).getName());
    		inspectContent(this.Members.getItems().get(i), this.userList.getAll_User().get(HighestThreeIndex[i]), 
    				screen.getWidth()/2.5*0.4, screen.getHeight()/1.5*0.4);
    	}
    }

//  Inspect content
    private void inspectContent(MenuItem menuItem, Client client, double w, double h) {
    	menuItem.setOnAction(e->{    		
    		Stage mainScene = (Stage) scrollPane.getScene().getWindow();
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
    			
    			if(i == 0) 		value.setText(client.getName());
    			else if(i == 1) value.setText(client.getID());
    			else if(i == 2) value.setText(client.getPosition());
    			else			value.setText(client.getEmail());
    		}	
    		
    		pane.getChildren().add(gridPane);
    		//Call new window
    		NewWindow(newWindow, secondScene, mainScene, "Information");  
    	});
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
   
    //Get optional alert
    private Alert getAlert(AlertType at, String content, ButtonType bt1, ButtonType bt2, String title) {
		Alert alert = new Alert(AlertType.INFORMATION,
				content,
		        bt1,
		        bt2);

		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setX(screen.getWidth()*0.7);
		alert.setY(screen.getHeight()*0.2);
		return alert;
    }
    
    //Get the current time
    private String getCurrentTime() {
    	DateFormat dateFormat =  new SimpleDateFormat("MM/dd/yyyy");
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
    
    //Check invalid ID
    private boolean isValidID(String id) {
    	boolean result = false;
    	for(int i = 0; i < this.userList.getAll_Size(); ++i) {
    		//The target ID can't be your own id.
    		if(this.target.getID().compareTo(id) != 0 && this.userList.getAll_User().get(i).getID().compareTo(id) == 0) {
    			result = true;
    			break;
    		}
    	}
    	//Check whether the target id is in your blacklist
    	for(int i = 0; i < this.Info_List.getInfo_Con().get(UserIndex).getPersonal_Blacklist().size(); ++i) {
    		if(id.compareTo(this.Info_List.getInfo_Con().get(UserIndex).getPersonal_Blacklist().get(i).getID()) == 0)
    			result = false;
    	}
    	return result;
    }
    
    private void incScore(String name, int score) {
    	for(int i = 0; i < userList.getAll_Size(); ++i) {
    		if(userList.getAll_User().get(i).getName().compareTo(name) == 0)
    			userList.getAll_User().get(i).addReputationScore(score);
    	}
    }
    
    @SuppressWarnings("unused")
	private void decScore(String name, int score) {
    	for(int i = 0; i < userList.getAll_Size(); ++i) {
    		if(userList.getAll_User().get(i).getName().compareTo(name) == 0)
    			userList.getAll_User().get(i).subReputationScore(score);
    	}
    }

    private int getUserIndex() {
    	int result = 0;
    	for(int i = 0; i < this.Info_List.getInfo_Con().size(); ++i) {
    		if(this.target.getID().compareTo(this.Info_List.getInfo_Con().get(i).getID()) == 0) {
    			this.emailNum = this.Info_List.getInfo_Con().get(i).getEmail_Content().size();
    			this.messageNum = this.Info_List.getInfo_Con().get(i).getMessage_Content().size();
    			this.notificationNum = this.Info_List.getInfo_Con().get(i).getNotice().size();
    			return i;
    		}
    	}
    	return result;
    }
  
    //Check the invalidation of email receiver
    private boolean isValidEmail(String email) {
    	for(int i = 0; i < this.userList.getAll_Size(); ++i) {
    		if(this.target.getEmail().compareTo(email) != 0 && 
    				this.userList.getAll_User().get(i).getEmail().compareTo(email) == 0) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private boolean isValidGroupID(String ID) {
    	for(int i = 0; i < this.G_List.getGroup_List().size(); ++i) {
    		if(this.G_List.getGroup_List().get(i).getGroup_ID().compareTo(ID) == 0) {
    			this.GroupIndex = i;
    			return true;
    		}
    	}
    	return false;
    }
    
    private void findGroupIndex() {
    	if(this.G_List.getGroup_List().size() == 0)
    		this.GroupIndex = -1;
    	else {
    		for(int i = 0; i < this.G_List.getGroup_List().size(); ++i) {
    			if(this.G_List.getGroup_List().get(i).isGroupMember(this.target.getID()) && !this.G_List.getGroup_List().get(i).isClose()) {
    				this.GroupIndex = i;
    				break;
    			}
    		}    		
    	}
    }

    private boolean newIDCheck(String id) {
    	for(int i = 0; i < this.userList.getAll_Size(); ++i) {
    		if(this.userList.getAll_User().get(i).getID().compareTo(id) == 0) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private void getNewGroup(String title, String description) {
    	Random rand = new Random();
    	String newID;
    	boolean result;
    	do {
    		newID = "";
    		result = true;
    		for(int i = 0; i < 4; ++i) {
    			int digit = rand.nextInt(10);
    			newID += Integer.toString(digit);	
    		}
    		for(int i = 0; i < this.G_List.getGroup_List().size(); ++i) {
    			if(this.G_List.getGroup_List().get(i).getGroup_ID().compareTo(newID) == 0)
    				result = false;
    		}
    	}while(!result);
    	
    	//Create a new group
    	Group group = new Group();
    	Group_Status GS = new Group_Status(this.target); //Save user to the group
    	group.addGroupMember(GS);
    	group.setGroup_ID(newID);
    	group.setTitle(title);
    	group.setDescription(description);
    	this.G_List.addGroup(group);
    	findGroupIndex(); //Refresh
    }
    
    //Get ID using Email address
    private String getUserID(String email) {
    	for(int i = 0; i < userList.getAll_Size(); ++i) {
    		if(userList.getAll_User().get(i).getEmail().compareTo(email) == 0)
    			return userList.getAll_User().get(i).getID();
    	}
    	return "";
    }

    
}