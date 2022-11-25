package org.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

            try {
                startGame();

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            } finally {
                System.out.println("Game over!");
            }



        }

        private static void startGame() throws IOException, InterruptedException {

            Terminal terminal = createTerminal();
            Player player = createPlayer();
            final char block = '\u2588';
            // creating obstacles
            // outer boundary of the game with obstacles
            Position[] obsticles1 = new Position[80];
            for(int i = 0;i<80;i++) {
                obsticles1[i] = new Position(i, 0);
            }
            Position[] obsticles2 = new Position[80];
            for(int i = 0;i<80;i++) {
                obsticles2[i] = new Position(0, i);
            }
            Position[] obsticles3 = new Position[80];
            for(int i = 0;i<80;i++) {
                obsticles3[i] = new Position(80, i);
            }
            Position[] obsticles4 = new Position[80];
            for(int i = 0;i<80;i++) {
                obsticles4[i] = new Position(i, 80);
            }

            // inner obstacles designing

            Position[] obsticles5 = new Position[10];
            for(int i = 0;i<10;i++){
                obsticles5[i] = new Position(28,10+i);
            }
            Position[] obsticles6 = new Position[10];
            for(int i = 0;i<10;i++){
                obsticles6[i] = new Position(19+i,5);
            }
            Position[] obsticles7 = new Position[10];
            for(int i = 0;i<10;i++){
                obsticles7[i] = new Position(60,4+i);
            }
            Position[] obsticles8 = new Position[13];
            for(int i = 0;i<13;i++){
                obsticles8[i] = new Position(45,4+i);
            }

// Use obsticles array to print to lanterna
            for (Position p : obsticles1) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter(block);
            }

            for (Position p : obsticles2) {
                terminal.setCursorPosition(p.x,p.y);
                terminal.putCharacter(block);
            }

            for (Position p : obsticles3) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter(block);
            }

            for (Position p : obsticles4) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter(block);
            }
            for (Position p : obsticles5) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter(block);
            }
            for (Position p : obsticles6) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter(block);
            }

            for (Position p : obsticles7) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter(block);
            }

            for (Position p : obsticles8) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter(block);
            }


            List<Monster> monsters = createMonsters();

            drawCharacters(terminal, player, monsters);

            do {
                KeyStroke keyStroke = getUserKeyStroke(terminal);

                movePlayer(player, keyStroke);

                moveMonsters(player, monsters);

                drawCharacters(terminal, player, monsters);

            } while (isPlayerAlive(player, monsters));

            terminal.setForegroundColor(TextColor.ANSI.RED);
            terminal.setCursorPosition(player.getX(), player.getY());
            terminal.putCharacter(player.getSymbol());
            terminal.bell();
            terminal.flush();
        }

        private static void moveMonsters(Player player, List<Monster> monsters) {
            for (Monster monster : monsters) {
                monster.moveTowards(player);
            }
        }

        private static void movePlayer(Player player, KeyStroke keyStroke) {
            switch (keyStroke.getKeyType()) {
                case ArrowUp:
                    player.moveUp();
                    break;
                case ArrowDown:
                    player.moveDown();
                    break;
                case ArrowLeft:
                    player.moveLeft();
                    break;
                case ArrowRight:
                    player.moveRight();
                    break;
            }
        }

        private static KeyStroke getUserKeyStroke(Terminal terminal) throws InterruptedException, IOException {
            KeyStroke keyStroke;
            do {
                Thread.sleep(5);
                keyStroke = terminal.pollInput();
            } while (keyStroke == null);
            return keyStroke;
        }

        public static Player createPlayer() {
            return new Player(10,10, 'P');
        }
/*
        public static void creatingObstacle()throws IOException{
            // DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            //Terminal terminal = terminalFactory.createTerminal();
            //terminal.setCursorVisible(false);
            Terminal terminal = createTerminal();
            final char block = '\u2588';
            // creating obstacles
            // outer boundary of the game with obstacles
            Position[] obsticles1 = new Position[80];
            for(int i = 0;i<80;i++) {
                obsticles1[i] = new Position(i, 0);
            }
            Position[] obsticles2 = new Position[80];
            for(int i = 0;i<80;i++) {
                obsticles2[i] = new Position(0, i);
            }
            Position[] obsticles3 = new Position[80];
            for(int i = 0;i<80;i++) {
                obsticles3[i] = new Position(80, i);
            }
            Position[] obsticles4 = new Position[80];
            for(int i = 0;i<80;i++) {
                obsticles4[i] = new Position(i, 80);
            }

            // inner obstacles designing

            Position[] obsticles5 = new Position[10];
            for(int i = 0;i<10;i++){
                obsticles5[i] = new Position(28,10+i);
            }
            Position[] obsticles6 = new Position[10];
            for(int i = 0;i<10;i++){
                obsticles6[i] = new Position(19+i,5);
            }
            Position[] obsticles7 = new Position[10];
            for(int i = 0;i<10;i++){
                obsticles7[i] = new Position(60,4+i);
            }
            Position[] obsticles8 = new Position[13];
            for(int i = 0;i<13;i++){
                obsticles8[i] = new Position(45,4+i);
            }

// Use obsticles array to print to lanterna
            for (Position p : obsticles1) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter(block);
            }

            for (Position p : obsticles2) {
                terminal.setCursorPosition(p.x,p.y);
                terminal.putCharacter(block);
            }

            for (Position p : obsticles3) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter(block);
            }

            for (Position p : obsticles4) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter(block);
            }
            for (Position p : obsticles5) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter(block);
            }
            for (Position p : obsticles6) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter(block);
            }

            for (Position p : obsticles7) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter(block);
            }

            for (Position p : obsticles8) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter(block);
            }

            terminal.flush();


        }

 */

        private static List<Monster> createMonsters() {
            List<Monster> monsters = new ArrayList<>();
            monsters.add(new Monster(10,10,'O'));
            monsters.add(new Monster(6,6,'O'));
            monsters.add(new Monster(3, 3, 'O'));
            monsters.add(new Monster(23, 23, 'O'));
            monsters.add(new Monster(23, 3, 'O'));
            monsters.add(new Monster(3, 23, 'O'));
            monsters.add(new Monster(6,6,'O'));
            monsters.add(new Monster(10,10,'O'));
            return monsters;
        }

        private static Terminal createTerminal() throws IOException {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            Terminal terminal = terminalFactory.createTerminal();
            terminal.setCursorVisible(false);
            return terminal;
        }

        private static void drawCharacters(Terminal terminal, Player player, List<Monster> monsters) throws IOException {
            for (Monster monster : monsters) {
                terminal.setCursorPosition(monster.getPreviousX(), monster.getPreviousY());
                terminal.putCharacter(' ');

                terminal.setCursorPosition(monster.getX(), monster.getY());
                terminal.putCharacter(monster.getSymbol());
            }

            terminal.setCursorPosition(player.getPreviousX(), player.getPreviousY());
            terminal.putCharacter(' ');

            terminal.setCursorPosition(player.getX(), player.getY());
            terminal.putCharacter(player.getSymbol());

            terminal.flush();

        }

        private static boolean isPlayerAlive(Player player, List<Monster> monsters) {
            for (Monster monster : monsters) {
                if (monster.getX() == player.getX() && monster.getY() == player.getY()) {
                    return false;
                }
            }
            return true;
        }


    }
