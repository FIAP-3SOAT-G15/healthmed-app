# Observabilidade

## Contexto

É crucial ter uma forma de monitorar o sistema para guarantir a prestação de um serviço de qualidade e sua alta disponibilidade. Precisamos de uma solução com métricas, logs, e alertas.

## Decisão

Adotaremos o CloudWatch para monitoramento e logging, em combinação com AWS X-Ray para rastreamento de requests.

## Consequências

### Positivas

- logs permitem identificar bugs e/ou anomalias nas regras de negócio
- alarmes ajudam a resolver incidentes mais rápido (ou antes que aconteçam)

### Negativas

- métricas podem se tornar facilmente muito caras (muitas startups tem como um dos maiores custos as plataformas de monitoramento como DataDog, por exemplo)
- complexidade para coletar e armazenar logs (talvez precise de um OpenSearch para pesquisas)
- é preciso disciplina para não vazar dados sensîveis (PII) nos logs (LGPD)
