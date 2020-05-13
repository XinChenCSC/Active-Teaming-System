package application;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;

import Appointment.Appointment;
import Group.Group;
import Clients.Client;
import Clients.Guest;
import Clients.OU;
import Clients.VIP;
import Group.Group_Status;
import Notice.Notice;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class GrouppageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane Anchor_Pane;

    @FXML
    private MenuButton Members;
    
    @FXML
    private ImageView Background;
    
    @FXML
    private TextArea ProjectDescription;

    @FXML
    private TextArea ProjectName;

    @FXML
    private MenuButton Group;
    
    @FXML
    private ScrollPane Scroll_Pane;

    @FXML
    private MenuButton Vote;

    @FXML
    private AnchorPane Scroll_Anchor;
    
    private boolean isGuest = false;
    
    private boolean isVisitor = false;
    
    //------------------------------Essential--------------------------
    private UserList User_List = new UserList();	//All users
    
    private Group_List G_List = new Group_List(); //All groups
    
    private Group group = new Group();	//The target  group
    
    @SuppressWarnings("unused")
	private Guest guest = new Guest();	//The guest
    
    private Information_List Info_List = new Information_List(); // All user datas
    
    private Group_Status target = new Group_Status();
    
    private Client Visitor = new Client();
    
    private Client User = new Client();
    
    private int Info_Index = -1;
    
    private int User_Group_Index = -1;
    
    private int Active_Members = -1;
    
    private int Group_Index = -1;
    
    private int User_List_Index = -1;
    
    //window size
    private Rectangle2D screen = Screen.getPrimary().getVisualBounds(); 
    
    private final double width = screen.getWidth()*0.2;
    
    private final double height = screen.getHeight()*0.5;
    
    //Vote types
    private final String[] voting_tags = {"Dismissal", "Warning", "Praise", "Closure", "Eva. exit", "Schedule"};
    
    //Your vote
//    private boolean[] votingStatus = {true, true, true, true, true, true};
    
    @FXML
    void initialize()throws IOException {
    	//Resize the background image
        Background.setLayoutX(screen.getWidth());
        Background.setLayoutY(screen.getHeight()); 
    }
 
    //Back to homepage
    @FXML
    private void moveToHomepage(ActionEvent event) throws IOException {
    	loadHomepage();
    }

    //display poll status
    private void pollStatus() {
    	findUserGroupIndex(); //Refresh
    	ButtonType ok = new ButtonType("Ok", ButtonData.OK_DONE);
    	Dialog<ButtonType> dialog = new Dialog<>(); // New dialog
    	Pane pane = new Pane();	// New pane
    	//Title
    	Label title = getHeaderText("Poll Status");
    	title.setAlignment(Pos.CENTER);
    	//Scroll Pane
    	ScrollPane scrollPane = new ScrollPane();
    	getSetting(scrollPane, width, height*0.75, 0, height*0.1+20);
    	scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
    	scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    	//GridPane
    	GridPane gridPane = new GridPane();
    	gridPane.setPadding(new Insets(10,20,10,20));
        gridPane.getColumnConstraints().add(new ColumnConstraints(width*0.4-20));
        gridPane.getColumnConstraints().add(new ColumnConstraints(width*0.6-20));
        gridPane.setVgap(10);
        for(int i = 0; i < voting_tags.length; ++i) {
            //Vote type
            Label voteType = new Label("Type: " + voting_tags[i]);
            voteType.setStyle("-fx-background-color:#DAA520;");
            voteType.prefWidthProperty().bind(gridPane.getColumnConstraints().get(0).prefWidthProperty());
            gridPane.getRowConstraints().add(new RowConstraints(height*0.07));
            GridPane.setHalignment(voteType, HPos.CENTER);
         	gridPane.add(voteType, 0, i*2);
         	//Target
         	Label target = new Label("Target: " + this.group.findTarget(i));
         	if(i == 3 || i == 5) 
         		target.setText("");
         	target.prefWidthProperty().bind(gridPane.getColumnConstraints().get(1).prefWidthProperty());
            target.setStyle("-fx-background-color:#DAA520;");
            GridPane.setHalignment(target, HPos.CENTER);
            gridPane.add(target, 1, i*2);
            
        	//Progress bar
            ProgressBar progress = new ProgressBar();
            //Read the current votes from the group database, ignore any members that have already been kicked.
            double value = 0.0;
            if(i == 3 || i == 5) {
            	for(int d = 0; d < this.group.getA_Group().size(); ++d) {
            		if(this.group.findPollStatus(i, d) == 1 && this.group.getA_Group().get(d).isCondition())
            			value += (double)2.0/this.Active_Members;
            	}            	
            }
            else {
            	for(int d = 0; d < this.group.getA_Group().size(); ++d) {
            		if(this.group.findPollStatus(i, d) == 1 && this.group.getA_Group().get(d).isCondition())
            			value += (double)2.0/(this.Active_Members-1);
            	}  
            }
            
            progress.setProgress(value);
            progress.setPrefWidth(width*0.5);
            progress.setPadding(new Insets(0,40,0,0));
            progress.setId("AgreeVotes");
            GridPane.setHalignment(progress, HPos.CENTER);
            
            //Indicator
            Label agreedVotes = new Label("Click me");
            //If vote not activate  or you already vote or the voting target is you , disable the button
            if(!this.group.getVT(i) || this.group.findPollStatus(i, this.User_Group_Index) == 1 || 
            		this.User.getID().compareTo(this.group.getTargetID(i)) == 0)
            	agreedVotes.setDisable(true);
            
            agreedVotes.setPadding(new Insets(0, 0, 0, 10));
            GridPane.setHalignment(agreedVotes, HPos.CENTER);
            gridPane.getRowConstraints().add(new RowConstraints(height*0.07));
            
            //If poll is activted and you are not make a vote yet
            if(this.group.getVT(i) && this.group.findPollStatus(i, this.User_Group_Index) == -1 && 
            		this.User.getID().compareTo(this.group.getTargetID(i)) != 0) {
            	getVotingAlert(i, agreedVotes, progress, (Stage) dialog.getDialogPane().getScene().getWindow());
            }
            //Set children
            gridPane.add(agreedVotes, 0, i*2+1);
            gridPane.add(progress, 1, i*2+1);    
        }
        //Set children
        scrollPane.setContent(gridPane);
        pane.getChildren().addAll(title, scrollPane);
        //Import css file
        pane.getStylesheets().add(getClass().getResource("GroupPage.css").toExternalForm());
        //Create new window
        @SuppressWarnings("unused")
		Optional<ButtonType> result = getDialog(dialog, pane, "Poll Status", "", ok);
    }

    //Votes
    @SuppressWarnings("unused")
	private void VotingPane() {
    	ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    	//GridPane
    	GridPane gridPane = new GridPane();
    	gridPane.setPadding(new Insets(height*0.05, width*0.1, height*0.05, width*0.1));
    	gridPane.getColumnConstraints().add(new ColumnConstraints(width*0.8));
        gridPane.setVgap(10);
        //Warning string
    	String str = "One vote at a time!";
     	Label instruction = new Label(str);
     	instruction.setPrefSize(width*0.8, height*0.2);
     	instruction.setAlignment(Pos.CENTER);
     	instruction.setStyle("-fx-background-color:#DAA520;");
     	gridPane.getRowConstraints().add(new RowConstraints(50));
     	gridPane.add(instruction, 0, 0);
     	
     	//Choose vote type label
     	Label voteType = new Label("Vote types");
     	voteType.setAlignment(Pos.CENTER);
     	GridPane.setHalignment(voteType, HPos.CENTER);
     	gridPane.getRowConstraints().add(new RowConstraints(30));
     	gridPane.add(voteType, 0, 1);
     	
     	//Choose vote type
     	ComboBox<String> vote = new ComboBox<>();
     	for(int i = 0; i < this.voting_tags.length-1; ++i)
     		vote.getItems().add(this.voting_tags[i]);
     	GridPane.setHalignment(vote, HPos.CENTER);
     	gridPane.getRowConstraints().add(new RowConstraints(30));
     	gridPane.add(vote, 0, 2);
     	
     	//Name label
     	Label pickName = new Label("Choose the name");
     	pickName.setAlignment(Pos.CENTER);
     	GridPane.setHalignment(pickName, HPos.CENTER);
     	gridPane.getRowConstraints().add(new RowConstraints(30));
     	gridPane.add(pickName, 0, 3);
     	
     	//Name
     	ComboBox<String> name = new ComboBox<String>();
     	for(int i = 0; i < this.group.getA_Group().size(); ++i) 
     		if(this.group.getA_Group().get(i).isCondition() && this.group.getA_Group().get(i).getUser() != this.User)
     			name.getItems().add(this.group.getA_Group().get(i).getUser().getName());
     	GridPane.setHalignment(name, HPos.CENTER);
     	gridPane.getRowConstraints().add(new RowConstraints(30));
     	gridPane.add(name, 0, 4);
    	
     	//Reason label
     	Label writeReason = new Label("Reason");
     	writeReason.setAlignment(Pos.CENTER);
     	GridPane.setHalignment(writeReason, HPos.CENTER);
     	gridPane.getRowConstraints().add(new RowConstraints(30));
     	gridPane.add(writeReason, 0, 5);
     	
     	//Reason area
     	TextArea reasonArea = new TextArea();
     	GridPane.setHalignment(reasonArea, HPos.CENTER);
     	gridPane.getRowConstraints().add(new RowConstraints(130));
     	gridPane.add(reasonArea, 0, 6);
     	
     	//Disable Name label and Name combobox when vote for closure
     	vote.setOnAction(e->{
     		if(vote.getSelectionModel().getSelectedItem().toString().compareTo("Closure") == 0) {
     			pickName.setDisable(true);
         		name.setDisable(true);
     		}
     		else {
     			pickName.setDisable(false);
         		name.setDisable(false);
     		}
     	});
     	//Submit button
     	Button submit = new Button("Submit");
     	submit.setOnAction(e->{
     		if(reasonArea.getText().isEmpty() || name.getSelectionModel().getSelectedItem() == null ||
     				vote.getSelectionModel().getSelectedItem() == null)
     			getAlert(AlertType.WARNING, "All inputs need to be valid.", ButtonType.OK, "Caution!");
     		else {
     			if(vote.getSelectionModel().getSelectedItem().toString().compareTo(voting_tags[0]) == 0) {
     				if(getConfirmationAlert("Are you sure to dismiss " + name.getSelectionModel().getSelectedItem() +
     			"?", "Confirm", (Stage) gridPane.getScene().getWindow(),
     						"Wait for the responds from other group members.")) {
           				
     					//Send a notification to all group member except kicked out member
     					Notice notification = new Notice(true, getCurrentDate(), "(" + voting_tags[0] + " poll) activated.");
     	        		for(int i = 0; i < this.group.getA_Group().size(); ++i) {
     	        			if(this.group.getA_Group().get(i).isCondition())	//No notification for kicked member
     	        				this.Info_List.CreateNotice(this.group.getA_Group().get(i).getUser().getID(), notification);
     	        		}
     	        		
       					//Initialize poll status
       					this.G_List.getGroup_List().get(Group_Index).PollInitialization(0);
       					//Activate the schedule vote
       					this.G_List.getGroup_List().get(Group_Index).addVoteTypes(0, true);
       					//Add target user
       					this.G_List.getGroup_List().get(Group_Index).addTarget(0, findTarget(name.getSelectionModel().getSelectedItem()));
     				}
     			}
     			else if(vote.getSelectionModel().getSelectedItem().toString().compareTo(voting_tags[1]) == 0) {
     				if(getConfirmationAlert("Are you sure to issue a warning to " + name.getSelectionModel().getSelectedItem() +
     		     			"?", "Confirm", (Stage) gridPane.getScene().getWindow(), "Wait for the responds from other group members.")) {
           				
     					//Send a notification to all group member except kicked out member
     					Notice notification = new Notice(true, getCurrentDate(), "(" + voting_tags[1] + " poll) activated.");
     	        		for(int i = 0; i < this.group.getA_Group().size(); ++i) {
     	        			if(this.group.getA_Group().get(i).isCondition())	//No notification for kicked member
     	        				this.Info_List.CreateNotice(this.group.getA_Group().get(i).getUser().getID(), notification);
     	        		}
     	        		
       					//Initialize poll status
       					this.G_List.getGroup_List().get(Group_Index).PollInitialization(1);
       					//Activate the schedule vote
       					this.G_List.getGroup_List().get(Group_Index).addVoteTypes(1, true);
       					//Add target user
       					this.G_List.getGroup_List().get(Group_Index).addTarget(1, findTarget(name.getSelectionModel().getSelectedItem()));
     				}
     			}
     			else if(vote.getSelectionModel().getSelectedItem().toString().compareTo(voting_tags[2]) == 0) {
     				if(getConfirmationAlert("Are you sure to give " + name.getSelectionModel().getSelectedItem()
     							+ " a pat on the back?", "Confirm", (Stage) gridPane.getScene().getWindow(),
     						"Wait for the responds from other group members.")) {
           				
     					//Send a notification to all group member except kicked out member
     					Notice notification = new Notice(true, getCurrentDate(), "(" + voting_tags[2] + " poll) activated.");
     	        		for(int i = 0; i < this.group.getA_Group().size(); ++i) {
     	        			if(this.group.getA_Group().get(i).isCondition())	//No notification for kicked member
     	        				this.Info_List.CreateNotice(this.group.getA_Group().get(i).getUser().getID(), notification);
     	        		}
     	        		
       					//Initialize poll status
       					this.G_List.getGroup_List().get(Group_Index).PollInitialization(2);
       					//Activate the schedule vote
       					this.G_List.getGroup_List().get(Group_Index).addVoteTypes(2, true);
       					//Add target user
       					this.G_List.getGroup_List().get(Group_Index).addTarget(2, findTarget(name.getSelectionModel().getSelectedItem()));
     				}
     			}
     			
     			else if(vote.getSelectionModel().getSelectedItem().toString().compareTo(voting_tags[3]) == 0) {
     				if(getConfirmationAlert("Are you sure to close the group?", "Confirm", (Stage) gridPane.getScene().getWindow(),
     						"Wait for the responds from other group members.")) {
           				
     					//Send a notification to all group member except kicked out member
     					Notice notification = new Notice(true, getCurrentDate(), "(" + voting_tags[3] + " poll) activated.");
     	        		for(int i = 0; i < this.group.getA_Group().size(); ++i) {
     	        			if(this.group.getA_Group().get(i).isCondition())	//No notification for kicked member
     	        				this.Info_List.CreateNotice(this.group.getA_Group().get(i).getUser().getID(), notification);
     	        		}
     	        		
       					//Initialize poll status
       					this.G_List.getGroup_List().get(Group_Index).PollInitialization(3);
       					//Activate the schedule vote
       					this.G_List.getGroup_List().get(Group_Index).addVoteTypes(3, true);
     				}
     			}
     			
     			else {
     				if(getConfirmationAlert("Are you sure to exit UNKNOWN's evaluation?", "Confirm", (Stage) gridPane.getScene().getWindow(),
     						"Wait for the responds from other group members.")) {
           				
     					//Send a notification to all group member except kicked out member
     					Notice notification = new Notice(true, getCurrentDate(), "(" + voting_tags[4] + " poll) activated.");
     	        		for(int i = 0; i < this.group.getA_Group().size(); ++i) {
     	        			if(this.group.getA_Group().get(i).isCondition())	//No notification for kicked member
     	        				this.Info_List.CreateNotice(this.group.getA_Group().get(i).getUser().getID(), notification);
     	        		}
     	        		
       					//Initialize poll status
       					this.G_List.getGroup_List().get(Group_Index).PollInitialization(4);
       					//Activate the schedule vote
       					this.G_List.getGroup_List().get(Group_Index).addVoteTypes(4, true);
       					//Add target user
       					this.G_List.getGroup_List().get(Group_Index).addTarget(4, findTarget(name.getSelectionModel().getSelectedItem()));
     				}
     			}
     		}
     	});
     	
     	GridPane.setHalignment(submit, HPos.CENTER);
     	gridPane.getRowConstraints().add(new RowConstraints(50));
     	gridPane.add(submit, 0, 7);
     	
     	//Get dialog
      	Dialog<ButtonType> dialog = new Dialog<>();
		Optional<ButtonType> result = getDialog(dialog, gridPane, "Vote", "", cancel);

    }
    
    //Display member's info
    @SuppressWarnings("unused")
	private BorderPane infoDisplay(Group_Status member) {    
      	 ButtonType ok = new ButtonType("OK",ButtonData.OK_DONE);
      	 //BorderPane
      	 BorderPane borderPane = new BorderPane();
      	 //Title
      	 Label title = getHeaderText("Information");
      	 title.setAlignment(Pos.CENTER);
      	 borderPane.setTop(title);
      	 
    	 //GridPane
    	 GridPane gridPane = new GridPane();
    	 //Import css file
    	 gridPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	 gridPane.getColumnConstraints().add(new ColumnConstraints(width*0.4));
    	 gridPane.getColumnConstraints().add(new ColumnConstraints(width*0.4));
    	 gridPane.setVgap(10);    	 
    	 
    	 String[] tags = {"Name:", "ID:", "Position:"};
    	 //list member's informations
    	 for(int i = 0; i < tags.length; ++i) {
    		 Label tag = new Label(tags[i]);
    		 GridPane.setHalignment(tag, HPos.CENTER);
        	 gridPane.getRowConstraints().add(new RowConstraints(height*0.1));
    		 gridPane.add(tag, 0, i);
    		 
    		 Label value = new Label();
    		 
    		 if(i == 0)   		value.setText(member.getUser().getName());
    		 else if(i == 1) 	value.setText(member.getUser().getID());
    		 else 				value.setText(member.getUser().getPosition());
    		 
    		 GridPane.setHalignment(value, HPos.CENTER);
    		 gridPane.add(value, 1, i);
    	 }
    	 //Evaluation
    	 Label evaluation = new Label("Evaluation: ");
    	 GridPane.setHalignment(evaluation, HPos.CENTER);
    	 gridPane.getRowConstraints().add(new RowConstraints(height*0.2));
    	 gridPane.add(evaluation, 0, tags.length);
    	 
    	 //Evaluation contents
    	 TextArea textArea = new TextArea();
    	 
    	 //If his/her evaluation is closed
    	 if(!member.isEvaluation()) {
    		 evaluation.setDisable(true);
    		 textArea.setDisable(true);
    	 }
    	 else {
        	 if(member.getEvaluation().isEmpty())
        		 textArea.setPromptText("Empty");
        	 else
        		 textArea.setText(member.getEvaluation());
    	 }
    	 
    	 GridPane.setHalignment(textArea, HPos.CENTER);
    	 GridPane.setValignment(textArea, VPos.CENTER);
    	 gridPane.add(textArea, 1, tags.length);
    	 
    	 //Set children
    	 borderPane.setCenter(gridPane);
    	 BorderPane.setMargin(gridPane, new Insets(height*0.05, width*0.1, height*0.05, width*0.1));
    	 //Call dialog
       	 Dialog<ButtonType> dialog = new Dialog<>();
       	 Optional<ButtonType> result = getDialog(dialog, borderPane, "Information", "", ok);
       	 return borderPane;
    }
        
    //Display personal status
    private void statusDisplay() {
     ButtonType ok = new ButtonType("OK",ButtonData.OK_DONE);
     BorderPane pane = new BorderPane();
   	 //GridPane
   	 GridPane gridPane = new GridPane();
   	 gridPane.setPadding(new Insets(30,10,10,10));
   	 gridPane.getColumnConstraints().add(new ColumnConstraints(width*0.5-10));
   	 gridPane.getColumnConstraints().add(new ColumnConstraints(width*0.5-10));
   	 
   	 //Create title
   	 Label title = getHeaderText("Status");
   	 pane.setTop(title);
 	
   	 final String[] tags = {"Attendances:", "Assignments:", "Warnings:", "Praises:", "Condition:", "Evaluation: "};
   	 //list member's informations
   	 for(int i = 0; i < tags.length; ++i) {
   		 Label tag = new Label(tags[i]);
   		 GridPane.setHalignment(tag, HPos.CENTER);
       	 gridPane.getRowConstraints().add(new RowConstraints(height*0.1));
   		 gridPane.add(tag, 0, i);
     	
   		 Label value = new Label("Unknown");
   		 GridPane.setHalignment(value, HPos.CENTER);
   		 gridPane.add(value, 1, i);
   		 
   		 if(i == 0)	 		value.setText(Integer.toString(this.target.getAttendances()));
   		 else if(i == 1) 	value.setText(Integer.toString(this.target.getAssignments()));
   		 else if(i == 2)  	value.setText(Integer.toString(this.target.getWarnings()));
   		 else if(i == 3) 	value.setText(Integer.toString(this.target.getPraises()));
   		 else if(i == 4 || i == 5) {
   			 value.setStyle("-fx-background-radius: 20;" +
   					 "-fx-border-radius: 20;");
   			 if(i == 4) {
   				 if(this.target.isCondition()) {
   					 value.setText("Engaging");
   					 value.setStyle("-fx-background-color: #ADFF2F;");
   				 }
   				 else {
   					 value.setText("Kicked");
   					value.setStyle("-fx-background-color: #D3D3D3;");
   				 }
   			 }
   			 else {
   				if(this.target.isEvaluation()) {
   					value.setText("On");
   					value.setStyle("-fx-background-color: #ADFF2F;");
   				}
  				 else {
  					 value.setText("Off");
  					value.setStyle("-fx-background-color: #D3D3D3;");
  				 }
   			 }
   		 }
   	 }
   	 
   	 pane.setCenter(gridPane);
   	 //Call dialog
   	 Dialog<ButtonType> dialog = new Dialog<>();
   	 @SuppressWarnings("unused")
   	 Optional<ButtonType> result = getDialog(dialog, pane, "Status", "", ok);
    }
    
    //Schedule a meeting
    private void scheduleAMeeting() {
      	 ButtonType cancel = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
      	 Pane pane = new Pane();
      	 //Title 
      	 Label title = getHeaderText("Schedule");
      	 title.setAlignment(Pos.CENTER);
      	 getSetting(title, width, height*0.1, 0, 0);
      	 //String 
      	 final String str = "Select your meeting time and submit by click the submit button. "
      	 		+ "The meeting time will be approved when all group members are agreed. The date can't be the current day!";
      	 //Instruction
      	 Label instruction = new Label(str);
      	 instruction.setPadding(new Insets(10,10,10,10));
      	 instruction.setWrapText(true);
      	 instruction.setStyle("-fx-background-color:#DAA520;");
      	 getSetting(instruction, width, height*0.25, 0, height*0.1);
      	 
      	 //Select meeting time  
         GridPane gridPane = new GridPane();
         gridPane.setPadding(new Insets(20,0,0,0));
         getSetting(gridPane, width, height*0.6, 0, height*0.35);
         gridPane.getColumnConstraints().add(new ColumnConstraints(width));
         gridPane.setHgap(10);
         gridPane.setVgap(10);
         
         Label dataTitle = new Label("Choose the date");
         gridPane.getRowConstraints().add(new RowConstraints(height*0.05));
         GridPane.setHalignment(dataTitle, HPos.CENTER);
         gridPane.add(dataTitle, 0, 0);
         
         DatePicker DatePicker = new DatePicker();
         gridPane.getRowConstraints().add(new RowConstraints(height*0.05));
         GridPane.setHalignment(DatePicker, HPos.CENTER);
         gridPane.add(DatePicker, 0, 1);
         
         Label duration = new Label("Duration (Hours)");
         gridPane.getRowConstraints().add(new RowConstraints(height*0.05));
         GridPane.setHalignment(duration, HPos.CENTER);
         gridPane.add(duration, 0, 2);
         
         //All hour options     
         ComboBox<String> durationTime = new ComboBox<String>();
         getSetting(durationTime, width*0.5, height*0.05, 0, 0);
         gridPane.getRowConstraints().add(new RowConstraints(height*0.05));
         durationTime.getItems().addAll("1", "2", "3", "4", "5", "6");
         GridPane.setHalignment(durationTime, HPos.CENTER);
         gridPane.add(durationTime, 0, 3);
         
         //Starting time
         Label starting = new Label("Starting time");
         gridPane.getRowConstraints().add(new RowConstraints(height*0.05));
         GridPane.setHalignment(starting, HPos.CENTER);
         gridPane.add(starting, 0, 4);
         
         ComboBox<String> startingTime = new ComboBox<String>();
         gridPane.getRowConstraints().add(new RowConstraints(height*0.05));
         getSetting(startingTime, width*0.5, height*0.05, 0, 0);
         startingTime.getItems().addAll("9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM",
        		 					"3:00 PM", "4:00 PM");
         GridPane.setHalignment(startingTime, HPos.CENTER);
         gridPane.add(startingTime, 0, 5);
         
         //Submit
         Button submit = new Button("Submit");
         getSetting(submit, width*0.2, height*0.05, 0, 0);
         GridPane.setHalignment(submit, HPos.CENTER);
         GridPane.setValignment(submit, VPos.BOTTOM);
         gridPane.getRowConstraints().add(new RowConstraints(height*0.1));
         gridPane.add(submit, 0, 6);
         
         //Submit Event
      	 submit.setOnAction(e->{
      		 //Get the current time
      		 String currentDate = getCurrentDate();
      		 //If wrong inputs
      		 if (durationTime.getSelectionModel().getSelectedItem() == null || 
      				 startingTime.getSelectionModel().getSelectedItem() == null ||
      				 DatePicker.getValue() == null ||
      				 DatePicker.getValue().toString().compareTo(currentDate) < 0 ||
      				 !isValidDay(DatePicker.getValue().toString()) ||
      				 isWorking()) 
      			 getAlert(AlertType.WARNING, "Fail to make a schedule.", ButtonType.OK, "Caution!");
      		 //If correct inputs
      		 else {
      			 
       			String confirmedTime = "Date: At " + startingTime.getSelectionModel().getSelectedItem() + 
       							" " + DatePicker.getValue().toString() + 
       							"\nDuration: " + durationTime.getSelectionModel().getSelectedItem() + " hours" ;
       			
       			if(getConfirmationAlert(confirmedTime, "Scheduled Time",(Stage) pane.getScene().getWindow(),
       					"Wait for the responds from other group members.")) {
       				//Create an appointment
       				Appointment appointment = new Appointment(DatePicker.getValue().toString() + " " + startingTime.getSelectionModel().getSelectedItem(),
       						durationTime.getSelectionModel().getSelectedItem());
       				
       				//Send a notification to all group member except kicked out member
 					Notice notification = new Notice(true, getCurrentDate(), "(" + voting_tags[4] + " poll) activated.");
 					
       				//Set all pending appointment and notification to each user's information database except kicked out member
       				for(int i = 0; i < this.group.getA_Group().size(); ++i) {
       					if(this.group.getA_Group().get(i).isCondition()) {
       						this.Info_List.addAppointment(this.group.getA_Group().get(i).getUser().getID(), appointment);  
       						this.Info_List.CreateNotice(this.group.getA_Group().get(i).getUser().getID(), notification);
       					}
       				}
   					//Initialize poll status
   					this.G_List.getGroup_List().get(Group_Index).PollInitialization(5);
   					//Activate the schedule vote
   					this.G_List.getGroup_List().get(Group_Index).addVoteTypes(5, true);  
       			}
      		 }
      	 });
      	 //Set children
      	 pane.getChildren().addAll(title, instruction, gridPane);
      	 //Call dialog
       	 Dialog<ButtonType> dialog = new Dialog<>();
       	 @SuppressWarnings("unused")
       	 Optional<ButtonType> result =  getDialog(dialog, pane, "Schedule a meeting", "", cancel);
      	
    }
    
    //Final window before close the group
    private void finalWindow(Stage stage) throws IOException {
    	ButtonType finish = new ButtonType("Finish", ButtonData.CANCEL_CLOSE);
    	Pane pane = new Pane();
    	//Set up components
    	final String str = "Once you close the current window, the group will no longer exist and you will be " +
    			"transferred to the homepage.(Default value of evaluation is 0, and only can be submitted once.))";
    	//Instruction label
    	Label instruction = new Label(str);
    	getSetting(instruction, width, height*0.25, 0, 0);
    	instruction.setPadding(new Insets(10,10,10,10));
    	instruction.setWrapText(true);
     	instruction.setStyle("-fx-background-color:#DAA520;");
     	
     	//Add to blacklist or to whitebox
     	GridPane gridPane = new GridPane();
     	getSetting(gridPane, width, height*0.7, 0, height*0.25);
     	gridPane.setPadding(new Insets(10,0,0,0));
     	gridPane.getColumnConstraints().add(new ColumnConstraints(width));
     	
     	//ComboBox for members
     	ComboBox<String> members = new ComboBox<>();
     	members.setPromptText("Members");
     	
     	//add item to the combobox
     	for(int i = 0; i < this.group.getA_Group().size(); ++i)
     		if(this.group.getA_Group().get(i).isCondition() && this.group.getA_Group().get(i).getUser().getID().compareTo(this.User.getID()) != 0) //Kicked out member will show up
     			members.getItems().add(this.group.getA_Group().get(i).getUser().getName());
     	gridPane.getRowConstraints().add(new RowConstraints(height*0.1));
     	GridPane.setHalignment(members, HPos.CENTER);
     	gridPane.add(members, 0, 0);
     	
     	//ComboBox for blacklist or whitebox
     	ComboBox<String> field = new ComboBox<>();
     	field.setPromptText("W/B");
     	field.getItems().addAll("Whitebox", "Blacklist");
     	gridPane.getRowConstraints().add(new RowConstraints(height*0.1));
     	GridPane.setHalignment(field, HPos.CENTER);
     	gridPane.add(field, 0, 1);
     	
     	//Textarea for reason
     	TextArea reason = new TextArea();
     	reason.setPromptText("Reason");
     	reason.setMaxSize(width*0.7, height*0.25);
     	gridPane.getRowConstraints().add(new RowConstraints(height*0.2));
     	GridPane.setValignment(reason, VPos.BOTTOM);
     	GridPane.setHalignment(reason, HPos.CENTER);
     	gridPane.add(reason, 0, 2);
     	     	
     	//Group evaluation
     	ComboBox<Integer> evaluation = new ComboBox<>();
     	evaluation.setPromptText("Evaluation");
     	evaluation.setPrefWidth(width*0.4);
     	evaluation.getItems().addAll(1,2,3,4,5);
     	gridPane.getRowConstraints().add(new RowConstraints(height*0.1));
     	GridPane.setValignment(evaluation, VPos.BOTTOM);
     	GridPane.setHalignment(evaluation, HPos.CENTER);
     	gridPane.add(evaluation, 0, 3);
     	
     	//Submit button
     	Button sumbit = new Button("Submit");
     	gridPane.getRowConstraints().add(new RowConstraints(height*0.15));
     	GridPane.setHalignment(sumbit, HPos.CENTER);
     	gridPane.add(sumbit, 0, 4);
     	
     	//Submit event
     	sumbit.setOnAction(e->{
     		if((!members.getSelectionModel().isEmpty() &&
     				!field.getSelectionModel().isEmpty() &&
     				!reason.getText().isEmpty())) {
     			getAlert(AlertType.CONFIRMATION, "Submission completed", ButtonType.OK, "Result");
     			if(field.getSelectionModel().getSelectedItem().compareTo("Whitebox") == 0) {
     				addToWhitebox(members.getSelectionModel().getSelectedItem());
     			}
     			else {
     				addToBlackbox(members.getSelectionModel().getSelectedItem());
     			}
     			members.getSelectionModel().clearSelection(members.getSelectionModel().getSelectedIndex()); //Remove  member
     		}
     		else if(!evaluation.isDisable()) {
     			getAlert(AlertType.CONFIRMATION, "Submission completed", ButtonType.OK, "Result");  
     			evaluation.setDisable(true); //Evaluation can only be sumbitted once.
     			this.group.addEvaluationScore(evaluation.getSelectionModel().getSelectedItem()); //Save scores
     		}
     		else {
     			getAlert(AlertType.ERROR, "Don't submit with nothing. Click FINISH if you are done.", ButtonType.OK, "Error");
     		}
     	});
     	
     	pane.getChildren().addAll(instruction, gridPane); // set children
    	
    	Dialog<ButtonType> dialog = new Dialog<>(); //Create new dialog
    	dialog.initOwner(stage);	//Set poll status dialog as owner
    	Optional<ButtonType> result = getDialog(dialog, pane, "Ending window", "", finish);

    	//Close button event
    	if(result.get() == finish) {
    		if(this.User instanceof OU) {
    			((OU)this.User).setInGroup(false);
    			((OU)this.User).setCreatingGroup(false);
    		}
    		else if(this.User instanceof VIP) {
    			((VIP)this.User).setInGroup(false);
    			((VIP)this.User).setCreatingGroup(false);
    		}
    		this.group.setClose(true);
    		//close poll status dialog
    		stage.close();
    		//Back to home page
    		loadHomepage();
    	}
    }
   
    //Warning alert for schedule
    private void getAlert(AlertType at, String content, ButtonType bt, String title) {
    	Alert alert = new Alert(at, content, bt);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.showAndWait();
    	alert.setX(screen.getWidth()/2);
    	alert.setY(screen.getHeight()/3);
    }
    
    //Confirmation alert for schedule
    private boolean getConfirmationAlert(String content, String title, Stage stage, String result) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, content, ButtonType.YES, ButtonType.NO);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.showAndWait();
    	alert.setX(screen.getWidth()/2);
    	alert.setY(screen.getHeight()/3);
    	
    	//Call for the ending alert
    	if(alert.getResult() == ButtonType.YES) {
    		Alert endingAlert = new Alert(AlertType.CONFIRMATION, result, ButtonType.OK);
    		endingAlert.setTitle("Result");
    		endingAlert.setHeaderText(null);
    		endingAlert.showAndWait();
    		endingAlert.setX(screen.getWidth()/2);
        	endingAlert.setY(screen.getHeight()/3);
        	stage.close();
        	return true;
    	}
    	return false;
    }
    
    //Get voting alert
    //If the value of progress bar is 1 and index is 3 then call the finalWindow method
    private void getVotingAlert(int index, Label agreedVotes, ProgressBar progress, Stage stage) {
    	findUserGroupIndex(); //Refresh
//    	agreedVotes.setDisable(false);
    	ButtonType reject = new ButtonType("Reject", ButtonData.FINISH);
    	ButtonType agree = new ButtonType("Agree", ButtonData.OK_DONE);
    	agreedVotes.setOnMouseClicked(e->{
        	Alert alert = new Alert(AlertType.CONFIRMATION, 
        			"Vote for agree or vote for rejection?", reject, agree);
        	alert.setTitle("Confirmation");
        	alert.setHeaderText(null);
        	
        	//check click button types
        	Optional<ButtonType> result = alert.showAndWait();
        	if(result.get() == reject) {
        		getAlert(AlertType.CONFIRMATION, "The poll didn't pass because you reject to vote."
        				, ButtonType.OK, "Result");
        		
        		this.group.addVoteTypes(index, false); 	//Deactivate the vote 
        		this.group.PollInitialization(index); //Reset all value to -1
        		this.group.addTarget(index, null);    //Add the target to null
        		//Send a notification to all group members 
        		Notice notification = new Notice(true, getCurrentDate(), "(" + voting_tags[index] + " poll) failed because someone rejected.");
        		for(int i = 0; i < this.group.getA_Group().size(); ++i) {
        			if(this.group.getA_Group().get(i).isCondition())	//No notification for kicked member
        				this.Info_List.CreateNotice(this.group.getA_Group().get(i).getUser().getID(), notification);
        		}
            	//Rejust
            	agreedVotes.setDisable(true);
            	progress.setProgress(0);
        	}
        	else {
        		double value;
        		//Update the value of progress bar
        		if(index == 3 || index == 5) 
        			value = (double)progress.getProgress()+(double)2.0/this.Active_Members;
        		else 
        			value = (double)progress.getProgress()+(double)2.0/(this.Active_Members-1);
        		
        		progress.setProgress(value);
        		
        		//Mark voted for the target user
        		this.group.addAVote(1, index, this.User_Group_Index);
        		
        		agreedVotes.setDisable(true);
        		
        		if(progress.getProgress() == 1.0) {
        			if(index == 3) {
        				try {
        					finalWindow(stage);
        				} catch (IOException e1) {
        					e1.printStackTrace();}        				
        			}
        			else {
                		getAlert(AlertType.CONFIRMATION, "Poll successfully passed!"
                				, ButtonType.OK, "Result");
                		progress.setProgress(0);
                		
                		//Send a notification to all group members 
                		Notice notification = new Notice(true, getCurrentDate(), "(" + voting_tags[index] + " poll) successfully passed!");
                		for(int i = 0; i < this.group.getA_Group().size(); ++i) 
                			if(this.group.getA_Group().get(i).isCondition())	//No notification for kicked member
                				this.Info_List.CreateNotice(this.group.getA_Group().get(i).getUser().getID(), notification);
                		//Confirm the appointment
                		if(index == 5) {
                			for(int i = 0; i < this.group.getA_Group().size(); ++i) {
                				if(this.group.getA_Group().get(i).isCondition())
                					this.Info_List.setAppointmentPending(this.group.getA_Group().get(i).getUser().getID());
                			}
                		}
                		else if(index == 0) {
                			//Kick this member 
                			this.group.getA_Group().get(VoteeIndex(0)).setCondition(false);
                			Client client = this.group.getA_Group().get(VoteeIndex(0)).getUser();
                			if(client instanceof OU)
                				((OU)client).setNeedAppeal(true);
                			else if(client instanceof VIP)
                				((VIP)client).setNeedAppeal(true);
                		}
                		else if(index == 1) {
                			//Give a warning
                			this.group.getA_Group().get(VoteeIndex(1)).addWarnings();
                		}
                		else if(index == 2) {
                			//Give a praise
                			this.group.getA_Group().get(VoteeIndex(2)).addPraise();
                		}
                		else {
                			//Close his/her evaluation
                			this.group.getA_Group().get(VoteeIndex(4)).setisEvaluation(false);
                		}
                		this.group.addVoteTypes(index, false); 	//Deactivate the vote 
                		this.group.PollInitialization(index); //Reset all value to -1
                		this.group.addTarget(index, null);    //Add the target to null
                	}
        		}
        	}
    	});
    }

    //Create a dialog
    private Optional<ButtonType> getDialog(Dialog<ButtonType> dialog, Node n, String title, String header, ButtonType bt) {
   	 dialog.getDialogPane().setPrefSize(width, height);
   	 dialog.getDialogPane().getButtonTypes().add(bt);
   	 dialog.setTitle(title);
   	 dialog.setHeaderText(header);
   	 dialog.getDialogPane().getChildren().add(n);
   	 Optional<ButtonType> result = dialog.showAndWait();
   	 return result;
    }
    
    //Set up general setting of component
    private void getSetting(Node n, double w, double h, double x, double y) {
    	((Region) n).setPrefSize(w,h);
    	n.setLayoutX(x);
    	n.setLayoutY(y);
    	n.setFocusTraversable(false);
    }
    
    //Set header text
    private Label getHeaderText(String str) {
      	 Label title = new Label(str);
       	 getSetting(title, width, height*0.15, 0, 0);
       	 title.setAlignment(Pos.CENTER);
       	 title.setFont(Font.font("Verdana",20));
       	 title.setStyle("-fx-background-color: #00BFFF;");
       	 return title;
    }
    
    //Get the current time
    private String getCurrentDate() {
    	DateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
    	Calendar cal = Calendar.getInstance();
    	String currentDate = dateFormat.format(cal.getTime()).toString();
    	return currentDate;
    }

    //----------------------------------------------------------------------------------------------------
    //Call homepage
    private void loadHomepage() throws IOException{
    	FXMLLoader Loader = sceneSwitch("Homepage.fxml", "Homepage");
    	if(!this.isGuest && !this.isVisitor) {   
    		this.group.getA_Group().set(User_Group_Index, this.target);
    		this.G_List.getGroup_List().set(this.Group_Index, this.group);
    		this.User_List.getAll_User().set(this.User_List_Index, this.target.getUser());
    		HomepageController HC = Loader.getController();
    		HC.GroupToHome(this.target, this.group, this.Info_List, this.User_List, this.G_List);
    	}
    	else if(this.isVisitor) {
    		HomepageController HC = Loader.getController();
    		HC.GroupToUser(this.Visitor, this.User_List, this.G_List, this.Info_List);
    	}
    	else {
    		HomepageController HC = Loader.getController();
    		HC.GuestToHome(this.User_List, this.G_List, this.Info_List);
    	}
    }
   
	public void HomeToGroup(Group_Status target, Group group, Information_List info_List, UserList ul, Group_List gl) {
		this.target = target;
		this.group = group;
		this.Info_List = info_List;
		this.User_List = ul;
		this.G_List = gl;
		this.User = target.getUser();
		getUserIndex();		//Load the user information index in the information list
		findUserGroupIndex(); //Find the index of user in the group
		getActiveMember(); 	//Get the active members
		getGroupIndex(); //Get the index of group in the group list
		getUserListIndex(); //Get the index of user in the user list 
		displayProjectInformation();
		displayMemberList();
		
        
        //Poll status
        Group.getItems().get(1).setOnAction(e-> pollStatus());
        //Personal status
        Group.getItems().get(0).setOnAction(e-> statusDisplay());
        //Schedule a meeting
        Vote.getItems().get(1).setOnAction(e-> scheduleAMeeting());
        //---------------------------Vote List--------------------------------------
        Vote.getItems().get(0).setOnAction(e-> VotingPane());
	}

	public void GuestToGroup(Group group, Group_List g_List2, Information_List info_list, UserList user_list) {
		this.group = group;
		this.G_List = g_List2;
		this.Info_List = info_list;
		this.User_List = user_list;
		this.isGuest = true;
		for(MenuItem menu : Vote.getItems())
			menu.setDisable(true);
		for(int i = 0; i < this.Group.getItems().size(); ++i) {
			if(i != 2)
				Group.getItems().get(i).setDisable(true);
		}
		displayProjectInformation();
		displayMemberList();
	}
	
	public void UserToGroup(Client target2, UserList userList, Group_List g_List2, Information_List info_List2,
			Group group2) {
		this.Visitor = target2;
		this.User_List = userList;
		this.G_List = g_List2;
		this.Info_List = info_List2;
		this.group = group2;
		this.isVisitor = true;
		for(MenuItem menu : Vote.getItems())
			menu.setDisable(true);
		for(int i = 0; i < this.Group.getItems().size(); ++i) {
			if(i != 2)
				Group.getItems().get(i).setDisable(true);
		}
		displayProjectInformation();
		displayMemberList();
	}
	
	private FXMLLoader sceneSwitch(String url, String title) throws IOException {
		FXMLLoader Loader = new FXMLLoader(getClass().getResource(url));
        Parent home_pane = Loader.load();
        Stage stage = (Stage) Scroll_Pane.getScene().getWindow();
        stage.setScene(new Scene(home_pane));
        stage.setTitle(title);
        return Loader;
	}
	//****************************************************************************************************
	
	//Display the information of the project
	private void displayProjectInformation() {
        //Text Area 
        ProjectName.setPadding(new Insets(45,45,45,45));
        ProjectName.setFont(Font.font(null, FontWeight.BOLD, 20));
        ProjectName.setText(this.group.getTitle() + "(" + this.group.getGroup_ID() + ")");
        
        ProjectDescription.setPadding(new Insets(40,40,40,40));
        ProjectDescription.setFont(Font.font(null, FontWeight.BOLD, 20));
        ProjectDescription.setText(this.group.getDescription());
	}

	private void displayMemberList() {
		getActiveMember();		//Refresh
		//Member is leaving or kicked
		for(int i = 0, j = 0; i < this.group.getA_Group().size(); ++i) {
			if(this.group.getA_Group().get(i).isCondition()) {
				MenuItem menu = new MenuItem(this.group.getA_Group().get(i).getUser().getName());
				Members.getItems().add(menu);
				int k = i;				
				Members.getItems().get(j++).setOnAction(e-> {
					BorderPane border = infoDisplay(this.group.getA_Group().get(k));
					this.group.getA_Group().get(k).setEvaluation(((TextArea)((GridPane)border.getCenter()).getChildren().get(7)).getText());
				});			
			}
		}
	}

	private void addToWhitebox(String name) {
		boolean result = true;
		for(int i = 0; i < this.Info_List.getInfo_Con().get(Info_Index).getWhitebox().size(); ++i) {
			if(this.Info_List.getInfo_Con().get(Info_Index).getWhitebox().get(i).getName().compareTo(name) == 0)
			{
				getAlert(AlertType.ERROR, "Already in your whitebox.", ButtonType.CLOSE, "Error");
				result = false;
			}
		}
		if(result) {
			Client client = getUser(name);
			this.Info_List.addFriend(this.User.getID(), client);
			getAlert(AlertType.CONFIRMATION, "All set.", ButtonType.CLOSE, "Confirmation");
		}
	}
	
	private void addToBlackbox(String name) {
		boolean result = true;
		for(int i = 0; i < this.Info_List.getInfo_Con().get(Info_Index).getWhitebox().size(); ++i) {
			if(this.Info_List.getInfo_Con().get(Info_Index).getWhitebox().get(i).getName().compareTo(name) == 0)
			{
				Client client = this.Info_List.getInfo_Con().get(Info_Index).getWhitebox().get(i);
				this.Info_List.removeFriend(this.User.getID(), client);
				this.Info_List.addBlacklist(this.User.getID(), client);
				getAlert(AlertType.CONFIRMATION, "All set.", ButtonType.CLOSE, "Confirmation");
				result = false;
			}
		}
		if(result) {
			Client client = getUser(name);
			this.Info_List.addBlacklist(this.User.getID(), client);
			getAlert(AlertType.CONFIRMATION, "All set.", ButtonType.CLOSE, "Confirmation");
		}
	}
	
	//Information list
    private void getUserIndex() {
    	for(int i = 0; i < this.Info_List.getInfo_Con().size(); ++i) {
    		if(this.User.getID().compareTo(this.Info_List.getInfo_Con().get(i).getID()) == 0) 
    			this.Info_Index = i;
    	}
    }

    private boolean isValidDay(String date) {
    	for(int i = 0; i < this.Info_List.getInfo_Con().get(Info_Index).getAppointment().size(); ++i) {
    		//extract the first 10 characters and compare
    		String str = this.Info_List.getInfo_Con().get(Info_Index).getAppointment().get(i).getDate().substring(0, 10);
    		if(str.compareTo(date) == 0)
    			return false;
    	}
    	return true;
    }
    
    //Check whether the poll is working
    private boolean isWorking() {
    	if(this.group.getVT(5))
    		return true;
    	return false;
    }

    private Client getUser(String name) {
    	for(int i = 0; i < this.User_List.getAll_Size(); ++i) {
    		if(this.User_List.getAll_User().get(i).getName().compareTo(name) == 0)
    			return this.User_List.getAll_User().get(i);
    	}
    	return null;
    }
    
    //Group list
	private void findUserGroupIndex() {
    	for(int j = 0; j < this.group.getA_Group().size(); ++j) {
    		if(this.group.getA_Group().get(j).getUser().getID().compareTo(this.User.getID()) == 0) {
    			this.User_Group_Index = j;
    			break;
    		}
    	}
    }

    private void getActiveMember() {
    	for(int i = 0; i < this.group.getA_Group().size(); ++i) {
    		if(this.group.getA_Group().get(i).isCondition())
    			++this.Active_Members;
    	}
    }

    private Client findTarget(String name) {
    	for(int i = 0; i < this.group.getA_Group().size(); ++i) {
    		if(this.group.getA_Group().get(i).getUser().getName().compareTo(name) == 0)
    			return this.group.getA_Group().get(i).getUser();
    	}
    	return null;
    }
    
    private void getGroupIndex() {
    	for(int i = 0; i < this.G_List.getGroup_List().size(); ++i) {
    		if(this.G_List.getGroup_List().get(i).getGroup_ID().compareTo(this.group.getGroup_ID()) == 0)
    			this.Group_Index = i;
    	}
    }
    
    private int VoteeIndex(int index) {
		for(int i = 0; i < this.group.getA_Group().size(); ++i) {
			if(this.group.getA_Group().get(i).getUser().getID().compareTo(this.group.getTarget()[index].getID()) == 0)
				return i;
		}
		return -1;
    }
    
    private void getUserListIndex() {
    	for(int i = 0; i < this.User_List.getAll_Size(); ++i) {
    		if(this.User.getID().compareTo(this.User_List.getAll_User().get(i).getID()) == 0) {
    			this.User_List_Index = i;
    			break;
    		}
    	}
    }
}
