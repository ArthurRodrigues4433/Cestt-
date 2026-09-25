# Cesttô API

> API REST para planejamento e conferência de compras de supermercado.

<div align="center">

**Cesttô** é um projeto em desenvolvimento que busca ajudar pessoas a organizar suas compras e acompanhar a diferença entre os valores planejados e os valores efetivamente cobrados.

[Sobre](#-sobre-o-projeto) •
[Objetivos](#-objetivos) •
[Tecnologias](#-tecnologias) •
[Arquitetura](#-arquitetura) •
[Status](#-status-do-projeto)

</div>

---

## Índice

- [Sobre o projeto](#-sobre-o-projeto)
- [O problema](#-o-problema)
- [Objetivos](#-objetivos)
- [Escopo da aplicação](#-escopo-da-aplicação)
- [Tecnologias](#-tecnologias)
- [Arquitetura](#-arquitetura)
- [Funcionalidades implementadas](#-funcionalidades-implementadas)
- [Como executar os testes](#-como-executar-os-testes)
- [Documentação da API](#-documentação-da-api)
- [Status do projeto](#-status-do-projeto)
- [Autor](#-autor)

---

## Sobre o projeto

O **Cesttô API** é o backend de uma aplicação voltada ao planejamento e à conferência de compras de supermercado.

A proposta é permitir que o usuário organize sua lista de compras, registre quantidades e preços, acompanhe o orçamento previsto e, posteriormente, compare essas informações com os valores cobrados na compra realizada.

A API será responsável por disponibilizar os recursos da aplicação, aplicar as regras de negócio e gerenciar os dados necessários para o funcionamento do sistema.

Este projeto também faz parte da minha jornada de aprendizado em desenvolvimento backend com Java e Spring Boot, com foco na construção de uma aplicação organizada e evolutiva.

## O problema

Durante uma compra de supermercado, acompanhar todos os preços e conferir cada produto no momento do pagamento pode ser trabalhoso.

Além disso, diferenças entre os valores planejados e os cobrados podem passar despercebidas quando a conferência é feita manualmente.

O Cesttô surge como uma proposta para facilitar esse processo, centralizando o planejamento da compra e permitindo uma conferência mais organizada dos valores.

## Objetivos

- Permitir o planejamento de compras de supermercado.
- Registrar produtos, quantidades e preços previstos.
- Calcular o valor estimado da compra.
- Comparar o planejamento com os valores efetivamente cobrados.
- Identificar diferenças entre os valores previstos e realizados.
- Desenvolver uma API com responsabilidades bem definidas e regras de negócio organizadas.

## Escopo da aplicação

A API está sendo construída gradualmente, com foco inicial em um MVP funcional.

Entre as funcionalidades previstas para a aplicação estão:

- Gerenciamento de feiras e listas de compras.
- Cadastro e gerenciamento dos produtos planejados.
- Cálculo de totais e valores previstos.
- Conferência dos valores da compra realizada.
- Consulta ao histórico de compras.

> **Observação:** os itens acima representam o escopo planejado. A implementação de cada funcionalidade será documentada conforme seu desenvolvimento.

## Tecnologias

As principais tecnologias utilizadas na estrutura atual do projeto são:

| Tecnologia | Finalidade |
|---|---|
| Java | Linguagem de programação |
| Spring Boot | Estrutura da aplicação backend |
| Spring MVC | Desenvolvimento da camada web e dos endpoints REST |
| Maven | Gerenciamento de dependências e build |
| Swagger / OpenAPI | Documentação e exploração dos endpoints da API |
| JUnit | Testes automatizados |

## Arquitetura

O projeto utiliza uma arquitetura em camadas, tendo o padrão MVC como referência para a organização dos componentes.

Atualmente, a aplicação possui as seguintes camadas:

- **Controller:** recebe as requisições HTTP e encaminha as operações para a camada de serviço.
- **Service:** concentra a lógica de aplicação relacionada às funcionalidades.
- **Domain:** contém os objetos de domínio e regras de negócio.
- **DTO:** define os objetos utilizados para transportar dados entre a API e seus clientes.

A arquitetura está sendo desenvolvida gradualmente, buscando separar responsabilidades, facilitar a manutenção e permitir a evolução do MVP.

Camadas adicionais, como Repository e persistência de dados, serão implementadas conforme o avanço do projeto.

## Funcionalidades implementadas

### Etapa 1 — Fundamentos REST ✅

- [x] Endpoints GET simples com `@PathVariable` e `@RequestParam`
- [x] Operações matemáticas: dobro, soma e subtotal
- [x] Endpoint POST `/api/feiras` com `@RequestBody`
- [x] Recebimento e processamento de JSON
- [x] Criação e utilização do DTO `FeiraRequest`

### Etapa 2 — Feira ✅

- [x] Modelo de domínio `Feira`
- [x] Estados da feira: `EM_ANDAMENTO`, `FINALIZADA` e `CANCELADA`
- [x] Serviço `FeiraService`
- [x] Criação de feiras
- [x] Consulta de feira por ID
- [x] Listagem de feiras
- [x] Finalização de feira
- [x] Cancelamento de feira
- [x] Exclusão de feira cancelada
- [x] Regra temporária de apenas uma feira em andamento por vez
- [x] Validação de nome e supermercado
- [x] Testes automatizados com JUnit para as regras de negócio
- [x] Tratamento dos principais códigos HTTP utilizados pelos fluxos implementados

### Etapa 3 — Produtos e orçamento 🚧

- [x] Criar o modelo de domínio `Produto`
- [x] Definir a relação entre `Feira` e `Produto`
- [x] Implementar escolha entre preço de varejo e atacado
- [x] Considerar quantidade mínima para preço de atacado
- [x] Utilizar `BigDecimal` para valores monetários
- [x] Calcular subtotal do produto
- [x] Adicionar produtos a uma feira em andamento
- [x] Impedir adição de produtos em feiras finalizadas
- [x] Criar testes automatizados para as regras de produtos
- [ ] Implementar alteração de produtos
- [ ] Implementar quantidades e preços através da API
- [ ] Calcular o total previsto da feira
- [ ] Definir as regras para alteração e remoção de produtos
- [ ] Validar entradas e regras de negócio restantes
- [ ] Expor endpoints para gerenciamento dos produtos

## Endpoints da API

### Feiras

- `POST /api/feiras` — cria uma nova feira
- `GET /api/feiras` — lista todas as feiras
- `GET /api/feiras/{id}` — busca uma feira por ID
- `PUT /api/feiras/{id}/finalizar` — finaliza uma feira em andamento
- `PUT /api/feiras/{id}/cancelar` — cancela uma feira em andamento
- `DELETE /api/feiras/{id}` — remove uma feira cancelada

> Os endpoints relacionados aos produtos serão adicionados conforme o desenvolvimento da Etapa 3.

## Como executar os testes

Execute o comando:

```bash
mvn test
```

Ou, pela IDE, clique com o botão direito no arquivo ou classe de teste e selecione **Run**.

## Documentação da API

A documentação interativa é disponibilizada por meio do Swagger UI durante a execução local da aplicação.

**Endereço local:**

http://localhost:8080/swagger-ui/index.html

A documentação será ampliada conforme novos endpoints forem desenvolvidos.

## Status do projeto

**Em desenvolvimento — Etapa 3 em andamento.**

**Progresso:**

- ✅ Preparação e estrutura inicial
- ✅ Fundamentos REST — Etapa 1
- ✅ Gestão de feiras e regras de negócio — Etapa 2
- 🚧 Produtos e orçamento — Etapa 3
- ⏳ Persistência — Etapa 4
- ⏳ Usuários e segurança — Etapa 5
- ⏳ Conferência — Etapa 6
- ⏳ Qualidade e entrega — Etapa 7

Atualmente, o desenvolvimento está concentrado no domínio de produtos, nas regras de preço, no cálculo de subtotais e na relação entre produtos e feiras.

A aplicação ainda utiliza armazenamento em memória. A persistência dos dados será implementada posteriormente.

O desenvolvimento prioriza a compreensão das decisões técnicas, a implementação progressiva das regras de negócio e a entrega de um MVP funcional.

## Autor

**Arthur Rodrigues**