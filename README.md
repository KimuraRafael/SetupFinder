# SetupFinder 🚀

Sistema backend em desenvolvimento que cadastra peças de PC (CPU, GPU, RAM etc.) e planeja sugerir setups completos com melhor custo-benefício, usando lógica de compatibilidade e banco de dados.

**Projeto em fase inicial – ainda em construção e sem funcionalidades completas.**

## ✨ Funcionalidades atuais (em desenvolvimento)
- Cadastro básico de peças
- Conexão com banco H2 para armazenamento temporário

## 🛠️ Tecnologias

[![Java 17+](https://img.shields.io/badge/Java%2017+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org)
[![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)](https://git-scm.com)
[![H2 Database](https://img.shields.io/badge/H2%20Database-000000?style=for-the-badge&logoColor=white)](https://h2database.com)
[![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white)](https://junit.org)



**Pretendo alterar o database futuramente quando o projeto tomar mais forma.** (Ex: migrar para PostgreSQL ou outro persistente para produção.)

## 🚀 Como rodar localmente
(O projeto ainda não está pronto para uso externo – essas instruções são só para desenvolvimento pessoal.)

## Endpoints disponíveis (testados no Postman)

Os endpoints CRUD para peças/componentes já estão operando corretamente.

GET /listarComponentes (lista todas as peças)
Retorna array JSON de peças cadastradas.
POST /adicionarComponente (cadastra nova peça)
Body JSON esperado: {
  "descricao": "Placa de Vídeo ASUS DUAL RTX 5060",
  "componente": "Placa_de_vídeo",
  "valor": 2399.99,
  "quantidade": 1,
  "dataValor": "2025-01-20"
}
PUT /atualizarComponente/{id} (atualiza peça)
Retorna mensagem de sucesso
DELETE /pecas/{id} (remove peça)
Retorna mensagem de sucesso.

## 📸 Demonstração 
Aqui vão screenshots reais dos endpoints funcionando (com verificação antes/depois onde aplicável):

### GET - Listando componentes/peças
![Listando componentes](ListarComponentes.png)

### POST - Adicionando componente
![Adicionando componente](AdicionaComponente.png)

### PUT - Atualizando componente

Antes da atualização:
![Antes da atualização](Antes_AtualizarComponente.png)

Mensagem de Retorno após alterar os dados devidamente:
![Execução do Endpoint](AtualizarComponente_Executado.png)

Resultado da atualização:
![Depois da atualização](Depois_AtualizarComponente.png)

### DELETE - Removendo componente

![Antes de deletar](Antes_AtualizarComponente.png)

![Execução do Endpoint](DeletarComponente.png)

Resultado do delete:
![Depois de deletar](Depois_DeletarComponente.png)

**Nota:** Capturas reais mostrando status de sucesso e respostas. Dados de teste no H2.



