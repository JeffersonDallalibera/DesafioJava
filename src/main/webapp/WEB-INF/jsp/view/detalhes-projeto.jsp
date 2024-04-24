<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalhes do Projeto</title>
    <!-- Adicionando Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .error-message {
            color: red;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="#">Projetos</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/projetos/">Home</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0" action="/projetos/search" method="GET">
                    <input class="form-control mr-sm-2" type="search" placeholder="Pesquisar projeto" aria-label="Search" name="q">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
<div class="container">
    <div class="container mt-3">
        <h1>Detalhes do Projeto</h1>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Detalhes do Projeto</h5>
                <form action="/projetos/atualizar" method="post">
                    <input type="hidden" name="id" value="${projeto.id}">
                    <div class="form-group">
                        <label for="nome">Nome do Projeto:</label>
                        <span class="visualizar">${projeto.nome}</span>
                        <input type="text" class="form-control editar" id="nome" name="nome" value="${projeto.nome}" style="display: none;">
                    </div>
                    <!-- Repita o mesmo padrão para outros campos -->
                    <div class="form-group">
                        <label for="descricao">Descricao:</label>
                        <span class="visualizar">${projeto.descricao}</span>
                        <textarea class="form-control editar" id="descricao" name="descricao" rows="4" cols="50" style="display: none;">${projeto.descricao}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="risco">Classificacao de Risco:</label>
                        <select class="form-control editar" id="risco" name="risco" style="display: none;">
                            <option value="baixo" ${projeto.risco eq 'baixo' ? 'selected' : ''}>Baixo Risco</option>
                            <option value="medio" ${projeto.risco eq 'medio' ? 'selected' : ''}>Medio Risco</option>
                            <option value="alto" ${projeto.risco eq 'alto' ? 'selected' : ''}>Alto Risco</option>
                        </select>
                        <span class="visualizar">${projeto.risco}</span>
                    </div>
                    <div class="form-group">
                        <label for="dataInicio">Data de Inicio:</label>
                        <span class="visualizar">${projeto.dataInicio}</span>
                        <input type="date" class="form-control editar" id="dataInicio" name="dataInicio" value="${projeto.dataInicio}" style="display: none;">
                    </div>
                    <div class="form-group">
                        <label for="dataFim">Data de Termino:</label>
                        <span class="visualizar">${projeto.dataFim}</span>
                        <input type="date" class="form-control editar" id="dataFim" name="dataFim" value="${projeto.dataFim}" style="display: none;">
                    </div>
                    <div class="form-group">
                        <label for="status">Status:</label>
                        <span class="visualizar">${projeto.status}</span>
                        <select class="form-control editar" id="status" name="status" style="display: none;">
                            <option value="em_analise" ${projeto.status eq 'em_analise' ? 'selected' : ''}>Em Analise</option>
                            <option value="analise_realizada" ${projeto.status eq 'analise_realizada' ? 'selected' : ''}>Analise Realizada</option>
                            <option value="analise_aprovada" ${projeto.status eq 'analise_aprovada' ? 'selected' : ''}>Analise Aprovada</option>
                            <option value="iniciado" ${projeto.status eq 'iniciado' ? 'selected' : ''}>Iniciado</option>
                            <option value="planejado" ${projeto.status eq 'planejado' ? 'selected' : ''}>Planejado</option>
                            <option value="em_andamento" ${projeto.status eq 'em_andamento' ? 'selected' : ''}>Em Andamento</option>
                            <option value="encerrado" ${projeto.status eq 'encerrado' ? 'selected' : ''}>Encerrado</option>
                            <option value="cancelado" ${projeto.status eq 'cancelado' ? 'selected' : ''}>Cancelado</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="dataFim">Data fim prevista:</label>
                        <span class="visualizar">${projeto.dataPrevisaoFim}</span>
                    </div>
                    <div class="form-group">
                        <label for="dataFim">Orcamento:</label>
                        <span class="visualizar">${projeto.orcamento}</span>
                    </div>
                    <div class="form-group">
                        <label for="dataFim">Gerente responsavel:</label>
                        <span class="visualizar">${projeto.gerenteResponsavel}</span>
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-primary editar-btn">Editar</button>
                        <button type="submit" class="btn btn-success salvar-btn" style="display: none;">Salvar</button>
                    </div>
                </form>
                <div class="form-group">
                    <form action="/projetos/excluir" method="post">
                        <input type="hidden" name="id" value="${projeto.id}">
                        <button type="submit" class="btn btn-danger excluir-btn"
                        ${projeto.status eq 'iniciado' || projeto.status eq 'em_andamento' || projeto.status eq 'encerrado' ? 'disabled' : ''}>Excluir Projeto</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Adicionando Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        $(".editar-btn").click(function() {
            $(this).closest('.card-body').find('.visualizar').hide();
            $(this).closest('.card-body').find('.editar').show();
            $(this).closest('.card-body').find('.salvar-btn').show();
            $(this).closest('.card-body').find('.excluir-btn').hide()
            $(this).hide();
        });
    });
</script>
</body>
</html>
