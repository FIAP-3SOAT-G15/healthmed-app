# Autenticação e Autorização

## Contexto

Precisamos implementar um sistema de autenticação para médicos e pacientes.

## Decisão

Utilizaremos o Amazon Cognito e funções AWS Lambda serão usadas para os fluxos de sign-in e sign-up, permitindo comunicação com o serviço de cadastro (para validação e persistência dos usuários).

## Consequências

### Positivas

- escalabilidade proporcionada pelo Cognito e Lambda.
- segurança de um serviço de autenticação largamente usado.
- suporte a MFA, aumentando a segurança das contas.
- personalização dos fluxos de autenticação via Lambda.

### Negativas

- complexidade inicial na configuração das funções Lambda.

Obs.: a pessoa que é médica cadastrada no sistema também pode desempenhar o papel de paciente em algum momento. Neste caso, optamos por manter dois cadastros separados.
