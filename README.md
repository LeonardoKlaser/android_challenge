Catálogo de Filmes
Descrição

Aplicativo Android para gerenciamento de um catálogo de filmes. O sistema permite o cadastro manual de filmes, pesquisa de filmes em uma API pública (OMDb API ou TMDb API), visualização de detalhes e gerenciamento da coleção localmente utilizando SQLite. O aplicativo não exige autenticação do usuário.

Funcionamento da aplicação

A aplicação deverá ser desenvolvida para Android, utilizando Java. O usuário pode adicionar filmes manualmente, visualizar e editar a lista de filmes armazenados localmente, além de buscar filmes em uma API pública e visualizar detalhes completos de cada filme. Todas as informações cadastradas manualmente são salvas no banco de dados SQLite do dispositivo. O aplicativo deve funcionar mesmo sem conexão, para as funcionalidades locais.

Requisitos Funcionais

Cadastro de Filmes

O usuário pode adicionar um novo filme manualmente.
Campos obrigatórios: título, ano, gênero, descrição.
Opcional: imagem do poster.
Ao cadastrar, o filme é salvo no banco SQLite.
O usuário pode editar ou remover filmes da lista local.
A imagem do poster pode ser uma imagem salva no dispositivo ou uma foto tirada com a câmera.
Listagem de Filmes Locais

Exibir todos os filmes cadastrados localmente em uma lista.
Permitir filtro por título, ano ou gênero.
Detalhes do Filme

Ao selecionar um filme local, exibir todos os seus detalhes (título, ano, gênero, descrição, poster).
Busca de Filmes na API

Permitir busca de filmes por título em uma API pública (TMDb).
Exibir resultados em lista, com informações básicas (título, ano e poster).
Ao selecionar um resultado, exibir detalhes completos do filme retornado pela API.
Permitir ao usuário importar um filme encontrado pela API para o catálogo local.
Exclusão e Edição

O usuário pode editar ou remover qualquer filme do catálogo local.
Interface

Informar ao usuário quando não houver conexão para buscas externas.
Requisitos Não Funcionais

Aplicação obrigatoriamente desenvolvida em Java usando Android Studio.
Armazenamento local utilizando exclusivamente SQLite.
Interface seguindo boas práticas de usabilidade, clareza e acessibilidade.
Funcionalidades locais devem estar disponíveis offline.
Tratamento de erros para entradas inválidas do usuário e falhas de conexão.
