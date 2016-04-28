package com.mb;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.facade.ClienteFacade;
import com.facade.CorFacade;
import com.facade.DenteFacade;
import com.facade.DentistaFacade;
import com.facade.HistoricoItemOrdemServicoFacade;
import com.facade.HistoricoOrdemServicoFacade;
import com.facade.ItemOrdemServicoFacade;
import com.facade.OrdemServicoFacade;
import com.facade.RelatorioOrdemServicoFacade;
import com.facade.ServicoFacade;
import com.model.Cliente;
import com.model.Cor;
import com.model.Dente;
import com.model.Dentista;
import com.model.HistoricoItemOrdemServico;
import com.model.HistoricoOrdemServico;
import com.model.ItemOrdemServico;
import com.model.OrdemServico;
import com.model.RelatorioOrdemServico;
import com.model.Servico;
import com.model.Situacao;
import com.model.User;
import com.util.Sistema;

@ViewScoped
@ManagedBean
public class OrdemServicoMB extends AbstractMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private OrdemServico ordemServico;
	private OrdemServico filtro;
	private List<OrdemServico> ordemServicos;
	private OrdemServicoFacade ordemServicoFacade;
	private ClienteFacade clienteFacade;
	private DentistaFacade dentistaFacade;
	private Cliente cliente;
	private Cliente clienteFiltro;
	private Dentista dentistaFiltro;
	private List<Cliente> allClientes;
	private Situacao situacao;
	private Situacao situacaoFiltro;
	private List<Dentista> allDentistas; 
	private Dentista dentista;
	
	private HistoricoOrdemServico  historicoOrdemServico;
	private HistoricoOrdemServicoFacade historicoOrdemServicoFacade;
	private ItemOrdemServico itemOrdemServico;
	private ItemOrdemServicoFacade itemOrdemServicoFacade;
	private User user;
	private RelatorioOrdemServicoFacade relatorioOrdemServicoFacade;
	private Servico servico;
	private ServicoFacade servicoFacade;
	private List<Servico> allServicos;
	private List<ItemOrdemServico> itemOrdemServicos;
	
	private Cor cor;
	private List<Cor> allCors;
	private CorFacade corFacade;
	
	private Dente dente;
	private Map<String, Dente> dentes;
	private List<String> selectedDentes;
	private DenteFacade denteFacade;
	private List<ItemOrdemServico> itemOrdemServicosDelete;
	
	private HistoricoItemOrdemServico historicoItemOrdemServico;
	private HistoricoItemOrdemServicoFacade historicoItemOrdemServicoFacade;
	
	public OrdemServicoFacade getOrdemServicoFacade() {
		if (ordemServicoFacade == null) {
			ordemServicoFacade = new OrdemServicoFacade();
		}

		return ordemServicoFacade;
	}
	
	public DentistaFacade getDentistaFacade() {
		if (dentistaFacade == null) {
			dentistaFacade = new DentistaFacade();
		}

		return dentistaFacade;
	}
	
	public RelatorioOrdemServicoFacade getRelatorioOrdemServicoFacade() {
		if (relatorioOrdemServicoFacade == null) {
			relatorioOrdemServicoFacade = new RelatorioOrdemServicoFacade();
		}

		return relatorioOrdemServicoFacade;
	}
	public ClienteFacade getClienteFacade() {
		if (clienteFacade == null) {
			clienteFacade = new ClienteFacade();
		}

		return clienteFacade;
	}
	
	public ServicoFacade getServicoFacade() {
		if (servicoFacade == null) {
			servicoFacade = new ServicoFacade();
		}

		return servicoFacade;
	}
	
	public HistoricoOrdemServicoFacade getHistoricoOrdemServicoFacade() {
		if (historicoOrdemServicoFacade == null) {
			historicoOrdemServicoFacade = new HistoricoOrdemServicoFacade();
		}
		
	    dentes       = new HashMap<String,Dente>();
		List<Dente> listDentes = getDenteFacade().listAll();
        for(Dente den :listDentes){
        	dentes.put(den.getNome(),den) ; 
        }
        
		return historicoOrdemServicoFacade;
	}
    
	public ItemOrdemServicoFacade getItemOrdemServicoFacade(){
		if (itemOrdemServicoFacade == null){
			itemOrdemServicoFacade = new ItemOrdemServicoFacade();
		}
		return itemOrdemServicoFacade;
	}
	
	
	
	public HistoricoItemOrdemServicoFacade getHistoricoItemOrdemServicoFacade() {
		
		if(historicoItemOrdemServicoFacade == null){
			historicoItemOrdemServicoFacade = new HistoricoItemOrdemServicoFacade();
		}
		return historicoItemOrdemServicoFacade;
	}

	public OrdemServico getOrdemServico() {
		if (ordemServico == null) {
			ordemServico = new OrdemServico();
		}

		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		List<HistoricoOrdemServico> hOS = getHistoricoOrdemServicoFacade().listAll(ordemServico);
		if(hOS != null){
			ordemServico.setHistoricoOrdemServico(hOS);
		}
		this.ordemServico = ordemServico;
		 historicoOrdemServicoAtual();
	}

	public void createOrdemServico() {
		try {
				
			user =  (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"); 
			/*if (situacao != null){
				ordemServico.setSituacao(situacao);
			}
			if(cliente != null){
				ordemServico.setCliente(cliente);
			}*/
			
			/*if(servico != null){
				ordemServico.setServico(servico);
			}*/
			if(user != null){
				ordemServico.setIdUser(user.getId());
				ordemServico.setDtCadastro(new Date());
			}
			
			if(getItemOrdemServicoFacade().verificaAlteracao(ordemServico)){
					
				getOrdemServicoFacade().createOrdemServico(ordemServico);
				
				for(ItemOrdemServico item : ordemServico.getItemOrdemServico()){
						
					item.setOrdemServico(ordemServico);
					item.setIdUser(user.getId());
					item.setDtCadastro(new Date());
					
					getItemOrdemServicoFacade().createItemOrdemServico(item);	
				}
					
				//ordemServico.setItemOrdemServico(itemOrdemServicos);
				
				
				if (ordemServicos == null){
					ordemServicos = getOrdemServicoFacade().recuperaOrdemServico(ordemServico);
				}
				else{
				    ordemServicos.add(ordemServico);
				}
				closeDialog();
				displayInfoMessageToUser("Ordem Gravada com Sucesso!");
				
				resetOrdemServico();
			}
			/*else{
				new Exception("N„o foi Informado Item para a OS!");
				displayErrorMessageToUser("N„o foi Informado Item para a OS. Favor Verificar!");	
				
				
			}*/
				
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n„o È possivel criar a OS. Tente novamente mais tarde! "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void updateOrdemServico() {
		try {
			
			user =  (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"); 
			if(user != null){
				ordemServico.setIdUserAlteracao(user.getId());		
			}
			if (situacao != null){
				ordemServico.setSituacao(situacao);
			}
			if(getItemOrdemServicoFacade().verificaAlteracao(ordemServico)){
				historicoOrdemServico.setIdUserAlteracao(user.getId());
				historicoOrdemServico.setDtAlteracao(new Date());
				
				getHistoricoOrdemServicoFacade().createHistoricoOrdemServico(historicoOrdemServico);
				
				for(HistoricoItemOrdemServico itemhist : historicoOrdemServico.getHistoricoItemOrdemServicos()){
				    getHistoricoItemOrdemServicoFacade().createHistoricoItemOrdemServico(itemhist);
				}
				
				List<HistoricoOrdemServico> hist /*= ordemServico.getHistoricoOrdemServico()*/;
				
				hist = getHistoricoOrdemServicoFacade().listAll(ordemServico);
				
				//hist.add(historicoOrdemServico);
				
				ordemServico.setHistoricoOrdemServico(hist);
				getOrdemServicoFacade().updateOrdemServico(ordemServico);
				
				for(ItemOrdemServico item : ordemServico.getItemOrdemServico()){
					
					item.setOrdemServico(ordemServico);
					if(item.getId() == 0){
					   item.setIdUser(user.getId());
					   item.setDtCadastro(new Date());
					   getItemOrdemServicoFacade().createItemOrdemServico(item);
					}
					else{
						item.setIdUserAlteracao(user.getId());
						item.setDtAlteracao(new Date());
						getItemOrdemServicoFacade().updateItemOrdemServico(item);
					}
				}
				
				closeDialog();
				displayInfoMessageToUser("Dados Gravados com Sucesso!");
				//loadOrdemServicos();
				resetOrdemServico();
			}
			/*else{
				Exception e = new Exception("N„o foi Informado Item para a OS!");
				displayErrorMessageToUser("N„o foi Informado Item para a OS. Favor Verificar!");
				e.printStackTrace();
				
			}*/
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, Erro ao atualizar Dados. Tente novamente mais tarde!"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void deleteOrdemServico() {
		try {
			getOrdemServicoFacade().deleteOrdemServico(ordemServico);
			closeDialog();
			displayInfoMessageToUser("Deleted With Sucess");
			loadOrdemServicos();
			resetOrdemServico();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public List<OrdemServico> getAllOrdemServicos() {
		if (ordemServicos == null) {
			//loadOrdemServicos();
		}
		if (clienteFiltro == null){
			clienteFiltro = new Cliente();
	
		}
		if (dentistaFiltro == null){
			dentistaFiltro = new Dentista();
	
		}
		if (filtro == null){
			filtro = new OrdemServico();
		}

		return ordemServicos;
	}

	private void loadOrdemServicos() {
		ordemServicos = getOrdemServicoFacade().listAll();
		
	}

	
	public void resetOrdemServico() {
		ordemServico = new OrdemServico();
		cliente      = new Cliente();
		filtro       = new OrdemServico();
		situacao     = null;
		servico      = new Servico();
		itemOrdemServico = new ItemOrdemServico();
		itemOrdemServicos = new ArrayList<ItemOrdemServico>();
		dente        = new Dente();
		dentes       = new LinkedHashMap<String,Dente>();
		selectedDentes    = new ArrayList<String>();
		List<Dente> listDentes = getDenteFacade().listAll();
        for(Dente den :listDentes){
        	dentes.put(den.getNome(),den) ; 
        }
        dentista = new Dentista();
	}
	
	public void resetItemOrdemServico(){
		itemOrdemServico = new ItemOrdemServico();
	}
    public void carregaFiltro(){
    	if (clienteFiltro != null && clienteFiltro.getId() != 0  ){
			if (filtro ==null){
				filtro = new OrdemServico();
			}
			filtro.setCliente(clienteFiltro);
		}
		if (dentistaFiltro != null && dentistaFiltro.getId() != 0  ){
			if (filtro ==null){
				filtro = new OrdemServico();
			}
			filtro.setDentista(dentistaFiltro);
		}
		
		if(situacaoFiltro != null ){
			if (filtro ==null){
				filtro = new OrdemServico();
			}
			filtro.setSituacao(situacaoFiltro);
		}
    }
	public void localizarOrdemServico() {
		
		carregaFiltro();
		ordemServicos = getOrdemServicoFacade().recuperaOrdemServico(filtro);
		
		for(OrdemServico ordem :ordemServicos){
			List<ItemOrdemServico> items = ordem.getItemOrdemServico();
			for(ItemOrdemServico item : items){
				if(item.getDentes() != null){
					item.setListadentes(getItemOrdemServicoFacade().dentesListaString(item.getDentes()));
					item.setDentesLista(getItemOrdemServicoFacade().deteString(item.getDentes()));
				}
			}
		}
		
	}
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Cliente> complete(String name) {
		List<Cliente> queryResult = new ArrayList<Cliente>();

		//if (allClientes == null) {
			
		allClientes = getClienteFacade().listAll();
		//}

		//allClientes.remove(ordemServico.getCliente());

		for (Cliente cliente : allClientes) {
			if (cliente.getName().toLowerCase().contains(name.toLowerCase())) {
				queryResult.add(cliente);
			}
		}

		return queryResult;
	}
	
	public List<Cliente> completeFiltro(String name) {
		List<Cliente> queryResult = new ArrayList<Cliente>();

		//if (allClientes == null) {
			
		allClientes = getClienteFacade().listAll();
		//}

		//allClientes.remove(ordemServico.getCliente());

		for (Cliente cliente : allClientes) {
			if (cliente.getName().toLowerCase().contains(name.toLowerCase())) {
				queryResult.add(cliente);
			}
		}

		return queryResult;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
	public List<Situacao> completeSituacao(String name) {
		List<Situacao> queryResult = new ArrayList<Situacao>();

		
		for (Situacao situacao : Situacao.values()) {
			
				queryResult.add(situacao);
			
		}

		return queryResult;
	}

	public OrdemServico getAllHistoricoOrdemServicos() {
		 List<HistoricoOrdemServico> hist = getHistoricoOrdemServicoFacade().listAll(ordemServico);
		
		 ordemServico.setHistoricoOrdemServico(hist);
		return  ordemServico;
	}



	public HistoricoOrdemServico getHistoricoOrdemServico() {
		if (historicoOrdemServico == null){
			historicoOrdemServico = new HistoricoOrdemServico();
		}
		return historicoOrdemServico;
	}

	public void setHistoricoOrdemServico(HistoricoOrdemServico historicoOrdemServico) {
		this.historicoOrdemServico = historicoOrdemServico;
	}
	
	
	public HistoricoItemOrdemServico getHistoricoItemOrdemServico() {
		return historicoItemOrdemServico;
	}

	public void setHistoricoItemOrdemServico(
			HistoricoItemOrdemServico historicoItemOrdemServico) {
		this.historicoItemOrdemServico = historicoItemOrdemServico;
	}

	public void historicoOrdemServicoAtual() {
		try {
			if(ordemServico != null){
				
				historicoOrdemServico = new HistoricoOrdemServico();
				historicoOrdemServico.setDtEntrada(ordemServico.getDtEntrada());
				historicoOrdemServico.setDtSaida(ordemServico.getDtSaida());
				//historicoOrdemServico.setIdOS(ordemServico.getId());
				historicoOrdemServico.setOrdemServico(ordemServico);
				historicoOrdemServico.setPreco(ordemServico.getPreco());
				historicoOrdemServico.setSituacao(ordemServico.getSituacao());
				historicoOrdemServico.setCliente(ordemServico.getCliente());
				historicoOrdemServico.setDentista(ordemServico.getDentista());
				historicoOrdemServico.setDsDescricao(ordemServico.getDsDescricao());
				//historicoOrdemServico.setServico(ordemServico.getServico());
				historicoOrdemServico.setPaciente(ordemServico.getPaciente());
				itemOrdemServico =new ItemOrdemServico();
			    
				//historicoOrdemServico.setHistoricoItemOrdemServicos(new ArrayList<HistoricoItemOrdemServico>());
				
				if(ordemServico.getItemOrdemServico()!= null){
				    
					List<HistoricoItemOrdemServico> listaItemHistorico = new ArrayList<HistoricoItemOrdemServico>();
					for(ItemOrdemServico item : ordemServico.getItemOrdemServico()){
						historicoItemOrdemServico =  new HistoricoItemOrdemServico();
						historicoItemOrdemServico.setCor(item.getCor());
						historicoItemOrdemServico.setDentes(item.getDentesLista());
						historicoItemOrdemServico.setDtAlteracao(item.getDtAlteracao());
						historicoItemOrdemServico.setDtCadastro(item.getDtCadastro());
						historicoItemOrdemServico.setHistoricoOrdemServico(historicoOrdemServico);
						historicoItemOrdemServico.setIdItemOS(item.getId());
						historicoItemOrdemServico.setIdOS(item.getOrdemServico().getId());
						historicoItemOrdemServico.setIdUser(item.getIdUser());
						historicoItemOrdemServico.setIdUserAlteracao(item.getIdUserAlteracao());
						historicoItemOrdemServico.setObservacao(item.getObservacao());
						historicoItemOrdemServico.setPrecoTotal(item.getPrecoTotal());
						historicoItemOrdemServico.setPrecoUnitario(item.getPrecoUnitario());
						historicoItemOrdemServico.setQuantidade(item.getQuantidade());
						historicoItemOrdemServico.setServico(item.getServico());
						
						//historicoOrdemServico.setHistoricoItemOrdemServicos(historicoItemOrdemServico);
						listaItemHistorico.add(historicoItemOrdemServico);
					}
					
					historicoOrdemServico.setHistoricoItemOrdemServicos(listaItemHistorico);
				}
			}
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public OrdemServico getFiltro() {
		return filtro == null ? new OrdemServico() : filtro ;
	}

	public void setFiltro(OrdemServico filtro) {
		this.filtro = filtro;
	}

	public Cliente getClienteFiltro() {
		return clienteFiltro;
	}

	public void setClienteFiltro(Cliente clienteFiltro) {
		this.clienteFiltro = clienteFiltro;
	}
	

	public Situacao getSituacaoFiltro() {
		return situacaoFiltro;
	}

	public void setSituacaoFiltro(Situacao situacaoFiltro) {
		this.situacaoFiltro = situacaoFiltro;
	}
	
	public void limpaCodigo(){
		filtro.setId(0);
	}
	
	public void limpaCliente(){
		clienteFiltro = new Cliente();
		filtro.setCliente(clienteFiltro);
	}
	
	public void limpaDentista(){
		dentistaFiltro = new Dentista();
		filtro.setDentista(dentistaFiltro);
	}
	
	public void limpaPaciente(){
		filtro.setPaciente("");
	}
	
	public void limpaPeriodo(){
		
		Date data = null ;
				
		filtro.setDtEntrada(data);
		filtro.setDtSaida(data);
	}
	public void limpaSituacao(){
		
		situacaoFiltro = null;
		filtro.setSituacao(situacaoFiltro);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	//gerar relatorio pdf
	private byte[] relatorioOrdemServico(
	        String path,
	        String slash,
	        Collection< RelatorioOrdemServico > lista,
	        String nomeRelatorio
	        
	        ) throws JRException,Exception {

	        if ( nomeRelatorio == null ) {
	            
	        	
	            displayErrorMessageToUser("Ops,Nome do relat·rio n„o informado");
				
	        }
	        if ( lista == null || lista.size() == 0 ) {
	        	
	            throw new Exception( "N„o h· informaÁ„o disponÌvel para imprimir "
	                 );
	        }

	        Map< String, Object > parametros = new HashMap< String, Object >();
	        parametros.put( "SUBREPORT_DIR", path + slash );

	       
	        GregorianCalendar g = new GregorianCalendar();
	        g.set( GregorianCalendar.getInstance().get( GregorianCalendar.YEAR ), GregorianCalendar.getInstance().get(
	            GregorianCalendar.MONDAY ), GregorianCalendar.getInstance().get( GregorianCalendar.DATE ) );
	        parametros.put( "dataAtual", g.getTime() );

	        @SuppressWarnings("deprecation")
			JasperReport relatorio = (JasperReport) JRLoader.loadObject( path + slash + nomeRelatorio );
	        byte[] bytes1 = JasperRunManager
	            .runReportToPdf( relatorio, parametros, new JRBeanCollectionDataSource( lista ) );

	        return bytes1;

	    }
	
	 /*
     * identifica qual relatorio pdf deve ser executado e retorna os bytes para
     * sua construcao
     */
    private Object[] verificaRelatorioPDF(  ) throws Exception {

        // Localiza√ß√£o do arquivo de propriedades lcalizado no servidor
        // String configPath = "\\opt\\javaapp\\config\\compras-config.properties";
        // √â a propriedade relativa ao caminho onde est√° o relat√≥rio
        // compilado.
        Properties configSistema = Sistema.configSistema();
        String path = configSistema.getProperty( "report.path" );
        String slash = configSistema.getProperty( "system.slash" );

        byte[] bytes = null;
        String nomeArquivoDownload = null;

        bytes = relatorioOrdemServico(path, slash);
        nomeArquivoDownload = "Relatorio_Final_".concat( new Date().toString() );
            
        Object[] relatorio = { bytes, nomeArquivoDownload };

        return relatorio;
    }
    
    private byte[] relatorioOrdemServico(
            String path,
            String slash
             ) throws JRException, Exception {

            String nomeRelatorio = "relatoria_fechamento_mensal_novo.jasper";
            carregaFiltro();
            Collection<RelatorioOrdemServico> lista =getRelatorioOrdemServicoFacade().recuperaOrdemServico(filtro);
            return relatorioOrdemServico(path, slash,  lista, nomeRelatorio);
                      
            
    }
    
    /* identifica e constroi um relatorio pdf */
    public void actionListenerImprimir(  ) {

        FacesContext fc = FacesContext.getCurrentInstance();

        try {

            Object[] relatorio = verificaRelatorioPDF(  );
            byte[] bytes = (byte[]) relatorio[ 0 ];
            String nomeArquivoDownload = (String) relatorio[ 1 ];

            
            if ( bytes != null && bytes.length > 0 ) {

                HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
                response.setHeader( "Content-disposition", "attachment;filename=\"".concat( nomeArquivoDownload )
                    .concat( ".pdf\"" ) );

                /*
                 * aqui ir√° abrir a janela do tipo abrir ou salvar...
                 * 
                 * caso se queira abrir direto na pagina basta comentar essa
                 * linha
                 */
                response.setContentType( "application/pdf" );

                response.setContentLength( bytes.length );
                ServletOutputStream ouputStream = response.getOutputStream();
                ouputStream.write( bytes, 0, bytes.length );
                ouputStream.flush();
                ouputStream.close();
                fc.renderResponse();
                fc.responseComplete();

            }
        }
        
        catch ( JRException e ) {
        	displayErrorMessageToUser("Ops,Erro ao gerar relat·rio."+e.getMessage());
			e.printStackTrace();
            
        }
        catch ( IOException e ) {
           
            displayErrorMessageToUser("Ops,Erro ao gerar relat·rio."+e.getMessage());
			e.printStackTrace();
        }
        catch ( Exception e ) {
           
            displayErrorMessageToUser("Ops,Erro ao gerar relat·rio."+e.getMessage());
			e.printStackTrace();
        }
        

    }

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
    
	public List<Servico> completeServico(String dsDescricao) {
		List<Servico> queryResult = new ArrayList<Servico>();
			
		 allServicos = getServicoFacade().listAll();
				
		for (Servico servico : allServicos) {
			if (servico.getDsDescricao().toLowerCase().contains(dsDescricao.toLowerCase())) {
				queryResult.add(servico);
			}
		}

		return queryResult;
	}

	public ItemOrdemServico getItemOrdemServico() {
		return itemOrdemServico;
	}

	public void setItemOrdemServico(ItemOrdemServico itemOrdemServico) {
		this.itemOrdemServico = itemOrdemServico;
	}

	public List<ItemOrdemServico> getItemOrdemServicos() {
		return itemOrdemServicos;
	}

	public void setItemOrdemServicos(List<ItemOrdemServico> itemOrdemServicos) {
		this.itemOrdemServicos = itemOrdemServicos;
	}
	
	
	public void createItemOS(){
		if(itemOrdemServicos == null){
			itemOrdemServicos = new ArrayList<ItemOrdemServico>();
			 
		}
		
		itemOrdemServico.setServico(servico);
		itemOrdemServico.setCor(cor);
		if(selectedDentes.size() > 0 ){
			String denteString = null ;
			int lista = 1;
			List<Dente> listaDente = new ArrayList<Dente>();
			for(String den : selectedDentes){
				if(lista == 1){
					denteString = den;
				}
				else {
					denteString = denteString + " - "+den;
				}
				lista = 2;
				listaDente.add( dentes.get(den));
			}
			itemOrdemServico.setDentesLista(denteString);
			itemOrdemServico.setDentes(listaDente);
			itemOrdemServico.setListadentes(selectedDentes);
		}
		
		if(ordemServico.getItemOrdemServico() != null){
			itemOrdemServicos = ordemServico.getItemOrdemServico();
		}
		itemOrdemServicos.add(itemOrdemServico);
		ordemServico.setItemOrdemServico(itemOrdemServicos);	
		reCalculaPrecoFinal(ordemServico);
		
		itemOrdemServico = new ItemOrdemServico();
		servico = new Servico();
		cor = new Cor();
		selectedDentes =  new ArrayList<String>();
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public CorFacade getCorFacade() {
		if (corFacade == null) {
			corFacade = new CorFacade();
		}

		return corFacade;
	}
	
	public List<Cor> completeCor(String dsDescricao) {
		List<Cor> queryResult = new ArrayList<Cor>();
			
		 allCors = getCorFacade().listAll();
				
		for (Cor  cor : allCors) {
			if (cor.getNome().toLowerCase().contains(dsDescricao.toLowerCase())) {
				queryResult.add(cor);
			}
		}

		return queryResult;
	}
	
	public DenteFacade getDenteFacade(){
		if (denteFacade == null){
			denteFacade = new DenteFacade();
		}
		return denteFacade;
	}

	public Dente getDente() {
		return dente;
	}

	public void setDente(Dente dente) {
		this.dente = dente;
	}

	public Map<String,Dente> getDentes() {
		return dentes;
	}

	public void setDentes(Map<String,Dente> dentes) {
		this.dentes = dentes;
	}

	public List<String> getSelectedDentes() {
		return selectedDentes;
	}

	public void setSelectedDentes(List<String> selectedDentes) {
		this.selectedDentes = selectedDentes;
	}
	
	public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Item Editado", ((ItemOrdemServico) event.getObject()).getServico().getDsDescricao());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);
       
        for(ItemOrdemServico item : ordemServico.getItemOrdemServico()){
	        
        	if(item.getListadentes().size() > 0 ){
				String denteString = null ;
				int lista = 1;
				List<Dente> listaDente = new ArrayList<Dente>();
				for(String den : item.getListadentes()){
					if(lista == 1){
						denteString = den;
					}
					else {
						denteString = denteString + " - "+den;
					}
					lista = 2;
					listaDente.add( dentes.get(den));
				}
				item.setDentesLista(denteString);
				item.setDentes(listaDente);
			}
        	else{
        		item.setDentesLista("");
        		item.setDentes(null);
        	}
        }
     
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Item Cancelado", ((ItemOrdemServico) event.getObject()).getServico().getDsDescricao());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
    public void calculaPreco() {
    	if(itemOrdemServico != null){
	    	if(itemOrdemServico.getQuantidade() != null && itemOrdemServico.getQuantidade() > 0 ){
	    		if(itemOrdemServico.getPrecoUnitario() != null && itemOrdemServico.getPrecoUnitario().compareTo(BigDecimal.ZERO) > 0  ){
	    			//System.out.println("Valor maior que zero");
	    			itemOrdemServico.setPrecoTotal(itemOrdemServico.getPrecoUnitario().multiply(new BigDecimal( itemOrdemServico.getQuantidade())));
	    		}
	    	}
    	}
    }
    
    public void calculaPrecoFinal(ItemOrdemServico itemOrdemServico) {
    	if(itemOrdemServico != null){
	    	if(itemOrdemServico.getQuantidade() != null && itemOrdemServico.getQuantidade() > 0 ){
	    		if(itemOrdemServico.getPrecoUnitario() != null && itemOrdemServico.getPrecoUnitario().compareTo(BigDecimal.ZERO) > 0  ){
	    		   itemOrdemServico.setPrecoTotal(itemOrdemServico.getPrecoUnitario().multiply(new BigDecimal( itemOrdemServico.getQuantidade())));
	    		  
	    		   BigDecimal precoTotalOS = BigDecimal.ZERO;
	    		   for(ItemOrdemServico item : ordemServico.getItemOrdemServico()){
	    			 precoTotalOS =  precoTotalOS.add(item.getPrecoTotal().setScale(2,BigDecimal.ROUND_HALF_EVEN)).setScale(2,BigDecimal.ROUND_HALF_EVEN);
	    		   }
	    		   ordemServico.setPreco(precoTotalOS);
	    		}
	    	}
    	}
    }

	public List<ItemOrdemServico> getItemOrdemServicosDelete() {
		return itemOrdemServicosDelete;
	}

	public void setItemOrdemServicosDelete(
			List<ItemOrdemServico> itemOrdemServicosDelete) {
		this.itemOrdemServicosDelete = itemOrdemServicosDelete;
	}

    public void deleteItemOS(ItemOrdemServico item){
    	
    	if(ordemServico.getItemOrdemServico().size() > 1){
	    	if(item.getId() == 0){
	    	    ordemServico.getItemOrdemServico().remove(item);	
	    	    reCalculaPrecoFinal(ordemServico);
	    	}
	    	else{
		    	int idOS = ordemServico.getId();
		    	getItemOrdemServicoFacade().deleteItemOrdemServico(item);
		    	ordemServico.getItemOrdemServico().remove(item);
		    	reCalculaPrecoFinal(ordemServico);
		    	
		    	updateOrdemServico();
		    	ordemServico = getOrdemServicoFacade().findOrdemServico(idOS);
		    	historicoOrdemServicoAtual();
	    	}
    	}
    	else{
    		FacesMessage msg = new FacesMessage("Existe somente Um item na OS, N„o È Possivel Excluir! Favor Verificar!" );  
    		  
            FacesContext.getCurrentInstance().addMessage(null, msg);
    	}
    }
    
    public void reCalculaPrecoFinal(OrdemServico ordemServico) {
    	    	
		   BigDecimal precoTotalOS = BigDecimal.ZERO;
		   for(ItemOrdemServico item : ordemServico.getItemOrdemServico()){
			 precoTotalOS =  precoTotalOS.add(item.getPrecoTotal().setScale(2,BigDecimal.ROUND_HALF_EVEN)).setScale(2,BigDecimal.ROUND_HALF_EVEN);
		   }
		   ordemServico.setPreco(precoTotalOS);
    		
    	
    	
    }
    
    public List<Dentista> dentistaClinica(String name) {
		List<Dentista> queryResult = new ArrayList<Dentista>();
		if(ordemServico.getCliente() != null && ordemServico.getCliente().getId() > 0 ){	
			allDentistas = getDentistaFacade().recuperaDesntistasClinica(ordemServico.getCliente());
					
			for (Dentista dentista : allDentistas) {
				if (dentista.getNome().toLowerCase().contains(name.toLowerCase())) {
					queryResult.add(dentista);
				}
			}
		}
		else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Favor informar o cliente! ", null);
	        
		    FacesContext.getCurrentInstance().addMessage(null, message);
		    return null;
		}

		return queryResult;
	}

	public Dentista getDentista() {
		return dentista;
	}

	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}
	
	public void clienteBusca(SelectEvent event){
		
		int idCliente =  Integer.parseInt(event.getObject().toString());
		ordemServico.setCliente( clienteFacade.findCliente(idCliente));
		
	}

	public Dentista getDentistaFiltro() {
		return dentistaFiltro;
	}

	public void setDentistaFiltro(Dentista dentistaFiltro) {
		this.dentistaFiltro = dentistaFiltro;
	}
    
	public List<Dentista> completeFiltroDentista(String name) {
		List<Dentista> queryResult = new ArrayList<Dentista>();
		if(ordemServico.getCliente() != null && ordemServico.getCliente().getId() > 0 ){	
			allDentistas = getDentistaFacade().recuperaDesntistasClinica(ordemServico.getCliente());
					
			for (Dentista dentista : allDentistas) {
				if (dentista.getNome().toLowerCase().contains(name.toLowerCase())) {
					queryResult.add(dentista);
				}
			}
		}
		else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Favor informar o cliente! ", null);
	        
		    FacesContext.getCurrentInstance().addMessage(null, message);
		    return null;
		}

		return queryResult;
	}
	
	public void listaDentesString(){
		
		if(selectedDentes.size() > 0 ){
			String denteString = null ;
			int lista = 1;
			long quantidade = 0;
			List<Dente> listaDente = new ArrayList<Dente>();
			for(String den : selectedDentes){
				quantidade++;
				if(lista == 1){
					denteString = den;
				}
				else {
					denteString = denteString + " - "+den;
				}
				lista = 2;
				listaDente.add( dentes.get(den));
			}
			itemOrdemServico.setDentesLista(denteString);
			itemOrdemServico.setQuantidade(quantidade);
			//itemOrdemServico.setDentes(listaDente);
		}
		else {
			itemOrdemServico.setDentesLista("");
			itemOrdemServico.setQuantidade(1l);
		}
	}
	
	public void listaDentesString2(ItemOrdemServico item){
		
		if(item.getListadentes().size() > 0 ){
			long quantidade = item.getListadentes().size();
			item.setQuantidade(quantidade);
			calculaPrecoFinal(item);
			String denteString = null ;
			int lista = 1;
			List<Dente> listaDente = new ArrayList<Dente>();
			for(String den : item.getListadentes()){
				quantidade++;
				if(lista == 1){
					denteString = den;
				}
				else {
					denteString = denteString + " - "+den;
				}
				lista = 2;
				listaDente.add( dentes.get(den));
			}
			item.setDentesLista(denteString);
		}
		else {

			item.setQuantidade(1l);
			calculaPrecoFinal(item);
			item.setDentesLista("");
		}
	}
	
}
