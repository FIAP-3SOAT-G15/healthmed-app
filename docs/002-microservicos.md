# Microserviços

## Contexto

De acordo com os requisitos não funcionais, o sistema precisa ser robusto e escalável, além de haver uma preocupação para reduzir custos e da garantia das melhores práticas de arquitetura de software. A escolha entre monolito e microserviços tem muita influência nesses quesitos.

## Decisão

Inicialmente, é recomendável começar com um monolito modular, isto é, um único serviço com módulos isolados que garantem uma separação tranquila no futuro. Tendo em mente o longo prazo, o aporte da Health&Med recebido, e os requisitos de escalabilidade e alta disponibilidade, optamos por uma arquitetura de microserviços (pelo menos trés: cadastro, agendamento, e prontuário).

## Consequências

### Positivas

- cada microserviço pode ser escalado de forma independente, permitindo uma melhor utilização dos recursos.
- equipes podem trabalhar em diferentes serviços ao mesmo tempo, aumentando a velocidade de desenvolvimento.
- problemas em um microserviço não necessariamente afetam todo o sistema.
- possibilidade de utilizar diferentes tecnologias e linguagens para diferentes microserviços conforme a necessidade.

### Negativas

- pode haver maior latência por causa da comunicação entre microserviços.
- é preciso uma grande infraestrutura para gerenciar, escalar, e monitorar todos os serviços.
