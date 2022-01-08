package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.RegraDeNegocioException;
import com.example.demo.model.entity.Lancamento;
import com.example.demo.model.enums.StatusLancamento;
import com.example.demo.model.repository.LancamentoRepository;
import com.example.demo.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	@Autowired
	private LancamentoRepository repository;
	
	@Override
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		validar(lancamento);
		lancamento.setStatus(StatusLancamento.PENDENTE);
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		validar(lancamento);
		return repository.save(lancamento);
	}

	@Override
	public void deletar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		repository.delete(lancamento);
	}

	@Override
	public List<Lancamento> buscar(Lancamento lancamento) {
		
//		Example example = Example.create(lancamento).ignoreCase();
		
		return repository.findByValor(lancamento.getValor());
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {

		lancamento.setStatus(status);
		atualizar(lancamento);
	}

	@Override
	public void validar(Lancamento lancamento) {
		
		if (lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
			throw new RegraDeNegocioException("Descrição inválida informe uma Descrição válida!");
		}
		
		if (lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
			throw new RegraDeNegocioException("Digite um mês válido");
		}
		if (lancamento.getAno() == null || lancamento.getAno().toString().length() != 4 ) {
			throw new RegraDeNegocioException("Digite um ano válido");
		}
		if (lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
			throw new RegraDeNegocioException("Informe um usuário");
		}
		if (lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
			throw new RegraDeNegocioException("Informe um valor válido");
		}
		if (lancamento.getTipo() == null) {
			throw new RegraDeNegocioException("Informe um tipo");
		}
	}

}
