package com.projecte.kaiju.models;

/**
 * @author: Álex Pellitero García
 * @version: 17/02/2023/A
 * Esta clase define el tablero del juego, que serán 3 cartas por jugador
 */

public class Board {

    //private String LastMode;

    private boolean cardTableP11 = false;
    private boolean cardTableP12 = false;
    private boolean cardTableP13 = false;
    private boolean cardTableP21 = false;
    private boolean cardTableP22 = false;
    private boolean cardTableP23 = false;
    private boolean diceRolledP1 = false;
    private boolean diceRolledP2 = false;
    private Player player1;
    private Player player2;

    protected String myClassTag = this.getClass().getSimpleName();

    /*private FirebaseAuth mAuth;

    private FirebaseDatabase db;

    private DatabaseReference playRef;*/


    public Board() {
        //final CountDownLatch latch = new CountDownLatch(1);
        this.player1 = new Player("J1");
        this.player2 = new Player("J2");
        player1.setLife(25);
        player2.setLife(25);
        player2.setType("player");

        if (this.player2.getType().equals("IA")){
            this.player2.setName("IA");
        }

        /*LastMode = GlobalInfo.getInstance().getLastMode();
        Log.d(myClassTag, LastMode);*/

        /*mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            String id = mAuth.getCurrentUser().getUid();
            String url = GlobalInfo.getInstance().getFB_DB();
            db = FirebaseDatabase.getInstance(url);
            playRef = db.getReference(id);

            Board.OnDataLoadedListener listener = new Board.OnDataLoadedListener() {
                @Override
                public void onDataLoaded(String LastMode) {
                    assignPlayer2(LastMode);
                    //latch.countDown();

                }

            };
            getLastMode(listener);
            /*try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }*/

    }

    /**
     * Método getter de jugador 1
     * @return
     */

    public Player getPlayer1(){
        return this.player1;
    }

    /**
     * Método getter de jugador 2
     * @return
     */

    public Player getPlayer2(){
        return this.player2;
    }

    /**
     * Método que nos permitirá decidir si hay una carta o no en el tablero del jugador 1
     */

    public void changeCardOnTableP11(){
        if (cardTableP11 == true){
            this.cardTableP11 = false;
        } else {
            this.cardTableP11 = true;
        }
    }

    public void changeCardOnTableP12(){
        if (cardTableP12 == true){
            this.cardTableP12 = false;
        } else {
            this.cardTableP12 = true;
        }
    }

    public void changeCardOnTableP13(){
        if (cardTableP13 == true){
            this.cardTableP13 = false;
        } else {
            this.cardTableP13 = true;
        }
    }

    /**
     * Método que nos retornará el estado de la carta en el tablero del jugador 1
     * @return
     */

    public boolean isCardOnTableP11(){
        return this.cardTableP11;
    }
    public boolean isCardOnTableP12(){
        return this.cardTableP12;
    }
    public boolean isCardOnTableP13(){
        return this.cardTableP13;
    }

    /**
     * Método que nos permitirá decidir si hay una carta o no en el tablero del jugador 2
     */

    public void changeCardOnTableP21(){
        if (cardTableP21 == true){
            this.cardTableP21 = false;
        } else {
            this.cardTableP21 = true;
        }
    }

    public void changeCardOnTableP22(){
        if (cardTableP22 == true){
            this.cardTableP22 = false;
        } else {
            this.cardTableP22 = true;
        }
    }

    public void changeCardOnTableP23(){
        if (cardTableP23 == true){
            this.cardTableP23 = false;
        } else {
            this.cardTableP23 = true;
        }
    }

    /**
     * Método que nos retornará el estado de la carta en el tablero del jugador 2
     * @return
     */

    public boolean isCardOnTableP21(){
        return this.cardTableP21;
    }
    public boolean isCardOnTableP22(){
        return this.cardTableP22;
    }
    public boolean isCardOnTableP23(){
        return this.cardTableP23;
    }

    /**
     * Método que nos retornará el estado del dado en el tablero del jugador 1
     * @return
     */

    public boolean getDiceRolledP1(){
        return this.diceRolledP1;
    }

    /**
     * Método que nos permitirá decidir si hay un dado o no en el tablero del jugador 1
     */

    public void changeDiceRolledP1(){
        if (diceRolledP1 == true){
            this.diceRolledP1 = false;
        } else {
            this.diceRolledP1 = true;
        }
    }

    /**
     * Método que nos retornará el estado del dado en el tablero del jugador 2
     * @return
     */

    public boolean getDiceRolledP2(){
        return this.diceRolledP2;
    }

    /**
     * Método que nos permitirá decidir si hay un dado o no en el tablero del jugador 2
     */

    public void changeDiceRolledP2(){
        if (diceRolledP2 == true){
            this.diceRolledP2 = false;
        } else {
            this.diceRolledP2 = true;
        }
    }

}