# Sistema-Digital-Spa
Este é um projeto acadêmico desenvolvido para a disciplina de Programação de Soluções Computacionais da FASEH. O objetivo é criar o backend para um sistema de gestão de clientes para a empresa SPA Space Wellness, focando na digitalização e otimização de seus processos de cadastro.

👥 Equipe

Líder do Projeto: Rubens
Desenvolvedor: Luan
Desenvolvedor: Caio
Desenvolvedor: Bruno

🎯 Objetivo do Projeto:

O sistema visa substituir o controle manual de clientes por uma solução backend em Java, proporcionando uma forma ágil e segura de gerenciar as informações. O escopo deste projeto está focado em um único módulo: o Cadastro de Clientes.

🚀 Funcionalidades Implementadas

✅ [RF001] Cadastro de clientes  
✅ [RF002] Edição e atualização de clientes  
✅ [RF003] Remoção de clientes  
✅ Validação de campos obrigatórios  
✅ Tratamento de erros de unicidade (CPF, email, telefone)

---

🧠 Regras de Negócio Aplicadas

- [RN001] Todos os campos são obrigatórios
- [RN002] CPF, e-mail e telefone devem ser únicos
- [RN003] Não deve ser possível salvar cadastros com campos vazios

---

📦 Tecnologias Utilizadas

- Java 11+
- JDBC (Java Database Connectivity)
- MySQL 8.0+
- IntelliJ IDEA (recomendado)
- Maven

---

 🗂 Estrutura do Projeto
 
```
src/
├── main/
│ ├── java/
│ │ └── br/com/faseh/cadastroclientes/
│ │ ├── controller/
│ │ │ └── Main.java
│ │ ├── entity/
│ │ │ └── Cliente.java
│ │ ├── repository/
│ │ │ └── ClienteRepository.java
│ │ ├── service/
│ │ │ └── ClienteService.java
│ │ └── util/
│ │ └── Conexao.java
└── pom.xm
```
🧪 Banco de Dados - MySQL

- Script de Criação:

```sql
CREATE DATABASE IF NOT EXISTS sistema_clientes;
USE sistema_clientes;

CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(150) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL UNIQUE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
````
⚠ Certifique-se de que o MySQL esteja ativo e que você saiba a senha do usuário root.

- ⚙ Configuração da Conexão com o Banco
Abra o arquivo:

📁 src/main/java/br/com/faseh/cadastroclientes/util/Conexao.java
```
private static final String URL = "jdbc:mysql://localhost:3306/sistema_clientes?allowPublicKeyRetrieval=TRUE";
private static final String USER = "root";
private static final String PASSWORD = "SUA_SENHA_AQUI";
```
🧰 Como Rodar o Projeto

- Clone o repositório ou baixe o arquivo "CadastroDigitalSpa"

- Abra o projeto com o IntelliJ IDEA

- Aguarde o Maven baixar as dependências

- Execute o script SQL no MySQL

- Configure sua senha de acesso no arquivo Conexao.java

- Rode a classe MainGUI.java

🎮 Como Usar
```
Ao rodar o sistema, será exibido um menu:

==== Sistema de Cadastro Digital ====
1. Cadastrar Cliente
2. Editar Cliente
3. Remover Cliente
4. Pesquisar Cliente por Telefone
5. Sair
```
- Deliverables:

Gerenciamento de Tarefas: O planejamento e acompanhamento das tarefas foram realizados através do Trello. O link do board pode ser encontrado AQUI.

https://trello.com/invite/b/684ed370bbffc1a0713d395b/ATTI1ffb94898214e8295a74757857b4dcb82463E7D5/projeto-programacao-de-solucoes-computacionais-a3

Apoio Visual: O Apoio visual, com as telas de funcionamento do sistema estão disponibilizados no Drive abaixo.

https://drive.google.com/drive/folders/10SD2_BdHbuw5V7pF4g-c09L0UwV5c5-g?usp=sharing

📜 Licença
- Projeto acadêmico sem fins lucrativos. Livre para fins educacionais.
