package guis;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vitek000 on 23.08.2016.
 */
public class TabShower {

    public static void main(String[] args) {
        List<Integer> tab = new ArrayList<>(Arrays.asList(-4,4,4,5,-5,6,-6,-7,7));


     //   showOnTable(tab);
        generateHtmlFromTabList(tab);
    }

    private static void showOnTable(List<Integer> tab) {
        JFrame frame = new JFrame("Tab shower!");
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // on center!

        int ROWS = 5;
        int COLUMNS = 3;
        Object[] colNames = new Object[COLUMNS];
        Object[][] objects = new Object[ROWS][COLUMNS];

        for (int i = 0; i < COLUMNS; i++) {
            colNames[i] = "c" + i;
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                objects[i][j] = i + " " + j;
            }
        }

        JTable jTable = new JTable(objects, colNames);

        JScrollPane scrollPane = new JScrollPane(jTable);
        jTable.setFillsViewportHeight(true);

        frame.getContentPane().add(scrollPane);
        frame.setVisible(true);




    }



    public static void generateHtmlFromTabList(List<Integer> tab) {
        StringBuilder stringBuilder = new StringBuilder();

        int MAX_COLS = 3;
        stringBuilder.append("<table border=\"1\">");

        int ind = 0;

        boolean newRow = true;
        boolean endRow = false;
        boolean firstRow = true;
        for (Integer integer : tab) {

//            if(ind++ > MAX_COLS) {
//
//            }
            if(newRow) {
                stringBuilder.append("\n");
                if(!firstRow) {
                    stringBuilder.append("</tr>");
                }
                firstRow = false;
                stringBuilder.append("<tr>");
            }

            stringBuilder.append("<td>");

            stringBuilder.append(integer);

            stringBuilder.append("</td>");

//            if(endRow) {
//                stringBuilder.append("</tr>");
//            }

            ind++;

            newRow = ind % MAX_COLS == 0;
            endRow = ind % (MAX_COLS) == 0;


        }
        stringBuilder.append("</table>");
        System.out.println(stringBuilder);
    }
}
