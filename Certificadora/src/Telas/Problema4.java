package Telas;

import Conexao.ModuloConexao;
import static Telas.Principal.ctRA;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static Telas.Problema3.lblProb3;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Problema4 extends javax.swing.JFrame {

    private double respUsuario;
    private double respSistema;

    int idUsuario = Integer.parseInt(ctRA.getText());
    int idQuestao = 4;

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Problema4() {
        initComponents();
        conexao = ModuloConexao.conector();
        adicionarActionListeners();
    }

    private ResultSet buscarResposta(int usuarioRa, int questaoId) throws SQLException {
        String sqbusca = "SELECT resposta_dada FROM Respostas WHERE usuario_ra = ? AND questao_id = ?";
        pst = conexao.prepareStatement(sqbusca);
        pst.setInt(1, usuarioRa);
        pst.setInt(2, questaoId);
        return pst.executeQuery();
    }

    private void pontuacao(int usuarioRa) {
        try {
            rs = buscarResposta(idUsuario, 4); // Substituir '1' pelo ID da questão

            if (rs.next()) {
                String resposta = rs.getString("resposta_dada");
                if (resposta != null || !resposta.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "A questão ja foi respondida anteriormente. A pontuação da questão será reduzida pela metade ");
                    String pontos = "UPDATE Pontuacao SET Pontos = Pontos + 50 WHERE RA = ?";
                    pst = conexao.prepareStatement(pontos);
                    pst.setInt(1, usuarioRa);
                    pst.executeUpdate();
                }
            } else {
                String pontos = "UPDATE Pontuacao SET Pontos = Pontos + 100 WHERE RA = ?";
                pst = conexao.prepareStatement(pontos);
                pst.setInt(1, usuarioRa);
                pst.executeUpdate();
            }
            String sqlPontuacao = "SELECT Pontos FROM Pontuacao WHERE RA = ?";
            pst = conexao.prepareStatement(sqlPontuacao);
            pst.setInt(1, usuarioRa);
            rs = pst.executeQuery();

            if (rs.next()) {
                int novaPontuacao = rs.getInt("Pontos");
                Inicial.atualizarPontuacao(Integer.toString(novaPontuacao));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
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

    private void adicionarActionListeners() {
        btCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validar()) {
                    calcular();
                } else {
                    JOptionPane.showMessageDialog(Problema4.this, "Por favor, insira sua resposta.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean validar() {
        return !txtRespUsuario.getText().isEmpty();
    }

    private void calcular() {
        try {
            float velocidade = Float.parseFloat(txtVelocidade.getText());
            float distancia = Float.parseFloat(txtDistancia.getText());
            double velMs = velocidade / 3.6;
            lblVelocidade.setText(":" + velMs);
            respSistema = (float) (velMs / distancia);

            lblRespSistema.setText(":" + respSistema);

            respUsuario = Float.parseFloat(txtRespUsuario.getText());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificar() throws SQLException {
        if (respUsuario == respSistema) {
            pontuacao(idUsuario);
            try {
                ArmazenarResposta();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
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
            JOptionPane.showMessageDialog(this, "Parabéns! Você acertou!.", "Acerto", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Desculpe, parece que a resposta está incorreta. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        // Limpar campos para a próxima pergunta
        limpar();
    }

    private void atualizarResposta(int usuarioRa, int questaoId, String novaResposta) throws SQLException {
        String sqlUpdate = "UPDATE Respostas SET resposta_dada = ? WHERE usuario_ra = ? AND questao_id = ?";
        try {
            pst = conexao.prepareStatement(sqlUpdate);
            pst.setString(1, novaResposta);
            pst.setInt(2, usuarioRa);
            pst.setInt(3, questaoId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

    private void limpar() {
        txtVelocidade.setText("");
        txtDistancia.setText("");
        lblVelocidade.setText("");
        lblRespSistema.setText("");
        txtRespUsuario.setText("");
    }

    private void ArmazenarResposta() throws SQLException {
        String sql = "SELECT resposta_dada FROM Respostas WHERE usuario_ra = ? AND questao_id = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            pst.setInt(2, idQuestao);

            // Execute a consulta SQL
            rs = pst.executeQuery();

            if (rs.next()) {
                // Se a resposta existe, atualiza
                atualizarResposta(idUsuario, idQuestao, String.valueOf(respUsuario));
            } else {
                // Se não existir, insira uma nova resposta
                String insertSql = "INSERT INTO Respostas (usuario_ra, questao_id, resposta_dada) VALUES (?, ?, ?)";
                pst = conexao.prepareStatement(insertSql);
                pst.setInt(1, idUsuario);
                pst.setInt(2, idQuestao);
                pst.setString(3, String.valueOf(respUsuario));

                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        lblProb4 = new javax.swing.JLabel();
        btVolta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtVelocidade = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblVelocidade = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDistancia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtRespUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblRespSistema = new javax.swing.JLabel();
        btCalc = new javax.swing.JButton();
        btConfirma = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Problema 4");

        jPanel1.setBackground(new java.awt.Color(56, 115, 115));

        jScrollPane1.setViewportView(lblProb4);

        btVolta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/volta.png"))); // NOI18N
        btVolta.setBorderPainted(false);
        btVolta.setContentAreaFilled(false);
        btVolta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Velocidade:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Velocidade em m/s:");

        lblVelocidade.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblVelocidade.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Distância (m):");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Sua Resposta:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Resposta Calculada Pelo Sistema:");

        lblRespSistema.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblRespSistema.setForeground(new java.awt.Color(255, 255, 255));

        btCalc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/calculadora.png"))); // NOI18N
        btCalc.setBorderPainted(false);
        btCalc.setContentAreaFilled(false);

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
                .addGap(12, 12, 12)
                .addComponent(btVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(lblVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jLabel3)
                .addGap(6, 6, 6)
                .addComponent(txtDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addGap(20, 20, 20)
                .addComponent(txtRespUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(jLabel5)
                .addGap(12, 12, 12)
                .addComponent(lblRespSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btCalc)
                .addGap(18, 18, 18)
                .addComponent(btConfirma))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(btVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(lblVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCalc)
                    .addComponent(btConfirma)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtRespUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(lblRespSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btVoltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltaActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "Voltar para a questão anterior?", "Voltar", JOptionPane.YES_NO_CANCEL_OPTION);
        if (resp == 0) {
            this.dispose();
            Problema3 problema3 = new Problema3();
            problema3.setVisible(true);

            String sql = "SELECT dados FROM imagens WHERE id = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, 3); // Substitua '1' pelo ID da imagem que você deseja exibir
                rs = pst.executeQuery();
                if (rs.next()) {
                    byte[] img = rs.getBytes("dados");
                    ImageIcon imagem = new ImageIcon(img);
                    Image imgRedimensionada = imagem.getImage().getScaledInstance(lblProb3.getWidth(), lblProb3.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imagemRedimensionada = new ImageIcon(imgRedimensionada);
                    lblProb3.setIcon(imagemRedimensionada);
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

    private void btConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfirmaActionPerformed
        if (respUsuario == 0) {
            JOptionPane.showMessageDialog(Problema4.this, "Por favor, insira sua resposta.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                verificar();
            } catch (SQLException ex) {
                Logger.getLogger(Problema4.class.getName()).log(Level.SEVERE, null, ex);
            }

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
            java.util.logging.Logger.getLogger(Problema4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Problema4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Problema4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Problema4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Problema4().setVisible(true);
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
    public static javax.swing.JLabel lblProb4;
    private javax.swing.JLabel lblRespSistema;
    private javax.swing.JLabel lblVelocidade;
    private javax.swing.JTextField txtDistancia;
    private javax.swing.JTextField txtRespUsuario;
    private javax.swing.JTextField txtVelocidade;
    // End of variables declaration//GEN-END:variables
}
