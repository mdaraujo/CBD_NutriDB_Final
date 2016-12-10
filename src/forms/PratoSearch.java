/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import databases.DocDB;
import databases.GraphDB;
import databases.KeyValueDB;
import databases.RelationalDB;
import entities.Prato;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel
 */
public class PratoSearch extends javax.swing.JFrame {

    private int selectedId = -1;

    /**
     * Creates new form PratoSearch
     */
    public PratoSearch() {
        initComponents();

        list.setFont(new Font("monospaced", Font.PLAIN, 12));
        setTitle("Listagem dos Pratos");
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchBtn.doClick();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchBtn = new javax.swing.JButton();
        listPanel = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        nomeInput = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        dosesInput = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        difInput = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cozinhaInput = new javax.swing.JComboBox<>();
        tempoInput = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        eliminarPrato = new javax.swing.JButton();
        Create = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        searchBtn.setText("Search");
        searchBtn.setToolTipText("");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMouseClicked(evt);
            }
        });
        listPanel.setViewportView(list);

        jLabel1.setText("Nome:");

        dosesInput.setModel(new javax.swing.SpinnerNumberModel(0, 0, 12, 2));

        jLabel2.setText("Doses");

        difInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "*", "Fácil", "Médio", "Difícil" }));

        jLabel3.setText("Dificuldade");

        jLabel4.setText("Tipo de Cozinha");

        cozinhaInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "*", "Contemporânea", "Internacional", "Contemporânea Fusão", "Vegetariana", "Internacional Contemporânea", "Portuguesa Tradicional", "Brasileira Vegetariana", "Japonesa", "Suíça", "Contemporânea Saudável", "Italiana", "Mediterrânea", "Britânica", "Francesa Contemporânea" }));

        tempoInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "*", "Lento", "Médio", "Rápido" }));

        jLabel5.setText("Tempo");

        eliminarPrato.setText("Delete");
        eliminarPrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPratoActionPerformed(evt);
            }
        });

        Create.setText("Create");
        Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateActionPerformed(evt);
            }
        });

        resetBtn.setText("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(listPanel)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(nomeInput, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dosesInput, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(difInput, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tempoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cozinhaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Create)))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eliminarPrato)
                    .addComponent(resetBtn))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nomeInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cozinhaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchBtn)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(resetBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dosesInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(difInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(tempoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(eliminarPrato)
                        .addComponent(Create)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed

        DefaultListModel listModel = new DefaultListModel();
        String header = String.format("%-3s %-70s %-11s %-8s %-5s %-20s", "ID", "NOME", "DIFICULDADE", "TEMPO", "DOSES", "COZINHA");
        listModel.addElement(header);

        List<Prato> pratosInDocs = getListInDocs();
        List<Prato> pratosInRel = getListInRelational();

        for (int i = 0; i < pratosInDocs.size(); i++) {
            for (int j = 0; j < pratosInRel.size(); j++) {
                if (pratosInDocs.get(i).getID() == pratosInRel.get(j).getID()) {
                    Prato prato = new Prato(pratosInRel.get(j).getID(), pratosInRel.get(j).getNome(),
                            pratosInRel.get(j).getDescricao(), pratosInDocs.get(i).getCozinha(),
                            pratosInDocs.get(i).getDificuldade(), pratosInDocs.get(i).getTempo(),
                            pratosInDocs.get(i).getDoses());

                    listModel.addElement(prato.toString());
                }
            }
        }
        list.setModel(listModel);
    }//GEN-LAST:event_searchBtnActionPerformed

    private void listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseClicked
        int i = -1;
        String p = list.getSelectedValue();

        Matcher matcher = Pattern.compile("\\d+").matcher(p);
        if (matcher.find()) {
            i = Integer.valueOf(matcher.group());
        }
        selectedId = i;
        if (evt.getClickCount() == 2 && selectedId != -1) {
            PratoEdit editForm = new PratoEdit(i);
            editForm.setVisible(true);
        }
    }//GEN-LAST:event_listMouseClicked

    private void eliminarPratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarPratoActionPerformed
        if (selectedId != -1) {
            if (JOptionPane.showConfirmDialog(null, "Tem a certeza que pertende eliminar o prato ?", "Eliminar Prato", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    DocDB.deletePratoDoc(selectedId);
                    GraphDB.deletePrato(selectedId);
                    KeyValueDB.deletePratoImage(selectedId);
                    RelationalDB.deletePrato(selectedId);
                    JOptionPane.showMessageDialog(this, "O prato foi eliminado com sucesso!");
                } catch (SQLException ex) {
                    Logger.getLogger(PratoSearch.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this,
                                            "O prato não foi eliminado!",
                                            "Erro na eliminação",
                                            JOptionPane.ERROR_MESSAGE);
                }
                searchBtn.doClick();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                                            "É necessario selecionar um prato para o eliminar!",
                                            "Informação",
                                            JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_eliminarPratoActionPerformed

    private void CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateActionPerformed
        PratoCreate createForm = new PratoCreate();
        createForm.setVisible(true);
    }//GEN-LAST:event_CreateActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        nomeInput.setText("");
        cozinhaInput.setSelectedIndex(0);
        difInput.setSelectedIndex(0);
        tempoInput.setSelectedIndex(0);
        dosesInput.setValue(0);
        searchBtn.doClick();
    }//GEN-LAST:event_resetBtnActionPerformed

    public List<Prato> getListInDocs() {
        Map<String,String> map = new HashMap<>();
        
        if (!cozinhaInput.getSelectedItem().toString().equals("*")) {
            map.put("Cozinha", cozinhaInput.getSelectedItem().toString());
        }
        if (!difInput.getSelectedItem().toString().equals("*")) {
            map.put("Dificuldade", difInput.getSelectedItem().toString());
        }
        if (!tempoInput.getSelectedItem().toString().equals("*")) {
            map.put("Tempo", tempoInput.getSelectedItem().toString());
        }
        if (!dosesInput.getValue().toString().equals("0")) {
            map.put("Doses", dosesInput.getValue().toString());
        }
        List<Prato> pratos = DocDB.selectPratos(map);

        return pratos;
    }

    public List<Prato> getListInRelational() {
        String query = "SELECT * FROM Pratos WHERE Nome LIKE '%" + nomeInput.getText() + "%'";
        List<Prato> pratos = new ArrayList<>();
        try {
            pratos = RelationalDB.selectPratos(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pratos;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PratoSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PratoSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PratoSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PratoSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PratoSearch().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Create;
    private javax.swing.JComboBox<String> cozinhaInput;
    private javax.swing.JComboBox<String> difInput;
    private javax.swing.JSpinner dosesInput;
    private javax.swing.JButton eliminarPrato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> list;
    private javax.swing.JScrollPane listPanel;
    private javax.swing.JTextField nomeInput;
    private javax.swing.JButton resetBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JComboBox<String> tempoInput;
    // End of variables declaration//GEN-END:variables
}
