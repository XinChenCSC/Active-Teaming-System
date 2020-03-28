package application;

import javafx.animation.Animation;
import javafx.animation.Transition;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
        Label lb = new Label();
        lb.setText("Currently no group avaliable!"); // Display group status
        
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
        
        //Creating scene
        createButton.setOnAction(e->{

        	GridPane gp = new GridPane();
        	ScrollPane sp = new ScrollPane(gp);
        	AnchorPane ap = new AnchorPane();
        	sp.setFitToHeight(true);
        	sp.setFitToWidth(true);
        	sp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        	sp.setVbarPolicy(ScrollBarPolicy.NEVER);
        	gp.setVgap(10);
        	gp.setHgap(120);
        	gp.setPadding(new Insets(20,20,10,0));
        	ColumnConstraints column1 = new ColumnConstraints(100);
        	ColumnConstraints column2 = new ColumnConstraints(70);
        	gp.getColumnConstraints().addAll(column1, column2);
        	//Show all avaliable friends
        	for(int i = 0; i < 5; ++i) {
            	//Label names
            	Label l1 = new Label("Unknown");
            	l1.setFont(new Font(16));
        		GridPane.setHalignment(l1, HPos.RIGHT);
            	gp.add(l1, 0, i);
            	//set up invite button
            	Button b1 = new Button("Invite");
            	b1.setFocusTraversable(false);
            	b1.setStyle("-fx-background-radius: 20;"
            			+ "-fx-border-radius: 20;"
            			+ "-fx-border-color: black;");
            	GridPane.setHalignment(b1, HPos.LEFT);
            	gp.add(b1, 1, i);
        	}
        	
        	
        	Scene thirdScene = new Scene(sp, 300, 600);
            // New window (Stage)
            Stage newWindow_2 = new Stage();
            newWindow_2.setTitle("Friends");
            newWindow_2.setScene(thirdScene);
            newWindow_2.setResizable(false);
            
            // Specifies the modality for new window.
            newWindow_2.initModality(Modality.WINDOW_MODAL);

            // Specifies the owner Window (parent) for new window
            newWindow_2.initOwner(newWindow);
            
            //Set the window default position
            newWindow_2.setX(screen.getWidth()/1.3);
            newWindow_2.setY(screen.getHeight()/4);
            newWindow_2.show();
        });
    }
    
    @FXML
    void Email_Click(ActionEvent event) {
    	//Get the screen size
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();          
        //Get the main scene size
        Stage mainScene = (Stage) Profile.getScene().getWindow();
        //Create new stage
        StackPane secondaryLayout = new StackPane();
        Scene secondScene = new Scene(secondaryLayout, screen.getWidth()/2, screen.getHeight()/2);
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
    
    @FXML
    void Message_Click(ActionEvent event) {   
    	//Get the screen size
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();          
        //Get the main scene size
        Stage mainScene = (Stage) Profile.getScene().getWindow();
        //Create new stage
        StackPane secondaryLayout = new StackPane();
        Scene secondScene = new Scene(secondaryLayout, screen.getWidth()/5, screen.getHeight()/2);
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