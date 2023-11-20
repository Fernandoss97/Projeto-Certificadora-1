package Telas;

import Conexao.ModuloConexao;
import static Telas.Principal.ctRA;
import static Telas.Problema6.lblProb6;
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

public class Problema5 extends javax.swing.JFrame {

    private int respUsuario;
    private float respSistema;
    private float respVazao;
    
    int idUsuario = Integer.parseInt(ctRA.getText());
    int idQuestao = 5;

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Problema5() {
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
                    JOptionPane.showMessageDialog(Problema5.this, "Por favor, insira sua resposta.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btProx.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                if (respSistema == 0) {
                    JOptionPane.showMessageDialog(Problema5.this, "Coloque sua Resposta", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        verificar();
                    } catch (SQLException ex) {
                        Logger.getLogger(Problema5.class.getName()).log(Level.SEVERE, null, ex);
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
            
            float volume = Float.parseFloat(txtVolume.getText());
            float tempo = Float.parseFloat(txtTempo.getText());

            respVazao = volume / tempo;
            lblVazao.setText(":" + respVazao);

            int voltagem = Integer.parseInt(txtVoltagem.getText());
            int densidade = Integer.parseInt(txtDensidade.getText());
            float calor = Float.parseFloat(txtCalor.getText());
            int temperatura = Integer.parseInt(txtTemperatura.getText());
            
            respSistema = ((densidade * respVazao * calor * temperatura)/ voltagem);
            lblRespSistema.setText(":" + respSistema);

            respUsuario = Integer.parseInt(txtRespUsuario.getText());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificar() throws SQLException {

        if ((respUsuario >= (respSistema-3)) && (respUsuario <= (respSistema+3))) {
            ArmazenarResposta();
            JOptionPane.showMessageDialog(this, "Parabéns! Você acertou!.", "Acerto", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Desculpe, parece que a resposta está incorreta. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        // Limpar campos para a próxima pergunta
        limpar();
    }

    private void limpar() {
        txtVolume.setText("");
        txtTempo.setText("");
        txtVoltagem.setText("");
        txtDensidade.setText("");
        txtCalor.setText("");
        txtTemperatura.setText("");
        lblVazao.setText("");
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
        btPula = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblProb5 = new javax.swing.JLabel();
        btProx = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtVolume = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTempo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblVazao = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtVoltagem = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDensidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCalor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTemperatura = new javax.swing.JTextField();
        btCalc = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtRespUsuario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lblRespSistema = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Problema 5");

        jPanel1.setBackground(new java.awt.Color(56, 115, 115));

        btPula.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btPula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/pular.png"))); // NOI18N
        btPula.setBorderPainted(false);
        btPula.setContentAreaFilled(false);
        btPula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPulaActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(lblProb5);

        btProx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/proximo.png"))); // NOI18N
        btProx.setBorderPainted(false);
        btProx.setContentAreaFilled(false);
        btProx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Volume:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tempo:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Vazão:");

        lblVazao.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblVazao.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Voltagem:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Densidade:");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Calor Específico:");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Temperatura:");

        btCalc.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btCalc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/calculadora.png"))); // NOI18N
        btCalc.setBorderPainted(false);
        btCalc.setContentAreaFilled(false);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Sua Resposta:");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Resposta Calculada Pelo Sistema:");

        lblRespSistema.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblRespSistema.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btPula)
                .addGap(39, 39, 39)
                .addComponent(btProx, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(txtVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(80, 80, 80)
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(59, 59, 59)
                            .addComponent(jLabel3)
                            .addGap(12, 12, 12)
                            .addComponent(lblVazao, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(15, 15, 15)
                            .addComponent(btCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(jLabel4)
                            .addGap(21, 21, 21)
                            .addComponent(txtVoltagem, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jLabel5)
                            .addGap(21, 21, 21)
                            .addComponent(txtDensidade, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(16, 16, 16)
                            .addComponent(jLabel6)
                            .addGap(12, 12, 12)
                            .addComponent(txtCalor, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jLabel7)
                            .addGap(7, 7, 7)
                            .addComponent(txtTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(jLabel8)
                            .addGap(20, 20, 20)
                            .addComponent(txtRespUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(110, 110, 110)
                            .addComponent(jLabel9)
                            .addGap(24, 24, 24)
                            .addComponent(lblRespSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(24, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btPula, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btProx, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(514, 514, 514)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(txtVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(txtTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(jLabel3))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(lblVazao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btCalc))
                    .addGap(12, 12, 12)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(txtVoltagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtDensidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(txtCalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(txtTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(21, 21, 21)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addComponent(txtRespUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(lblRespSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btProxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProxActionPerformed
         if (respSistema == 0) {
            Problema6 problema6 = new Problema6();
            problema6.setVisible(false);
        } else if ((respUsuario >= (respSistema-3)) && (respUsuario <= (respSistema+3))) {
            this.dispose();
            Problema6 problema6 = new Problema6();
            problema6.setVisible(true);
            String sql = "SELECT dados FROM imagens WHERE id = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, 6); // Substitua '1' pelo ID da imagem que você deseja exibir
                rs = pst.executeQuery();
                if (rs.next()) {
                    byte[] img = rs.getBytes("dados");
                    ImageIcon imagem = new ImageIcon(img);
                    Image imgRedimensionada = imagem.getImage().getScaledInstance(lblProb6.getWidth(), lblProb6.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imagemRedimensionada = new ImageIcon(imgRedimensionada);
                    lblProb6.setIcon(imagemRedimensionada);
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
            Problema6 problema6 = new Problema6();
            problema6.setVisible(false);
        }
    }//GEN-LAST:event_btProxActionPerformed

    private void btPulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPulaActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "Deseja Pular a Questão?", "Pular Questão", JOptionPane.YES_NO_CANCEL_OPTION, 2);
        if (resp == 0) {
            this.dispose();
            Problema6 problema6 = new Problema6();
            problema6.setVisible(true);
            String sql = "SELECT dados FROM imagens WHERE id = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, 6); // Substitua '1' pelo ID da imagem que você deseja exibir
                rs = pst.executeQuery();
                if (rs.next()) {
                    byte[] img = rs.getBytes("dados");
                    ImageIcon imagem = new ImageIcon(img);
                    Image imgRedimensionada = imagem.getImage().getScaledInstance(lblProb6.getWidth(), lblProb6.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imagemRedimensionada = new ImageIcon(imgRedimensionada);
                    lblProb6.setIcon(imagemRedimensionada);
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
            java.util.logging.Logger.getLogger(Problema5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Problema5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Problema5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Problema5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Problema5().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCalc;
    private javax.swing.JButton btProx;
    private javax.swing.JButton btPula;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblProb5;
    private javax.swing.JLabel lblRespSistema;
    private javax.swing.JLabel lblVazao;
    private javax.swing.JTextField txtCalor;
    private javax.swing.JTextField txtDensidade;
    private javax.swing.JTextField txtRespUsuario;
    private javax.swing.JTextField txtTemperatura;
    private javax.swing.JTextField txtTempo;
    private javax.swing.JTextField txtVoltagem;
    private javax.swing.JTextField txtVolume;
    // End of variables declaration//GEN-END:variables

}
