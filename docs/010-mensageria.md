# Mensageria

## Contexto

Para que o sistema seja escalável e resiliente, quando por exemplo em horário de pico, precisamos desacoplar alguns componentes em nossa arquitetura com a comunicação assíncrona através de um message broker.

## Decisão

Usaremoss o Simple Queue Service (SQS) da AWS para gerenciar a comunicação assíncrona como, por exemplo, no agendamento, notificações, upload de documentos, etc.

## Consequências

### Positivas

- desacoplamento dos microserviços
- serviço escala automaticamente conforme a demanda.
- possui "garantia de entregas" (at-most-once, at-least-once, exactly-once)

### Negativas

- latência adicional devido ao processamento assíncrono.
- custo para processar e armazenar muitas mensagens pode ser alto.
- consistência eventual talvez afete a experiência do usuário.
