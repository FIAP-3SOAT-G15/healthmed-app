# Orquestração de Containers

## Contexto

Para gerenciar um sistema de Telemedicina que deve ser escalável, resiliente e altamente disponível, é crucial escolher uma solução eficaz para a orquestração de contêineres. O sistema será composto por vários microserviços, cada um rodando em seu próprio contêiner. A escolha da plataforma de orquestração impacta diretamente a escalabilidade, gerenciamento e operação do sistema.

## Decisão

Decidimos adotar Kubernetes como a plataforma de orquestração de contêineres.

## Consequências

### Positivas

- alta disponibilidade e resiliência com recuperação automática de pods ("pingando" health checks).
- escalonamento horizontal automático com base em métricas (requests, CPU, etc).
- disponibiliza várias estratégias para deployment.
- menor chance de erros humanos em deployments.
- alta segurança e isolamento entre serviços.
- versionamento (com manifests).

### Negativas

- configuração e gerenciamento podem ser complexos e exigir uma curva de aprendizado aguda.
- o custo de um cluster EKS, por exemplo, pode ser bem alto.
