# Escolha dos Bancos de Dados

## Contexto

Precisamos escolher os bancos de dados para os microserviços, levando em consideração custo, escalabilidade e requisitos de desempenho.

## Decisão

Utilizaremos Amazon Aurora com PostgreSQL para os serviços de cadastro e agendamento, e Amazon DynamoDB para o serviço de prontuário eletrônico.

## Consequências

### Positivas

#### Amazon Aurora (PostgreSQL):

- melhor custo-benefício em comparação com RDS de acordo com a calculadora de custo da AWS.
- alta disponibilidade e desempenho com replicação automática.
- familiriedade com queries SQL.
- ACID.
- indexes, sharding, e outras opções para performance.

#### Amazon DynamoDB:

- escalabilidade horizontal automática.
- baixa latência e alta performance.
- modelagem flexível para prontuário eletrônico (documentos).
- streams (eventos), DAX (cache), e outras opções disponíveis.

### Negativas

#### Amazon Aurora (PostgreSQL):

- custo pode ser alto dependendo do uso.
- configuração inicial e gestão de backups e replicações.

#### Amazon DynamoDB:

- necessidade de adaptação à modelagem NoSQL.
- curva de aprendizado para otimização de consultas e índices.
