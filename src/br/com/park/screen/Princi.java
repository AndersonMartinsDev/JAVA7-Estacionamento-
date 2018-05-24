package br.com.park.screen;

import br.com.park.dtbase.CtrlGeral;
import br.com.park.job.Estacionamento;
import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Princi extends JFrame {

    private Estacionamento park;
    private PrincipalPage pnPage;
    private ConfigEsta pnConfigPara;

    public Princi() {
        initComponents();
        new Thread(t1).start();
        propriedades();

    }
    private final Runnable t1 = new Runnable() {
        @Override
        public void run() {
            while (true) {
                infoTela();
            }
        }
    };

    public void propriedades() {
        AreaLogo area = new AreaLogo();
        alternaJanela(area);
    }

    public void selectBtn(JLabel j, String texto) {
        j.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_" + texto + "_select.png"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        canvas1 = new java.awt.Canvas();
        jScrollPane1 = new javax.swing.JScrollPane();
        homePage = new javax.swing.JDesktopPane();
        pn_btnLateral = new javax.swing.JPanel();
        btn_ticket = new javax.swing.JLabel();
        btn_caixa = new javax.swing.JLabel();
        btn_relatorio = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_config = new javax.swing.JLabel();
        btn_desligar = new javax.swing.JLabel();
        lb_dataAtual = new java.awt.Label();
        lb_horaAtual = new java.awt.Label();
        lb_saudacao = new java.awt.Label();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" TIRE STOP SOFT                              < BOA NOITE >          JOSÉ FULANO ");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 1024, 768));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusTraversalPolicyProvider(true);
        setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        setForeground(java.awt.Color.white);
        setName("princi"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setSize(new java.awt.Dimension(1366, 768));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        homePage.setAutoscrolls(homePage.isShowing());
        homePage.setDragMode(javax.swing.JDesktopPane.OUTLINE_DRAG_MODE);
        homePage.setLayout(new javax.swing.BoxLayout(homePage, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(homePage);

        pn_btnLateral.setBackground(new java.awt.Color(102, 102, 102));
        pn_btnLateral.setBorder(new javax.swing.border.MatteBorder(null));
        pn_btnLateral.setPreferredSize(new java.awt.Dimension(150, 580));
        pn_btnLateral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_ticket.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        btn_ticket.setForeground(new java.awt.Color(153, 153, 153));
        btn_ticket.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_geral_static.png")); // NOI18N
        btn_ticket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ticketMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ticketMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ticketMousePressed(evt);
            }
        });
        pn_btnLateral.add(btn_ticket, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 180, 40));

        btn_caixa.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_caixa_static.png")); // NOI18N
        btn_caixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_caixaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_caixaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_caixaMouseExited(evt);
            }
        });
        pn_btnLateral.add(btn_caixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 180, 40));

        btn_relatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_relatorio_static.png")); // NOI18N
        btn_relatorio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_relatorioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_relatorioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_relatorioMouseExited(evt);
            }
        });
        pn_btnLateral.add(btn_relatorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 180, 40));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("JOSÉ FULANO");
        pn_btnLateral.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\Imagens de base\\icone-empresa.png")); // NOI18N
        pn_btnLateral.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 120, 110));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("EMPRESA CONTRATANTE");
        pn_btnLateral.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("OPERADOR:");
        pn_btnLateral.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        btn_config.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_config_static.png")); // NOI18N
        btn_config.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_configMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_configMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_configMousePressed(evt);
            }
        });
        pn_btnLateral.add(btn_config, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 180, 50));

        btn_desligar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_shutdown_static.png")); // NOI18N
        btn_desligar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_desligarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_desligarMouseExited(evt);
            }
        });
        pn_btnLateral.add(btn_desligar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 640, 150, 40));

        lb_dataAtual.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        lb_dataAtual.setForeground(new java.awt.Color(153, 153, 153));
        lb_dataAtual.setText("lableS");
        pn_btnLateral.add(lb_dataAtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 60, 20));

        lb_horaAtual.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        lb_horaAtual.setForeground(new java.awt.Color(153, 153, 153));
        lb_horaAtual.setText("lableS");
        pn_btnLateral.add(lb_horaAtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 508, 66, 20));

        lb_saudacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_saudacao.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        lb_saudacao.setForeground(new java.awt.Color(153, 153, 153));
        lb_saudacao.setText("Saudação");
        pn_btnLateral.add(lb_saudacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, -1, 10));
        lb_saudacao.getAccessibleContext().setAccessibleName("Saudacao");

        jButton1.setText("Carregar tudo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pn_btnLateral.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_btnLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pn_btnLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleParent(this);

        setSize(new java.awt.Dimension(1073, 726));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public final void infoTela() {

        DateFormat dataF = DateFormat.getDateInstance();
        DateFormat dataH = DateFormat.getTimeInstance();
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        lb_horaAtual.setText(String.valueOf(dataF.format(date)));
        lb_dataAtual.setText(String.valueOf(dataH.format(date)));
        if (hora > 6 && hora < 12) {
            lb_saudacao.setText("Bom dia!");
        } else if (hora > 12 && hora < 18) {
            lb_saudacao.setText("Boa Tarde!");
        } else {
            lb_saudacao.setText("Boa Noite!");
        }
    }

    public void alternaJanela(JPanel J) {
        homePage.removeAll();
        J.setAlignmentX(CENTER_ALIGNMENT);
        J.setAlignmentY(TOP_ALIGNMENT);
        J.setVisible(true);
        homePage.add(J);
    }

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost

    }//GEN-LAST:event_formFocusLost

    private void btn_ticketMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ticketMouseExited
        btn_ticket.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_geral_static.png"));
    }//GEN-LAST:event_btn_ticketMouseExited

    private void btn_ticketMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ticketMouseEntered
        btn_ticket.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_geral_focus.png"));
    }//GEN-LAST:event_btn_ticketMouseEntered

    private void btn_caixaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_caixaMouseEntered
        btn_caixa.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_caixa_focus.png"));
    }//GEN-LAST:event_btn_caixaMouseEntered

    private void btn_caixaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_caixaMouseExited
        btn_caixa.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_caixa_static.png"));
    }//GEN-LAST:event_btn_caixaMouseExited

    private void btn_relatorioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_relatorioMouseEntered
        btn_relatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_relatorio_focus.png"));
    }//GEN-LAST:event_btn_relatorioMouseEntered

    private void btn_relatorioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_relatorioMouseExited
        btn_relatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_relatorio_static.png"));
    }//GEN-LAST:event_btn_relatorioMouseExited

    private void btn_configMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_configMouseEntered
        btn_config.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_config_focus.png"));
    }//GEN-LAST:event_btn_configMouseEntered

    private void btn_configMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_configMouseExited
        btn_config.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_config_static.png"));
    }//GEN-LAST:event_btn_configMouseExited

    private void btn_desligarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_desligarMouseEntered
        btn_desligar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_shutdown_focus.png"));
    }//GEN-LAST:event_btn_desligarMouseEntered

    private void btn_desligarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_desligarMouseExited
        btn_desligar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_shutdown_static.png"));
    }//GEN-LAST:event_btn_desligarMouseExited

    private void btn_ticketMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ticketMousePressed
        PrincipalPage pag = new PrincipalPage();
        alternaJanela(pag);
    }//GEN-LAST:event_btn_ticketMousePressed

    private void btn_configMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_configMousePressed
        Configuracoes cfg = new Configuracoes();
        alternaJanela(cfg);
    }//GEN-LAST:event_btn_configMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       CtrlGeral ctrl = new CtrlGeral();
       ctrl.loadBd();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_caixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_caixaMouseClicked
        telaCaixa tc = new telaCaixa();
        alternaJanela(tc);
    }//GEN-LAST:event_btn_caixaMouseClicked

    private void btn_relatorioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_relatorioMouseClicked
       Relatorios re = new Relatorios();
        alternaJanela(re);
    }//GEN-LAST:event_btn_relatorioMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Princi().setVisible(true);
            }
        });
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_caixa;
    private javax.swing.JLabel btn_config;
    private javax.swing.JLabel btn_desligar;
    private javax.swing.JLabel btn_relatorio;
    private javax.swing.JLabel btn_ticket;
    private java.awt.Canvas canvas1;
    private javax.swing.JDesktopPane homePage;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label lb_dataAtual;
    private java.awt.Label lb_horaAtual;
    private java.awt.Label lb_saudacao;
    private javax.swing.JPanel pn_btnLateral;
    // End of variables declaration//GEN-END:variables

}
