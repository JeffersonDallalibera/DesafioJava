<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Novo Projeto</title>
	<!-- Adicionando Bootstrap -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	<style>
		body {
			padding: 20px;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<style>
		.error-message {
			color: red;
		}
	</style>
</head>
<body>
<div class="container">
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">
				<a class="navbar-brand">Projetos</a>
				<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="/projetos/">Home</a>
						</li>

					</ul>
					<form class="d-flex">
						<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">Search</button>
					</form>
				</div>
			</div>
		</nav>
	<h2>Novo Projeto</h2>
	<form action="novo-projeto" method="post" onsubmit="return validarForm()">
		<div class="form-group">
			<label for="nome">Nome do Projeto:</label>
			<input type="text" class="form-control" id="nome" name="nome" value="${projeto.nome}">
			<span id="nomeError" class="error-message"></span><br>
		</div>
		<div class="form-group">
			<label for="dataInicio">Data de Início:</label>
			<input type="date" class="form-control" id="dataInicio" name="dataInicio" value="${projeto.dataInicio}">
			<span id="dataInicioError" class="error-message"></span><br>
		</div>
		<div class="form-group">
			<label for="gerenteResponsavel">Gerente Responsável:</label>
			<input type="number" class="form-control" id="gerenteResponsavel" name="gerenteResponsavel" value="${projeto.gerenteResponsavel}">
		</div>
		<div class="form-group">
			<label for="dataPrevisaoFim">Previsão de Término:</label>
			<input type="date" class="form-control" id="dataPrevisaoFim" name="dataPrevisaoFim" value="${projeto.dataPrevisaoFim}">
		</div>
		<div class="form-group">
			<label for="dataFim">Data Real de Término:</label>
			<input type="date" class="form-control" id="dataFim" name="dataFim" value="${projeto.dataFim}">
		</div>
		<div class="form-group">
			<label for="orcamento">Orçamento Total:</label>
			<input type="number" class="form-control" id="orcamento" name="orcamento" value="${projeto.orcamento}">
			<span id="orcamentoTotalError" class="error-message"></span><br>
		</div>
		<div class="form-group">
			<label for="descricao">Descrição:</label>
			<textarea class="form-control" id="descricao" name="descricao" rows="4" cols="50" value="${projeto.descricao}"></textarea>
		</div>
		<div class="form-group">
			<label for="risco">Classificação de Risco:</label>
			<select class="form-control" id="risco" name="risco" value="${projeto.risco}">
				<option value="">Selecione uma opção</option>
				<option value="baixo">Baixo Risco</option>
				<option value="medio">Médio Risco</option>
				<option value="alto">Alto Risco</option>
			</select>
			<span id="classificacaoRiscoError" class="error-message"></span><br>
		</div>
		<div class="form-group">
			<label for="status">Status:</label>
			<select class="form-control" id="status" name="status" value="${projeto.status}">
				<option value="">Selecione uma opção</option>
				<option value="em_analise">Em Análise</option>
				<option value="analise_realizada">Análise Realizada</option>
				<option value="analise_aprovada">Análise Aprovada</option>
				<option value="iniciado">Iniciado</option>
				<option value="planejado">Planejado</option>
				<option value="em_andamento">Em Andamento</option>
				<option value="encerrado">Encerrado</option>
				<option value="cancelado">Cancelado</option>
			</select>
			<span id="statusError" class="error-message"></span><br>
		</div>
		<button type="submit" class="btn btn-primary">Salvar</button>
		<a href="/projetos/" class="btn btn-secondary">Voltar à Página Inicial</a>
	</form>
</div>
<script>
	function validarForm() {
		console.log("Entrei validar")
		var nome = document.getElementById("nome").value.trim();
		var orcamentoTotal = document.getElementById("orcamento").value.trim();
		var classificacaoRisco = document.getElementById("risco").value;
		var status = document.getElementById("status").value.trim();
		var gerente = document.getElementById("gerenteResponsavel").value.trim();
		var errorMessages = [];

		// Validar Nome do Projeto
		if (nome === "") {
			errorMessages.push("O nome do projeto é obrigatório.");
			document.getElementById("nomeError").innerHTML = "O nome do projeto é obrigatório.";
			document.getElementById("nomeError").style.display = "block";
		} else {
			document.getElementById("nomeError").innerHTML = "";
			document.getElementById("nomeError").style.display = "none";
		}

		// Validar Orçamento Total
		if (orcamentoTotal === "") {
			errorMessages.push("O orçamento total é obrigatório.");
			document.getElementById("orcamentoTotalError").innerHTML = "O orçamento total é obrigatório.";
			document.getElementById("orcamentoTotalError").style.display = "block";
		} else {
			document.getElementById("orcamentoTotalError").innerHTML = "";
			document.getElementById("orcamentoTotalError").style.display = "none";
		}

		// Validar Classificação de Risco
		if (classificacaoRisco === "") {
			errorMessages.push("A classificação de risco é obrigatória.");
			document.getElementById("classificacaoRiscoError").innerHTML = "A classificação de risco é obrigatória.";
			document.getElementById("classificacaoRiscoError").style.display = "block";
		} else {
			document.getElementById("classificacaoRiscoError").innerHTML = "";
			document.getElementById("classificacaoRiscoError").style.display = "none";
		}

		// Validar Status
		if (status === "") {
			errorMessages.push("O status é obrigatório.");
			document.getElementById("statusError").innerHTML = "O status é obrigatório.";
			document.getElementById("statusError").style.display = "block";
		} else {
			document.getElementById("statusError").innerHTML = "";
			document.getElementById("statusError").style.display = "none";
		}

		// Validar Gerente Responsável
		if (gerente === "") {
			errorMessages.push("O gerente responsável é obrigatório.");
			document.getElementById("gerenteError").innerHTML = "O gerente responsável é obrigatório.";
			document.getElementById("gerenteError").style.display = "block";
		} else {
			document.getElementById("gerenteError").innerHTML = "";
			document.getElementById("gerenteError").style.display = "none";
		}

		// Se houver mensagens de erro, impede o envio do formulário
		if (errorMessages.length > 0) {
			console.log("mensagem de erro")
			alert("O formulario possui alguns erros, favor corrigir e reenviar!")
			return false;
		}
		return true;
	}
</script>
</body>
</html>
