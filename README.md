# Health&Med

[Acesse nosso website!](http://fiap-3soat-g15-healthmed.s3-website-us-east-1.amazonaws.com)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=FIAP-3SOAT-G15_healthmed-app&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=FIAP-3SOAT-G15_healthmed-app)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=FIAP-3SOAT-G15_healthmed-app&metric=coverage)](https://sonarcloud.io/summary/new_code?id=FIAP-3SOAT-G15_healthmed-app)

Autores membros do Grupo 15:

- Bleno Humberto Claus
- Giovanni Di Luca Evangelista
- Lucas Gabriel dos Santos
- Mateus Sales Albino
- Wellyson de Freitas Santos

## Requisitos

Health&Med, uma startup inovadora no setor de saúde, está desenvolvendo um novo sistema que irá revolucionar a Telemedicina no país. Atualmente, a startup oferece a possibilidade de agendamento de consultas e realização de consultas online (Telemedicina) por meio de sistemas terceiros como Google Agenda e Google Meetings.

Recentemente, a empresa recebeu um aporte e decidiu investir no desenvolvimento de um **sistema proprietário**, visando proporcionar um serviço de maior **qualidade**, **segurança dos dados** dos pacientes e **redução de custos**. O objetivo é criar um sistema **robusto, escalável e seguro** que permita o gerenciamento eficiente desses agendamentos e consultas.

Além de conter as funcionalidades de agendamento e realização de consultas online, o sistema terá o diferencial de uma nova funcionalidade: o Prontuário Eletrônico. O Prontuário Eletrônico permitirá o armazenamento e compartilhamento de documentos, exames, cartão de vacinas, e outros registros médicos entre as partes envolvidas, garantindo maior assertividade nos diagnósticos.

Para viabilizar o desenvolvimento de um sistema que esteja em conformidade com as melhores práticas de qualidade e arquitetura de software, a Health&Med contratou os alunos do curso (SOAT) para fazer a análise do projeto e a arquitetura do software.

[Continuar lendo...](/docs/README)

## Decisões de Arquitetura

1. [Infraestrutura](/docs/001-infraestrutura.md)
2. [Microserviços](/docs/002-microservicos.md)
3. [Bancos de dados](/docs/003-bancos-de-dados.md)
4. [Estratégia de testes](/docs/004-estrategia-de-testes.md)
5. [CI/CD](/docs/005-ci-cd.md)
6. [Orquestração de containers](/docs/006-orquestracao-de-containers.md)
7. [Observabilidade](/docs/007-observabilidade.md)
8. [Autenticação e autorização](/docs/008-autenticacao-e-autorizacao.md)
9. [Validação de médicos](/docs/009-validacao-de-medicos.md)
10. [Mensageria](/docs/010-mensageria.md)
11. [Notificações](/docs/011-notificacoes.md)
12. [Videoconferências](/docs/012-videoconferencias.md)
13. [Prontuário Eletrônico](/docs/013-prontuario-eletronico.md)
14. [LGPD](/docs/014-lgpd.md)
15. [Otimização de rede](/docs/015-otimizacao-de-rede.md)

## Diagramas de Arquitetura

### Diagrama de Contexto C4

[![Diagrama de Contexto C4](/docs/diagrams/c4-context.png)](/docs/diagrams/c4-context.png)

### Diagrama de Container C4

[![Diagrama de Container C4](/docs/diagrams/c4-container.png)](/docs/diagrams/c4-container.png)

### Diagrama de Infraestrutura

[![Diagrama de Infraestrutura](/docs/diagrams/infra-diagram.png)](/docs/diagrams/infra-diagram.png)

### Diagramas de Sequência

TODO

### Diagramas de Entidade-Relacionamento

### Cadastro

TODO

### Agendamento

TODO

### Diagramas de Estado

#### Estados de Consulta

[![Estados de Consulta](/docs/diagrams/appointment-states.png)](/docs/diagrams/appointment-states.png)

## CI/CD

Descrição dos workflows do GitHub Actions:

- [build.yaml](.github/workflows/build.yaml): faz o build da aplicação, executando testes e análise estática.
- [destroy.yaml](.github/workflows/destroy.yaml): destrói todos os recursos de infra na AWS.
- [docs.yaml](.github/workflows/docs.yaml): cria o website para a documentação contida em [/docs](/docs).
- [provision.yaml](.github/workflows/provision.yaml): realiza o provisionamento dos recursos de infra na AWS.

## Análise Estática

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=FIAP-3SOAT-G15_healthmed-app&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=FIAP-3SOAT-G15_healthmed-app)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=FIAP-3SOAT-G15_healthmed-app&metric=coverage)](https://sonarcloud.io/summary/new_code?id=FIAP-3SOAT-G15_healthmed-app)

Projeto no SonarCloud: https://sonarcloud.io/project/overview?id=FIAP-3SOAT-G15_healthmed-app

## Executar

```bash
docker compose up
```

### Mappers

```
mvn clean compile
```

### Testes

```
mvn clean verify
```

Testes de integração:

```
mvn clean verify -DskipITs=false
```

### ktlint

```
mvn antrun:run@ktlint-format
