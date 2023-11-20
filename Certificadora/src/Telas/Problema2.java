package Telas;

import Conexao.ModuloConexao;
import static Telas.Principal.ctRA;
import static Telas.Problema1.lblProb1;
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

public class Problema2 extends javax.swing.JFrame {
    
    int idUsuario = Integer.parseInt(ctRA.getText());
    int idQuestao = 2;

    private double respostaUsuarioTerra;
    private double respostaUsuarioLua;
    private double respostaCalculadaTerra;
    private double respostaCalculadaLua;
    private double respostaGeral;


    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Problema2() {
        initComponents();
        conexao = ModuloConexao.conector();
        adicionarActionListeners();
    }

    private void adicionarActionListeners() {
        btCalcTerra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarEntradaTerra()) {
                    calcularTerra();
                } else {
                    JOptionPane.showMessageDialog(Problema2.this, "Por favor, insira sua resposta para a Terra.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btCalcLua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarEntradaLua()) {
                    calcularLua();
                } else {
                    JOptionPane.showMessageDialog(Problema2.this, "Por favor, insira sua resposta para a Lua.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btProx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((respostaCalculadaTerra == 0) || (respostaCalculadaLua == 0)) {
                    JOptionPane.showMessageDialog(Problema2.this, "Coloque sua Resposta.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        verificarRespostas();
                    } catch (SQLException ex) {
                        Logger.getLogger(Problema2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

    }

    private boolean validarEntradaTerra() {
        return !txtRespTerra.getText().isEmpty();
    }

    private boolean validarEntradaLua() {
        return !txtRespLua.getText().isEmpty();
    }

    private void calcularTerra() {
        try {
            double forcaPesoTerra = Double.parseDouble(txtPeso.getText());
            double gravidadeTerra = Double.parseDouble(txtGravidade.getText());

            respostaCalculadaTerra = forcaPesoTerra / gravidadeTerra;

            txtRespSistemaTerra.setText(":" + respostaCalculadaTerra);

            respostaUsuarioTerra = Double.parseDouble(txtRespTerra.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para o cálculo na Terra.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calcularLua() {
        try {
            double massaLua = Double.parseDouble(txtMassa.getText());
            double gravidadeLua = Double.parseDouble(txtGravidade1.getText());

            respostaCalculadaLua = massaLua * gravidadeLua;

            txtRespSistemaLua.setText(":" + respostaCalculadaLua);

            respostaUsuarioLua = Double.parseDouble(txtRespLua.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para o cálculo na Lua.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificarRespostas() throws SQLException {

        if ((respostaUsuarioTerra == respostaCalculadaTerra) && (respostaUsuarioLua == respostaCalculadaLua)) {
            ArmazenarResposta();
            JOptionPane.showMessageDialog(this, "Parabéns! Você acertou ambas as respostas.", "Acerto", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Desculpe, parece que uma ou ambas as respostas estão incorretas. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        // Limpar campos para a próxima pergunta
        limparCampos();
    }

    private void limparCampos() {
        txtPeso.setText("");
        txtGravidade.setText("");
        txtRespSistemaTerra.setText("");

        txtMassa.setText("");
        txtGravidade1.setText("");
        txtRespSistemaLua.setText("");

        txtRespTerra.setText("");
        txtRespLua.setText("");
    }
    
    private void ArmazenarResposta() throws SQLException {
    String sql = "INSERT INTO Respostas (usuario_ra, questao_id, resposta_dada) VALUES (?, ?, ?)";
    try {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, idUsuario);
        pst.setInt(2, idQuestao);
        pst.setDouble(3, respostaUsuarioTerra + respostaUsuarioLua); 

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
        lblProb2 = new javax.swing.JLabel();
        btProx = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtGravidade1 = new javax.swing.JTextField();
        btCalcTerra = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtRespTerra = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtRespSistemaTerra = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMassa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtGravidade = new javax.swing.JTextField();
        btCalcLua = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtRespLua = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtRespSistemaLua = new javax.swing.JLabel();
        btVolta = new javax.swing.JButton();
        btPula = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Problema 2");
        setName("Problema 2"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(56, 115, 115));

        jScrollPane1.setViewportView(lblProb2);

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
        jLabel1.setText("Força Peso:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Gravidade:");

        btCalcTerra.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btCalcTerra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/calculadora.png"))); // NOI18N
        btCalcTerra.setBorderPainted(false);
        btCalcTerra.setContentAreaFilled(false);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Sua Resposta:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Resposta Calculada Pelo Sistema:");

        txtRespSistemaTerra.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtRespSistemaTerra.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Peso na Lua");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Massa:");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Gravidade:");

        btCalcLua.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btCalcLua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/calculadora.png"))); // NOI18N
        btCalcLua.setBorderPainted(false);
        btCalcLua.setContentAreaFilled(false);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Sua Resposta:");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Resposta Calculada Pelo Sistema:");

        txtRespSistemaLua.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtRespSistemaLua.setForeground(new java.awt.Color(255, 255, 255));

        btVolta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/volta.png"))); // NOI18N
        btVolta.setBorderPainted(false);
        btVolta.setContentAreaFilled(false);
        btVolta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltaActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(658, 658, 658)
                            .addComponent(btPula)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btProx, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtMassa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(79, 79, 79)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtGravidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(194, 194, 194)
                                    .addComponent(jLabel9)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtRespSistemaLua, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtRespLua, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(68, 68, 68)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtGravidade, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(260, 260, 260)
                                                .addComponent(jLabel4)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtRespTerra, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtRespSistemaTerra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(324, 324, 324)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btCalcTerra, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btCalcLua, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(35, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(195, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btPula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btProx, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(435, 435, 435)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btCalcTerra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtRespTerra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRespSistemaTerra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtGravidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))))
                    .addGap(18, 18, 18)
                    .addComponent(jLabel5)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtMassa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(txtGravidade1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(txtRespLua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtRespSistemaLua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btCalcLua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btProxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProxActionPerformed
        if ((respostaCalculadaTerra == 0) || (respostaCalculadaLua == 0)) {
            Problema3 problema3 = new Problema3();
            problema3.setVisible(false);
        } else if ((respostaUsuarioTerra == respostaCalculadaTerra) && (respostaUsuarioLua == respostaCalculadaLua)) {
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
        } else {
            Problema3 problema3 = new Problema3();
            problema3.setVisible(false);
        }
    }//GEN-LAST:event_btProxActionPerformed

    private void btVoltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltaActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "Voltar para a questão anterior?", "Voltar", JOptionPane.YES_NO_CANCEL_OPTION);
        if (resp == 0) {
            this.dispose();
            Problema1 problema1 = new Problema1();
            problema1.setVisible(true);

            String sql = "SELECT dados FROM imagens WHERE id = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, 1); // Substitua '1' pelo ID da imagem que você deseja exibir
                rs = pst.executeQuery();
                if (rs.next()) {
                    byte[] img = rs.getBytes("dados");
                    ImageIcon imagem = new ImageIcon(img);
                    Image imgRedimensionada = imagem.getImage().getScaledInstance(lblProb1.getWidth(), lblProb1.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imagemRedimensionada = new ImageIcon(imgRedimensionada);
                    lblProb1.setIcon(imagemRedimensionada);
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

    private void btPulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPulaActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "Deseja Pular a Questão?", "Pular Questão", JOptionPane.YES_NO_CANCEL_OPTION,2);
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
            java.util.logging.Logger.getLogger(Problema2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Problema2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Problema2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Problema2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Problema2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCalcLua;
    private javax.swing.JButton btCalcTerra;
    private javax.swing.JButton btProx;
    private javax.swing.JButton btPula;
    private javax.swing.JButton btVolta;
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
    public static javax.swing.JLabel lblProb2;
    private javax.swing.JTextField txtGravidade;
    private javax.swing.JTextField txtGravidade1;
    private javax.swing.JTextField txtMassa;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtRespLua;
    private javax.swing.JLabel txtRespSistemaLua;
    private javax.swing.JLabel txtRespSistemaTerra;
    private javax.swing.JTextField txtRespTerra;
    // End of variables declaration//GEN-END:variables
}
