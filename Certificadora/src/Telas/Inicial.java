package Telas;

import Conexao.ModuloConexao;
import static Telas.Principal.ctRA;
import static Telas.Problema1.lblProb1;
import static Telas.Problema2.lblProb2;
import static Telas.Problema3.lblProb3;
import static Telas.Problema4.lblProb4;
import static Telas.Problema5.lblProb5;
import static Telas.Problema6.lblProb6;
import static Telas.Problema7.lblProb7;
import static Telas.Problema8.lblProb8;
import static Telas.Problema9.lblProb9;
import static Telas.Problema10.lblProb10;
import java.awt.Image;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author Rafa
 */
public class Inicial extends javax.swing.JFrame {

    int idUsuario = Integer.parseInt(ctRA.getText());
    JTextField txtResp1 = new JTextField();
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private static int pontuacaoAtual = 0;

    public static void atualizarPontuacao(String novaPontuacao) {
        lblPontuacao.setText(novaPontuacao);
    }

    public static int getPontuacaoAtual() {
        return pontuacaoAtual;
    }

    public void verificarRespostaEAtualizarImagem(int idUsuario, int questaoId, JLabel[] labels) {
        String sqbusca = "SELECT resposta_dada FROM Respostas WHERE usuario_ra = ? AND questao_id = ?";
        try {
            pst = conexao.prepareStatement(sqbusca);
            pst.setInt(1, idUsuario);
            pst.setInt(2, questaoId);
            rs = pst.executeQuery();

            // Verifica se há um resultado
            if (rs.next()) {
                String resposta = rs.getString("resposta_dada");
                // Verifica se a resposta não é nula e não está vazia
                if (resposta != null && !resposta.isEmpty()) {
                    // Atualiza apenas o JLabel correspondente à questão
                    if (questaoId <= labels.length) {
                        labels[questaoId - 1].setIcon(new ImageIcon(getClass().getResource("/Icones/feito.png")));
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar imagem: " + e.getMessage());
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

// Exemplo de uso para as 10 imagens
    public Inicial() {
        initComponents();
        conexao = ModuloConexao.conector();
        JLabel[] labels = {lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10};
        for (int i = 1; i <= 10; i++) {
            verificarRespostaEAtualizarImagem(idUsuario, i, labels);
        }
    }

    //função para ver se a questão ja foi respondida
    private ResultSet buscarResposta(int usuarioRa, int questaoId) throws SQLException {
        String sqbusca = "SELECT resposta_dada FROM Respostas WHERE usuario_ra = ? AND questao_id = ?";
        pst = conexao.prepareStatement(sqbusca);
        pst.setInt(1, usuarioRa);
        pst.setInt(2, questaoId);
        return pst.executeQuery();
    }

    //função para trazer a imagem de cada questão do banco de dados
    private void carregarImagem(int imagemId, JLabel label) {
        String sql = "SELECT dados FROM imagens WHERE id = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, imagemId);
            rs = pst.executeQuery();

            if (rs.next()) {
                byte[] img = rs.getBytes("dados");
                ImageIcon imagem = new ImageIcon(img);
                Image imgRedimensionada = imagem.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon imagemRedimensionada = new ImageIcon(imgRedimensionada);
                label.setIcon(imagemRedimensionada);
            } else {
                JOptionPane.showMessageDialog(null, "Imagem não encontrada.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar imagem: " + e.getMessage());
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblData = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        btSair = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblPontuacao = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        painel2 = new javax.swing.JPanel();
        btProb8 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btProb10 = new javax.swing.JButton();
        painel1 = new javax.swing.JPanel();
        btProb9 = new javax.swing.JButton();
        painel3 = new javax.swing.JPanel();
        btProb5 = new javax.swing.JButton();
        painel4 = new javax.swing.JPanel();
        btProb6 = new javax.swing.JButton();
        painel5 = new javax.swing.JPanel();
        btProb7 = new javax.swing.JButton();
        painel9 = new javax.swing.JPanel();
        btProb3 = new javax.swing.JButton();
        painel10 = new javax.swing.JPanel();
        btProb4 = new javax.swing.JButton();
        painel11 = new javax.swing.JPanel();
        btProb2 = new javax.swing.JButton();
        painel12 = new javax.swing.JPanel();
        btProb1 = new javax.swing.JButton();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl6 = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        lbl8 = new javax.swing.JLabel();
        lbl9 = new javax.swing.JLabel();
        lbl10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setBackground(new java.awt.Color(2, 115, 94));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(10, 58, 64));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Questões De Nível 1");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Questões De Nível 3");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Questões De Nível 2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(100, 100, 100)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(37, 37, 37))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 830, -1));

        jPanel2.setBackground(new java.awt.Color(4, 35, 38));

        lblData.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblData.setForeground(new java.awt.Color(255, 255, 255));
        lblData.setText("Data");

        lblUsuario.setBackground(new java.awt.Color(255, 255, 255));
        lblUsuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Usuário");

        btSair.setBackground(new java.awt.Color(29, 115, 115));
        btSair.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btSair.setForeground(new java.awt.Color(255, 255, 255));
        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/sair.png"))); // NOI18N
        btSair.setBorderPainted(false);
        btSair.setContentAreaFilled(false);
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Pontuação : ");

        lblPontuacao.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPontuacao.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblUsuario)
                        .addContainerGap(127, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPontuacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblData)
                            .addComponent(btSair))
                        .addGap(18, 18, 18))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsuario)
                .addGap(49, 49, 49)
                .addComponent(lblData)
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPontuacao, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 299, Short.MAX_VALUE)
                .addComponent(btSair)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, -1, 550));

        jPanel3.setBackground(new java.awt.Color(29, 115, 115));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        lbl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/fazer.png"))); // NOI18N

        painel2.setBackground(new java.awt.Color(4, 35, 38));

        btProb8.setBackground(new java.awt.Color(4, 35, 38));
        btProb8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btProb8.setForeground(new java.awt.Color(255, 255, 255));
        btProb8.setText("Problema 8");
        btProb8.setBorderPainted(false);
        btProb8.setContentAreaFilled(false);
        btProb8.setDefaultCapable(false);
        btProb8.setEnabled(false);
        btProb8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProb8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painel2Layout = new javax.swing.GroupLayout(painel2);
        painel2.setLayout(painel2Layout);
        painel2Layout.setHorizontalGroup(
            painel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btProb8, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        painel2Layout.setVerticalGroup(
            painel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btProb8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBackground(new java.awt.Color(4, 35, 38));

        btProb10.setBackground(new java.awt.Color(4, 35, 38));
        btProb10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btProb10.setForeground(new java.awt.Color(255, 255, 255));
        btProb10.setText("Problema 10");
        btProb10.setBorderPainted(false);
        btProb10.setContentAreaFilled(false);
        btProb10.setEnabled(false);
        btProb10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProb10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btProb10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(btProb10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        painel1.setBackground(new java.awt.Color(4, 35, 38));

        btProb9.setBackground(new java.awt.Color(4, 35, 38));
        btProb9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btProb9.setForeground(new java.awt.Color(255, 255, 255));
        btProb9.setText("Problema 9");
        btProb9.setBorderPainted(false);
        btProb9.setContentAreaFilled(false);
        btProb9.setEnabled(false);
        btProb9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProb9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painel1Layout = new javax.swing.GroupLayout(painel1);
        painel1.setLayout(painel1Layout);
        painel1Layout.setHorizontalGroup(
            painel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btProb9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        painel1Layout.setVerticalGroup(
            painel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btProb9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painel3.setBackground(new java.awt.Color(4, 35, 38));

        btProb5.setBackground(new java.awt.Color(4, 35, 38));
        btProb5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btProb5.setForeground(new java.awt.Color(255, 255, 255));
        btProb5.setText("Problema 5");
        btProb5.setBorderPainted(false);
        btProb5.setContentAreaFilled(false);
        btProb5.setEnabled(false);
        btProb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProb5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painel3Layout = new javax.swing.GroupLayout(painel3);
        painel3.setLayout(painel3Layout);
        painel3Layout.setHorizontalGroup(
            painel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btProb5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        painel3Layout.setVerticalGroup(
            painel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btProb5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painel4.setBackground(new java.awt.Color(4, 35, 38));

        btProb6.setBackground(new java.awt.Color(4, 35, 38));
        btProb6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btProb6.setForeground(new java.awt.Color(255, 255, 255));
        btProb6.setText("Problema 6");
        btProb6.setBorderPainted(false);
        btProb6.setContentAreaFilled(false);
        btProb6.setEnabled(false);
        btProb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProb6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painel4Layout = new javax.swing.GroupLayout(painel4);
        painel4.setLayout(painel4Layout);
        painel4Layout.setHorizontalGroup(
            painel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btProb6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        painel4Layout.setVerticalGroup(
            painel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btProb6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painel5.setBackground(new java.awt.Color(4, 35, 38));

        btProb7.setBackground(new java.awt.Color(4, 35, 38));
        btProb7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btProb7.setForeground(new java.awt.Color(255, 255, 255));
        btProb7.setText("Problema 7");
        btProb7.setBorderPainted(false);
        btProb7.setContentAreaFilled(false);
        btProb7.setEnabled(false);
        btProb7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProb7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painel5Layout = new javax.swing.GroupLayout(painel5);
        painel5.setLayout(painel5Layout);
        painel5Layout.setHorizontalGroup(
            painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel5Layout.createSequentialGroup()
                .addComponent(btProb7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        painel5Layout.setVerticalGroup(
            painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btProb7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painel9.setBackground(new java.awt.Color(4, 35, 38));

        btProb3.setBackground(new java.awt.Color(4, 35, 38));
        btProb3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btProb3.setForeground(new java.awt.Color(255, 255, 255));
        btProb3.setText("Problema 3");
        btProb3.setBorderPainted(false);
        btProb3.setContentAreaFilled(false);
        btProb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProb3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painel9Layout = new javax.swing.GroupLayout(painel9);
        painel9.setLayout(painel9Layout);
        painel9Layout.setHorizontalGroup(
            painel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btProb3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        painel9Layout.setVerticalGroup(
            painel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btProb3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painel10.setBackground(new java.awt.Color(4, 35, 38));

        btProb4.setBackground(new java.awt.Color(4, 35, 38));
        btProb4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btProb4.setForeground(new java.awt.Color(255, 255, 255));
        btProb4.setText("Problema 4");
        btProb4.setBorderPainted(false);
        btProb4.setContentAreaFilled(false);
        btProb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProb4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painel10Layout = new javax.swing.GroupLayout(painel10);
        painel10.setLayout(painel10Layout);
        painel10Layout.setHorizontalGroup(
            painel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btProb4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        painel10Layout.setVerticalGroup(
            painel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btProb4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painel11.setBackground(new java.awt.Color(4, 35, 38));

        btProb2.setBackground(new java.awt.Color(4, 35, 38));
        btProb2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btProb2.setForeground(new java.awt.Color(255, 255, 255));
        btProb2.setText("Problema 2");
        btProb2.setBorderPainted(false);
        btProb2.setContentAreaFilled(false);
        btProb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProb2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painel11Layout = new javax.swing.GroupLayout(painel11);
        painel11.setLayout(painel11Layout);
        painel11Layout.setHorizontalGroup(
            painel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btProb2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        painel11Layout.setVerticalGroup(
            painel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btProb2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        painel12.setBackground(new java.awt.Color(4, 35, 38));

        btProb1.setBackground(new java.awt.Color(4, 35, 38));
        btProb1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btProb1.setForeground(new java.awt.Color(255, 255, 255));
        btProb1.setText("Problema 1");
        btProb1.setBorderPainted(false);
        btProb1.setContentAreaFilled(false);
        btProb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProb1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painel12Layout = new javax.swing.GroupLayout(painel12);
        painel12.setLayout(painel12Layout);
        painel12Layout.setHorizontalGroup(
            painel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel12Layout.createSequentialGroup()
                .addComponent(btProb1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        painel12Layout.setVerticalGroup(
            painel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btProb1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        lbl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/fazer.png"))); // NOI18N

        lbl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/fazer.png"))); // NOI18N

        lbl4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/fazer.png"))); // NOI18N

        lbl5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/fazer.png"))); // NOI18N

        lbl6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/fazer.png"))); // NOI18N

        lbl7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/fazer.png"))); // NOI18N

        lbl8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/fazer.png"))); // NOI18N

        lbl9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/fazer.png"))); // NOI18N

        lbl10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/fazer.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(painel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(painel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(painel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(painel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl4)
                                    .addComponent(lbl3))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(painel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl6))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(painel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl7))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(painel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl5)))
                        .addGap(100, 100, 100)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl10))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(painel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(painel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl8)
                                    .addComponent(lbl9))))
                        .addGap(29, 29, 29))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(painel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addComponent(painel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(painel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(painel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(painel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addComponent(painel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(lbl10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(painel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addComponent(painel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(painel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(101, 101, 101))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 840, 400));

        jPanel4.setBackground(new java.awt.Color(29, 115, 115));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
    }//GEN-LAST:event_formWindowActivated

    private void btProb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProb1ActionPerformed
        try {
            rs = buscarResposta(idUsuario, 1); // Substituir '1' pelo ID da questão

            if (rs.next()) {
                String resposta = rs.getString("resposta_dada");
                if (resposta != null || !resposta.isEmpty()) {
                    int resp = JOptionPane.showConfirmDialog(null, "Você já respondeu esta questão. Deseja responder novamente?", "Confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (resp == 0) {
                        Problema1 problema1 = new Problema1();
                        problema1.setVisible(true);
                        carregarImagem(1, lblProb1);
                    }
                }
            } else {
                Problema1 problema1 = new Problema1();
                problema1.setVisible(true);
                carregarImagem(1, lblProb1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
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
    }//GEN-LAST:event_btProb1ActionPerformed

    private void btProb6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProb6ActionPerformed
        try {
            rs = buscarResposta(idUsuario, 6); // Substituir '1' pelo ID da questão

            if (rs.next()) {
                String resposta = rs.getString("resposta_dada");
                if (resposta != null || !resposta.isEmpty()) {
                    int resp = JOptionPane.showConfirmDialog(null, "Você já respondeu esta questão. Deseja responder novamente?", "Confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (resp == 0) {
                        Problema6 problema6 = new Problema6();
                        problema6.setVisible(true);
                        carregarImagem(6, lblProb6);
                    }
                }
            } else {
                Problema6 problema6 = new Problema6();
                problema6.setVisible(true);
                carregarImagem(6, lblProb6);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
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
    }//GEN-LAST:event_btProb6ActionPerformed

    private void btProb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProb2ActionPerformed
        try {
            rs = buscarResposta(idUsuario, 2); // Substituir '1' pelo ID da questão

            if (rs.next()) {
                String resposta = rs.getString("resposta_dada");
                if (resposta != null || !resposta.isEmpty()) {
                    int resp = JOptionPane.showConfirmDialog(null, "Você já respondeu esta questão. Deseja responder novamente?", "Confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (resp == 0) {
                        Problema2 problema2 = new Problema2();
                        problema2.setVisible(true);
                        carregarImagem(2, lblProb2);
                    }
                }
            } else {
                Problema2 problema2 = new Problema2();
                problema2.setVisible(true);
                carregarImagem(2, lblProb2);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
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
    }//GEN-LAST:event_btProb2ActionPerformed

    private void btProb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProb3ActionPerformed
        try {
            rs = buscarResposta(idUsuario, 3);

            if (rs.next()) {
                String resposta = rs.getString("resposta_dada");
                if (resposta != null || !resposta.isEmpty()) {
                    int resp = JOptionPane.showConfirmDialog(null, "Você já respondeu esta questão. Deseja responder novamente?", "Confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (resp == 0) {
                        Problema3 problema3 = new Problema3();
                        problema3.setVisible(true);
                        carregarImagem(3, lblProb3);
                    }
                }
            } else {
                Problema3 problema3 = new Problema3();
                problema3.setVisible(true);
                carregarImagem(3, lblProb3);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
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
    }//GEN-LAST:event_btProb3ActionPerformed

    private void btProb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProb5ActionPerformed
        try {
            rs = buscarResposta(idUsuario, 5); // Substituir '1' pelo ID da questão

            if (rs.next()) {
                String resposta = rs.getString("resposta_dada");
                if (resposta != null || !resposta.isEmpty()) {
                    int resp = JOptionPane.showConfirmDialog(null, "Você já respondeu esta questão. Deseja responder novamente?", "Confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (resp == 0) {
                        Problema5 problema5 = new Problema5();
                        problema5.setVisible(true);
                        carregarImagem(5, lblProb5); // Substituir '1' pelo ID da imagem que você deseja exibir
                    }
                }
            } else {
                Problema5 problema5 = new Problema5();
                problema5.setVisible(true);
                carregarImagem(5, lblProb5); // Substituir '1' pelo ID da imagem que você deseja exibir
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
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
    }//GEN-LAST:event_btProb5ActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        sair();
    }//GEN-LAST:event_btSairActionPerformed
    public void sair() {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja mesmo sair?", "Sair", JOptionPane.YES_NO_CANCEL_OPTION);
        if (resp == 0) {
            this.dispose();
            Principal principal = new Principal();
            principal.setVisible(true);
        }
    }
    private void btProb10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProb10ActionPerformed
        try {
            rs = buscarResposta(idUsuario, 10);

            if (rs.next()) {
                String resposta = rs.getString("resposta_dada");
                if (resposta != null || !resposta.isEmpty()) {
                    int resp = JOptionPane.showConfirmDialog(null, "Você já respondeu esta questão. Deseja responder novamente?", "Confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (resp == 0) {
                        Problema10 problema10 = new Problema10();
                        problema10.setVisible(true);
                        carregarImagem(10, lblProb10);
                    }
                }
            } else {
                Problema10 problema10 = new Problema10();
                problema10.setVisible(true);
                carregarImagem(10, lblProb10);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
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
    }//GEN-LAST:event_btProb10ActionPerformed

    private void btProb9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProb9ActionPerformed
        try {
            rs = buscarResposta(idUsuario, 9);

            if (rs.next()) {
                String resposta = rs.getString("resposta_dada");
                if (resposta != null || !resposta.isEmpty()) {
                    int resp = JOptionPane.showConfirmDialog(null, "Você já respondeu esta questão. Deseja responder novamente?", "Confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (resp == 0) {
                        Problema9 problema9 = new Problema9();
                        problema9.setVisible(true);
                        carregarImagem(9, lblProb9);
                    }
                }
            } else {
                Problema9 problema9 = new Problema9();
                problema9.setVisible(true);
                carregarImagem(9, lblProb9);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
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
    }//GEN-LAST:event_btProb9ActionPerformed

    private void btProb7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProb7ActionPerformed
        try {
            rs = buscarResposta(idUsuario, 7);

            if (rs.next()) {
                String resposta = rs.getString("resposta_dada");
                if (resposta != null || !resposta.isEmpty()) {
                    int resp = JOptionPane.showConfirmDialog(null, "Você já respondeu esta questão. Deseja responder novamente?", "Confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (resp == 0) {
                        Problema7 problema7 = new Problema7();
                        problema7.setVisible(true);
                        carregarImagem(7, lblProb7);
                    }
                }
            } else {
                Problema7 problema7 = new Problema7();
                problema7.setVisible(true);
                carregarImagem(7, lblProb7);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
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
    }//GEN-LAST:event_btProb7ActionPerformed

    private void btProb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProb4ActionPerformed
        try {
            rs = buscarResposta(idUsuario, 4);

            if (rs.next()) {
                String resposta = rs.getString("resposta_dada");
                if (resposta != null || !resposta.isEmpty()) {
                    int resp = JOptionPane.showConfirmDialog(null, "Você já respondeu esta questão. Deseja responder novamente?", "Confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (resp == 0) {
                        Problema4 problema4 = new Problema4();
                        problema4.setVisible(true);
                        carregarImagem(4, lblProb4);
                    }
                }
            } else {
                Problema4 problema4 = new Problema4();
                problema4.setVisible(true);
                carregarImagem(4, lblProb4);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
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
    }//GEN-LAST:event_btProb4ActionPerformed

    private void btProb8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProb8ActionPerformed
        try {
            rs = buscarResposta(idUsuario, 8);

            if (rs.next()) {
                String resposta = rs.getString("resposta_dada");
                if (resposta != null || !resposta.isEmpty()) {
                    int resp = JOptionPane.showConfirmDialog(null, "Você já respondeu esta questão. Deseja responder novamente?", "Confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (resp == 0) {
                        Problema8 problema8 = new Problema8();
                        problema8.setVisible(true);
                        carregarImagem(8, lblProb8);
                    }
                }
            } else {
                Problema8 problema8 = new Problema8();
                problema8.setVisible(true);
                carregarImagem(8, lblProb8);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
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
    }//GEN-LAST:event_btProb8ActionPerformed

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
            java.util.logging.Logger.getLogger(Inicial.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicial.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicial.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicial.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btProb1;
    public static javax.swing.JButton btProb10;
    public static javax.swing.JButton btProb2;
    public static javax.swing.JButton btProb3;
    public static javax.swing.JButton btProb4;
    public static javax.swing.JButton btProb5;
    public static javax.swing.JButton btProb6;
    public static javax.swing.JButton btProb7;
    public static javax.swing.JButton btProb8;
    public static javax.swing.JButton btProb9;
    private javax.swing.JButton btSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl10;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lbl9;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblPontuacao;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel painel1;
    private javax.swing.JPanel painel10;
    private javax.swing.JPanel painel11;
    private javax.swing.JPanel painel12;
    private javax.swing.JPanel painel2;
    private javax.swing.JPanel painel3;
    private javax.swing.JPanel painel4;
    private javax.swing.JPanel painel5;
    private javax.swing.JPanel painel9;
    // End of variables declaration//GEN-END:variables
}
