# Videoconferência

## Contexto

Precisamos integrar uma funcionalidade de videoconferência que permita a criação de links seguros de reuniões online e a as próprias consultas médicas.

## Decisão

Transmissão/streaming de vídeo não é o core business da Health&Med e decidimos por utilizar um serviço como o Google Meet ou Zoom para as consultas, que oferecem APIs escaláveis e bem documentadas. É possível integrar esses serviços no frontend de forma transparente para o usuário.

## Consequências

### Positivas

- documentação abrangente.
- confiabilidade dos serviços.
- possibilidade de personalização da interface do usuário.

### Negativas

- dependência de um provedor externo.
- custos associados.
- complexidade de integração e configuração.
