# Sistema Distribuído - Pessoas e Veículos

Sistema completo de gerenciamento de pessoas e veículos usando arquitetura de microsserviços com Spring Boot.

## 📋 Estrutura do Projeto

### 🟦 Serviço 1 - Pessoas (Porta 8081)
- **Banco de Dados:** H2 em memória
- **Tabelas:** 
  - `pessoa` (id, nome, cpf)
  - `pessoa_veiculo` (id, id_pessoa, id_veiculo)
- **Endpoints:**
  - `POST /pessoas` - Cadastrar pessoa
  - `GET /pessoas` - Listar todas as pessoas
  - `GET /pessoas/{id}` - Buscar pessoa por ID
  - `DELETE /pessoas/{id}` - Excluir pessoa
  - `POST /pessoas/{idPessoa}/vincular/{idVeiculo}` - Vincular veículo

### 🟩 Serviço 2 - Veículos (Porta 8082)
- **Banco de Dados:** H2 em memória
- **Tabelas:** 
  - `veiculos` (id, modelo, ano, placa)
- **Endpoints:**
  - `POST /veiculos` - Cadastrar veículo
  - `GET /veiculos` - Listar todos os veículos
  - `GET /veiculos/{id}` - Buscar veículo por ID
  - `DELETE /veiculos/{id}` - Excluir veículo

### 🟧 Serviço 3 - Gestor (Porta 8080)
- **Função:** Orquestrador dos serviços (API Gateway)
- **Tecnologia:** RestTemplate para comunicação
- **Endpoints:**
  - `GET /gestor/pessoas` - Listar pessoas (chama Serviço 1)
  - `GET /gestor/pessoas/{id}` - Buscar pessoa
  - `POST /gestor/pessoas` - Cadastrar pessoa
  - `DELETE /gestor/pessoas/{id}` - Excluir pessoa
  - `GET /gestor/veiculos` - Listar veículos (chama Serviço 2)
  - `GET /gestor/veiculos/{id}` - Buscar veículo
  - `POST /gestor/veiculos` - Cadastrar veículo
  - `DELETE /gestor/veiculos/{id}` - Excluir veículo
  - `POST /gestor/vincular/{idPessoa}/{idVeiculo}` - Vincular veículo à pessoa

### 🟨 Cliente Web
- **Páginas:**
  - `pessoas.html` - Cadastro e listagem de pessoas
  - `veiculos.html` - Cadastro e listagem de veículos
  - `relatorio.html` - Relatório visual de pessoas
- **Tecnologias:** HTML5, Bootstrap 5, JavaScript (Fetch API)

## 🚀 Como Executar

### 1. Executar os Serviços (ordem importante!)

#### PowerShell:
```powershell
# Terminal 1 - Serviço Pessoas (8081)
cd "C:\Users\Pichau\Desktop\Outros\Aula 1 - Sistemas Distribuidos\2AvaliaçãoSD\AvaliacaoSDPessoas"
.\mvnw.cmd spring-boot:run

# Terminal 2 - Serviço Veículos (8082)
cd "C:\Users\Pichau\Desktop\Outros\Aula 1 - Sistemas Distribuidos\2AvaliaçãoSD\AvaliacaoSDVeiculo"
.\mvnw.cmd spring-boot:run

# Terminal 3 - Serviço Gestor (8080)
cd "C:\Users\Pichau\Desktop\Outros\Aula 1 - Sistemas Distribuidos\2AvaliaçãoSD\AvaliacaoGestorSD"
.\mvnw.cmd spring-boot:run
```

### 2. Abrir o Cliente Web

Abra qualquer um dos arquivos HTML no navegador:
- `Cliente/pessoas.html`
- `Cliente/veiculos.html`
- `Cliente/relatorio.html`

Ou use um servidor HTTP local:
```powershell
cd "C:\Users\Pichau\Desktop\Outros\Aula 1 - Sistemas Distribuidos\2AvaliaçãoSD\Cliente"
python -m http.server 8000
```

Acesse: `http://localhost:8000/pessoas.html`

## 🧪 Testar a API com cURL

### Cadastrar Pessoa:
```powershell
Invoke-RestMethod -Uri http://localhost:8080/gestor/pessoas -Method POST -Headers @{"Content-Type"="application/json"} -Body '{"nome":"João Silva","cpf":"12345678900"}'
```

### Listar Pessoas:
```powershell
Invoke-RestMethod -Uri http://localhost:8080/gestor/pessoas -Method GET
```

### Cadastrar Veículo:
```powershell
Invoke-RestMethod -Uri http://localhost:8080/gestor/veiculos -Method POST -Headers @{"Content-Type"="application/json"} -Body '{"modelo":"Gol","ano":2020,"placa":"ABC1234"}'
```

### Listar Veículos:
```powershell
Invoke-RestMethod -Uri http://localhost:8080/gestor/veiculos -Method GET
```

### Vincular Veículo à Pessoa:
```powershell
Invoke-RestMethod -Uri http://localhost:8080/gestor/vincular/1/1 -Method POST
```

## 🔧 Tecnologias Utilizadas

- **Backend:** Spring Boot 2.x, Spring Data JPA, H2 Database
- **Frontend:** HTML5, CSS3 (Bootstrap 5), JavaScript (ES6+)
- **Comunicação:** REST API, RestTemplate, CORS habilitado

## 📊 Banco de Dados H2

Consoles H2 disponíveis em:
- Pessoas: http://localhost:8081/h2-console (URL: jdbc:h2:mem:pessoasdb)
- Veículos: http://localhost:8082/h2-console (URL: jdbc:h2:mem:veiculosdb)

**Credenciais:** 
- Username: `sa`
- Password: (deixe em branco)

## ✨ Funcionalidades

✅ CRUD completo de Pessoas e Veículos  
✅ Vinculação de veículos a pessoas  
✅ Interface web responsiva com Bootstrap  
✅ Comunicação entre microsserviços via RestTemplate  
✅ Bancos de dados independentes (H2)  
✅ API Gateway centralizado (Gestor)  
✅ CORS habilitado para desenvolvimento  

## 📝 Observações

- Os bancos H2 são em memória - dados são perdidos ao reiniciar
- Inicie os serviços na ordem: Pessoas → Veículos → Gestor
- O cliente web consome apenas o Serviço Gestor (porta 8080)
- Todos os endpoints possuem CORS habilitado com @CrossOrigin("*")

## 👨‍💻 Autor

Sistema desenvolvido para a disciplina de Sistemas Distribuídos.
