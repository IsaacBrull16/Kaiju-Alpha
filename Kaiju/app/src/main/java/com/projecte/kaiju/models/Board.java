package com.projecte.kaiju.models;

/**
 * @author: Álex Pellitero García
 * @version: 17/02/2023/A
 * Esta clase define el tablero del juego, que serán 3 cartas por jugador
 */

public class Board {

    //private String LastMode;

    private boolean cardTableP1 = false;
    private boolean cardTableP2 = false;
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

    public void changeCardOnTableP1(){
        if (cardTableP1 == true){
            this.cardTableP1 = false;
        } else {
            this.cardTableP1 = true;
        }
    }

    /**
     * Método que nos retornará el estado de la carta en el tablero del jugador 1
     * @return
     */

    public boolean isCardOnTableP1(){
        return this.cardTableP1;
    }

    /**
     * Método que nos permitirá decidir si hay una carta o no en el tablero del jugador 2
     */

    public void changeCardOnTableP2(){
        if (cardTableP2 == true){
            this.cardTableP2 = false;
        } else {
            this.cardTableP2 = true;
        }
    }

    /**
     * Método que nos retornará el estado de la carta en el tablero del jugador 2
     * @return
     */

    public boolean isCardOnTableP2(){
        return this.cardTableP2;
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

    /*public void getLastMode(final Board.OnDataLoadedListener listener){
        playRef.child("play_mode").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                LastMode = snapshot.getValue(String.class);
                Log.d(myClassTag, "Play mode: " + LastMode);

                listener.onDataLoaded(LastMode);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void assignPlayer2(String mode){
        if (mode.equals("IA")){
            player2.setName("IA");
            player2.setType("IA");
        } else {
            player2.setType("player");
        }
        String type = player2.getType();
        Log.d(myClassTag, "Player 2 type: " + type);
    }
    public interface OnDataLoadedListener {
        void onDataLoaded(String LastMode);
    }*/

}