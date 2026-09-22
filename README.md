

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

As principais tecnologias utilizadas na estrutura inicial do projeto são:

| Tecnologia | Finalidade |
|---|---|
| Java | Linguagem de programação |
| Spring Boot | Estrutura da aplicação backend |
| Spring MVC | Desenvolvimento da camada web e dos endpoints REST |
| Maven | Gerenciamento de dependências e build |
| Swagger / OpenAPI | Documentação e exploração dos endpoints da API |

## Arquitetura

O projeto utiliza o padrão **MVC (Model-View-Controller)** como referência, com uma organização em camadas para separar as responsabilidades da aplicação.

A estrutura busca favorecer:

- Separação de responsabilidades.
- Organização das regras de negócio.
- Facilidade de manutenção.
- Evolução gradual da aplicação.
- Maior clareza na comunicação entre as partes do sistema.

A arquitetura será detalhada à medida que os componentes forem implementados.

## Documentação da API

A documentação interativa é disponibilizada por meio do Swagger UI durante a execução local da aplicação.

**Endereço local:**

http://localhost:8080/swagger-ui/index.html

> A documentação será ampliada conforme os endpoints forem desenvolvidos.

## Status do projeto

**Em desenvolvimento — fase inicial.**

O projeto está sendo construído gradualmente, começando pela configuração da aplicação e pela estruturação de sua base backend.

O desenvolvimento prioriza a compreensão das decisões técnicas, a implementação progressiva das regras de negócio e a entrega de um MVP funcional.


## Autor

**Arthur Rodrigues**

