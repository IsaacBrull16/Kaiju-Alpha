package com.projecte.kaiju.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projecte.kaiju.helpers.GlobalInfo;
import com.projecte.kaiju.models.Card;
import com.projecte.kaiju.models.Game;

public class PartidaViewModel extends ViewModel {
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference usrRef;
    private MutableLiveData<Integer> numDice1 = new MutableLiveData<>();
    private MutableLiveData<Integer> numDice2 = new MutableLiveData<>();
    private MutableLiveData<Card> cardOnT1 = new MutableLiveData<Card>();
    private MutableLiveData<Card> cardOnT2 = new MutableLiveData<Card>();
    private MutableLiveData<Integer> life1 = new MutableLiveData<>();
    private MutableLiveData<Integer> life2 = new MutableLiveData<>();

    private MutableLiveData<String> currentPlayer = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard1Playable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard2Playable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard1OnTable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard2OnTable = new MutableLiveData<>();

    private MutableLiveData<Boolean> winner = new MutableLiveData<>();

    private MutableLiveData<Boolean> isDice1Rolled = new MutableLiveData<>();

    private MutableLiveData<Boolean> isDice2Rolled = new MutableLiveData<>();

    private MutableLiveData<Boolean> turnChanged = new MutableLiveData<>();

    private MutableLiveData<Integer> actNum1 = new MutableLiveData<>();
    private MutableLiveData<Integer> actNum2 = new MutableLiveData<>();
    private MutableLiveData<String> actPlusNewDiceNum1 = new MutableLiveData<>();
    private MutableLiveData<String> actPlusNewDiceNum2 = new MutableLiveData<>();
    private MutableLiveData<String> objective1 = new MutableLiveData<>();
    private MutableLiveData<String> objective2 = new MutableLiveData<>();
    private MutableLiveData<Integer> card1Life = new MutableLiveData<>();
    private MutableLiveData<Integer> card2Life = new MutableLiveData<>();

    private Game game;

    public LiveData<Integer> getDice1(){
        return numDice1;
    }

    public LiveData<Integer> getDice2(){
        return numDice2;
    }

    public LiveData<Card> getCard1(){
        return cardOnT1;
    }

    public LiveData<Card> getCard2(){
        return cardOnT2;
    }

    public LiveData<Integer> getLife1() {
        return life1;
    }

    public LiveData<Integer> getLife2(){
        return life2;
    }

    public LiveData<String> getCurrentPlayer(){
        return currentPlayer;
    }
    public LiveData<Boolean> getIsCard1Playable(){
        return isCard1Playable;
    }
    public LiveData<Boolean> getIsCard2Playable(){
        return isCard2Playable;
    }
    public LiveData<Boolean> getIsCard1OnTable(){
        return isCard1OnTable;
    }
    public LiveData<Boolean> getIsCard2OnTable(){
        return isCard2OnTable;
    }

    public LiveData<Boolean> getWinner(){return winner;}

    public LiveData<Boolean> getIsDice1Rolled(){return isDice1Rolled;}

    public LiveData<Boolean> getIsDice2Rolled(){return isDice2Rolled;}

    public LiveData<Boolean> getTurnChanged(){return turnChanged;}

    public MutableLiveData<Integer> getActNum1() {
        return actNum1;
    }

    public MutableLiveData<Integer> getActNum2() {
        return actNum2;
    }

    public MutableLiveData<String> getActPlusNewDiceNum1() {
        return actPlusNewDiceNum1;
    }

    public MutableLiveData<String> getActPlusNewDiceNum2() {
        return actPlusNewDiceNum2;
    }

    public MutableLiveData<String> getObjective1() {
        return objective1;
    }

    public MutableLiveData<String> getObjective2() {
        return objective2;
    }

    public MutableLiveData<Integer> getCard1Life() {
        return card1Life;
    }

    public MutableLiveData<Integer> getCard2Life() {
        return card2Life;
    }

    private int l1;
    private int l2;

    private int cl1;
    private int cl2;

    public PartidaViewModel() {
        game = new Game();
        isDice1Rolled.setValue(game.getBoard().getDiceRolledP1());
        isCard1Playable.setValue(false);
        isCard2Playable.setValue(false);
        life1.setValue(game.getBoard().getPlayer1().getLife());
        life2.setValue(game.getBoard().getPlayer2().getLife());
        turnChanged.setValue(true);
        l1 = game.getBoard().getPlayer1().getLife();
        l2 = game.getBoard().getPlayer2().getLife();
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            String id = mAuth.getCurrentUser().getUid();
            String url = GlobalInfo.getInstance().getFB_DB();
            db = FirebaseDatabase.getInstance(url);
            usrRef = db.getReference(id);
            usrRef.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = snapshot.getValue(String.class);
                    game.getBoard().getPlayer1().setName(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        currentPlayer.setValue(game.getBoard().getPlayer1().getName());
    }

    public void launchDice1() {
        if ((game.getTurn().getTurnValue() == true) && (game.getBoard().getDiceRolledP1() == false)) {
            game.dice1Actions();
            if ((game.getBoard().getPlayer1().getPlayerDice().getAcumValue() >= game.getCardT1().getCost()) && (game.getBoard().isCardOnTableP1() == true)){
                isCard1Playable.setValue(true);
            }
            int act = game.getBoard().getPlayer1().getPlayerDice().getOriginalValue();
            int acum = game.getBoard().getPlayer1().getPlayerDice().getAcumValue();
            actNum1.setValue(act);
            if (!((acum - act) == 0)){
                actPlusNewDiceNum1.setValue(String.valueOf(act) + " + " + String.valueOf(acum - act));
            } else {
                actPlusNewDiceNum1.setValue(String.valueOf(act));
            }
            numDice1.setValue(acum);
            isDice1Rolled.setValue(game.getBoard().getDiceRolledP1());
        }
    }

    public void launchDice2() {
        if ((game.getTurn().getTurnValue() == false) && (game.getBoard().getDiceRolledP2() == false)) {
            game.dice2Actions();
            if ((game.getBoard().getPlayer2().getPlayerDice().getAcumValue() >= game.getCardT2().getCost()) && (game.getBoard().isCardOnTableP2() == true)){
                isCard2Playable.setValue(true);
            }
            int act = game.getBoard().getPlayer2().getPlayerDice().getOriginalValue();
            int acum = game.getBoard().getPlayer2().getPlayerDice().getAcumValue();
            actNum2.setValue(act);
            if (!((acum - act) == 0)){
                actPlusNewDiceNum2.setValue(String.valueOf(act) + " + " + String.valueOf(acum - act));
            } else {
                actPlusNewDiceNum2.setValue(String.valueOf(act));
            }
            numDice2.setValue(acum);
            isDice2Rolled.setValue(game.getBoard().getDiceRolledP2());
        }
    }

    public void setCardOnT1(){
        if ((game.getTurn().getTurnValue() == true) && (game.getBoard().isCardOnTableP1() == false) && (game.getBoard().getDiceRolledP1() == true) && (numDice1.getValue() != null)) {
            if (game.getBoard().getPlayer1().getDeckOfPlayer().deckSize() != 0) {
                game.deck1Actions();
                Card card1 = game.getCardT1();
                game.setCardT1(card1);
                cl1 = game.getCardT1().getLife();
                card1Life.setValue(cl1);
                cardOnT1.setValue(card1);
                isCard1OnTable.setValue(true);
                if (card1.getCost() <= numDice1.getValue()){
                    isCard1Playable.setValue(true);
                }
            }
        }
    }

    public void setCardOnT2(){
        if ((game.getTurn().getTurnValue() == false) && (game.getBoard().isCardOnTableP2() == false) && (game.getBoard().getDiceRolledP2() == true) && (numDice2.getValue() != null)) {
            if (game.getBoard().getPlayer2().getDeckOfPlayer().deckSize() != 0) {
                game.deck2Actions();
                Card card2 = game.getCardT2();
                game.setCardT2(card2);
                cl2 = game.getCardT2().getLife();
                card2Life.setValue(cl2);
                cardOnT2.setValue(card2);
                isCard2OnTable.setValue(true);
                if (card2.getCost() <= numDice2.getValue()){
                    isCard2Playable.setValue(true);
                }
            }
        }
    }

    public void useCard1(){
        if ((game.getTurn().getTurnValue() == true) && (game.getBoard().isCardOnTableP1() == true)) {
            if (game.getBoard().getPlayer1().getPlayerDice().getAcumValue() >= game.getCardT1().getCost()) {
                game.card1Actions();
                String objectiveP1 = objective1.getValue();
                //if (objectiveP1.equals("player")){
                    l2 = l2 - game.getCardT1().getDamage();
                    game.getBoard().getPlayer2().setLife(l2);
                /*} else if (objectiveP1.equals("card1")){
                    cl1 = cl1 - game.getCardT1().getDamage();
                    game.getCardT1().setLife(cl1);
                    card1Life.setValue(cl1);
                }*/
                int d1 = game.getBoard().getPlayer1().getPlayerDice().getAcumValue();
                isCard1OnTable.setValue(false);
                isCard1Playable.setValue(false);
                numDice1.setValue(d1);
                life2.setValue(l2);
            }
        }
    }

    public void useCard2(){
        if ((game.getTurn().getTurnValue() == false) && (game.getBoard().isCardOnTableP2() == true)) {
            if (game.getBoard().getPlayer2().getPlayerDice().getAcumValue() >= game.getCardT2().getCost()) {
                game.card2Actions();
                String objectiveP2 = objective2.getValue();
                //if (objectiveP2.equals("player")){
                    l1 = l1 - game.getCardT1().getDamage();
                    game.getBoard().getPlayer2().setLife(l1);
                /*} else if (objectiveP2.equals("card1")) {
                    cl2 = cl2 - game.getCardT2().getDamage();
                    game.getCardT1().setLife(cl2);
                    card2Life.setValue(cl2);
                }*/
                int d2 = game.getBoard().getPlayer2().getPlayerDice().getAcumValue();
                isCard2OnTable.setValue(false);
                isCard2Playable.setValue(false);
                life1.setValue(l1);
                numDice2.setValue(d2);
            }
        }
    }

    public void changeTurn1(){
        if (game.getTurn().getTurnValue() == true) {
            game.endTurn1();
            String p2 = game.getBoard().getPlayer2().getName();
            currentPlayer.setValue(p2);
            isDice1Rolled.setValue(false);
            turnChanged.setValue(false);
        }
    }

    public void changeTurn2() {
        if (game.getTurn().getTurnValue() == false) {
            game.endTurn2();
            String p1 = game.getBoard().getPlayer1().getName();
            currentPlayer.setValue(p1);
            isDice2Rolled.setValue(false);
            turnChanged.setValue(true);
        }
    }

    public void setPlayerMode(String mode){
        game.getBoard().getPlayer2().setType(mode);
    }
}
