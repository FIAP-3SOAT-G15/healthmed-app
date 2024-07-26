# Validação de médicos

## Contexto

Precisamos validar a identidade e o CRM dos médicos para garantir que somente profissionais qualificados possam oferecer serviços na Health&Med.

## Decisão

Usaremos uma API para validação do número de CRM e do status do CRM dos médicos. Além disso, implementaremos um processo para validação da identidade do médico (manual ou através de um serviço de identidade para escalar).

## Consequências

### Positivas

- garantia de que apenas médicos verificados prestam consultas.
- redução do risco de fraudes.

### Negativas

- dependência de uma API de CRM e/ou provedor de verificação de identidade (com custos extra).
- aumento no tempo de cadastro devido ao processo de verificação.
