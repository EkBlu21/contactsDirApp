package com.idexx.isd.interview;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class ContactsApp{

    public static void main(String[] args){
        
        /*Create JFrame and JTable */
        JFrame frame = new JFrame();
        JTable table = new JTable(); 
        
        /*Create a table model and set Column Identifiers*/
        Object[] columns = {"Name","Phone Number"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        /*Set the model to the table */
        table.setModel(model);
        
        /*Set JTable Bkgrd Color, Font Size & Color, Row Height */
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,16);
        table.setFont(font);
        table.setRowHeight(30);
        
        /*Create JTextFields for Name, Number & Search: */
        JTextField textName = new JTextField();
        JTextField textNum = new JTextField();
        JTextField textSrch = new JTextField();
        
        /*Create JLabels for Name, Number & Search: */
        JLabel nameLabel = new JLabel("Contact Name");
        JLabel numberLabel = new JLabel("Phone Number");
        JLabel searchLabel = new JLabel("Search Contacts:");
        
        /*Create JButtons */
        JButton btnAdd = new JButton("Add Contact");
        JButton btnDelete = new JButton("Delete Contact");
        JButton btnSearch = new JButton("Search Contacts");
        JButton btnSave = new JButton("Save & Exit");
        
        /*JtextFields */
        textName.setBounds(120, 250, 250, 25);
        textNum.setBounds(120, 300, 250, 25);
        textSrch.setBounds(120,360,250,25);
        
        /* Labels */
        nameLabel.setBounds(120,225, 250, 25);
        numberLabel.setBounds(120, 275, 250, 25);
        searchLabel.setBounds(120, 340, 250,25);
        
        /*Buttons*/
        btnAdd.setBounds(440, 250, 170, 25);
        btnDelete.setBounds(440, 290, 170, 25);
        btnSearch.setBounds(440, 360, 170, 25);
        btnSave.setBounds(675, 360, 170,25);
        
        
        /*Create JScrollPane */
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        
        frame.setLayout(null);
        
        frame.add(pane);
        
        /*Add JTextFields, JLabels, JButtons to the jframe */
        frame.add(textName);
        frame.add(textNum);
        frame.add(textSrch);

        frame.add(nameLabel);
        frame.add(numberLabel);
        frame.add(searchLabel);

        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnSearch);
        frame.add(btnSave);
        
        /*Create objects to set the row data*/
        Object[] row = new Object[2]; 
        
        /*Load contacts from contacts.dat file */
        try {
            BufferedReader br = new BufferedReader(new FileReader("contacts.dat"));

            String line = br.readLine();
            String[] colHeaders = line.split(",");

            model.setColumnIdentifiers(colHeaders);

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
        
        /* Listener for 'Add Contact' button. */
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
                row[0] = textName.getText();
                row[1] = textNum.getText();
                
                
                try {
                        long i = Long.parseLong(textNum.getText()); 
                }catch(NumberFormatException ev) 
                        { JOptionPane.showMessageDialog(null,"Phone Number Must Be Numeric. \n Please Try Again");
                    }
                
                
                // Add row to the model
                model.addRow(row);
            }
        });
        
        /* Listener for 'Delete Contact' button. */
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            
                // i = the index of the selected row.
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from JTable.
                    model.removeRow(i);
                }
                else{
                    System.out.println("Error: Could Not Delete Contact");
                }
            }
        });
        
        
        /*
             
        Listener for "Save & Exit" button.
        btnSave.addActionListener(new ActionListener(){ 

        /* 
        
        Listener for 'Search Contacts' button.
        btnSearch.addActionListener(new ActionListener(){        
        
        */
        
        frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
        
        
    }
}