/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import databases.DocDB;
import databases.KeyValueDB;
import databases.RelationalDB;
import entities.Prato;
import java.io.File;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel
 */
public class PratoEdit extends javax.swing.JFrame {

    private int idPrato;
    private String imgPath;

    public PratoEdit(int id) {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Detalhes do Prato");
        this.setLocationRelativeTo(null);

        this.idPrato = id;

        fillInputs();
        disableInputs();
    }

    private void fillInputs() {
        try {
            Prato pratoRelInfo = RelationalDB.getPrato(idPrato);
            tituloInput.setText(pratoRelInfo.getNome());
            descricaoInput.setText(pratoRelInfo.getDescricao());

            Prato pratoDocInfo = DocDB.getPrato(idPrato);
            cozinhaInput.setSelectedItem(pratoDocInfo.getCozinha());
            dosesInput.setValue(pratoDocInfo.getDoses());
            difInput.setSelectedItem(pratoDocInfo.getDificuldade());
            tempoInput.setSelectedItem(pratoDocInfo.getTempo());
            preparacaoInput.setText(pratoDocInfo.getPreparacao());

            byte[] img = KeyValueDB.getImage(idPrato);
            if (img != null) {
                ImageIcon icon = new ImageIcon(img);
                imgLabel.setIcon(icon);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void disableInputs() {
        tituloInput.setEditable(false);
        descricaoInput.setEditable(false);
        cozinhaInput.setEnabled(false);
        difInput.setEnabled(false);
        tempoInput.setEnabled(false);
        preparacaoInput.setEditable(false);
        saveBtn.setVisible(false);
        cancelBtn.setVisible(false);
        editBtn.setVisible(true);
        editImgLabel.setVisible(false);
        imgDirectoryLabel.setVisible(false);
        searchImgBtn.setVisible(false);
    }

    private void enableInputs() {
        tituloInput.setEditable(true);
        descricaoInput.setEditable(true);
        cozinhaInput.setEnabled(true);
        difInput.setEnabled(true);
        tempoInput.setEnabled(true);
        preparacaoInput.setEditable(true);
        saveBtn.setVisible(true);
        cancelBtn.setVisible(true);
        editBtn.setVisible(false);
        editImgLabel.setVisible(true);
        imgDirectoryLabel.setVisible(true);
        searchImgBtn.setVisible(true);
        imgDirectoryLabel.setText("Sem alterações");
        imgPath = "";
    }

    public PratoEdit() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tituloInput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cozinhaInput = new javax.swing.JComboBox<>();
        dosesInput = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        difInput = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        tempoInput = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        ingredientesBtn = new javax.swing.JButton();
        imgLabel = new javax.swing.JLabel();
        searchImgBtn = new javax.swing.JButton();
        editImgLabel = new javax.swing.JLabel();
        imgDirectoryLabel = new javax.swing.JLabel();
        editBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoInput = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        preparacaoInput = new javax.swing.JTextArea();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Título");

        jLabel2.setText("Descrição");

        jLabel3.setText("Preparação");

        jLabel4.setText("Tipo de Cozinha");

        cozinhaInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Contemporânea", "Internacional", "Contemporânea Fusão", "Vegetariana", "Internacional Contemporânea", "Portuguesa Tradicional", "Brasileira Vegetariana", "Japonesa", "Suíça", "Contemporânea Saudável", "Italiana", "Mediterrânea", "Britânica", "Francesa Contemporânea", "Outro" }));

        dosesInput.setModel(new javax.swing.SpinnerNumberModel(0, 0, 12, 2));

        jLabel5.setText("Doses");

        difInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fácil", "Médio", "Difícil" }));

        jLabel6.setText("Dificuldade");

        tempoInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lento", "Médio", "Rápido" }));

        jLabel7.setText("Tempo");

        ingredientesBtn.setText("Ingredientes");
        ingredientesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredientesBtnActionPerformed(evt);
            }
        });

        imgLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgLabel.setMinimumSize(new java.awt.Dimension(364, 289));

        searchImgBtn.setText("Browse");
        searchImgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchImgBtnActionPerformed(evt);
            }
        });

        editImgLabel.setText("Editar Imagem");
        editImgLabel.setToolTipText("");

        imgDirectoryLabel.setText("Sem alterações");

        editBtn.setText("Editar");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        saveBtn.setText("Guardar");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        descricaoInput.setColumns(20);
        descricaoInput.setRows(5);
        jScrollPane1.setViewportView(descricaoInput);

        preparacaoInput.setColumns(20);
        preparacaoInput.setRows(5);
        jScrollPane2.setViewportView(preparacaoInput);

        cancelBtn.setText("Cancelar");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(imgDirectoryLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(searchImgBtn))
                                .addComponent(editImgLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(tituloInput, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel2)
                                .addComponent(jScrollPane1)
                                .addComponent(jScrollPane2))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cozinhaInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(ingredientesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(dosesInput, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(difInput, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tempoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(imgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(cozinhaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tituloInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dosesInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(difInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(tempoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addComponent(ingredientesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editImgLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(imgDirectoryLabel)
                            .addComponent(searchImgBtn))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchImgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchImgBtnActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imgPath = selectedFile.getAbsolutePath();
            System.out.println("Selected file: " + imgPath);
        }
        if (imgPath.isEmpty())
            imgDirectoryLabel.setText("Sem alterações");
        else
            imgDirectoryLabel.setText(imgPath);
    }//GEN-LAST:event_searchImgBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        enableInputs();
        setTitle("Editar Prato");
    }//GEN-LAST:event_editBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        disableInputs();
        fillInputs();
        setTitle("Detalhes do Prato");
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        disableInputs();
        try {
            Prato pratoDocUpdate = new Prato(idPrato, cozinhaInput.getSelectedItem().toString(), difInput.getSelectedItem().toString(), tempoInput.getSelectedItem().toString(), Integer.parseInt(dosesInput.getValue().toString()), preparacaoInput.getText());
            DocDB.updatePratoDoc(pratoDocUpdate);
            if (!imgPath.isEmpty()) {
                KeyValueDB.updatePratoImg(idPrato, imgPath);
            }
            RelationalDB.updatePrato(idPrato, tituloInput.getText(), descricaoInput.getText());

            JOptionPane.showMessageDialog(this, "O prato foi alterado com sucesso!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(this, "ERRO.\nO prato não foi alterado.");
        }

        fillInputs();
        setTitle("Detalhes do Prato");

    }//GEN-LAST:event_saveBtnActionPerformed

    private void ingredientesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredientesBtnActionPerformed
        IngredientesInsert ingredientesForm = new IngredientesInsert(idPrato);
        ingredientesForm.setVisible(true);
    }//GEN-LAST:event_ingredientesBtnActionPerformed

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
            java.util.logging.Logger.getLogger(PratoEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PratoEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PratoEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PratoEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PratoEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JComboBox<String> cozinhaInput;
    private javax.swing.JTextArea descricaoInput;
    private javax.swing.JComboBox<String> difInput;
    private javax.swing.JSpinner dosesInput;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel editImgLabel;
    private javax.swing.JLabel imgDirectoryLabel;
    private javax.swing.JLabel imgLabel;
    private javax.swing.JButton ingredientesBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea preparacaoInput;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton searchImgBtn;
    private javax.swing.JComboBox<String> tempoInput;
    private javax.swing.JTextField tituloInput;
    // End of variables declaration//GEN-END:variables
}
