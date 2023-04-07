import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class Controller implements ActionListener {
    private Random rnd;
    private SettingsImpl settings;
    private ButtonImpl buttonImpl;
    private Player player1;
    private Player player2;
    private boolean player1Turn;
    private boolean madeAMove;
    private int idLastButton = -1;
    private int idLastButtonN = -1;
    private int lastSide = -1;
    private int lastSideN = -1;


    public Controller(SettingsImpl settings, ButtonImpl buttonImpl){
        this.rnd = new Random();
        this.madeAMove = false;
        this.settings = settings;
        this.buttonImpl = buttonImpl;

        this.player1 = new Player(Color.BLUE);
        this.player2 = new Player(Color.RED);

        this.buttonImpl.getScoreButton().addActionListener(this);
        this.settings.getTitlePanel().add(this.buttonImpl.getScoreButton());

        this.initializeButtons();
        this.firstTurn();
    }

    private void initializeButtons(){
        for (int i = 0; i < this.buttonImpl.getButtons().length; i++) {
            this.buttonImpl.getButtons()[i] = new JButton();
            this.buttonImpl.getButtons()[i].setBorder(BorderFactory.createDashedBorder(null, 4, 4));

            this.buttonImpl.getButtons()[i].setFocusable(false);
            this.buttonImpl.getButtons()[i].setFont(new Font("Calibre", Font.BOLD, 120));
            this.buttonImpl.getButtons()[i].addActionListener(this);
            this.settings.getButtonPanel().add(this.buttonImpl.getButtons()[i]);
        }
    }

    private void firstTurn() {
        if (this.rnd.nextInt(2) == 0) {
            this.player1Turn = true;
            this.buttonImpl.getScoreButton().setBackground(Color.BLUE);
        } else {
            this.player1Turn = false;
            this.buttonImpl.getScoreButton().setBackground(Color.RED);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buttonImpl.getScoreButton()) {
            NextTurn();
        } else {
            for (int i = 0; i < this.buttonImpl.getButtons().length; i++) {
                if (this.buttonImpl.getButtons()[i] == e.getSource()) {
                    long count = Arrays.stream(this.buttonImpl.getButtonSides()[i]).filter(x -> x).count();

                    if (count == 3) {
                        WonPoint(i);

                    } else if (count < 3) {
                        ModifyButton(i);
                    }
                    break;
                }
            }
        }
    }

    private void WonPoint(int i) {
        //set background
        this.buttonImpl.getButtons()[i].setBackground(this.player1Turn ? Color.BLUE : Color.RED);

        //update player score
        if (this.player1Turn) {
            this.player1.setScore(this.player1.getScore() + 1);
            this.settings.getTextFields()[0].setText(String.format("Score BLUE: %d", this.player1.getScore()));
        } else {
            this.player2.setScore(this.player2.getScore() + 1);

            this.settings.getTextFields()[1].setText(String.format("Score RED: %d", this.player2.getScore()));
        }

        //setBorder
        int j;
        for (j = 0; j < this.buttonImpl.getButtonSides()[i].length; j++) {
            if (!this.buttonImpl.getButtonSides()[i][j]) {
                break;
            }
        }
        ClearLastClickedButton();

        UpdateButtonSide(j, i);
        ModifyNeighbourButton(i, j);
        if (this.idLastButtonN != -1) {
            this.buttonImpl.getButtonSides()[this.idLastButtonN][this.lastSideN] = true;
        }

        //disable button
        this.buttonImpl.getButtons()[i].setEnabled(false);

        if (this.idLastButtonN != -1) {
            if (Arrays.stream(this.buttonImpl.getButtonSides()[this.idLastButtonN]).filter(x -> x).count() >= 3) {
                WonPoint(this.idLastButtonN);
            }
        }
    }

    private void ModifyButton(int i) {
        int sideToModify = -1;

        if (i == this.idLastButton) {
            for (int j = 1; j < this.buttonImpl.getButtonSides()[i].length; j++) {
                sideToModify = (j + this.lastSide) % 4;
                if (!this.buttonImpl.getButtonSides()[i][sideToModify]) {
                    break;
                }
            }
        } else if (i != this.idLastButton) {
            for (int j = 0; j < 4; j++) {
                if (!this.buttonImpl.getButtonSides()[i][j]) {
                    sideToModify = j;
                    break;
                }
            }
        }

        // clear lastClicked button
        ClearLastClickedButton();

        //set the border of the clicked button
        var arr = this.buttonImpl.getButtonSides()[i].clone();
        arr[sideToModify] = true;
        this.SetBorders(i, arr);

        //set the border of the neighbour button
        ModifyNeighbourButton(i, sideToModify);

        this.idLastButton = i;
        this.lastSide = sideToModify;
        this.madeAMove = true;
    }

    private void ClearLastClickedButton() {
        if (this.lastSide != -1) {
            var arr = this.buttonImpl.getButtonSides()[this.idLastButton].clone();
            this.SetBorders(this.idLastButton, arr);

            // clear neighbour of lastClicked button
            if (this.lastSideN != -1) {
                arr = this.buttonImpl.getButtonSides()[this.idLastButtonN].clone();
                this.SetBorders(this.idLastButtonN, arr);
            }
        }
    }

    private void ModifyNeighbourButton(int i, int sideToModify) {

        switch (sideToModify) {
            case 0:
                this.idLastButtonN = i - this.settings.getCols();
                if (this.idLastButtonN >= 0) {
                    UpdateButtonSide(2, this.idLastButtonN);
                    this.lastSideN = 2;
                } else {
                    this.idLastButtonN = -1;
                    this.lastSideN = -1;
                }
                break;
            case 1:
                this.idLastButtonN = i - 1;
                if (this.idLastButtonN >= 0 && this.idLastButtonN > (this.settings.getCols() * (i / this.settings.getCols())) - 1) {
                    UpdateButtonSide(3, this.idLastButtonN);
                    this.lastSideN = 3;
                } else {
                    this.idLastButtonN = -1;
                    this.lastSideN = -1;
                }
                break;
            case 2:
                this.idLastButtonN = i + this.settings.getCols();
                if (this.idLastButtonN < this.settings.getCols() * this.settings.getRows()) {
                    UpdateButtonSide(0, this.idLastButtonN);
                    this.lastSideN = 0;
                } else {
                    this.idLastButtonN = -1;
                    this.lastSideN = -1;
                }
                break;
            case 3:
                this.idLastButtonN = i + 1;
                if (this.idLastButtonN % this.settings.getCols() != 0) {
                    UpdateButtonSide(1, this.idLastButtonN);
                    this.lastSideN = 1;
                } else {
                    this.idLastButtonN = -1;
                    this.lastSideN = -1;
                }
                break;
        }

        if (this.idLastButtonN != -1) {
            if (Arrays.stream(this.buttonImpl.getButtonSides()[this.idLastButtonN]).filter(x -> x).count() >= 3) {
                WonPoint(this.idLastButtonN);
            }
        }
    }

    private void UpdateButtonSide(int side, int idButton) {
        Boolean[] arr = this.buttonImpl.getButtonSides()[idButton].clone();
        arr[side] = true;
        this.SetBorders(idButton, arr);
    }

    private void SetBorders(int i, Boolean[] values) {
        this.buttonImpl.getButtons()[i].setBorder(BorderFactory.createMatteBorder(
                values[0] == true ? 4 : 0,
                values[1] == true ? 4 : 0,
                values[2] == true ? 4 : 0,
                values[3] == true ? 4 : 0, Color.BLACK));
    }

    private void NextTurn() {
        if (this.madeAMove) {
            if (this.player1Turn) {
                this.buttonImpl.getScoreButton().setBackground(Color.RED);
                this.player1Turn = false;
            } else {
                this.buttonImpl.getScoreButton().setBackground(Color.BLUE);
                this.player1Turn = true;
            }

            this.buttonImpl.getButtonSides()[this.idLastButton][this.lastSide] = true;
            if (this.idLastButtonN != -1) {
                this.buttonImpl.getButtonSides()[this.idLastButtonN][this.lastSideN] = true;
            }
            this.madeAMove = false;
        }
    }
}
