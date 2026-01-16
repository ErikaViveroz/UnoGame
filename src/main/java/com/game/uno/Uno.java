package com.game.uno;

import com.game.uno.dao.PlayerRepository;
import com.game.uno.model.Card;
import com.game.uno.model.Player;
import com.game.uno.model.Turn;
import com.game.uno.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Random;
import java.util.stream.IntStream;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Erika Viveroz
 */

@Component
@Lazy
public class Uno extends JFrame implements MouseListener{
	
	/*Inicialización de Componentes*/
	private JButton jButtonDrawCard;
    private JButton jButtonPassTurn;
    private JButton jButtonRestart;
    private JButton jButtonExit;
    private JLabel jLabelCard;
    private JLabel jLabelPlayer;
    private JLabel jLabelPlayerScoreA;
    private JLabel jLabelPlayerScoreB;
    private javax.swing.JPanel jPanelPlayerA;
    private javax.swing.JPanel jPanelPlayerB;
    private javax.swing.JTable jDataTable;
    private javax.swing.JScrollPane jScrollPanel;
	
    /*Inicialización de varibles*/
    int playerCardsA = 7,playerCardsB = 7;
    int indexInitialLetter = playerCardsA + playerCardsB;
    int remainingCards = 21;
    int usedCards = 0;
    int index[]=new int[36];
    int playerScore1 = 0, playerScore2 = 0;
    JButton playerButtonsA []=new JButton[playerCardsA];
    JButton playerButtonsB []=new JButton[playerCardsB];
    String colorsUno[] = {"am", "az","ve","ro"};
    String numbersUno[] = {"1","2","3","4","5","6","7","8","9"};
    Resource resource = new ClassPathResource("static/Cards");
    File folder = resource.getFile();
    String route = folder.getAbsolutePath() + File.separator;

    private Turn turn;
    Player playerA, playerB;
    @Autowired
    private final PlayerService playerService;
    Card fullDeck[];
    Card remainingDeck[];
    Card currentCard;


    public Uno(PlayerService playerService) throws IOException {
        this.playerService = playerService;
    }

    public void initUI() throws IOException {
        initComponents();
        viewList();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        setSize(screenWidth, screenHeight); 
        add(jPanelPlayerB);
        add(jPanelPlayerA);

        /*Creación Grid y dimensiones*/
        jPanelPlayerA.setLayout(new GridLayout(1,playerCardsA));
        jPanelPlayerA.setSize(new Dimension((76*playerCardsA),119));
        jPanelPlayerB.setLayout(new GridLayout(1,playerCardsB));
        jPanelPlayerB.setSize(new Dimension((76*playerCardsB),119));
        
        for(int i=0;i<playerButtonsA.length;i++){
        	playerButtonsA[i]=new JButton();
        	playerButtonsA[i].setBounds(0,0,76,119);
        	playerButtonsA[i].addMouseListener(this);
        	jPanelPlayerA.add(playerButtonsA[i]);
            playerButtonsB[i]=new JButton();
            playerButtonsB[i].setBounds(0,0,76,119);
            playerButtonsB[i].addMouseListener(this);
            jPanelPlayerB.add(playerButtonsB[i]);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

    	/*Se crea toda la interfaz, botones, etiquetas, paneles y adentro los botones*/
    	jPanelPlayerA = new javax.swing.JPanel();
    	jLabelCard = new JLabel();
        jPanelPlayerB = new javax.swing.JPanel();
        jLabelPlayer = new JLabel();
        jButtonDrawCard = new JButton();
        jButtonPassTurn = new JButton();
        jButtonRestart = new JButton();
        jButtonExit = new JButton();
        jLabelPlayerScoreA = new JLabel();
        jLabelPlayerScoreB = new JLabel();
        jScrollPanel = new javax.swing.JScrollPane(); 
        jDataTable = new javax.swing.JTable();
        
        jDataTable.setModel(new DefaultTableModel(
                new Object [][] {
                    {null, null, null, null}, 
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Id player", "Name", "Score", "Date" 
                }
            ));
        jScrollPanel.setViewportView(jDataTable);
        Dimension fixedSize = new Dimension(380, 80);

        // 4. Asigna el tamaño fijo al JScrollPane
        jScrollPanel.setPreferredSize(fixedSize);
        jScrollPanel.setMinimumSize(fixedSize);
        jScrollPanel.setMaximumSize(fixedSize);

        /*
         * // Ajustar el ancho de cada columna
			jDataTable.getColumnModel().getColumn(0).setPreferredWidth(60);   // Id player
			jDataTable.getColumnModel().getColumn(1).setPreferredWidth(150);  // Name
			jDataTable.getColumnModel().getColumn(2).setPreferredWidth(80);   // Score
			jDataTable.getColumnModel().getColumn(3).setPreferredWidth(120);  // Date
			
			*/
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelPlayerA.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanelPlayerA);
        jPanelPlayerA.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        jLabelCard.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        jPanelPlayerB.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanelPlayerB);
        jPanelPlayerB.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        jLabelPlayer.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        jLabelPlayer.setText("Inicia el jugador1");

        jButtonDrawCard.setText("Draw");
        jButtonDrawCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDrawCardActionPerformed(evt);
            }
        });

        jButtonPassTurn.setText("Pass");
        jButtonPassTurn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPassTurnActionPerformed(evt);
            }
        });

        jButtonRestart.setText("Restart");
        jButtonRestart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButtonRestartMouseClicked(evt);
            }
        });

        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jLabelPlayerScoreA.setText("Puntuación: 0");

        jLabelPlayerScoreB.setText("Puntuación: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addComponent(jLabelPlayer)
                .addGap(53, 53, 53)
                .addComponent(jLabelCard, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDrawCard)
                    .addComponent(jButtonPassTurn))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonRestart)
                    .addComponent(jButtonExit))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                		.addComponent(jScrollPanel))
                .addContainerGap(97, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanelPlayerB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelPlayerA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelPlayerScoreB, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPlayerScoreA, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 540, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanelPlayerB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabelPlayerScoreB)
                        .addGap(10, 10, 10)
                        .addComponent(jLabelPlayer))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCard, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonDrawCard)
                                    .addComponent(jButtonRestart))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonPassTurn)
                                    .addComponent(jButtonExit))
                                .addComponent(jScrollPanel)))))
                .addGap(10, 10, 10)
                .addComponent(jLabelPlayerScoreA)
                .addGap(0, 0, 0) 
                .addComponent(jPanelPlayerA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void startGame(String playerName1, String playerName2) {
        playerA = new Player();
        playerA.setName(playerName1);
        playerA.setScore(0);

        playerB = new Player();
        playerB.setName(playerName2);
        playerB.setScore(0);

        turn = new Turn(playerA, playerB);

        start();
    }

    String validateName(String title) {
        String name;
        do {
            name = JOptionPane.showInputDialog(null, title + ", ingresa tu nombre:", "Nombre de jugador", JOptionPane.QUESTION_MESSAGE);
            if (name == null) {
                message("⚠️ Debes ingresar un nombre para continuar.");
            } else if (name.trim().isEmpty()) {
                message("⚠️ El nombre no puede estar vacío.");
                name = null;
            }
        } while (name == null);
        return name.trim();
    }

    public void start() {
    	/*Se generan los números aletorios para asignarlos a las cartas
    	 * se asignan cartas al mazo restante, la carta inicial y las cartas de los jugadores */
    	
        int k=7;
        int totalUsed = playerCardsA + playerCardsB + 1;
        fullDeck = new Card[colorsUno.length * numbersUno.length];
        remainingDeck = new Card[fullDeck.length - totalUsed];
        generateRandoms();
        manageCards();
        currentCard = fullDeck[index[indexInitialLetter]];
        jLabelCard.setName(currentCard.getImagePath());
        jLabelCard.setIcon(
                currentCard.getScaledIcon(
                        jLabelCard.getWidth(),
                        jLabelCard.getHeight()
                )
        );
        
        jLabelPlayer.setText("Inicia " + playerA.getName());

        for (int i = 0; i < playerButtonsA.length; i++) {

            Card cardA = fullDeck[index[i]];
            playerButtonsA[i].putClientProperty("card", cardA);
            playerButtonsA[i].setIcon(
                    cardA.getScaledIcon(
                            playerButtonsA[i].getWidth(),
                            playerButtonsA[i].getHeight()
                    )
            );

            Card cardB = fullDeck[index[k]];
            playerButtonsB[i].putClientProperty("card", cardB);
            playerButtonsB[i].setIcon(
                    cardB.getScaledIcon(
                            playerButtonsB[i].getWidth(),
                            playerButtonsB[i].getHeight()
                    )
            );
            k++;
        }

        for(int i=0;i<remainingDeck.length;i++){
        	remainingDeck[i]=fullDeck[index[totalUsed]];
            totalUsed++;
        }
        
        jButtonDrawCard.setEnabled(true);
        jButtonPassTurn.setEnabled(true);
    }
    
    public void generateRandoms(){ 
    	/*Se genera un arreglo con numeros aleatorios para reorganizar las cartas*/
        Random random = new Random();
        int N[] = IntStream.range(0, 36).toArray();
            for(int i = N.length; i > 0; i--){
                int j = random.nextInt(i);
                int tmp = N[i-1];
                N[i-1] = N[j];
                N[j] = tmp;
            }
            if (index.length < N.length) {
                throw new IllegalStateException("El arreglo 'index' es más pequeño que la cantidad de números generados.");
            }
            
            for(int i=0;i<N.length;i++){
                index[i] = N[i];
            }
    }

    public void manageCards() {
        int x = 0;
        for (String color : colorsUno) {
            for (String number : numbersUno) {
                if (x >= fullDeck.length) break;
                fullDeck[x] = new Card(color, number, route);
                x++;
            }
        }
    }

    private void jButtonDrawCardActionPerformed(java.awt.event.ActionEvent evt) {
    	/*Lógica para tomar una carta del mazo*/

    	boolean card=true;
    	
        if(usedCards == remainingCards){
            card=false;
            message("⚠️ Ya no hay más imagenes en el monto");
            jButtonDrawCard.setEnabled(false);
            usedCards = 0;
        }
        
        if(turn.getCurrentPlayer() == playerA)
        	drawCard(playerButtonsA, card);
        else 
        	drawCard(playerButtonsB, card);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void drawCard(JButton[] playerButtons, boolean card) {
        int i = 0;

        while (card) {
            if (i >= playerButtons.length) {
                message("⚠️ El jugador no puede tener más de 7 cartas.");
                break;
            }

            if (playerButtons[i].getIcon() == null) {
                playerButtons[i].setEnabled(true);

                Card newCard = remainingDeck[usedCards];
                playerButtons[i].putClientProperty("card", newCard);
                playerButtons[i].setIcon(
                        newCard.getScaledIcon(
                                playerButtons[i].getWidth(),
                                playerButtons[i].getHeight()
                        )
                );

                usedCards++;
                card = false;
            }
            i++;
        }
    }

    private void jButtonPassTurnActionPerformed(java.awt.event.ActionEvent evt) {
    	/*Lógica para ceder el turno*/
        turn.shiftChange();
        jLabelPlayer.setText("Va " + turn.getCurrentPlayer().getName());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonRestartMouseClicked(MouseEvent evt) {
    	jLabelCard.setIcon(null);
    	
        for(int i=0; i<playerButtonsA.length; i++){
        	playerButtonsA[i].setIcon(null);
        	playerButtonsB[i].setIcon(null);
        	playerButtonsA[i].setEnabled(true);
        	playerButtonsB[i].setEnabled(true);
            
        }
        start();
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {
        saveScore();
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    public void winningPlayer(String playerName, JButton[] playerButtons){
    	/*Corrobora en cada movimiento si ha ganado el jugador1*/
        int c1=0;
        for(int i = 0; i<playerButtons.length; i++){
            if(playerButtons[i].getIcon()==null){
                c1++;
            }
        }
        if(c1==playerButtons.length){
            message("Gana el jugador: "+ playerName);
            message("Apague o Reinicie el juego");
            if (turn.getCurrentPlayer() == playerA)
                playerA.setScore(playerA.getScore() + 1520);
            else 
                playerB.setScore(playerB.getScore() + 1520);

            for (JButton b : playerButtonsA) b.setEnabled(false);
            for (JButton b : playerButtonsB) b.setEnabled(false);

            jButtonDrawCard.setEnabled(false);
            jButtonPassTurn.setEnabled(false);
            jLabelPlayerScoreA.setText("Puntuación: " + playerA.getScore());
            jLabelPlayerScoreB.setText("Puntuación: " + playerB.getScore());
        }
        
        if(c1==6){
            message("UNO " + playerName + "!");
        }
          
    }    
    
    @Override
    public void mouseClicked(MouseEvent e) {//Evento para cada selección de tarjeta
	    if(turn.getCurrentPlayer() == playerA){ 
	    	clickedCard(playerButtonsA, e);
	    }else{
	    	clickedCard(playerButtonsB, e);
	    }
    }

    public void clickedCard(JButton[] playerButtons, MouseEvent e) {
        for (JButton btn : playerButtons) {
            if (e.getSource() == btn) {

                Card card = (Card) btn.getClientProperty("card");
                if (card == null) {
                    message("⚠️ Este botón no tiene una carta asignada.");
                    return;
                }

                if (verifyMatch(card)) {
                    btn.setIcon(null);
                    btn.setEnabled(false);
                    btn.putClientProperty("card", null);

                    setCurrentCard(card);
                    winningPlayer(turn.getCurrentPlayer().getName(), playerButtons);
                    turn.shiftChange();
                    jLabelPlayer.setText("Va " + turn.getCurrentPlayer().getName());

                } else {
                    message("⚠️ Movimiento inválido.");
                }
            }
        }
    }

    public void setCurrentCard(Card card) {
        currentCard = card;
        jLabelCard.setName(card.getImagePath());
        jLabelCard.setIcon(
                card.getScaledIcon(
                        jLabelCard.getWidth(),
                        jLabelCard.getHeight()
                )
        );
    }

    public boolean verifyMatch(Card playedCard) {
        return playedCard.getColor().equals(currentCard.getColor()) ||
                playedCard.getNumber().equals(currentCard.getNumber());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    	JButton btn = (JButton) e.getSource();
        btn.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
    	 JButton btn = (JButton) e.getSource();
    	 btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3)); 
    	 btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    }

    @Override
    public void mouseExited(MouseEvent e) {
    	JButton btn = (JButton) e.getSource();
        btn.setBorder(null); 
        btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    public void message(String ms) {
    	JOptionPane.showMessageDialog(null, ms);
    }

    void saveScore() {
        try {

            Calendar date = Calendar.getInstance();
            String d = String.format("%02d/%02d/%04d",
                    date.get(Calendar.DAY_OF_MONTH),
                    date.get(Calendar.MONTH) + 1,
                    date.get(Calendar.YEAR));

            if (playerA.getScore() != playerB.getScore()) {
                Player win = (playerA.getScore() > playerB.getScore()) ? playerA : playerB;

                playerService.savePlayer(win.getName(), win.getScore(), d);
                message("🏆 " + win.getName() + " ha sido guardado con una puntuación de " + win.getScore());
            } else {
                message("⚠️ Empate, no se guardará ningún puntaje.");
            }

        } catch (Exception e) {
            message("❌ Error al guardar puntaje: " + e.getMessage());
        }
    }
    
    void viewList() {
        try {
            List<Player> topPlayers = playerService.getTopPlayers();

            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new String[] {"Id player", "Name", "Score", "Date"});
            
            if (topPlayers.isEmpty()) {
                model.addRow(new Object[] {"-", "No hay jugadas guardadas", "-", "-"});
            } else {
            	for (Player d : topPlayers) {
                    model.addRow(new Object[] {
                        d.getId(),
                        d.getName(),
                        d.getScore(),
                        d.getDate()
                    });
                }
            }

            jDataTable.setModel(model);

        } catch (Exception e) {
           message("❌ Error al mostrar datos: " + e.getMessage());
        }
    }

    
}
