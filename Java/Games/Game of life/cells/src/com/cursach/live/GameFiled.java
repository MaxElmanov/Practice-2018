package com.cursach.live;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFiled extends JPanel implements ActionListener{
    private Timer timer;
    private Cell[][] cells;
    private Cell[][] tempCells;
    private final int DOT_SIZE = 15;
    private int countGeneration = 0;

    public GameFiled() {
        setBackground(Color.DARK_GRAY);
        initGame();
    }
    
    public void initGame(){
        cells = new Cell[30][30];
        tempCells = new Cell[30][30];
        initCells();
        timer = new Timer(250, this);
        timer.start();
    }

    private void initCells() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
                tempCells[i][j] = new Cell();
            }
        }
        cells[1][2].setAlive(true);
        cells[2][3].setAlive(true);
        cells[3][1].setAlive(true);
        cells[3][2].setAlive(true);
        cells[3][3].setAlive(true);

        tempCells[1][2].setAlive(true);
        tempCells[2][3].setAlive(true);
        tempCells[3][1].setAlive(true);
        tempCells[3][2].setAlive(true);
        tempCells[3][3].setAlive(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].isAlive()) {
                    g.setColor(Color.green);
                    g.fillRect(j * DOT_SIZE, i * DOT_SIZE, DOT_SIZE, DOT_SIZE);
                }
                else{
                    g.setColor(Color.darkGray);
                    g.fillRect(j * DOT_SIZE, i * DOT_SIZE, DOT_SIZE, DOT_SIZE);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("generation = "+ countGeneration);

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (i < 1 || j < 1 || i > 28 || j > 28){
                    continue;
                }
                else {
                    if (cells[i][j].isAlive()) {
                        aliveMethod(i, j);
                    } else {
                        deadMethod(i, j);
                    }
                }
            }
        }

        countGeneration++;

        resetCells();

        repaint();
    }

    private void resetCells() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].setAlive(tempCells[i][j].isAlive());
            }
        }
    }

    private void aliveMethod(int row, int col) {
        int counterAlive = 0;

        for (int i = row-1; i < ((row-1)+3); i++) {
            for (int j = col-1; j < ((col-1)+3); j++) {
                if (i != row || j != col) {
                    if (cells[i][j].isAlive()) {
                        counterAlive++;
                    }
                }
            }
        }

        if(counterAlive == 2 || counterAlive == 3){
            tempCells[row][col].setAlive(true);
        }
        else if(counterAlive > 3 || counterAlive < 2){
            tempCells[row][col].setAlive(false);
        }
    }

    private void deadMethod(int row, int col) {
        int counterAlive = 0;

        for (int i = row-1; i < ((row-1)+3); i++) {
            for (int j = col-1; j < ((col-1)+3); j++) {
                if (i != row || j != col) {
                    if (cells[i][j].isAlive()) {
                        counterAlive++;
                    }
                }
            }
        }

        if(counterAlive == 3){
            tempCells[row][col].setAlive(true);
        }
    }
}

