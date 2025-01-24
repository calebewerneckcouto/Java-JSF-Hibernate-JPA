package br.com.cursojsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dao.DaoGeneric;
import br.com.entidades.Lancamento;
import br.com.repository.IDaoLancamento;

@ViewScoped
@Named(value = "relLancamento")
public class RelLancamento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataIni;
	private Date dataFim;

	private String topico;
	
	
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

	@Inject
	private IDaoLancamento daoLancamento;

	@Inject
	private DaoGeneric<Lancamento> daoGeneric;
	
	
	

	

	public String getTopico() {
		return topico;
	}

	public void setTopico(String topico) {
		this.topico = topico;
	}

	public IDaoLancamento getDaoLancamento() {
		return daoLancamento;
	}

	public void setDaoLancamento(IDaoLancamento daoLancamento) {
		this.daoLancamento = daoLancamento;
	}

	public DaoGeneric<Lancamento> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Lancamento> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public Date getDataIni() {
		return dataIni;
	}

	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	


	public void bucarLancamento() {
		if (dataIni == null && dataFim == null && topico == null) {
			lancamentos = daoGeneric.getListEntity(Lancamento.class);
		} else {
			lancamentos = daoLancamento.relatorioLancamento(topico, dataIni, dataFim);
		}

	}

}
