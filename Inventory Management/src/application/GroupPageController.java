package application;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class GroupPageController {

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
    private Button Edit;

    @FXML
    private AnchorPane Scroll_Anchor;
    
    //window size
    Rectangle2D screen = Screen.getPrimary().getVisualBounds(); 
    //Vote types
    final String[] voting_tags = {"Dismissal", "Warning", "Praise", "Closure", "Eva. exit", "Schedule"};
    
    @FXML
    void initialize()throws IOException {
    	//Resize the background image
        Background.setLayoutX(screen.getWidth());
        Background.setLayoutY(screen.getHeight()); 
        //Set edit button position
        Edit.setLayoutX(Scroll_Anchor.getPrefWidth()-100);
        Edit.setLayoutY(Scroll_Anchor.getPrefHeight()-70);
        
        //Text Area 
        ProjectName.setPadding(new Insets(45,45,45,45));
        ProjectName.setFont(Font.font(null, FontWeight.BOLD, 20));
        ProjectName.setText("Qichen You");
        
        ProjectDescription.setPadding(new Insets(40,40,40,40));
        ProjectDescription.setFont(Font.font(null, FontWeight.BOLD, 20));
        ProjectDescription.setText("");
        
        //Member inforamtion
    	Members.getItems().get(0).setOnAction(e-> infoDisplay());
        //Personal status
        Group.getItems().get(0).setOnAction(e-> statusDisplay());
        //Schedule a meeting
        Vote.getItems().get(1).setOnAction(e-> scheduleAMeeting());
        //---------------------------Vote List--------------------------------------
        Vote.getItems().get(0).setOnAction(e-> VotingPane());
        //Poll status
        Group.getItems().get(1).setOnAction(e-> pollStatus());
        //SU only
        Edit.setOnAction(e-> groupEdit());
    }
 
    //Back to homepage
    @FXML
    private void moveToHomepage(ActionEvent event) throws IOException {
    	Parent home_page = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Stage home_scene = (Stage) Anchor_Pane.getScene().getWindow();
        home_scene.setScene(new Scene(home_page));
        home_scene.setTitle("Homepage");
        home_scene.show();
    }
    
    //Group internal operations
    private void groupEdit() {
    	ButtonType ok = new ButtonType("Ok", ButtonData.OK_DONE);
    	Pane pane = new Pane();
    	//GridPane
    	GridPane gridPane = new GridPane();
    	getSetting(gridPane, 400, 450, 0, 0);
        gridPane.getColumnConstraints().add(new ColumnConstraints(200));
        gridPane.getColumnConstraints().add(new ColumnConstraints(197));
        gridPane.setVgap(10);
    	//tags
        String[] tags = {"Scores reduction", "Expel from the group", "Expel from the system", "Revise a member"};
        for(int i = 0; i < tags.length; ++i) {
        	//Subject
            Label subject = new Label(tags[i]);
            getSetting(subject, 200, 30, 0, 0);
            subject.setPadding(new Insets(5,5,5,5));
            subject.setAlignment(Pos.CENTER_LEFT);
            subject.setStyle("-fx-background-color:#DAA520;");
         	gridPane.getRowConstraints().add(new RowConstraints(40));
         	gridPane.add(subject, 0, i*2);
         	//Scores reduction
         	ComboBox<String> validMembers = new ComboBox<>();
         	validMembers.setPromptText("Names");
         	getSetting(validMembers, 180, 30, 0, 0);
     		//Scores comboBox
     		ComboBox<Integer> score = new ComboBox<>();
         	//Confirm button
         	Button confirm = new Button("Confirm");
         	getSetting(confirm, 80, 30, 0, 0);
         	confirm.setPadding(new Insets(5,5,5,5));
     		GridPane.setHalignment(confirm, HPos.RIGHT);
     		gridPane.getRowConstraints().add(new RowConstraints(40));
     		gridPane.add(confirm, 1, i*2+1);
         	//set up combobox
     		for(int j = 0; j < 5; ++j)
         		validMembers.getItems().add("Unknown" + j);
         	GridPane.setHalignment(validMembers, HPos.CENTER);
         	gridPane.add(validMembers, 0, i*2+1);
         	//set confirm Button
         	if(i == 0) {
         		//set up scores comboBox
         		score.setPromptText("Scores");
             	getSetting(score, 100, 30, 0, 0);
         		score.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
         		GridPane.setHalignment(score, HPos.LEFT);
         		gridPane.add(score, 1, i*2+1);
         	}
         	confirm.setOnAction(e->{
         		if(validMembers.getSelectionModel().getSelectedItem() == null)
         			getWarningAlert(AlertType.WARNING, "All inputs need to be valid.", ButtonType.OK, "Caution!");
         		else {
         			if(subject.getText().compareTo(tags[0]) == 0) {
         				if(score.getSelectionModel().getSelectedItem() == null)
         					getWarningAlert(AlertType.WARNING, "All inputs need to be valid.", ButtonType.OK, "Caution!");
         				else
         					getConfirmationAlert("Are you sure to take " + score.getSelectionModel().getSelectedItem() + 
         							" scores off from " + validMembers.getSelectionModel().getSelectedItem() + "?",
         							"Confirm", (Stage) gridPane.getScene().getWindow(), "Action completed.");
         			}
         			else if(subject.getText().compareTo(tags[1]) == 0) {
         				getConfirmationAlert("Are you sure to expel " + validMembers.getSelectionModel().getSelectedItem() + " from the group?",
         						"Confirm", (Stage) gridPane.getScene().getWindow(), "Action completed.");
         			}
         			else if(subject.getText().compareTo(tags[2]) == 0) {
         				getConfirmationAlert("Are you sure to expel " + validMembers.getSelectionModel().getSelectedItem() + " from the SYSTEM?",
         						"Confirm", (Stage) gridPane.getScene().getWindow(), "Action completed.");
         			}
         			else{
         				getConfirmationAlert("Are you sure to revise " + validMembers.getSelectionModel().getSelectedItem() + " back to the group?",
         						"Confirm", (Stage) gridPane.getScene().getWindow(), "Action completed.");
         			}
         		}
         	});
        }
        pane.getChildren().add(gridPane);
    	Dialog<ButtonType> dialog = new Dialog<>();
    	getDialog(dialog, pane, "Editing", "", ok);
    }

    //display poll status
    private void pollStatus() {
    	ButtonType ok = new ButtonType("Ok", ButtonData.OK_DONE);
    	Pane pane = new Pane();
    	//Scroll Pane
    	ScrollPane scrollPane = new ScrollPane();
    	getSetting(scrollPane, 400, 450, 0, 0);
    	scrollPane.setFitToWidth(true);
    	scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
    	scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    	//GridPane
    	GridPane gridPane = new GridPane();
        gridPane.getColumnConstraints().add(new ColumnConstraints(150));
        gridPane.getColumnConstraints().add(new ColumnConstraints(247));
        gridPane.setVgap(10);
        for(int i = 0; i < voting_tags.length; ++i) {
            //Vote type
            Label voteType = new Label("Type: " + voting_tags[i]);
            getSetting(voteType, 150, 30, 0, 0);
            voteType.setPadding(new Insets(10,10,10,10));
            voteType.setAlignment(Pos.CENTER_LEFT);
            voteType.setStyle("-fx-background-color:#DAA520;");
            voteType.setDisable(true);
         	gridPane.getRowConstraints().add(new RowConstraints(40));
         	gridPane.add(voteType, 0, i*2);
         	//Target
         	Label target = new Label("Target: UNKNOWN");
         	if(i == 3 || i == 5) 
         		target.setText("");
         	getSetting(target, 250, 30, 0, 0);
            target.setPadding(new Insets(10,10,10,10));
            target.setAlignment(Pos.CENTER_LEFT);
            target.setStyle("-fx-background-color:#DAA520;");
            target.setDisable(true);
            gridPane.add(target, 1, i*2);
            //Indicator
            Label agreedVotes = new Label("Agrees");
            agreedVotes.setPadding(new Insets(0, 0, 0, 10));
            agreedVotes.setDisable(true);
            GridPane.setHalignment(agreedVotes, HPos.CENTER);
            gridPane.getRowConstraints().add(new RowConstraints(40));
            gridPane.add(agreedVotes, 0, i*2+1);
        	//Progress bar
            ProgressBar progress = new ProgressBar();
            progress.setProgress(0);
            progress.setPrefWidth(250);
            progress.setPadding(new Insets(0,40,0,0));
            progress.setId("AgreeVotes");
            progress.setDisable(true);
            GridPane.setHalignment(progress, HPos.CENTER);
            gridPane.add(progress, 1, i*2+1);
        }
        
        gridPane.getStylesheets().add(getClass().getResource("GroupPage.css").toExternalForm());
        scrollPane.setContent(gridPane);
        pane.getChildren().add(scrollPane);
    	Dialog<ButtonType> dialog = new Dialog<>();
    	getDialog(dialog, pane, "Poll Status", "", ok);
    }
    
    //Vote for dismissal
    private void VotingPane() {
    	ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    	//GridPane
    	GridPane gridPane = new GridPane();
        getSetting(gridPane, 400, 400, 0, 10);
        gridPane.getColumnConstraints().add(new ColumnConstraints(400));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        //Warning string
    	String str = "One vote at a time!";
     	Label instruction = new Label(str);
     	instruction.setPadding(new Insets(10,10,10,10));
     	instruction.setAlignment(Pos.CENTER);
     	instruction.setStyle("-fx-background-color:#DAA520;");
     	getSetting(instruction, 400, 50, 0, 50);
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
     	for(int i = 0; i < voting_tags.length; ++i)
     		vote.getItems().add(voting_tags[i]);
     	getSetting(vote, 200, 30, 0, 0);
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
     	getSetting(name, 200, 30, 0, 0);
     	for(int i = 0; i < 5; ++i) 
     		name.getItems().add("Unknown");
     	GridPane.setHalignment(name, HPos.CENTER);
     	gridPane.getRowConstraints().add(new RowConstraints(30));
     	gridPane.add(name, 0, 4);
    	
     	//Reason label
     	Label writeReason = new Label("Give a reason");
     	writeReason.setAlignment(Pos.CENTER);
     	GridPane.setHalignment(writeReason, HPos.CENTER);
     	gridPane.getRowConstraints().add(new RowConstraints(30));
     	gridPane.add(writeReason, 0, 5);
     	
     	//Reason area
     	TextArea reasonArea = new TextArea();
     	reasonArea.setMaxSize(300, 130);
     	reasonArea.setPadding(new Insets(10,10,10,10));
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
     			getWarningAlert(AlertType.WARNING, "All inputs need to be valid.", ButtonType.OK, "Caution!");
     		else {
     			if(vote.getSelectionModel().getSelectedItem().toString().compareTo(voting_tags[0]) == 0)
     				getConfirmationAlert("Are you sure to dismiss UNKNOWN?", "Confirm", (Stage) gridPane.getScene().getWindow(),
     						"Wait for the responds from other group members.");
     			else if(vote.getSelectionModel().getSelectedItem().toString().compareTo(voting_tags[1]) == 0)
     				getConfirmationAlert("Are you sure to issue a warning to UNKNOWN?", "Confirm", (Stage) gridPane.getScene().getWindow(),
     						"Wait for the responds from other group members.");
     			else if(vote.getSelectionModel().getSelectedItem().toString().compareTo(voting_tags[2]) == 0)
     				getConfirmationAlert("Are you sure to give UNKNOWN a pat on the back?", "Confirm", (Stage) gridPane.getScene().getWindow(),
     						"Wait for the responds from other group members.");
     			else if(vote.getSelectionModel().getSelectedItem().toString().compareTo(voting_tags[3]) == 0)
     				getConfirmationAlert("Are you sure to close the group?", "Confirm", (Stage) gridPane.getScene().getWindow(),
     						"Wait for the responds from other group members.");
     			else 
     				getConfirmationAlert("Are you sure to exit UNKNOWN's evaluation?", "Confirm", (Stage) gridPane.getScene().getWindow(),
     						"Wait for the responds from other group members.");
     		}
     	});
     	GridPane.setHalignment(submit, HPos.CENTER);
     	gridPane.getRowConstraints().add(new RowConstraints(50));
     	gridPane.add(submit, 0, 7);
     	
     	//Get dialog
      	Dialog<ButtonType> dialog = new Dialog<>();
     	getDialog(dialog, gridPane, "Vote", "", cancel);

    }
    
    //Display member's info
    private void infoDisplay() {    
      	 ButtonType ok = new ButtonType("OK",ButtonData.OK_DONE);
    	 //GridPane
    	 GridPane gridPane = new GridPane();
    	 //Import css file
    	 gridPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	 getSetting(gridPane, 400, 200, 0, 70);
    	 gridPane.getColumnConstraints().add(new ColumnConstraints(100));
    	 gridPane.getColumnConstraints().add(new ColumnConstraints(300));
    	 gridPane.setVgap(10);    	 
    	 
    	 String[] tags = {"Name:", "ID:", "Position:"};
    	 //list member's informations
    	 for(int i = 0; i < tags.length; ++i) {
    		 Label tag = new Label(tags[i]);
    		 GridPane.setHalignment(tag, HPos.CENTER);
        	 gridPane.getRowConstraints().add(new RowConstraints(50));
    		 gridPane.add(tag, 0, i);
    		 
    		 Label value = new Label("Unknown");
    		 GridPane.setHalignment(value, HPos.CENTER);
    		 gridPane.add(value, 1, i);
    	 }
    	 //Evaluation
    	 Label evaluation = new Label("Evaluation: ");
    	 getSetting(evaluation, 100, 30, 0, 0);
    	 evaluation.setAlignment(Pos.CENTER);
    	 gridPane.getRowConstraints().add(new RowConstraints(200));
    	 gridPane.add(evaluation, 0, tags.length);
    	 
    	 //Evaluation contents
    	 TextArea textArea = new TextArea("Unknown");
    	 textArea.setMaxSize(250, 150);
    	 textArea.setEditable(false);
    	 GridPane.setHalignment(textArea, HPos.CENTER);
    	 GridPane.setValignment(textArea, VPos.CENTER);
    	 gridPane.getRowConstraints().add(new RowConstraints(200));
    	 gridPane.add(textArea, 1, tags.length);
    	 
    	 //Call dialog
       	 Dialog<ButtonType> dialog = new Dialog<>();
    	 getDialog(dialog, gridPane, "Information", "Information", ok);
    }
        
    //Display personal status
    private void statusDisplay() {
     ButtonType ok = new ButtonType("OK",ButtonData.OK_DONE);
   	 //GridPane
   	 GridPane gridPane = new GridPane();
   	 getSetting(gridPane, 400, 200, 0, 70);
   	 gridPane.getColumnConstraints().add(new ColumnConstraints(200));
   	 gridPane.getColumnConstraints().add(new ColumnConstraints(200));
   	 gridPane.setVgap(10);    	 
   	 
   	 String[] tags = {"Attendances:", "Assignments:", "Warnings:", "Praises:", "Condition:", "Evaluation: "};
   	 //list member's informations
   	 for(int i = 0; i < tags.length; ++i) {
   		 Label tag = new Label(tags[i]);
   		 GridPane.setHalignment(tag, HPos.CENTER);
       	 gridPane.getRowConstraints().add(new RowConstraints(50));
   		 gridPane.add(tag, 0, i);
   		 
   		 if(i == 5) {
   			 ToggleButton isOn = new ToggleButton("On");
   			 isOn.setStyle("-fx-background-radius: 20;" +
   					 "-fx-border-radius: 20;" +
   					 "-fx-background-color: #ADFF2F;");
   	   		 GridPane.setHalignment(isOn, HPos.CENTER);
   	   		 gridPane.add(isOn, 1, i);
   		 }
   		 else if (i == 4) {
   			 ToggleButton isOn = new ToggleButton("Engaging");
   			 isOn.setStyle("-fx-background-radius: 20;" +
   					 "-fx-border-radius: 20;" +
   					 "-fx-background-color: #ADFF2F;");
   	   		 GridPane.setHalignment(isOn, HPos.CENTER);
   	   		 gridPane.add(isOn, 1, i);
   		 }
   		 else {
   	   		 Label value = new Label("Unknown");
   	   		 GridPane.setHalignment(value, HPos.CENTER);
   	   		 gridPane.add(value, 1, i);
   		 }
   	 }
   	 
   	 //Call dialog
   	 Dialog<ButtonType> dialog = new Dialog<>();
   	 getDialog(dialog, gridPane, "Status", "Status", ok);
    }
    
    //Schedule a meeting
    private void scheduleAMeeting() {
      	 ButtonType cancel = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
      	 Pane pane = new Pane();
      	 //String 
      	 String str = "Select your meeting time and submit by click the submit button. "
      	 		+ "The meeting time will be approved when all group members are agreed. The date can't be the current day!";
      	 //Instruction
      	 Label instruction = new Label(str);
      	 instruction.setPadding(new Insets(10,10,10,10));
      	 instruction.setWrapText(true);
      	 instruction.setStyle("-fx-background-color:#DAA520;");
      	 getSetting(instruction, 400, 110, 0, 50);
      	 
      	 //Select meeting time  
         GridPane gridPane = new GridPane();
         getSetting(gridPane, 400, 200, 0, 160);
         gridPane.getColumnConstraints().add(new ColumnConstraints(400));
         gridPane.setHgap(10);
         gridPane.setVgap(10);
         
         Label dataTitle = new Label("Choose the date");
         gridPane.getRowConstraints().add(new RowConstraints(30));
         GridPane.setHalignment(dataTitle, HPos.CENTER);
         gridPane.add(dataTitle, 0, 0);
         
         DatePicker DatePicker = new DatePicker();
         gridPane.getRowConstraints().add(new RowConstraints(30));
         GridPane.setHalignment(DatePicker, HPos.CENTER);
         gridPane.add(DatePicker, 0, 1);
         
         Label duration = new Label("Duration (Hours)");
         gridPane.getRowConstraints().add(new RowConstraints(30));
         GridPane.setHalignment(duration, HPos.CENTER);
         gridPane.add(duration, 0, 2);
         
         //All hour options     
         ComboBox<String> durationTime = new ComboBox<String>();
         getSetting(durationTime, 100, 30, 0, 0);
         gridPane.getRowConstraints().add(new RowConstraints(30));
         durationTime.getItems().addAll("1", "2", "3", "4", "5", "6");
         GridPane.setHalignment(durationTime, HPos.CENTER);
         gridPane.add(durationTime, 0, 3);
         
         //Starting time
         Label starting = new Label("Starting time");
         gridPane.getRowConstraints().add(new RowConstraints(30));
         GridPane.setHalignment(starting, HPos.CENTER);
         gridPane.add(starting, 0, 4);
         
         ComboBox<String> startingTime = new ComboBox<String>();
         gridPane.getRowConstraints().add(new RowConstraints(30));
         getSetting(startingTime, 150, 30, 0, 0);
         startingTime.getItems().addAll("9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM",
        		 					"3:00 PM", "4:00 PM");
         GridPane.setHalignment(startingTime, HPos.CENTER);
         gridPane.add(startingTime, 0, 5);
         
         //Submit
         Button submit = new Button("Submit");
         getSetting(submit, 80, 30, 0, 0);
         GridPane.setHalignment(submit, HPos.CENTER);
         GridPane.setValignment(submit, VPos.BOTTOM);
         gridPane.getRowConstraints().add(new RowConstraints(85));
         gridPane.add(submit, 0, 6);
         
         //Submit Event
      	 submit.setOnAction(e->{
      		 //Get the current time
      		 String currentDate = getCurrentDate();
      		 //If wrong inputs
      		 if (durationTime.getSelectionModel().getSelectedItem() == null || 
      				 startingTime.getSelectionModel().getSelectedItem() == null ||
      				 DatePicker.getValue() == null ||
      				 DatePicker.getValue().toString().compareTo(currentDate) < 0) 
      			 getWarningAlert(AlertType.WARNING, "All inputs need to be valid.", ButtonType.OK, "Caution!");
      		 //If correct inputs
      		 else {
      			String confirmedTime = "Date: At " + startingTime.getSelectionModel().getSelectedItem() + 
      							" " + DatePicker.getValue().toString() + 
      							"\nDuration: " + durationTime.getSelectionModel().getSelectedItem() + " hours" ;
      			getConfirmationAlert(confirmedTime, "Scheduled Time",(Stage) pane.getScene().getWindow(),
      					"Wait for the responds from other group members.");
      		 }
      	 });
      	 //Set children
      	 pane.getChildren().addAll(instruction, gridPane);
      	 //Call dialog
       	 Dialog<ButtonType> dialog = new Dialog<>();
      	 getDialog(dialog, pane, "Schedule a meeting", "Scheduling", cancel);
      	
    }
    
    //Warning alert for schedule
    private void getWarningAlert(AlertType at, String content, ButtonType bt, String title) {
    	Alert alert = new Alert(at, content, bt);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.showAndWait();
    	alert.setX(screen.getWidth()/2);
    	alert.setY(screen.getHeight()/3);
    }
    
    //Confirmation alert for schedule
    private void getConfirmationAlert(String content, String title, Stage stage, String result) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, content, ButtonType.YES, ButtonType.NO);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.showAndWait();
    	alert.setX(screen.getWidth()/2);
    	alert.setY(screen.getHeight()/3);
    	
    	//Call for the ending alert
    	if(alert.getResult() == ButtonType.YES) {
    		Alert endingAlert = new Alert(AlertType.CONFIRMATION, result, ButtonType.OK);
    		endingAlert.setTitle("Congratulation!");
    		endingAlert.setHeaderText(null);
    		endingAlert.showAndWait();
    		endingAlert.setX(screen.getWidth()/2);
        	endingAlert.setY(screen.getHeight()/3);
        	stage.close();
    	}
    }
    
    //Create a dialog
    private void getDialog(Dialog<ButtonType> dialog, Node n, String title, String header, ButtonType bt) {
   	 dialog.getDialogPane().setPrefSize(400, 500);
   	 dialog.getDialogPane().getButtonTypes().add(bt);
   	 dialog.setTitle(title);
   	 dialog.setHeaderText(header);
   	 dialog.getDialogPane().getChildren().add(n);
   	 dialog.showAndWait();
    }
    
    //Set up general setting of component
    private void getSetting(Node n, double w, double h, double x, double y) {
    	((Region) n).setPrefSize(w,h);
    	n.setLayoutX(x);
    	n.setLayoutY(y);
    	n.setFocusTraversable(false);
    }
    
    //Get the current time
    private String getCurrentDate() {
    	DateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
  		Calendar cal = Calendar.getInstance();
  		String currentDate = dateFormat.format(cal.getTime()).toString();
  		return currentDate;
    }
}
