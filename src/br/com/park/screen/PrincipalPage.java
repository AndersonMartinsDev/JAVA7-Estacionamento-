/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.park.screen;

import br.com.park.dtbase.ConnectionFactory;
import br.com.park.dtbase.CtrlGeral;
import br.com.park.dtbase.ServicoDAO;
import br.com.park.dtbase.TicketDAO;
import br.com.park.dtbase.bdBack;
import br.com.park.job.Caixa;
import br.com.park.job.Servicos;
import br.com.park.job.Ticket;
import br.com.park.tools.MinhaTabela;
import com.sun.glass.events.KeyEvent;
import static java.awt.Component.RIGHT_ALIGNMENT;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Anderson Martins
 * @data 24/03/2018
 *
 */
public class PrincipalPage extends JPanel {

    private bdBack banco1 = new bdBack();
    TicketDAO bancoTicket = new TicketDAO();
    private ServicoDAO bancoS = new ServicoDAO();
    private Caixa cx = new Caixa();
    CtrlGeral ctrlConfig = new CtrlGeral();

    public PrincipalPage() {
        initComponents();
        initMethods();
        try {
            bancoS.writeService();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(btn_Gerar, "Erro ao encontrar Serviço");
        }
    }

    public void print(String x) {
        JasperPrint jasperPrint = null;
        try {
            Connection con = ConnectionFactory.getConnection();
            String src = "C:\\Users\\Programador\\Documents\\ireport\\"+x+".jasper";

            jasperPrint = JasperFillManager.fillReport(src, null, con);
        } catch (JRException ex) {
            System.out.println("Erro" + "   " + ex);
            Logger.getLogger(PrincipalPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperViewer view = new JasperViewer(jasperPrint, false);
        view.setVisible(true);
    }

    public int qualServico(String nomeServ) {
        int tempoTolerancia = 0;
        for (int i = 0; i < bancoS.getBdServico().size(); i++) {
            if (nomeServ.equalsIgnoreCase(bancoS.getBdServico().get(i).getNome())) {
                tempoTolerancia = bancoS.getBdServico().get(i).getTolerancia();
            }
        }
        return tempoTolerancia;
    }
    //CALMA ANALISAR ISSO

    public int qualServicoNome() {
        int idx = 0;
        ArrayList<Servicos> servicoArray = new ArrayList();
        for (int i = 0; i < bancoS.getBdServico().size(); i++) {
            if (cd_servTrafego.getSelectedItem().toString().
                    equalsIgnoreCase(bancoS.getBdServico().get(i).getNome())) {
                idx = i;
                System.out.print(i);
            }
        }

        return bancoS.getBdServico().get(idx).getId();
    }

    public String converteData(String dateStr) {
        Locale loc = new Locale("pr", "BR");
        DateFormat readFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy", loc);
        DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = null;
        try {
            date = readFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = "";
        if (date != null) {
            formattedDate = writeFormat.format(date);
        }
        return formattedDate;

    }

    protected void setFilterInJTable() throws ParseException {

        String busca = "",
                servico = "",
                dataE = "",
                dataS = "",
                um = "", dois = "";
        if (!cd_servTrafego.getSelectedItem().toString().trim().equalsIgnoreCase("Todos")) {
            servico = "servico =" + "'" + cd_servTrafego.getSelectedItem().toString() + "'";
        }
        if (!jformat_dataSaida.getValue().toString().equals("00/00/0000")) {
            String sim = converteData(jformat_dataSaida.getValue().toString());
            dataS = "dataSaida < " + "'" + sim + "'";
            System.out.println(dataE);
        }
        if (!jformat_dataEntrada.getValue().toString().equals("00/00/0000")) {
            String sim = converteData(jformat_dataEntrada.getValue().toString());
            dataE = "dataEntrada > " + "'" + sim + "'";
        }

        if (!servico.equals("")) {
            if (!dataE.equals("")) {
                um = " AND ";
            }
        }
        if (!dataE.equals("")) {
            if (!dataS.equals("")) {
                dois = " AND ";
            }
        }
        busca = "where " + servico + um + dataE + dois + dataS;
        System.out.println(busca);
        try {
            popTabela(busca);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(tb_regCod, "Nenhuma Busca Encontrada!\n" + busca);
        }

    }

    public void popTabela(String busca) {
        DateFormat data = DateFormat.getDateInstance();
        DateFormat hora = DateFormat.getTimeInstance();
        ArrayList dados = new ArrayList();

        String[] colunas = new String[]{"ID", "Código", "Data Entrada",
            "Hora Entrada", "Data Saida", "Hora Saida",
            "Serviço", "Tempo", "Unidade"};
        try {

            for (Ticket t : bancoTicket.queryFilter(busca)) {
                int id = t.getId(),
                        codigo = (int) t.getCodigo();
                String dataEntrada = data.format(t.getEntrada()),
                        horaEntrada = hora.format(t.getEntrada()),
                        dataSaida = data.format(t.getSaida()),
                        horaSaida = hora.format(t.getSaida()),
                        servico = t.getServ();

                dados.add(new Object[]{id, codigo, dataEntrada, horaEntrada, dataSaida, horaSaida, servico, "", "Unidade"});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(btn_Gerar, "Erro Ao preencher" + e);
        }

        MinhaTabela Mtable = new MinhaTabela(dados, colunas);
        tb_regCod.setModel(Mtable);
        tb_regCod.getColumnModel().getColumn(0).setPreferredWidth(30);
        tb_regCod.getColumnModel().getColumn(0).setResizable(false);
        tb_regCod.getColumnModel().getColumn(1).setPreferredWidth(110);
        tb_regCod.getColumnModel().getColumn(1).setResizable(false);
        tb_regCod.getColumnModel().getColumn(2).setPreferredWidth(90);
        tb_regCod.getColumnModel().getColumn(2).setResizable(false);
        tb_regCod.getColumnModel().getColumn(3).setPreferredWidth(90);
        tb_regCod.getColumnModel().getColumn(3).setResizable(false);
        tb_regCod.getColumnModel().getColumn(4).setPreferredWidth(80);
        tb_regCod.getColumnModel().getColumn(4).setResizable(false);
        tb_regCod.getColumnModel().getColumn(5).setPreferredWidth(80);
        tb_regCod.getColumnModel().getColumn(5).setResizable(false);
        tb_regCod.getColumnModel().getColumn(6).setPreferredWidth(80);
        tb_regCod.getColumnModel().getColumn(6).setResizable(false);
        tb_regCod.getColumnModel().getColumn(7).setPreferredWidth(77);
        tb_regCod.getColumnModel().getColumn(7).setResizable(false);
        tb_regCod.getColumnModel().getColumn(8).setPreferredWidth(80);
        tb_regCod.getColumnModel().getColumn(8).setResizable(false);

        tb_regCod.getTableHeader().setReorderingAllowed(false);
        tb_regCod.setAutoResizeMode(tb_regCod.AUTO_RESIZE_OFF);
        tb_regCod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tb_regCod.setAlignmentY(RIGHT_ALIGNMENT);

    }

    public void limpaTela() {
        lb_dataEntrada.setText("");
        lb_HoraEntrada.setText("");
        lb_dataSaida.setText("");
        lb_horaSaida.setText("");
        lb_codigo.setText("");
        lb_tolerancia.setText("");
    }

    public void geraTicket() {
        
        DateFormat forma = DateFormat.getDateInstance();
        DateFormat forma1 = DateFormat.getTimeInstance();
        String servicoNome = cb_serv.getSelectedItem().toString();
        Ticket passe = new Ticket().gerarTicket(servicoNome, qualServico(servicoNome));
        lb_codigo.setText(String.valueOf(passe.getCodigo()));
        lb_dataEntrada.setText(String.valueOf(forma.format(passe.getEntrada())));
        lb_HoraEntrada.setText(String.valueOf(forma1.format(passe.getEntrada())));
        lb_dataSaida.setText(String.valueOf(forma.format(passe.getSaida())));
        lb_horaSaida.setText(String.valueOf(forma1.format(passe.getSaida())));
        lb_tolerancia.setText(String.valueOf(qualServico(servicoNome)));
        JOptionPane.showMessageDialog(btn_Gerar, "Salvo com Sucesso!");
        try {
            bancoTicket.create(passe);
            print("ticket_vert");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(btn_clear, "Erro ao gerar ticket");

        }
    }

    public void telaCobranca(Ticket passe) {
        DateFormat padrao = DateFormat.getDateInstance();
        DateFormat padrao2 = DateFormat.getDateTimeInstance();
        lb_codigoConsulta.setText(String.valueOf((int) passe.getCodigo()));
        lb_validade.setText(String.valueOf(padrao2.format(passe.getSaida())));
        lb_cobrar.setText("R$ " + String.valueOf(passe.getValor())+"0");
        lb_status.setText(passe.buscaStatus(lb_status));
    }

    @SuppressWarnings("unchecked")
    private void initMethods() {//Usado para iniciar as coisas
        jPanel3.setVisible(false);
        cb_serv.removeAllItems();
        cd_servTrafego.removeAllItems();
        cd_servTrafego.addItem("Todos");
        bancoS.popComboServ(cb_serv);
        bancoS.popComboServ(cd_servTrafego);
        banco1.popCombConvenio(cb_convenio);

        popTabela("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jt_geral = new javax.swing.JTabbedPane();
        jt_validacao = new javax.swing.JPanel();
        pn_consulta = new javax.swing.JPanel();
        l5 = new javax.swing.JLabel();
        l6 = new javax.swing.JLabel();
        lb_validade = new javax.swing.JLabel();
        lb_status = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lb_cobrar = new javax.swing.JLabel();
        lb_txCódigo = new javax.swing.JLabel();
        lb_codigoConsulta = new javax.swing.JLabel();
        tx_cod = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        cb_convenio = new javax.swing.JComboBox<>();
        l4 = new javax.swing.JLabel();
        l3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        l_hora = new java.awt.Label();
        l_data = new java.awt.Label();
        l_horaS = new java.awt.Label();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        l_dataS = new java.awt.Label();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        l_t = new java.awt.Label();
        lb_tolerancia = new java.awt.Label();
        l_c = new java.awt.Label();
        lb_codigo = new java.awt.Label();
        l1 = new java.awt.Label();
        btn_clear = new java.awt.Button();
        btn_Gerar = new java.awt.Button();
        cb_serv = new javax.swing.JComboBox<>();
        lb_horaSaida = new javax.swing.JLabel();
        lb_dataSaida = new javax.swing.JLabel();
        lb_HoraEntrada = new javax.swing.JLabel();
        lb_dataEntrada = new javax.swing.JLabel();
        jt_trafego = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_regCod = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jl_servico = new javax.swing.JLabel();
        cd_servTrafego = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jformat_dataEntrada = new javax.swing.JSpinner();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jformat_dataSaida = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        cd_Status = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        pn_botoes = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btn_newTicket = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setForeground(new java.awt.Color(102, 102, 102));

        jt_geral.setBackground(new java.awt.Color(255, 255, 255));
        jt_geral.setBorder(null);
        jt_geral.setForeground(new java.awt.Color(255, 255, 255));
        jt_geral.setAutoscrolls(true);
        jt_geral.setFocusable(false);

        jt_validacao.setBackground(new java.awt.Color(255, 255, 255));
        jt_validacao.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pn_consulta.setBackground(new java.awt.Color(255, 255, 255));
        pn_consulta.setBorder(null);

        l5.setText("Validade:");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l3, org.jdesktop.beansbinding.ELProperty.create("${font}"), l5, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l3, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), l5, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        l6.setText("Status:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l3, org.jdesktop.beansbinding.ELProperty.create("${font}"), l6, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l3, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), l6, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        jLabel5.setText("Valor:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l3, org.jdesktop.beansbinding.ELProperty.create("${font}"), jLabel5, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l3, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), jLabel5, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        lb_cobrar.setBackground(new java.awt.Color(255, 255, 255));
        lb_cobrar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lb_cobrar.setForeground(new java.awt.Color(102, 102, 102));

        lb_txCódigo.setText("Código:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l3, org.jdesktop.beansbinding.ELProperty.create("${font}"), lb_txCódigo, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l3, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), lb_txCódigo, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        tx_cod.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        tx_cod.setForeground(new java.awt.Color(153, 153, 153));
        tx_cod.setBorder(null);
        tx_cod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tx_codKeyPressed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));

        cb_convenio.setBackground(new java.awt.Color(255, 255, 255));
        cb_convenio.setBorder(null);
        cb_convenio.setFocusable(false);
        cb_convenio.setLightWeightPopupEnabled(false);

        l4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        l4.setForeground(new java.awt.Color(102, 102, 102));
        l4.setText("Desconto:");

        l3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        l3.setForeground(new java.awt.Color(102, 102, 102));
        l3.setText("Código:");

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\t_pay_static.png")); // NOI18N
        jButton1.setText("Pagar");

        javax.swing.GroupLayout pn_consultaLayout = new javax.swing.GroupLayout(pn_consulta);
        pn_consulta.setLayout(pn_consultaLayout);
        pn_consultaLayout.setHorizontalGroup(
            pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_consultaLayout.createSequentialGroup()
                .addGroup(pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_consultaLayout.createSequentialGroup()
                        .addComponent(lb_txCódigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_codigoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pn_consultaLayout.createSequentialGroup()
                            .addComponent(l5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lb_validade, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_consultaLayout.createSequentialGroup()
                            .addComponent(l6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_consultaLayout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(74, 74, 74))
                                .addGroup(pn_consultaLayout.createSequentialGroup()
                                    .addGroup(pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pn_consultaLayout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(26, 26, 26)
                                            .addComponent(lb_cobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lb_status, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addGap(45, 45, 45))
            .addGroup(pn_consultaLayout.createSequentialGroup()
                .addGroup(pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_consultaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(l3)
                        .addGap(18, 18, 18)
                        .addComponent(tx_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_consultaLayout.createSequentialGroup()
                        .addComponent(l4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_convenio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_consultaLayout.setVerticalGroup(
            pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_consultaLayout.createSequentialGroup()
                .addGroup(pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_consultaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tx_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_consultaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(l3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_consultaLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_convenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_txCódigo, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(lb_codigoConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(l5)
                    .addComponent(lb_validade, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l6)
                    .addComponent(lb_status, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(lb_cobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(53, 53, 53))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new javax.swing.OverlayLayout(jPanel2));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(null);
        jPanel3.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        l_hora.setText("Hora:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l_c, org.jdesktop.beansbinding.ELProperty.create("${font}"), l_hora, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l_c, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), l_hora, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        jPanel3.add(l_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 33, -1));

        l_data.setText("Data:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l_c, org.jdesktop.beansbinding.ELProperty.create("${font}"), l_data, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l_c, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), l_data, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        jPanel3.add(l_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, -1));

        l_horaS.setText("Hora:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l_c, org.jdesktop.beansbinding.ELProperty.create("${font}"), l_horaS, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l_c, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), l_horaS, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        jPanel3.add(l_horaS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 33, -1));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Saida");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, 20));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 170, 20));

        l_dataS.setText("Data:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l_c, org.jdesktop.beansbinding.ELProperty.create("${font}"), l_dataS, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l_c, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), l_dataS, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        jPanel3.add(l_dataS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("Entrada");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, 20));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 170, 20));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 243, 144, 10));

        l_t.setText("Tolerância:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l_c, org.jdesktop.beansbinding.ELProperty.create("${font}"), l_t, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l_c, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), l_t, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        jPanel3.add(l_t, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));
        jPanel3.add(lb_tolerancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 60, -1));

        l_c.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        l_c.setForeground(new java.awt.Color(102, 102, 102));
        l_c.setText("Código:");
        jPanel3.add(l_c, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, -1));

        lb_codigo.setAlignment(java.awt.Label.CENTER);
        lb_codigo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jPanel3.add(lb_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 90, 20));

        l1.setText("Serviço:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l_c, org.jdesktop.beansbinding.ELProperty.create("${font}"), l1, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l_c, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), l1, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        jPanel3.add(l1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 50, -1));

        btn_clear.setLabel("Limpar");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        jPanel3.add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 75, -1));

        btn_Gerar.setLabel("Gerar");
        btn_Gerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GerarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_Gerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 77, -1));

        cb_serv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jPanel3.add(cb_serv, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 100, -1));

        lb_horaSaida.setText("jLabel6");
        jPanel3.add(lb_horaSaida, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        lb_dataSaida.setText("jLabel6");
        jPanel3.add(lb_dataSaida, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        lb_HoraEntrada.setText("jLabel6");
        jPanel3.add(lb_HoraEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        lb_dataEntrada.setText("jLabel6");
        jPanel3.add(lb_dataEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

        jPanel2.add(jPanel3);

        javax.swing.GroupLayout jt_validacaoLayout = new javax.swing.GroupLayout(jt_validacao);
        jt_validacao.setLayout(jt_validacaoLayout);
        jt_validacaoLayout.setHorizontalGroup(
            jt_validacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jt_validacaoLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(pn_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(425, Short.MAX_VALUE))
        );
        jt_validacaoLayout.setVerticalGroup(
            jt_validacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jt_validacaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jt_validacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jt_validacaoLayout.createSequentialGroup()
                        .addComponent(pn_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(174, 174, 174))
        );

        jt_geral.addTab("Validação", jt_validacao);

        jt_trafego.setBackground(new java.awt.Color(255, 255, 255));

        tb_regCod.setForeground(new java.awt.Color(102, 102, 102));
        tb_regCod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tb_regCod);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jl_servico.setText("Serviço:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l3, org.jdesktop.beansbinding.ELProperty.create("${font}"), jl_servico, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l3, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), jl_servico, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        cd_servTrafego.setBackground(new java.awt.Color(255, 255, 255));
        cd_servTrafego.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cd_servTrafego.setBorder(null);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entrada", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        jLabel2.setText("Data/Hora:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l3, org.jdesktop.beansbinding.ELProperty.create("${font}"), jLabel2, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l3, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), jLabel2, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        jformat_dataEntrada.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1420147140000L), new java.util.Date(1420147140000L), new java.util.Date(), java.util.Calendar.DAY_OF_MONTH));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jformat_dataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jformat_dataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Saida", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        jLabel12.setText("Data/Hora:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l1, org.jdesktop.beansbinding.ELProperty.create("${font}"), jLabel12, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l1, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), jLabel12, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        jformat_dataSaida.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jformat_dataSaida.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1536100605067L), null, null, java.util.Calendar.DAY_OF_MONTH));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jformat_dataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jformat_dataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setText("Status:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l1, org.jdesktop.beansbinding.ELProperty.create("${font}"), jLabel8, org.jdesktop.beansbinding.BeanProperty.create("font"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, l1, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), jLabel8, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        cd_Status.setBackground(new java.awt.Color(255, 255, 255));
        cd_Status.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cd_Status.setForeground(new java.awt.Color(102, 102, 102));
        cd_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Status" }));

        jButton2.setText("Pesquisar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("imprimir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jl_servico)
                                .addGap(13, 13, 13)
                                .addComponent(cd_servTrafego, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cd_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cd_servTrafego, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_servico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cd_Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jt_trafegoLayout = new javax.swing.GroupLayout(jt_trafego);
        jt_trafego.setLayout(jt_trafegoLayout);
        jt_trafegoLayout.setHorizontalGroup(
            jt_trafegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jt_trafegoLayout.createSequentialGroup()
                .addGroup(jt_trafegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jt_trafegoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jt_trafegoLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        jt_trafegoLayout.setVerticalGroup(
            jt_trafegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jt_trafegoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        jt_geral.addTab("Trafégo", jt_trafego);

        pn_botoes.setBackground(new java.awt.Color(255, 255, 255));
        pn_botoes.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pn_botoesLayout = new javax.swing.GroupLayout(pn_botoes);
        pn_botoes.setLayout(pn_botoesLayout);
        pn_botoesLayout.setHorizontalGroup(
            pn_botoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1355, Short.MAX_VALUE)
        );
        pn_botoesLayout.setVerticalGroup(
            pn_botoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/park/img/t_title_patio.png"))); // NOI18N

        btn_newTicket.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\new_ticket.png")); // NOI18N
        btn_newTicket.setToolTipText("Clique aqui para gerar um novo ticket");
        btn_newTicket.setBorder(null);
        btn_newTicket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_newTicketMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_newTicketMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_newTicketMouseExited(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\new_ticketF_static.png")); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jt_geral, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pn_botoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_newTicket)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_botoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_newTicket)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jt_geral, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_GerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GerarActionPerformed
        if (cb_serv.getSelectedItem() == "Não possui") {
            JOptionPane.showMessageDialog(btn_Gerar, "Verificar Serviço");
        } else {
            geraTicket();
        }
    }//GEN-LAST:event_btn_GerarActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        limpaTela();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_newTicketMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_newTicketMouseEntered
        btn_newTicket.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\new_ticket_select.png"));
    }//GEN-LAST:event_btn_newTicketMouseEntered

    private void btn_newTicketMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_newTicketMouseExited
        btn_newTicket.setIcon(new javax.swing.ImageIcon("C:\\Users\\Programador\\Desktop\\icopasta\\new_ticket.png"));

    }//GEN-LAST:event_btn_newTicketMouseExited

    private void tx_codKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_codKeyPressed
        //Pegar o Enter aqui e fazer funcionar
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {

            try {
                int tega = Integer.parseInt(tx_cod.getText());
                tx_cod.setText("");
                try {
                    telaCobranca(bancoTicket.consultaTicket(tega));
                } catch (Exception e) {

                    JOptionPane.showMessageDialog(tx_cod, "Ticket Inexistente1", "NENHUM DADO", 2);
                    System.err.println(e);
                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(tx_cod, "Ticket Inexistente", "NENHUM DADO", 2);
            }

        }
    }//GEN-LAST:event_tx_codKeyPressed

    private void btn_newTicketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_newTicketMouseClicked
        geraTicket();
    }//GEN-LAST:event_btn_newTicketMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        if (jPanel3.isVisible()) {
            jPanel3.setVisible(false);
        } else {
            jPanel3.setVisible(true);
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            setFilterInJTable();
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("Clicado");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        print("RelatorioTrafego2");
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btn_Gerar;
    private java.awt.Button btn_clear;
    private javax.swing.JLabel btn_newTicket;
    private javax.swing.JComboBox<String> cb_convenio;
    private javax.swing.JComboBox<String> cb_serv;
    private javax.swing.JComboBox<String> cd_Status;
    private javax.swing.JComboBox<String> cd_servTrafego;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSpinner jformat_dataEntrada;
    private javax.swing.JSpinner jformat_dataSaida;
    private javax.swing.JLabel jl_servico;
    private javax.swing.JTabbedPane jt_geral;
    private javax.swing.JPanel jt_trafego;
    private javax.swing.JPanel jt_validacao;
    private java.awt.Label l1;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel l4;
    private javax.swing.JLabel l5;
    private javax.swing.JLabel l6;
    private java.awt.Label l_c;
    private java.awt.Label l_data;
    private java.awt.Label l_dataS;
    private java.awt.Label l_hora;
    private java.awt.Label l_horaS;
    private java.awt.Label l_t;
    private javax.swing.JLabel lb_HoraEntrada;
    private javax.swing.JLabel lb_cobrar;
    private java.awt.Label lb_codigo;
    private javax.swing.JLabel lb_codigoConsulta;
    private javax.swing.JLabel lb_dataEntrada;
    private javax.swing.JLabel lb_dataSaida;
    private javax.swing.JLabel lb_horaSaida;
    private javax.swing.JLabel lb_status;
    private java.awt.Label lb_tolerancia;
    private javax.swing.JLabel lb_txCódigo;
    private javax.swing.JLabel lb_validade;
    private javax.swing.JPanel pn_botoes;
    private javax.swing.JPanel pn_consulta;
    private javax.swing.JTable tb_regCod;
    private javax.swing.JTextField tx_cod;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
