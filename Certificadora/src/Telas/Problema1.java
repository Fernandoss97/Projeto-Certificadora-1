package Telas;

import Conexao.ModuloConexao;
import static Telas.Principal.ctRA;
import static Telas.Problema2.lblProb2;
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

public class Problema1 extends javax.swing.JFrame {

    private float respostaUsuario;
    private float respostaCalculada;

    int idUsuario = Integer.parseInt(ctRA.getText());
    int idQuestao = 1;

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Problema1() {
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
                    JOptionPane.showMessageDialog(Problema1.this, "Por favor, insira sua resposta.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btProx.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                if (respostaCalculada == 0) {
                    JOptionPane.showMessageDialog(Problema1.this, "Coloque sua Resposta", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        verificar();
                    } catch (SQLException ex) {
                        Logger.getLogger(Problema1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    private boolean validar() {
        return !txtResp1.getText().isEmpty();
    }

    private void calcular() {
        try {
            float corrente = Float.parseFloat(txtCorrente.getText());
            float tempo = Float.parseFloat(txtTempo.getText());

            respostaCalculada = corrente * tempo;

            lblRespSistema.setText(":" + respostaCalculada);

            respostaUsuario = Float.parseFloat(txtResp1.getText());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificar() throws SQLException {

        if (respostaUsuario == respostaCalculada) {
            ArmazenarResposta();
            JOptionPane.showMessageDialog(this, "Parabéns! Você acertou!.", "Acerto", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Desculpe, parece que a resposta está incorreta. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        // Limpar campos para a próxima pergunta
        limpar();
    }

    private void limpar() {
        txtCorrente.setText("");
        txtTempo.setText("");
        lblRespSistema.setText("");
        txtResp1.setText("");
    }

    private void ArmazenarResposta() throws SQLException {
        String sql = "INSERT INTO Respostas (usuario_ra, questao_id, resposta_dada) VALUES (?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            pst.setInt(2, idQuestao);
            pst.setFloat(3, respostaUsuario); // Se respostaUsuario for do tipo inteiro

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
        Painel1 = new javax.swing.JScrollPane();
        lblProb1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtCorrente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTempo = new javax.swing.JTextField();
        btCalc = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtResp1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblRespSistema = new javax.swing.JLabel();
        btProx = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Problema 1");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(56, 115, 115));

        Painel1.setViewportView(lblProb1);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Corrente Elétrica:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tempo:");

        btCalc.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btCalc.setForeground(new java.awt.Color(255, 255, 255));
        btCalc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/calculadora.png"))); // NOI18N
        btCalc.setBorderPainted(false);
        btCalc.setContentAreaFilled(false);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Sua Resposta:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Resposta Calculada Pelo Sistema:");

        lblRespSistema.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblRespSistema.setForeground(new java.awt.Color(255, 255, 255));

        btProx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/proximo.png"))); // NOI18N
        btProx.setBorderPainted(false);
        btProx.setContentAreaFilled(false);
        btProx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProxActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/pular.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(213, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(lblRespSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtResp1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btCalc)))
                .addGap(104, 104, 104))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtCorrente, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(64, 64, 64)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(599, 599, Short.MAX_VALUE)
                            .addComponent(jButton1)
                            .addGap(18, 18, 18)
                            .addComponent(btProx, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(Painel1, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(453, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtResp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btCalc, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblRespSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btProx, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Painel1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtCorrente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(txtTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(86, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btProxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProxActionPerformed
        if (respostaCalculada == 0) {
            Problema2 problema2 = new Problema2();
            problema2.setVisible(false);
        } else if (respostaUsuario == respostaCalculada) {
            this.dispose();
            Problema2 problema2 = new Problema2();
            problema2.setVisible(true);
            String sql = "SELECT dados FROM imagens WHERE id = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, 2); // Substitua '1' pelo ID da imagem que você deseja exibir
                rs = pst.executeQuery();
                if (rs.next()) {
                    byte[] img = rs.getBytes("dados");
                    ImageIcon imagem = new ImageIcon(img);
                    Image imgRedimensionada = imagem.getImage().getScaledInstance(lblProb2.getWidth(), lblProb2.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imagemRedimensionada = new ImageIcon(imgRedimensionada);
                    lblProb2.setIcon(imagemRedimensionada);
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
        } else {
            Problema2 problema2 = new Problema2();
            problema2.setVisible(false);
        }
    }//GEN-LAST:event_btProxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "Deseja Pular a Questão?", "Pular Questão", JOptionPane.YES_NO_CANCEL_OPTION, 2);
        if (resp == 0) {
            this.dispose();
            Problema2 problema2 = new Problema2();
            problema2.setVisible(true);
            String sql = "SELECT dados FROM imagens WHERE id = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, 2); // Substitua '1' pelo ID da imagem que você deseja exibir
                rs = pst.executeQuery();
                if (rs.next()) {
                    byte[] img = rs.getBytes("dados");
                    ImageIcon imagem = new ImageIcon(img);
                    Image imgRedimensionada = imagem.getImage().getScaledInstance(lblProb2.getWidth(), lblProb2.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imagemRedimensionada = new ImageIcon(imgRedimensionada);
                    lblProb2.setIcon(imagemRedimensionada);
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
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Problema1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Problema1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Problema1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Problema1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Problema1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Painel1;
    private javax.swing.JButton btCalc;
    private javax.swing.JButton btProx;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JLabel lblProb1;
    private javax.swing.JLabel lblRespSistema;
    private javax.swing.JTextField txtCorrente;
    public static javax.swing.JTextField txtResp1;
    private javax.swing.JTextField txtTempo;
    // End of variables declaration//GEN-END:variables
}
