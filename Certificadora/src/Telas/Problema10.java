package Telas;

import Conexao.ModuloConexao;
import static Telas.Principal.ctRA;
import static Telas.Problema9.lblProb9;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Problema10 extends javax.swing.JFrame {

    private double respUsuario;
    private double respSistema;
    
    int idUsuario = Integer.parseInt(ctRA.getText());
    int idQuestao = 10;
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Problema10() {
        initComponents();
        conexao = ModuloConexao.conector();
        adicionarActionListeners();
    }

     private void adicionarActionListeners() {
        btCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validar()) {
                    calcular();
                } else {
                    JOptionPane.showMessageDialog(Problema10.this, "Por favor, insira sua resposta.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean validar() {
        return !txtRespUsuario.getText().isEmpty();
    }

    private void calcular() {
             try {
            double areaOleo = Double.parseDouble(txtOleo.getText());
            double tamanhoPonteFoto =  Double.parseDouble(txtPonteFoto.getText());
            double tamanhoPonteReal =  Double.parseDouble(txtPonteReal.getText());


            respSistema = Math.pow((Math.sqrt(areaOleo) * tamanhoPonteReal) / tamanhoPonteFoto,2)/1000000;

            lblRespSistema.setText(":" + respSistema);

            respUsuario = Float.parseFloat(txtRespUsuario.getText());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificar() throws SQLException {

        if ((respUsuario >= respSistema-0.5) && (respUsuario <= respSistema+0.5)) {
             ArmazenarResposta();
            JOptionPane.showMessageDialog(this, "Parabéns! Você acertou!.", "Acerto", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Desculpe, parece que a resposta está incorreta. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        // Limpar campos para a próxima pergunta
        limpar();
    }

    private void limpar() {
        txtOleo.setText("");
        txtPonteReal.setText("");
        txtPonteFoto.setText("");
        lblRespSistema.setText("");
        txtRespUsuario.setText("");
    }
    
    private void ArmazenarResposta() throws SQLException {
    String sql = "INSERT INTO Respostas (usuario_ra, questao_id, resposta_dada) VALUES (?, ?, ?)";
    try {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, idUsuario);
        pst.setInt(2, idQuestao);
        pst.setDouble(3, respUsuario); // Se respostaUsuario for do tipo inteiro

        // Execute a instrução SQL
        pst.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace(); // Imprima a exceção para depuração
    } finally {
        // Feche a conexão e os recursos
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btVolta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblProb10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtOleo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPonteReal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPonteFoto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtRespUsuario = new javax.swing.JTextField();
        btCalc = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblRespSistema = new javax.swing.JLabel();
        btConfirma = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Problema 10");

        jPanel1.setBackground(new java.awt.Color(56, 115, 115));

        btVolta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/volta.png"))); // NOI18N
        btVolta.setBorderPainted(false);
        btVolta.setContentAreaFilled(false);
        btVolta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltaActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(lblProb10);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Área do Óleo:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tamanho da Ponte Real (m):");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tamanho Ponte Foto:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Sua Resposta:");

        btCalc.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btCalc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/calculadora.png"))); // NOI18N
        btCalc.setBorderPainted(false);
        btCalc.setContentAreaFilled(false);
        btCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCalcActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Resposta Calculada Pelo Sistema:");

        lblRespSistema.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblRespSistema.setForeground(new java.awt.Color(255, 255, 255));

        btConfirma.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btConfirma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/confirmar.png"))); // NOI18N
        btConfirma.setBorderPainted(false);
        btConfirma.setContentAreaFilled(false);
        btConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfirmaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(823, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(txtOleo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(238, 238, 238)
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtPonteReal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtPonteFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(68, 68, 68)
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtRespUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(32, 32, 32)
                                    .addComponent(jLabel5)
                                    .addGap(6, 6, 6)
                                    .addComponent(lblRespSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(8, 8, 8)
                            .addComponent(btCalc)
                            .addGap(88, 88, 88)
                            .addComponent(btConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(28, Short.MAX_VALUE))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(635, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtOleo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPonteReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(7, 7, 7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(jLabel3))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPonteFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(txtRespUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(20, 20, 20)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(lblRespSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btCalc)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(btConfirma)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btVoltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltaActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "Voltar para a questão anterior?", "Voltar", JOptionPane.YES_NO_CANCEL_OPTION);
        if (resp == 0) {
            this.dispose();
            Problema9 problema9 = new Problema9();
            problema9.setVisible(true);

             String sql = "SELECT dados FROM imagens WHERE id = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, 9); // Substitua '1' pelo ID da imagem que você deseja exibir
                rs = pst.executeQuery();
                if (rs.next()) {
                    byte[] img = rs.getBytes("dados");
                    ImageIcon imagem = new ImageIcon(img);
                    Image imgRedimensionada = imagem.getImage().getScaledInstance(lblProb9.getWidth(), lblProb9.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imagemRedimensionada = new ImageIcon(imgRedimensionada);
                    lblProb9.setIcon(imagemRedimensionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Imagem não encontrada.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar imagem: " + e.getMessage());
            } finally {
                // Feche a conexão e os recursos
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btVoltaActionPerformed

    private void btCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCalcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCalcActionPerformed

    private void btConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfirmaActionPerformed
       if(respUsuario == 0){
           JOptionPane.showMessageDialog(Problema10.this, "Por favor, insira sua resposta.", "Erro", JOptionPane.ERROR_MESSAGE);
       }else{
           try {
               verificar();
           } catch (SQLException ex) {
               Logger.getLogger(Problema10.class.getName()).log(Level.SEVERE, null, ex);
           }
        this.dispose();
       }
    }//GEN-LAST:event_btConfirmaActionPerformed

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
            java.util.logging.Logger.getLogger(Problema10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Problema10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Problema10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Problema10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Problema10().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCalc;
    private javax.swing.JButton btConfirma;
    private javax.swing.JButton btVolta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblProb10;
    private javax.swing.JLabel lblRespSistema;
    private javax.swing.JTextField txtOleo;
    private javax.swing.JTextField txtPonteFoto;
    private javax.swing.JTextField txtPonteReal;
    private javax.swing.JTextField txtRespUsuario;
    // End of variables declaration//GEN-END:variables
}
