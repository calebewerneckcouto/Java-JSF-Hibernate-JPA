# Sistema de Gerenciamento de Lançamentos JSF
🚧 Projeto em desenvolvimento 🚧

Sistema web empresarial desenvolvido em JavaServer Faces (JSF) para gerenciamento de lançamentos financeiros e cadastro de usuários, implementando uma arquitetura MVC robusta com persistência JPA.

📦 Tecnologias Utilizadas
JavaServer Faces (JSF) 2.2
Java Persistence API (JPA)
Contexts and Dependency Injection (CDI)
BootsFaces
Java 8
🔨 Funcionalidades
📊 Gerenciamento de Lançamentos Financeiros
Cadastro e edição de lançamentos
Listagem com paginação
Relatórios por período e tópico
Integração com sistema de autenticação
👤 Gerenciamento de Usuários
Sistema completo de cadastro
Autenticação com login/senha
Upload e processamento de fotos de perfil
Validação de CEP integrada com API externa
📁 Estrutura do Projeto
src/main/java/
├── br.com.converter/
│   ├── CidadesConverter.java
│   └── EstadoConverter.java
├── br.com.cursojsf/
│   ├── LancementoBean.java
│   ├── PessoaBean.java
│   └── ...
├── br.com.dao/
│   └── DaoGeneric.java
└── br.com.jpautil/
    └── JPAUtil.java


    🛠️ Como Usar

1
Pré-requisitos
Java EE 7 ou superior
Servidor de aplicação compatível (JBoss, GlassFish, etc.)
Banco de dados relacional
Maven para gerenciamento de dependências

# Clone o repositório
git clone https://seu-repositorio.git

# Compile o projeto
mvn clean install

# Deploy no servidor de aplicação
Acesse o Sistema
http://localhost:8080/meuprimeiroprojetojsf/index.jsf


#Arquitetura
classDiagram
    %% Beans
    class LancementoBean {
        -Lancamento lancamento
        -List~Lancamento~ lancamentos
        -DaoGeneric~Lancamento~ daoGeneric
        -IDaoLancamento daoLancamento
        +salvar()
        +carregarLancamentos()
        +remover()
    }
    
    class PessoaBean {
        -Pessoa pessoa
        -List~Pessoa~ pessoas
        -DaoGeneric~Pessoa~ daoGeneric
        -IDaoPessoa iDaoPessoa
        -List~SelectItem~ estados
        -List~SelectItem~ cidades
        -Part arquivofoto
        -JPAUtil jpaUtil
        +salvar()
        +pesquisaCep()
        +carregaCidades()
        +logar()
        +deslogar()
    }
    
    class RelLancamento {
        -Date dataIni
        -Date dataFim
        -String topico
        -List~Lancamento~ lancamentos
        -IDaoLancamento daoLancamento
        -DaoGeneric~Lancamento~ daoGeneric
        +bucarLancamento()
    }
    
    class RelUsuario {
        -Date dataIni
        -Date dataFim
        -String nome
        -List~Pessoa~ pessoas
        -IDaoPessoa iDaoPessoa
        -DaoGeneric~Pessoa~ daoGeneric
        +relPessoa()
    }
    
    %% DAOs
    class DaoGeneric~E~ {
        #EntityManager entityManager
        +salvar(E entidade)
        +merge(E entidade)
        +delete(E entidade)
        +getListEntity(Class~E~ entidade)
        +getListEntityLimit10(Class~E~ entidade)
    }
    
    class IDaoLancamento {
        <<interface>>
        +consultar(Long codUser)
        +consultarLimit10(Long codUser)
        +relatorioLancamento(String topico, Date dataIni, Date dataFim)
    }
    
    class IDaoPessoa {
        <<interface>>
        +consultarUsuario(String login, String senha)
        +listaEstados()
        +relatorioPessoa(String nome, Date dataIni, Date dataFim)
    }
    
    %% Conversores
    class CidadesConverter {
        <<@FacesConverter>>
        +getAsObject(FacesContext, UIComponent, String codigoCidade)
        +getAsString(FacesContext, UIComponent, Object cidade)
    }
    
    class EstadoConverter {
        <<@FacesConverter>>
        +getAsObject(FacesContext, UIComponent, String codigoEstado)
        +getAsString(FacesContext, UIComponent, Object estado)
    }
    
    %% Entidades
    class Lancamento {
        #id Long
        #usuario Pessoa
        #dataIni Date
        #dataFin Date
        #topico String
    }
    
    class Pessoa {
        #id Long
        #login String
        #senha String
        #nome String
        #fotoIconBase64Original byte[]
        #fotoIconBase64 String
        #estados Estados
        #cidades Cidades
    }
    
    class Estados {
        #id Long
        #nome String
    }
    
    class Cidades {
        #id Long
        #nome String
        #estados Estados
    }

    %% Relacionamentos
    LancementoBean --> DaoGeneric~Lancamento~
    LancementoBean --> IDaoLancamento
    PessoaBean --> DaoGeneric~Pessoa~
    PessoaBean --> IDaoPessoa
    RelLancamento --> IDaoLancamento
    RelLancamento --> DaoGeneric~Lancamento~
    RelUsuario --> IDaoPessoa
    RelUsuario --> DaoGeneric~Pessoa~
    
    Lancamento --> Pessoa
    Pessoa --> Estados
    Pessoa --> Cidades
    Cidades --> Estados
    
    %% Conversores
    CidadesConverter ..> Cidades : converte
    EstadoConverter ..> Estados : converte

    Explicação do Diagrama
O diagrama acima ilustra a arquitetura completa do sistema, onde:

As setas sólidas (-->) indicam dependências diretas entre componentes
As setas pontilhadas (..>) mostram relacionamentos de conversão
DaoGeneric~E~ representa um DAO genérico que pode trabalhar com qualquer entidade
Os estereótipos <<interface>> indicam contratos que devem ser implementados
@FacesConverter indica conversores JSF personalizados
🔒 Segurança
Autenticação obrigatória para funcionalidades restritas
Validação de entrada em todos os formulários
Proteção contra CSRF implementada
Senhas armazenadas de forma segura
📈 Melhorias Futuras
Implementação de cache para consultas frequentes
Adição de testes unitários
Documentação mais detalhada dos métodos
Otimização de queries SQL
