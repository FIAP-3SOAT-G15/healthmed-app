# Estratégia de testes

## Contexto

Para proporcionar um serviço de maior qualidade e que seja capaz de suportar 20.000 usuários simultâneos em horários de pico, precisamos definir uma boa estratégia de testes.

## Decisão

Adotaremos uma abordagem de testes baseada na pirâmide de testes, que inclui:

- testes unitários
- testes de integração
- testes end-to-end
- testes de performance / carga (ex.: usando Gatling)

Os testes unitários e de integração serão executados nas pipelines de CI, e um quality gate de 80% será definido (com o SonarQube) como requisito. BDD também será utilizado.

- Implementação
Testes Unitários:

## Consequências

### Positivas

- detecção precoce de possíveis problemas antes de aplicar mudanças.
- maior nível de confiança no sistema como um todo.
- melhor entendimento dos testes com cenários claramente definidos com BDD.

### Negativas

- tempo de implementação.
- complexidade na configuração dos ambientes de teste.
