package chirica;

import static chirica.GroupGenerator.ITEMS;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/* *
 * This project was created for educational purposes.
 * Copyright (c) 2022. All rights reserved. 
 * @author Stefan Chirica
 */
public class Groups extends javax.swing.JDialog {

    Random rNumber = new Random();
    ArrayList<Student> permanentList = GroupGenerator.getItems();
    ArrayList<Student> starsArrayList = new ArrayList<>();
    ArrayList<Student> finalList = new ArrayList<>();

    int groupSize;
    int groupAmount;
    int remainder;
    int currentItem;

    /**
     * Creates new form Groups
     *
     * @param parent
     * @param modal
     * @param items
     * @param groupAmount
     */
    /*
    Shuffle groups
    
    Go 1 2 3 4 1 2 3 4 until all the groups are done
     */
    public Groups(java.awt.Frame parent, boolean modal, ArrayList<Student> items, int groupAmount) {
        super(parent, modal);
        this.groupAmount = groupAmount;
        initComponents();
        createGroups(items, groupAmount);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        groupsScrollPane = new javax.swing.JScrollPane();
        groupsTextArea = new javax.swing.JTextArea();
        redoButton = new javax.swing.JButton();
        doneButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Final Groups");

        groupsTextArea.setEditable(false);
        groupsTextArea.setColumns(20);
        groupsTextArea.setRows(5);
        groupsTextArea.setAutoscrolls(false);
        groupsScrollPane.setViewportView(groupsTextArea);

        redoButton.setText("Make New Groups");
        redoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoButtonActionPerformed(evt);
            }
        });

        doneButton.setText("OK");
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save Groups");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(groupsScrollPane)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(redoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(doneButton))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {doneButton, redoButton, saveButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(groupsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(redoButton)
                    .addComponent(doneButton)
                    .addComponent(saveButton)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        for (int i = 0; i < permanentList.size(); i++) {
            if (permanentList.get(i).getIsStarred() && permanentList.get(i).getName().charAt(0) != '*') {
                permanentList.get(i).setName("*" + permanentList.get(i).getName());
            }
        }
        this.dispose();
    }//GEN-LAST:event_doneButtonActionPerformed

    /**
     * Events on
     *
     * @param evt
     */
    private void redoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoButtonActionPerformed
        System.out.println("\n");
        permanentList = GroupGenerator.getItems();
        createGroups(permanentList, groupAmount);

        finalList = new ArrayList<>();
    }//GEN-LAST:event_redoButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter groupFilter = new FileNameExtensionFilter("text files (*.txt)", "txt");
        fileChooser.addChoosableFileFilter(groupFilter);
        fileChooser.setFileFilter(groupFilter);

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File fileName = fileChooser.getSelectedFile();
            String filePath = fileName.getAbsolutePath(); //maybe change to dialog

            if (!filePath.endsWith(".txt")) {
                fileName = new File(filePath + ".txt");
            }

            String[] group = groupsTextArea.getText().split("\\n");

            try {
                FileWriter fileWriter = new FileWriter(filePath);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                for (int i = 0; i < group.length; i++) {
                    bufferedWriter.write(group[i]);
                    bufferedWriter.newLine();
                }

                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            for (int i = 0; i < group.length; i++) {
                System.out.println(group[i]);
            }
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void createGroups(ArrayList<Student> items, int groupAmount) {
        ArrayList<ArrayList<Student>> finalGroups = new ArrayList<>();

        groupSize = items.size() / groupAmount;
        remainder = items.size() % groupAmount;
        groupsTextArea.setText("");

//        System.out.println("Amount of items to sort: " + items.size());
//        System.out.println("Amount of groups: " + groupAmount);
//        System.out.println("Items per group: " + groupSize);
//        System.out.println("Extras: " + remainder + "\n\n");

        //Initializing the 2D arrayList
        for (int i = 0; i < groupAmount; i++) {
            finalGroups.add(new ArrayList<>());
        }

        //This is the "random" list of students
        finalList = shuffleList();
        int group = 0;

        //Groups are given as 0, 1, 2, 3, 4, ..., (groupAmount - 1), 0, 1, 2, ...
        for (int i = 0; i < finalList.size(); i++) {
            finalList.get(i).setGroup(group % groupAmount);

            for (int j = 0; j < finalGroups.size(); j++) {
                if (j == group % groupAmount) {
                    finalGroups.get(j).add(finalList.get(i));

                    if (!finalList.get(i).getName().equals("")) {
                        if (finalList.get(i).getName().charAt(0) == '*') {
                            finalList.get(i).setName(finalList.get(i).getName().substring(1));
                        }
                    }
                }
            }
            group++;
        }

        System.out.println("\n");

        for (int i = 0; i < finalGroups.size(); i++) {
            groupsTextArea.setText(groupsTextArea.getText() + "Group " + (i + 1) + ":\n");

            for (int j = 0; j < finalGroups.get(i).size(); j++) {

                if (j == finalGroups.get(i).size() - 1) {
                    groupsTextArea.setText(groupsTextArea.getText() + finalGroups.get(i).get(j).getName() + "\n\n");
                } else {
                    groupsTextArea.setText(groupsTextArea.getText() + finalGroups.get(i).get(j).getName() + "\n");
                }

            }
        }
    }

    private ArrayList<Student> shuffleList() {
        ArrayList<Student> newList = new ArrayList<>();

        for (int i = 0; i < permanentList.size(); i++) {
            newList.add(permanentList.get(i));
        }

        Collections.shuffle(newList);

        for (int i = 0; i < newList.size(); i++) {
            if (!newList.get(i).getName().equals("")) {
                if (newList.get(i).getIsStarred()) {
                    newList.add(0, newList.get(i));
                    newList.remove(i + 1);

                }
            } else {
                newList.remove(i);
                permanentList.remove(i);
            }

        }

        for (int i = 0; i < newList.size(); i++) {
            System.out.println(permanentList.get(i).getName());
        }

        return newList;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton doneButton;
    private javax.swing.JScrollPane groupsScrollPane;
    private javax.swing.JTextArea groupsTextArea;
    private javax.swing.JButton redoButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

}
