/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker2;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author T-Money
 */
public class PokerController {
    @FXML private TextField cardrnk1;
    @FXML private TextField cardrnk2;
    @FXML private TextField cardrnk3;
    @FXML private TextField cardrnk4;
    @FXML private TextField cardrnk5;
    
    @FXML private TextField cardsuit1;
    @FXML private TextField cardsuit2;
    @FXML private TextField cardsuit3;
    @FXML private TextField cardsuit4;
    @FXML private TextField cardsuit5;
    
   
    @FXML private Label Hand;
    //@FXML public Button DisplayHand; 
    
    int cardrnk;
    String cardsuit;
    boolean flush;
    boolean straight;
    int temp;
 
    
    public void displayHand(ActionEvent event){
        int card1 = Integer.parseInt(cardrnk1.getText());
        String suit1 = cardsuit1.getText();
        int card2 = Integer.parseInt(cardrnk2.getText());
        String suit2 = cardsuit2.getText();
        int card3 = Integer.parseInt(cardrnk3.getText());
        String suit3 = cardsuit3.getText();
        int card4 = Integer.parseInt(cardrnk4.getText());
        String suit4 = cardsuit4.getText();
        int card5 = Integer.parseInt(cardrnk5.getText());
        String suit5 = cardsuit5.getText();
        
        int poker [][] = new int[2][5];
        poker[0][0] = card1;
        poker[0][1] = card2;
        poker[0][2] = card3;
        poker[0][3] = card4;
        poker[0][4] = card5;
        
        poker[1][0] = determineSuit(suit1);
        poker[1][1] = determineSuit(suit2);
        poker[1][2] = determineSuit(suit3);
        poker[1][3] = determineSuit(suit4);
        poker[1][4] = determineSuit(suit5);
        
    
        Hand.setText(HandType(poker));
}
    public int determineSuit(String suit){
    
      if (suit.equals("S")){
       return 4;
      }
   
      else if(suit.equals("H")){
       return  3;
      }
   
      else if (suit.equals("C")){
       return 2;
      }
   
      else {
       return  1;
      }
  }
  private String HandType(int [][] poker){
  //flush
  for(int i= 0; i < 4; i++){
   if (poker[1][i] == poker[1][i+1]){
    if (i == 3){
     flush = true;
    }
   }
   else{
    break;
   }
  }
  

  
  //bubble sort
  for (int i = 0; i < 4; i++){
   for(int j = 0; j < 4; j++){
    if (poker[0][j] > poker[0][j+1]){
     temp = poker[0][j];
     poker[0][j] = poker[0][j+1];
     poker [0][j+1] = temp;
    
    //takes the suit with the rank
    temp = poker[1][j];
    poker[1][j] = poker[1][j+1];
    poker[1][j+1] = temp;
    }
   }
  }
  
  //straight
  for (int i= 0; i < 4; i++){
   //System.out.println(".");
   for(int j = i; j < 4; j++){
    //Ace movement
    //if (poker[0][j] == 1 && poker[0][4] == 13)
      // j=1;
  //   System.out.println((((((poker[0][j])+1)%13)+"   "+ (poker[0][j+1])%13)));
    if(((((poker[0][j])+1)%13) == (poker[0][j+1])%13)){
     if(j == 3){
      straight = true;
     }
   
    }
    else{
     break;
    }
   }
  }
  
  //straight flush
  if (flush == true && straight == true){
   return("Your hand is a straight flush! \n");
   
   
  }
  else if (flush == true && straight == false){
   return("Your hand is a flush! \n");
   
   
  }
  
  else if (flush == false && straight == true){
   return("You have a straight hand! \n");
   
  }
  
  
  //four-of-a-kind/fullhouse/three-of-a-kind/ one-pair/ two-pairs
   int slot1rnk = poker[0][0];
   int slot1freq = 1;
   int slot2rnk = -1;
   int slot2freq = 0;
  
  for (int i = 1; i < 5; i++){
   if (poker[0][i] == slot1rnk){
    slot1freq++;
   }
   
   else if (poker[0][i] != slot1rnk && slot2rnk == -1){
     slot2rnk = poker[0][i];
     slot2freq=1;
    
   }
    else if (poker[0][i] != slot1rnk && poker[0][i] == slot2rnk){
      slot2freq++;
     
   } else if (slot1freq ==1 && poker[0][i] != slot1rnk){
     slot1rnk = poker[0][i];
     
   } else if (slot2freq == 1 && poker[0][i] != slot2rnk){
      slot2rnk = poker[0][i];
   }
  }
  
  //four-of-a-kind
  if (slot1freq == 4 || slot2freq == 4){
   return("Your hand is a four of a kind \n");
   
  }
  
  else if (slot1freq == 3 && slot2freq == 2 || slot1freq == 2 && slot2freq == 3){
   return("Your hand is a Full House!\n");
   
  }
  
  else if (slot1freq == 3 || slot2freq == 3){
   return("Your hand is a three of a kind! \n");
   
  }
  
  else if (slot1freq == 2 && slot2freq == 2){
   return("Your hand has two pairs!\n");
   
  }
  
  else if (slot1freq == 2 || slot2freq == 2){
   return("Your hand has one pair!\n");
   
  }
  else{
   return("Your hand has no special combos!");
  }
 }
}
