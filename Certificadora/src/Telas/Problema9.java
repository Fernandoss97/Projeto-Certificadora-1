package Telas;

import Conexao.ModuloConexao;
import static Telas.Principal.ctRA;
import java.awt.Image;
import java.sql.Connection;
import static Telas.Problema8.lblProb8;
import static Telas.Problema10.lblProb10;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Problema9 extends javax.swing.JFrame {

    private float respUsuario;
    private double respSistema;
    private double x1;
    private double x2;
    
    int idUsuario = Integer.parseInt(ctRA.getText());
    int idQuestao = 9;

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Problema9() {
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
                    JOptionPane.showMessageDialog(Problema9.this, "Por favor, insira sua resposta.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btProx.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                if (respSistema == 0) {
                    JOptionPane.showMessageDialog(Problema9.this, "Coloque sua Resposta", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        verificar();
                    } catch (SQLException ex) {
                        Logger.getLogger(Problema9.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    private boolean validar() {
        return !txtRespUsuario.getText().isEmpty();
    }

    private void calcular() {
        try {
            float altura = Float.parseFloat(txtAltura.getText());
            float gravidade = Float.parseFloat(txtGravidade.getText());
            float velocidadeInicial = Float.parseFloat(txtVelocidade.getText());
            float angulo = Float.parseFloat(txtAngulo.getText());
            double rad = (Math.PI / 180) * angulo;
            double b = velocidadeInicial * Math.sin((rad));
            gravidade = gravidade / 2;
            double delta = Math.pow(b, 2) - 4 * (gravidade) * altura;

            double x1 = (float) ((b + (Math.sqrt(delta))) / (2 * gravidade)); //2a

            double x2 = (float) ((b - (Math.sqrt(delta))) / (2 * gravidade)); //2a

            respSistema = (float) x1 - x2;

            lblRespSistema.setText(":" + respSistema);

            respUsuario = Float.parseFloat(txtRespUsuario.getText());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificar() throws SQLException {

        if (respUsuario == respSistema) {
             ArmazenarResposta();
            JOptionPane.showMessageDialog(this, "Parabéns! Você acertou!.", "Acerto", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Desculpe, parece que a resposta está incorreta. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        // Limpar campos para a próxima pergunta
        limpar();
    }

    private void limpar() {
        txtAltura.setText("");
        txtGravidade.setText("");
        txtVelocidade.setText("");
        txtAngulo.setText("");
        lblRespSistema.setText("");
        txtRespUsuario.setText("");
    }
    
    private void ArmazenarResposta() throws SQLException {
    String sql = "INSERT INTO Respostas (usuario_ra, questao_id, resposta_dada) VALUES (?, ?, ?)";
    try {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, idUsuario);
        pst.setInt(2, idQuestao);
        pst.setFloat(3, respUsuario); // Se respostaUsuario for do tipo inteiro

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
        jScrollPane1 = new javax.swing.JScrollPane();
        lblProb9 = new javax.swing.JLabel();
        btVolta = new javax.swing.JButton();
        btProx = new javax.swing.JButton();
        btPula = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtAltura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtGravidade = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtVelocidade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAngulo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtRespUsuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblRespSistema = new javax.swing.JLabel();
        btCalc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Problema 9");

        jPanel1.setBackground(new java.awt.Color(56, 115, 115));

        jScrollPane1.setViewportView(lblProb9);

        btVolta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/volta.png"))); // NOI18N
        btVolta.setBorderPainted(false);
        btVolta.setContentAreaFilled(false);
        btVolta.setFocusPainted(false);
        btVolta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltaActionPerformed(evt);
            }
        });

        btProx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/proximo.png"))); // NOI18N
        btProx.setBorderPainted(false);
        btProx.setContentAreaFilled(false);
        btProx.setFocusPainted(false);
        btProx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProxActionPerformed(evt);
            }
        });

        btPula.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btPula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/pular.png"))); // NOI18N
        btPula.setBorderPainted(false);
        btPula.setContentAreaFilled(false);
        btPula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPulaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Altura:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Gravidade:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Velocidade Inicial:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ângulo:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sua Resposta:");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Resposta Calculada Pelo Sistema:");

        lblRespSistema.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblRespSistema.setForeground(new java.awt.Color(255, 255, 255));

        btCalc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/calculadora.png"))); // NOI18N
        btCalc.setBorderPainted(false);
        btCalc.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 941, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(705, 705, 705)
                            .addComponent(btPula)
                            .addGap(18, 18, 18)
                            .addComponent(btProx, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtAngulo))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(139, 139, 139)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblRespSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtGravidade, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)
                                        .addComponent(jLabel3)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtRespUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(11, Short.MAX_VALUE))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 642, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btProx, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btPula, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(txtGravidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(txtRespUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblRespSistema, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap(24, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btCalc)
                            .addContainerGap()))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            Problema8 problema8 = new Problema8();
            problema8.setVisible(true);

            String sql = "SELECT dados FROM imagens WHERE id = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, 8); // Substitua '1' pelo ID da imagem que você deseja exibir
                rs = pst.executeQuery();
                if (rs.next()) {
                    byte[] img = rs.getBytes("dados");
                    ImageIcon imagem = new ImageIcon(img);
                    Image imgRedimensionada = imagem.getImage().getScaledInstance(lblProb8.getWidth(), lblProb8.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imagemRedimensionada = new ImageIcon(imgRedimensionada);
                    lblProb8.setIcon(imagemRedimensionada);
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

    private void btProxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProxActionPerformed
        if (respSistema == 0) {
            Problema10 problema10 = new Problema10();
            problema10.setVisible(false);
        } else if (respUsuario == respSistema) {
            this.dispose();
            Problema10 problema10 = new Problema10();
            problema10.setVisible(true);
            String sql = "SELECT dados FROM imagens WHERE id = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, 10); // Substitua '1' pelo ID da imagem que você deseja exibir
                rs = pst.executeQuery();
                if (rs.next()) {
                    byte[] img = rs.getBytes("dados");
                    ImageIcon imagem = new ImageIcon(img);
                    Image imgRedimensionada = imagem.getImage().getScaledInstance(lblProb10.getWidth(), lblProb10.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imagemRedimensionada = new ImageIcon(imgRedimensionada);
                    lblProb10.setIcon(imagemRedimensionada);
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
            Problema10 problema10 = new Problema10();
            problema10.setVisible(false);
        }
    }//GEN-LAST:event_btProxActionPerformed

    private void btPulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPulaActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "Deseja Pular a Questão?", "Pular Questão", JOptionPane.YES_NO_CANCEL_OPTION, 2);
        if (resp == 0) {
            this.dispose();
            Problema10 problema10 = new Problema10();
            problema10.setVisible(true);
            String sql = "SELECT dados FROM imagens WHERE id = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, 10); // Substitua '1' pelo ID da imagem que você deseja exibir
                rs = pst.executeQuery();
                if (rs.next()) {
                    byte[] img = rs.getBytes("dados");
                    ImageIcon imagem = new ImageIcon(img);
                    Image imgRedimensionada = imagem.getImage().getScaledInstance(lblProb10.getWidth(), lblProb10.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imagemRedimensionada = new ImageIcon(imgRedimensionada);
                    lblProb10.setIcon(imagemRedimensionada);
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
    }//GEN-LAST:event_btPulaActionPerformed

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
            java.util.logging.Logger.getLogger(Problema9.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Problema9.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Problema9.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Problema9.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Problema9().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCalc;
    private javax.swing.JButton btProx;
    private javax.swing.JButton btPula;
    private javax.swing.JButton btVolta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblProb9;
    private javax.swing.JLabel lblRespSistema;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtAngulo;
    private javax.swing.JTextField txtGravidade;
    private javax.swing.JTextField txtRespUsuario;
    private javax.swing.JTextField txtVelocidade;
    // End of variables declaration//GEN-END:variables
}
