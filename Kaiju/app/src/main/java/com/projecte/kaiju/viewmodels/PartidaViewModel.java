package com.projecte.kaiju.viewmodels;

import android.util.Log;

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

    private boolean canUseCard21;
    private boolean canUseCard22;
    private boolean canUseCard23;

    private boolean cardTable11;
    private boolean cardTable12;
    private boolean cardTable13;
    private boolean cardTable21;
    private boolean cardTable22;
    private boolean cardTable23;
    private MutableLiveData<Integer> numDice1 = new MutableLiveData<>();
    private MutableLiveData<Integer> numDice2 = new MutableLiveData<>();
    private MutableLiveData<Card> cardOnT11 = new MutableLiveData<Card>();
    private MutableLiveData<Card> cardOnT12 = new MutableLiveData<Card>();
    private MutableLiveData<Card> cardOnT13 = new MutableLiveData<Card>();
    private MutableLiveData<Card> cardOnT21 = new MutableLiveData<Card>();
    private MutableLiveData<Card> cardOnT22 = new MutableLiveData<Card>();
    private MutableLiveData<Card> cardOnT23 = new MutableLiveData<Card>();
    private MutableLiveData<Integer> life1 = new MutableLiveData<>();
    private MutableLiveData<Integer> life2 = new MutableLiveData<>();

    private MutableLiveData<String> currentPlayer = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard11Playable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard12Playable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard13Playable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard21Playable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard22Playable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard23Playable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard11OnTable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard12OnTable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard13OnTable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard21OnTable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard22OnTable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCard23OnTable = new MutableLiveData<>();

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
    private MutableLiveData<Integer> card11Life = new MutableLiveData<>();
    private MutableLiveData<Integer> card12Life = new MutableLiveData<>();
    private MutableLiveData<Integer> card13Life = new MutableLiveData<>();
    private MutableLiveData<Integer> card21Life = new MutableLiveData<>();
    private MutableLiveData<Integer> card22Life = new MutableLiveData<>();
    private MutableLiveData<Integer> card23Life = new MutableLiveData<>();

    private  MutableLiveData<String> player2Type = new MutableLiveData<>();

    private MutableLiveData<Boolean> noCards1 = new MutableLiveData<>();

    private MutableLiveData<Boolean> noCards2 = new MutableLiveData<>();

    private MutableLiveData<Boolean> isCard11Selected = new MutableLiveData<>();

    private MutableLiveData<Boolean> isCard12Selected = new MutableLiveData<>();

    private MutableLiveData<Boolean> isCard13Selected = new MutableLiveData<>();

    private MutableLiveData<Boolean> isCard21Selected = new MutableLiveData<>();

    private MutableLiveData<Boolean> isCard22Selected = new MutableLiveData<>();

    private MutableLiveData<Boolean> isCard23Selected = new MutableLiveData<>();

    private MutableLiveData<String> userName2 = new MutableLiveData<>();

    private Game game;

    public LiveData<Integer> getDice1(){
        return numDice1;
    }

    public LiveData<Integer> getDice2(){
        return numDice2;
    }

    public LiveData<Card> getCard11(){
        return cardOnT11;
    }
    public LiveData<Card> getCard12(){
        return cardOnT12;
    }
    public LiveData<Card> getCard13(){
        return cardOnT13;
    }

    public LiveData<Card> getCard21(){
        return cardOnT21;
    }
    public LiveData<Card> getCard22(){
        return cardOnT22;
    }
    public LiveData<Card> getCard23(){
        return cardOnT23;
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
    public LiveData<Boolean> getIsCard11Playable(){
        return isCard11Playable;
    }
    public LiveData<Boolean> getIsCard12Playable(){
        return isCard12Playable;
    }
    public LiveData<Boolean> getIsCard13Playable(){
        return isCard13Playable;
    }
    public LiveData<Boolean> getIsCard21Playable(){
        return isCard21Playable;
    }
    public LiveData<Boolean> getIsCard22Playable(){
        return isCard22Playable;
    }
    public LiveData<Boolean> getIsCard23Playable(){
        return isCard23Playable;
    }
    public LiveData<Boolean> getIsCard11OnTable(){
        return isCard11OnTable;
    }
    public LiveData<Boolean> getIsCard12OnTable(){
        return isCard12OnTable;
    }
    public LiveData<Boolean> getIsCard13OnTable(){
        return isCard13OnTable;
    }
    public LiveData<Boolean> getIsCard21OnTable(){
        return isCard21OnTable;
    }
    public LiveData<Boolean> getIsCard22OnTable(){
        return isCard22OnTable;
    }
    public LiveData<Boolean> getIsCard23OnTable(){
        return isCard23OnTable;
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

    public MutableLiveData<Integer> getCard11Life() {
        return card11Life;
    }
    public MutableLiveData<Integer> getCard12Life() {
        return card12Life;
    }
    public MutableLiveData<Integer> getCard13Life() {
        return card13Life;
    }

    public MutableLiveData<Integer> getCard21Life() {
        return card21Life;
    }
    public MutableLiveData<Integer> getCard22Life() {
        return card22Life;
    }
    public MutableLiveData<Integer> getCard23Life() {
        return card23Life;
    }

    public MutableLiveData<String> getPlayer2Type() {
        return player2Type;
    }

    public MutableLiveData<Boolean> getNoCards1() {
        return noCards1;
    }
    public MutableLiveData<Boolean> getNoCards2() {
        return noCards2;
    }

    public MutableLiveData<Boolean> getIsCard11Selected() {
        return isCard11Selected;
    }

    public MutableLiveData<Boolean> getIsCard12Selected() {
        return isCard12Selected;
    }

    public MutableLiveData<Boolean> getIsCard13Selected() {
        return isCard13Selected;
    }

    public MutableLiveData<Boolean> getIsCard21Selected() {
        return isCard21Selected;
    }

    public MutableLiveData<Boolean> getIsCard22Selected() {
        return isCard22Selected;
    }

    public MutableLiveData<Boolean> getIsCard23Selected() {
        return isCard23Selected;
    }

    public MutableLiveData<String> getUserName2() {
        return userName2;
    }

    protected String myClassTag = this.getClass().getSimpleName();

    private int l1;
    private int l2;

    private int cl11;
    private int cl12;
    private int cl13;
    private int cl21;
    private int cl22;
    private int cl23;

    public PartidaViewModel() {
        game = new Game();
        isDice1Rolled.setValue(game.getBoard().getDiceRolledP1());
        isCard11Playable.setValue(false);
        isCard12Playable.setValue(false);
        isCard13Playable.setValue(false);
        isCard21Playable.setValue(false);
        isCard22Playable.setValue(false);
        isCard23Playable.setValue(false);
        isCard11OnTable.setValue(false);
        isCard12OnTable.setValue(false);
        isCard13OnTable.setValue(false);
        isCard21OnTable.setValue(false);
        isCard22OnTable.setValue(false);
        isCard23OnTable.setValue(false);
        isCard11Selected.setValue(false);
        isCard12Selected.setValue(false);
        isCard13Selected.setValue(false);
        isCard21Selected.setValue(false);
        isCard22Selected.setValue(false);
        isCard23Selected.setValue(false);
        noCards1.setValue(false);
        noCards2.setValue(false);
        life1.setValue(game.getBoard().getPlayer1().getLife());
        life2.setValue(game.getBoard().getPlayer2().getLife());
        turnChanged.setValue(true);
        l1 = game.getBoard().getPlayer1().getLife();
        l2 = game.getBoard().getPlayer2().getLife();
        objective1.setValue("none");
        objective2.setValue("none");
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            String id = mAuth.getCurrentUser().getUid();
            String url = GlobalInfo.getInstance().getFB_DB();
            db = FirebaseDatabase.getInstance(url);
            usrRef = db.getReference("users/" + id);
            usrRef.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = snapshot.getValue(String.class);
                    game.getBoard().getPlayer1().setName(name);
                    Log.d("NameP1: ", game.getBoard().getPlayer1().getName());
                    currentPlayer.setValue(game.getBoard().getPlayer1().getName());
                    userName2.setValue(game.getBoard().getPlayer2().getName());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }

    public void launchDice1() {
        if ((game.getTurn().getTurnValue() == true) && (game.getBoard().getDiceRolledP1() == false)) {
            game.dice1Actions();
            if ((game.getBoard().getPlayer1().getPlayerDice().getAcumValue() >= game.getCardT11().getCost()) && (game.getCardT11().isOnTable())){
                isCard11Playable.setValue(true);
            } else {
                isCard11Playable.setValue(false);
            }
            if ((game.getBoard().getPlayer1().getPlayerDice().getAcumValue() >= game.getCardT12().getCost()) && (game.getCardT12().isOnTable())){
                isCard12Playable.setValue(true);
            } else {
                isCard12Playable.setValue(false);
            }
            if ((game.getBoard().getPlayer1().getPlayerDice().getAcumValue() >= game.getCardT13().getCost()) && (game.getCardT13().isOnTable())){
                isCard13Playable.setValue(true);
            } else {
                isCard13Playable.setValue(false);
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
            if ((game.getBoard().getPlayer2().getPlayerDice().getAcumValue() >= game.getCardT21().getCost()) && (game.getCardT21().isOnTable())){
                isCard21Playable.setValue(true);
                canUseCard21 = true;
            } else {
                isCard21Playable.setValue(false);
                canUseCard21 = false;
            }
            if ((game.getBoard().getPlayer2().getPlayerDice().getAcumValue() >= game.getCardT22().getCost()) && (game.getCardT22().isOnTable())){
                isCard22Playable.setValue(true);
                canUseCard22 = true;
            } else {
                isCard22Playable.setValue(false);
                canUseCard22 = false;
            }
            if ((game.getBoard().getPlayer2().getPlayerDice().getAcumValue() >= game.getCardT23().getCost()) && (game.getCardT23().isOnTable())){
                isCard23Playable.setValue(true);
                canUseCard23 = true;
            } else {
                isCard23Playable.setValue(false);
                canUseCard23 = false;
            }
            int act = game.getBoard().getPlayer2().getPlayerDice().getOriginalValue();
            int acum = game.getBoard().getPlayer2().getPlayerDice().getAcumValue();
            actNum2.setValue(act);
            /*if (!((acum - act) == 0)){
                actPlusNewDiceNum2.setValue(String.valueOf(act) + " + " + String.valueOf(acum - act));
            } else {
                actPlusNewDiceNum2.setValue(String.valueOf(act));
            }*/
            numDice2.setValue(acum);
            isDice2Rolled.setValue(game.getBoard().getDiceRolledP2());
        }
    }

    public void setCardOnT1(){
        if ((game.getTurn().getTurnValue() == true) && (game.getBoard().getDiceRolledP1() == true) && (numDice1.getValue() != null)) {
            /*if (game.getBoard().getPlayer1().getDeckOfPlayer().deckSize() <= 0){
                game.getBoard().getPlayer1().setDeckOfPlayer(new Deck());
            }*/
            if (game.getBoard().getPlayer1().getDeckOfPlayer().deckSize() > 0) {
                /*for (int i = 0; i < game.getBoard().getPlayer1().getDeckOfPlayer().deckSize(); i++){
                    Log.d(myClassTag, game.getBoard().getPlayer1().getDeckOfPlayer().getDeckCards().get(i).getName());
                }*/
                if (!cardTable11) {
                    game.deck1Actions();
                    Card card11 = game.getCardT11();
                    cl11 = game.getCardT11().getLife();
                    card11Life.setValue(cl11);
                    cardOnT11.setValue(card11);
                    isCard11OnTable.setValue(true);
                    cardTable11 = true;
                    if (card11.getCost() <= numDice1.getValue()) {
                        isCard11Playable.setValue(true);
                    }
                } else if (!cardTable12){
                    game.deck1Actions();
                    Card card12 = game.getCardT12();
                    cl12 = game.getCardT12().getLife();
                    card12Life.setValue(cl12);
                    cardOnT12.setValue(card12);
                    isCard12OnTable.setValue(true);
                    cardTable12 = true;
                    if (card12.getCost() <= numDice1.getValue()){
                        isCard12Playable.setValue(true);
                    }
                } else if (!cardTable13){
                    game.deck1Actions();
                    Card card13 = game.getCardT13();
                    cl13 = game.getCardT13().getLife();
                    card13Life.setValue(cl13);
                    cardOnT13.setValue(card13);
                    isCard13OnTable.setValue(true);
                    cardTable13 = true;
                    if (card13.getCost() <= numDice1.getValue()){
                        isCard13Playable.setValue(true);
                    }
                }
            }
            Log.d("Deck1 Size:", String.valueOf(game.getBoard().getPlayer1().getDeckOfPlayer().deckSize()));
        }
    }

    public void setCardOnT2(){
        if ((game.getTurn().getTurnValue() == false) && (game.getBoard().getDiceRolledP2() == true) && (numDice2.getValue() != null)) {
            /*if (game.getBoard().getPlayer2().getDeckOfPlayer().deckSize() <= 0){
                game.getBoard().getPlayer2().setDeckOfPlayer(new Deck());
            }*/
            if (game.getBoard().getPlayer2().getDeckOfPlayer().deckSize() > 0) {
                /*for (int i = 0; i < game.getBoard().getPlayer2().getDeckOfPlayer().deckSize(); i++){
                    Log.d(myClassTag, game.getBoard().getPlayer2().getDeckOfPlayer().getDeckCards().get(i).getName());
                }*/
                if (!cardTable21){
                    game.deck2Actions();
                    Card card21 = game.getCardT21();
                    cl21 = game.getCardT21().getLife();
                    card21Life.setValue(cl21);
                    cardOnT21.setValue(card21);
                    isCard21OnTable.setValue(true);
                    cardTable21 = true;
                    if (card21.getCost() <= numDice2.getValue()){
                        isCard21Playable.setValue(true);
                        canUseCard21 = true;
                    }
                } else if (!cardTable22){
                    game.deck2Actions();
                    Card card22 = game.getCardT22();
                    cl22 = game.getCardT22().getLife();
                    card22Life.setValue(cl22);
                    cardOnT22.setValue(card22);
                    isCard22OnTable.setValue(true);
                    cardTable22 = true;
                    if (card22.getCost() <= numDice2.getValue()){
                        isCard22Playable.setValue(true);
                        canUseCard22 = true;
                    }
                } else if (!cardTable23){
                    game.deck2Actions();
                    Card card23 = game.getCardT23();
                    cl23 = game.getCardT23().getLife();
                    card23Life.setValue(cl23);
                    cardOnT23.setValue(card23);
                    isCard23OnTable.setValue(true);
                    cardTable23 = true;
                    if (card23.getCost() <= numDice2.getValue()){
                        isCard23Playable.setValue(true);
                        canUseCard23 = true;
                    }
                }
            }
            Log.d("Deck2 Size:", String.valueOf(game.getBoard().getPlayer2().getDeckOfPlayer().deckSize()));
        }
    }

    public void useCard11(){
        if ((game.getTurn().getTurnValue() == true) && (game.getBoard().isCardOnTableP11() == true)) {
            if (game.getBoard().getPlayer1().getPlayerDice().getAcumValue() >= game.getCardT11().getCost()) {
                game.card1Actions();
                int diceValue1 = game.getBoard().getPlayer1().getPlayerDice().getAcumValue();
                diceValue1 = diceValue1 - game.getCardT11().getCost();
                game.getBoard().getPlayer1().getPlayerDice().setDiceValue(diceValue1);
                numDice1.setValue(diceValue1);
                String objectiveP1 = objective1.getValue();
                if (objectiveP1.equals("player")){
                    l2 = l2 - game.getCardT11().getDamage();
                    game.getBoard().getPlayer2().setLife(l2);
                    life2.setValue(l2);
                } else if (objectiveP1.equals("card1")){
                    cl21 = cl21 - game.getCardT11().getDamage() + detectType(game.getCardT11().getType(), game.getCardT21().getType());
                    if(cl21<=0){
                        isCard21OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP21();
                        cardTable21 = false;
                        isCard21Playable.setValue(false);
                        canUseCard21 = false;
                        game.getCardT21().setOnTable(false);
                        l2 = l2 + cl21;
                        game.getBoard().getPlayer2().setLife(l2);
                        life2.setValue(l2);

                    }
                    game.getCardT21().setLife(cl21);
                    card21Life.setValue(cl21);
                } else if (objectiveP1.equals("card2")) {
                    cl22 = cl22 - game.getCardT11().getDamage() + detectType(game.getCardT11().getType(), game.getCardT22().getType());
                    if(cl22<=0){
                        isCard22OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP22();
                        cardTable22 = false;
                        isCard22Playable.setValue(false);
                        canUseCard22 = false;
                        game.getCardT22().setOnTable(false);
                        l2 = l2 + cl22;
                        game.getBoard().getPlayer2().setLife(l2);
                        life2.setValue(l2);

                    }
                    game.getCardT22().setLife(cl22);
                    card22Life.setValue(cl22);
                } else if (objectiveP1.equals("card3")) {
                    cl23 = cl23 - game.getCardT11().getDamage() + detectType(game.getCardT11().getType(), game.getCardT23().getType());
                    if(cl23<=0){
                        isCard23OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP23();
                        cardTable23 = false;
                        isCard23Playable.setValue(false);
                        canUseCard23 = false;
                        game.getCardT23().setOnTable(false);
                        l2 = l2 + cl23;
                        game.getBoard().getPlayer2().setLife(l2);
                        life2.setValue(l2);

                    }
                    game.getCardT23().setLife(cl23);
                    card23Life.setValue(cl23);
                }
                objective1.setValue("none");
                isCard11Selected.setValue(false);
                if (diceValue1 < game.getCardT11().getCost()){
                    isCard11Playable.setValue(false);
                } else {
                    isCard11Playable.setValue(true);
                }
                if (diceValue1 < game.getCardT12().getCost()){
                    isCard12Playable.setValue(false);
                } else {
                    isCard12Playable.setValue(true);
                }
                if (diceValue1 < game.getCardT13().getCost()){
                    isCard13Playable.setValue(false);
                } else {
                    isCard13Playable.setValue(true);
                }
            }
        }
        Log.d("baraja2", String.valueOf(game.getBoard().getPlayer2().getDeckOfPlayer().deckSize()));
        if (game.getBoard().getPlayer2().getDeckOfPlayer().deckSize() <= 0){
            if (!cardTable21 && !cardTable22 && !cardTable23){
                noCards2.setValue(true);
            }
        }
    }

    public void useCard12(){
        if ((game.getTurn().getTurnValue() == true) && (game.getBoard().isCardOnTableP12() == true)) {
            if (game.getBoard().getPlayer1().getPlayerDice().getAcumValue() >= game.getCardT12().getCost()) {
                game.card1Actions();
                int diceValue1 = game.getBoard().getPlayer1().getPlayerDice().getAcumValue();
                diceValue1 = diceValue1 - game.getCardT12().getCost();
                game.getBoard().getPlayer1().getPlayerDice().setDiceValue(diceValue1);
                numDice1.setValue(diceValue1);
                String objectiveP1 = objective1.getValue();
                if (objectiveP1.equals("player")){
                    l2 = l2 - game.getCardT12().getDamage();
                    game.getBoard().getPlayer2().setLife(l2);
                    life2.setValue(l2);
                } else if (objectiveP1.equals("card1")){
                    //cl2=game.getCardT2().getLife();
                    cl21 = cl21 - game.getCardT12().getDamage() + detectType(game.getCardT12().getType(), game.getCardT21().getType());
                    if(cl21<=0){
                        isCard21OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP21();
                        cardTable21 = false;
                        isCard21Playable.setValue(false);
                        canUseCard21 = false;
                        game.getCardT21().setOnTable(false);
                        l2 = l2 + cl21;
                        game.getBoard().getPlayer2().setLife(l2);
                        life2.setValue(l2);

                    }
                    game.getCardT21().setLife(cl21);
                    card21Life.setValue(cl21);
                } else if (objectiveP1.equals("card2")) {
                    cl22 = cl22 - game.getCardT12().getDamage() + detectType(game.getCardT12().getType(), game.getCardT22().getType());
                    if(cl22<=0){
                        isCard22OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP22();
                        cardTable22 = false;
                        isCard22Playable.setValue(false);
                        canUseCard22 = false;
                        game.getCardT22().setOnTable(false);
                        l2 = l2 + cl22;
                        game.getBoard().getPlayer2().setLife(l2);
                        life2.setValue(l2);

                    }
                    game.getCardT22().setLife(cl22);
                    card22Life.setValue(cl22);
                } else if (objectiveP1.equals("card3")) {
                    cl23 = cl23 - game.getCardT12().getDamage() + detectType(game.getCardT12().getType(), game.getCardT23().getType());
                    if(cl23<=0){
                        isCard23OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP23();
                        cardTable23 = false;
                        isCard23Playable.setValue(false);
                        canUseCard23 = false;
                        game.getCardT23().setOnTable(false);
                        l2 = l2 + cl23;
                        game.getBoard().getPlayer2().setLife(l2);
                        life2.setValue(l2);

                    }
                    game.getCardT23().setLife(cl23);
                    card23Life.setValue(cl23);
                }
                objective1.setValue("none");
                isCard12Selected.setValue(false);
                if (diceValue1 < game.getCardT11().getCost()){
                    isCard11Playable.setValue(false);
                } else {
                    isCard11Playable.setValue(true);
                }
                if (diceValue1 < game.getCardT12().getCost()){
                    isCard12Playable.setValue(false);
                } else {
                    isCard12Playable.setValue(true);
                }
                if (diceValue1 < game.getCardT13().getCost()){
                    isCard13Playable.setValue(false);
                } else {
                    isCard13Playable.setValue(true);
                }
            }
        }
        Log.d("baraja2", String.valueOf(game.getBoard().getPlayer2().getDeckOfPlayer().deckSize()));
        if (game.getBoard().getPlayer2().getDeckOfPlayer().deckSize() <= 0){
            if (!cardTable21 && !cardTable22 && !cardTable23){
                noCards2.setValue(true);
            }
        }
    }
    public void useCard13(){
        if ((game.getTurn().getTurnValue() == true) && (game.getBoard().isCardOnTableP13() == true)) {
            if (game.getBoard().getPlayer1().getPlayerDice().getAcumValue() >= game.getCardT13().getCost()) {
                game.card1Actions();
                int diceValue1 = game.getBoard().getPlayer1().getPlayerDice().getAcumValue();
                diceValue1 = diceValue1 - game.getCardT13().getCost();
                game.getBoard().getPlayer1().getPlayerDice().setDiceValue(diceValue1);
                numDice1.setValue(diceValue1);
                String objectiveP1 = objective1.getValue();
                if (objectiveP1.equals("player")){
                    l2 = l2 - game.getCardT13().getDamage();
                    game.getBoard().getPlayer2().setLife(l2);
                    life2.setValue(l2);
                } else if (objectiveP1.equals("card1")){
                    //cl2=game.getCardT2().getLife();
                    cl21 = cl21 - game.getCardT13().getDamage() + detectType(game.getCardT13().getType(), game.getCardT21().getType());
                    if(cl21<=0){
                        isCard21OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP21();
                        cardTable21 = false;
                        isCard21Playable.setValue(false);
                        canUseCard21 = false;
                        game.getCardT21().setOnTable(false);
                        l2 = l2 + cl21;
                        game.getBoard().getPlayer2().setLife(l2);
                        life2.setValue(l2);

                    }
                    game.getCardT21().setLife(cl21);
                    card21Life.setValue(cl21);
                } else if (objectiveP1.equals("card2")) {
                    cl22 = cl22 - game.getCardT13().getDamage() + detectType(game.getCardT13().getType(), game.getCardT22().getType());
                    if(cl22<=0){
                        isCard22OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP22();
                        cardTable22 = false;
                        isCard22Playable.setValue(false);
                        canUseCard22 = false;
                        game.getCardT22().setOnTable(false);
                        l2 = l2 + cl22;
                        game.getBoard().getPlayer2().setLife(l2);
                        life2.setValue(l2);

                    }
                    game.getCardT22().setLife(cl22);
                    card22Life.setValue(cl22);
                } else if (objectiveP1.equals("card3")) {
                    cl23 = cl23 - game.getCardT13().getDamage() + detectType(game.getCardT13().getType(), game.getCardT23().getType());
                    if(cl23<=0){
                        isCard23OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP23();
                        cardTable23 = false;
                        isCard23Playable.setValue(false);
                        canUseCard23 = false;
                        game.getCardT23().setOnTable(false);
                        l2 = l2 + cl23;
                        game.getBoard().getPlayer2().setLife(l2);
                        life2.setValue(l2);

                    }
                    game.getCardT23().setLife(cl23);
                    card23Life.setValue(cl23);
                }
                objective1.setValue("none");
                isCard13Selected.setValue(false);
                if (diceValue1 < game.getCardT11().getCost()){
                    isCard11Playable.setValue(false);
                } else {
                    isCard11Playable.setValue(true);
                }
                if (diceValue1 < game.getCardT12().getCost()){
                    isCard12Playable.setValue(false);
                } else {
                    isCard12Playable.setValue(true);
                }
                if (diceValue1 < game.getCardT13().getCost()){
                    isCard13Playable.setValue(false);
                } else {
                    isCard13Playable.setValue(true);
                }
            }
        }
        Log.d("baraja2", String.valueOf(game.getBoard().getPlayer2().getDeckOfPlayer().deckSize()));
        if (game.getBoard().getPlayer2().getDeckOfPlayer().deckSize() <= 0){
            if (!cardTable21 && !cardTable22 && !cardTable23){
                noCards2.setValue(true);
            }
        }
    }

    public void useCard21(){
        if ((game.getTurn().getTurnValue() == false) && (game.getBoard().isCardOnTableP21() == true)) {
            if (game.getBoard().getPlayer2().getPlayerDice().getAcumValue() >= game.getCardT21().getCost()) {
                game.card2Actions();
                int diceValue2 = game.getBoard().getPlayer2().getPlayerDice().getAcumValue();
                diceValue2 = diceValue2 - game.getCardT21().getCost();
                game.getBoard().getPlayer2().getPlayerDice().setDiceValue(diceValue2);
                numDice2.setValue(diceValue2);
                String objectiveP2 = objective2.getValue();
                if (objectiveP2.equals("player")){
                    l1 = l1 - game.getCardT21().getDamage();
                    game.getBoard().getPlayer2().setLife(l1);
                    life1.setValue(l1);
                } else if (objectiveP2.equals("card1")) {
                    //cl1=game.getCardT1().getLife();
                    cl11 = cl11 - game.getCardT21().getDamage() + detectType(game.getCardT21().getType(), game.getCardT11().getType());
                    if(cl11<=0){
                        isCard11OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP11();
                        cardTable11 = false;
                        isCard11Playable.setValue(false);
                        game.getCardT11().setOnTable(false);
                        l1 = l1 + cl11;
                        game.getBoard().getPlayer2().setLife(l1);
                        life1.setValue(l1);

                    }
                    game.getCardT11().setLife(cl11);
                    card11Life.setValue(cl11);
                }
                else if (objectiveP2.equals("card2")) {
                    cl12 = cl12 - game.getCardT21().getDamage() + detectType(game.getCardT21().getType(), game.getCardT12().getType());
                    if(cl12<=0){
                        isCard12OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP12();
                        cardTable12 = false;
                        isCard12Playable.setValue(false);
                        game.getCardT12().setOnTable(false);
                        l1 = l1 + cl12;
                        game.getBoard().getPlayer2().setLife(l1);
                        life1.setValue(l1);

                    }
                    game.getCardT12().setLife(cl12);
                    card12Life.setValue(cl12);
                } else if (objectiveP2.equals("card3")) {
                    cl13 = cl13 - game.getCardT21().getDamage() + detectType(game.getCardT21().getType(), game.getCardT13().getType());
                    if(cl13<=0){
                        isCard13OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP13();
                        cardTable13 = false;
                        isCard13Playable.setValue(false);
                        game.getCardT13().setOnTable(false);
                        l1 = l1 + cl13;
                        game.getBoard().getPlayer2().setLife(l1);
                        life1.setValue(l1);

                    }
                    game.getCardT13().setLife(cl13);
                    card13Life.setValue(cl13);
                }
                objective2.setValue("none");
                isCard21Selected.setValue(false);
                if (diceValue2 < game.getCardT21().getCost()){
                    isCard21Playable.setValue(false);
                    canUseCard21 = false;
                } else {
                    isCard21Playable.setValue(true);
                    canUseCard21 = true;
                }
                if (diceValue2 < game.getCardT22().getCost()){
                    isCard22Playable.setValue(false);
                    canUseCard22 = false;
                } else {
                    isCard22Playable.setValue(true);
                    canUseCard22 = true;
                }
                if (diceValue2 < game.getCardT23().getCost()){
                    isCard23Playable.setValue(false);
                    canUseCard23 = false;
                } else {
                    isCard23Playable.setValue(true);
                    canUseCard23 = true;
                }
            }
        }
        Log.d("baraja1", String.valueOf(game.getBoard().getPlayer1().getDeckOfPlayer().deckSize()));
        if (game.getBoard().getPlayer1().getDeckOfPlayer().deckSize() <= 0){
            if (!cardTable11 && !cardTable12 && !cardTable13){
                noCards1.setValue(true);
            }
        }
    }

    public void useCard22(){
        if ((game.getTurn().getTurnValue() == false) && (game.getBoard().isCardOnTableP22() == true)) {
            if (game.getBoard().getPlayer2().getPlayerDice().getAcumValue() >= game.getCardT22().getCost()) {
                game.card2Actions();
                int diceValue2 = game.getBoard().getPlayer2().getPlayerDice().getAcumValue();
                diceValue2 = diceValue2 - game.getCardT22().getCost();
                game.getBoard().getPlayer2().getPlayerDice().setDiceValue(diceValue2);
                numDice2.setValue(diceValue2);
                String objectiveP2 = objective2.getValue();
                if (objectiveP2.equals("player")){
                    l1 = l1 - game.getCardT22().getDamage();
                    game.getBoard().getPlayer2().setLife(l1);
                    life1.setValue(l1);
                } else if (objectiveP2.equals("card1")) {
                    //cl1=game.getCardT1().getLife();
                    cl11 = cl11 - game.getCardT22().getDamage() + detectType(game.getCardT22().getType(), game.getCardT11().getType());
                    if(cl11<=0){
                        isCard11OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP11();
                        cardTable11 = false;
                        isCard11Playable.setValue(false);
                        game.getCardT11().setOnTable(false);
                        l1 = l1 + cl11;
                        game.getBoard().getPlayer2().setLife(l1);
                        life1.setValue(l1);

                    }
                    game.getCardT11().setLife(cl11);
                    card11Life.setValue(cl11);
                }
                else if (objectiveP2.equals("card2")) {
                    cl12 = cl12 - game.getCardT22().getDamage() + detectType(game.getCardT22().getType(), game.getCardT12().getType());
                    if(cl12<=0){
                        isCard12OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP12();
                        cardTable12 = false;
                        isCard12Playable.setValue(false);
                        game.getCardT12().setOnTable(false);
                        l1 = l1 + cl12;
                        game.getBoard().getPlayer2().setLife(l1);
                        life1.setValue(l1);

                    }
                    game.getCardT12().setLife(cl12);
                    card12Life.setValue(cl12);
                } else if (objectiveP2.equals("card3")) {
                    cl13 = cl13 - game.getCardT22().getDamage() + detectType(game.getCardT22().getType(), game.getCardT13().getType());
                    if(cl13<=0){
                        isCard13OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP13();
                        cardTable13 = false;
                        isCard13Playable.setValue(false);
                        game.getCardT13().setOnTable(false);
                        l1 = l1 + cl13;
                        game.getBoard().getPlayer2().setLife(l1);
                        life1.setValue(l1);

                    }
                    game.getCardT13().setLife(cl13);
                    card13Life.setValue(cl13);
                }
                objective2.setValue("none");
                isCard22Selected.setValue(false);
                if (diceValue2 < game.getCardT21().getCost()){
                    isCard21Playable.setValue(false);
                    canUseCard21 = false;
                } else {
                    isCard21Playable.setValue(true);
                    canUseCard21 = true;
                }
                if (diceValue2 < game.getCardT22().getCost()){
                    isCard22Playable.setValue(false);
                    canUseCard22 = false;
                } else {
                    isCard22Playable.setValue(true);
                    canUseCard22 = true;
                }
                if (diceValue2 < game.getCardT23().getCost()){
                    isCard23Playable.setValue(false);
                    canUseCard23 = false;
                } else {
                    isCard23Playable.setValue(true);
                    canUseCard23 = true;
                }
            }
        }
        Log.d("baraja1", String.valueOf(game.getBoard().getPlayer1().getDeckOfPlayer().deckSize()));
        if (game.getBoard().getPlayer1().getDeckOfPlayer().deckSize() <= 0){
            if (!cardTable11 && !cardTable12 && !cardTable13){
                noCards1.setValue(true);
            }
        }
    }
    public void useCard23(){
        if ((game.getTurn().getTurnValue() == false) && (game.getBoard().isCardOnTableP23() == true)) {
            if (game.getBoard().getPlayer2().getPlayerDice().getAcumValue() >= game.getCardT23().getCost()) {
                game.card2Actions();
                int diceValue2 = game.getBoard().getPlayer2().getPlayerDice().getAcumValue();
                diceValue2 = diceValue2 - game.getCardT23().getCost();
                game.getBoard().getPlayer2().getPlayerDice().setDiceValue(diceValue2);
                numDice2.setValue(diceValue2);
                String objectiveP2 = objective2.getValue();
                if (objectiveP2.equals("player")){
                    l1 = l1 - game.getCardT23().getDamage();
                    game.getBoard().getPlayer2().setLife(l1);
                    life1.setValue(l1);
                } else if (objectiveP2.equals("card1")) {
                    //cl1=game.getCardT1().getLife();
                    cl11 = cl11 - game.getCardT23().getDamage() + detectType(game.getCardT23().getType(), game.getCardT11().getType());
                    if(cl11<=0){
                        isCard11OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP11();
                        cardTable11 = false;
                        isCard11Playable.setValue(false);
                        game.getCardT11().setOnTable(false);
                        l1 = l1 + cl11;
                        game.getBoard().getPlayer2().setLife(l1);
                        life1.setValue(l1);

                    }
                    game.getCardT11().setLife(cl11);
                    card11Life.setValue(cl11);
                }
                else if (objectiveP2.equals("card2")) {
                    cl12 = cl12 - game.getCardT23().getDamage() + detectType(game.getCardT23().getType(), game.getCardT12().getType());
                    if(cl12<=0){
                        isCard12OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP12();
                        cardTable12 = false;
                        isCard12Playable.setValue(false);
                        game.getCardT12().setOnTable(false);
                        l1 = l1 + cl12;
                        game.getBoard().getPlayer2().setLife(l1);
                        life1.setValue(l1);

                    }
                    game.getCardT12().setLife(cl12);
                    card12Life.setValue(cl12);
                } else if (objectiveP2.equals("card3")) {
                    cl13 = cl13 - game.getCardT23().getDamage() + detectType(game.getCardT23().getType(), game.getCardT13().getType());
                    if(cl13<=0){
                        isCard13OnTable.setValue(false);
                        game.getBoard().changeCardOnTableP13();
                        cardTable13 = false;
                        isCard13Playable.setValue(false);
                        game.getCardT13().setOnTable(false);
                        l1 = l1 + cl13;
                        game.getBoard().getPlayer2().setLife(l1);
                        life1.setValue(l1);

                    }
                    game.getCardT13().setLife(cl13);
                    card13Life.setValue(cl13);
                }
                objective2.setValue("none");
                isCard23Selected.setValue(false);
                if (diceValue2 < game.getCardT21().getCost()){
                    isCard21Playable.setValue(false);
                    canUseCard21 = false;
                } else {
                    isCard21Playable.setValue(true);
                    canUseCard21 = true;
                }
                if (diceValue2 < game.getCardT22().getCost()){
                    isCard22Playable.setValue(false);
                    canUseCard22 = false;
                } else {
                    isCard22Playable.setValue(true);
                    canUseCard22 = true;
                }
                if (diceValue2 < game.getCardT23().getCost()){
                    isCard23Playable.setValue(false);
                    canUseCard23 = false;
                } else {
                    isCard23Playable.setValue(true);
                    canUseCard23 = true;
                }
            }
        }
        Log.d("baraja1", String.valueOf(game.getBoard().getPlayer1().getDeckOfPlayer().deckSize()));
        if (game.getBoard().getPlayer1().getDeckOfPlayer().deckSize() <= 0){
            if (!cardTable11 && !cardTable12 && !cardTable13){
                noCards1.setValue(true);
            }
        }
    }

    public void changeTurn1(){
        if (game.getTurn().getTurnValue() == true) {
            game.endTurn1();
            String p2 = game.getBoard().getPlayer2().getName();
            //Log.d(myClassTag, "player2 name: " + p2);
            currentPlayer.setValue(p2);
            isDice1Rolled.setValue(false);
            isCard11Selected.setValue(false);
            isCard12Selected.setValue(false);
            isCard13Selected.setValue(false);
            if (isCard11Playable.getValue()){
                isCard11Playable.setValue(true);
            }
            if (isCard12Playable.getValue()){
                isCard12Playable.setValue(true);
            }
            if (isCard13Playable.getValue()){
                isCard13Playable.setValue(true);
            }
            turnChanged.setValue(false);
        }
    }

    public void changeTurn2() {
        if (game.getTurn().getTurnValue() == false) {
            game.endTurn2();
            String p1 = game.getBoard().getPlayer1().getName();
            currentPlayer.setValue(p1);
            isDice2Rolled.setValue(false);
            isCard21Selected.setValue(false);
            isCard22Selected.setValue(false);
            isCard23Selected.setValue(false);
            if (isCard21Playable.getValue()){
                isCard21Playable.setValue(true);
            }
            if (isCard22Playable.getValue()){
                isCard22Playable.setValue(true);
            }
            if (isCard23Playable.getValue()){
                isCard23Playable.setValue(true);
            }
            turnChanged.setValue(true);
        }
    }

    public void setPlayerMode(String mode){
        game.getBoard().getPlayer2().setType(mode);
        if (mode.equals("player")){
            game.getBoard().getPlayer2().setName("J2");
        } else {
            game.getBoard().getPlayer2().setName("IA");
        }
        player2Type.setValue(game.getBoard().getPlayer2().getType());
    }

    public void IAMode() {
        boolean finished = false;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        launchDice2();
        if (game.getBoard().getPlayer2().getDeckOfPlayer().deckSize() > 0){
            if (!cardTable21) {
                setCardOnT2();
            }
            if (!cardTable22) {
                setCardOnT2();
            }
            if (!cardTable23) {
                setCardOnT2();
            }
        }
        while (!finished) {
            if (l1 <= 0) {
                finished = true;
            }
            if (!canUseCard21 && !canUseCard22 && !canUseCard23 && (cardTable21 || cardTable22 || cardTable23)) {
                finished = true;
            }
            if ((cardTable21 && cardTable22 && cardTable23) && (canUseCard21 || canUseCard22 || canUseCard23)) {
                if (game.getCardT21().getDamage() >= game.getCardT22().getDamage()) {
                    if ((game.getCardT21().getDamage() >= game.getCardT23().getDamage()) && canUseCard21) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard21();
                    } else if ((game.getCardT23().getDamage() >= game.getCardT21().getDamage()) && canUseCard23) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard23();
                    } else if ((game.getCardT22().getDamage() >= game.getCardT23().getDamage()) && canUseCard22) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard22();
                    } else if ((game.getCardT23().getDamage() >= game.getCardT22().getDamage()) && canUseCard23) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard23();
                    } else if (canUseCard22) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard22();
                    }
                }
                if (game.getCardT22().getDamage() >= game.getCardT21().getDamage()) {
                    if ((game.getCardT22().getDamage() >= game.getCardT23().getDamage()) && canUseCard22) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard22();
                    } else if ((game.getCardT23().getDamage() >= game.getCardT22().getDamage()) && canUseCard23) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard23();
                    } else if ((game.getCardT21().getDamage() >= game.getCardT23().getDamage()) && canUseCard21) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard21();
                    } else if ((game.getCardT23().getDamage() >= game.getCardT21().getDamage()) && canUseCard23) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard23();
                    } else if (canUseCard21) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard21();
                    }
                }
                if (game.getCardT23().getDamage() >= game.getCardT21().getDamage()) {
                    if ((game.getCardT23().getDamage() >= game.getCardT22().getDamage()) && canUseCard23) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard23();
                    } else if ((game.getCardT22().getDamage() >= game.getCardT23().getDamage()) && canUseCard22) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard22();
                    } else if ((game.getCardT21().getDamage() >= game.getCardT22().getDamage()) && canUseCard21) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard21();
                    } else if ((game.getCardT22().getDamage() >= game.getCardT21().getDamage()) && canUseCard22) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard22();
                    } else if (canUseCard21) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard21();
                    }
                }
                if (canUseCard21) {
                    if (cardTable11) {
                        setObjective2("card1");
                    } else if (cardTable12) {
                        setObjective2("card2");
                    } else if (cardTable13) {
                        setObjective2("card3");
                    } else {
                        setObjective2("player");
                    }
                    useCard21();
                } else if (canUseCard22) {
                    if (cardTable11) {
                        setObjective2("card1");
                    } else if (cardTable12) {
                        setObjective2("card2");
                    } else if (cardTable13) {
                        setObjective2("card3");
                    } else {
                        setObjective2("player");
                    }
                    useCard22();
                } else if (canUseCard23) {
                    if (cardTable11) {
                        setObjective2("card1");
                    } else if (cardTable12) {
                        setObjective2("card2");
                    } else if (cardTable13) {
                        setObjective2("card3");
                    } else {
                        setObjective2("player");
                    }
                    useCard23();
                }
            } else if ((cardTable21 && cardTable22) && (canUseCard21 || canUseCard22)){
                if ((game.getCardT21().getDamage() >= game.getCardT22().getDamage()) && canUseCard21){
                    if (canUseCard21) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard21();
                    }
                } else {
                    if (canUseCard22) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard22();
                    }
                }
                if ((game.getCardT22().getDamage() >= game.getCardT21().getDamage()) && canUseCard22){
                    if (canUseCard22) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard22();
                    }
                } else {
                    if (canUseCard22) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard22();
                    }
                }
            } else if ((cardTable21 && cardTable23) && (canUseCard21 || canUseCard23)) {
                if ((game.getCardT21().getDamage() >= game.getCardT23().getDamage()) && canUseCard21){
                    if (canUseCard21) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard21();
                    }
                } else {
                    if (canUseCard23) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard23();
                    }
                }
                if ((game.getCardT23().getDamage() >= game.getCardT21().getDamage()) && canUseCard23){
                    if (canUseCard23) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard23();
                    }
                } else {
                    if (canUseCard21) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard21();
                    }
                }
            } else if ((cardTable22 && cardTable23) && (canUseCard22 || canUseCard23)) {
                if ((game.getCardT22().getDamage() >= game.getCardT23().getDamage()) && canUseCard22){
                    if (canUseCard23) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard23();
                    }
                } else {
                    if (canUseCard22) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard22();
                    }
                }
                if ((game.getCardT23().getDamage() >= game.getCardT22().getDamage()) && canUseCard23){
                    if (canUseCard22) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard22();
                    }
                } else {
                    if (canUseCard23) {
                        if (cardTable11) {
                            setObjective2("card1");
                        } else if (cardTable12) {
                            setObjective2("card2");
                        } else if (cardTable13) {
                            setObjective2("card3");
                        } else {
                            setObjective2("player");
                        }
                        useCard23();
                    }
                }
            } else if (cardTable21 && canUseCard21) {
                if (canUseCard21) {
                    if (cardTable11) {
                        setObjective2("card1");
                    } else if (cardTable12) {
                        setObjective2("card2");
                    } else if (cardTable13) {
                        setObjective2("card3");
                    } else {
                        setObjective2("player");
                    }
                    useCard21();
                }
            } else if (cardTable22 && canUseCard22) {
                if (canUseCard22) {
                    if (cardTable11) {
                        setObjective2("card1");
                    } else if (cardTable12) {
                        setObjective2("card2");
                    } else if (cardTable13) {
                        setObjective2("card3");
                    } else {
                        setObjective2("player");
                    }
                    useCard22();
                }
            } else if (cardTable23 && canUseCard23) {
                if (canUseCard23) {
                    if (cardTable11) {
                        setObjective2("card1");
                    } else if (cardTable12) {
                        setObjective2("card2");
                    } else if (cardTable13) {
                        setObjective2("card3");
                    } else {
                        setObjective2("player");
                    }
                    useCard23();
                }
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        changeTurn2();
    }

    public void setObjective1(String objective1) {
        String objectiveP1 = objective1;
        this.objective1.setValue(objectiveP1);
    }
    public void setObjective2(String objective2) {
        String objectiveP2 = objective2;
        this.objective2.setValue(objectiveP2);
    }

    public int detectType(String card1Type, String card2Type){
        if ((card1Type.equalsIgnoreCase("Technology") && (card2Type.equalsIgnoreCase("Science")))){
            return -1;
        } else if ((card1Type.equalsIgnoreCase("Science") && (card2Type.equalsIgnoreCase("Nature")))){
            return -1;
        } else if ((card1Type.equalsIgnoreCase("Nature") && (card2Type.equalsIgnoreCase("Technology")))) {
            return -1;
        } else if ((card1Type.equalsIgnoreCase("Technology") && (card2Type.equalsIgnoreCase("Nature")))){
            return +1;
        } else if ((card1Type.equalsIgnoreCase("Nature") && (card2Type.equalsIgnoreCase("Science")))) {
            return +1;
        } else if ((card1Type.equalsIgnoreCase("Science") && (card2Type.equalsIgnoreCase("Technology")))) {
            return +1;
        } else {
            return 0;
        }
    }

    public boolean selectedCard(boolean status){
        if (status){
            status = false;
        } else {
            status= true;
        }
        return status;
    }

    public void changeCard11Status() {
        isCard11Selected.setValue(true);
        if (isCard12Selected.getValue()){
            isCard12Selected.setValue(false);
            if (isCard12Playable.getValue()){
                isCard12Playable.setValue(true);
            }
        }
        if (isCard13Selected.getValue()){
            isCard13Selected.setValue(false);
            if (isCard13Playable.getValue()){
                isCard13Playable.setValue(true);
            }
        }
    }
    public void changeCard12Status() {
        isCard12Selected.setValue(true);
        if (isCard11Selected.getValue()){
            isCard11Selected.setValue(false);
            if (isCard11Playable.getValue()){
                isCard11Playable.setValue(true);
            }
        }
        if (isCard13Selected.getValue()){
            isCard13Selected.setValue(false);
            if (isCard13Playable.getValue()){
                isCard13Playable.setValue(true);
            }
        }
    }
    public void changeCard13Status() {
        isCard13Selected.setValue(true);
        if (isCard11Selected.getValue()){
            isCard11Selected.setValue(false);
            if (isCard11Playable.getValue()){
                isCard11Playable.setValue(true);
            }
        }
        if (isCard12Selected.getValue()){
            isCard12Selected.setValue(false);
            if (isCard12Playable.getValue()){
                isCard12Playable.setValue(true);
            }
        }
    }
    public void changeCard21Status() {
        isCard21Selected.setValue(true);
        if (isCard22Selected.getValue()){
            isCard22Selected.setValue(false);
            if (isCard22Playable.getValue()){
                isCard22Playable.setValue(true);
            }
        }
        if (isCard23Selected.getValue()){
            isCard23Selected.setValue(false);
            if (isCard23Playable.getValue()){
                isCard23Playable.setValue(true);
            }
        }
    }
    public void changeCard22Status() {
        isCard22Selected.setValue(true);
        if (isCard21Selected.getValue()){
            isCard21Selected.setValue(false);
            if (isCard21Playable.getValue()){
                isCard21Playable.setValue(true);
            }
        }
        if (isCard23Selected.getValue()){
            isCard23Selected.setValue(false);
            if (isCard23Playable.getValue()){
                isCard23Playable.setValue(true);
            }
        }
    }
    public void changeCard23Status() {
        isCard23Selected.setValue(true);
        if (isCard21Selected.getValue()){
            isCard21Selected.setValue(false);
            if (isCard21Playable.getValue()){
                isCard21Playable.setValue(true);
            }
        }
        if (isCard22Selected.getValue()){
            isCard22Selected.setValue(false);
            if (isCard22Playable.getValue()){
                isCard22Playable.setValue(true);
            }
        }
    }

    public void setName1(String name1) {
        game.getBoard().getPlayer1().setName(name1);
    }
}
