package com.example.omar.androidcryptography;

//import android.app.Activity;
import android.content.DialogInterface;
//import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Character.toUpperCase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//public class MainActivity extends Activity implements View.OnClickListener {

    Button enrypt;
    Button decrypt;
    RadioGroup group;
    RadioButton scytale;
    RadioButton caesar;
    RadioButton vigenere;
    EditText input;
    TextView output;
    int columnHeight;
    int offset = 0;
    String keyword = "a";
    String temp;
    String Finalz = "";
    String phrase = "";
    String newPhrase = "";
    char[][] band;
    int column;
    int row;
    int keyword2[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enrypt = (Button)this.findViewById(R.id.button1);
        enrypt.setOnClickListener(this);
        decrypt = (Button)this.findViewById(R.id.button2);
        decrypt.setOnClickListener(this);
        group = (RadioGroup)this.findViewById(R.id.radioGroup1);
        scytale = (RadioButton)this.findViewById(R.id.radioButton1);
        caesar = (RadioButton)this.findViewById(R.id.radioButton2);
        vigenere = (RadioButton)this.findViewById(R.id.radioButton3);
        input = (EditText)this.findViewById(R.id.editText1);
        output = (TextView)this.findViewById(R.id.textView1);
    }

    public void onRadioClicked(View view) {
        if (view.equals(scytale)) {
            //choose columnHeight height
            setColumn();
        }
        if (view.equals(caesar)) {
            //choose offset
            setOffset();
        }
        if (view.equals(vigenere)) {
            //choose keyword
            setKeyword();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(enrypt)) {
            if (scytale.isChecked()) {
                //scytale
                phrase = String.valueOf(input.getText());
                for (int i = 0; i < phrase.length(); i++) {
                    char letter = phrase.charAt(i);
                    letter = toUpperCase(letter);
                    newPhrase += letter;
                }
                newPhrase = newPhrase.replaceAll("[^a-zA-Z]", "");
                column = columnHeight;
                row = newPhrase.length() / column + 1;
                band = new char[column][row];
                int keepGoing = column * row - newPhrase.length();
                for (int i = 0; i < keepGoing; i++) {
                    band[column - 1 - i][row - 1] = '@';
                }
                encryptScytale(band);
            }
            if (caesar.isChecked()) {
                //caesar
                phrase = String.valueOf(input.getText());
                encryptCaesar();
            }
            if (vigenere.isChecked()) {
                //vigenere
                phrase = String.valueOf(input.getText());
                initializeKeyword();
                encryptVigenere();
            }
            else if (!scytale.isChecked() && !caesar.isChecked() && !vigenere.isChecked()) {
                Toast.makeText(this,"Select a Cipher.", Toast.LENGTH_SHORT).show();
            }
        }
        if (v.equals(decrypt)) {
            if (scytale.isChecked()) {
                //scytale
                phrase = String.valueOf(input.getText());
                for (int i = 0; i < phrase.length(); i++) {
                    char letter = phrase.charAt(i);
                    letter = toUpperCase(letter);
                    newPhrase += letter;
                }
                newPhrase = newPhrase.replaceAll("[^A-Z]", "");
                column = columnHeight;
                row = newPhrase.length() / column + 1;
                band = new char[column][row];
                int keepGoing = column * row - newPhrase.length();
                for (int i = 0; i < keepGoing; i++) {
                    band[column - 1 - i][row - 1] = '@';
                }
                decryptScytale(band);
            }
            if (caesar.isChecked()) {
                //caesar
                phrase = String.valueOf(input.getText());
                decryptCaesar();
            }
            if (vigenere.isChecked()) {
                //vigenere
                phrase = String.valueOf(input.getText());

                initializeKeyword();
                decryptVigenere();
            }
            else if (!scytale.isChecked() && !caesar.isChecked() && !vigenere.isChecked()) {
                Toast.makeText(this,"Select a Cipher.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setColumn() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please enter your column height.");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                temp = input.getText().toString();
                if (temp.matches("[0-9]+") && temp.length() > 0) {
                    columnHeight = Integer.parseInt(temp);
                }
                else {
                    setColumn();
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void setOffset() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please enter your offset amount.");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                temp = input.getText().toString();
                if (temp.matches("[0-9]+") && temp.length() > 0) {
                    offset = Integer.parseInt(temp);
                }
                else {
                    setOffset();
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void setKeyword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please enter your keyword.");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                temp = input.getText().toString();
                if (temp.length() > 0) {
                    keyword = temp;
                }
                else {
                    setKeyword();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void encryptScytale(char[][] band) {
        int letter = 0;
        for (int x = 0; x < column; x++) {
            for (int i = 0; i < row; i++) {
                if (band[x][i] == '@') {
                    i++;
                }
                else if (letter < newPhrase.length()) {
                    band[x][i] = newPhrase.charAt(letter);
                    letter++;
                }
                else {
                    band[x][i] = '@';
                    letter++;
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int x = 0; x < column; x++) {
                Finalz += band[x][i];
            }
        }
        output.setText(Finalz);
        Finalz = "";
        newPhrase = "";
    }

    private void decryptScytale(char[][] band) {
        int letter = 0;
        for (int i = 0; i < row; i++) {
            for (int x = 0; x< column; x++) {
                if ( band[x][i] == '@') {
                    x++;
                }
                else if (letter < newPhrase.length()) {

                    band[x][i] = newPhrase.charAt(letter);
                    letter++;
                }
                else {
                    band[x][i] = '@';
                    letter++;
                }
            }
        }
        for ( int x = 0 ; x < column; x++) {
            for (int i = 0; i< row; i++) {
                if (band[x][i] == '@') {
                }
                else {
                    Finalz += band[x][i];
                }
            }
        }
        output.setText(Finalz);
        Finalz = "";
        newPhrase = "";
    }

    private void encryptCaesar() {
        for (int i = 0; i < phrase.length(); i++) {
            char letter = phrase.charAt(i);
            letter = toUpperCase(letter);
            newPhrase += letter;
        }
        newPhrase = newPhrase.replaceAll("[^A-Z]","");
        int keepGoing = newPhrase.length();
        int yeet = 0;
        while (yeet != keepGoing) {
            char temp = newPhrase.charAt(yeet);
            /*if ((int) temp < 97) {
                temp = toLowerCase(temp);
            }*/
            if (temp == ' ') {
                temp = ' ';
            }
            else {
                temp = (char) (temp + offset % 26);
                if ((int)temp > 90) {
                    temp = (char) (temp - 26);
                }
            }
            Finalz = Finalz + temp;
            yeet++;
        }
        output.setText(Finalz);
        Finalz = "";
        newPhrase = "";
    }

    private void decryptCaesar() {
        for (int i = 0; i < phrase.length(); i++) {
            char letter = phrase.charAt(i);
            letter = toUpperCase(letter);
            newPhrase += letter;
        }
        newPhrase = newPhrase.replaceAll("[^A-Z]","");
        int keepGoing = newPhrase.length();
        int yeet = 0;
        while (yeet != keepGoing) {
            char temp = newPhrase.charAt(yeet);
            /*if ((int) temp < 97) {
                temp = toLowerCase(temp);
            }*/
            if (temp == ' ') {
                temp = ' ';
            }
            else {
                temp = (char) (temp - offset % 26);
                if ((int)temp < 65) {
                    temp = (char) (temp + 26);
                }
            }
            Finalz = Finalz + temp;
            yeet++;
        }
        output.setText(Finalz);
        Finalz = "";
        newPhrase = "";
    }

    private void encryptVigenere() {
        for (int i = 0; i < phrase.length(); i++) {
            char letter = phrase.charAt(i);
            letter = toUpperCase(letter);
            newPhrase += letter;
        }
        newPhrase = newPhrase.replaceAll("[^A-Z]","");
        int keepGoing = newPhrase.length();
        //System.out.println(keepGoing);
        int yeet = 0;
        int keywordCounter = 0;
        while (yeet != keepGoing) {
            int shift = keyword2[keywordCounter % keyword2.length] - 65;
            //System.out.println(phrase.charAt(yeet));
            char temp = newPhrase.charAt(yeet);
            if (temp == ' ') {
                temp = ' ';
            }
            else {
                keywordCounter++;
                temp = (char) (temp + shift % 26);
                if ((int)temp > 90) {
                    temp = (char) (temp - 26);
                }
            }
            Finalz = Finalz + temp;
            //System.out.println(temp);
            yeet++;
        }
        output.setText(Finalz);
        Finalz = "";
        newPhrase = "";
        //System.out.println(Finalz);
    }

    private void decryptVigenere() {
        for (int i = 0; i < phrase.length(); i++) {
            char letter = phrase.charAt(i);
            letter = toUpperCase(letter);
            newPhrase += letter;
        }
        newPhrase = newPhrase.replaceAll("[^A-Z]","");
        int keepGoing = newPhrase.length();
        //System.out.println(keepGoing);
        int yeet = 0;
        int keywordCounter = 0;
        while (yeet != keepGoing) {
            int shift = keyword2[keywordCounter % keyword2.length] - 65;
            //System.out.println(shift);
            //System.out.println(phrase.charAt(yeet));
            char temp = newPhrase.charAt(yeet);
            if (temp == ' ') {
                temp = ' ';
            }
            else {
                //System.out.println(temp + "," + (int)temp);
                keywordCounter++;
                //System.out.println(shift % 26);
                temp = (char) (temp - shift % 26);
                //System.out.println(temp + "," + (int)temp);
                if ((int)temp < 65) {
                    temp = (char) (temp + 26);
                }
                //System.out.println(temp + "," +(int)temp);
            }
            Finalz = Finalz + temp;
            yeet++;
        }
        output.setText(Finalz);
        Finalz = "";
        newPhrase = "";
        //System.out.println(Finalz);
    }

    private void initializeKeyword() {
        String newKeyword = "";
        for (int i = 0; i < keyword.length(); i++) {
            char letter = keyword.charAt(i);
            letter = toUpperCase(letter);
            newKeyword += letter;
        }
        int temp [] = new int[newKeyword.length()];
        for (int i = 0; i < newKeyword.length(); i++) {
            temp[i] = newKeyword.charAt(i);
            //System.out.println(temp[i]);
        }
        keyword2 = temp;
        for (int i = 0; i < keyword2.length; i++) {
            //System.out.println(keyword2[i]);
        }
    }
}

