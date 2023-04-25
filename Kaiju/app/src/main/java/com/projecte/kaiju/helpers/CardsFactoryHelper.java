package com.projecte.kaiju.helpers;

import android.content.Context;
import android.content.res.AssetManager;

import com.opencsv.CSVReader;
import com.projecte.kaiju.models.Card;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CardsFactoryHelper {

    /**
     * Utilitza el context del singleton per llegir el fitxer de cartes i retornar-ne una llista
     * @return Llista de cartes llegides dels del fitxer csv
     */
    public static ArrayList<Card> readData(){
        ArrayList<Card> output = new ArrayList<>();
        try {
            Context c = GlobalInfo.getInstance().getContext();
            String csvFile = "kaiju.csv";
            AssetManager manager = c.getAssets();
            InputStream in = manager.open(csvFile);
            InputStreamReader isr = new InputStreamReader(in);
            CSVReader csvReader = new CSVReader(isr);
            String[] line = null;
            while ((line = csvReader.readNext()) != null) {
                //String[] values = line.split(",");
                int id = Integer.parseInt(line[0]);
                String name = line[1];
                int cost = Integer.parseInt(line[2]);
                int damage = Integer.parseInt(line[3]);
                int life = Integer.parseInt(line[4]);
                String type = line[5];
                output.add(new Card(id, name, cost, damage, life, type));
            }
            csvReader.close();
            //fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return output;
    }
}