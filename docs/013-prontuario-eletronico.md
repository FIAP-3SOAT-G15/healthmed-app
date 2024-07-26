# Prontuário Eletrônico

## Contexto

O serviço de prontuário eletrônico deve permitir o upload/download, armazenamento duradouro e compartilhamento seguro de documentos médicos (que pertencem ao paciente).

## Decisão

Usaremos o S3 para o armazenamento de arquivos. Implementaremos signed URLs para controle seguro do acesso aos arquivos. Utilizaremos as configurações de durabilidade (9s) e alta disponibilidade oferecidas pelo S3.

## Consequências

### Positivas

- alta durabilidade e disponibilidade dos arquivos.
- o S3 é um serviço relativamente barato e facilmente escalável.
- URLs assinadas expiram rapidamente, garantindo segurança.
- possibilidade de uso de um CDN (CloudFront) para reduzir latência.
- criptografia em repouso (at rest) com chaves KMS.

### Negativas

- configuração minuciosa para evitar vazamento de dados.
