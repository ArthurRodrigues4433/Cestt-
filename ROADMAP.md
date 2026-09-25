# Cesttô API — Roadmap de Desenvolvimento

## Objetivo

Desenvolver gradualmente uma API REST para planejamento e conferência de compras de supermercado, consolidando os fundamentos de Java e Spring Boot durante a construção de um MVP funcional.

## Como trabalhar

1. Trabalhar em uma tarefa por vez.
2. Entender a regra de negócio antes de implementar.
3. Evitar adicionar funcionalidades fora do escopo atual.
4. Testar cada comportamento implementado.
5. Atualizar este documento ao concluir uma tarefa.
6. Fazer commits pequenos e com mensagens descritivas.

## Etapas

### Preparação

* [x] Criar projeto Spring Boot.
* [x] Executar a aplicação localmente.
* [x] Configurar Swagger / OpenAPI.
* [x] Criar README inicial.
* [x] Inicializar Git e publicar o repositório.

### Etapa 1 — Fundamentos REST

* [x] Entender requisição e resposta HTTP.
* [x] Criar o primeiro controller.
* [x] Criar um endpoint GET simples.
* [x] Testar o endpoint no Swagger.
* [x] Trabalhar com parâmetros de rota usando @PathVariable.
* [x] Trabalhar com parâmetros de consulta usando @RequestParam.
* [x] Criar endpoints GET para operações simples: dobro, soma e subtotal.
* [x] Criar a classe FeiraRequest com os atributos nome e supermercado.
* [x] Implementar getters e setters em FeiraRequest.
* [x] Criar o endpoint POST /api/feiras.
* [x] Receber dados JSON usando @RequestBody.
* [x] Testar o POST pelo Swagger e devolver uma mensagem com os dados recebidos.
* [x] Compreender e consolidar o fluxo completo da requisição na aplicação.

### Etapa 2 — Feira

* [x] Definir os dados e estados de uma feira.
* [x] Definir as regras de criação e encerramento.
* [x] Criar a estrutura de domínio.
* [x] Implementar a camada de serviço.
* [x] Expor os primeiros endpoints da feira.
* [x] Testar as regras implementadas.
* [x] Criar testes automatizados com JUnit para o FeiraService.
* [x] Testar o status inicial EM_ANDAMENTO.
* [x] Testar a finalização de uma feira em andamento.
* [x] Testar o bloqueio da finalização de uma feira cancelada.
* [x] Testar o bloqueio da finalização repetida de uma feira finalizada.
* [x] Testar o cancelamento de uma feira em andamento.
* [x] Testar o bloqueio do cancelamento repetido.
* [x] Testar o bloqueio do cancelamento de uma feira finalizada.
* [x] Testar a busca de feira por ID.
* [x] Testar o comportamento para ID inexistente.
* [x] Revisar Controller, Service e domínio.
* [x] Implementar a regra temporária de apenas uma feira em andamento por vez.
* [x] Criar endpoints GET para listar feiras e consultar por ID.
* [x] Implementar finalização e cancelamento de feiras por ID.
* [x] Implementar exclusão de feira cancelada.
* [x] Testar bloqueio de exclusão para feiras em andamento e finalizadas.
* [x] Testar exclusão de feira inexistente.
* [x] Validar entrada de dados no controller (campos vazios/nulos).
* [x] Retornar HTTP corretos para os fluxos implementados (200, 400, 409 e 204).
* [ ] Implementar persistência das feiras.
* [ ] Implementar a regra de uma feira ativa por usuário após a implementação de usuários e autenticação.

### Etapa 3 — Produtos e orçamento

* [x] Definir os dados de um produto planejado.
* [x] Definir a relação entre Feira e Produto.
* [x] Criar a classe Produto.
* [x] Implementar a regra de escolha entre preço de varejo e atacado.
* [x] Considerar a quantidade mínima para preço de atacado.
* [x] Utilizar BigDecimal para representar valores monetários.
* [x] Implementar o cálculo do subtotal do produto.
* [x] Adicionar produtos a uma feira em andamento.
* [x] Impedir a adição de produtos em feiras finalizadas.
* [x] Testar a inclusão de produtos em uma feira.
* [x] Testar uma feira iniciando sem produtos.
* [x] Testar a inclusão de múltiplos produtos em uma feira.
* [x] Testar o bloqueio de inclusão de produto em feira finalizada.
* [ ] Implementar alteração de produtos.
* [ ] Implementar quantidades e preços através da API.
* [ ] Calcular o total previsto da feira.
* [ ] Definir as regras para alteração e remoção de produtos.
* [ ] Validar entradas e regras de negócio.
* [ ] Testar os cálculos e regras restantes.
* [ ] Expor endpoints da API para gerenciamento dos produtos.

### Etapa 4 — Persistência

* [ ] Definir o modelo de dados.
* [ ] Configurar JPA e o banco de dados.
* [ ] Criar repositories.
* [ ] Persistir e consultar feiras.
* [ ] Persistir e consultar produtos.
* [ ] Testar a persistência.

### Etapa 5 — Usuários e segurança

* [ ] Implementar cadastro.
* [ ] Implementar autenticação.
* [ ] Proteger endpoints.
* [ ] Restringir os dados por usuário.
* [ ] Implementar a regra de uma feira ativa por usuário.
* [ ] Testar acesso autorizado e não autorizado.

### Etapa 6 — Conferência

* [ ] Definir o modelo dos dados cobrados.
* [ ] Implementar comparação de valores.
* [ ] Registrar divergências e pendências.
* [ ] Implementar o encerramento da conferência.
* [ ] Planejar e desenvolver a integração NFC-e.

### Etapa 7 — Qualidade e entrega

* [ ] Ampliar os testes automatizados.
* [ ] Revisar tratamento de erros e validações.
* [ ] Completar a documentação OpenAPI.
* [ ] Atualizar instruções de execução.
* [ ] Revisar o README.
* [ ] Verificar os critérios de conclusão do MVP.

## Registro de progresso

| Data       | Etapa            | O que foi feito | Próxima ação |
| ---------- | ---------------- | --------------- | ------------ |
| 2026-09-22 | Preparação | Projeto Spring Boot criado, Swagger configurado e repositório publicado no GitHub. | Iniciar fundamentos REST. |
| 2026-09-22 | Fundamentos REST | Endpoints GET e POST implementados e testados no Swagger. Prática de `@PathVariable`, `@RequestParam` e `@RequestBody`. DTO `FeiraRequest` criado com atributos, getters e setters. | Consolidar o fluxo da requisição e resposta para concluir a Etapa 1. |
| 2026-09-23 | Etapa 2 — Feira | Implementação inicial do domínio Feira, enum StatusFeira, regras de finalização e cancelamento, FeiraService e POST `/api/feiras`. Controller testado pelo Swagger. Criados testes JUnit para regras iniciais. | Implementar consulta, cancelamento, exclusão e ampliar os testes. |
| 2026-09-24 | Etapa 2 — Feira | Implementação da gestão de feiras: criação, consulta por ID, listagem, finalização, cancelamento e exclusão de feira cancelada; validações no controller; testes JUnit cobrindo estados e regras de negócio. | Revisar Controller, Service e domínio. |
| 2026-09-25 | Etapa 2 — Feira | Revisão do Controller, Service, domínio, enum e DTO. Testes JUnit concluídos para criação, busca, finalização, cancelamento e exclusão. Correções de organização e tipagem realizadas. | Iniciar Etapa 3 — Produtos e orçamento. |
| 2026-09-25 | Etapa 3 — Produtos e orçamento | Criada a classe Produto e definida sua relação com Feira. Implementadas as regras de escolha entre preço de varejo e atacado, quantidade mínima para atacado e cálculo de subtotal com BigDecimal. Implementada a adição de produtos à Feira e criados testes JUnit para inclusão, múltiplos produtos, lista inicialmente vazia e bloqueio de inclusão em feira finalizada. | Continuar a implementação das regras de produtos e orçamento. |

## Decisões e pendências

- A regra atual permite apenas uma feira `EM_ANDAMENTO` por vez em toda a aplicação.
- A regra definitiva será de uma feira ativa por usuário e será implementada após usuários e autenticação.
- As feiras ainda são armazenadas em memória. A persistência será implementada posteriormente.
- O domínio mantém as regras de transição de estado (`finalizar` e `cancelar`).
- A exclusão definitiva é permitida somente para feiras `CANCELADA`.
- Produtos pertencem a uma feira e, no momento, são mantidos em memória através da relação de domínio entre `Feira` e `Produto`.
- O produto possui preço de varejo e pode possuir preço de atacado, sendo escolhido o preço aplicável conforme a quantidade.
- O subtotal do produto é calculado a partir do preço escolhido e da quantidade.
- Valores monetários são representados utilizando `BigDecimal`.
- A implementação atual de produtos ainda não possui persistência nem endpoints próprios na API.
- Não adicionar funcionalidades ao MVP sem revisar seu impacto no planejamento.