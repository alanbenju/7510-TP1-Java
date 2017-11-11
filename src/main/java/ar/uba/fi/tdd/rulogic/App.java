package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.exceptions.InvalidDataException;
import ar.uba.fi.tdd.rulogic.exceptions.InvalidQueryException;
import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) {
        boolean exit = false;
        KnowledgeBase base;
        System.out.println("Enter name of database file: ");
        String fileName = "rules.db";
        String input = "";
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = buffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f = new File(input);
        if(!f.exists() || f.isDirectory()) {
            System.out.println("Couldnt find file, using default");
        }
        else{
            fileName=input;
        }

        try {
            base = new KnowledgeBase(fileName);
        } catch (InvalidDataException e) {
            e.printStackTrace();
            return;
        }
        while (!exit) {
            System.out.println("I shall answer all your questions! Enter 'e' or 'E' for exit ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                String s = br.readLine();
                if (s.equals("e") || s.equals("E")) exit = true;
                else{
                    System.out.println(base.answer(s));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidQueryException e) {
                e.printStackTrace();
            }
        }
    }
}
