# Otimização de Rede

## Contexto

Para garantir que o sistema da Health&Med ofereça uma experiência rápida e confiável, é essencial otimizar a rede e gerenciar o tráfego de forma eficaz.

## Decisão

Adotaremos como estratégia o uso do Elastic Load Balancer (ELB) para distribuir o tráfego para os pods e o CloudFront como CDN para arquivos estáticos, inclusive para os documentos do prontuário.

## Consequências

### Positivas

- menor latência.
- melhor experiência do usuário.

### Negativas

- uso de load balancers e CDN pode aumentar o custo.
- complexidade de configuração de rede.
