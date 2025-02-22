 # 📝Organizador de Tarefas Pessoais
### 🎯 Objetivo
Criar um programa simples que ajude o usuário a gerenciar suas tarefas diárias, aplicando conceitos de POO em Java.

💻 Imagens

![Captura de tela 2025-02-22 112737](https://github.com/user-attachments/assets/679a42d9-fd9a-4052-8476-6c41f7d953fe)
![Captura de tela 2025-02-22 112535](https://github.com/user-attachments/assets/90b36df6-7aa6-4875-9802-6de39da106de)




### ⚙️Requisitos para Inicialização do Projeto

* Java JDK (versão 8 ou superior)
* Banco de Dados MySQL instalado e configurado
* Driver JDBC para MySQL (Connector/J)
* IDE Java (IntelliJ IDEA, Eclipse, VS Code ou outra)

### 🏗️ Configuração do Banco de Dados

* Criar um banco de dados chamado "atividade"
* Criar a tabela "fila" com os seguintes campos:

  
 CREATE TABLE fila
 ### 
    (Id INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL,
    NomeDescricao TEXT,
    DataRegistro DATE
    );

⚙ Como Executar

1 - Clone o repositório do projeto: https://github.com/GabrielPires123/Organizador-tarefas-pessoais.git.

2 - Configure a conexão com o banco de dados no arquivo DB.java (usuário, senha, host).

3 - Compile e execute o projeto na sua IDE.
