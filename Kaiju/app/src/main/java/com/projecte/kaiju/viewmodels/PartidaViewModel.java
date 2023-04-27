package com.projecte.kaiju.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.projecte.kaiju.models.Card;
import com.projecte.kaiju.models.Game;

public class PartidaViewModel extends ViewModel {
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

    public PartidaViewModel() {
        game = new Game();
        isDice1Rolled.setValue(game.getBoard().getDiceRolledP1());
        isCard1Playable.setValue(false);
        isCard2Playable.setValue(false);
        life1.setValue(game.getBoard().getPlayer1().getLife());
        life2.setValue(game.getBoard().getPlayer2().getLife());
        turnChanged.setValue(true);
    }

    public void launchDice1() {
        if ((game.getTurn().getTurnValue() == true) && (game.getBoard().getDiceRolledP1() == false)) {
            game.dice1Actions();
            if ((game.getBoard().getPlayer1().getPlayerDice().getAcumValue() >= game.getCardT1().getCost()) && (game.getBoard().isCardOnTableP1() == true)){
                isCard1Playable.setValue(true);
            }
            int res = game.getBoard().getPlayer1().getPlayerDice().getAcumValue();
            numDice1.setValue(res);
            isDice1Rolled.setValue(game.getBoard().getDiceRolledP1());
        }
    }

    public void launchDice2() {
        if ((game.getTurn().getTurnValue() == false) && (game.getBoard().getDiceRolledP2() == false)) {
            game.dice2Actions();
            if ((game.getBoard().getPlayer2().getPlayerDice().getAcumValue() >= game.getCardT2().getCost()) && (game.getBoard().isCardOnTableP2() == true)){
                isCard2Playable.setValue(true);
            }
            int res = game.getBoard().getPlayer2().getPlayerDice().getAcumValue();
            numDice2.setValue(res);
            isDice2Rolled.setValue(game.getBoard().getDiceRolledP2());
        }
    }

    public void setCardOnT1(){
        if ((game.getTurn().getTurnValue() == true) && (game.getBoard().isCardOnTableP1() == false) && (game.getBoard().getDiceRolledP1() == true) && (numDice1.getValue() != null)) {
            if (game.getBoard().getPlayer1().getDeckOfPlayer().deckSize() != 0) {
                game.deck1Actions();
                Card card1 = game.getCardT1();
                game.setCardT1(card1);
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
                int l2 = game.getBoard().getPlayer2().getLife();
                int d1 = game.getBoard().getPlayer1().getPlayerDice().getAcumValue();
                isCard1OnTable.setValue(false);
                isCard1Playable.setValue(false);
                numDice1.setValue(d1);
                life2.setValue(l2);
                if(l2 <= 0){
                    winner.setValue(true);
                }
            }
        }
    }

    public void useCard2(){
        if ((game.getTurn().getTurnValue() == false) && (game.getBoard().isCardOnTableP2() == true)) {
            if (game.getBoard().getPlayer2().getPlayerDice().getAcumValue() >= game.getCardT2().getCost()) {
                game.card2Actions();
                int l1 = game.getBoard().getPlayer1().getLife();
                int d2 = game.getBoard().getPlayer2().getPlayerDice().getAcumValue();
                isCard2OnTable.setValue(false);
                isCard2Playable.setValue(false);
                life1.setValue(l1);
                numDice2.setValue(d2);
                if(l1 <= 0){
                    winner.setValue(false);
                }
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

    public void changeTurn2(){
        if (game.getTurn().getTurnValue() == false) {
            game.endTurn2();
            String p1 = game.getBoard().getPlayer1().getName();
            currentPlayer.setValue(p1);
            isDice2Rolled.setValue(false);
            turnChanged.setValue(true);
        }
    }
}
