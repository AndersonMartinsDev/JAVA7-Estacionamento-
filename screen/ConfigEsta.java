/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.park.screen;

import br.com.park.dtbase.bdBack;
import br.com.park.job.Caixa;
import br.com.park.job.Estacionamento;
import br.com.park.job.Servicos;
import br.com.park.job.TabelaPreco;
import br.com.park.logs.Logs;
import br.com.park.tools.MinhaTabela;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ConfigEsta extends javax.swing.JPanel {

    private Estacionamento park;
    private Servicos servico;
    private bdBack banco;
    private Logs lg;

    public ConfigEsta() {
        initComponents();
        initMethods();
    }

    public void printLog(String modulo, String txt) {
        lg = new Logs();
        lg.setDescricao(txt);
        lg.setModulo(modulo);
        banco.salvaLog(lg);

    }

    public void carregaTabServico() {
        ArrayList dados = new ArrayList();
        banco = new bdBack();
        dados.clear();
        String[] colunas = new String[]{"", "ID", "Nome", "Tempo", "Unidade"};

        // Tem que preencher o arraylist do banco com o Servico e trocar aqui para o Array certo
        try {
            for (int i = 0; i < banco.getBdServico().size(); i++) {
                int id = banco.getBdServico().get(i).getId();
                String nome = banco.getBdServico().get(i).getNome();
                int tole = banco.getBdServico().get(i).getTolerancia();
                String uni = banco.getBdServico().get(i).getUnidadeMedida();
                dados.add(new Object[]{"x", id, nome, tole, uni});

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro Ao preencher" + e);
        }
        MinhaTabela Mtable = new MinhaTabela(dados, colunas);
        tb_servico1.setModel(Mtable);
        tb_servico1.getColumnModel().getColumn(0).setPreferredWidth(20);
        tb_servico1.getColumnModel().getColumn(0).setResizable(false);
        tb_servico1.getColumnModel().getColumn(1).setPreferredWidth(22);
        tb_servico1.getColumnModel().getColumn(1).setResizable(false);
        tb_servico1.getColumnModel().getColumn(2).setPreferredWidth(120);
        tb_servico1.getColumnModel().getColumn(2).setResizable(false);
        tb_servico1.getColumnModel().getColumn(3).setPreferredWidth(80);
        tb_servico1.getColumnModel().getColumn(3).setResizable(false);
        tb_servico1.getTableHeader().setReorderingAllowed(false);
        tb_servico1.setAutoResizeMode(tb_servico1.AUTO_RESIZE_OFF);
        tb_servico1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void carregaTabTBPreco() {
        banco = new bdBack();
        ArrayList dados1 = new ArrayList();
        dados1.clear();
        String[] colunas = new String[]{"", "ID", "Nome", "Tempo", "Valor R$"};

        // Tem que preencher o arraylist do banco com o Servico e trocar aqui para o Array certo
        try {
            for (int i = 0; i < banco.getBdTabPreco().size(); i++) {
                int id = banco.getBdTabPreco().get(i).getId();
                String nome = String.valueOf(banco.getBdTabPreco().get(i).getId() + 1 + " ª Hora");
                double tole = banco.getBdTabPreco().get(i).getTempo();
                double uni = banco.getBdTabPreco().get(i).getMoeda();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initMethods() {
        carregaTabServico();
        carregaTabTBPreco();
        abas_parametros.setEnabledAt(1, false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        abas_parametros = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_nomeServico = new javax.swing.JTextField();
        tf_tolerancia = new javax.swing.JTextField();
        btn_salvar = new javax.swing.JButton();
        btn_cancelarServico = new javax.swing.JButton();
        cb_unidadeServico = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_tbPreco = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        js_tempo = new javax.swing.JSpinner();
        tf_valor = new javax.swing.JTextField();
        btn_salvarTP = new javax.swing.JButton();
        btn_cancelarTP = new javax.swing.JButton();
        check_ConvenioSp = new java.awt.Checkbox();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_servico1 = new javax.swing.JTable();
        pn_ConvenioSP = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tf_nomeConvenio = new javax.swing.JTextField();
        tf_DescontoConvenio = new javax.swing.JTextField();
        btn_salvarConvenio = new javax.swing.JButton();
        btn_cancelarConvenio = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_convenio = new javax.swing.JTable();

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPanel4FocusLost(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Serviços");

        jLabel2.setText("Nome:");

        jLabel3.setText("Tempo Livre:");

        tf_nomeServico.setForeground(new java.awt.Color(201, 204, 189));
        tf_nomeServico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tf_nomeServico.setText("Título do Serviço");
        tf_nomeServico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_nomeServicoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_nomeServicoFocusLost(evt);
            }
        });

        tf_tolerancia.setForeground(new java.awt.Color(204, 204, 204));
        tf_tolerancia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tf_tolerancia.setText("Tempo de Tolerancia");
        tf_tolerancia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_toleranciaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_toleranciaFocusLost(evt);
            }
        });

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

        cb_unidadeServico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minuto", "Hora", "Dia", "Mês", "Ano" }));

        jLabel8.setText("Un:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel1)
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_nomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_tolerancia, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_cancelarServico)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_unidadeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 40, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_nomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_tolerancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_unidadeServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_salvar)
                    .addComponent(btn_cancelarServico))
                .addContainerGap(34, Short.MAX_VALUE))
        );

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
        if (tb_tbPreco.getColumnModel().getColumnCount() > 0) {
            tb_tbPreco.getColumnModel().getColumn(0).setResizable(false);
            tb_tbPreco.getColumnModel().getColumn(0).setPreferredWidth(20);
            tb_tbPreco.getColumnModel().getColumn(1).setResizable(false);
            tb_tbPreco.getColumnModel().getColumn(1).setPreferredWidth(30);
            tb_tbPreco.getColumnModel().getColumn(2).setResizable(false);
            tb_tbPreco.getColumnModel().getColumn(2).setPreferredWidth(200);
            tb_tbPreco.getColumnModel().getColumn(3).setResizable(false);
        }

        jButton3.setText("jButton3");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        label1.setText("Tempo:");

        label2.setText("Valor:");

        label3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label3.setText("Tabela de Preço");

        js_tempo.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        js_tempo.setToolTipText("");

        tf_valor.setToolTipText("");

        btn_salvarTP.setText("Adicionar");
        btn_salvarTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarTPActionPerformed(evt);
            }
        });

        btn_cancelarTP.setText("Cancelar");
        btn_cancelarTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarTPActionPerformed(evt);
            }
        });

        check_ConvenioSp.setLabel("Convênio Especial");
        check_ConvenioSp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_ConvenioSpItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(js_tempo, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                .addComponent(tf_valor))
                            .addComponent(check_ConvenioSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_salvarTP, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cancelarTP)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(js_tempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_ConvenioSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_salvarTP)
                    .addComponent(btn_cancelarTP))
                .addContainerGap())
        );

        label1.getAccessibleContext().setAccessibleName("Jlabel");

        tb_servico1.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_servico1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tb_servico1);
        if (tb_servico1.getColumnModel().getColumnCount() > 0) {
            tb_servico1.getColumnModel().getColumn(0).setResizable(false);
            tb_servico1.getColumnModel().getColumn(0).setPreferredWidth(20);
            tb_servico1.getColumnModel().getColumn(1).setResizable(false);
            tb_servico1.getColumnModel().getColumn(1).setPreferredWidth(30);
            tb_servico1.getColumnModel().getColumn(2).setResizable(false);
            tb_servico1.getColumnModel().getColumn(2).setPreferredWidth(200);
            tb_servico1.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(67, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jButton3)
                .addContainerGap())
        );

        abas_parametros.addTab("Serviços", jPanel1);

        pn_ConvenioSP.setAutoscrolls(true);

        jLabel7.setText("AQUI SERÁ o CONVENIO ESPECIAL se habilitado aparecera essa tela");

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Convênio");

        jLabel5.setText("Nome:");

        jLabel6.setText("Desconto:");

        tf_nomeConvenio.setForeground(new java.awt.Color(204, 204, 204));
        tf_nomeConvenio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tf_nomeConvenio.setText("Descrição do Convênio");
        tf_nomeConvenio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_nomeConvenioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_nomeConvenioFocusLost(evt);
            }
        });

        tf_DescontoConvenio.setForeground(new java.awt.Color(204, 204, 204));
        tf_DescontoConvenio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tf_DescontoConvenio.setText("Tolerância");
        tf_DescontoConvenio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_DescontoConvenioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_DescontoConvenioFocusLost(evt);
            }
        });

        btn_salvarConvenio.setText("Adicionar");

        btn_cancelarConvenio.setText("Cancelar");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel4))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_nomeConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_DescontoConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(btn_salvarConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cancelarConvenio)))))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tf_nomeConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tf_DescontoConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_salvarConvenio)
                    .addComponent(btn_cancelarConvenio))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        tb_convenio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "ID", "Nome", "Desconto"
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
        tb_convenio.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tb_convenio);
        if (tb_convenio.getColumnModel().getColumnCount() > 0) {
            tb_convenio.getColumnModel().getColumn(0).setResizable(false);
            tb_convenio.getColumnModel().getColumn(0).setPreferredWidth(20);
            tb_convenio.getColumnModel().getColumn(1).setResizable(false);
            tb_convenio.getColumnModel().getColumn(1).setPreferredWidth(40);
            tb_convenio.getColumnModel().getColumn(2).setResizable(false);
            tb_convenio.getColumnModel().getColumn(2).setPreferredWidth(200);
            tb_convenio.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout pn_ConvenioSPLayout = new javax.swing.GroupLayout(pn_ConvenioSP);
        pn_ConvenioSP.setLayout(pn_ConvenioSPLayout);
        pn_ConvenioSPLayout.setHorizontalGroup(
            pn_ConvenioSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ConvenioSPLayout.createSequentialGroup()
                .addGroup(pn_ConvenioSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_ConvenioSPLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel7))
                    .addGroup(pn_ConvenioSPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        pn_ConvenioSPLayout.setVerticalGroup(
            pn_ConvenioSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_ConvenioSPLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pn_ConvenioSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(77, 77, 77))
        );

        abas_parametros.addTab("Convênio Especial", pn_ConvenioSP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(abas_parametros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(abas_parametros, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        lg = new Logs();
        try {
            String nome = tf_nomeServico.getText();
            int tolerancia = Integer.valueOf(tf_tolerancia.getText());
            String unidade = String.valueOf(cb_unidadeServico.getSelectedItem());

            servico = new Servicos();
            servico.setNome(nome);
            servico.setTolerancia(tolerancia);
            servico.setUnidadeMedida(unidade);
            banco.salvaServico(servico);
            carregaTabServico();
            setTxtShow("Título do Serviço", tf_nomeServico);
            setTxtShow("Tempo de Tolerancia", tf_tolerancia);
            printLog("Serviço", "Novo serviço criado");
        } catch (Error ex) {
            printLog("Serviço", "Não foi possivel Gerar novo Serviço");
        }

        //IMplementar metodo para salvar no banco de dados
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void tf_nomeServicoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_nomeServicoFocusGained
        txtHide("", tf_nomeServico);
    }//GEN-LAST:event_tf_nomeServicoFocusGained

    private void tf_nomeServicoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_nomeServicoFocusLost
        validTxtShow("Título do Serviço", tf_nomeServico);
        habilitaBotoes(btn_salvar, tf_nomeServico, tf_tolerancia, "Título do Serviço", "Tempo de Tolerancia");
    }//GEN-LAST:event_tf_nomeServicoFocusLost

    private void tf_toleranciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_toleranciaFocusGained
        txtHide("", tf_tolerancia);
    }//GEN-LAST:event_tf_toleranciaFocusGained

    private void tf_toleranciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_toleranciaFocusLost
        validTxtShow("Tempo de Tolerancia", tf_tolerancia);
        habilitaBotoes(btn_salvar, tf_nomeServico, tf_tolerancia, "Título do Serviço", "Tempo de Tolerancia");
    }//GEN-LAST:event_tf_toleranciaFocusLost

    private void tf_nomeConvenioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_nomeConvenioFocusGained
        txtHide("", tf_nomeConvenio);
    }//GEN-LAST:event_tf_nomeConvenioFocusGained

    private void tf_nomeConvenioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_nomeConvenioFocusLost
        validTxtShow("Título do Convênio", tf_nomeConvenio);
    }//GEN-LAST:event_tf_nomeConvenioFocusLost

    private void tf_DescontoConvenioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_DescontoConvenioFocusGained
        txtHide("", tf_DescontoConvenio);
    }//GEN-LAST:event_tf_DescontoConvenioFocusGained

    private void tf_DescontoConvenioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_DescontoConvenioFocusLost
        validTxtShow("Tempo em minutos", tf_DescontoConvenio);
    }//GEN-LAST:event_tf_DescontoConvenioFocusLost

    private void jPanel4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel4FocusLost
        // TODO add your handling code here:
        tf_nomeServico.setText("Tempo de tolerancia");
    }//GEN-LAST:event_jPanel4FocusLost

    private void btn_cancelarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarServicoActionPerformed
        setTxtShow("Título do Serviço", tf_nomeServico);
        setTxtShow("Tempo de Tolerancia", tf_tolerancia);
    }//GEN-LAST:event_btn_cancelarServicoActionPerformed

    private void btn_salvarTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarTPActionPerformed

        TabelaPreco tb = new TabelaPreco();
        banco = new bdBack();
        Double tempo = Double.valueOf(js_tempo.getValue().toString());
        float valor = Float.parseFloat(tf_valor.getText());
        tb.setMoeda(valor);
        tb.setTempo(tempo);
        banco.salvaTbPreco(tb);

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

    private void check_ConvenioSpItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_check_ConvenioSpItemStateChanged
        if (check_ConvenioSp.getState()) {
            abas_parametros.setEnabledAt(1, true);
            JOptionPane.showMessageDialog(check_ConvenioSp, "Convênio Especial Habilitado");
        } else {
            System.out.println("Está sendo usado o Convenio automático");
            abas_parametros.setEnabledAt(1, false);
            JOptionPane.showConfirmDialog(check_ConvenioSp, "Deseja Realmente desabilitar?\n por enquanto vai ficar essa");
        }
    }//GEN-LAST:event_check_ConvenioSpItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane abas_parametros;
    private javax.swing.JButton btn_cancelarConvenio;
    private javax.swing.JButton btn_cancelarServico;
    private javax.swing.JButton btn_cancelarTP;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JButton btn_salvarConvenio;
    private javax.swing.JButton btn_salvarTP;
    private javax.swing.JComboBox<String> cb_unidadeServico;
    private java.awt.Checkbox check_ConvenioSp;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner js_tempo;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private javax.swing.JPanel pn_ConvenioSP;
    private javax.swing.JTable tb_convenio;
    private javax.swing.JTable tb_servico1;
    private javax.swing.JTable tb_tbPreco;
    private javax.swing.JTextField tf_DescontoConvenio;
    private javax.swing.JTextField tf_nomeConvenio;
    private javax.swing.JTextField tf_nomeServico;
    private javax.swing.JTextField tf_tolerancia;
    private javax.swing.JTextField tf_valor;
    // End of variables declaration//GEN-END:variables
}
