/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.park.screen;

import br.com.park.dtbase.ServicoDAO;
import br.com.park.dtbase.TBdao;
import br.com.park.dtbase.bdBack;
import br.com.park.job.Caixa;
import br.com.park.job.Servicos;
import br.com.park.job.TabelaPreco;
import br.com.park.tools.MinhaTabela;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Programador
 */
public class Configuracoes extends javax.swing.JPanel {

    private Servicos servico;
    private final ServicoDAO bancoServico = new ServicoDAO();
    private bdBack banco;
    TBdao bancoDAO = new TBdao();

    /**
     * Creates new form Configuracoes
     */
    public Configuracoes() {
        initComponents();
        initMethods();
    }
     public int conversorMinuto(JComboBox cb, int tempo) {

        if (cb.getSelectedItem().equals("Hora")) {
            int horas = 0;
            horas = tempo * 60;
            tempo = horas;
        } else if (cb.getSelectedItem().equals("Dia")) {
            int horas = 0;
            horas = tempo * 1440;
            tempo = horas;
        } else if (cb.getSelectedItem().equals("Mês")) {
            int horas = 0;
            horas = tempo * 43800;
            tempo = horas;
        } else if (cb.getSelectedItem().equals("Ano")) {
            int horas = 0;
            horas = tempo * 525600;
            tempo = horas;
        }
        return tempo;
    }
      public void carregaTabTBPreco() {
        
        ArrayList dados1 = new ArrayList();
        dados1.clear();
        try {
            bancoDAO.bdtoArray();
        } catch (SQLException ex) {
            Logger.getLogger(ConfigEsta.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] colunas = new String[]{"", "ID", "Nome", "Tempo", "Valor R$"};

        // Tem que preencher o arraylist do banco com o Servico e trocar aqui para o Array certo
        try {
            for (TabelaPreco tb : bancoDAO.bdtoArray()) {
                int id = tb.getId();
                String nome = String.valueOf(tb.getId() + 1 + " ª Hora");
                double tole = tb.getTempo();
                double uni = tb.getMoeda();
                dados1.add(new Object[]{"x", id, nome, tole, uni});

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro Ao preencher" + e);
        }
        MinhaTabela Mtable = new MinhaTabela(dados1, colunas);
        tb_tbPreco.setModel(Mtable);
        tb_tbPreco.getColumnModel().getColumn(0).setPreferredWidth(20);
        tb_tbPreco.getColumnModel().getColumn(0).setResizable(false);
        tb_tbPreco.getColumnModel().getColumn(1).setPreferredWidth(22);
        tb_tbPreco.getColumnModel().getColumn(1).setResizable(false);
        tb_tbPreco.getColumnModel().getColumn(2).setPreferredWidth(120);
        tb_tbPreco.getColumnModel().getColumn(2).setResizable(false);
        tb_tbPreco.getColumnModel().getColumn(3).setPreferredWidth(80);
        tb_tbPreco.getColumnModel().getColumn(3).setResizable(false);
        tb_tbPreco.getTableHeader().setReorderingAllowed(false);
        tb_tbPreco.setAutoResizeMode(tb_servico1.AUTO_RESIZE_OFF);
        tb_tbPreco.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public void carregaTabServico() {
        Boolean jC = false;
        ArrayList dados = new ArrayList();
        dados.clear();
        String[] colunas = new String[]{"", "ID", "Nome", "Tempo", "Unidade"};

        // Tem que preencher o arraylist do banco com o Servico e trocar aqui para o Array certo
        try {
            for (Servicos s : bancoServico.writeService()) {
                int id = s.getId();
                String nome = s.getNome();
                int tempo = s.getTolerancia();
                String unMedida = s.getUnidadeMedida();
                dados.add(new Object[]{jC, id, nome, tempo, unMedida});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro Ao preencher" + e);
        }
        MinhaTabela Mtable = new MinhaTabela(dados, colunas);
     
        tb_servico1.setModel(Mtable);
        tb_servico1.getColumnModel().getColumn(0).setPreferredWidth(40);
        tb_servico1.getColumnModel().getColumn(0).setResizable(false);
        tb_servico1.getColumnModel().getColumn(1).setPreferredWidth(35);
        tb_servico1.getColumnModel().getColumn(1).setResizable(false);
        tb_servico1.getColumnModel().getColumn(2).setPreferredWidth(230);
        tb_servico1.getColumnModel().getColumn(2).setResizable(false);
        tb_servico1.getColumnModel().getColumn(3).setPreferredWidth(70);
        tb_servico1.getColumnModel().getColumn(3).setResizable(false);
        tb_servico1.getColumnModel().getColumn(4).setPreferredWidth(160);
        tb_servico1.getColumnModel().getColumn(4).setResizable(false);
        tb_servico1.getColumnModel().getColumn(4).setCellEditor(null);
        tb_servico1.getTableHeader().setReorderingAllowed(false);
        tb_servico1.setAutoResizeMode(tb_servico1.AUTO_RESIZE_OFF);
        tb_servico1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
     
        
    }

    public void txtHide(String texto, JTextField lb_texto) {
        lb_texto.setText(texto);
        lb_texto.setForeground(Color.black);
    }

    public void setTxtShow(String texto, JTextField lb_texto) {
        lb_texto.setText(texto);
        lb_texto.setForeground(Color.getHSBColor(70, 7, 80));
    }

    public void validTxtShow(String texto, JTextField lb_texto) {
        if (lb_texto.getText().trim().equals("")) {
            lb_texto.setText(texto);
            lb_texto.setForeground(Color.getHSBColor(70, 7, 80));
        }
    }
     public void ppCb() {
        cb_unidadeServico.addItem("");
        cb_unidadeServico.addItem("Minuto");
        cb_unidadeServico.addItem("Hora");
        cb_unidadeServico.addItem("Dia");
        cb_unidadeServico.addItem("Mês");
        cb_unidadeServico.addItem("Ano");

    }
       public void txtErrorShow(String texto, JTextField lb_texto) {
        lb_texto.setText(texto);
        lb_texto.setForeground(Color.red);
    }

    public void habilitaBotoes(JButton botao, JTextField j1, JTextField j2, String txt1, String txt2) {
        String nomeServico = j1.getText().trim();
        String tempoTolerancia = j2.getText().trim();
        if (!(nomeServico.equals(txt1)) || !(tempoTolerancia.equals(txt2))) {
            botao.setEnabled(true);
        } else {
            botao.setEnabled(false);
        }
    }
     private void initMethods() {
        ppCb();
        carregaTabServico();
        carregaTabTBPreco();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jtb_parametros = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pn_serv = new javax.swing.JPanel();
        l1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cb_unidadeServico = new javax.swing.JComboBox<>();
        tf_nomeServico = new javax.swing.JTextField();
        tf_tolerancia = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_servico1 = new javax.swing.JTable();
        btn_salvar = new javax.swing.JButton();
        btn_cancelarServico = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        js_tempo = new javax.swing.JSpinner();
        tf_valor = new javax.swing.JTextField();
        btn_salvarTP = new javax.swing.JButton();
        btn_cancelarTP = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_tbPreco = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        jtb_parametros.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(null);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );

        jtb_parametros.addTab("Padrão de Usuários", jPanel5);

        jPanel1.setBackground(java.awt.SystemColor.window);

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(null);

        jLabel1.setText("Servico Padrão:");

        jLabel2.setText("Pgto Pós Validação:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(553, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(460, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Gerais", jPanel6);

        pn_serv.setBackground(new java.awt.Color(255, 255, 255));
        pn_serv.setBorder(null);
        pn_serv.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pn_servFocusLost(evt);
            }
        });

        l1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        l1.setForeground(new java.awt.Color(102, 102, 102));
        l1.setText("Nome:");

        jLabel3.setText("Tempo Livre:");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l1, org.jdesktop.beansbinding.ELProperty.create("${font}"), jLabel3, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l1, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), jLabel3, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        jLabel8.setText("Unidade:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l1, org.jdesktop.beansbinding.ELProperty.create("${font}"), jLabel8, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l1, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), jLabel8, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        cb_unidadeServico.setBackground(new java.awt.Color(255, 255, 255));
        cb_unidadeServico.setForeground(new java.awt.Color(153, 153, 153));
        cb_unidadeServico.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tf_nomeServico.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tf_nomeServico.setForeground(new java.awt.Color(102, 102, 102));
        tf_nomeServico.setBorder(null);
        tf_nomeServico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_nomeServicoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_nomeServicoFocusLost(evt);
            }
        });

        tf_tolerancia.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tf_tolerancia.setForeground(new java.awt.Color(102, 102, 102));
        tf_tolerancia.setBorder(null);
        tf_tolerancia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_toleranciaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_toleranciaFocusLost(evt);
            }
        });

        tb_servico1.setForeground(new java.awt.Color(102, 102, 102));
        tb_servico1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "ID", "Nome", "Tempo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_servico1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tb_servico1);

        btn_salvar.setText("Adicionar");
        btn_salvar.setEnabled(false);
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        btn_cancelarServico.setText("Cancelar");
        btn_cancelarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarServicoActionPerformed(evt);
            }
        });

        jCheckBox2.setText("jCheckBox2");

        javax.swing.GroupLayout pn_servLayout = new javax.swing.GroupLayout(pn_serv);
        pn_serv.setLayout(pn_servLayout);
        pn_servLayout.setHorizontalGroup(
            pn_servLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_servLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pn_servLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_servLayout.createSequentialGroup()
                        .addGroup(pn_servLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBox2)
                            .addGroup(pn_servLayout.createSequentialGroup()
                                .addGroup(pn_servLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_servLayout.createSequentialGroup()
                                        .addComponent(l1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tf_nomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel3))
                                    .addGroup(pn_servLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addGroup(pn_servLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pn_servLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(btn_cancelarServico)
                                                .addGap(18, 18, 18)
                                                .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(4, 4, 4)
                                .addGroup(pn_servLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator2)
                                    .addComponent(tf_tolerancia, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_unidadeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        pn_servLayout.setVerticalGroup(
            pn_servLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_servLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_servLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(cb_unidadeServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_nomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_tolerancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pn_servLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_servLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_servLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancelarServico)
                    .addComponent(btn_salvar))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Serviços", pn_serv);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(null);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(null);

        label1.setText("Tempo:");

        label2.setText("Valor:");

        js_tempo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        js_tempo.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        js_tempo.setToolTipText("");
        js_tempo.setBorder(null);

        tf_valor.setToolTipText("");
        tf_valor.setBorder(null);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(js_tempo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(tf_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(js_tempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 630, 50));

        btn_salvarTP.setText("Adicionar");
        btn_salvarTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarTPActionPerformed(evt);
            }
        });
        jPanel2.add(btn_salvarTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, 92, -1));

        btn_cancelarTP.setText("Cancelar");
        btn_cancelarTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarTPActionPerformed(evt);
            }
        });
        jPanel2.add(btn_cancelarTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, -1, -1));

        tb_tbPreco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "ID", "Nome", "Tempo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_tbPreco.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tb_tbPreco);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 570, 200));

        jTabbedPane2.addTab("Tabela de Preço", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(null);

        jCheckBox1.setText("Habilitar descontos automáticos.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jCheckBox1)
                .addContainerGap(483, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Descontos", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jtb_parametros.addTab("Paramêtros", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jtb_parametros, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jtb_parametros)
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void pn_servFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pn_servFocusLost
        // TODO add your handling code here:
        tf_nomeServico.setText("Tempo de tolerancia");
    }//GEN-LAST:event_pn_servFocusLost

    private void btn_cancelarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarServicoActionPerformed
        setTxtShow("Título do Serviço", tf_nomeServico);
        setTxtShow("Tempo de Tolerancia", tf_tolerancia);
    }//GEN-LAST:event_btn_cancelarServicoActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed

        try {
            String nome = tf_nomeServico.getText();
            int tolerancia = Integer.valueOf(tf_tolerancia.getText());
            String unidade = String.valueOf(cb_unidadeServico.getSelectedItem());
            servico = new Servicos();
            servico.setNome(nome);
            servico.setTolerancia(tolerancia);
            servico.setUnidadeMedida(unidade);
            bancoServico.salvaServico(servico);
            carregaTabServico();
            setTxtShow("Título do Serviço", tf_nomeServico);
            setTxtShow("Tempo de Tolerancia", tf_tolerancia);

            JOptionPane.showMessageDialog(btn_salvar, "Serviço Criado!");

        } catch (Error ex) {
            // printLog("Serviço", "Não foi possivel Gerar novo Serviço");
        }

        //IMplementar metodo para salvar no banco de dados
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void tf_toleranciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_toleranciaFocusLost
        validTxtShow("Título do Serviço", tf_nomeServico);
        habilitaBotoes(btn_salvar, tf_nomeServico, tf_tolerancia, "Título do Serviço", "Tempo de Tolerancia");
    }//GEN-LAST:event_tf_toleranciaFocusLost

    private void tf_toleranciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_toleranciaFocusGained
        txtHide("", tf_tolerancia);
    }//GEN-LAST:event_tf_toleranciaFocusGained

    private void tf_nomeServicoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_nomeServicoFocusLost
        validTxtShow("Título do Serviço", tf_nomeServico);
        habilitaBotoes(btn_salvar, tf_nomeServico, tf_tolerancia, "Título do Serviço", "Tempo de Tolerancia");
    }//GEN-LAST:event_tf_nomeServicoFocusLost

    private void tf_nomeServicoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_nomeServicoFocusGained
        txtHide("", tf_nomeServico);
    }//GEN-LAST:event_tf_nomeServicoFocusGained

    private void btn_salvarTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarTPActionPerformed

        TabelaPreco tb = new TabelaPreco();
        banco = new bdBack();
        Double tempo = Double.valueOf(js_tempo.getValue().toString());
        float valor = Float.parseFloat(tf_valor.getText());
        tb.setMoeda(valor);
        tb.setTempo(tempo);
        try {
            bancoDAO.create(tb);
        } catch (SQLException ex) {
            Logger.getLogger(ConfigEsta.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*-- AQUI TERÁ que ser MUDADO para da a opção de Fazer o Convenio Especial
        Caso seja habilitado.
        */
        Caixa convenio = new Caixa();
        convenio.setConvenioNome(convenio.getId() + "ª Hora");
        convenio.setDesconto(Integer.valueOf(tf_valor.getText()));
        // pode ser feita uma intervenção por aqui
        banco.salvaConvenio(convenio);
        carregaTabTBPreco();
    }//GEN-LAST:event_btn_salvarTPActionPerformed

    private void btn_cancelarTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarTPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelarTPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelarServico;
    private javax.swing.JButton btn_cancelarTP;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JButton btn_salvarTP;
    private javax.swing.JComboBox<String> cb_unidadeServico;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JSpinner js_tempo;
    private javax.swing.JTabbedPane jtb_parametros;
    private javax.swing.JLabel l1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private javax.swing.JPanel pn_serv;
    private javax.swing.JTable tb_servico1;
    private javax.swing.JTable tb_tbPreco;
    private javax.swing.JTextField tf_nomeServico;
    private javax.swing.JTextField tf_tolerancia;
    private javax.swing.JTextField tf_valor;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
