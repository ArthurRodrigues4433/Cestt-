
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
* [x] Revisar Controller, Service e domínio.
* [x] Implementar a regra de apenas uma feira ativa por usuário.
* [ ] Implementar persistência das feiras.

### Etapa 3 — Produtos e orçamento

* [ ] Definir os dados de um produto planejado.
* [ ] Implementar inclusão e alteração de produtos.
* [ ] Implementar quantidades e preços.
* [ ] Calcular subtotais e total previsto.
* [ ] Validar entradas e regras de negócio.
* [ ] Testar os cálculos.

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

| Data       | Etapa            | O que foi feito                                                                                                                                                                                                                                             | Próxima ação                                                                                                |
| ---------- | ---------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| 2026-09-22 | Preparação       | Projeto Spring Boot criado, Swagger configurado e repositório publicado no GitHub.                                                                                                                                                                          | Iniciar fundamentos REST.                                                                                   |
| 2026-09-22 | Fundamentos REST | Endpoints GET e POST implementados e testados no Swagger. Prática de `@PathVariable`, `@RequestParam` e `@RequestBody`. DTO `FeiraRequest` criado com atributos, getters e setters.                                                                         | Consolidar o fluxo da requisição e resposta para concluir a Etapa 1.                                        |
| 2026-09-23 | Etapa 2 — Feira  | Implementação inicial do domínio Feira, enum StatusFeira, regras de finalização e cancelamento, FeiraService e POST `/api/feiras`. Controller testado pelo Swagger. Criados e executados testes JUnit para criação, status inicial e regras de finalização. | Revisar Controller, Service e domínio; depois planejar a regra de feira ativa por usuário e a persistência. |
| 2026-09-24 | Etapa 2 — Feira | Implementação completa: FeiraService com gerenciamento de estado (uma feira ativa por vez), métodos finalizar() e cancelar(), endpoints GET `/api/feiras` e `/api/feiras/{id}`, validações de entrada no Controller, testes JUnit abrangentes com 12 cenários testados (criação, estados, regras de negócio, consultas). | Iniciar Etapa 3 — Produtos e orçamento; avaliar persistência. |

## Decisões e pendências

Registrar aqui decisões técnicas importantes, dúvidas em aberto e mudanças aprovadas no escopo.

Não adicionar funcionalidades ao MVP sem revisar seu impacto no planejamento.
